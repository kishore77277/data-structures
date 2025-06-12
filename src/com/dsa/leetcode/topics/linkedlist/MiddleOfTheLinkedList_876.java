package com.dsa.leetcode.topics.linkedlist;

public class MiddleOfTheLinkedList_876 {

    public ListNode bruteForce(ListNode head) {
        if (head == null) return null;

        int length = 0;
        ListNode current = head;

        // Calculate the length of the linked list
        while (current != null) {
            length++;
            current = current.next;
        }

        // Find the middle index
        int middleIndex = length / 2;
        current = head;

        // Traverse to the middle node
        for (int i = 0; i < middleIndex; i++) {
            current = current.next;
        }

        return current; // Return the middle node
    }

    public ListNode fastSlowPointer(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Move fast pointer two steps and slow pointer one step
        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by 1
            fast = fast.next.next; // Move fast pointer by 2
        }

        return slow; // When fast reaches the end, slow will be at the middle
    }
}
