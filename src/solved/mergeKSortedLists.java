package solved;

public class mergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        if (lists.length == 1) {
            return lists[0];
        }

        int bound = lists.length;

        while (bound > 1) {

            if (bound % 2 == 1 && bound != 1) {
                lists[0] = mergeTwoLists(lists[0], lists[bound - 1]);
            }

            for (int i = 0; i < (bound / 2); i++) {
                lists[i] = mergeTwoLists(lists[i], lists[i + (bound / 2)]);
            }

            bound /= 2;

        }

        return lists[0];

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummyNode = new ListNode(-1);
        ListNode current = dummyNode;

        while (l1 != null || l2 != null) {

            if (l1 == null) {
                current.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                current.next = l1;
                l1 = l1.next;
            } else {

                if (l1.val <= l2.val) {
                    current.next = l1;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    l2 = l2.next;
                }

            }

            current = current.next;

        }

        return dummyNode.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
