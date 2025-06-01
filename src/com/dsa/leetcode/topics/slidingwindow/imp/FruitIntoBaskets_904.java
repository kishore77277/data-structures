package com.dsa.leetcode.topics.slidingwindow.imp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <h2>Fruit Into Baskets (LeetCode 904)</h2>
 *
 * <p>This class solves the problem of collecting fruits using at most two baskets.
 * Each basket can only hold one type of fruit, and fruits are picked consecutively from a row of trees represented by an integer array.</p>
 *
 * <p><b>Problem Statement:</b></p>
 * <p>Given an array <code>fruits[]</code> where each element represents a type of fruit growing on a tree in a line,
 * return the length of the longest subarray that contains at most two different types of fruits.</p>
 *
 * <p><b>Examples:</b></p>
 * <pre>
 * Input:  [1, 2, 1]
 * Output: 3  // Pick all fruits: [1, 2, 1]
 *
 * Input:  [0, 1, 2, 2]
 * Output: 3  // Pick [1, 2, 2]
 *
 * Input:  [1, 2, 3, 2, 2]
 * Output: 4  // Pick [2, 3, 2, 2]
 *
 * Input:  [3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4]
 * Output: 5  // Pick [1, 2, 1, 1, 2]
 * </pre>
 *
 * <p><b>Edge Cases:</b></p>
 * <ul>
 *   <li>Empty array should return 0.</li>
 *   <li>All elements the same: return full array length.</li>
 *   <li>All unique elements: return 2 (at most two baskets allowed).</li>
 * </ul>
 *
 * <p><b>Link:</b> <a href="https://leetcode.com/problems/fruit-into-baskets/">LeetCode 904</a></p>
 */
public class FruitIntoBaskets_904 {

    /**
     * <p><b>Brute Force Approach</b></p>
     *
     * <p>Finds the longest subarray with at most two distinct fruit types by checking all possible subarrays.</p>
     *
     * <p><b>Intuition:</b> Try every possible starting point and keep collecting fruits until more than two types are picked.</p>
     *
     * <p><b>Algorithm:</b></p>
     * <ul>
     *   <li>Use a set to track distinct fruit types between every pair of indices (i, j).</li>
     *   <li>If the set size exceeds 2, break the inner loop.</li>
     *   <li>Track and update the maximum length found.</li>
     * </ul>
     *
     * @param fruits An array representing fruits on trees in a row.
     * @return The maximum number of fruits that can be collected using two baskets.
     *
     * <p><b>Time Complexity:</b> O(n²) — two nested loops over the array.<br>
     * <b>Space Complexity:</b> O(1 to 2) — max 3 elements in the set at any time.</p>
     */
    public int bruteForce(int[] fruits) {
        int n = fruits.length;
        if (n == 0) return 0; // Edge case: empty array
        int maxFruits = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> basket = new HashSet<>();
            for (int j = i; j < n; j++) {
                basket.add(fruits[j]);
                if (basket.size() > 2) {
                    break;
                }
                maxFruits = Math.max(maxFruits, j - i + 1);
            }
        }
        return maxFruits;
    }

    /**
     * <p><b>Better Approach Using Sliding Window</b></p>
     *
     * <p>Uses a dynamic window and hash map to track fruit counts while maintaining only two types.</p>
     *
     * <p><b>Intuition:</b> Slide a window to the right as long as we have at most 2 fruit types.
     * If we exceed, move the left pointer to shrink the window until valid again.</p>
     *
     * <p><b>Algorithm:</b></p>
     * <ul>
     *   <li>Use a map to track fruit types and their counts.</li>
     *   <li>Expand the window by moving <code>right</code> and updating the map.</li>
     *   <li>If the map exceeds 2 types, shrink from <code>left</code> while decrementing counts and removing zero-count types.</li>
     *   <li>Track the max window size.</li>
     * </ul>
     *
     * @param fruits An array where each element represents a type of fruit.
     * @return Length of the longest subarray with at most two distinct fruit types.
     *
     * <p><b>Time Complexity:</b> O(n) — each fruit is added and removed at most once.<br>
     * <b>Space Complexity:</b> O(1 to 3) — at most 3 fruit types tracked at any time.</p>
     */
    public int betterUsingSlidingWindow(int[] fruits) {
        int n = fruits.length;
        int maxFruits = 0;
        int left = 0;
        Map<Integer, Integer> basket = new HashMap<>();

        for (int right = 0; right < n; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }

    /**
     * <p><b>Optimal Approach Using Sliding Window</b></p>
     *
     * <p>This method is a refined version of the previous sliding window, with an early check optimization.</p>
     *
     * <p><b>Intuition:</b> Use a map to store counts, shrink the window when the basket exceeds 2 fruit types.
     * Only update maxFruits when the basket is valid.</p>
     *
     * <p><b>Algorithm:</b></p>
     * <ul>
     *   <li>Iterate using a right pointer and add the fruit to the map.</li>
     *   <li>When basket size exceeds 2, shrink window from the left by decrementing count and removing if count is zero.</li>
     *   <li>Update the max size when the window becomes valid again.</li>
     * </ul>
     *
     * @param fruits An array representing types of fruits on each tree.
     * @return Maximum number of fruits that can be collected with at most two types.
     *
     * <p><b>Time Complexity:</b> O(n) — linear traversal with efficient shrinking.<br>
     * <b>Space Complexity:</b> O(1 to 3) — map holds at most 3 entries.</p>
     *
     * <p><b>Note:</b> This version minimizes the number of max checks by ensuring they happen only when basket is valid.</p>
     */
    public int optimalUsingSlidingWindow(int[] fruits) {
        int n = fruits.length;
        int maxFruits = 0;
        int left = 0;
        Map<Integer, Integer> basket = new HashMap<>();

        for (int right = 0; right < n; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            if (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            if (basket.size() <= 2) {
                maxFruits = Math.max(maxFruits, right - left + 1);
            }
        }

        return maxFruits;
    }
}
