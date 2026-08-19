/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        // Dummy node simplifies edge cases where left = 1
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 1. Advance `prev` to the node immediately preceding the `left` position
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // `curr` points to the first node of the sublist to be reversed
        ListNode curr = prev.next;

        // 2. Reverse nodes between `left` and `right` in-place
        for (int i = 0; i < right - left; i++) {
            ListNode nextTemp = curr.next;
            curr.next = nextTemp.next;
            nextTemp.next = prev.next;
            prev.next = nextTemp;
        }

        return dummy.next;
    }
}