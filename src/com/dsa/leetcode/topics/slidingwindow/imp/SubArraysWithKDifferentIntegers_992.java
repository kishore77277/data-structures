package com.dsa.leetcode.topics.slidingwindow.imp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 🔢 Problem: Subarrays with K Different Integers
 *
 * Given an integer array `nums` and an integer `k`, return the number of *good subarrays* of `nums`.
 * A *good subarray* is defined as a contiguous part of the array that contains exactly `k` different integers.
 *
 * 📌 Problem Statement:
 * You are to count the number of subarrays that contain **exactly k different integers**.
 *
 * ✅ Constraints:
 * - 1 <= nums.length <= 20,000
 * - 1 <= nums[i], k <= nums.length
 *
 * 🔍 Examples:
 * 1. Input: nums = [1,2,1,2,3], k = 2 → Output: 7
 *    Explanation: The 7 subarrays with exactly 2 different integers are:
 *    [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,3]
 *
 * 2. Input: nums = [1,2,1,3,4], k = 3 → Output: 6
 *    Explanation: Subarrays with 3 different integers: [1,2,1,3], [2,1,3], [1,3,4], etc.
 *
 * 🧪 Edge Cases:
 * - nums = [1,1,1,1], k = 1 → Output: 10 (every possible subarray qualifies)
 * - nums = [1,2,3,4,5], k = 5 → Output: 1 (only the full array is valid)
 *
 * 🔗 LeetCode Link: https://leetcode.com/problems/subarrays-with-k-different-integers/
 *
 * This class provides both a brute-force and optimized solution using the sliding window technique.
 */
public class SubArraysWithKDifferentIntegers_992 {

    /**
     * 🌲 Brute-force approach to count subarrays with exactly k different integers.
     *
     * @param nums The input array of integers.
     * @param k The number of distinct integers required in the subarray.
     * @return The count of subarrays with exactly k different integers.
     *
     * 🧠 Intuition:
     * Try every possible subarray, use a Set to count unique elements.
     * If the number of unique elements == k, we count it.
     *
     * 🪜 Step-by-step:
     * 1. Use two nested loops to check all subarrays.
     * 2. Use a HashSet to track distinct integers.
     * 3. Stop inner loop early if distinct count exceeds k.
     *
     * ⏱️ Time Complexity: O(n²) <br>
     *    Each subarray is checked with an inner loop, and adding to a set takes O(1).
     *
     * 🧠 Space Complexity: O(k) <br>
     *    For each subarray, we store up to k distinct elements.
     *
     * ⚠️ Edge Cases:
     * - The entire array has fewer than k distinct elements — return 0.
     * - k = 1 — only subarrays with identical repeated values count.
     */
    public int bruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> uniqueElements = new HashSet<>();
            for (int j = i; j < n; j++) {
                uniqueElements.add(nums[j]);
                if (uniqueElements.size() == k) {
                    count++;
                } else if (uniqueElements.size() > k) {
                    break;
                }
            }
        }
        return count;
    }

    /**
     * ⚡ Optimized approach using Sliding Window to count subarrays with exactly k different integers.
     *
     * @param nums The input array of integers.
     * @param k The number of distinct integers required in the subarray.
     * @return The count of subarrays with exactly k different integers.
     *
     * 🧠 Intuition:
     * The number of subarrays with exactly k distinct integers =
     * Number of subarrays with at most k distinct integers
     * – Number of subarrays with at most (k - 1) distinct integers.
     *
     * 🪜 Step-by-step:
     * 1. Define a helper method that computes number of subarrays with at most K distinct elements.
     * 2. Use the mathematical trick: exactlyK = atMostK(k) - atMostK(k - 1).
     *
     * ⏱️ Time Complexity: O(n) <br>
     *    Each element is processed at most twice (once by right and once by left pointer).
     *
     * 🧠 Space Complexity: O(k) <br>
     *    The HashMap will store up to k unique integers in the current window.
     *
     * ⚠️ Assumptions:
     * - The array can have duplicates.
     * - Subarrays are contiguous.
     */
    public int slidingWindow(int[] nums, int k) {
        return countSubArraysWithAtMostKDifferentIntegers(nums, k) - countSubArraysWithAtMostKDifferentIntegers(nums, k - 1);
    }

    /**
     * 🔍 Helper method to count subarrays with at most k different integers using sliding window.
     *
     * @param nums The input array of integers.
     * @param k The maximum number of distinct integers allowed in the subarray.
     * @return The count of subarrays with at most k different integers.
     *
     * 🧠 Intuition:
     * Use the sliding window technique to expand the window until the number of distinct elements
     * exceeds k, then shrink from the left until we are within limits again.
     *
     * 🪜 Step-by-step:
     * 1. Initialize a left pointer and a HashMap to store frequencies of elements in window.
     * 2. Expand the right pointer to include more elements.
     * 3. If the number of distinct elements exceeds k, move the left pointer right to shrink window.
     * 4. At each step, add the size of the current valid window (right - left + 1) to the count.
     *
     * ⏱️ Time Complexity: O(n) <br>
     *    Each element is visited at most twice: once when expanding the window, and once when shrinking it.
     *
     * 🧠 Space Complexity: O(k) <br>
     *    The HashMap tracks frequencies of up to k unique elements in the window.
     *
     * ⚠️ Watch out:
     * - If k = 0, no subarray is valid.
     * - Properly decrement and remove from the map to maintain accurate count of unique elements.
     */
    public int countSubArraysWithAtMostKDifferentIntegers(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        for (int right = 0; right < n; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (left <= right && map.size() > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            count = count + right - left + 1;
        }
        return count;
    }
}
