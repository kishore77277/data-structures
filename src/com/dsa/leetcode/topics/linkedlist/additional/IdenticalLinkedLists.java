package com.dsa.leetcode.topics.linkedlist.additional;

// https://www.geeksforgeeks.org/problems/identical-linked-lists/1?page=1&category=Linked%20List&sortBy=difficulty
public class IdenticalLinkedLists {

    class Node {
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public boolean areIdentical(Node head1, Node head2) {
        Node node1 = head1;
        Node node2 = head2;
        while (node1 != null && node2 != null) {
            if (node1.data != node2.data) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1 == null && node2 == null;
    }

    public boolean usingRecursion (Node head1, Node head2){
        if (head1 == null || head2 == null) {
            return head1 == head2;
        }
        if (head1.data != head2.data) {
            return false;
        }
        return usingRecursion(head1.next, head2.next);
    }
}
