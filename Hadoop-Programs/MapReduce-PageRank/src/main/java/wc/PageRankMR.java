package wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class PageRankMR extends Configured implements Tool {
	private static final Logger logger = LogManager.getLogger(PageRankMR.class);


	@Override
	public int run(final String[] args) throws Exception {

		// generate input data as (vertex , page rank, adjacency list)

		int total_no_of_Nodes = InputDataGen.inputFileLoader(args[0], args[1]);

		double dangling_nodes_mass = 0.0;

		// MapReduce Job for page rank
		Configuration conf = getConf();
		Path jobOutputPath = null;
		Path out = new Path(args[1]);
		Path in = new Path(out, "initialInput.txt");

		boolean Job_status_FLAG = true;


		int iterations = 1 ;

		// Set the number of pagerank iterations to be executed with below condition
		// Iteratively update the pagerank
		while (iterations <= 10) {

			// set the output path of the reducer
			jobOutputPath = new Path(out, String.valueOf(iterations));

			// set the number of nodes in the graph and used in reducer for pagerank computation
			conf.setInt("NO_OF_NODES", (int) total_no_of_Nodes);
			conf.setDouble("DANGLING_MASS", (double) dangling_nodes_mass);

			// set the MapReduce Job assigning mapper and reducer, input and output formats
			Job job = Job.getInstance(conf);
			job.setJarByClass(PageRankMR.class);
			job.setMapperClass(PageRankMapperClass.class);
			job.setReducerClass(PageRankReducerClass.class);

			job.setInputFormatClass(KeyValueTextInputFormat.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);

			// FileInputFormat.setInputPaths(job, in);
			NLineInputFormat.setInputPaths(job, in);
			NLineInputFormat.setNumLinesPerSplit(job, 50000);
			FileOutputFormat.setOutputPath(job, jobOutputPath);

			// run the job
			Job_status_FLAG = job.waitForCompletion(true);

			if (!Job_status_FLAG) {
				throw new Exception("Job Execution Failed");
			}

			// If job execution is successful, get dangling nodes mass and update it for next iteration
			long total_sink_sum = job.getCounters().findCounter(PageRankReducerClass.Counter.DANGLING_NODE_MASS).getValue();

			dangling_nodes_mass =  (double) total_sink_sum/100000;

			// Check the total page rank in the current iteration for sanity check
			long total_pr = job.getCounters().findCounter(PageRankReducerClass.Counter.TOTAL_PR).getValue();
			double value_convertion_var = Double.parseDouble(BigInteger.valueOf(10).pow(12).toString());
			double total_pr_val = (double) total_pr/value_convertion_var;

			// Update the input path for the next iteration of MapReduce with output path of current iteration
			in = jobOutputPath;

			// print the information into the log files for debugging purpose
			logger.info("Dangling nodes mass of Page Rank is " + dangling_nodes_mass + " in iteration number " + iterations );
			logger.info("Total Page Rank is " + total_pr_val + " in iteration number " + iterations );
			logger.info("Page Rank iteration number :" + iterations + "is done.");

			iterations++;

		}
		return Job_status_FLAG ? 0 : 1 ;
	}

	public static void main(final String[] args) {
		if (args.length != 2) {
			throw new Error("Two arguments required:\n<input-dir> <output-dir> ");
		}

		try {
			ToolRunner.run(new PageRankMR(), args);
		} catch (final Exception e) {
			logger.error("", e);
		}
	}

}