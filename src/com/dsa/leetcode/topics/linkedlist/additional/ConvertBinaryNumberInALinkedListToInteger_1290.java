package com.dsa.leetcode.topics.linkedlist.additional;

import com.dsa.leetcode.topics.linkedlist.ListNode;

public class ConvertBinaryNumberInALinkedListToInteger_1290 {


    public int getDecimalValue(ListNode head) {
//        return bruteForce(head);
        return optimal(head);
    }

    /**
     * This method converts a binary number represented by a linked list to its decimal value.
     * The linked list is traversed in reverse order to calculate the decimal value.
     *
     * @param head The head of the linked list representing the binary number.
     * @return The decimal value of the binary number.
     */
    public int bruteForce(ListNode head) {
        head = reverse(head);
        int num = 1;
        int result = 0;
        while (head != null) {
            result = result + head.val * num;
            num = num * 2;
            head = head.next;
        }
        return result;
    }

    public ListNode reverse(ListNode head) {
        ListNode current = head, prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     * This method converts a binary number represented by a linked list to its decimal value
     * using an optimal approach. It processes the linked list in a single pass.
     *
     * @param head The head of the linked list representing the binary number.
     * @return The decimal value of the binary number.
     */
    public int optimal(ListNode head) {
        int result = 0;
        while (head != null) {
            result = (result << 1) | head.val; // Shift left and add current bit
            head = head.next;
        }
        return result;
    }
}
