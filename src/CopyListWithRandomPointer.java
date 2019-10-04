import datastructures.Node;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {

        Node cur = head;
        Node copy = new Node();
        Node answer = copy;
        while (cur != null) {
            copy.val = cur.val;
            if (cur.next != null) {
                copy.next = new Node();
                copy.next.val = cur.next.val;
            }
            if (cur.random != null) {
                copy.random = new Node();
                copy.random.val = cur.random.val;
            }
            copy = copy.next;
            cur = cur.next;
        }

        return answer;
    }

    @Test
    public void test() {

        Node node_1 = new Node(1);
        Node node_2 = new Node(2);
        node_1.next = node_2;
        node_1.random = node_2;
        node_2.next = null;
        node_2.random = node_2;

        Node ans = copyRandomList(node_1);

        while (ans != null) {
            System.out.println(ans);
            ans = ans.next;
        }

    }
}
