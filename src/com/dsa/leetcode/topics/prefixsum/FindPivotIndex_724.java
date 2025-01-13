package com.dsa.leetcode.topics.prefixsum;

// https://leetcode.com/problems/find-pivot-index/description/
public class FindPivotIndex_724 {

    public int pivotIndex(int[] nums) {
//        return pivotIndexBruteForce(nums);
//        return betterUsingPrefixSumUsingTwoArrays(nums);
//        return optimalUsingPrefixSumUsingTwoArraysSimplified(nums);
//        return optimalUsingPrefixSumWithOneArray(nums);
        return pivotIndexOptimalWithoutUsingExtraSpace(nums);
    }

    /**
     * pivotIndexBruteForce: Finds the pivot index using a brute force approach.
     *
     * <p><b>Intuition:</b></p>
     * We calculate the sum of the elements on the left and right side for every index and check if they are equal.
     * This approach results in redundant calculations, leading to a higher time complexity.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Loop through each index `i` from 0 to `n-1`.
     * 2. For each index `i`, calculate the sum of elements to the left (`leftSum`) and the sum of elements to the right (`rightSum`).
     *    a. Left sum can be calculated by iterating from 0 to `i-1`.
     *    b. Right sum can be calculated by iterating from `i+1` to `n-1`.
     * 3. If `leftSum == rightSum`, return the index `i`.
     * 4. If no such index exists, return -1.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n²) - For each index, we calculate the left and right sums by iterating through the array.
     *
     * <p><b>Space Complexity:</b></p>
     * O(1) - No additional space is used except for the sums.
     *
     * @param nums The input array of integers.
     * @return The pivot index or -1 if no pivot index exists.
     */
    public int pivotIndexBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0, rightSum = 0;

            // Calculate the left sum
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }

            // Calculate the right sum
            for (int j = i + 1; j < nums.length; j++) {
                rightSum += nums[j];
            }

            // Check if the left sum and right sum are equal
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }


    public int betterUsingPrefixSumUsingTwoArrays(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        }
        suffixSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = nums[i] + suffixSum[i + 1];
        }
        for (int i = 0; i < n; i++) {
            int leftSum = 0;
            if (i > 0) {
                leftSum = prefixSum[i - 1];
            }
            int rightSum = 0;
            if (i < n - 1) {
                rightSum = suffixSum[i + 1];
            }
            if (rightSum == leftSum) {
                return i;
            }
        }
        return -1;
    }

    public int optimalUsingPrefixSumWithOneArray(int[] nums) {
        int n = nums.length;
        int[] suffixSum = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i + 1];
        }

        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            if (prefixSum == suffixSum[i]) {
                return i;
            }
            prefixSum = prefixSum + nums[i];
        }

        return -1;
    }

    /**
     * [optimalUsingPrefixSum]: Finds the index where the prefix sum equals the suffix sum in an integer array.
     *
     * <p><b>Intuition:</b></p>
     * The goal is to find the index where the sum of elements before the index (prefix sum) is equal to the sum of elements after the index (suffix sum). This can be achieved by calculating both prefix and suffix sums for each index in the array and comparing them.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Initialize two arrays, `prefixSum` and `suffixSum`, both of size `n`, where `n` is the length of the input array `nums`.
     * 2. Compute the `prefixSum` array, where each element at index `i` represents the sum of all elements before `i` in the `nums` array.
     * 3. Compute the `suffixSum` array, where each element at index `i` represents the sum of all elements after `i` in the `nums` array.
     * 4. Iterate through the array, and for each index, compare the corresponding values in `prefixSum` and `suffixSum`. If they are equal, return the index.
     * 5. If no such index is found, return `-1`.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - The algorithm computes both the prefix and suffix sum arrays, each requiring a single pass over the array. Hence, the total time complexity is linear in terms of the number of elements `n`.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - Two additional arrays (`prefixSum` and `suffixSum`) are used, each of size `n`, which results in a space complexity proportional to the size of the input array.
     *
     * @param nums An integer array representing the input array for which the prefix and suffix sums are to be calculated.
     * @return The index where the prefix sum equals the suffix sum, or `-1` if no such index exists.
     */
    public int optimalUsingPrefixSumUsingTwoArraysSimplified(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];

        // Compute prefix sums
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // Compute suffix sums
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i + 1];
        }

        // Check for the index where prefix sum equals suffix sum
//        prefixSum[i] : represents the prefix sum that is from 0th index to i - 1 index inclusive
        for (int i = 0; i < n; i++) {
            if (prefixSum[i] == suffixSum[i]) {
                return i;
            }
        }

        // If no such index exists, return -1
        return -1;
    }

    /**
     * pivotIndexOptimal: Finds the pivot index using a more efficient approach.
     *
     * <p><b>Intuition:</b></p>
     * Instead of recalculating the left and right sums for every index, we calculate the total sum of the array and use it to find the pivot.
     * By maintaining a `leftSum`, we can calculate the right sum as `rightSum = totalSum - leftSum - nums[i]`.
     * This approach only requires one pass through the array.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Calculate the total sum of the array `totalSum`.
     * 2. Initialize `leftSum` as 0.
     * 3. Loop through each index `i`:
     *    a. Calculate the right sum as `rightSum = totalSum - leftSum - nums[i]`.
     *    b. If `leftSum == rightSum`, return the current index `i` as the pivot index.
     *    c. Update `leftSum = leftSum + nums[i]` for the next iteration.
     * 4. If no such index exists, return -1.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - We calculate the total sum in one pass and then iterate through the array once more.
     *
     * <p><b>Space Complexity:</b></p>
     * O(1) - Only a few extra variables (`leftSum`, `rightSum`, `totalSum`) are used.
     *
     * @param nums The input array of integers.
     * @return The pivot index or -1 if no pivot index exists.
     */
    public int pivotIndexOptimalWithoutUsingExtraSpace(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

}
