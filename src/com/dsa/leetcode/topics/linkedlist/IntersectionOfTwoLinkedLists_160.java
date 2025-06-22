package com.dsa.leetcode.topics.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists_160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // return bruteForce(headA, headB);
        // return better(headA, headB);
        return optimal(headA, headB);
    }

    public ListNode bruteForce(ListNode headA, ListNode headB) {
        ListNode current = headA;
        Set<ListNode> set = new HashSet<>();
        while (current != null) {
            set.add(current);
            current = current.next;
        }
        current = headB;
        while (current != null) {
            if (set.contains(current)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public ListNode better(ListNode headA, ListNode headB) {
        int n1 = length(headA);
        int n2 = length(headB);
        if (n1 == n2) {
            return findCommonNode(headA, headB);
        }
        if (n1 > n2) {
            int skipCount = n1 - n2;
            ListNode list1 = skip(headA, skipCount);
            return findCommonNode(list1, headB);
        } else {
            int skipCount = n2 - n1;
            ListNode list2 = skip(headB, skipCount);
            return findCommonNode(headA, list2);
        }
    }

    public ListNode skip(ListNode head, int skip) {
        ListNode current = head;
        while (skip > 0) {
            current = current.next;
            skip--;
        }
        return current;
    }

    public ListNode findCommonNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != null && node2 != null) {
            if (node1 == node2) {
                return node1;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return null;
    }

    public int length(ListNode head) {
        ListNode current = head;
        int length = 1;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public ListNode optimal(ListNode headA, ListNode headB) {
        ListNode list1 = headA, list2 = headB;
        while (list1 != list2) { // if both the lists are same, it would exit here
            list1 = list1.next;
            list2 = list2.next;
            if (list1 == list2) { // f we have intersection, or both reached null at same point
                return list1;
            }
            if (list1 == null) {
                list1 = headB;
            }
            if (list2 == null) {
                list2 = headA;
            }
        }
        return list1; // both are equal
    }
}
