package wc

import org.apache.log4j.LogManager
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Dataset, SparkSession}


object FollowerCount_RDDG {
  
  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 3) {
      logger.error("Usage:\nwc.WordCountMain <input dir> <output dir> <TYPE>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Word Count")
    conf.set("spark.logLineage", "true")
    conf.set("spark.speculation", "false")
    conf.set("spark.driver.memory", "20G")
    conf.set("spark.executor.memory", "20G")
    conf.set("spark.cores.max", "4")
    val sc = new SparkContext(conf)


      val textFile = sc.textFile(args(0))
      val counts = textFile.flatMap(line => line.split("\n")).flatMap(line => line.split(",")(1))
        .map(word => (word, 1))
        .groupByKey()
        .map(token => (token._1,token._2.sum)).coalesce(10)

      println("Debug info :", counts.toDebugString)
      counts.saveAsTextFile(args(1))
      logger.info("executed RDD-G")



  }
}