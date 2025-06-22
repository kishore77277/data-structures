package com.dsa.leetcode.topics.linkedlist.additional;

import com.dsa.leetcode.topics.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeTwoSortedLists_21 {

    public ListNode bruteForceMergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> values = new ArrayList<>();
        while (list1 != null) {
            values.add(list1.val);
            list1 = list1.next;
        }
        while (list2 != null) {
            values.add(list2.val);
            list2 = list2.next;
        }
        Collections.sort(values);
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (int value : values) {
            ListNode newNode = new ListNode(value);
            tail.next = newNode;
            tail = tail.next;
        }
        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        }
        if (list2 != null) {
            tail.next = list2;
        }
        return dummy.next;
    }

}
