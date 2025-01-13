package com.dsa.leetcode.topics.prefixsum;

// https://leetcode.com/problems/running-sum-of-1d-array/description/
public class RunningSumOf1dArray_1480 {

    public int[] runningSum(int[] nums) {
        return runningSumBruteForce(nums);
//        return betterUsingPrefixSumWithExtraSpace(nums);
//        return runningSumPrefixSum(nums);
    }

    /**
     * runningSumBruteForce: Calculates the running sum using a brute force approach.
     *
     * <p><b>Intuition:</b></p>
     * For each element in the array, compute the sum of all elements from the start to that index.
     * This involves recalculating the sum for each element, leading to redundant calculations.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Create a new array `result` to store the running sums.
     * 2. For each index `i` in the array:
     *    a. Initialize a sum variable to 0.
     *    b. Iterate through all indices from 0 to `i`, adding each element to the sum.
     *    c. Store the sum in `result[i]`.
     * 3. Return the `result` array.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n^2) - For each element, we iterate through all previous elements.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - A new array of size n is used to store the results.
     *
     * @param nums The input array of integers.
     * @return A new array where each element is the running sum.
     */
    public int[] runningSumBruteForce(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += nums[j];
            }
            result[i] = sum;
        }
        return result;
    }

    /**
     * [betterUsingPrefixSumWithExtraSpace]: Calculates the prefix sum of an integer array using an extra array to store the prefix sums.
     *
     * <p><b>Intuition:</b></p>
     * The idea behind prefix sum is to preprocess the input array to store cumulative sums. This allows for efficient range queries or summations. Here, we are calculating the prefix sum of the input array using an additional `prefixSum` array to store intermediate sums.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Initialize an array `prefixSum` of the same length as `nums` to store cumulative sums.
     * 2. Set the first element of `prefixSum` equal to the first element of `nums`.
     * 3. Iterate through the `nums` array, and for each element at index `i`, add the current element to the previous prefix sum (i.e., `prefixSum[i] = nums[i] + prefixSum[i - 1]`).
     * 4. Initialize a `result` array to store the final result and copy all elements from `prefixSum` to `result`.
     * 5. Return the `result` array.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - We iterate through the input array `nums` once to calculate the prefix sum and then copy the prefix sum to the result array. Thus, the time complexity is linear in terms of the number of elements.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - The algorithm uses an extra array `prefixSum` of the same size as the input array `nums`, so the space complexity is proportional to the size of the input array.
     *
     * @param nums An integer array representing the input array for which the prefix sum is to be calculated.
     * @return An integer array representing the prefix sum of the input array `nums`.
     */
    public int[] betterUsingPrefixSumWithExtraSpace(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = prefixSum[i];
        }
        return result;
    }



    /**
     * runningSumPrefixSum: Calculates the running sum using the prefix sum technique.
     *
     * <p><b>Intuition:</b></p>
     * The running sum at index `i` can be calculated as the running sum at index `i-1` plus the current element.
     * This eliminates redundant calculations and ensures linear time complexity.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Create a new array `result` to store the running sums.
     * 2. Initialize the first element of `result` to the first element of `nums`.
     * 3. For each index `i` from 1 to `n-1`:
     *    a. Compute `result[i] = result[i-1] + nums[i]`.
     * 4. Return the `result` array.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - We iterate through the array once.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - A new array of size n is used to store the results.
     *
     * @param nums The input array of integers.
     * @return A new array where each element is the running sum.
     */
    public int[] runningSumPrefixSum(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }
        return result;
    }
}
