package com.dsa.leetcode.topics.slidingwindow.imp;

/**
 * <b>Max Consecutive Ones III</b>
 * <p>
 * <b>Problem Statement:</b><br>
 * Given a binary array <code>nums</code> and an integer <code>k</code>, return the maximum number of consecutive 1's in the array if you can flip at most <code>k</code> 0's to 1's.<br>
 * In other words, you are allowed to change up to <code>k</code> zeros to ones, and you want the longest possible sequence of 1's you can get in a row.
 * <p>
 * <b>Tricky or Edge-Case Examples:</b><br>
 * <ul>
 *   <li><b>Input:</b> nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2 <b>Output:</b> 6 (flip two zeros at positions 5 and 10)</li>
 *   <li><b>Input:</b> nums = [0,0,1,1,1,0,0], k = 0 <b>Output:</b> 3 (no flips allowed, so just the longest run of 1's)</li>
 *   <li><b>Input:</b> nums = [1,1,1,1], k = 2 <b>Output:</b> 4 (no zeros to flip, so the answer is the array length)</li>
 *   <li><b>Input:</b> nums = [0,0,0,0], k = 4 <b>Output:</b> 4 (can flip all zeros to ones)</li>
 *   <li><b>Input:</b> nums = [1,0,1,0,1], k = 1 <b>Output:</b> 3 (flip either zero for a run of three 1's)</li>
 * </ul>
 * <p>
 * <b>Original Problem Link:</b><br>
 * <a href="https://leetcode.com/problems/max-consecutive-ones-iii/">LeetCode 1004: Max Consecutive Ones III</a>
 * <p>
 * <b>Notes:</b> This class provides three approaches: brute force, sliding window, and an optimized sliding window.
 */
public class MaxConsecutiveOnes_III_1004 {

    /**
     * Finds the maximum number of consecutive 1's in the array if you can flip at most k 0's to 1's, using brute force.
     * <p>
     * <b>Intuition:</b> Try every possible subarray and count how many zeros you would need to flip. If you flip more than k, stop checking that subarray.
     * <p>
     * <b>Step-by-step:</b>
     * <ol>
     *   <li>Start from each index in the array.</li>
     *   <li>Expand the subarray to the right, counting zeros.</li>
     *   <li>If the zero count exceeds k, stop expanding.</li>
     *   <li>Track the maximum length of valid subarrays found.</li>
     * </ol>
     *
     * @param nums The binary input array (only 0's and 1's).
     * @param k The maximum number of zeros you can flip to ones.
     * @return The length of the longest subarray with at most k zeros flipped to ones.
     * <br><b>Time Complexity:</b> O(n^2) — For each starting index, we may scan the rest of the array (nested loops).
     * <br><b>Space Complexity:</b> O(1) — Only a few integer variables are used.
     * <p>
     * <b>Edge Cases:</b> Handles empty arrays, arrays with all 1's or all 0's, and k = 0 or k >= array length.
     */
    public int bruteForce(int[] nums, int k) {
        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int zeroCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) {
                    zeroCount++;
                }
                if (zeroCount > k) {
                    break; // More than k zeros, stop expanding this window
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }

    /**
     * Finds the maximum number of consecutive 1's in the array if you can flip at most k 0's to 1's, using a sliding window.
     * <p>
     * <b>Intuition:</b> Use two pointers to create a window. Expand the window to the right, and if you have more than k zeros, shrink from the left until you have at most k zeros in the window.
     * <p>
     * <b>Step-by-step:</b>
     * <ol>
     *   <li>Initialize left and right pointers, and a zero counter.</li>
     *   <li>Move the right pointer, counting zeros.</li>
     *   <li>If zero count exceeds k, move the left pointer right and decrease zero count if you pass a zero.</li>
     *   <li>Update the maximum window size after each step.</li>
     * </ol>
     *
     * @param nums The binary input array (only 0's and 1's).
     * @param k The maximum number of zeros you can flip to ones.
     * @return The length of the longest subarray with at most k zeros flipped to ones.
     * <br><b>Time Complexity:</b> O(n) — Each element is visited at most twice (once by right, once by left pointer).
     * <br><b>Space Complexity:</b> O(1) — Only a few integer variables are used.
     * <p>
     * <b>Edge Cases:</b> Handles empty arrays, arrays with all 1's or all 0's, and k = 0 or k >= array length.
     */
    public int betterUsingSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int left = 0, zeroCount = 0, maxLength = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * 💡 Returns the maximum length of a contiguous subarray that contains only 1s after flipping at most <code>k</code> zeroes.
     *
     * <p><b>Intuition:</b><br>
     * Think of a window (like a sliding glass door) moving across the array. As you expand the window to the right,
     * you count how many 0s you've "picked up". If you ever exceed the allowed number of flips (k),
     * you shrink the window from the left until you're back within the limit.
     * This way, you always keep the window as wide as possible while satisfying the constraint.
     *
     * <p><b>Algorithm Walkthrough:</b>
     * <ol>
     *   <li>Initialize two pointers: <code>left</code> (start of window), <code>right</code> (end of window).</li>
     *   <li>As you iterate through <code>nums</code>:
     *       <ul>
     *         <li>If you see a 0, increment the <code>zeroCount</code>.</li>
     *         <li>While <code>zeroCount</code> exceeds <code>k</code>, move the <code>left</code> pointer forward
     *             (and decrease <code>zeroCount</code> if a 0 is passed).</li>
     *         <li>Each time <code>zeroCount</code> is within the limit, update <code>maxLength</code> to the window size.</li>
     *       </ul>
     *   </li>
     * </ol>
     *
     * @param nums the input binary array consisting only of 0s and 1s
     * @param k the maximum number of 0s you are allowed to flip to 1s
     * @return the length of the longest contiguous subarray that contains only 1s after flipping at most <code>k</code> zeroes
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * We visit each element at most twice — once when expanding the window (right++) and once when shrinking it (left++).
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * Only a few integer variables are used — no extra space that grows with input size.
     *
     * <p><b>Edge Cases to Consider:</b>
     * <ul>
     *   <li><code>nums</code> is empty — return 0.</li>
     *   <li><code>k = 0</code> — no flips allowed, find the max stretch of natural 1s.</li>
     *   <li>All 0s — result is min(k, nums.length).</li>
     * </ul>
     */
    public int optimalUsingSmartSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int left = 0, zeroCount = 0, maxLength = 0;

        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            if (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            if (zeroCount <= k) {
                // If the number of zeros is within the allowed limit, update maxLength
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }
}



