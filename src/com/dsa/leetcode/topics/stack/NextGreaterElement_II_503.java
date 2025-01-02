package com.dsa.leetcode.topics.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

// https://leetcode.com/problems/next-greater-element-ii/description/
public class NextGreaterElement_II_503 {

    public int[] nextGreaterElements(int[] nums) {
//        return nextGreaterElementsBruteForce(nums);
        return nextGreaterElementsUsingMonotonicStack(nums);
    }
    
    public int[] nextGreaterElementsBruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int nextGreaterElementIndex = (i + 1) % n;
            result[i] = -1;
            while (nextGreaterElementIndex != i) {
                if (nums[i] < nums[nextGreaterElementIndex]) {
                    result[i] = nums[nextGreaterElementIndex];
                    break;
                }
                nextGreaterElementIndex = (nextGreaterElementIndex + 1) % n;
            }
        }
        return result;
    }

    public int[] nextGreaterElementsUsingMonotonicStack(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            if (i < n) {
                result[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[i % n]);
        }
        return result;
    }
}
