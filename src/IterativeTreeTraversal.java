import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeTreeTraversal {
	
	public static void main(String[] args) {
		IterativeTreeTraversal driver = new IterativeTreeTraversal();
		
	}
	
	public List<Integer> iterativePostOrder(Node root) {
		
		List<Integer> answers = new ArrayList<>();
		
		if (root == null) return answers;
		
		Stack<Node> nodeStack = new Stack<>();
		nodeStack.push(root);
		
		while(!nodeStack.isEmpty()) {
			Node current = nodeStack.peek();
			
			if (current.children != null) {
				for (int i = current.children.size()-1; i >= 0; i--)
					nodeStack.push(current.children.get(i));
				current.children = null;
			} else {
				answers.add(current.val);
				nodeStack.pop();
			}
		}
		
		return answers;
	}

	class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val,List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	}
}
