package com.dsa.leetcode.topics.stack;

import java.util.Stack;

public class MinStack_155 {

    /**
     * MinStack implementation using a brute force approach.
     *
     * <p>Intuition: Use a single stack to store the elements. For the `getMin()`
     * operation, iterate through the stack to find the minimum value.</p>
     *
     * Time Complexity:
     * - push(x): O(1)
     * - pop(): O(1)
     * - top(): O(1)
     * - getMin(): O(n), where n is the size of the stack
     *
     * Space Complexity: O(n), as the stack stores all elements.
     */
    class MinStackBruteForce {
        private Stack<Integer> stack;

        public MinStackBruteForce() {
            stack = new Stack<>();
        }

        /**
         * Pushes an element onto the stack.
         */
        public void push(int x) {
            stack.push(x);
        }

        /**
         * Removes the top element from the stack.
         */
        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        /**
         * Retrieves the top element of the stack.
         */
        public int top() {
            return stack.isEmpty() ? -1 : stack.peek();
        }

        /**
         * Retrieves the minimum element in the stack.
         * This operation takes O(n) time as we iterate through the stack.
         */
        public int getMin() {
            int min = Integer.MAX_VALUE;
            for (int num : stack) {
                min = Math.min(min, num);
            }
            return min;
        }
    }

    /**
     * MinStack implementation using two stacks.
     *
     * <p>Intuition: Maintain a second stack to keep track of the current minimum
     * at every stage. This ensures O(1) time complexity for all operations.</p>
     *
     * Time Complexity:
     * - push(x): O(1)
     * - pop(): O(1)
     * - top(): O(1)
     * - getMin(): O(1)
     *
     * Space Complexity: O(n), as both the primary stack and minStack store elements.
     */
    class MinStackTwoStacks {

        private Stack<Integer> mainStack;
        private Stack<Integer> minStack;

        public MinStackTwoStacks() {
            mainStack = new Stack<>();
            minStack = new Stack<>();
        }

        /**
         * Pushes an element onto the stack.
         */
        public void push(int x) {
            mainStack.push(x);
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
        }

        /**
         * Removes the top element from the stack.
         */
        public void pop() {
            if (!mainStack.isEmpty()) {
                int removed = mainStack.pop();
                if (removed == minStack.peek()) {
                    minStack.pop();
                }
            }
        }

        /**
         * Retrieves the top element of the stack.
         */
        public int top() {
            return mainStack.isEmpty() ? -1 : mainStack.peek();
        }

        /**
         * Retrieves the minimum element in the stack.
         * This operation is O(1) as we directly access the top of the minStack.
         */
        public int getMin() {
            return minStack.isEmpty() ? -1 : minStack.peek();
        }
    }
}
