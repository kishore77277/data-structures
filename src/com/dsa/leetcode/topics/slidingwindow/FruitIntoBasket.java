package com.dsa.leetcode.topics.slidingwindow;

// https://www.geeksforgeeks.org/problems/fruit-into-baskets-1663137462/1

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Find length of the longest subarray containing atmost two distinct integers
 * Difficulty: Medium
 * Given an array arr[] containing positive elements,
 * the task is to find the length of the longest subarray of an input array containing at most two distinct integers.
 */
public class FruitIntoBasket {

    public static int totalElements(Integer[] arr) {
//        return bruteForce(arr);
        return optimalUsingSlidingWindow(arr);
    }

    public static int bruteForce(Integer[] nums) {
        int maxLength = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums[j]);
                if (set.size() > 2) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }

    public static int optimalUsingSlidingWindow(Integer[] nums) {
        int maxLength = 0;
        int n = nums.length;
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < n; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.size() > 2) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

}
