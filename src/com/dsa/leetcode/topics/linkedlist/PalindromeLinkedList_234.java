package com.dsa.leetcode.topics.linkedlist;

import java.util.Stack;

public class PalindromeLinkedList_234 {


    public boolean bruteForce(ListNode head) {
        ListNode current = head;
        Stack<Integer> stack = new Stack<>();
        while (current != null) {
            stack.push(current.val);
            current = current.next;
        }
        current = head;
        while (current != null) {
            if (stack.pop() != current.val) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public boolean optimalUsingFastSlowPointers(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalfNode = reverse(slow);
        ListNode secondHead = secondHalfNode;
        ListNode firstHalfNode = head;
        while (secondHalfNode != null) {
            if (firstHalfNode.val != secondHalfNode.val) {
                reverse(secondHead); // Reverse back to restore the original list, before returning
                return false;
            }
            secondHalfNode = secondHalfNode.next;
            firstHalfNode = firstHalfNode.next;
        }
        reverse(secondHead); // reverse back to restore the original list, before returning
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }
}
