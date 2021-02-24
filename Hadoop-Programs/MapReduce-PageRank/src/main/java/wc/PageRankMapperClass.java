package wc;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PageRankMapperClass extends Mapper<Text, Text, Text, Text> {

	private Text outKey = new Text();
	private Text outValue = new Text();


	@Override
	protected void map(Text key, Text value, Context context)
			throws IOException, InterruptedException {

		// EMIT (nodeid , vertex)
		if (value.toString().split("\t")[1].equalsIgnoreCase("0")){
			// For dead-end vertex, set the pagerank of those to "0.0"
			// so that page rank of those vertices don't use values assigned them in previous Mapreduce job.
			// (Already, written pagerank for dead-end vertices as "0.0" in previous iteration reducer output.
			// This is to again ensure that value is "0.0")

			String out_value = 0.0 + "\t" + "0";
			context.write(new Text(key), new Text(out_value));

		} else {
			context.write(key, value);
		}

		Node node = Node.getNode(value.toString());

		// For all nodes in the adjacenct list, EMIT (nodeid , pagerank)
		if (node.getAdjacencyList() != null && node.getAdjacencyList().length > 0) {

			// Calculate the new Page Rank

			double pagerank_value = node.getPageRank() / (double) node.getAdjacencyList().length;

			for (int i = 0; i < node.getAdjacencyList().length; i++) {

				String adjacent_node = node.getAdjacencyList()[i];

				outKey.set(adjacent_node);

				outValue.set(String.valueOf(pagerank_value));

				// EMIT (nodeid, pagerank)
				context.write(outKey, new Text (outValue) );

			}

		}



	}
}