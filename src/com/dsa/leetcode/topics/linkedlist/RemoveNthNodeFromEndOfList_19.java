package com.dsa.leetcode.topics.linkedlist;

public class RemoveNthNodeFromEndOfList_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        return bruteForce(head, n);
    }

    public ListNode bruteForce(ListNode head, int n) {
        int length = length(head);
        int skip = length - n;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode current = dummy;
        for (int i = 0; i < skip; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        return dummy.next;
    }

    public int length(ListNode head) {
        ListNode current = head;
        int length = 0;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * Optimal solution using two pointers.
     * The first pointer moves n+1 steps ahead, then both pointers move together until the first reaches the end.
     * The second pointer will be at the node before the one to be removed.
     */
    public ListNode optimal(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        for (int i = 0; i <= n; i++) { // Move first n+1 steps ahead
            first = first.next;
        }

        while (first != null) { // Move both pointers until the first reaches the end
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next; // Skip the nth node from the end
        return dummy.next;
    }
}
