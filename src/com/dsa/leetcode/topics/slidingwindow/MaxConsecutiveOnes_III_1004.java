package com.dsa.leetcode.topics.slidingwindow;

// https://leetcode.com/problems/max-consecutive-ones-iii/description/?envType=study-plan-v2&envId=leetcode-75
public class MaxConsecutiveOnes_III_1004 {

    public int longestOnes(int[] nums, int k) {
//        return maxConsecutiveOnesBruteForce(nums, k);
        return maxConsecutiveOnesBetter(nums, k);
    }

    /**
     * maxConsecutiveOnesBruteForce: Finds the maximum consecutive 1s by checking all subarrays.
     *
     * <b>Intuition:</b>
     * For every subarray, count the number of zeros. If the zeros are less than or equal to `k`,
     * calculate the length of the subarray.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Iterate over all possible subarrays using two loops.
     * 2. Count the number of zeros in the subarray.
     * 3. If the zero count <= `k`, calculate the length of the subarray.
     * 4. Track the maximum length encountered.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n^2) \) - Nested loops over the array.
     *
     * <b>Space Complexity:</b>
     * \( O(1) \) - No additional space used.
     *
     * @param nums Input binary array.
     * @param k Maximum number of flips allowed.
     * @return Maximum consecutive 1s possible.
     */
    public int maxConsecutiveOnesBruteForce(int[] nums, int k) {
        int maxLength = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int zeroCount = 0;

            for (int j = i; j < n; j++) {
                if (nums[j] == 0) {
                    zeroCount++;
                }
                if (zeroCount > k) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        return maxLength;
    }

    /**
     * maxConsecutiveOnesBetter: Optimizes by limiting window checks to valid subarrays.
     *
     * <b>Intuition:</b>
     * Use a sliding window-like approach but explicitly count zeros in a single pass. Shrink
     * the window only when zero count exceeds `k`.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Use two pointers `start` and `end` for the current subarray.
     * 2. Increment `end` and count zeros in the subarray.
     * 3. If zeros > `k`, move `start` forward to reduce zeros.
     * 4. Track the maximum subarray length.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n) \) - Each pointer traverses the array at most once.
     *
     * <b>Space Complexity:</b>
     * \( O(1) \) - Constant extra space.
     *
     * @param nums Input binary array.
     * @param k Maximum number of flips allowed.
     * @return Maximum consecutive 1s possible.
     */
    public int maxConsecutiveOnesBetter(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;
        for (int right = 0; right < nums.length; right++) {
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

}
