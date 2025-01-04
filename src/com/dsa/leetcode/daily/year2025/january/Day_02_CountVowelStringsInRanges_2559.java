package com.dsa.leetcode.daily.year2025.january;

import java.util.Set;

// https://leetcode.com/problems/count-vowel-strings-in-ranges/description/?envType=daily-question&envId=2025-01-02
public class Day_02_CountVowelStringsInRanges_2559 {

    private final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    public int[] vowelStrings(String[] words, int[][] queries) {
//        return bruteForce(words, queries);
        return optimalUsingPrefixSum(words, queries);
    }

    /**
     * Brute force approach to count the words that start and end with vowels for each query range.
     *
     * <p>
     * Problem Description:
     * - Given an array of words and a set of queries, count how many words start and end with vowels within each query range.
     * </p>
     *
     * <p>
     * Intuition:
     * - Iterate over the query ranges directly and count the number of words satisfying the condition.
     * </p>
     *
     * <p>
     * Approach:
     * - Iterate over all queries.
     * - For each query, loop through the specified range of words.
     * - Use a helper function to check if a word starts and ends with vowels.
     * - Count such words and store the result for each query.
     * </p>
     *
     * @param words   An array of lowercase strings.
     * @param queries A 2D array where each query is of the form [start, end].
     * @return An integer array where each element corresponds to the count of such words for each query.
     *
     * <p>
     * Complexity:
     * - Time Complexity: O(q * r) where q is the number of queries and r is the average range size.
     * - Space Complexity: O(1) as no additional data structures are used.
     * </p>
     */
    public int[] bruteForce(String[] words, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int count = 0;
            // Check each word in the range [queries[i][0], queries[i][1]].
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                if (isWordStartingAndEndingWithVowel(words[j])) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    /**
     * Checks if a word starts and ends with vowels.
     *
     * @param word The input word to check.
     * @return true if the word starts and ends with vowels, false otherwise.
     */
    public boolean isWordStartingAndEndingWithVowel(String word) {
        int n = word.length();
        if (VOWELS.contains(word.charAt(0)) && VOWELS.contains(word.charAt(n - 1))) {
            return true;
        }
        return false;
    }

    /**
     * Optimal approach to count the words that start and end with vowels for each query range using prefix sum.
     *
     * <p>
     * Problem Description:
     * - Given an array of words and a set of queries, count how many words start and end with vowels within each query range.
     * </p>
     *
     * <p>
     * Intuition:
     * - Use a prefix sum array to precompute the count of valid words up to each index.
     * - Use the prefix sum to calculate the result for any query in constant time.
     * </p>
     *
     * <p>
     * Approach:
     * - Precompute a prefix sum array:
     *     - Traverse the words array and maintain a running count of words starting and ending with vowels.
     *     - Store this count in the prefix sum array.
     * - For each query:
     *     - Use the prefix array to calculate the count within the query range in constant time.
     * </p>
     *
     * @param words   An array of lowercase strings.
     * @param queries A 2D array where each query is of the form [start, end].
     * @return An integer array where each element corresponds to the count of such words for each query.
     *
     * <p>
     * Complexity:
     * - Time Complexity: O(n + q) where n is the length of the words array and q is the number of queries.
     * - Space Complexity: O(n) for the prefix sum array.
     * </p>
     */
    public int[] optimalUsingPrefixSum(String[] words, int[][] queries) {
        int[] result = new int[queries.length];
        int[] prefixCount = new int[words.length];
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if (isWordStartingAndEndingWithVowel(words[i])) {
                count++;
            }
            prefixCount[i] = count;
        }
        for (int i = 0; i < queries.length; i++) {
            int startIndex = queries[i][0];
            int endIndex = queries[i][1];
            result[i] = prefixCount[endIndex] - ((startIndex == 0) ? 0 : prefixCount[startIndex]);
        }
        return result;
    }
}
