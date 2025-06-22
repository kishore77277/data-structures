package com.dsa.leetcode.topics.linkedlist;

public class DeleteMiddleNodeOfLinkedList_2095 {

    public ListNode deleteMiddle(ListNode head) {
        return deleteMiddleOptimal2(head);
    }

    /**
     * Using slow and fast pointers without using third prev pointer
     * 1. we will just skip the first iteration for slow
     */
    public ListNode deleteMiddleOptimal2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * Using slow and fast pointers
     * 1. we will use third variable to track prev value of slow
     * 2. prev.next = prev.next.next;
     */
    public ListNode deleteMiddleOptimal1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    /**
     * 1. finding the length
     * 2. traversing till prev of floor(n/2)
     * 3. making current.next = current.next.next;
     */
    public ListNode deleteMiddleBruteForce(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        int length = findLength(head);
        int mid = length / 2;
        ListNode current = head;
        int count = 1;
        while (current != null) {
            if (count == mid) {
                current.next = current.next.next;
                break;
            }
            count++;
            current = current.next;
        }
        return head;
    }

    public static int findLength(ListNode head) {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
