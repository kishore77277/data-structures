package com.dsa.leetcode.topics.slidingwindow.imp;
/**
 * Problem: Maximum Sum Subarray of Size K
 * <p>
 * Given an array of integers and an integer `k`, find the maximum sum of a subarray of size `k`.
 * In simple terms, pick any `k` consecutive numbers in the array and find which group gives the highest sum.
 * <p>
 * Example:
 * <pre>
 * Input: arr = [2, 1, 5, 1, 3, 2], k = 3
 * Output: 9
 * Explanation: Subarray [5, 1, 3] has the maximum sum = 9
 * </pre>
 * <p>
 * Edge Cases:
 * - If the array is smaller than `k`, we cannot form a window of size `k` — so return 0.
 * - Array may contain positive integers only (for this problem).
 */
public class MaxSumSubArrayOfSizeK {

    /**
     * Brute-force approach to find the maximum sum of a subarray of size k.
     *
     * <p><b>Intuition:</b><br>
     * Check every possible group of `k` elements in the array one by one and compute their sum.
     * Keep track of the maximum sum seen so far.
     *
     * <p><b>Step-by-step explanation:</b><br>
     * 1. Start from the 0th index and go till `n - k` (because beyond that, we can't form a group of size `k`).<br>
     * 2. For each starting index, calculate the sum of the next `k` elements.<br>
     * 3. Update the maximum sum if this current sum is greater.<br>
     * 4. Finally, return the maximum sum found.
     *
     * @param arr The input array of integers (non-empty, positive integers assumed)
     * @param k   The fixed size of the subarray window
     * @return The maximum sum of any subarray of size `k`. Returns 0 if the array has fewer than `k` elements.
     *
     * <p><b>Time Complexity:</b> O(n * k)<br>
     * We have a loop that runs `n - k + 1` times, and for each iteration, we calculate the sum of `k` elements.<br>
     * So it's `k` operations for each of `n-k` elements → O(nk).
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * We use only a few variables (`maxSum`, `currentSum`) — no extra space proportional to input size.
     *
     * <p><b>Edge Cases:</b><br>
     * - If `k` > `arr.length`, we can't form a valid subarray. The function handles this by returning 0.
     */
    public int bruteForce(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return 0; // Edge case: if k is larger than array size
        int maxSum = 0;
        for (int i = 0; i <= n - k; i++) {
            int currentSum = 0;
            for (int j = i; j < i + k; j++) {
                currentSum += arr[j];
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    /**
     * Optimal approach using the sliding window technique to find the maximum sum of a subarray of size k.
     *
     * <p><b>Intuition:</b><br>
     * Instead of recalculating the sum for every window from scratch, we "slide" the window by:
     * - Adding the new element that enters the window
     * - Subtracting the element that exits the window
     * This reduces repeated work and improves efficiency.
     *
     * <p><b>Step-by-step explanation:</b><br>
     * 1. First, calculate the sum of the first `k` elements and store it as `currentSum`.<br>
     * 2. Store this as the initial `maxSum`.<br>
     * 3. Then, starting from index `k` to the end of the array:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;- Add `arr[i]` (new element entering the window)<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;- Subtract `arr[i - k]` (element leaving the window)<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;- Update `maxSum` if `currentSum` becomes larger<br>
     * 4. After the loop, return the `maxSum`.
     *
     * @param arr The input array of integers (non-empty, positive integers assumed)
     * @param k   The fixed size of the subarray window
     * @return The maximum sum of any subarray of size `k`. Returns 0 if the array has fewer than `k` elements.
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * We loop through the array once — each element is added and subtracted at most once.<br>
     * So the overall operations are linear in the size of the array.
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * Only a few variables are used (`maxSum`, `currentSum`, etc.) — no extra space used.
     *
     * <p><b>Edge Cases:</b><br>
     * - If `k` > `arr.length`, we return 0.<br>
     * - If all elements are the same, the function still returns the correct result.
     */
    public int optimalUsingSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return 0; // Edge case: if k is larger than array size
        int maxSum = 0;
        int currentSum = 0;

        // Calculate the sum of the first 'k' elements
        for (int i = 0; i < k; i++) {
            currentSum += arr[i];
        }
        maxSum = currentSum;

        // Slide the window across the array
        for (int i = k; i < n; i++) {
            currentSum = currentSum + (arr[i] - arr[i - k]); // Add new, remove old
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}


