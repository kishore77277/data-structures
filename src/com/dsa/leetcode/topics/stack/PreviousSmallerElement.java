package com.dsa.leetcode.topics.stack;

import java.util.ArrayDeque;
import java.util.Deque;

// https://www.interviewbit.com/problems/nearest-smaller-element/
public class PreviousSmallerElement {

    public int[] prevSmaller(int[] A) {
        return prevSmallerBruteForce(A);
//        return prevSmallerUsingMonotonicStack(A);
    }

    public int[] prevSmallerBruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    result[i] = nums[j];
                    break;
                }
            }
        }
        return result;
    }

    public int[] prevSmallerUsingMonotonicStack(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return result;
    }
}
