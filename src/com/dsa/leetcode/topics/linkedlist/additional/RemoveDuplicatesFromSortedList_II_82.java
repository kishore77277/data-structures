package com.dsa.leetcode.topics.linkedlist.additional;

import com.dsa.leetcode.topics.linkedlist.ListNode;

import java.util.Map;
import java.util.TreeMap;

public class RemoveDuplicatesFromSortedList_II_82 {

    public ListNode deleteDuplicates(ListNode head) {
        // return bruteForce(head);
        return optimal(head);
    }

    public ListNode bruteForce(ListNode head) {
        Map<Integer, Integer> map = new TreeMap<>();
        ListNode current = head;
        while (current != null) {
            map.put(current.val, map.getOrDefault(current.val, 0) + 1);
            current = current.next;
        }
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                tail.next = new ListNode(entry.getKey());
                tail = tail.next;
            }
        }
        return dummy.next;
    }

    public ListNode optimal(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            // Check if it's a start of a duplicate group
            if (curr.next != null && curr.val == curr.next.val) {
                // Skip all nodes with curr.val
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                prev.next = curr.next; // remove the whole duplicate block
            } else {
                prev = prev.next; // move prev only if no deletion
            }
            curr = curr.next; // always move curr
        }

        return dummy.next;
    }
}
