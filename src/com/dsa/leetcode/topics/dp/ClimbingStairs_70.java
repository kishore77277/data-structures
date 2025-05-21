package com.dsa.leetcode.topics.dp;

/**
 *You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * <a href="https://leetcode.com/problems/climbing-stairs/">link</a>
 */
public class ClimbingStairs_70 {

    /**
     * Calculates the number of distinct ways to climb to the top of a staircase with n steps using simple recursion.
     * <p>
     * <b>Intuition:</b> At each step, you can climb either 1 or 2 steps. The total number of ways to reach step n is the sum of the ways to reach step n-1 and step n-2.
     * <p>
     * <b>Algorithm:</b>
     * <ul>
     *   <li>If n is 1 or 2, return n (base cases).</li>
     *   <li>Otherwise, recursively compute usingRecursion(n-1) + usingRecursion(n-2).</li>
     * </ul>
     * <b>Parameters:</b>
     * @param n The total number of steps in the staircase.
     * <b>Returns:</b>
     * @return The number of distinct ways to reach the top.<br>
     * <b>Time Complexity:</b> O(2^n) (exponential, due to overlapping subproblems).<br>
     * <b>Space Complexity:</b> O(n) (call stack depth).
     * <b>Edge Cases:</b> n <= 2 returns n directly.
     */
    public int usingRecursion(int n) {
        if (n <= 2) {
            return n;
        }
        return usingRecursion(n - 1) + usingRecursion(n - 2);
    }

    /**
     * Calculates the number of distinct ways to climb to the top using memoization (top-down dynamic programming).
     * <p>
     * <b>Intuition:</b> Avoids redundant calculations by storing results of subproblems in a memo array.
     * <p>
     * <b>Algorithm:</b>
     * <ul>
     *   <li>Create a memo array of size n+1.</li>
     *   <li>Use a helper function to recursively compute the result, storing and reusing values from the memo array.</li>
     * </ul>
     * <b>Parameters:</b>
     * @param n The total number of steps in the staircase.
     * <b>Returns:</b>
     * @return The number of distinct ways to reach the top.<br>
     * <b>Time Complexity:</b> O(n) (each subproblem solved once).<br>
     * <b>Space Complexity:</b> O(n) (memo array + call stack).
     * <b>Edge Cases:</b> n <= 2 returns n directly.
     */
    public int usingMemoization(int n) {
        int[] memo = new int[n + 1];
        return usingMemoizationHelper(n, memo);
    }

    /**
     * Helper function for the memoization approach.
     * <p>
     * <b>Intuition:</b> Recursively computes the number of ways, storing results in the memo array to avoid recomputation.
     * <b>Parameters:</b>
     * @param n The current step being evaluated.
     * @param memo The memoization array storing results for each step.
     * <b>Returns:</b>
     * @return The number of ways to reach the nth step.<br>
     * <b>Time Complexity:</b> O(n).<br>
     * <b>Space Complexity:</b> O(n).
     */
    private int usingMemoizationHelper(int n, int[] memo) {
        if (n <= 2) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = usingMemoizationHelper(n - 1, memo) + usingMemoizationHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Calculates the number of distinct ways to climb to the top using tabulation (bottom-up dynamic programming).
     * <p>
     * <b>Intuition:</b> Builds the solution iteratively from the base cases up to n, storing results in a dp array.
     * <p>
     * <b>Algorithm:</b>
     * <ul>
     *   <li>If n is 1 or 2, return n.</li>
     *   <li>Initialize dp[1] = 1, dp[2] = 2.</li>
     *   <li>For i from 3 to n, set dp[i] = dp[i-1] + dp[i-2].</li>
     *   <li>Return dp[n].</li>
     * </ul>
     * <b>Parameters:</b>
     * @param n The total number of steps in the staircase.
     * <b>Returns:</b>
     * @return The number of distinct ways to reach the top.<br>
     * <b>Time Complexity:</b> O(n) (single pass through the array).<br>
     * <b>Space Complexity:</b> O(n) (dp array).
     * <b>Edge Cases:</b> n <= 2 returns n directly.
     */
    public int usingTabulation(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Calculates the number of distinct ways to climb to the top using a space-optimized dynamic programming approach.
     * <p>
     * <b>Intuition:</b> Only the last two computed values are needed at any time, so use two variables instead of an array.
     * <p>
     * <b>Algorithm:</b>
     * <ul>
     *   <li>If n is 1 or 2, return n.</li>
     *   <li>Initialize prev2 = 1 (ways to reach step 1), prev = 2 (ways to reach step 2).</li>
     *   <li>For i from 3 to n, compute current = prev + prev2, then update prev2 and prev.</li>
     *   <li>Return prev (ways to reach the nth step).</li>
     * </ul>
     * <b>Parameters:</b>
     * @param n The total number of steps in the staircase.
     * <b>Returns:</b>
     * @return The number of distinct ways to reach the top.<br>
     * <b>Time Complexity:</b> O(n) (single pass, constant work per step).<br>
     * <b>Space Complexity:</b> O(1) (constant space, no array needed).
     * <b>Edge Cases:</b> n <= 2 returns n directly.
     */
    public int usingSpaceOptimized(int n) {
        if (n <= 2) {
            return n;
        }
        int prev2 = 1, prev = 2;
        for (int i = 3; i <= n; i++) {
            int current = prev + prev2;
            prev2 = prev;
            prev = current;
        }
        return prev;
    }
}
