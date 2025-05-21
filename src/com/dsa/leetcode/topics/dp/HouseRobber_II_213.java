package com.dsa.leetcode.topics.dp;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * <a href="https://leetcode.com/problems/house-robber-ii/">link</a>
 */
public class HouseRobber_II_213 {

    /**
     * Solves the House Robber II problem using plain recursion.
     * <p>
     * <b>Intuition:</b> Since the houses are arranged in a circle, robbing the first and last house together is not allowed. The problem is split into two linear subproblems:
     * <ul>
     *   <li>Rob houses from index 0 to n-2 (exclude last house)</li>
     *   <li>Rob houses from index 1 to n-1 (exclude first house)</li>
     * </ul>
     * The maximum of these two cases is the answer.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>If only one house, return its value.</li>
     *   <li>Otherwise, recursively compute the max loot for both cases above using a helper.</li>
     * </ol>
     *
     * @param nums Array of non-negative integers representing money in each house
     * @return Maximum amount of money that can be robbed without alerting the police
     *
     * <b>Time Complexity:</b> O(2^n) in the worst case (exponential, due to overlapping subproblems)
     * <b>Space Complexity:</b> O(n) for recursion stack
     *
     * <b>Edge Cases:</b> Handles single-house input. Not efficient for large inputs.
     */
    public int robRecursion(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRecursionHelper(nums, 0, n - 2), robRecursionHelper(nums, 1, n - 1));
    }

    /**
     * Helper for the recursive approach.
     * <p>
     * At each step, chooses to either rob the current house and skip the next, or skip the current house.
     *
     * @param nums Array of house values
     * @param start Start index (inclusive)
     * @param end End index (inclusive)
     * @return Maximum money that can be robbed from the subarray [start, end]
     */
    private int robRecursionHelper(int[] nums, int start, int end) {
        if (start > end) return 0;
        int rob = nums[start] + robRecursionHelper(nums, start + 2, end);
        int skip = robRecursionHelper(nums, start + 1, end);
        return Math.max(rob, skip);
    }

    /**
     * Solves House Robber II using memoization (top-down dynamic programming).
     * <p>
     * <b>Intuition:</b> Same as the recursive approach, but caches results to avoid redundant calculations.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>If only one house, return its value.</li>
     *   <li>Otherwise, use a helper with a memoization array to store results for each subproblem.</li>
     * </ol>
     *
     * @param nums Array of non-negative integers representing money in each house
     * @return Maximum amount of money that can be robbed without alerting the police
     *
     * <b>Time Complexity:</b> O(n), each subproblem is solved once
     * <b>Space Complexity:</b> O(n) for memoization array and recursion stack
     *
     * <b>Edge Cases:</b> Handles single-house input. Efficient for large inputs.
     */
    public int robMemoization(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robMemoHelper(nums, 0, n - 2, new Integer[n]), robMemoHelper(nums, 1, n - 1, new Integer[n]));
    }

    /**
     * Helper for the memoization approach.
     * <p>
     * Uses a dp array to cache results for each start index.
     *
     * @param nums Array of house values
     * @param start Start index (inclusive)
     * @param end End index (inclusive)
     * @param dp Memoization array (stores max loot for each start index)
     * @return Maximum money that can be robbed from the subarray [start, end]
     */
    private int robMemoHelper(int[] nums, int start, int end, Integer[] dp) {
        if (start > end) return 0;
        if (dp[start] != null) return dp[start];
        int rob = nums[start] + robMemoHelper(nums, start + 2, end, dp);
        int skip = robMemoHelper(nums, start + 1, end, dp);
        return dp[start] = Math.max(rob, skip);
    }

    /**
     * Solves House Robber II using tabulation (bottom-up dynamic programming).
     * <p>
     * <b>Intuition:</b> Converts the recursive relation into an iterative process, filling a dp array from the base cases up.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>If only one house, return its value.</li>
     *   <li>Otherwise, for both cases (excluding first or last house), fill a dp array where dp[i] is the max loot up to house i.</li>
     *   <li>Return the maximum of the two cases.</li>
     * </ol>
     *
     * @param nums Array of non-negative integers representing money in each house
     * @return Maximum amount of money that can be robbed without alerting the police
     *
     * <b>Time Complexity:</b> O(n), where n is the number of houses
     * <b>Space Complexity:</b> O(n) for the dp array
     *
     * <b>Edge Cases:</b> Handles single-house input. Efficient and easy to understand.
     */
    public int robTabulation(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robTabHelper(nums, 0, n - 2), robTabHelper(nums, 1, n - 1));
    }

    /**
     * Helper for the tabulation approach.
     * <p>
     * Iteratively fills a dp array where each entry represents the max loot up to that house.
     *
     * @param nums Array of house values
     * @param start Start index (inclusive)
     * @param end End index (inclusive)
     * @return Maximum money that can be robbed from the subarray [start, end]
     *
     * <b>Time Complexity:</b> O(n)
     * <b>Space Complexity:</b> O(n)
     */
    private int robTabHelper(int[] nums, int start, int end) {
        int n = end - start + 1;
        if (n == 0) return 0;
        if (n == 1) return nums[start];
        int[] dp = new int[n];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[start + i] + dp[i - 2]);
        }
        return dp[n - 1];
    }

    /**
     * Solves House Robber II using space-optimized dynamic programming.
     * <p>
     * <b>Intuition:</b> The current state only depends on the previous two states, so a full dp array is unnecessary.
     * <p>
     * <b>Algorithm:</b>
     * <ol>
     *   <li>If only one house, return its value.</li>
     *   <li>Otherwise, for both cases (excluding first or last house), use two variables to keep track of the max loot up to the previous two houses.</li>
     *   <li>Return the maximum of the two cases.</li>
     * </ol>
     *
     * @param nums Array of non-negative integers representing money in each house
     * @return Maximum amount of money that can be robbed without alerting the police
     *
     * <b>Time Complexity:</b> O(n), where n is the number of houses
     * <b>Space Complexity:</b> O(1), uses only constant extra space
     *
     * <b>Edge Cases:</b> Handles single-house input. Most efficient in terms of space.
     */
    public int robSpaceOptimized(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robSpaceHelper(nums, 0, n - 2), robSpaceHelper(nums, 1, n - 1));
    }

    /**
     * Helper for the space-optimized approach.
     * <p>
     * Iteratively computes the max loot using only two variables to store previous results.
     *
     * @param nums Array of house values
     * @param start Start index (inclusive)
     * @param end End index (inclusive)
     * @return Maximum money that can be robbed from the subarray [start, end]
     *
     * <b>Time Complexity:</b> O(n)
     * <b>Space Complexity:</b> O(1)
     */
    private int robSpaceHelper(int[] nums, int start, int end) {
        int prev2 = 0, prev1 = 0;
        for (int i = start; i <= end; i++) {
            int curr = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}

