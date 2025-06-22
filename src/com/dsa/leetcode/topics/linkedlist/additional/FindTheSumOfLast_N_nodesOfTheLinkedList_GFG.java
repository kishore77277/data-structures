package com.dsa.leetcode.topics.linkedlist.additional;

import java.util.LinkedList;
import java.util.Queue;

// https://www.geeksforgeeks.org/problems/find-the-sum-of-last-n-nodes-of-the-linked-list/1?page=1&category=Linked%20List&sortBy=difficulty
public class FindTheSumOfLast_N_nodesOfTheLinkedList_GFG {


    class Node {
        int data;
        Node next;
        Node(int d)  { data = d;  next = null; }
    }

    public int bruteForce(Node head, int n) {
        int length = 0;
        Node temp = head;

        // First pass: find length
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // Calculate starting position
        int skip = length - n;
        temp = head;

        // Second pass: skip first (length - n) nodes
        for (int i = 0; i < skip; i++) {
            temp = temp.next;
        }

        // Sum last n nodes
        int sum = 0;
        while (temp != null) {
            sum += temp.data;
            temp = temp.next;
        }

        return sum;
    }

    public int better(Node head, int n) {
        Queue<Integer> queue = new LinkedList<>();
        Node temp = head;

        // Keep last n elements in queue
        while (temp != null) {
            if (queue.size() == n) {
                queue.poll(); // remove oldest
            }
            queue.offer(temp.data);
            temp = temp.next;
        }

        int sum = 0;
        for (int val : queue) {
            sum += val;
        }

        return sum;
    }

    public int optimal(Node head, int n) {
        if (head == null || n <= 0) return 0;

        Node first = head, second = head;

        // Move first pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (first == null) return 0; // n > length
            first = first.next;
        }

        // Move both pointers until first reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Sum the last n nodes
        int sum = 0;
        while (second != null) {
            sum += second.data;
            second = second.next;
        }

        return sum;
    }

}
