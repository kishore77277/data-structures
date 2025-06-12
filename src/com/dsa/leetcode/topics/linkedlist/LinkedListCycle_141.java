package com.dsa.leetcode.topics.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle_141 {

    public boolean bruteForce(ListNode head) {
        if (head == null) return false;

        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (visited.contains(current)) {
                return true; // Cycle detected
            }
            visited.add(current);
            current = current.next;
        }

        return false; // No cycle detected
    }

    public boolean fastSlowPointer(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move slow pointer by 1
            fast = fast.next.next; // Move fast pointer by 2

            // Why do they always meet?
            // Let's say the initial distance between fast and slow is d.
            // In the first iteration, fast moves 2 steps and slow moves 1 step.
            // So, the distance between them becomes d - 1.
            // In the next iteration, fast moves 2 steps again, and slow moves 1 step.
            // The distance becomes d - 2.
            // This continues until they meet, which means the distance becomes 0.
            if (slow == fast) {
                return true; // Cycle detected
            }
        }

        return false; // No cycle detected
    }
}
