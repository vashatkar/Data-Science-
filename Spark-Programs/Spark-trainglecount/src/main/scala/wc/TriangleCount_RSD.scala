package wc

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.LogManager
import org.apache.log4j.Level
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{LongType, StructField, StructType}
import org.apache.spark.sql.{Dataset, Row, SparkSession, functions}


object TriangleCount_RSD {

  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 3) {
      logger.error("Usage:\nwc.TriangleCountMain <input dir> <output dir> <MAX>")
      System.exit(1)
    }


    val sparksession = SparkSession
                      .builder().appName("Traingle Count")
                      .master("yarn").config("spark.logLineage", "true").getOrCreate()

		// Delete output directory, only to ease local development; will not work on AWS. ===========
//    val hadoopConf = new org.apache.hadoop.conf.Configuration
//    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
//    try { hdfs.delete(new org.apache.hadoop.fs.Path(args(1)), true) } catch { case _: Throwable => {} }
		// ================

    import sparksession.implicits._

    val input_schema = StructType(Array (
      StructField("user1" , LongType ,nullable = false) ,
      StructField("user2" , LongType ,nullable = false)))

    val twitterDS = sparksession.read.format("csv").schema(input_schema)
                    .load(args(0)).where($"user1" < args(2).toLong && $"user2" < args(2).toLong)

    val PATH2DS : Dataset[(Row, Row)] =
      twitterDS.as("dataset1").joinWith(twitterDS.as("dataset2"), $"dataset1.user2" === $"dataset2.user1")

    val TRIANGLEDS: Dataset[((Row, Row), Row)] = PATH2DS.as("table1").joinWith(twitterDS.as("table2"),
      $"table1._1.user1" === $"table2.user2" && $"table1._2.user2" === $"table2.user1" )

    // write the number of triangle to output file
    val outrdd = sparksession.sparkContext.parallelize( Array(TRIANGLEDS.count() / 3))

    outrdd.coalesce(1).saveAsTextFile(args(1))





    logger.info("Number of Paths of length 2 in the twitter follower dataset are : " + ( PATH2DS.count()) )
    logger.info("Number of Triangles in the twitter follower dataset are : " + ( TRIANGLEDS.count() / 3 ) )

    println("Number of Paths of length 2 in the twitter follower dataset are : " + ( PATH2DS.count()) )
    println("Number of Triangles in the twitter follower dataset are : " + ( TRIANGLEDS.count() / 3 ) )


  }



}