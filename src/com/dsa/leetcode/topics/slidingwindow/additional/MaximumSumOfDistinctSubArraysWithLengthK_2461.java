package com.dsa.leetcode.topics.slidingwindow.additional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumSumOfDistinctSubArraysWithLengthK_2461 {
    public long maximumSubarraySum(int[] nums, int k) {
        // return brutForce(nums, k);
        return slidingWindow(nums, k);
    }

    public long brutForce(int[] nums, int k) {
        int n = nums.length;
        long maxSum = 0;
        for (int i = 0; i <= n - k; i++) {
            long sum = 0;
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                sum = sum + nums[j];
                set.add(nums[j]);
            }
            if (set.size() == k) {
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public long slidingWindow(int[] nums, int k) {
        int n = nums.length;
        long maxSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        long currentSum = 0;
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            currentSum = currentSum + nums[i];
        }
        if (map.size() == k) {
            maxSum = currentSum;
        }
        for (int i = k; i < n; i++) {
            map.put(nums[i - k], map.get(nums[i - k]) - 1);
            if (map.get(nums[i - k]) == 0) {
                map.remove(nums[i - k]);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            currentSum = currentSum - nums[i - k] + nums[i];
            if (map.size() == k) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}
