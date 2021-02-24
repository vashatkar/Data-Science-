package wc;

import org.apache.commons.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.*;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.Arrays;

public class InputDataGen {


	public static int countNoOfNodes(Path file) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = file.getFileSystem(conf);

		return IOUtils.readLines(fs.open(file), "UTF8").size();

	}

	public static int inputFileLoader(String inputDir, String outputDir)
			throws Exception {

		Configuration conf = new Configuration();
		Path out = new Path(outputDir);
		// if (out.getFileSystem(conf).exists(out))
		//	out.getFileSystem(conf).delete(out, true);
		// out.getFileSystem(conf).mkdirs(out);

		Path input_file_with_pagerank = new Path(out, "initialInput.txt");
		Path input_file = new Path(inputDir , "input_file.txt");

		FileSystem fs = input_file.getFileSystem(conf);
		int no_Of_Nodes = countNoOfNodes(input_file) ;

		// Initialize pagerank to 1 by total number of nodes for all nodes
		double initialPageRank = 1.0 / (double) no_Of_Nodes;

		OutputStream os = fs.create(input_file_with_pagerank);
		LineIterator iter = IOUtils.lineIterator(fs.open(input_file), "UTF8"); // open the input file

		// Iterate through the graph file and write it to another file along with pageranks
		while (iter.hasNext()) {
			String line = iter.nextLine();
			String[] parts = line.toString().split(" ");

			if (parts[1].equalsIgnoreCase("0")){
				Node node = new Node().setPageRank(0.0).setAdjacencyList(
						Arrays.copyOfRange(parts, 1, parts.length));
				IOUtils.write(parts[0] + '\t' + node.writeNode() + '\n', os);
			} else{
				Node node = new Node().setPageRank(initialPageRank).setAdjacencyList(
						Arrays.copyOfRange(parts, 1, parts.length));
				IOUtils.write(parts[0] + '\t' + node.writeNode() + '\n', os);
			}
		}
		os.close();

		// return number of nodes so that we can use it in main page rank algorithm for calculations
		return no_Of_Nodes;

	}

}