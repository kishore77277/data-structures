package com.dsa.leetcode.topics.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII_142 {

    public ListNode bruteForce(ListNode head) {
        ListNode current = head;
        Set<ListNode> set = new HashSet<>();
        while (current != null) {
            if (set.contains(current)) { // Cycle detected
                return current;
            }
            set.add(current);
            current = current.next;
        }
        return null; // No cycle detected
    }

    public ListNode optimal(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return getStartNode(head, slow);
            }
        }
        return null;
    }

    /**
     * Why this always works?
     * When the slow and fast pointers meet, it means there is a cycle.
     * When a slow pointer reaches to the start of the cycle, let's say it has traversed l1 distance.
     * The fast pointer has traversed 2 * l1 distance.
     * The distance between the fast pointer and the start of the cycle is d.
     * At the collision point, the fast pointer has traversed 2d distance.
     * And a slow pointer has traversed d distance.
     * So, the distance between the slow pointer and the start of the cycle is d.
     * The remaining distance is l1.
     * So, if we move the slow pointer to the head and move both pointers one step at a time, they will meet at the start of the cycle.
     *
     * @param head The head of the linked list.
     * @param slow The slow pointer, which has collided with the fast pointer.
     * @return The node where the cycle begins.
     */
    public ListNode getStartNode(ListNode head, ListNode slow) {
        ListNode current = head;
        while (current != slow) {
            current = current.next;
            slow = slow.next;
        }
        return current;
    }
}
