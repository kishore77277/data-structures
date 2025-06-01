package com.dsa.leetcode.topics.slidingwindow.imp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <b>Longest Substring Without Repeating Characters</b>
 * <p>
 * <b>Problem Statement:</b><br>
 * Given a string <code>s</code>, find the length of the longest substring without repeating characters.
 * A substring is a contiguous sequence of characters within the string. The goal is to find the maximum length of such a substring where every character is unique.
 * <p>
 * <b>Tricky or Edge-Case Examples:</b><br>
 * <ul>
 *   <li><b>Input:</b> "abcabcbb" <b>Output:</b> 3 ("abc")</li>
 *   <li><b>Input:</b> "bbbbb" <b>Output:</b> 1 ("b")</li>
 *   <li><b>Input:</b> "pwwkew" <b>Output:</b> 3 ("wke")</li>
 *   <li><b>Input:</b> "" <b>Output:</b> 0 (empty string)</li>
 *   <li><b>Input:</b> "dvdf" <b>Output:</b> 3 ("vdf")</li>
 *   <li><b>Input:</b> "abba" <b>Output:</b> 2 ("ab" or "ba")</li>
 * </ul>
 * <p>
 * <b>Original Problem Link:</b><br>
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">LeetCode 3: Longest Substring Without Repeating Characters</a>
 * <p>
 * <b>Notes:</b> This class provides three different approaches: brute force, sliding window with HashSet, and optimal sliding window with HashMap.
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {

    /**
     * Finds the length of the longest substring without repeating characters using a brute-force approach.
     * <p>
     * <b>Intuition:</b> Try every possible substring and check if all characters are unique. This is like checking every possible group of letters and seeing if any letter repeats.
     * <p>
     * <b>Step-by-step:</b>
     * <ol>
     *   <li>Start from each character in the string.</li>
     *   <li>For each starting point, expand the substring one character at a time.</li>
     *   <li>Check if the current substring has all unique characters using a helper method.</li>
     *   <li>If a duplicate is found, stop expanding and move to the next starting point.</li>
     *   <li>Keep track of the maximum length found.</li>
     * </ol>
     *
     * @param s The input string to search.
     * @return The length of the longest substring without repeating characters.
     * <br><b>Time Complexity:</b> O(n^3) — For each starting index, we check all substrings and for each substring, we check uniqueness (O(n^2) substrings, O(n) for uniqueness check).
     * <br><b>Space Complexity:</b> O(1) — The helper uses a fixed-size array for ASCII characters.
     * <p>
     * <b>Edge Cases:</b> Handles empty strings (returns 0), strings with all unique or all identical characters.
     */
    public int bruteForce(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                } else {
                    break; // No need to check further if we found a duplicate
                }
            }
        }
        return maxLength;
    }

    /**
     * Checks if all characters in the substring s[startIndex..endIndex] are unique.
     * <p>
     * <b>Intuition:</b> Use a boolean array to mark if a character has been seen before in the current substring.
     * <p>
     * <b>Step-by-step:</b>
     * <ol>
     *   <li>Iterate from startIndex to endIndex.</li>
     *   <li>For each character, check if it has already been seen.</li>
     *   <li>If yes, return false. If not, mark it as seen.</li>
     *   <li>If loop completes, all characters are unique.</li>
     * </ol>
     *
     * @param s The input string.
     * @param startIndex The starting index of the substring (inclusive).
     * @param endIndex The ending index of the substring (inclusive).
     * @return True if all characters are unique, false otherwise.
     * <br><b>Time Complexity:</b> O(n) — n is the length of the substring (endIndex - startIndex + 1).
     * <br><b>Space Complexity:</b> O(1) — Uses a fixed-size array for ASCII characters.
     * <p>
     * <b>Edge Cases:</b> Assumes input contains only ASCII characters.
     */
    private boolean allUnique(String s, int startIndex, int endIndex) {
        boolean[] seen = new boolean[256]; // Assuming ASCII characters
        for (int i = startIndex; i <= endIndex; i++) {
            char c = s.charAt(i);
            if (seen[c]) {
                return false; // Found a duplicate character
            }
            seen[c] = true; // Mark this character as seen
        }
        return true; // All characters are unique
    }

    /**
     * Finds the length of the longest substring without repeating characters using a sliding window and HashSet.
     * <p>
     * <b>Intuition:</b> Use two pointers to create a window that expands to the right. If a duplicate is found, shrink the window from the left until the duplicate is removed. This keeps the window always unique.
     * <p>
     * <b>Step-by-step:</b>
     * <ol>
     *   <li>Initialize a set to store unique characters in the current window.</li>
     *   <li>Expand the window by moving the right pointer.</li>
     *   <li>If a duplicate is found, remove characters from the left until the duplicate is gone.</li>
     *   <li>Update the maximum length after each expansion.</li>
     * </ol>
     *
     * @param s The input string to search.
     * @return The length of the longest substring without repeating characters.
     * <br><b>Time Complexity:</b> O(n) — Each character is added and removed from the set at most once.
     * <br><b>Space Complexity:</b> O(min(n, m)) — n is the string length, m is the character set size (e.g., 26 for lowercase, 128 for ASCII).
     * <p>
     * <b>Edge Cases:</b> Handles empty strings, strings with all unique or all identical characters.
     */
    public int betterUsingSlidingWindowAndHashSet(String s) {
        int maxLength = 0;
        int left = 0;
        Set<Character> seen = new HashSet<>();
        for (int right = 0; right < s.length(); right++) {
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    /**
     * Finds the length of the longest substring without repeating characters using an optimal sliding window and HashMap.
     * <p>
     * <b>Intuition:</b> Use a HashMap to remember the last index of each character. When a duplicate is found, jump the left pointer to the right of the last occurrence, skipping unnecessary checks.
     * <p>
     * <b>Step-by-step:</b>
     * <ol>
     *   <li>Initialize a map to store the last index of each character.</li>
     *   <li>Expand the window by moving the right pointer.</li>
     *   <li>If the character is already in the map, move the left pointer to one position after its last occurrence.</li>
     *   <li>Update the character's last index in the map.</li>
     *   <li>Update the maximum length after each expansion.</li>
     * </ol>
     *
     * @param s The input string to search.
     * @return The length of the longest substring without repeating characters.
     * <br><b>Time Complexity:</b> O(n) — Each character is visited at most once, and map operations are O(1) on average.
     * <br><b>Space Complexity:</b> O(min(n, m)) — n is the string length, m is the character set size.
     * <p>
     * <b>Edge Cases:</b> Handles empty strings, strings with all unique or all identical characters. Assumes input is within the character set size.
     */
    public int optimalUsingSlidingWindowAndHashMap(String s) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            if (charIndexMap.containsKey(s.charAt(right))) { // If the character is already seen
                left = Math.max(left, charIndexMap.get(s.charAt(right)) + 1); // Move left pointer to the right of the last occurrence
                // max(left, charIndexMap.get(s.charAt(right)) + 1);, because we want to ensure left is always ahead of the last occurrence
                // This ensures, we are not moving left pointer back, which can cause duplicates in the current window
            }
            charIndexMap.put(s.charAt(right), right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}

