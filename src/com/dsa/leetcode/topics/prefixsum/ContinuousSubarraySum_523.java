package com.dsa.leetcode.topics.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum_523 {

    public boolean checkSubarraySum(int[] nums, int k) {
        // return bruteForce(nums, k);
        return optimalUsingPrefixSum(nums, k);
    }

    public boolean bruteForce(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum = sum + nums[j];
                if (j - i + 1 > 1 && (sum % k) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean optimalUsingPrefixSum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> prefixModMap = new HashMap<>();
        prefixModMap.put(0, -1);
        int prefixMod = 0;
        for (int i = 0; i < n; i++) {
            prefixMod = (prefixMod + nums[i]) % k;
            int longestSubArraySize = i - prefixModMap.getOrDefault(prefixMod, i);
            if (longestSubArraySize > 1) {
                return true;
            }
            prefixModMap.putIfAbsent(prefixMod, i);
        }
        return false;
    }
}
