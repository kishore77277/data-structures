package com.dsa.leetcode.daily.year2025.january;

// https://leetcode.com/problems/maximum-score-after-splitting-a-string/description/?envType=daily-question&envId=2025-01-01
public class Day_01_MaximumScoreAfterSplittingAString_1422 {

    public int maxScore(String s) {
//        return bruteForce(s);
//        return usingTwoPassBetterApproach(s);
        return optimalUsingOnePass(s);
    }


    /**
     * Brute force approach to calculate the maximum score by splitting a binary string.
     * The score is the sum of the number of '0's in the left substring and the number of '1's in the right substring.
     *
     * Steps:
     * 1. For every possible split index, count the number of '0's in the left substring.
     * 2. Count the number of '1's in the right substring.
     * 3. Compute the score for each split and keep track of the maximum score.
     *
     * Time Complexity: O(n^2), where n is the length of the string.
     * Space Complexity: O(1), as no additional space is used.
     *
     * @param s the input binary string.
     * @return the maximum score for any valid split.
     */
    public int bruteForce(String s) {
        int n = s.length();
        int maxScore = 0;
        // Traverse till n - 1, because we are looking for non-empty substrings.
        for (int i = 0; i < n - 1; i++) {
            int zeroes = 0;
            // Count '0's in the left substring.
            for (int left = 0; left <= i; left++) {
                if (s.charAt(left) == '0') {
                    zeroes++;
                }
            }
            int ones = 0;
            // Count '1's in the right substring.
            for (int right = i + 1; right < n; right++) {
                if (s.charAt(right) == '1') {
                    ones++;
                }
            }
            // Update maximum score.
            maxScore = Math.max(maxScore, zeroes + ones);
        }
        return maxScore;
    }

    /**
     * Optimized two-pass approach to calculate the maximum score by splitting a binary string.
     * The score is the sum of the number of '0's in the left substring and the number of '1's in the right substring.
     *
     * Steps:
     * 1. Count the total number of '1's in the string in a first pass.
     * 2. Traverse the string again, maintaining a running count of '0's in the left substring.
     * 3. For each split, adjust the count of '1's in the right substring and calculate the score.
     * 4. Keep track of the maximum score.
     *
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(1), as no additional space is used.
     *
     * @param s the input binary string.
     * @return the maximum score for any valid split.
     */
    public int usingTwoPassBetterApproach(String s) {
        int n = s.length();
        int ones = 0;
        // Count total number of '1's in the string.
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones++;
            }
        }
        int zeroes = 0;
        int maxScore = 0;
        // Traverse the string to compute scores for each split.
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == '1') {
                ones--;
            } else {
                zeroes++;
            }
            // Update maximum score.
            maxScore = Math.max(maxScore, ones + zeroes);
        }
        return maxScore;
    }

    /**
     * Optimal one-pass approach to calculate the maximum score by splitting a binary string.
     * The score is the sum of the number of '0's in the left substring and the number of '1's in the right substring.
     *
     * Steps:
     * 1. Maintain running counts of '0's and '1's as the string is traversed.
     * 2. Calculate a "best possible" score by considering the difference between '0's and '1's.
     * 3. Adjust the score based on the remaining '1's in the string.
     *
     *  Z_L + 0_R
     *  0_T = 0_L + 0_R
     *  Z_L + (0_T - 0_L)
     * Time Complexity: O(n), where n is the length of the string.
     * Space Complexity: O(1), as no additional space is used.
     *
     * @param s the input binary string.
     * @return the maximum score for any valid split.
     */
    public int optimalUsingOnePass(String s) {
        int n = s.length();
        int maxScore = 0;
        int bestPossible = Integer.MIN_VALUE;
        int ones = 0, zeroes = 0;

        // Traverse the string to calculate the score dynamically.
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                zeroes++;
            } else {
                ones++;
            }
            // Update the best possible score using the difference.
            bestPossible = Math.max(bestPossible, zeroes - ones);
        }
        // Account for the last character if it's a '1'.
        if (s.charAt(n - 1) == '1') {
            ones++;
        }
        return bestPossible + ones;
    }

}
