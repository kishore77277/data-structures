package com.dsa.leetcode.topics.dp;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *<br>
 * <a href="https://leetcode.com/problems/house-robber/">link</a>
 */
public class HouseRobber_198 {

    public int usingRecursion(int n, int[] nums) {
        if (n == 0) {
            return nums[0];
        }
        if (n < 0) {
            return 0;
        }
        int rob = usingRecursion(n - 2, nums) + nums[n];
        int skip = usingRecursion(n - 1, nums);
        return Math.max(rob, skip);
    }

    public int usingMemoization(int n, int[] nums) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }
        return usingMemoizationHelper(n, nums, dp);
    }

    private int usingMemoizationHelper(int n, int[] nums, int[] dp) {
        if (n == 0) {
            return nums[0];
        }
        if (n < 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int rob = usingMemoizationHelper(n - 2, nums, dp) + nums[n];
        int skip = usingMemoizationHelper(n - 1, nums, dp);
        return dp[n] = Math.max(rob, skip);
    }

    public int usingTabulation(int n, int[] nums) {
        int[] dp = new int[n + 1];
        dp[0] = nums[0];
        for (int i = 1; i <= n; i++) {
            int rob = (i - 2 >= 0 ? dp[i - 2] : 0) + nums[i];
            int skip = dp[i - 1];
            dp[i] = Math.max(rob, skip);
        }
        return dp[n];
    }

    public int usingSpaceOptimized(int n, int[] nums) {
        int prev1 = nums[0];
        int prev2 = 0;
        for (int i = 1; i <= n; i++) {
            int rob = (i - 2 >= 0 ? prev2 : 0) + nums[i];
            int skip = prev1;
            int curr = Math.max(rob, skip);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}





