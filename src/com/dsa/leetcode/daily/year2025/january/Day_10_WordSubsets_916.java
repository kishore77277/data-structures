package com.dsa.leetcode.daily.year2025.january;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/word-subsets/description/?envType=daily-question&envId=2025-01-10
public class Day_10_WordSubsets_916 {

    public List<String> wordSubsets(String[] words1, String[] words2) {
//        return bruteForceWordSubsets(words1, words2);
        return betterWordSubsets(words1, words2);
    }

    /**
     * bruteForceWordSubsets: Finds universal words using a brute-force approach.
     *
     * <b>Intuition:</b>
     * Check for each word in `words1` if it satisfies the conditions for all words in `words2`.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Iterate over each word `a` in `words1`.
     * 2. For each word in `words2`, check if all characters in `b` are present in `a` with the required frequency.
     * 3. If all words in `words2` are subsets of `a`, add `a` to the result list.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n * m * k), where:
     * - n = length of `words1`
     * - m = length of `words2`
     * - k = average length of words in `words2`
     *
     * <b>Space Complexity:</b>
     * O(1) - No extra data structures used.
     *
     * @param words1 Array of candidate universal strings.
     * @param words2 Array of required subset strings.
     * @return List of universal strings.
     */
    public List<String> bruteForceWordSubsets(String[] words1, String[] words2) {
        List<String> result = new ArrayList<>();
        for (String word : words1) {
            boolean isUniversal = true;
            for (String subset : words2) {
                if (!isSubset(word, subset)) {
                    isUniversal = false;
                    break;
                }
            }
            if (isUniversal) result.add(word);
        }
        return result;
    }

    private boolean isSubset(String a, String b) {
        int[] countA = new int[26];
        int[] countB = new int[26];
        for (char c : a.toCharArray()) countA[c - 'a']++;
        for (char c : b.toCharArray()) countB[c - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (countB[i] > countA[i]) return false;
        }
        return true;
    }

    /**
     * betterWordSubsets: Optimized by precomputing requirements for `words2`.
     *
     * <b>Intuition:</b>
     * Instead of checking each word in `words2` against `words1`, merge requirements of all words in `words2`.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Compute the maximum frequency of each character across all words in `words2`.
     * 2. For each word in `words1`, check if it satisfies the combined character frequency.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n * k + m * k), where:
     * - n = length of `words1`
     * - m = length of `words2`
     * - k = average word length
     *
     * <b>Space Complexity:</b>
     * O(1) - Using only fixed-size arrays.
     *
     * @param words1 Array of candidate universal strings.
     * @param words2 Array of required subset strings.
     * @return List of universal strings.
     */
    public List<String> betterWordSubsets(String[] words1, String[] words2) {
        int[] maxFreq = new int[26];
        for (String word : words2) {
            int[] count = getCharFrequency(word);
            for (int i = 0; i < 26; i++) {
                maxFreq[i] = Math.max(maxFreq[i], count[i]);
            }
        }
        List<String> result = new ArrayList<>();
        for (String word : words1) {
            int[] count = getCharFrequency(word);
            if (isUniversal(count, maxFreq)) {
                result.add(word);
            }
        }
        return result;
    }

    private int[] getCharFrequency(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) count[c - 'a']++;
        return count;
    }

    private boolean isUniversal(int[] countA, int[] maxFreq) {
        for (int i = 0; i < 26; i++) {
            if (countA[i] < maxFreq[i]) return false;
        }
        return true;
    }


}
