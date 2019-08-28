import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTreeMaxPathSum {

    public int maxPathSum(TreeNode root) {



        return 0;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);

        root.left = left;
        root.right = right;

        postOrderIterative(root);
    }

    public void postOrderIterative(TreeNode root) {

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        TreeNode current = null;

        while (!nodeStack.isEmpty() || current != null) {

            if (current == null) {
                current = nodeStack.pop();
            }

            //
            if (current.left == null && current.right == null) {

                System.out.println(current.val);
                current = null;

            } else {
                // Push Self and Right
                nodeStack.push(current);

                if (current.right != null) {
                    nodeStack.push(current.right);
                }

                // Go Left
                current = current.left;
            }

        }

    }
}
