package com.dsa.leetcode.blind75.SlidingWindow;

// https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/?envType=study-plan-v2&envId=leetcode-75
public class LongestSubarrayOf1sAfterDeletingOneElement_1493 {

    public int longestSubarray(int[] nums) {
        return bruteForce(nums);
        //        return longestSubarrayBetter(nums);
    }

    public int bruteForce(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int currentLength = 0;
            int zeroes = 0;
            for (int j = i; j < n; j++) {
                if (nums[j] == 0) {
                    zeroes++;
                }
                if (zeroes > 1) {
                    break;
                }
                currentLength = j - i; // handling deletion of one char here
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    /**
     * longestSubarrayBetter: Uses a sliding window to calculate the longest subarray efficiently.
     *
     * <b>Intuition:</b>
     * Use two pointers to create a sliding window. Allow at most one zero in the current window.
     * Shrink the window if more than one zero exists.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Use two pointers `start` and `end` to maintain a window.
     * 2. Keep a count of zeros in the current window.
     * 3. Expand the window by incrementing `end`.
     * 4. Shrink the window by incrementing `start` if zero count > 1.
     * 5. Track the maximum length of the valid window (subtracting one element for deletion).
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n) \) - Each pointer traverses the array once.
     *
     * <b>Space Complexity:</b>
     * \( O(1) \) - No additional space used.
     *
     * @param nums Input binary array.
     * @return Maximum length of subarray of 1s after removing one element.
     */
    public int longestSubarrayBetter(int[] nums) {
        int start = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                zeroCount++;
            }

            while (zeroCount > 1) {
                if (nums[start] == 0) {
                    zeroCount--;
                }
                start++;
            }

            // Subtract one element to simulate the deletion
            maxLength = Math.max(maxLength, end - start);
        }

        return maxLength;
    }

}
