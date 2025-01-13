package com.dsa.leetcode.blind75.ArraysAndStrings;

// https://leetcode.com/problems/increasing-triplet-subsequence/description/?envType=study-plan-v2&envId=leetcode-75
public class IncreasingTripletSubsequence_334 {

    public boolean increasingTriplet(int[] nums) {
//        return bruteForce(nums);
//        return betterWithDP(nums);
        return optimal(nums);
    }

    /**
     * bruteForce: Check all possible triplets to find an increasing subsequence.
     *
     * <b>Intuition:</b>
     * Use three nested loops to iterate over all possible triplets `(i, j, k)` and check the condition.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Loop through each index `i` for the first element.
     * 2. For each `i`, loop through each index `j` for the second element.
     * 3. For each `j`, loop through each index `k` for the third element.
     * 4. Check if `nums[i] < nums[j] < nums[k]`.
     * 5. Return true if a valid triplet is found; otherwise, return false.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n³) - Three nested loops for all possible triplets.
     *
     * <b>Space Complexity:</b>
     * O(1) - No additional space used.
     *
     * @param nums Input array.
     * @return true if an increasing triplet exists, false otherwise.
     */
    public boolean bruteForce(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[j] < nums[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * betterWithDP: Use auxiliary arrays to track left minimum and right maximum.
     *
     * <b>Intuition:</b>
     * Use `leftMin` to track the smallest value to the left of an index and `rightMax` to track the largest value to the right.
     * Check if there exists an element `nums[i]` such that `leftMin[i - 1] < nums[i] < rightMax[i + 1]`.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Create two arrays:
     *    - `leftMin[i]` stores the minimum value from the start to index `i`.
     *    - `rightMax[i]` stores the maximum value from index `i` to the end.
     * 2. Populate `leftMin` by iterating from left to right.
     * 3. Populate `rightMax` by iterating from right to left.
     * 4. Iterate through the array, checking if `leftMin[i - 1] < nums[i] < rightMax[i + 1]`.
     * 5. Return true if such a condition exists; otherwise, return false.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n) - Two passes to populate `leftMin` and `rightMax`, and one pass to check the condition.
     *
     * <b>Space Complexity:</b>
     * O(n) - Extra space for `leftMin` and `rightMax`.
     *
     * @param nums Input array.
     * @return true if an increasing triplet exists, false otherwise.
     */
    public boolean betterWithDP(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int[] leftMin = new int[n];
        int[] rightMax = new int[n];

        // Populate leftMin
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }

        // Populate rightMax
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        // Check for triplet
        for (int i = 1; i < n - 1; i++) {
            if (leftMin[i - 1] < nums[i] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * optimal: Use two pointers to track the smallest and second smallest elements.
     *
     * <b>Intuition:</b>
     * Maintain two variables `first` and `second`:
     * - `first` stores the smallest element encountered so far.
     * - `second` stores the smallest element greater than `first`.
     * If a number greater than `second` is found, an increasing triplet exists.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Initialize `first` and `second` to `Integer.MAX_VALUE`.
     * 2. Traverse the array:
     *    - If `nums[i] <= first`, update `first`.
     *    - Else if `nums[i] <= second`, update `second`.
     *    - Else, return true (a triplet exists).
     * 3. If the loop ends, return false.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n) - Single pass through the array.
     *
     * <b>Space Complexity:</b>
     * O(1) - No additional space used.
     *
     * @param nums Input array.
     * @return true if an increasing triplet exists, false otherwise.
     */
    public boolean optimal(int[] nums) {
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= firstMin) {
                firstMin = num;
            } else if (num <= secondMin) {
                secondMin = num;
            } else {
                return true; // Found a number greater than both firstMin and secondMin
            }
        }
        return false;
    }


}
