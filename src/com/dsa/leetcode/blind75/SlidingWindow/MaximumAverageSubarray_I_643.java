package com.dsa.leetcode.blind75.SlidingWindow;

// https://leetcode.com/problems/maximum-average-subarray-i/description/?envType=study-plan-v2&envId=leetcode-75
public class MaximumAverageSubarray_I_643 {

    public double findMaxAverage(int[] nums, int k) {
        // return bruteForce(nums, k);
//        return optimalUsingSlidingWindowUsingTwoVariables(nums, k);
        return optimalUsingSlidingWindow(nums, k);
    }

    public double bruteForce(int[] nums, int k) {
        int n = nums.length;
        double maxAvg = Integer.MIN_VALUE;
        for (int i = 0; i <= n - k; i++) {
            double sum = 0;
            for (int j = i; j < i + k; j++) {
                sum = sum + nums[j];
            }
            maxAvg = Math.max(maxAvg, sum / k);
        }
        return maxAvg;
    }

    public double optimalUsingSlidingWindowUsingTwoVariables(int[] nums, int k) {
        int n = nums.length;
        double maxAvg = Integer.MIN_VALUE;
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum = currentSum + nums[i];
        }
        maxAvg = currentSum / k;
        int left = 0;
        int right = k;
        while (right < n) {
            currentSum = currentSum - nums[left];
            currentSum = currentSum + nums[right];
            maxAvg = Math.max(maxAvg, currentSum / k);
            left++;
            right++;
        }
        return maxAvg;
    }

    public double optimalUsingSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double maxAvg = Integer.MIN_VALUE;
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum = currentSum + nums[i];
        }
        maxAvg = currentSum / k;
        for (int i = k; i < n; i++) {
            currentSum = currentSum - nums[i - k] + nums[i];
            maxAvg = Math.max(currentSum / k, maxAvg);
        }
        return maxAvg;
    }
}
