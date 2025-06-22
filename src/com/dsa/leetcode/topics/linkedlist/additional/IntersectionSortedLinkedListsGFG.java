package com.dsa.leetcode.topics.linkedlist.additional;

// https://www.geeksforgeeks.org/problems/intersection-of-two-sorted-linked-lists/1?page=1&category=Linked%20List&sortBy=difficulty
public class IntersectionSortedLinkedListsGFG {

    class Node {
        int data;
        Node next;
        Node(int d)  { data = d;  next = null; }
    }


    public Node findIntersection(Node head1, Node head2) {
        Node node1 = head1;
        Node node2 = head2;
        Node dummy = new Node(-1);
        Node tail = dummy;
        while (node1 != null && node2 != null) {
            if (node1.data == node2.data) {
                tail.next = new Node(node1.data);
                tail = tail.next;
                node1 = node1.next;
                node2 = node2.next;
            } else if (node1.data < node2.data) {
                node1 = node1.next;
            } else {
                node2 = node2.next;
            }
        }
        return dummy.next;
    }
}
