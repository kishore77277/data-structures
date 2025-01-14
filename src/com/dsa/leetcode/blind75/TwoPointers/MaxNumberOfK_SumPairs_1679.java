package com.dsa.leetcode.blind75.TwoPointers;

import java.util.*;

// https://leetcode.com/problems/max-number-of-k-sum-pairs/description/?envType=study-plan-v2&envId=leetcode-75
public class MaxNumberOfK_SumPairs_1679 {

    public int maxOperations(int[] nums, int k) {
        // return bruteForce(nums, k);
        return betterUsingTwoPointers(nums, k);
    }

    public int bruteForce(int[] nums, int k) {
        Set<Integer> visitedIndices = new HashSet<>();
        int n = nums.length;
        int maxOperations = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!visitedIndices.contains(i) && !visitedIndices.contains(j) && nums[i] + nums[j] == k) {
                    visitedIndices.add(i);
                    visitedIndices.add(j);
                    maxOperations++;
                }
            }
        }
        return maxOperations;
    }

    /**
     * twoPointerMaxOperations: Uses sorting and two pointers to find pairs summing to `k`.
     *
     * <b>Intuition:</b>
     * Sorting helps to organize numbers for efficient searching. Use two pointers
     * (one at the start and one at the end) to find pairs whose sum equals `k`.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Sort the array.
     * 2. Use two pointers:
     *    - Start at the beginning (`left`) and the end (`right`) of the array.
     *    - If `nums[left] + nums[right] == k`, increment the count and adjust both pointers.
     *    - If the sum is less than `k`, move `left` forward.
     *    - If the sum is greater than `k`, move `right` backward.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n \log n) \) - Due to sorting.
     *
     * <b>Space Complexity:</b>
     * \( O(1) \) - Sorting in-place if allowed.
     *
     * @param nums Input array of integers.
     * @param k Target sum.
     * @return Maximum number of valid pairs.
     */
    public int betterUsingTwoPointers(int[] nums, int k) {
        int n = nums.length;
        int maxOperations = 0;
        Arrays.sort(nums);
        int left = 0;
        int right = n - 1;
        while (left < right) {
            if (nums[left] + nums[right] == k) {
                maxOperations++;
                left++;
                right--;
            } else if (nums[left] + nums[right] < k) {
                left++;
            } else {
                right--;
            }
        }
        return maxOperations;
    }

    /**
     * optimalMaxOperations: Uses a HashMap to count and track complements.
     *
     * <b>Intuition:</b>
     * Use a HashMap to count occurrences of numbers. For each number, check if
     * its complement (`k - num`) exists in the map. If so, a pair is found.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Initialize a HashMap to track the frequency of each number.
     * 2. For each number in the array:
     *    - Check if its complement (`k - num`) exists in the map.
     *    - If it exists, decrement the count of the complement and increment the pair count.
     *    - Otherwise, add the number to the map.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n) \) - Single traversal of the array.
     *
     * <b>Space Complexity:</b>
     * \( O(n) \) - To store the counts in the HashMap.
     *
     * @param nums Input array of integers.
     * @param k Target sum.
     * @return Maximum number of valid pairs.
     */
    public int optimalUsingHashing(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int operationsCount = 0;
        for (int num : nums) {
            int targetSum = k - num;
            if (freq.getOrDefault(targetSum, 0) > 0) { // target sum is present
                operationsCount++;
                freq.put(targetSum, freq.get(targetSum) - 1);
            } else { // target sum is not present, put the current num
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }
        }
        return operationsCount;
    }
}
