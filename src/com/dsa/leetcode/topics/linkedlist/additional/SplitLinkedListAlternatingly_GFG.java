package com.dsa.leetcode.topics.linkedlist.additional;

// https://www.geeksforgeeks.org/problems/split-singly-linked-list-alternatingly/1?page=1&category=Linked%20List&sortBy=difficulty
public class SplitLinkedListAlternatingly_GFG {

    class Node {
        int data;
        Node next;
        Node(int d)  { data = d;  next = null; }
    }

    Node[] alternatingSplitList(Node head) {
        Node[] result = new Node[2];
        Node dummy1 = new Node(-1);
        Node tail1 = dummy1;
        Node dummy2 = new Node(-1);
        Node tail2 = dummy2;
        Node node = head;
        boolean addFirst = true;
        while (node != null) {
            if (addFirst) {
                tail1.next = node;
                tail1 = tail1.next;
            } else {
                tail2.next = node;
                tail2 = tail2.next;
            }
            node = node.next;
            addFirst = !addFirst;
        }
        tail1.next = null;
        tail2.next = null;
        result[0] = dummy1.next;
        result[1] = dummy2.next;
        return result;
    }
}
