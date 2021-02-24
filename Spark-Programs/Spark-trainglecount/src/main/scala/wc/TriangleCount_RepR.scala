package wc

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.LogManager
import org.apache.log4j.Level
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession, functions}


object TriangleCount_RepR {
  
  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 3) {
      logger.error("Usage:\nwc.TriangleCountMain <input dir> <output dir> <MAX>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Triangle Count")
    conf.set("spark.logLineage", "true")
    val sc = new SparkContext(conf)


    // load the file into RDD

    val inputRDD = sc.textFile(args(0))

    // filter the data using "MAX_Filter"

    val filteredRDD = inputRDD.filter(line => {
      val users = line.split(",")
      val user1 = users(0)
      val user2 = users(1)
      val max_value = args(2)

      user1.toLong < max_value.toLong && user2.toLong < max_value.toLong
    })

    val RDD1 = filteredRDD.map(line => {
      val users = line.split(",")
      val user1 = users(0)
      val user2 = users(1)

      (user1, user2)

    })


    val path2_accum = sc.longAccumulator("PATH2 Counting Accumulator")
    val traingles_accum = sc.longAccumulator("Triangle Counting Accumulator")



    val RDD3 = RDD1.collect().groupBy{ case (node1, node2) => node1 }

    val broadcastRDD = sc.broadcast(RDD3)

    val PATH2RDD = RDD1.mapPartitions( iter => iter map{ case (user1 , user2) => {
        if (broadcastRDD.value.get(user2).isDefined) {
          val users_list_1: Array[(String, String)] = broadcastRDD.value(user2)
          users_list_1.foreach { case (user1_firstjoin, user2_firstjoin) => {
            path2_accum.add(1)
            if (broadcastRDD.value.get(user2_firstjoin).isDefined) {
              val users_list_2: Array[(String, String)] = broadcastRDD.value(user2_firstjoin)
              users_list_2.foreach { case (user1_secondjoin, user2_secondjoin) => {
                if (user2_secondjoin.equals(user1)) { traingles_accum.add(1)}
              }
              }
            }
          }
          }
      }
    } } , preservesPartitioning = true )


    PATH2RDD.collect().map(println)

    // write the number of triangle to output file
    val outrdd = Array(traingles_accum.value / 3)

    sc.parallelize(outrdd).coalesce(1, shuffle = true).saveAsTextFile(args(1))

    logger.info("Number of Paths of length 2 in the twitter follower dataset are : " + ( path2_accum.value ) )
    logger.info("Number of Triangles in the twitter follower dataset are : " + ( traingles_accum.value / 3 ) )


    }

}