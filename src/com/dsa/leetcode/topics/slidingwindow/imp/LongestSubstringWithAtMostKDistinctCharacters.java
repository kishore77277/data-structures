package com.dsa.leetcode.topics.slidingwindow.imp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ✅ <b>Longest Substring with At Most K Distinct Characters</b>
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string <code>s</code> and an integer <code>k</code>, return the length of the longest substring
 * that contains at most <code>k</code> distinct characters.
 * <br><br>
 * You must identify the longest possible contiguous block (substring) in which the number of unique
 * characters does not exceed <code>k</code>.
 *
 * <p><b>Example:</b><br>
 * <pre>
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: The substring is "aa".
 *
 * Input: s = "a", k = 0
 * Output: 0
 * Explanation: You cannot include any characters if k = 0.
 * </pre>
 *
 * <p><b>Edge Cases & Tricky Scenarios:</b>
 * <ul>
 *     <li>k = 0 → return 0 (no characters allowed)</li>
 *     <li>k >= number of unique characters in s → return s.length()</li>
 *     <li>Empty string s → return 0</li>
 * </ul>
 *
 * <p><b>Link:</b> <a href="https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/">LeetCode 340</a>
 * <p><b>Link:</b> <a href="https://www.naukri.com/code360/problems/distinct-characters_2221410">Code 360</a>
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    /**
     * 🚀 Brute-force solution to find the longest substring with at most k distinct characters.
     *
     * <p><b>Intuition:</b><br>
     * Try every possible substring starting from each character, and check if it satisfies
     * the constraint of at most <code>k</code> distinct characters.
     *
     * <p><b>Algorithm Steps:</b>
     * <ol>
     *     <li>Start at every index i of the string</li>
     *     <li>Keep a set to store unique characters as we expand to the right</li>
     *     <li>If the size of the set exceeds k, break the inner loop</li>
     *     <li>Update maxLength accordingly</li>
     * </ol>
     *
     * @param s the input string
     * @param k the maximum number of distinct characters allowed
     * @return length of the longest valid substring
     *
     * <p><b>Time Complexity:</b> O(n²) <br>
     * <b>Space Complexity:</b> O(k) – for the set storing at most k characters
     *
     * <p><b>Edge Cases:</b>
     * <ul>
     *     <li>If s is null or empty, returns 0</li>
     *     <li>If k is 0, returns 0</li>
     * </ul>
     */
    public int bruteForce(String s, int k) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> uniqueChars = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                uniqueChars.add(s.charAt(j));
                if (uniqueChars.size() > k) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }

    /**
     * 🔁 Improved solution using a sliding window technique with a frequency map.
     *
     * <p><b>Intuition:</b><br>
     * Instead of checking all substrings, use two pointers to maintain a window that
     * always satisfies the k distinct character rule. Expand the window to the right,
     * and shrink from the left when the condition breaks.
     *
     * <p><b>Algorithm Steps:</b>
     * <ol>
     *     <li>Initialize a left pointer and a map to count characters</li>
     *     <li>Iterate with the right pointer</li>
     *     <li>Add characters to the map and update their count</li>
     *     <li>While map size exceeds k, shrink from the left and update counts</li>
     *     <li>Track max window length at each step</li>
     * </ol>
     *
     * @param s the input string
     * @param k the max number of distinct characters allowed
     * @return length of the longest substring with at most k distinct characters
     *
     * <p><b>Time Complexity:</b> O(n) — Each character is added and removed at most once <br>
     * <b>Space Complexity:</b> O(k) — At most k entries in the map
     *
     * <p><b>Edge Cases:</b>
     * <ul>
     *     <li>If s is empty or k == 0 → returns 0</li>
     *     <li>If k ≥ number of unique characters in s → returns s.length()</li>
     * </ul>
     */
    public int betterUsingSlidingWindow(String s, int k) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> charCount = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    /**
     * 🧠 Optimal solution using a "smart" sliding window with a character index map.
     *
     * <p><b>Intuition:</b><br>
     * This method optimizes the shrinking part by tracking the <i>last occurrence</i>
     * of each character and removing the leftmost recently used character when k is exceeded.
     *
     * <p><b>Algorithm Steps:</b>
     * <ol>
     *     <li>Maintain a map of characters to their latest index</li>
     *     <li>For each character at index <code>right</code>:
     *         <ul>
     *             <li>Update its index in the map</li>
     *             <li>If size exceeds k, find the leftmost index and shrink window</li>
     *         </ul>
     *     </li>
     *     <li>Update maxLength if current window is valid</li>
     * </ol>
     *
     * @param s the input string
     * @param k the max number of distinct characters allowed
     * @return length of the longest valid substring
     *
     * <p><b>Time Complexity:</b> O(n * k) — We may scan the map to find min index <br>
     * <b>Space Complexity:</b> O(k) — For storing up to k character indices
     *
     * <p><b>Watch Out For:</b>
     * <ul>
     *     <li>This version may perform slower than the previous method if k is large</li>
     *     <li>Still very effective when needing strict character order tracking</li>
     * </ul>
     */
    public int optimalUsingSmartSlidingWindow(String s, int k) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charIndexMap.put(currentChar, right);

            if (charIndexMap.size() > k) {
                int minIndex = Integer.MAX_VALUE;
                for (int index : charIndexMap.values()) {
                    minIndex = Math.min(minIndex, index);
                }
                left = minIndex + 1;
                charIndexMap.remove(s.charAt(minIndex));
            }

            if (charIndexMap.size() <= k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        return maxLength;
    }

}
