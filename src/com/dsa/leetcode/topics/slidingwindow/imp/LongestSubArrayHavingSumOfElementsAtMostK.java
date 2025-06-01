package com.dsa.leetcode.topics.slidingwindow.imp;

/**
 * This class provides multiple approaches to solve the problem of finding the
 * length of the longest contiguous subarray such that the sum of its elements
 * is less than or equal to a given value `k`.
 */
public class LongestSubArrayHavingSumOfElementsAtMostK {

    /**
     * Brute Force Approach to find the length of the longest subarray
     * with sum at most k.
     *
     * <p><b>Summary:</b> This method tries every possible subarray,
     * checks their sum, and keeps track of the maximum length
     * for which the sum is less than or equal to k.</p>
     *
     * <p><b>Intuition:</b> Check all combinations of starting and ending
     * indices to find valid subarrays. This is a brute force way to solve the problem.</p>
     *
     * <p><b>Step-by-step logic:</b><br>
     * - Loop from every possible start index `i`<br>
     * - For each `i`, extend the subarray to index `j`<br>
     * - Accumulate the sum and stop early if the sum exceeds `k`<br>
     * - Update max length whenever the sum is valid (≤ k)</p>
     *
     * @param nums the input array of integers (assumed to be non-negative)
     * @param k    the maximum allowed sum of a subarray
     * @return the maximum length of any contiguous subarray whose sum ≤ k
     *
     * <p><b>Time Complexity:</b> O(n²)<br>
     * Because we are using two nested loops, each running up to n.</p>
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * No extra space used apart from a few variables.</p>
     *
     * <p><b>Edge Cases:</b> <br>
     * - If the array is empty, return 0<br>
     * - If no subarray satisfies the condition, return 0</p>
     */
    public int bruteForce(int[] nums, int k) {
        int maxLength = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                } else {
                    break; // No need to continue if sum exceeds k
                }
            }
        }
        return maxLength;
    }

    /**
     * Improved approach using Sliding Window technique.
     *
     * <p><b>Summary:</b> This method uses two pointers (left and right) to
     * create a window that expands and contracts to find valid subarrays efficiently.</p>
     *
     * <p><b>Intuition:</b> When the sum becomes invalid (i.e. > k),
     * shrink the window from the left until it becomes valid again.</p>
     *
     * <p><b>Step-by-step logic:</b><br>
     * - Start with both pointers at 0 and keep a running sum<br>
     * - Move `right` to add more elements<br>
     * - If sum > k, move `left` to reduce sum until valid<br>
     * - After each move, check and update the max length</p>
     *
     * @param nums the input array of integers (non-negative)
     * @param k    the maximum allowed sum of a subarray
     * @return the maximum length of any contiguous subarray whose sum ≤ k
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * Each element is visited at most twice (once by right, once by left)</p>
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * No extra space used beyond variables</p>
     *
     * <p><b>Edge Cases:</b><br>
     * - Assumes non-negative numbers for reliable shrinking<br>
     * - With negative numbers, this logic may break</p>
     */
    public int betterApproachUsingSlidingWindow(int[] nums, int k) {
        int maxLength = 0;
        int n = nums.length;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum = sum + nums[right];

            while (sum > k) {
                sum = sum - nums[left];
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Slightly optimized version of sliding window approach.
     *
     * <p><b>Summary:</b> Similar to the better approach but with a more
     * compact structure and earlier validation to avoid unnecessary window updates.</p>
     *
     * <p><b>Intuition:</b> The key idea is to shrink the window only when
     * sum exceeds `k`, and check for max length only when it’s valid.</p>
     *
     * <p><b>Step-by-step logic:</b><br>
     * - Add the current element to the sum<br>
     * - If sum > k, shrink from the left<br>
     * - If sum ≤ k, update the max length<br>
     * - This avoids calling `max()` when sum is invalid</p>
     *
     * @param nums the input array of integers (non-negative)
     * @param k    the maximum allowed sum of a subarray
     * @return the maximum length of any contiguous subarray whose sum ≤ k
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * Right and left pointers each traverse the array at most once</p>
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * Constant extra space usage</p>
     *
     * <p><b>Edge Cases:</b><br>
     * - If array contains negative numbers, this may not work correctly<br>
     * - Make sure input array is non-negative</p>
     */
    public int optimalApproachUsingSlidingWindow(int[] nums, int k) {
        int maxLength = 0;
        int n = nums.length;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            if (sum > k) {
                sum -= nums[left];
                left++;
            }
            if (sum <= k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }
}
