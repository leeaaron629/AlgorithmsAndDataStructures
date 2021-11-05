import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTreeMaxPathSum {

    static int answer = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();

        while (true) {

            while(root != null) {
                stack.push(root);
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (!stack.isEmpty() && root.val == stack.peek().val) {
                root = root.right;
            } else {
                System.out.println(root.val);
                root = null;
            }

        }

    }

    private int postOrderTraversal(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = postOrderTraversal(root.left);
        int right = postOrderTraversal(root.right);

        // Case 1: Left + Root
        // Case 2: Right + Root
        // Case 3: Root
        // Case 4: Left + Root + right

        int result = resolve(left, right, root.val);

        // System.out.println(result);

        answer = Math.max(answer, result);

        setValue(root, left, right);

        return root.val;

    }

    private int resolve(int left, int right, int root) {

        // System.out.println(left + " " + right + " " + root);

        if (left > 0 && right > 0) {
            return root + left + right;
        }

        if (left > right && left > 0) {
            return left + root;
        } else if (right > left && right > 0) {
            return right + root;
        } else {
            return root;
        }
    }

    private void setValue(TreeNode root, int left, int right) {

        // System.out.printf("%d %d %d", root.val, left, right);

        if (left > right) {
            root.val = left + root.val;
        } else {
            root.val = right + root.val;
        }

        // System.out.println(" -> " + root.val);

    }
}
