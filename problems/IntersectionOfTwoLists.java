import datastructures.ListNode;

public class IntersectionOfTwoLists {

	/**
	 * To return the node of intersection in O(N) time and O(1) memory: Understand
	 * that the length may differ, but if you traverse through the list starting
	 * from A, then B it will be the same distance. So, the trick is to traverse A
	 * once, and traverse B 2nd time and the opposite for the 2nd pointer. That way
	 * when the two pointer finally do meet, they will meet at the same time of
	 * intersection.
	 * 
	 * This is an optimized version of using a counter, which works as well. Suppose
	 * headA takes 5 steps to go from start to end, while headB takes 3 steps to go
	 * from start to end. Then the counter will begin counting, once pointerB
	 * reaches the end and will result in 2 after headA reaches the end as well. Now
	 * you can reset both pointers to their head and have the slower pointer move up
	 * N times depending on the counter.
	 * 
	 * @param headA
	 * @param headB
	 * @return
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		ListNode pointerA = headA;
		ListNode pointerB = headB;
		ListNode lastA = null;
		ListNode lastB = null;

		while (pointerA != pointerB) {

			if (pointerA != null) {

				if (pointerA.next == null)
					lastA = pointerA;

				pointerA = pointerA.next;
			} else {
				// Reset the pointer to headB
				pointerA = headB;
			}

			if (pointerB != null) {

				if (pointerB.next == null)
					lastB = pointerB;

				pointerB = pointerB.next;
			} else {
				// Reset the pointer to headA
				pointerB = headA;
			}

			if (lastA != null && lastB != null && lastA != lastB)
				return null;
		}
		
		return pointerA;
	}
}
