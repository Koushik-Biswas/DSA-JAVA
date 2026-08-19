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
    public ListNode rotateRight(ListNode head, int k) {
        // Edge cases: empty list, single node, or no rotation
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 1: Find the length of the list and the tail node
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Make the list circular
        tail.next = head;

        // Step 3: Find the new tail
        // Effective rotations needed
        k = k % length; 
        int stepsToNewTail = length - k;
        
        ListNode newTail = head;
        // Traverse to the node just before the break point (length - k - 1 steps)
        for (int i = 0; i < stepsToNewTail - 1; i++) {
            newTail = newTail.next;
        }

        // Step 4: Break the circle and set the new head
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}