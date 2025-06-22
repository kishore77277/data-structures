package com.dsa.leetcode.topics.linkedlist.additional;

public class LinkedListMatrix_GFG {

    static class Node
    {
        int data;
        Node right,down;

        Node(int data){
            this.data = data;
            right = null;
            down = null;
        }
    }

    static Node constructFromBottomUp(int arr[][]) {
        // traversing from bottom up, so that we do not need to traverse again for attaching the pointers
        int rows = arr.length;
        int cols = arr[0].length;
        Node[][] nodes = new Node[rows][cols];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                Node node = new Node(arr[i][j]);
                nodes[i][j] = node;
                if (j != cols - 1) { // setting the right pointer
                    node.right = nodes[i][j + 1];
                }
                if (i != rows - 1) { // setting the down pointer
                    node.down = nodes[i + 1][j];
                }
            }
        }

        return nodes[0][0];
    }

    static Node constructNormally(int arr[][]) {
        int rows = arr.length;
        int cols = arr[0].length;
        Node[][] nodes = new Node[rows][cols];
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                Node node = new Node(arr[i][j]);
                nodes[i][j] = node;
            }
        }

         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 Node node = nodes[i][j];
                 if (j != cols - 1) { // setting the right pointer
                     node.right = nodes[i][j + 1];
                 }
                 if (i != rows - 1) { // setting the down pointer
                     node.down = nodes[i + 1][j];
                 }
             }
         }
        return nodes[0][0];
    }

    static Node constructOptimalWithoutUsingExtraSpace(int mat[][]) {
        Node head = null;
        Node prevRowStart = null;
        for (int[] arr : mat) {
            Node prev = null;
            Node above = prevRowStart;
            Node rowStart = null;
            for (int num : arr) {
                Node node = new Node(num);
                if (rowStart == null) {
                    rowStart = node;
                }
                if (prev != null) {
                    prev.right = node;
                }
                if (above != null) {
                    above.down = node;
                    above = above.right;
                }
                prev = node;
            }
            prevRowStart = rowStart;
            if (head == null) {
                head = rowStart;
            }
        }
        return head;
    }
}
