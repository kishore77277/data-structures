package com.dsa.leetcode.topics.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class FindLengthOfLinkedList {

    class Node {
        public int data;
        public Node next;

        Node()
        {
            this.data = 0;
            this.next = null;
        }

        Node(int data)
        {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    };

    public static int lengthOfLoop(Node head) {
        // return bruteForce(head);
        return optimal(head);
    }

    public static int bruteForce(Node head) {
        Map<Node, Integer> map = new HashMap<>();
        int currentIndex = 1;
        Node current = head;
        while (current != null) {
            if (map.containsKey(current)) {
                return currentIndex - map.get(current);
            }
            map.put(current, currentIndex);
            currentIndex++;
            current = current.next;
        }
        return 0;
    }

    public static int optimal(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node cycleStartNode = getStartNode(head, fast);
                return findLengthOfTheCycle(cycleStartNode);
            }
        }
        return 0;
    }

    public static Node getStartNode(Node head, Node fast) {
        Node current = head;
        while (current != fast) {
            current = current.next;
            fast = fast.next;
        }
        return fast;
    }

    public static int findLengthOfTheCycle(Node start) {
        Node current = start;
        int length = 1;
        while (current.next != start) {
            length++;
            current = current.next;
        }
        return length;
    }

}
