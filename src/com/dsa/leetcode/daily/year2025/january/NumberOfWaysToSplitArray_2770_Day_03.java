package com.dsa.leetcode.daily.year2025.january;

// https://leetcode.com/problems/number-of-ways-to-split-array/description/?envType=daily-question&envId=2025-01-03
public class NumberOfWaysToSplitArray_2770_Day_03 {

    public int waysToSplitArray(int[] nums) {
        // return bruteForce(nums);
        // return betterApproach(nums);
        return optimalUsingPrefixSum(nums);
    }

    /**
     * bruteForce: A straightforward approach to count valid splits by calculating left and right sums
     * for every possible split point.
     *
     * <p><b>Intuition:</b></p>
     * Iterate through all possible split points and calculate the left and right sums manually.
     * Compare the sums for each split.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Loop through the array to determine split points.
     * 2. For each split point:
     *    - Calculate the left subarray sum.
     *    - Calculate the right subarray sum.
     *    - Compare the sums and count valid splits.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n²) - Nested loops for calculating left and right sums.
     *
     * <p><b>Space Complexity:</b></p>
     * O(1) - No additional space is used.
     *
     * @param nums The input array of integers.
     * @return The number of valid splits in the array.
     */
    public int bruteForce(int[] nums) {
        int n = nums.length;
        int validSplitsCount = 0;
        for (int i = 0; i < n - 1; i++) {
            int leftSum = 0;
            for (int j = 0; j <= i; j++) {
                leftSum = leftSum + nums[j];
            }
            int rightSum = 0;
            for (int j = i + 1; j < n; j++) {
                rightSum = rightSum + nums[j];
            }
            if (leftSum >= rightSum) {
                validSplitsCount++;
            }
        }
        return validSplitsCount;
    }

    /**
     * betterApproach: A slightly optimized version that computes the left sum incrementally
     * while recalculating the right sum for each split point.
     *
     * <p><b>Intuition:</b></p>
     * Instead of recalculating the left sum for every split, maintain a running sum for the left subarray.
     * However, the right sum is still recalculated in a nested loop.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Maintain a running sum for the left subarray.
     * 2. For each split point:
     *    - Calculate the right subarray sum using a nested loop.
     *    - Compare the sums and count valid splits.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n²) - Still requires a nested loop for calculating the right sum.
     *
     * <p><b>Space Complexity:</b></p>
     * O(1) - No additional space is used.
     *
     * @param nums The input array of integers.
     * @return The number of valid splits in the array.
     */
    public int betterApproach(int[] nums) {
        int n = nums.length;
        long leftSum = 0;
        int validSplitsCount = 0;
        for (int i = 0; i < n - 1; i++) {
            leftSum = leftSum + nums[i];
            long rightSum = 0;
            for (int j = i + 1; j < n; j++) {
                rightSum = rightSum + nums[j];
            }
            if (leftSum >= rightSum) {
                validSplitsCount++;
            }
        }
        return validSplitsCount;
    }

    /**
     * optimalUsingPrefixSum: An efficient solution using a prefix sum for the left subarray and
     * a precomputed suffix sum array for the right subarray.
     *
     * <p><b>Intuition:</b></p>
     * By precomputing the suffix sums, we can avoid recalculating the right sum for each split,
     * reducing time complexity significantly.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Precompute the suffix sum array.
     * 2. Maintain a running prefix sum for the left subarray.
     * 3. For each split point, compare the prefix sum with the corresponding suffix sum.
     * 4. Count valid splits.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - Linear time to compute the prefix and suffix sums and iterate through the array.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - Additional space for the suffix sum array.
     *
     * @param nums The input array of integers.
     * @return The number of valid splits in the array.
     */
    public int optimalUsingPrefixSum(int[] nums) {
        int n = nums.length;
        long[] suffixSum = new long[n];
        suffixSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }
        long prefixSum = 0;
        int validSplitsCount = 0;
        for (int i = 0; i < n - 1; i++) {
            prefixSum = prefixSum + nums[i];
            if (prefixSum >= suffixSum[i + 1]) {
                validSplitsCount++;
            }
        }
        return validSplitsCount;
    }

    /**
     * optimalOnePass: An efficient one-pass solution using prefix sums to calculate left and
     * right sums on-the-fly without a suffix sum array.
     *
     * <p><b>Intuition:</b></p>
     * Use a single pass with a prefix sum array to calculate both left and right sums dynamically,
     * eliminating the need for a suffix sum array.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Compute the prefix sum array.
     * 2. For each split point, calculate:
     *    - Left sum using the prefix sum.
     *    - Right sum as the difference between total sum and left sum.
     * 3. Compare the sums and count valid splits.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - Linear time to compute the prefix sums and iterate through the array.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - Additional space for the prefix sum array.
     *
     * @param nums The input array of integers.
     * @return The number of valid splits in the array.
     */
    public int optimalOnePass(int[] nums) {
        int n = nums.length;
        long[] prefixSum = new long[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        int validSplitsCount = 0;
        for (int i = 0; i < n - 1; i++) {
            long leftSum = prefixSum[i];
            long rightSum = prefixSum[n - 1] - prefixSum[i];
            if (leftSum >= rightSum) {
                validSplitsCount++;
            }
        }
        return validSplitsCount;
    }


    public int optimalOnePassWithSuffixSum(int[] nums) {
        int n = nums.length;
        long[] suffixSum = new long[n];
        suffixSum[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + nums[i];
        }
        int validSplitsCount = 0;
        long prefixSum = 0;
        for (int i = 0; i < n - 1; i++) {
            prefixSum = prefixSum + nums[i];
            long rightSum = suffixSum[i + 1];
            if (prefixSum >= rightSum) {
                validSplitsCount++;
            }
        }
        return validSplitsCount;
    }
}
