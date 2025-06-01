package com.dsa.leetcode.topics.slidingwindow.additional;

/**
 * <b>Problem Statement:</b><br>
 * Given an array of integers and a number k, find the maximum sum of any contiguous subarray such that the sum is less than or equal to k.<br>
 * <br>
 * <b>Example 1:</b><br>
 * Input: arr = [2, 1, 5, 1, 3, 2], k = 7<br>
 * Output: 7<br>
 * Explanation: The subarray [2, 1, 5] has a sum of 8 (too big), but [5, 1] has a sum of 6, and [1, 3, 2] has a sum of 6. The largest sum not exceeding 7 is 7 (from [2, 1, 5] up to the 1 before 5).<br>
 * <br>
 * <b>Example 2 (Edge Case):</b><br>
 * Input: arr = [10, 20, 30], k = 5<br>
 * Output: 0<br>
 * Explanation: No subarray has a sum less than or equal to 5.<br>
 * <br>
 * <b>Edge Cases:</b><br>
 * - If all numbers are negative, the largest sum is 0 (since we only consider non-empty subarrays with sum <= k).<br>
 * - If k is very large, the answer could be the sum of the whole array.<br>
 * - If k is negative, the answer is 0 unless there are negative subarrays.<br>
 * <br>
 * <b>Related Link:</b><br>
 * <a href="https://www.geeksforgeeks.org/problems/maximum-sum-of-subarray-less-than-or-equal-to-x4033/1">GFG: Maximum sum of subarray less than or equal to x</a>
 */
public class MaximumSumOfSubArrayLessThanOrEqualToK {

    /**
     * Finds the maximum sum of any contiguous subarray such that the sum is less than or equal to k, using a brute-force approach.
     * <p>
     * <b>Intuition:</b><br>
     * Imagine you want to try every possible group of numbers in the array, add them up, and see which group gives the biggest sum without going over k. This method checks all possible starting and ending points for subarrays.
     * </p>
     * <p>
     * <b>Step-by-step Explanation:</b><br>
     * 1. Start at each index in the array (outer loop).<br>
     * 2. For each starting index, add up numbers one by one (inner loop), keeping a running sum.<br>
     * 3. If the running sum is less than or equal to k, update the maximum sum found so far.<br>
     * 4. If the running sum goes over k, stop adding more numbers for this starting point (since adding more will only make it bigger).<br>
     * 5. Repeat for all starting points.<br>
     * 6. Return the largest sum found that does not exceed k.
     * </p>
     *
     * @param arr The input array of integers.
     * @param k The maximum allowed sum for any subarray.
     * @return The largest sum of any contiguous subarray not exceeding k. Returns 0 if no such subarray exists.
     *
     * <b>Time Complexity:</b> O(n^2) <br>
     * We try every possible subarray (about n^2/2 of them), so the work grows quickly as the array gets bigger.<br>
     * <b>Space Complexity:</b> O(1) <br>
     * We only use a few variables for tracking sums, not extra arrays or data structures.
     *
     * <b>Edge Cases & Considerations:</b><br>
     * - If k is smaller than all elements, the result is 0.<br>
     * - Works for arrays with negative numbers.<br>
     * - If the array is empty, the result is 0.<br>
     * - Uses long to avoid integer overflow for large sums.
     */
    public long bruteForce(int[] arr, long k) {
        long maxSum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            long currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += arr[j];
                if (currentSum <= k) {
                    maxSum = Math.max(maxSum, currentSum);
                } else {
                    break; // No need to continue if the sum exceeds k
                }
            }
        }
        return maxSum;
    }

    /**
     * Finds the maximum sum of any contiguous subarray such that the sum is less than or equal to k, using the sliding window technique.
     * <p>
     * <b>Intuition:</b><br>
     * Instead of checking every possible group, we "slide" a window over the array. If the sum inside the window gets too big, we shrink the window from the left until it's small enough. This way, we only look at each number a few times, making it much faster.
     * </p>
     * <p>
     * <b>Step-by-step Explanation:</b><br>
     * 1. Start with an empty window (no numbers included yet).<br>
     * 2. Move the right end of the window forward, adding each number to the current sum.<br>
     * 3. If the sum goes over k, move the left end forward (removing numbers from the sum) until the sum is less than or equal to k.<br>
     * 4. After each move, check if the current sum is the biggest found so far (but still <= k).<br>
     * 5. Continue until the right end reaches the end of the array.<br>
     * 6. Return the largest sum found that does not exceed k.
     * </p>
     *
     * @param arr The input array of integers.
     * @param k The maximum allowed sum for any subarray.
     * @return The largest sum of any contiguous subarray not exceeding k. Returns 0 if no such subarray exists.
     *
     * <b>Time Complexity:</b> O(n) <br>
     * Each element is added and removed from the sum at most once, so the work grows linearly with the array size.<br>
     * <b>Space Complexity:</b> O(1) <br>
     * We only use a few variables for tracking sums and window boundaries.
     *
     * <b>Edge Cases & Considerations:</b><br>
     * - If k is smaller than all elements, the result is 0.<br>
     * - Works for arrays with negative numbers, but if there are negative numbers, the window may not always be optimal.<br>
     * - If the array is empty, the result is 0.<br>
     * - Uses long to avoid integer overflow for large sums.
     */
    public long optimalUsingSlidingWindow(int[] arr, long k) {
        long maxSum = 0;
        int left = 0;
        long currentSum = 0;
        for (int right = 0; right < arr.length; right++) {
            currentSum += arr[right];
            while (currentSum > k) {
                currentSum -= arr[left];
                left++;
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
