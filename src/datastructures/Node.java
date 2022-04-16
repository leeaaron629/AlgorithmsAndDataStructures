package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
	
	public Node next;
	public Node prev;
	public Node random;
	public List<Node> neighbors = new ArrayList<>();
	public int val;

	public Node() {}

	public Node(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "Node{" +
				"val=" + val +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) return false;

		Node other = (Node) obj;
		return other.val == this.val;
	}

	public static Node simpleGraph() {

		List<Integer> values = Arrays.asList(1,2,3,4,5,6);
		List<Node> nodes = new ArrayList<>();

		for (Integer v : values) {
			nodes.add(new Node(v));
		}

		nodes.get(0).neighbors = List.of(nodes.get(1), nodes.get(2), nodes.get(3));
		nodes.get(3).neighbors = List.of(nodes.get(4), nodes.get(5));
		nodes.get(5).neighbors = List.of(nodes.get(0));

		return nodes.get(0);
	}
}


