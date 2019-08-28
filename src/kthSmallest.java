import java.util.Stack;

public class kthSmallest {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(0);

	}

	public int kthSmallest(TreeNode root, int k) {

		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;

		while (!stack.isEmpty() || current != null) {

			while (current != null) {
				stack.push(current);
				current = current.left;
			}

			if (current == null) {
				current = stack.pop();
				System.out.println(current.val);
				current = current.right;
			}

		}

		return 0;
	}
}
