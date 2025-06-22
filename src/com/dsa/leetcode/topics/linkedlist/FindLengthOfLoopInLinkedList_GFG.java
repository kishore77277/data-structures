package com.dsa.leetcode.topics.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class FindLengthOfLoopInLinkedList_GFG {

    class Node
    {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }

    public int countNodesinLoop(Node head) {
        // return bruteForce(head);
        return optimal(head);
    }

    public int bruteForce(Node head) {
        Node current = head;
        Map<Node, Integer> map = new HashMap<>();
        int index = 1;
        while (current != null) {
            if (map.containsKey(current)) {
                return index - map.get(current);
            }
            map.put(current, index);
            index++;
            current = current.next;
        }
        return 0;
    }

    public int optimal(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                int length = 1;
                slow = slow.next;
                while (slow != fast) {
                    slow = slow.next;
                    length++;
                }
                return length;
            }
        }
        return 0;
    }
}
