package com.dsa.leetcode.topics.prefixsum;

import java.util.HashMap;
import java.util.Map;

// https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1?itm_source=geeksforgeeks
public class LongestSubArrayWithSumK_325 {

    public int lenOfLongestSubarr(int[] arr, int k) {
        // return bruteForce(arr, k);
        return optimalUsingPrefixSumAndHashMap(arr, k);
    }

    /**
     * bruteForce: Finds the maximum subarray length with sum equal to k using nested loops.
     *
     * <p><b>Intuition:</b></p>
     * For each starting index, calculate all possible subarray sums ending at every index after it.
     * Track the length of subarrays with a sum equal to k.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Iterate through each possible starting index `i`.
     * 2. For each starting index, iterate through the array to calculate subarray sums.
     * 3. If the sum equals `k`, update the maximum subarray length.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n^2) - Nested loop to calculate subarray sums for each starting index.
     *
     * <p><b>Space Complexity:</b></p>
     * O(1) - No additional space is used apart from variables.
     *
     * @param nums Input array of integers.
     * @param k Target sum.
     * @return Maximum length of a subarray with sum equal to k.
     */
    public int bruteForce(int[] nums, int k) {
        int maxLength = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }


    /**
     * optimalUsingPrefixSumAndHashMap: Finds the maximum subarray length with sum k using a hashmap.
     *
     * <p><b>Intuition:</b></p>
     * Use a HashMap to store prefix sums and their earliest occurrences.
     * If `prefixSum - k` exists in the map, it means the subarray from that index to the current index sums to `k`.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Initialize a HashMap to store prefix sums and their indices.
     * 2. Traverse the array, updating the running prefix sum.
     * 3. Check if `prefixSum - k` exists in the map; if yes, calculate the subarray length.
     * 4. Store the prefix sum in the map if it doesn't already exist.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - Single pass through the array.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - HashMap stores prefix sums.
     *
     * @param nums Input array of integers.
     * @param k Target sum.
     * @return Maximum length of a subarray with sum equal to k.
     */
    public int optimalUsingPrefixSumAndHashMap(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, -1); // Initialize for edge cases where prefixSum == k
        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // Check if the required prefix exists
            if (prefixSumMap.containsKey(prefixSum - k)) {
                maxLength = Math.max(maxLength, i - prefixSumMap.get(prefixSum - k));
            }

            // Store the prefix sum in the map if not already present
            prefixSumMap.putIfAbsent(prefixSum, i);
        }

        return maxLength;
    }

    public int usingSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < n) {
            while (left <= right && sum > k) {
                sum = sum - nums[left];
                left++;
            }
            if (sum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
            sum = sum + nums[right];
            right++;
        }
        return maxLength;
    }

}
