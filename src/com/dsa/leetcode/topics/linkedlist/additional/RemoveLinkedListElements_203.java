package com.dsa.leetcode.topics.linkedlist.additional;

import com.dsa.leetcode.topics.linkedlist.ListNode;

public class RemoveLinkedListElements_203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode current = head;
        while (current != null) {
            if (current.val == val) {
                tail.next = current.next;
            } else {
                tail.next = current;
                tail = tail.next;
            }
            current = current.next;
        }
        return dummy.next;
    }

    public ListNode bruteForce(ListNode head, int val) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode current = head;
        while (current != null) {
            if (current.val != val) {
                tail.next = new ListNode(current.val);
                tail = tail.next;
            }
            current = current.next;
        }
        return dummy.next;
    }

    public ListNode optimal(ListNode head, int val) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return dummy.next;
    }

    public ListNode optimal2(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = head;
        ListNode prev = dummy;
        while (current != null) {
            if (current.val == val) {
                // Skip the current node
                prev.next = current.next;
            } else {
                // Move prev only if we didn't delete the current node
                prev = current;
            }
            // Always move current to the next node
            current = current.next;
        }

        return dummy.next; // Return the modified list
    }
}
