package wc

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.log4j.LogManager
import org.apache.log4j.Level
import org.apache.spark.sql.{Dataset, SparkSession, functions}


object FollowerCount {
  
  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 3) {
      logger.error("Usage:\nwc.WordCountMain <input dir> <output dir> <TYPE>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Word Count")
    conf.set("spark.logLineage", "true")
    val sc = new SparkContext(conf)

		// Delete output directory, only to ease local development; will not work on AWS. ===========
//    val hadoopConf = new org.apache.hadoop.conf.Configuration
//    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)
//    try { hdfs.delete(new org.apache.hadoop.fs.Path(args(1)), true) } catch { case _: Throwable => {} }
		// ================

    if (args(2) == "R") {

      val textFile = sc.textFile(args(0))
      val counts = textFile.flatMap(line => line.split("\n")).flatMap(line => line.split(",")(1))
        .map(word => (word, 1))
        .reduceByKey(_ + _)

      println("Debug info :", counts.toDebugString)
      counts.coalesce(5).saveAsTextFile(args(1))
      logger.info("executed RDD-R")
    }

    // RDD-R

    if (args(2) == "G") {

      val textFile = sc.textFile(args(0))
      val counts = textFile.flatMap(line => line.split("\n")).flatMap(line => line.split(",")(1))
        .map(word => (word, 1))
        .groupByKey()
        .map(token => (token._1,token._2.sum)).coalesce(1)

      println("Debug info :", counts.toDebugString)
      counts.saveAsTextFile(args(1))
      logger.info("executed RDD-G")
    }

    if (args(2) == "F") {

      val textFile = sc.textFile(args(0))
      val counts = textFile.flatMap(line => line.split("\n")).flatMap(line => line.split(",")(1))
        .map(word => (word, 1))
        .foldByKey(0)((a, b) => a + b ).coalesce(1)

      println("Debug info :", counts.toDebugString)
      counts.saveAsTextFile(args(1))
      logger.info("executed RDD-F")
    }

    if (args(2) == "A") {

      val textFile = sc.textFile(args(0))
      val counts = textFile.flatMap(line => line.split("\n")).flatMap(line => line.split(",")(1))
        .map(word => (word, 1))
        .aggregateByKey(0)((k,v) => k+v , (k,v) => k+v).coalesce(1)

      println("Debug info :", counts.toDebugString)
      counts.saveAsTextFile(args(1))
      logger.info("executed RDD-A")
    }

    if (args(2) == "DSET") {

      val sparkSession = SparkSession.builder().getOrCreate()

      import sparkSession.implicits._

      case class MyCase(user1: String, user2: String)


      val textFile = sparkSession.read.csv(args(0))
      val counts = textFile.toDF("users1","users2")
      val datasetcounts : Dataset[(String,String)] = counts.as[(String, String)]

      // datasetcounts.show()
      // println("class of data is now:" + datasetcounts.getClass)

      val groupedDataset = datasetcounts.groupBy($"users2").count()

      // val resultdata = groupedDataset.select(functions.array("users2", "count"))
      // groupedDataset.show()

      // groupedDataset.write.format("text").save(args(1))
      groupedDataset.map(x => x.mkString(",")).show()
      // groupedDataset.map(x => x.mkString(",")).write.option("header","false").format("text").save(args(1))

      println("Debug info :", groupedDataset.explain())

      groupedDataset.write.option("header","false").format("csv").save(args(1))
      logger.info("executed DSET")
    }


  }
}