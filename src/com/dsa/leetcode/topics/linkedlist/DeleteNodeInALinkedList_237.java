package com.dsa.leetcode.topics.linkedlist;

public class DeleteNodeInALinkedList_237 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
