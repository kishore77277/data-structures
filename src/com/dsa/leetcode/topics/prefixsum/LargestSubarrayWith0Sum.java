package com.dsa.leetcode.topics.prefixsum;

// https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
public class LargestSubarrayWith0Sum {

    int maxLen(int arr[]) {
        return bruteForce(arr);
    }

    public int bruteForce(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + nums[j];
                if (sum == 0) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }
}
