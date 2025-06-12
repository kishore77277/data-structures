package com.dsa.leetcode.topics.linkedlist;

import java.util.LinkedList;
import java.util.List;

public class OddEvenLinkedList_328 {

    public ListNode oddEvenList(ListNode head) {
        // return bruteForce1(head);
        // return better(head);
        // return optimal1(head);
        return optimalIntuition(head);
    }

    public ListNode bruteForce1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<Integer> list = new LinkedList<>();
        ListNode odd = head;
        while (odd != null && odd.next != null) {
            list.add(odd.val);
            odd = odd.next.next;
        }
        // for last node if missed, think of only 3 nodes
        if (odd != null) {
            list.add(odd.val);
        }
        ListNode even = head.next;
        while (even != null && even.next != null) {
            list.add(even.val);
            even = even.next.next;
        }
        if (even != null) {
            list.add(even.val);
        }
        int index = 0;
        ListNode current = head;
        while (current != null) {
            current.val = list.get(index);
            index++;
            current = current.next;
        }
        return head;

    }

    public ListNode better(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenDummy = new ListNode();
        ListNode oddDummy = new ListNode();
        ListNode evenTail = evenDummy;
        ListNode oddTail = oddDummy;
        int count = 1;
        ListNode current = head;
        while (current != null) {
            if (count % 2 == 1) {
                oddTail.next = current;
                oddTail = oddTail.next;
            } else {
                evenTail.next = current;
                evenTail = evenTail.next;
            }
            current = current.next;
            count++;
        }
        oddTail.next = evenDummy.next;
        evenTail.next = null; // to avoid a cycle
        // think like, 1-2-3-4-5,
        // odd 1-3-5
        // even 2-4
        // but 4 is pointing to Node 5, which creats a cycle
        // 1-3-5-2-4-and back to 5, cycle
        return head;
    }

    public ListNode optimal1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public ListNode optimalIntuition(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
