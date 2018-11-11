package algorithms;

import java.util.Stack;

import datastructures.TreeNode;

public class AlgorithmsForBST {
	
	public void inorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode current = root;
		
		while (!stack.isEmpty() || current != null) {
			
			if (current == null) {
				current = stack.pop();
				System.out.print(current.val + " " );
				current = current.right;
			} else {
				stack.push(current);
				current = current.left;
			}
		}
	}

}
