package wc;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.math.BigInteger;

public class PageRankReducerClass extends Reducer<Text, Text, Text, Text> {

	public static final double JF = 0.15; // Jump factor
	private int totalNoOfNodes;
	private double dangling_mass;
	private Text outValue = new Text();

	// Global counters for "Dangling Node Mass" and "total page rank"
	public enum Counter{
		DANGLING_NODE_MASS,
		TOTAL_PR
	}

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		totalNoOfNodes = Integer.parseInt(context.getConfiguration().get("NO_OF_NODES"));
		dangling_mass = Double.parseDouble(context.getConfiguration().get("DANGLING_MASS"));
	}


	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		double pr_sum = 0.0;

		// calculate the hyperjump factor part
		double hyperJump = ((JF) / (double) totalNoOfNodes);

		Node Original_Node = new Node();

		// for all values of a given nodeId
		for (Text node_Value : values) {

			Node node = Node.getNode(node_Value.toString());

			// If it is node, store the node. Otherwise, value is pagerank, so add it to "pr_sum"
			if (node.isNode()) {
				Original_Node = node;
			} else {

				pr_sum += Double.parseDouble(node_Value.toString());
			}

		}

		// Calculate the pagerank of nodeId using pagerank formula given in Textbook

		double newPageRank = hyperJump + ((1.0 - JF) * pr_sum) + ((1.0 - JF)*(dangling_mass/totalNoOfNodes)) ;

		// Increment the "TOTAL_PR" counter which accumulates the pagerank of all the vertices across all reducers
		double value_convertion_var = Double.parseDouble(BigInteger.valueOf(10).pow(12).toString());
		double pr_increment_val = newPageRank * value_convertion_var;
		context.getCounter(Counter.TOTAL_PR).increment((long)pr_increment_val);

		Original_Node.setPageRank(newPageRank);

		outValue.set(Original_Node.writeNode());

		// If the nodeId is "0" i.e., dummy vertex, just get pagerank lost at dangling nodes. Otherwise, EMIT (node_id, vertex)
		if (!key.toString().equalsIgnoreCase("0")){

			if (outValue.toString().split("\t")[1].equalsIgnoreCase("0")){
				// reset the PageRank of Dangling vertices to "0.0"
				String out_value = 0.0 + "\t" + "0";
				outValue.set(new Text (out_value));
			}

			context.write(key, outValue);

		} else{ // If key is "0", then increment Counter which gets all the dangling nodes pageranks from "0" (dummy vertex)

			double all_dangling_pagerank = Original_Node.getPageRank() * 100000;

			context.getCounter(Counter.DANGLING_NODE_MASS).increment((long)all_dangling_pagerank);

		}


	}
}