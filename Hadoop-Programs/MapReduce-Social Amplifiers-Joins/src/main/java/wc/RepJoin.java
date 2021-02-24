package wc;

import java.io.*;
import java.net.URI;
import java.util.*;

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

public class RepJoin extends Configured implements Tool {
	private static final Logger logger = LogManager.getLogger(RepJoin.class);

	private static enum Counterenum{
		PATH2 ,
		TRIANGLE;
	}

	public static class RepMapper extends Mapper<Object, Text, Text, Text> {

		private HashMap<String, HashSet<String>> hash_file_input =  new HashMap<String , HashSet<String>>();

		@Override
		public void setup (Context context ) throws IOException, InterruptedException {
			Configuration conf = context.getConfiguration();
			Integer max = Integer.parseInt(conf.get("max"));

			// BufferedReader rdr = null;

			//try {
				URI[] files = context.getCacheFiles();

				logger.info("Input files are :" + Arrays.toString(files));


				if (files == null || files.length == 0 ) {
					logger.info("No File Found in the directory");
					throw  new FileNotFoundException("Edges dataset is not present in the Distributed cache");
				}

				FileSystem fs = FileSystem.get(conf) ;
				// Read all the files in the Distributed Cache and create hashmap for each file while processing

				// for (URI file : files) {
				//	String file_name ;
				//	int last_index = file.toString().lastIndexOf("/");
				//	if (last_index != -1){
				//		file_name = file.toString().substring(last_index + 1);
				//	}
				//	else {
				//		file_name = file.toString();
				//	}


					// Prepare the HashIndex for the file by traversing each record of file

					// BufferedReader file_reader = new BufferedReader(new FileReader(file_fs));


				BufferedReader file_reader = new BufferedReader(new FileReader("edges.csv"));
				// BufferedReader file_reader = new BufferedReader(new InputStreamReader(fs.open(new Path ("edges.csv")) ));

					String line ;
					while ((line = file_reader.readLine()) != null ){
						String[] user_ids = line.split(",");
						if (Integer.parseInt(user_ids[0]) < max && Integer.parseInt(user_ids[1]) < max ) {
							HashSet<String> users_set =  hash_file_input.get(user_ids[0]);
							if (users_set == null ){
								users_set = new HashSet<String>();
								users_set.add(user_ids[1]);
								hash_file_input.put(user_ids[0], users_set);
							}
							else {
								users_set.add(user_ids[1]);
							}
						}
					}
					logger.info("Done Caching" ) ;
					file_reader.close();

				// }


			//} catch(IOException e){
			//		logger.info("error with the caching and setup" + e.getMessage());
			//}

		}

		@Override
		public void map(final Object key, final Text value, final Context context) throws IOException, InterruptedException {

			Configuration conf = context.getConfiguration();
			Integer max = Integer.parseInt(conf.get("max"));

			String[] user_ids = value.toString().split(",");
			// logger.info("entered Caching" ) ;
			if (Integer.parseInt(user_ids[0]) < max && Integer.parseInt(user_ids[1]) < max ) {

				HashSet<String> user2_set = hash_file_input.get(user_ids[1]);

				// if user2 is present in hashIndex, output the Path2

				if (user2_set != null){
					Iterator<String> user2 = user2_set.iterator();
					while (user2.hasNext()) {
						context.getCounter(Counterenum.PATH2).increment(1);
						HashSet<String> user3_set = hash_file_input.get(user2.next());
						if (user3_set != null ) {
							Iterator<String> user3 = user3_set.iterator();
							while (user3.hasNext()) {
								if ((user3.hasNext())) {
									if ((user3.next() .equals(user_ids[0]))) {
										context.getCounter(Counterenum.TRIANGLE).increment(1);
									}
								}
							}
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

		final Job jobOne = Job.getInstance(conf, "Replicate Join");
		// System.out.println("max vaule is :" + args[4]);
		jobOne.setJarByClass(RepJoin.class);
		final Configuration jobConf = jobOne.getConfiguration();
		jobConf.set("mapreduce.output.textoutputformat.separator", ",");
		// Delete output directory, only to ease local development; will not work on AWS. ===========
		// final FileSystem fileSystem = FileSystem.get(conf);
		// if (fileSystem.exists(new Path(args[1]))) {
		//	fileSystem.delete(new Path(args[1]), true);
		//}
		//if (fileSystem.exists(new Path(args[2]))) {
		//	fileSystem.delete(new Path(args[2]), true);
		//}
		//if (fileSystem.exists(new Path(args[3]))) {
		//	fileSystem.delete(new Path(args[3]), true);
		// }
		// ================

		jobOne.setMapperClass(RepMapper.class);
		jobOne.setNumReduceTasks(0);

		jobOne.setOutputKeyClass(Text.class);
		jobOne.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(jobOne, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobOne, new Path(args[1]));



		// jobOne.addCacheFile(new URI("s3://com.srikanth.my-bucket.rsjoin.1/input/edges.csv"));
		Path temppath = new Path(args[0] + "/edges.csv");
		jobOne.addCacheFile(temppath.toUri());


		boolean traingles_flag = jobOne.waitForCompletion(true);
		Counters cns = jobOne.getCounters();
		Counter count_value = cns.findCounter(Counterenum.TRIANGLE);
		Counters cns_2 = jobOne.getCounters();
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
			ToolRunner.run(new RepJoin(), args);
		} catch (final Exception e) {
			logger.error("", e);
		}
	}

}