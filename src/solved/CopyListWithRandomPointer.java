package solved;

import datastructures.Node;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {

        if (head == null) return null;

        Node cur = head;
        Node copy = new Node();
        Node answer = copy;
        Map<Integer, Node> copiesMap = new HashMap<>();

        if (cur != null) {
            copy.val = cur.val;
            copiesMap.put(copy.val, copy);
        }

        while (cur != null) {

            if (cur.next != null) {

                Node nextCopy = copiesMap.get(cur.next.val);

                if (nextCopy != null) {
                    copy.next = nextCopy;
                } else {
                    copy.next = new Node();
                    copy.next.val = cur.next.val;
                    copiesMap.put(copy.next.val, copy.next);
                }

            }

            if (cur.random != null) {

                Node randomCopy = copiesMap.get(cur.random.val);

                if (randomCopy != null) {
                    copy.random = randomCopy;
                } else {
                    copy.random = new Node();
                    copy.random.val = cur.random.val;
                    copiesMap.put(copy.random.val, copy.random);
                }

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

        printNodes(ans);

    }

    @Test
    public void testNull() {
        Node nullNode = copyRandomList(null);
    }

    @Test
    public void testOne() {
        Node oneNode = new Node(1);
        oneNode.next = null;
        oneNode.random = oneNode;

        printNodes(copyRandomList(oneNode));
    }

    private void printNodes(Node node) {
        if (node == null) {
            System.out.println("NULL");
        }

        while(node != null) {
            System.out.println(node);
            node = node.next;
        }
    }
}
