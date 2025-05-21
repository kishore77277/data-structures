package com.dsa.leetcode.topics.dp;

/**
 * frog jump with k distances defines the minimum cost to reach the top of a staircase with k distances.
 */
public class FrogJumpWithKDistances {

    public int usingRecursion(int n, int k, int[] height) {
        if (n == 0) {
            return 0;
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if (n - i >= 0) {
                int jumpCost = usingRecursion(n - i, k, height) + Math.abs(height[n] - height[n - i]);
                minCost = Math.min(minCost, jumpCost);
            }
        }
        return minCost;
    }

    public int usingMemoization(int n, int k, int[] height) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }
        return usingMemoizationHelper(n, k, height, dp);
    }

    private int usingMemoizationHelper(int n, int k, int[] height, int[] dp) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if (n - i >= 0) {
                int jumpCost = usingMemoizationHelper(n - i, k, height, dp) + Math.abs(height[n] - height[n - i]);
                minCost = Math.min(minCost, jumpCost);
            }
        }
        return dp[n] = minCost;
    }

    public int usingTabulation(int n, int k, int[] height) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int minCost = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jumpCost = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    minCost = Math.min(minCost, jumpCost);
                }
            }
            dp[i] = minCost;
        }
        return dp[n];
    }


}
