package datastructures;

public class Node {
	
	public Node next;
	public Node prev;
	public Node random;
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
}
