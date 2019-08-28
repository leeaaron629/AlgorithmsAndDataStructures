import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class CompleteBalanceTree {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		CompleteBalanceTree cbt = new CompleteBalanceTree(root);
		TreeNode nodeTwo = new TreeNode(2);
		cbt.insert(2);
		
	}

	TreeNode root;
	Deque<TreeNode> dq;

	public CompleteBalanceTree(TreeNode root) {
		this.root = root;
		
		// Populate dq
		dq = new LinkedList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		
		while(!q.isEmpty()) {
			TreeNode current = q.poll();
			
			if (current.left == null || current.right == null)
				dq.offerLast(current);
			
			if (current.left != null)
				q.offer(current.left);
			
			if (current.right != null) 
				q.offer(current.right);
		}
	}

	public int insert(int v) {
		TreeNode node = dq.peekFirst();
		dq.offerLast(new TreeNode(v));
		
		if (node.left == null) {
			node.left = dq.peekLast();
		} else if (node.right == null) {
			node.right = dq.peekLast();
			dq.pollFirst();
		}
		return node.val;
	}

	public TreeNode get_root() {
		return root;
	}

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}
