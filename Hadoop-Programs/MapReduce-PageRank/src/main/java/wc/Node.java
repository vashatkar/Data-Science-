package wc;

import org.apache.commons.lang.StringUtils;
import java.io.IOException;
import java.util.Arrays;

public class Node {

	private double pageRank = 0.0;
	private String[] adjacencyList;

	public double getPageRank() {
		return pageRank;
	}

	public Node setPageRank(double pageRank) {
		this.pageRank = pageRank;
		return this;
	}

	public String[] getAdjacencyList() {
		return adjacencyList;
	}

	public Node setAdjacencyList(String[] adjacencyList) {
		this.adjacencyList = adjacencyList;
		return this;
	}

	public boolean isNode() {
		return adjacencyList != null;
	}

	// This function is used to write node information into a record format
	public String writeNode() {
		StringBuilder sb = new StringBuilder();

		sb.append(pageRank);

		if (getAdjacencyList() != null) {
			sb.append('\t').append(StringUtils.join(getAdjacencyList(), '\t'));
		}

		return sb.toString();
	}


	// This function is used to get the node information from the record or line format of node
	public static Node getNode(String value) throws IOException {
		String[] parsed_values = StringUtils.splitPreserveAllTokens(value, '\t');
		if (parsed_values.length < 1) {
			throw new IOException();
		}
		Node node = new Node().setPageRank(Double.parseDouble(parsed_values[0]));
		if (parsed_values.length > 1) {
			node.setAdjacencyList(Arrays.copyOfRange(parsed_values, 1, parsed_values.length));
		}
		return node;
	}

}