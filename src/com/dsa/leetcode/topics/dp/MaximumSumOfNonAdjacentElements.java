package com.dsa.leetcode.topics.dp;

/**
 * Given an array of ‘N’  positive integers, we need to return the maximum sum of the subsequence such that no two elements of the subsequence are adjacent elements in the array.
 *
 * Note: A subsequence of an array is a list with elements of the array where some elements are deleted ( or not deleted at all) and the elements should be in the same order in the subsequence as in the array.
 * <p></p><br>
 *
 */
public class MaximumSumOfNonAdjacentElements {

    /**
     * Computes the maximum sum of a subsequence with no two adjacent elements using plain recursion.
     * <p>
     * <b>Intuition:</b> At each index, you can either pick the current element (and skip the previous), or skip the current element. The maximum of these two choices is the answer.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>If n == 0, return arr[0] (base case: only one element can be picked).</li>
     *   <li>If n < 0, return 0 (no elements to pick).</li>
     *   <li>Recursively compute the maximum by picking or not picking the current element.</li>
     * </ol>
     *
     * @param n The current index (0-based) in the array to consider
     * @param arr The array of positive integers
     * @return The maximum sum of a non-adjacent subsequence up to index n
     *
     * <b>Time Complexity:</b> O(2^n) (exponential, due to overlapping subproblems)
     * <b>Space Complexity:</b> O(n) (recursion stack)
     *
     * <b>Edge Cases:</b> Handles single-element arrays. Not efficient for large n.
     */
    public int usingRecursion(int n, int[] arr) {
        if (n == 0) {
            return arr[0];
        }
        if (n < 0) {
            return 0;
        }
        int pick = usingRecursion(n - 2, arr) + arr[n];
        int notPick = usingRecursion(n - 1, arr);
        return Math.max(pick, notPick);
    }

    /**
     * Computes the maximum sum of a non-adjacent subsequence using memoization (top-down DP).
     * <p>
     * <b>Intuition:</b> Same as the recursive approach, but stores results of subproblems to avoid redundant calculations.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>Initialize a dp array of size n+1 with -1.</li>
     *   <li>Use a helper function to recursively compute the answer, storing results in dp.</li>
     * </ol>
     *
     * @param n The current index (0-based) in the array to consider
     * @param arr The array of positive integers
     * @return The maximum sum of a non-adjacent subsequence up to index n
     *
     * <b>Time Complexity:</b> O(n) (each subproblem solved once)
     * <b>Space Complexity:</b> O(n) (for dp array and recursion stack)
     *
     * <b>Edge Cases:</b> Handles single-element arrays. Efficient for large n.
     */
    public int usingMemoization(int n, int[] arr) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }
        return usingMemoizationHelper(n, arr, dp);
    }

    /**
     * Helper for the memoization approach.
     * <p>
     * Recursively computes the maximum sum, storing results in the dp array to avoid recomputation.
     *
     * @param n The current index (0-based) in the array
     * @param arr The array of positive integers
     * @param dp Memoization array (stores max sum for each index)
     * @return The maximum sum of a non-adjacent subsequence up to index n
     */
    private int usingMemoizationHelper(int n, int[] arr, int[] dp) {
        if (n == 0) {
            return arr[0];
        }
        if (n < 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int pick = usingMemoizationHelper(n - 2, arr, dp) + arr[n];
        int notPick = usingMemoizationHelper(n - 1, arr, dp);
        return dp[n] = Math.max(pick, notPick);
    }

    /**
     * Computes the maximum sum of a non-adjacent subsequence using tabulation (bottom-up DP).
     * <p>
     * <b>Intuition:</b> Builds the solution iteratively from the base case, using a dp array.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>Initialize dp[0] = arr[0].</li>
     *   <li>For each i from 1 to n, compute the max sum by picking or not picking arr[i].</li>
     *   <li>Return dp[n] as the answer.</li>
     * </ol>
     *
     * @param n The last index (0-based) to consider in the array
     * @param arr The array of positive integers
     * @return The maximum sum of a non-adjacent subsequence up to index n
     *
     * <b>Time Complexity:</b> O(n)
     * <b>Space Complexity:</b> O(n) (for dp array)
     *
     * <b>Edge Cases:</b> Handles single-element arrays. Efficient and easy to understand.
     */
    public int usingTabulation(int n, int[] arr) {
        int[] dp = new int[n + 1];
        dp[0] = arr[0];
        for (int i = 1; i <= n; i++) {
            int pick = (i - 2 >= 0 ? dp[i - 2] : 0) + arr[i];
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n];
    }

    /**
     * Computes the maximum sum of a non-adjacent subsequence using space-optimized dynamic programming.
     * <p>
     * <b>Intuition:</b> Only the last two computed values are needed at each step, so a full dp array is unnecessary.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>Initialize prev1 = arr[0], prev2 = 0.</li>
     *   <li>For each i from 1 to n, compute the max sum using only prev1 and prev2.</li>
     *   <li>Update prev2 and prev1 accordingly.</li>
     *   <li>Return prev1 as the answer.</li>
     * </ol>
     *
     * @param n The last index (0-based) to consider in the array
     * @param arr The array of positive integers
     * @return The maximum sum of a non-adjacent subsequence up to index n
     *
     * <b>Time Complexity:</b> O(n)
     * <b>Space Complexity:</b> O(1) (constant extra space)
     *
     * <b>Edge Cases:</b> Handles single-element arrays. Most efficient in terms of space.
     */
    public int usingSpaceOptimized(int n, int[] arr) {
        int prev1 = arr[0];
        int prev2 = 0;
        for (int i = 1; i <= n; i++) {
            int pick = (i - 2 >= 0 ? prev2 : 0) + arr[i];
            int notPick = prev1;
            int curr = Math.max(pick, notPick);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
