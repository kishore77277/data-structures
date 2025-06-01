package com.dsa.leetcode.topics.slidingwindow.imp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode Problem #1358 - **Number of Substrings Containing All Three Characters**
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string `s` consisting only of characters `'a'`, `'b'`, and `'c'`, return the number of substrings that contain at least one occurrence of each character.
 *
 * <p>The substring must include **at least one `'a'`**, **at least one `'b'`**, and **at least one `'c'`**, though their order does not matter.
 *
 * <p><b>Examples:</b><br>
 * - Input: <code>"abcabc"</code> → Output: <code>10</code><br>
 * - Input: <code>"aaacb"</code> → Output: <code>3</code><br>
 * - Input: <code>"abc"</code> → Output: <code>1</code><br>
 * - Input: <code>"aaa"</code> → Output: <code>0</code> (no `'b'` or `'c'`)<br>
 *
 * <p><b>Tricky Edge Cases:</b><br>
 * - Strings with repeated characters like "aaabbbccc" — substrings must still contain all 3.
 * - Strings where one character is missing entirely → answer is `0`.
 * - Long strings with many repeating valid triples should be optimized to avoid brute-force.
 *
 * <p><b>Original Problem Link:</b><br>
 * <a href="https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/">LeetCode #1358</a>
 *
 * <p>This class provides four different strategies to solve the problem, progressing from brute-force to optimized sliding window techniques.
 *
 * @author
 */
public class NumberOfSubstringsContainingAllThreeCharacters_1358 {

    /**
     * Counts the number of substrings containing all three characters ('a', 'b', 'c') using brute-force.
     *
     * <p><b>Intuition:</b> Try all substrings and check each one to see if it contains 'a', 'b', and 'c'.
     * It's straightforward but slow for large strings.
     *
     * <p><b>Algorithm Steps:</b><br>
     * 1. Iterate over all possible substrings using two nested loops.<br>
     * 2. For each substring, check if it contains all three characters using a helper method.<br>
     * 3. If it does, increment the count.<br>
     *
     * @param s The input string containing only characters 'a', 'b', and 'c'.
     * @return The total number of valid substrings.
     *
     * <b>Time Complexity:</b> O(n³) – due to nested loops and substring operation.<br>
     * <b>Space Complexity:</b> O(n) – due to substring storage and function stack.
     *
     * <b>Edge Cases:</b><br>
     * - Empty string → return 0.<br>
     * - String with fewer than 3 characters → return 0.<br>
     */
    public int bruteForce(String s) {
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = s.substring(i, j);
                if (containsAllThreeCharacters(substring)) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Helper method to check if a given substring contains at least one 'a', one 'b', and one 'c'.
     *
     * @param substring The substring to check.
     * @return true if all three characters are present, false otherwise.
     *
     * <b>Time Complexity:</b> O(k), where k is the length of the substring.<br>
     * <b>Space Complexity:</b> O(1)
     */
    private boolean containsAllThreeCharacters(String substring) {
        boolean hasA = false, hasB = false, hasC = false;

        for (char ch : substring.toCharArray()) {
            if (ch == 'a') hasA = true;
            else if (ch == 'b') hasB = true;
            else if (ch == 'c') hasC = true;

            if (hasA && hasB && hasC) {
                return true;
            }
        }

        return false;
    }

    /**
     * Optimized brute-force approach using a set to reduce repeated checks.
     *
     * <p><b>Intuition:</b> We track unique characters while extending the substring. Once we see all three, all longer substrings will also be valid.
     *
     * <p><b>Algorithm Steps:</b><br>
     * 1. Fix a starting point `i`.<br>
     * 2. Extend the window `j` until you find all three characters.<br>
     * 3. Once found, all substrings from `j` to end are valid (count = n - j).<br>
     *
     * @param s The input string.
     * @return The number of valid substrings.
     *
     * <b>Time Complexity:</b> O(n²) – still uses nested loops but avoids unnecessary checks.<br>
     * <b>Space Complexity:</b> O(1) – uses a fixed-size set.
     */
    public int bruteForceOptimized(String s) {
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            Set<Character> uniqueChars = new HashSet<>();
            for (int j = i; j < n; j++) {
                uniqueChars.add(s.charAt(j));
                if (uniqueChars.size() == 3) {
                    count = count + (n - j);
                    break;
                }
            }
        }

        return count;
    }

    /**
     * Sliding window approach using a character count map.
     *
     * <p><b>Intuition:</b> Use a dynamic window from `left` to `right` to track counts of 'a', 'b', and 'c'.<br>
     * When the window contains all three, all substrings ending at `right` and starting from any valid `left` are valid.
     *
     * <p><b>Algorithm Steps:</b><br>
     * 1. Use two pointers: `left` and `right`.<br>
     * 2. Move `right` forward, updating character counts.<br>
     * 3. When the window contains all three characters, increment the count by `(n - right)`.<br>
     * 4. Move `left` to shrink the window until it no longer has all three.<br>
     *
     * @param s The input string.
     * @return The number of valid substrings.
     *
     * <b>Time Complexity:</b> O(n) – each character is visited at most twice.<br>
     * <b>Space Complexity:</b> O(1) – map has at most 3 keys.
     *
     * <b>Edge Cases:</b><br>
     * - Efficient for large inputs.<br>
     * - Assumes only 'a', 'b', 'c' are in the string.
     */
    public int slidingWindow(String s) {
        int count = 0;
        int n = s.length();
        int left = 0;
        Map<Character, Integer> charCount = new HashMap<>();
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            while (charCount.size() == 3) {
                count = count + (n - right);

                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
        }
        return count;
    }

    /**
     * Highly optimized approach using tracking of last seen indices for 'a', 'b', and 'c'.
     *
     * <p><b>Intuition:</b> For each index `i`, the number of valid substrings ending at `i` is determined by the earliest index where all three characters have occurred.
     *
     * <p><b>Algorithm Steps:</b><br>
     * 1. Maintain an array `lastSeenIndices` for indices of 'a', 'b', 'c'.<br>
     * 2. For each character, update its last seen index.<br>
     * 3. Take the minimum of the three indices — that’s how far back we can start a valid substring ending at `i`.<br>
     * 4. Add `1 + minIndex` to the count.<br>
     *
     * @param s The input string.
     * @return The number of substrings containing all three characters.
     *
     * <b>Time Complexity:</b> O(n) – single pass through the string.<br>
     * <b>Space Complexity:</b> O(1) – fixed-size array of length 3.
     *
     * <b>Assumptions:</b><br>
     * - String only contains 'a', 'b', and 'c'.<br>
     * - Characters are lowercase.
     */
    public int slidingWindow2(String s) {
        int n = s.length();
        int count = 0;
        int[] lastSeenIndices = {-1, -1, -1};
        for (int i = 0; i < n; i++) {
            lastSeenIndices[s.charAt(i) - 'a'] = i;
            count = count + 1 + Math.min(lastSeenIndices[0], Math.min(lastSeenIndices[1], lastSeenIndices[2]));
        }
        return count;
    }
}
