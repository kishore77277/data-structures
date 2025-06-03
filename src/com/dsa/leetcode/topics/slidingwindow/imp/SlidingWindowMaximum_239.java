package com.dsa.leetcode.topics.slidingwindow.imp;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum_239 {

    public int[] bruteForce(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    public int[] slidingWindowUsingDeque(int[] nums, int k) {
        int n = nums.length;

        int[] result = new int[n - k + 1];
        // We will store indices of the elements in the deque
        // . We need to access the elements in the nums array,
        // and we want to maintain the order of elements in the deque,
        // and also we want to remove the elements which are not in the current window
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Remove elements not in the current window
            if (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.pollFirst();
            }
            // Remove smaller elements from the back
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // Add the maximum to the result
            // check if we have filled the first k elements, means a valid window is formed
            if (i >= k - 1) {
                // The front of the deque is the index of the maximum element for the current window
                // because we are maintaining the deque in decreasing order, monotonic queue
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
