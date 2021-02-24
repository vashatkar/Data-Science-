package wc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RSJoin extends Configured implements Tool {
	private static final Logger logger = LogManager.getLogger(RSJoin.class);

	private static enum Counterenum{
		PATH2 ,
		TRIANGLE;
	}

	public static class MaxMapper extends Mapper<Object, Text, Text, Text> {
		private final Text key1 = new Text();
		private final Text value1 = new Text();

		@Override
		public void map(final Object key, final Text value, final Context context) throws IOException, InterruptedException {

			Configuration conf = context.getConfiguration();
			int max_value = Integer.parseInt(conf.get("max")) ;

			// System.out.println(" max_value is : " + max_value);
			String[] users = value.toString().split(",");

			if (Integer.parseInt(users[0]) < max_value && Integer.parseInt(users[1]) < max_value) {
				// System.out.println("entered map phase 1");
				key1.set(users[0]);
				value1.set(users[1]);
				context.write(key1, value1);
			}



		}
	}

	public static class MaxReducer extends Reducer<Text, Text, Text, Text> {
		private final Text result = new Text();

		@Override
		public void reduce(final Text key, final Iterable<Text> values, final Context context) throws IOException, InterruptedException {
			// System.out.println("entered reduce phase 1");
			for (Text t: values) {
				result.set(t);
				context.write(key, result);
			}

		}
	}


	public static class JoinMapper1 extends Mapper<Object, Text, Text, Text> {
		private final Text key1 = new Text();
		private final Text value1 = new Text();
		private final Text key2 = new Text();
		private final Text value2 = new Text();

		@Override
		public void map(final Object key, final Text value, final Context context) throws IOException, InterruptedException {

			Configuration conf = context.getConfiguration();
			int max_value = Integer.parseInt(conf.get("max")) ;

			// System.out.println(" max_value is : " + max_value);
			String[] users = value.toString().split(",");

			if (Integer.parseInt(users[0]) < max_value && Integer.parseInt(users[1]) < max_value) {
				// System.out.println("entered map phase 1");
				key1.set(users[1]);
				value1.set("A," + value.toString());

				key2.set(users[0]);
				value2.set("B," + value.toString());

				context.write(key1, value1);
				context.write(key2, value2);
			}

			// System.out.println("finished map phase 2");

		}
	}

	public static class JoinReducer1 extends Reducer<Text, Text, Text, Text> {
		private final ArrayList<Text> listA = new ArrayList<Text>();
		private final ArrayList<Text> listB = new ArrayList<Text>();

		@Override
		public void reduce(final Text key, final Iterable<Text> values, final Context context) throws IOException, InterruptedException {

			listA.clear();
			listB.clear();

			for (Text t: values) {
				String[] values_in = t.toString().split(",");

				if (values_in[0].equals("A")) {
					listA.add(new Text(values_in[1] + "," + values_in[2]));
				} else if (values_in[0].equals("B")) {
					listB.add(new Text(values_in[1] + "," + values_in[2]));
				}
			}

			if (!listA.isEmpty() && !listB.isEmpty()){
				for (Text A : listA) {
					for (Text B: listB){
						String[] A_in = A.toString().split(",");
						String[] B_in = B.toString().split(",");
						context.write(new Text(A_in[0]), B);
						context.getCounter(Counterenum.PATH2).increment(1);
					}
				}
			}

		}
	}


	public static class JoinMapper21 extends Mapper<Object, Text, Text, Text> {
		private final Text key1 = new Text();
		private final Text value1 = new Text();

		@Override
		public void map(final Object key, final Text value, final Context context) throws IOException, InterruptedException {

			// Configuration conf = context.getConfiguration();
			// int max_value = Integer.parseInt(conf.get("max")) ;

			// System.out.println(" max_value is : " + max_value);
			String[] users = value.toString().split(",");

			// if (Integer.parseInt(users[0]) <= max_value && Integer.parseInt(users[1]) <= max_value) {
				// System.out.println("entered map phase 1");
				key1.set(users[2]);
				value1.set("A," + value.toString());

				context.write(key1, value1);

			//}

		}
	}

	public static class JoinMapper22 extends Mapper<Object, Text, Text, Text> {
		private final Text key2 = new Text();
		private final Text value2 = new Text();

		@Override
		public void map(final Object key, final Text value, final Context context) throws IOException, InterruptedException {

			// Configuration conf = context.getConfiguration();
			// int max_value = Integer.parseInt(conf.get("max")) ;

			String[] users = value.toString().split(",");

			// if (Integer.parseInt(users[0]) <= max_value && Integer.parseInt(users[1]) <= max_value) {
				// System.out.println("entered map phase 1");

				key2.set(users[0]);
				value2.set("B," + value.toString());

				context.write(key2, value2);
			// }

		}
	}

	public static class JoinReducer2 extends Reducer<Text, Text, Text, Text> {
		private final ArrayList<Text> listA = new ArrayList<Text>();
		private final ArrayList<Text> listB = new ArrayList<Text>();

		@Override
		public void reduce(final Text key, final Iterable<Text> values, final Context context) throws IOException, InterruptedException {

			listA.clear();
			listB.clear();

			for (Text t: values) {
				String[] values_in = t.toString().split(",");

				if (values_in[0].equals("A")) {
					listA.add(new Text(values_in[1] + "," + values_in[2] + "," + values_in[3] ));
				} else if (values_in[0].equals("B")) {
					listB.add(new Text(values_in[1] + "," + values_in[2] ));
				}
			}

			if (!listA.isEmpty() && !listB.isEmpty()){
				for (Text A : listA) {
					for (Text B: listB){
						String[] A_in = A.toString().split(",");
						String[] B_in = B.toString().split(",");
						if (A_in[0].equals(B_in[1])) {
							context.write(new Text (A_in[0] + A_in[1]), new Text (A_in[2]) );
							context.getCounter(Counterenum.TRIANGLE).increment(1);
						}

					}
				}
			}

		}
	}


	@Override
	public int run(final String[] args) throws Exception {

		// Job One
		final Configuration conf = getConf();
		conf.set("max" , args[2]);
		conf.set("mapreduce.fileoutputcommitter.marksuccessfuljobs", "false");

		final Job jobOne = Job.getInstance(conf, "Word Count");
		// System.out.println("max vaule is :" + args[4]);
		jobOne.setJarByClass(RSJoin.class);
		final Configuration jobConf = jobOne.getConfiguration();
		jobConf.set("mapreduce.output.textoutputformat.separator", ",");
		// Delete output directory, only to ease local development; will not work on AWS. ===========
		// final FileSystem fileSystem = FileSystem.get(conf);
		// if (fileSystem.exists(new Path(args[1]))) {
		//	fileSystem.delete(new Path(args[1]), true);
		// }

		// ================
		jobOne.setMapperClass(MaxMapper.class);
		//job.setCombinerClass(MaxReducer.class);
		jobOne.setReducerClass(MaxReducer.class);
		jobOne.setOutputKeyClass(Text.class);
		jobOne.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(jobOne, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobOne, new Path("/Users/srikanthmandru/intermediate"));
		// FileOutputFormat.setOutputPath(jobOne, new Path(args[1]));

		jobOne.waitForCompletion(true);

		// Job Two

		// final Configuration conf2 = getConf();
		// conf.set("max" , args[4]);
		// conf.set("mapreduce.fileoutputcommitter.marksuccessfuljobs", "false");

		final Job jobTwo = Job.getInstance(conf, "Join One");
		// System.out.println("max vaule is :" + args[4]);
		jobTwo.setJarByClass(RSJoin.class);
		final Configuration jobTwoConf = jobTwo.getConfiguration();
		jobTwoConf.set("mapreduce.output.textoutputformat.separator", ",");
		jobTwoConf.set("max", args[2]);
		jobTwoConf.set("mapreduce.fileoutputcommitter.marksuccessfuljobs", "false");

		jobTwo.setMapperClass(JoinMapper1.class);
		//job.setCombinerClass(MaxReducer.class);
		jobTwo.setReducerClass(JoinReducer1.class);
		jobTwo.setOutputKeyClass(Text.class);
		jobTwo.setOutputValueClass(Text.class);


		FileInputFormat.addInputPath(jobTwo, new Path("/Users/srikanthmandru/intermediate"));
		FileOutputFormat.setOutputPath(jobTwo, new Path("/Users/srikanthmandru/intermediate2"));



		jobTwo.waitForCompletion(true) ;


		// Job Three

		final Job jobThree = Job.getInstance(conf, "Join One");
		// System.out.println("max vaule is :" + args[4]);
		jobThree.setJarByClass(RSJoin.class);
		final Configuration jobThreeConf = jobThree.getConfiguration();
		jobThreeConf.set("mapreduce.output.textoutputformat.separator", ",");
		jobThreeConf.set("max", args[2]);


		// jobThree.setMapperClass(JoinMapper2.class);
		//job.setCombinerClass(MaxReducer.class);
		jobThree.setReducerClass(JoinReducer2.class);
		jobThree.setOutputKeyClass(Text.class);
		jobThree.setOutputValueClass(Text.class);

		MultipleInputs.addInputPath(jobThree, new Path("/Users/srikanthmandru/intermediate2"),
				TextInputFormat.class, JoinMapper21.class);
		MultipleInputs.addInputPath(jobThree, new Path("/Users/srikanthmandru/intermediate"),
				TextInputFormat.class, JoinMapper22.class);

		FileOutputFormat.setOutputPath(jobThree, new Path(args[1]));

		boolean traingles_flag = jobThree.waitForCompletion(true);
		Counters cns = jobThree.getCounters();
		Counter count_value = cns.findCounter(Counterenum.TRIANGLE);
		Counters cns_2 = jobTwo.getCounters();
		Counter count_value_2 = cns_2.findCounter(Counterenum.PATH2);
		logger.info("Total Number of Paths of length 2 in Twitter Graph are :" + count_value_2.getValue() ) ;
		logger.info("Total Number of triangles from Twitter Graph are :" + count_value.getValue()/3 ) ;


		return traingles_flag ? 0 : 1;
	}

	public static void main(final String[] args) {
		if (args.length != 3) {
			throw new Error("Three arguments required:\n<input-dir> <output-dir> <max>");
		}

		try {
			ToolRunner.run(new RSJoin(), args);
		} catch (final Exception e) {
			logger.error("", e);
		}
	}

}