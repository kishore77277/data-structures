package com.dsa.leetcode.topics.linkedlist;

import java.util.Stack;

// https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
public class AddOneToLinkedList_GFG {

    class Node {
        int data;
        Node next;
        Node(int d) { data = d; next = null; }
    }


    public Node bruteForce(Node head) {
        Stack<Node> stack = new Stack<>();
        Node current = head; // Traverse the linked list and push nodes onto the stack
        while (current != null) {
            stack.push(current);
            current = current .next;
        }
        int carry = 1;
        while (!stack.isEmpty() && carry != 0) { // Pop nodes from the stack and add carry
            int sum = carry + stack.peek().data;
            carry = sum / 10;
            sum = sum % 10;
            stack.pop().data = sum;
        }
        if (carry == 1) { // If there's still a carry left, we need to add a new node at the front
            Node newHead = new Node(1);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }

    public Node betterUsingReversal(Node head) {
        head = reverse(head);
        int carry = 1;
        Node current = head;
        while (current != null && carry != 0) {
            int sum = current.data + carry;
            carry = sum / 10;
            sum = sum % 10;
            current.data = sum;
            current = current.next;
        }
        head = reverse(head);
        if (carry == 1) {
            Node newHead = new Node(carry);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }

    public Node reverse(Node head) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        return prev;
    }

    public int helper(Node head) {
        if (head == null) {
            return 1;
        }
        int carry = helper(head.next);
        int sum = head.data + carry;
        head.data = (sum % 10);
        return sum /10;
    }

    public Node optimalUsingRecursion(Node head) {
        int carry = helper(head);
        if (carry == 1) {
            Node newHead = new Node(carry);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }
}
