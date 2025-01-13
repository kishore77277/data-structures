package com.dsa.leetcode.topics.prefixsum;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/
public class LargestSubarrayOf0sAnd1s {

    public int maxLen(int[] arr) {
        // return bruteForce(arr);
        return optimalUsingPrefixSum(arr);
    }

    public int bruteForce(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + ((nums[j] == 0) ? 1 : -1);
                if (sum == 0) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    public int optimalUsingPrefixSum(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        Map<Integer, Integer> prefixSumIndexMap = new HashMap<>();
        prefixSumIndexMap.put(0, -1);
        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum = prefixSum + ((nums[i] == 0) ? 1 : -1);
            maxLength = Math.max(maxLength, i - prefixSumIndexMap.getOrDefault(prefixSum, i));
            prefixSumIndexMap.putIfAbsent(prefixSum, i);
        }
        return maxLength;
    }
}
