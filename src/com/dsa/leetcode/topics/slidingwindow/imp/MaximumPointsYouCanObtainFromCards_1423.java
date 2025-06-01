package com.dsa.leetcode.topics.slidingwindow.imp;

/**
 * ✅ Problem: 1423. Maximum Points You Can Obtain from Cards
 *
 * 🔗 <a href="https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/">LeetCode 1423</a>
 *
 * <p><b>🎯 Objective:</b><br>
 * You are given an integer array {@code cardPoints}, where each element represents the points on a card.
 * You can pick exactly {@code k} cards from either the start or end of the array.
 * Your task is to return the <b>maximum score</b> you can get by picking exactly {@code k} cards.
 *
 * <p><b>🧪 Examples:</b>
 * <ul>
 *   <li>Input: cardPoints = [1,2,3,4,5,6,1], k = 3 → Output: 12<br>
 *       Explanation: Pick cards 6, 1, and 5 from the end.</li>
 *   <li>Input: cardPoints = [2,2,2], k = 2 → Output: 4</li>
 *   <li>Input: cardPoints = [9,7,7,9,7,7,9], k = 7 → Output: 55<br>
 *       Explanation: Take all the cards.</li>
 * </ul>
 *
 * <p><b>⚠️ Edge Cases:</b>
 * <ul>
 *   <li>{@code k == cardPoints.length} → Must take all cards</li>
 *   <li>{@code k == 1} → Only one card to choose from either end</li>
 *   <li>All values are the same → Any valid selection gives same score</li>
 * </ul>
 *
 * <p><b>💡 Insight:</b><br>
 * Try all combinations where you take {@code i} cards from the front and {@code k - i} cards from the back.
 * A brute-force approach checks all such combinations. A sliding window optimization improves performance.
 */
public class MaximumPointsYouCanObtainFromCards_1423 {

    /**
     * 🚀 Entry-point method that returns the result using a brute-force approach.
     *
     * @param cardPoints Array of integers representing the points on each card.
     * @param k Number of cards to pick in total from either end.
     * @return Maximum points obtainable by picking exactly {@code k} cards.
     */
    public int maxScore(int[] cardPoints, int k) {
        return maxScoreBruteForce(cardPoints, k);
    }

    /**
     * 🧮 Brute-Force Approach
     *
     * <p><b>Summary:</b><br>
     * Computes the maximum score by trying all possible combinations of picking cards from the start and end.
     *
     * <p><b>📚 Intuition:</b><br>
     * Think of the array as a line of dishes — you can only take from either end. Try every combination
     * of taking {@code i} dishes from the front and {@code k - i} from the end. Find which combination gives the highest total.
     *
     * <p><b>🧠 Algorithm Steps:</b>
     * <ol>
     *   <li>Loop {@code i} from 0 to {@code k}.</li>
     *   <li>For each {@code i}, calculate:
     *     <ul>
     *       <li>Sum of first {@code i} cards from the start.</li>
     *       <li>Sum of last {@code k - i} cards from the end.</li>
     *     </ul>
     *   </li>
     *   <li>Keep track of the maximum sum encountered.</li>
     * </ol>
     *
     * @param cardPoints Array of card values.
     * @param k Number of cards to pick.
     * @return Maximum score possible by picking k cards from either end.
     *
     * <p><b>⏱ Time Complexity:</b> O(k²) <br>
     * For each of k combinations, we loop up to k elements to compute sum.
     *
     * <p><b>🧠 Space Complexity:</b> O(1) <br>
     * No additional space is used beyond a few variables.
     *
     * <p><b>🔍 Edge Cases:</b>
     * <ul>
     *   <li>{@code k == 0} → Return 0</li>
     *   <li>{@code k == cardPoints.length} → Return sum of the entire array</li>
     * </ul>
     */
    private int maxScoreBruteForce(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int maxScore = 0;

        for (int i = 0; i <= k; i++) {
            int leftSum = 0;
            int rightSum = 0;

            // Take i cards from the front
            for (int j = 0; j < i; j++) {
                leftSum += cardPoints[j];
            }

            // Take (k - i) cards from the end
            for (int j = 0; j < k - i; j++) {
                rightSum += cardPoints[n - 1 - j];
            }

            maxScore = Math.max(maxScore, leftSum + rightSum);
        }

        return maxScore;
    }

    /**
     * ⚡ Optimized Approach Using Sliding Window
     *
     * <p><b>Summary:</b><br>
     * Starts by taking the first {@code k} cards and computes their sum.
     * Then, gradually replaces cards from the front with cards from the end, one at a time, while tracking the max sum.
     *
     * <p><b>📚 Intuition:</b><br>
     * Imagine holding the first {@code k} cards in your hands.
     * Now, slide one card at a time from the front to the back. At each step, check if the total score is better.
     *
     * <p><b>🧠 Algorithm Steps:</b>
     * <ol>
     *   <li>Calculate the sum of the first {@code k} cards and store as {@code currentSum}.</li>
     *   <li>Initialize {@code maxSum = currentSum}.</li>
     *   <li>Iteratively:
     *     <ul>
     *       <li>Remove one card from the end of the current left window.</li>
     *       <li>Add one card from the right end of the array.</li>
     *       <li>Update {@code maxSum} accordingly.</li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * @param cardPoints Array of integers representing the point values on cards.
     * @param k Total number of cards you must pick from either end.
     * @return Maximum achievable score.
     *
     * <p><b>⏱ Time Complexity:</b> O(k) <br>
     *   Only two passes of size k — one for initial sum, one for window shifts.
     *
     * <p><b>🧠 Space Complexity:</b> O(1) <br>
     *   Constant space used for a few tracking variables.
     *
     * <p><b>🔍 Edge Cases:</b>
     * <ul>
     *   <li>{@code k == cardPoints.length} → Return full sum of array</li>
     *   <li>{@code k == 0} → Return 0</li>
     * </ul>
     */
    public int optimalUsingSlidingWindow(int[] cardPoints, int k) {
        int n = cardPoints.length;

        // Step 1: Take first k cards from the front
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += cardPoints[i];
        }

        int maxSum = currentSum;
        int left = k - 1;
        int right = n - 1;
        int leftSum = currentSum;
        int rightSum = 0;

        // Step 2: Slide window from left to right
        for (int i = 0; i < k; i++) {
            leftSum -= cardPoints[left--];
            rightSum += cardPoints[right--];
            maxSum = Math.max(maxSum, leftSum + rightSum);
        }

        return maxSum;
    }
}
