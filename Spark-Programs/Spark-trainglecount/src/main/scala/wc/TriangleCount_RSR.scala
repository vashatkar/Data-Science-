package wc

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.LogManager
import org.apache.log4j.Level
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession, functions}


object TriangleCount_RSR {
  
  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 3) {
      logger.error("Usage:\nwc.TriangleCountMain <input dir> <output dir> <MAX>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Triangle Count")
    conf.set("spark.logLineage", "true")
    val sc = new SparkContext(conf)

		// Delete output directory, only to ease local development; will not work on AWS. ===========
//    val hadoopConf = new org.apache.hadoop.conf.Configuration
//    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
//    try { hdfs.delete(new org.apache.hadoop.fs.Path(args(1)), true) } catch { case _: Throwable => {} }
		// ================


    // load the file into RDD

    val inputRDD = sc.textFile(args(0))

    // filter the data using "MAX_Filter"

    val filteredRDD = inputRDD.filter(line => {
      val users = line.split(",")
      val user1 = users(0).toLong
      val user2 = users(1).toLong
      val max_value = args(2)

      user1 < max_value.toLong && user2 < max_value.toLong
    })

    val RDD1 = filteredRDD.map(line => {
      val users = line.split(",")
      val user1 = users(0).toLong
      val user2 = users(1).toLong

      (user1, user2)

    })

    val RDD2 = filteredRDD.map(line => {
      val users = line.split(",")
      val user1 = users(0).toLong
      val user2 = users(1).toLong

      (user2, user1)

    })

    def rsjoinfunc(RDD1_join : RDD[(Long, Long)] ,
                   RDD2_join: RDD[(Long ,Long)]) : RDD[(Long , (Long, Long))] = {
      val joinedRDD = RDD1_join.join(RDD2_join)
      joinedRDD
    }

    val path2_accum = sc.longAccumulator("Triangle Counting Accumulator")
    val traingles_accum = sc.longAccumulator("Triangle Counting Accumulator")

    val PATH2RDD = rsjoinfunc(RDD2, RDD1).map(_._2)

    PATH2RDD.foreach{ x => if(x._1 >= 0 && x._2 >=0) path2_accum.add(1) }
    // PATH2RDD.saveAsTextFile(args(1))

    rsjoinfunc(PATH2RDD, RDD2).map(_._2).foreach{ x => if (x._1 == x._2) traingles_accum.add(1) }

    // write the number of triangle to output file
    val outrdd = Array(traingles_accum.value / 3)

    sc.parallelize(outrdd).coalesce(1, shuffle = true).saveAsTextFile(args(1))

    logger.info("Number of Paths of length 2 in the twitter follower dataset are : " + ( path2_accum.value ) )
    logger.info("Number of Triangles in the twitter follower dataset are : " + ( traingles_accum.value / 3 ) )




  }
}