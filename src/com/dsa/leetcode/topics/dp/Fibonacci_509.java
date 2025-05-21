package com.dsa.leetcode.topics.dp;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 * <p>
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * <p>
 * The sequence is:
 * <p>
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...
 * <p>
 *
 *
 * Given n, calculate F(n).<br>
 * <a href="https://leetcode.com/problems/fibonacci-number/">link</a></a>
 */
public class Fibonacci_509 {

    /**
     * Calculates the nth Fibonacci number using simple recursion.
     * Intuition: Recursively computes F(n) by summing F(n-1) and F(n-2).
     * Time Complexity: O(2^n) - Exponential due to repeated subproblems.
     * Space Complexity: O(n) - Due to call stack depth.
     *
     * @param n the index of the Fibonacci number
     * @return the nth Fibonacci number
     */
    public int fibUsingRecursion(int n) {
        if (n <= 1) {
            return n;
        }
        return fibUsingRecursion(n - 1) + fibUsingRecursion(n - 2);
    }

    /**
     * Calculates the nth Fibonacci number using memoization (top-down DP).
     * Intuition: Uses a memo array to store results of subproblems to avoid recomputation.
     * Time Complexity: O(n) - Each subproblem is solved once.
     * Space Complexity: O(n) - For memo array and call stack.
     *
     * @param n the index of the Fibonacci number
     * @return the nth Fibonacci number
     */
    public int fibUsingMemoization(int n) {
        int[] memo = new int[n + 1];
        return fibUsingMemoizationHelper(n, memo);
    }

    /**
     * Helper for memoization approach.
     *
     * @param n the index
     * @param memo the memoization array
     * @return the nth Fibonacci number
     */
    private int fibUsingMemoizationHelper(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = fibUsingMemoizationHelper(n - 1, memo) + fibUsingMemoizationHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Calculates the nth Fibonacci number using tabulation (bottom-up DP).
     * Intuition: Builds up the solution from the base cases using an array.
     * Time Complexity: O(n) - Iterates from 2 to n.
     * Space Complexity: O(n) - For the dp array.
     *
     * @param n the index of the Fibonacci number
     * @return the nth Fibonacci number
     */
    public int fibUsingTabulation(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Calculates the nth Fibonacci number using space-optimized DP.
     * Intuition: Only stores the last two computed values to save space.
     * Time Complexity: O(n) - Iterates from 2 to n.
     * Space Complexity: O(1) - Uses constant space.
     *
     * @param n the index of the Fibonacci number
     * @return the nth Fibonacci number
     */
    public int fibUsingSpaceOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        int prev2 = 0;
        int prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

}
