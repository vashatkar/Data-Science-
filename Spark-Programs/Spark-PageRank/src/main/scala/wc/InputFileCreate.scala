package wc

import java.io._
import org.apache.log4j.LogManager
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession



object InputFileCreate {
  
  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 2) {
      logger.error("Usage:\nwc.PageRank <k> <output>")
      System.exit(1)
    }

    // val sparksession = SparkSession.builder().appName("Page Rank")
    //                   .config("spark.logLineage", "true").getOrCreate()

    // Number of vertices in a chain
    val k = args(0).toInt

    val num_of_nodes = k * k

    // create edges
    var edges = Seq[(Int, Int)]()

    val file_name = new File("input_file.txt")

    val print_writer = new PrintWriter(file_name)

    for (i <- 1 to num_of_nodes){
        if (i % k == 0){
          edges = edges :+ (i , 0)
          print_writer.write(i.toString + " " + "0" + "\n")
        } else {
          edges = edges :+ (i , i+1)
          print_writer.write(i.toString + " " + (i+1).toString + "\n")
        }
    }
    print_writer.close()


    def writeFile(filename: String, lines: Seq[(Int, Int)]): Unit = {
      val file = new File(filename)
      val print_writer = new PrintWriter(file)
      //val bw = new BufferedWriter(new FileWriter(file))
      for (line <- lines) {
        val node1 = line._1
        val node2 = line._2
        print_writer.write(node1 + " " + node2 + "\n")
      }
      print_writer.close()
    }

    // writeFile(filename = "input_file.txt" , lines = edges)


    // create RDDs
    // val graph_rdd = sparksession.sparkContext.parallelize(edges, 100)
    // sparksession.sparkContext.parallelize(graph_rdd.toSeq).coalesce(1).saveAsTextFile(args(1))

    // graph_rdd.map(x => { val first = x._1
    //  val second = x._2
    //  val outval = first + " " + second
    //  outval
    // }).coalesce(1).sortBy(_.split(" ")(0).toInt, ascending = true).saveAsTextFile(args(1))


    // sparksession.stop()

  }
}