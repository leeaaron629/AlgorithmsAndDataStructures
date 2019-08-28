package algorithms;

import java.util.Stack;

import datastructures.TreeNode;

public class AlgorithmsForBST {

	public static void main(String[] args) {
		TreeNode root;

		root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		System.out.println("In Order Traversal: ");
		inOrderTraversal(root);
		System.out.println("\nPost Order Traversal: ");
		postOrderTraversal(root);
		System.out.println("\nPost Order Traversal Double Push Method: " );
		postOrderTraversalDoublePush(root);

	}

	public static void inOrderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;

		while (!stack.isEmpty() || current != null) {

			while (current != null) {
				stack.push(current);
				current = current.left;
			}

			if (current == null) {
				current = stack.pop();
				System.out.print(current.val + " ");
				current = current.right;
			}

		}

	}

	/**
	 * 1. Create an empty stack
	 * 2. Do following while root is not NULL
	 * 		A) Push root right's child to stack
	 * 		B) Push root to stack
	 * 		C) Set root as root's left child
	 * 3. Pop an item and set it as root
	 * 		A) If the popped item has a right child
	 * 			and the right child is at the top of
	 * 			the stack, remove the right child from
	 * 			the stack, and push the root back.
	 * 			Then set root as right child.
	 * 		B) Else Print Value
	 * @param root
	 */
	public static void postOrderTraversal(TreeNode root) {

		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;
		stack.push(root);

		while (true) {

			// Iterate to the left-most Node
			while (current != null) {
				// If right child exists push right child to stack\
				if (current.right != null)  {
					stack.push(current.right);
				}
				// Push current node to stack
				stack.push(current);
				current = current.left;
			}

			// Current is NULL after leaving loop
			current = stack.pop();

			if (stack.isEmpty()) return;

			// Check if current has a right child
			if (current.right != null && current.right == stack.peek()) {
				// Current has a right child

				// Remove right child
				stack.pop();
				// Push current node to top of stack
				stack.push(current);
				// Go to current node right child
				current = current.right;
			} else {
				// Current has no right child -> Print Value
				System.out.print(current.val + " ");
				current = null;
			}
		}

	}

	public static void postOrderTraversalDoublePush(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();

		while (true) {
			while(root != null) {
				stack.push(root);
				stack.push(root);
				root = root.left;
			}

			if (stack.empty())
				return;

			root = stack.pop();

			if (!stack.empty() && stack.peek() == root)
				root = root.right;
			else {
				System.out.print(root.val + " ");
				root = null;
			}
		}
	}

}
