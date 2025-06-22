package com.dsa.leetcode.topics.linkedlist.additional;

public class DesignHashMap_706 {

    class MyHashMap {

        private class ListNode {
            private int key;
            private int value;
            private ListNode next;
            public ListNode() {

            }

            public ListNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private ListNode head;
        public MyHashMap() {

        }

        public void put(int key, int value) {
            ListNode newNode = new ListNode(key, value);
            if (this.head == null) {
                this.head = newNode;
            } else {
                ListNode current = this.head;
                ListNode prev = current;
                while (current != null) {
                    if (current.key == key) {
                        current.value = value;
                        return;
                    }
                    prev = current;
                    current = current.next;
                }
                prev.next = newNode;
            }
        }

        public int get(int key) {
            if (this.head == null) {
                return -1;
            }
            ListNode current = this.head;
            while (current != null) {
                if (current.key == key) {
                    return current.value;
                }
                current = current.next;
            }
            return -1;
        }

        public void remove(int key) {
            if (this.head == null) {
                return;
            }
            ListNode dummy = new ListNode();
            ListNode temp = dummy;
            temp.next = this.head;

            while (temp.next != null) {
                if (temp.next.key == key) {
                    temp.next = temp.next.next;
                } else {
                    temp = temp.next;
                }
            }
            this.head = dummy.next;
        }
    }
}
