package com.dsa.leetcode.topics.linkedlist;

public class ReverseLinkedList_206 {

    public ListNode usingStackBruteForce(ListNode head) {
        if (head == null) return null;

        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        ListNode current = head;

        // Push all nodes onto the stack
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // Pop nodes from the stack to reverse the linked list
        ListNode newHead = stack.pop();
        current = newHead;

        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }

        // Set the next of the last node to null, otherwise it will point to the old head
        // if not cycle is formed
        current.next = null;
        return newHead;
    }

    public ListNode iterative(ListNode head) {
        if (head == null) return null;

        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next; // Store the next node
            current.next = prev; // Reverse the link
            prev = current; // Move prev to current
            current = nextNode; // Move to the next node
        }

        return prev; // New head of the reversed linked list
    }

    public ListNode recursive(ListNode head) {
        if (head == null || head.next == null) { // Base case: if head is null or only one node, return head
            return head;
        }
        ListNode newHead = recursive(head.next); // Recursively reverse the rest of the list
        head.next.next = head; // Set the next node's next to current head
        head.next = null; // Set the current head's next to null to avoid cycle
        return newHead; // Return the new head of the reversed linked list
    }
}
