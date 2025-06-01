package com.dsa.leetcode.topics.slidingwindow.imp;

/**
 * 📊 Problem: Binary Subarrays With Sum
 * <p>
 * Given a binary array (only contains 0s and 1s), return the number of subarrays
 * that sum up to a given target value `goal`.
 * <p>
 * A subarray is a contiguous, non-empty portion of the array.
 * <p>
 * 🔍 Problem Link: <a href="https://leetcode.com/problems/binary-subarrays-with-sum/">LeetCode 930</a>
 *
 * <p><b>Examples:</b>
 * <ul>
 *   <li>Input: nums = [1,0,1,0,1], goal = 2 → Output: 4<br>
 *       Explanation: The subarrays are [1,0,1], [0,1,0], [1,0,1] (starting at different positions).</li>
 *   <li>Input: nums = [0,0,0,0,0], goal = 0 → Output: 15<br>
 *       Explanation: Every possible subarray (including overlapping) sums to 0.</li>
 *   <li>Input: nums = [1,1,1,1], goal = 3 → Output: 2</li>
 * </ul>
 *
 * <p><b>Tricky Edge Cases:</b>
 * <ul>
 *   <li>Goal is 0 → This means we count all subarrays made entirely of 0s.</li>
 *   <li>Array with alternating 0s and 1s → Need precise windowing to avoid overcounting.</li>
 * </ul>
 *
 * This class provides both a brute-force and optimized sliding window solution.
 */
public class BinarySubArraysWithSum_930 {

    /**
     * 🚫 <b>Why Standard Sliding Window Fails for <code>sum == k</code> Problems</b>
     *
     * <p>This problem asks:</p>
     * <blockquote>“How many subarrays have exact sum == k?”</blockquote>
     *
     * <p>Here's why the standard sliding window approach fails:</p>
     *
     * <ul>
     *   <li>✅ Sometimes you need to <b>expand</b> to find a valid subarray.</li>
     *   <li>✅ Sometimes you need to <b>shrink</b> to reach the target sum.</li>
     *   <li>⚠️ But even after finding a valid sum, you may need to <b>keep shrinking</b> —
     *       because multiple subarrays ending at the same right index may be valid.</li>
     *   <li>❌ Standard sliding window finds only one subarray per expansion and <b>misses overlapping or nested valid subarrays</b>.</li>
     * </ul>
     *
     * <p><b>Example:</b> nums = [0, 0, 0, 1, 1, 0, 0], goal = 2</p>
     * <pre>
     *   Indexes:  0  1  2  3  4  5  6
     *   Values : [0, 0, 0, 1, 1, 0, 0]
     *
     *   Sliding window progression:
     *
     *   left=0, right=0 → sum=0 → expand
     *   left=0, right=1 → sum=0 → expand
     *   left=0, right=2 → sum=0 → expand
     *   left=0, right=3 → sum=1 → expand
     *   left=0, right=4 → sum=2 → match found ✅
     *   left=0, right=5 → sum=2 → another match ✅, but...
     *   earlier valid subarrays ending at right=5 get skipped ❌
     * </pre>
     *
     * <p><b>Problem:</b> After finding a match, standard window doesn't shrink or backtrack to explore
     * all subarrays ending at current right — missing valid results.</p>
     *
     * <p><b>✅ Better Approach:</b> Use inclusion-exclusion:</p>
     * <ul>
     *   <li>Count subarrays with sum ≤ goal → <code>atMost(goal)</code></li>
     *   <li>Count subarrays with sum ≤ goal - 1 → <code>atMost(goal - 1)</code></li>
     *   <li>Then, exact match = <code>atMost(goal) - atMost(goal - 1)</code></li>
     * </ul>
     */
    public void note(){}


    /**
     * 💡 Brute-force method to count subarrays with a sum equal to the goal.
     *
     * @param nums The binary input array (only 0s and 1s).
     * @param goal The target sum for subarrays.
     * @return Number of subarrays that sum exactly to the given goal.
     *
     * 🔍 Intuition:
     * Try every possible subarray and count the ones that sum to the goal.
     *
     * 🪜 Algorithm Steps:
     * 1. Initialize a counter to zero.
     * 2. Loop through all starting indices i of the array.
     * 3. For each i, loop through ending indices j ≥ i.
     * 4. Keep a running sum from i to j.
     * 5. If the running sum equals the goal, increment the counter.
     *
     * 🧠 Parameters:
     * @param nums The input binary array.
     * @param goal The desired subarray sum.
     *
     * 🔁 Return:
     * @return The total count of valid subarrays with sum == goal.
     *
     * ⏱️ Time Complexity: O(n²) <br>
     * 🗂️ Space Complexity: O(1) <br>
     * Because we're using two nested loops for all possible subarrays.
     *
     * ⚠️ Edge Cases:
     * - Handles zeros correctly.
     * - Will time out on large arrays due to quadratic complexity.
     */
    public int bruteForce(int[] nums, int goal) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == goal) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * ⚡ Optimized method using sliding window + inclusion-exclusion trick.
     *
     * @param nums The binary input array.
     * @param goal The target sum for subarrays.
     * @return The number of subarrays that sum exactly to the goal.
     *
     * 🔍 Intuition:
     * Instead of counting subarrays with *exactly* sum = goal,
     * count those with sum ≤ goal and subtract those with sum ≤ goal - 1.
     *
     * Think of it like this:
     * - "Exactly k" = "At most k" − "At most k − 1"
     * - This trick allows us to use a single sliding window approach.
     *
     * 🪜 Algorithm Steps:
     * 1. Call a helper method to count subarrays with sum at most `goal`.
     * 2. Call it again with `goal - 1`.
     * 3. Subtract the two results.
     *
     * 🧠 Parameters:
     * @param nums The input binary array.
     * @param goal The exact sum to match.
     *
     * 🔁 Return:
     * @return Total number of subarrays that sum exactly to `goal`.
     *
     * ⏱️ Time Complexity: O(n) <br>
     * 🗂️ Space Complexity: O(1) <br>
     * Because we're iterating once through the array with two pointers and using constant space.
     *
     * ⚠️ Edge Cases:
     * - Goal can be 0 → then we want only fully-zero subarrays.
     * - Goal - 1 can be negative → handled in the helper method.
     */
    public int slidingWindow(int[] nums, int goal) {
        return countSubArraysWithSumAtMostK(nums, goal) - countSubArraysWithSumAtMostK(nums, goal - 1);
    }

    /**
     * 🪟 Helper method to count subarrays with sum ≤ k using sliding window.
     *
     * @param nums The binary input array.
     * @param k The maximum allowable sum for subarrays.
     * @return The number of subarrays where the sum is at most k.
     *
     * 🔍 Intuition:
     * We keep expanding the window to the right as long as the sum stays ≤ k.
     * When it exceeds, we move the left pointer to shrink it.
     * Every time we add a new right element, the number of valid subarrays
     * ending at right is (right - left + 1).
     *
     * 🪜 Algorithm Steps:
     * 1. Initialize two pointers `left` and `right` and a sum variable.
     * 2. Expand `right` and add to sum.
     * 3. If sum > k, shrink the window from the left.
     * 4. At each step, count subarrays ending at `right` using (right - left + 1).
     *
     * 🧠 Parameters:
     * @param nums The binary array.
     * @param k The max sum allowed in the subarray.
     *
     * 🔁 Return:
     * @return Count of subarrays with sum ≤ k.
     *
     * ⏱️ Time Complexity: O(n) <br>
     * 🗂️ Space Complexity: O(1) <br>
     * Since we traverse the array once using two pointers.
     *
     * ⚠️ Edge Cases:
     * - If k < 0, no valid subarray is possible → return 0 immediately.
     */
    public int countSubArraysWithSumAtMostK(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        int n = nums.length;
        int count = 0;
        int left = 0;
        int sum = 0;
        for (int right = 0; right < n; right++) {
            sum = sum + nums[right];
            while (sum > k) {
                sum = sum - nums[left];
                left++;
            }
            count = count + (right - left + 1);
        }
        return count;
    }
}
