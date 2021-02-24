package wc

import org.apache.log4j.LogManager
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Dataset, SparkSession}


object PageRank {
  
  def main(args: Array[String]) {
    val logger: org.apache.log4j.Logger = LogManager.getRootLogger
    if (args.length != 2) {
      logger.error("Usage:\nwc.PageRank <k> <output>")
      System.exit(1)
    }

    val sparksession = SparkSession.builder().appName("Page Rank")
                      .config("spark.logLineage", "true").getOrCreate()

    // Number of vertices in a chain
    val k = args(0).toInt

    // Compute Number of nodes in total and initial Page Rank to be assigned to each vertex
    val num_of_nodes = k * k
    val initial_PR = 1.0/num_of_nodes


    // create edges table
    var edges = Seq[(Int, Int)]()

    for (i <- 1 to num_of_nodes){
        if (i % k == 0){
          edges = edges :+ (i , 0)
        } else {
          edges = edges :+ (i , i+1)
        }
    }

    // create ranks table
    var ranks = Seq[(Int, Double)]()

    for (i <- 1 to num_of_nodes){
        ranks = ranks :+ (i, initial_PR)
    }
    ranks = ranks :+ (0, 0.0)


    // create RDDs
    val graph_rdd = sparksession.sparkContext.parallelize(edges,4).cache()

    var ranks_rdd = sparksession.sparkContext.parallelize(ranks,4)


    // update pagerank iteratively
    val num_of_iters = 10

    for (i <- 1 to num_of_iters){
      val joined_rdd = graph_rdd.join(ranks_rdd)

      // list all the pairs (destination vertex, Pagerank). If last vertex in chain is reached, add (last vertex, 0.0)
      val temp_rdd2 : RDD[(Int, Double)] = joined_rdd.flatMap(tuple => {
        if (tuple._1.toInt % k == 1) {
          List((tuple._1 , 0.0 ), tuple._2)
        }else {
          List(tuple._2)
        }
      })

      // sum up the page rank for each destination vertex in an edge
      val pagerank_sum_rdd: RDD[(Int, Double)] = temp_rdd2.groupByKey().map(tuple => (tuple._1 , tuple._2.sum ))

      val lost_pagerank = pagerank_sum_rdd.lookup(0).head

      // distribute the lost_mass evenly to each node except dangling node "0" and update "ranks_rdd" for next iteration

      ranks_rdd = pagerank_sum_rdd.map( tuple => {
        if ( tuple._1 != 0 ){
          val alpha = 0.15
          val distrib_mass = lost_pagerank/num_of_nodes
          (tuple._1, (alpha/num_of_nodes) +  ((1 - alpha) * (tuple._2 + distrib_mass)))
          // (tuple._1, (tuple._2 + distrib_mass))
        } else{
          (tuple._1 , 0.0)
        }
      })

      // calculate the total page rank
      val total_pagerank = ranks_rdd.map( tuple => tuple._2).sum()
      logger.warn("distributed mass in iteration " + i + " is : " + lost_pagerank)
      logger.warn("Total Page Rank in iteration " + i + " is : " + total_pagerank)

    }

    // print debug string for ranks
    // print(ranks_rdd.toDebugString)
    logger.info("Debug Info : " + ranks_rdd.toDebugString)

    // Finally, output the page rank of top 101 vertices (including dummy vertex 0 )
    sparksession.sparkContext.parallelize(ranks_rdd.sortByKey().take(101)).saveAsTextFile(args(1))
    sparksession.stop()

  }
}