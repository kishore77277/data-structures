package com.dsa.leetcode.topics.linkedlist;
// https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1
public class SortLinkedList012_GFG {

    class Node {
        int data;
        Node next;
        Node(int d)  { data = d;  next = null; }
    }

    Node segregate(Node head) {
        Node zeroDummy = new Node(-1);
        Node oneDummy = new Node(-1);
        Node twoDummy = new Node(-1);

        Node zeroTail = zeroDummy;
        Node oneTail = oneDummy;
        Node twoTail = twoDummy;

        Node current = head;
        while (current != null) {
            if (current.data == 0) {
                zeroTail.next = current;
                zeroTail = zeroTail.next;
            } else if (current.data == 1) {
                oneTail.next = current;
                oneTail = oneTail.next;
            } else {
                twoTail.next = current;
                twoTail = twoTail.next;
            }
            current = current.next;
        }

        // Connect the three lists
        zeroTail.next = (oneDummy.next != null) ? oneDummy.next : twoDummy.next;
        oneTail.next = twoDummy.next;
        twoTail.next = null;

        return zeroDummy.next; // Return the head of the sorted list
    }

    Node bruteForce(Node head) {
        // Count occurrences of 0s, 1s, and 2s
        int count0 = 0, count1 = 0, count2 = 0;
        Node current = head;
        while (current != null) {
            if (current.data == 0) count0++;
            else if (current.data == 1) count1++;
            else count2++;
            current = current.next;
        }

        // Reconstruct the list
        current = head;
        for (int i = 0; i < count0; i++) {
            current.data = 0;
            current = current.next;
        }
        for (int i = 0; i < count1; i++) {
            current.data = 1;
            current = current.next;
        }
        for (int i = 0; i < count2; i++) {
            current.data = 2;
            current = current.next;
        }

        return head; // Return the head of the sorted list
    }
}
