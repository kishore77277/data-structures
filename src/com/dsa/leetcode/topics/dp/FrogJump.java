package com.dsa.leetcode.topics.dp;

/**
 * <h2>Frog Jump - Minimum Cost Problem</h2>
 * <p>
 * Given an integer array <b>height[]</b> where <b>height[i]</b> represents the height of the i-th stair,
 * a frog starts from the first stair and wants to reach the top (last stair).
 * </p>
 * <ul>
 *   <li>From any stair <b>i</b>, the frog can jump to either the (i+1)th or (i+2)th stair.</li>
 *   <li>The cost of a jump is the absolute difference in height between the two stairs: <b>|height[i] - height[j]|</b>.</li>
 *   <li>Determine the <b>minimum total cost</b> required for the frog to reach the top.</li>
 * </ul>
 * <p>
 * <b>Example:</b><br>
 * height = [10, 30, 40, 20]<br>
 * Output: 30<br>
 * Explanation: Jump from 0 → 1 (cost 20), then 1 → 3 (cost 10). Total = 20 + 10 = 30.
 * </p>
 * <a href="https://www.geeksforgeeks.org/problems/geek-jump/1">Related GFG Problem: Geek Jump</a>
 */
public class FrogJump {

    /**
     * Calculates the minimum total cost for the frog to reach the nth stair using pure recursion.
     * <p>
     * <b>Intuition:</b> At each stair, the frog can jump to the next stair or skip one stair. The cost is the minimum of these two options.
     * <br><b>Algorithm:</b>
     * <ul>
     *   <li>If n == 0, the frog is at the starting stair, so cost is 0.</li>
     *   <li>If n == 1, the cost is the absolute difference between the first and second stair.</li>
     *   <li>Otherwise, recursively calculate the cost for jumping from n-1 and n-2, and return the minimum.</li>
     * </ul>
     * @param n The index of the target stair (0-based).
     * @param height The array of stair heights.
     * @return The minimum total cost to reach the nth stair.
     * <br><b>Time Complexity:</b> O(2^n) (exponential, due to overlapping subproblems).
     * <br><b>Space Complexity:</b> O(n) (call stack depth).
     * <br><b>Edge Cases:</b> n == 0 returns 0; n == 1 returns |height[1] - height[0]|.
     */
    public int usingRecursion(int n, int[] height) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return Math.abs(height[1] - height[0]);
        }
        int jumpOne = usingRecursion(n - 1, height) + Math.abs(height[n] - height[n - 1]);
        int jumpTwo = usingRecursion(n - 2, height) + Math.abs(height[n] - height[n - 2]);
        return Math.min(jumpOne, jumpTwo);
    }

    /**
     * Calculates the minimum total cost for the frog to reach the nth stair using memoization (top-down DP).
     * <p>
     * <b>Intuition:</b> Stores results of subproblems to avoid redundant calculations, improving efficiency.
     * <br><b>Algorithm:</b>
     * <ul>
     *   <li>Initialize a dp array with -1 to indicate uncomputed states.</li>
     *   <li>Use a helper function to recursively compute the minimum cost, storing and reusing results from dp.</li>
     * </ul>
     * @param n The index of the target stair (0-based).
     * @param height The array of stair heights.
     * @return The minimum total cost to reach the nth stair.
     * <br><b>Time Complexity:</b> O(n) (each subproblem solved once).
     * <br><b>Space Complexity:</b> O(n) (dp array + call stack).
     * <br><b>Edge Cases:</b> n == 0 returns 0; n == 1 returns |height[1] - height[0]|.
     */
    public int usingMemoization(int n, int[] height) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }
        return usingMemoizationHelper(n, height, dp);
    }

    /**
     * Helper for the memoization approach. Recursively computes the minimum cost to reach the nth stair,
     * storing results in the dp array to avoid recomputation.
     * @param n The index of the target stair (0-based).
     * @param height The array of stair heights.
     * @param dp The memoization array, where dp[i] stores the minimum cost to reach stair i.
     * @return The minimum total cost to reach the nth stair.
     * <br><b>Time Complexity:</b> O(n).
     * <br><b>Space Complexity:</b> O(n).
     */
    private int usingMemoizationHelper(int n, int[] height, int[] dp) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return Math.abs(height[1] - height[0]);
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int jumpOne = usingMemoizationHelper(n - 1, height, dp) + Math.abs(height[n] - height[n - 1]);
        int jumpTwo = usingMemoizationHelper(n - 2, height, dp) + Math.abs(height[n] - height[n - 2]);
        return dp[n] = Math.min(jumpOne, jumpTwo);
    }

    /**
     * Calculates the minimum total cost for the frog to reach the nth stair using tabulation (bottom-up DP).
     * <p>
     * <b>Intuition:</b> Builds the solution iteratively from the base cases, storing results in a dp array.
     * <br><b>Algorithm:</b>
     * <ul>
     *   <li>Initialize dp[0] = 0 and dp[1] = |height[1] - height[0]|.</li>
     *   <li>For each stair from 2 to n, compute the minimum cost using the previous two stairs.</li>
     *   <li>Return dp[n] as the answer.</li>
     * </ul>
     * @param n The index of the target stair (0-based).
     * @param height The array of stair heights.
     * @return The minimum total cost to reach the nth stair.
     * <br><b>Time Complexity:</b> O(n) (single pass through the array).
     * <br><b>Space Complexity:</b> O(n) (dp array).
     * <br><b>Edge Cases:</b> n == 0 returns 0; n == 1 returns |height[1] - height[0]|.
     */
    public int usingTabulation(int n, int[] height) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = Math.abs(height[1] - height[0]);
        for (int i = 2; i <= n; i++) {
            int jumpOne = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int jumpTwo = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            dp[i] = Math.min(jumpOne, jumpTwo);
        }
        return dp[n];
    }

    /**
     * Calculates the minimum total cost for the frog to reach the nth stair using a space-optimized approach.
     * <p>
     * <b>Intuition:</b> Only the last two computed values are needed at any time, so use two variables instead of an array.
     * <br><b>Algorithm:</b>
     * <ul>
     *   <li>Initialize prev2 = 0 (cost to reach stair 0), prev = |height[1] - height[0]| (cost to reach stair 1).</li>
     *   <li>For each stair from 2 to n, compute the minimum cost using prev and prev2, then update them.</li>
     *   <li>Return prev as the answer.</li>
     * </ul>
     * @param n The index of the target stair (0-based).
     * @param height The array of stair heights.
     * @return The minimum total cost to reach the nth stair.
     * <br><b>Time Complexity:</b> O(n) (single pass, constant work per step).
     * <br><b>Space Complexity:</b> O(1) (constant space, no array needed).
     * <br><b>Edge Cases:</b> n == 0 returns 0; n == 1 returns |height[1] - height[0]|.
     */
    public int usingSpaceOptimization(int n, int[] height) {
        int prev2 = 0;
        int prev = Math.abs(height[1] - height[0]);
        for (int i = 2; i <= n; i++) {
            int jumpOne = prev + Math.abs(height[i] - height[i - 1]);
            int jumpTwo = prev2 + Math.abs(height[i] - height[i - 2]);
            int curr = Math.min(jumpOne, jumpTwo);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }


}
