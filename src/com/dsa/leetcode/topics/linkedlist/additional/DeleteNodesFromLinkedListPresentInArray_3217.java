package com.dsa.leetcode.topics.linkedlist.additional;

import com.dsa.leetcode.topics.linkedlist.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DeleteNodesFromLinkedListPresentInArray_3217 {

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null) {
            if (set.contains(current.next.val)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return dummy.next;
    }

    public ListNode deleteNodes(ListNode head, int[] nums) {
        // Step 1: Store all values from 'nums' into a HashSet for O(1) lookup
        Set<Integer> deleteSet = new HashSet<>();
        for (int num : nums) {
            deleteSet.add(num);
        }

        // Step 2: Create a dummy node to simplify edge cases (e.g., deleting head node)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Step 3: Initialize two pointers:
        // 'prev' will point to the last confirmed node that should remain
        // 'curr' will traverse the list
        ListNode prev = dummy;
        ListNode curr = head;

        // Step 4: Traverse the entire list
        while (curr != null) {
            // If current node's value is in deleteSet, skip it
            if (deleteSet.contains(curr.val)) {
                // Remove 'curr' by linking 'prev' to 'curr.next'
                prev.next = curr.next;
            } else {
                // Otherwise, move 'prev' to 'curr'
                prev = curr;
            }
            // Always move 'curr' forward
            curr = curr.next;
        }

        // Step 5: Return the modified list starting from dummy.next
        return dummy.next;
    }

}
