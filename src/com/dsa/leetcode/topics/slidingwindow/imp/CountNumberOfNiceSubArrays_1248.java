package com.dsa.leetcode.topics.slidingwindow.imp;

/**
 * <h2>LeetCode 1248: Count Number of Nice Subarrays</h2>
 *
 * <p>
 * Given an integer array <code>nums</code> and an integer <code>k</code>, return the number of "nice" subarrays.
 * A subarray is called <b>nice</b> if it contains exactly <code>k</code> odd numbers.
 * </p>
 *
 * <h3>💡 Problem Intuition</h3>
 * <p>
 * A subarray is any contiguous part of the array. We're interested only in subarrays that contain exactly <code>k</code> odd numbers.
 * The challenge is to efficiently count all such subarrays without generating all possible subarrays explicitly.
 * </p>
 *
 * <h3>🧪 Edge Cases & Examples</h3>
 * <pre>
 * Input: nums = [1, 1, 2, 1, 1], k = 3
 * Output: 2
 * Explanation: The subarrays [1, 1, 2, 1] and [1, 2, 1, 1] contain exactly 3 odd numbers.
 *
 * Input: nums = [2, 4, 6], k = 1
 * Output: 0
 * Explanation: There are no odd numbers at all.
 *
 * Input: nums = [0, 0, 0, 1, 1, 0, 0], k = 2
 * Output: 6
 * </pre>
 * <pre>
 *     in the above example, the valid subarrays are: 6
 *     - [0, 0, 0, 1, 1], our window starts at index 0 and ends at index 4
 *     now we can move our window to the right or left both are valid,
 *     so, we are in dilemma, we can move our window to the right or left
 *     so we will use <=k - <=k-1 to get the exact count of subarrays
 * </pre>
 *
 * <h3>🔗 Problem Link</h3>
 * <a href="https://leetcode.com/problems/count-number-of-nice-subarrays/">LeetCode #1248</a>
 *
 * @author
 */
public class CountNumberOfNiceSubArrays_1248 {

    /**
     * Brute force approach to count subarrays with exactly k odd numbers.
     *
     * <p><b>Intuition:</b> Try all subarrays and count the number of odd numbers in each.
     * If the count equals <code>k</code>, increment the result. Stop early if odd count exceeds <code>k</code>.
     * </p>
     *
     * <p><b>Step-by-step:</b></p>
     * <ol>
     *   <li>Use two nested loops to explore all subarrays.</li>
     *   <li>Track the count of odd numbers in each subarray.</li>
     *   <li>If the count matches <code>k</code>, it's a valid subarray.</li>
     *   <li>If the count exceeds <code>k</code>, break early (no need to check longer subarrays).</li>
     * </ol>
     *
     * @param nums The input array of integers.
     * @param k The exact number of odd numbers required in the subarray.
     * @return The number of nice subarrays (exactly k odd numbers).
     *
     * <p><b>Time Complexity:</b> O(n²) — due to nested loops checking all subarrays.<br>
     * <b>Space Complexity:</b> O(1) — no extra space used.</p>
     */
    public int bruteForce(int[] nums, int k) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int oddCount = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] % 2 != 0) {
                    oddCount++;
                }
                if (oddCount == k) {
                    count++;
                } else if (oddCount > k) {
                    break; // No need to continue if we exceed k odd numbers
                }
            }
        }

        return count;
    }

    /**
     * Efficient approach to count the number of nice subarrays using the prefix sum trick.
     *
     * <p><b>Intuition:</b> It's easier to count subarrays with at most <code>k</code> odd numbers, so we use inclusion-exclusion:
     * <code>exactly(k) = atMost(k) - atMost(k - 1)</code></p>
     *
     * <p><b>Step-by-step:</b></p>
     * <ol>
     *   <li>Call helper method to count subarrays with ≤ k odd numbers.</li>
     *   <li>Call again with (k - 1) to exclude extra subarrays.</li>
     *   <li>Subtract to get exact count of subarrays with exactly k odd numbers.</li>
     * </ol>
     *
     * @param nums The input array of integers.
     * @param k The exact number of odd numbers required in subarrays.
     * @return The number of subarrays that contain exactly <code>k</code> odd numbers.
     *
     * <p><b>Time Complexity:</b> O(n) — each element is processed once by the sliding window.<br>
     * <b>Space Complexity:</b> O(1) — constant extra space used.</p>
     */
    public int slidingWindow(int[] nums, int k) {
        return countSubArraysWithOddNumbersCountAtMostK(nums, k) - countSubArraysWithOddNumbersCountAtMostK(nums, k - 1);
    }

    /**
     * Helper method to count subarrays with at most <code>k</code> odd numbers.
     *
     * <p><b>Intuition:</b> Use sliding window to keep track of the number of odd numbers.
     * For each valid window (with ≤ k odd numbers), the number of subarrays ending at <code>right</code>
     * is <code>right - left + 1</code>.</p>
     *
     * <p><b>Step-by-step:</b></p>
     * <ol>
     *   <li>Initialize two pointers: <code>left</code> and <code>right</code>.</li>
     *   <li>Expand <code>right</code> pointer to include new elements and count odds.</li>
     *   <li>If the count of odd numbers exceeds <code>k</code>, move <code>left</code> to shrink the window.</li>
     *   <li>Each time we adjust the window, add the count of valid subarrays ending at <code>right</code>.</li>
     * </ol>
     *
     * @param nums The input array of integers.
     * @param k Maximum allowed odd numbers in the subarray.
     * @return Number of subarrays with at most <code>k</code> odd numbers.
     *
     * <p><b>Time Complexity:</b> O(n) — both pointers traverse the array once.<br>
     * <b>Space Complexity:</b> O(1) — no additional space needed.</p>
     *
     * <p><b>Edge Case:</b> If k is 0, the method still works and returns subarrays with no odd numbers.</p>
     */
    public int countSubArraysWithOddNumbersCountAtMostK(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        int left = 0;
        int oddNumbersCount = 0;
        for (int right = 0; right < n; right++) {
            if ((nums[right] & 1) == 1) {
                oddNumbersCount++;
            }
            while (oddNumbersCount > k) {
                if ((nums[left] & 1) == 1) {
                    oddNumbersCount--;
                }
                left++;
            }
            count = count + (right - left + 1);
        }
        return count;
    }
}
