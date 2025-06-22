package com.dsa.leetcode.topics.linkedlist.additional;

// https://www.geeksforgeeks.org/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/1?page=1&category=Linked%20List&sortBy=difficulty
public class Delete_N_NodesAfter_M_NodesOfALinkedList {

    class Node
    {
        int data;
        Node next;
        Node(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    static void linkdelete(Node head, int n, int m) {
        Node current = head;

        while (current != null) {
            // Skip m-1 nodes
            for (int i = 1; i < m && current != null; i++) {
                current = current.next;
            }

            if (current == null || current.next == null) break;

            // Start from current.next to delete n nodes
            Node temp = current.next;
            for (int i = 1; i <= n && temp != null; i++) {
                temp = temp.next;
            }

            // Link m-th node to the node after the deleted n nodes
            current.next = temp;
            current = temp;
        }
    }
}
