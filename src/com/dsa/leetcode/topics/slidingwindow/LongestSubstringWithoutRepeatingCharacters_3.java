package com.dsa.leetcode.topics.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubstringWithoutRepeatingCharacters_3 {

    public int lengthOfLongestSubstring(String s) {
        return lengthOfLongestSubstringBruteForce(s);
    }

    /**
     * [lengthOfLongestSubstringBruteForce]: Finds the length of the longest substring without repeating characters using brute force.
     *
     * <b>Intuition:</b>
     * Check all possible substrings, and for each substring, verify if it has unique characters.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Iterate over all possible starting points of substrings.
     * 2. For each starting point, iterate to find substrings and check if characters are unique.
     * 3. Update the maximum length if a valid substring is found.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n^3) - Nested loops to generate substrings and a set to check uniqueness.
     *
     * <b>Space Complexity:</b>
     * O(n) - Space for the set to store characters.
     *
     * @param s Input string.
     * @return Length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstringBruteForce(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> seen = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (seen.contains(s.charAt(i))) {
                return false;
            }
            seen.add(s.charAt(i));
        }
        return true;
    }

    /**
     * [lengthOfLongestSubstringSlidingWindow]: Finds the length of the longest substring without repeating characters using a sliding window.
     *
     * <b>Intuition:</b>
     * Use a sliding window to efficiently check for substrings with unique characters.
     * Expand the window by adding characters, and shrink it when a duplicate is found.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Use a set to store characters in the current window.
     * 2. Expand the window by adding characters from the string.
     * 3. If a duplicate is found, shrink the window by removing characters from the left until it becomes unique again.
     * 4. Track the maximum window size during this process.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(2n) = O(n) - Each character is added and removed at most once.
     *
     * <b>Space Complexity:</b>
     * O(n) - Space for the set to store characters.
     *
     * @param s Input string.
     * @return Length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstringSlidingWindow(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * [lengthOfLongestSubstringOptimized]: Finds the length of the longest substring without repeating characters using a sliding window and a hash map.
     *
     * <b>Intuition:</b>
     * Instead of shrinking the window character by character, directly jump the left pointer to the next position
     * after the last occurrence of the duplicate character.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Use a map to store the last index of each character.
     * 2. Expand the window by iterating through the string.
     * 3. If a character is repeated, move the left pointer to the position after the last occurrence of the duplicate.
     * 4. Track the maximum window size during this process.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n) - Each character is processed once.
     *
     * <b>Space Complexity:</b>
     * O(n) - Space for the map to store characters and their indices.
     *
     * @param s Input string.
     * @return Length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstringOptimized(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0, left = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }
            map.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }



}
