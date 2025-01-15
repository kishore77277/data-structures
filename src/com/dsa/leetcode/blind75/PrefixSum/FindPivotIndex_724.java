package com.dsa.leetcode.blind75.PrefixSum;

// https://leetcode.com/problems/find-pivot-index/description/?envType=study-plan-v2&envId=leetcode-75
public class FindPivotIndex_724 {

    public int pivotIndex(int[] nums) {
//        return findPivotIndexBruteForce(nums);
        // return prefixSumUsingTwoArrays(nums);
        // return prefixSumUsingOneArray(nums);
        return prefixSumNoExtraSpace(nums);
    }

    /**
     * findPivotIndexBruteForce: Find pivot index by calculating left and right sums for each index.
     *
     * <b>Intuition:</b>
     * For each index `i`, calculate the sum of elements to the left and the sum of elements to the right.
     * Compare these sums to determine if the index is the pivot index.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Iterate through the array.
     * 2. For each index, calculate the left sum and the right sum.
     * 3. If left sum equals right sum, return the index.
     * 4. If no such index exists, return -1.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n^2) \) - Calculating left and right sums for each index.
     *
     * <b>Space Complexity:</b>
     * \( O(1) \) - No additional space used.
     *
     * @param nums Input array of integers.
     * @return Index of the pivot or -1 if no pivot exists.
     */
    public int findPivotIndexBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0;
            int rightSum = 0;

            // Calculate left sum
            for (int j = 0; j < i; j++) {
                leftSum += nums[j];
            }

            // Calculate right sum
            for (int j = i + 1; j < nums.length; j++) {
                rightSum += nums[j];
            }

            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    public int prefixSumUsingTwoArrays(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            if (prefix[i] == suffix[i]) {
                return i;
            }
        }
        return -1;
    }

    public int prefixSumUsingOneArray(int[] nums) {
        int n = nums.length;
        int[] suffix = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + nums[i + 1];
        }
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            if (prefix == suffix[i]) {
                return i;
            }
            prefix = prefix + nums[i];
        }
        return -1;
    }

    public int prefixSumNoExtraSpace(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum = totalSum + num;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            if (rightSum == leftSum) {
                return i;
            }
            leftSum = leftSum + nums[i];
        }
        return -1;
    }
}
