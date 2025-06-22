package com.dsa.leetcode.topics.linkedlist.additional;

import com.dsa.leetcode.topics.linkedlist.ListNode;

public class RotateList_61 {

    public ListNode rotateRight(ListNode head, int k) {
        // return bruteForce(head, k);
        return optimal(head, k);
    }

    public ListNode bruteForce(ListNode head, int k) {
        if (head == null || k == 0 || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        for (int i = 0; i < k; i++) {
            while (current.next != null) {
                prev = current;
                current = current.next;
            }
            prev.next = null;
            current.next = head;
            head = current;
            current = head;
        }
        return current;
    }

    public ListNode optimal(ListNode head, int k) {
        int length = length(head);
        if (head == null || k == 0 || head.next == null) {
            return head;
        }
        ListNode lastNode = head; // Find the last node
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = head; // Connect the last node to the head to make it circular
        ListNode current = head;
        ListNode prev = null;
        k = k % length;
        for (int i = 0; i < length - k; i++) { // Move to the node before the new head
            prev = current;
            current = current.next;
        }
        prev.next = null;
        return current;
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
}
