package com.dsa.leetcode.topics.slidingwindow;

// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/
public class MaximumPointsYouCanObtainFromCards_1423 {

    public int maxScore(int[] cardPoints, int k) {
        return maxScoreBruteForce(cardPoints, k);
    }


    /**
     * [maxScoreBruteForce]: Finds the maximum score by brute force calculation.
     *
     * <b>Intuition:</b>
     * This approach computes the score for all possible splits of `k` cards taken
     * from the start and end of the array, tracking the maximum sum.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Loop through all possible splits of `k` cards between the start and the end.
     * 2. For each split:
     *    - Calculate the sum of the left part (start of the array).
     *    - Calculate the sum of the right part (end of the array).
     *    - Update the maximum score if the current combination's sum is higher.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(k^2) - For each split, we iterate up to `k` elements to calculate sums.
     *
     * <b>Space Complexity:</b>
     * O(1) - No extra space is used apart from a few variables.
     *
     * @param cardPoints Array of integers representing card point values.
     * @param k Number of cards to select.
     * @return Maximum score obtainable from `k` cards.
     */
    public int maxScoreBruteForce(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int maxScore = 0;

        for (int i = 0; i <= k; i++) {
            int leftSum = 0;
            int rightSum = 0;

            // Sum of first `i` cards from the left
            for (int j = 0; j < i; j++) {
                leftSum += cardPoints[j];
            }

            // Sum of last `k-i` cards from the right
            for (int j = 0; j < k - i; j++) {
                rightSum += cardPoints[n - 1 - j];
            }

            maxScore = Math.max(maxScore, leftSum + rightSum);
        }

        return maxScore;
    }

    /**
     * [maxScoreTwoPointers]: Optimal solution using two pointers.
     *
     * <b>Intuition:</b>
     * Use two pointers to compute the maximum score by iterating over possible splits
     * of `k` cards from the start and end of the array. This avoids recalculating sums repeatedly.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Start with the sum of the first `k` cards from the start of the array.
     * 2. Use a pointer to shift cards from the end of the array into the sum while removing cards from the start.
     * 3. Track the maximum score during this process.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(k) - Only `k` iterations are performed to adjust the sum.
     *
     * <b>Space Complexity:</b>
     * O(1) - Only a few variables are used for calculation.
     *
     * @param cardPoints Array of integers representing card point values.
     * @param k Number of cards to select.
     * @return Maximum score obtainable from `k` cards.
     */
    public int optimalUsingSlidingWindow(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int currentSum = 0;

        // Calculate the sum of the first `k` cards.
        for (int i = 0; i < k; i++) {
            currentSum += cardPoints[i];
        }

        int maxScore = currentSum;

        // Shift cards from the end into the sum while removing from the start.
        for (int i = 0; i < k; i++) {
            currentSum = currentSum - cardPoints[k - 1 - i] + cardPoints[n - 1 - i];
            maxScore = Math.max(maxScore, currentSum);
        }

        return maxScore;
    }

}
