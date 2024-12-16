package com.dsa.leetcode.topics.stack;

// https://www.geeksforgeeks.org/problems/implement-stack-using-array/1
public class ImplementStackUsingArray {

    class MyStack {
        private int[] arr;
        private int top;

        public MyStack() {
            arr = new int[1000];
            top = -1;
        }

        public void push(int x) {
            arr[++top] = x;
        }

        public int pop() {
            if (top == -1) {
                return -1;
            }
            return arr[top--];
        }
    }
}
