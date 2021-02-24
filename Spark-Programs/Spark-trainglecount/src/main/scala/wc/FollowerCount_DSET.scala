package wc

import org.apache.log4j.LogManager
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Dataset, SparkSession}


object FollowerCount_DSET {
  
  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 3) {
      logger.error("Usage:\nwc.WordCountMain <input dir> <output dir> <TYPE>")
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Word Count")
    conf.set("spark.logLineage", "true")
    val sc = new SparkContext(conf)


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

    // groupedDataset.map(x => x.mkString(",")).show()

    // groupedDataset.map(x => x.mkString(",")).write.option("header","false").format("text").save(args(1))

    println("Debug info :", groupedDataset.explain(extended=true))

    // groupedDataset.write.option("header","false").format("csv").save(args(1))

    groupedDataset.rdd.saveAsTextFile(args(1))

    logger.info("executed DSET")



  }
}