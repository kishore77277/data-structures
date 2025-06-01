package com.dsa.leetcode.topics.slidingwindow.imp;

/**
 * 🔠 LeetCode 424 - Longest Repeating Character Replacement
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string `s` consisting of only uppercase English letters, you can perform at most `k` character replacements
 * (i.e., change any letter to any other uppercase letter). Your goal is to return the length of the longest substring
 * that can be obtained such that all characters in the substring are the same after performing these replacements.
 *
 * <p><b>Examples:</b>
 * <ul>
 *   <li>Input: s = "ABAB", k = 2 → Output: 4 ("ABAB" → replace both 'A's or both 'B's → "BBBB" or "AAAA")</li>
 *   <li>Input: s = "AABABBA", k = 1 → Output: 4 ("ABBA" is the optimal window)</li>
 *   <li>Input: s = "ABCDE", k = 1 → Output: 2 (only two chars can be same after 1 replacement)</li>
 * </ul>
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Empty string: s = "", k = any → Output: 0</li>
 *   <li>k = 0: must find longest run of a single character</li>
 *   <li>All characters are the same: s = "AAAA", k = 2 → Output: 4</li>
 * </ul>
 *
 * <p>🔗 <a href="https://leetcode.com/problems/longest-repeating-character-replacement/">LeetCode 424</a>
 *
 * <p>This class contains two solutions:
 * <ul>
 *   <li>{@link #bruteForce(String, int)} - A naive O(n²) approach</li>
 *   <li>{@link #slidingWindow(String, int)} - An optimized O(n) sliding window technique</li>
 * </ul>
 */
public class LongestRepeatingCharacterReplacement_424 {

    /**
     * Brute-force solution to find the longest substring where up to `k` characters can be replaced
     * to make all characters in the substring the same.
     *
     * <p><b>Intuition:</b> Try every possible substring and calculate the number of replacements needed.
     * If the replacements needed are ≤ k, then it's a valid candidate. Track the max length of such substrings.
     *
     * <p><b>Algorithm Steps:</b>
     * <ol>
     *   <li>Loop over all possible starting indices (i)</li>
     *   <li>For each start index, expand the window to every end index (j ≥ i)</li>
     *   <li>Maintain a frequency array of letters in current window</li>
     *   <li>Track the most frequent character count (maxFreq)</li>
     *   <li>If (window length - maxFreq) ≤ k, it's valid → update maxLength</li>
     * </ol>
     *
     * @param s the input string consisting of uppercase English letters only
     * @param k the maximum number of allowed character replacements
     * @return the length of the longest valid substring after at most k replacements
     *
     * <p><b>Time Complexity:</b> O(n²) <br>
     * <b>Space Complexity:</b> O(1) – fixed size frequency array (26 elements)
     *
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li>If s is empty, returns 0</li>
     *   <li>If k = 0, returns length of the longest contiguous block of the same character</li>
     * </ul>
     */
    public int bruteForce(String s, int k) {
        int maxLength = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26]; // Frequency array for 'A' to 'Z'
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'A']++;
                maxFreq = Math.max(maxFreq, freq[s.charAt(j) - 'A']);
                int replacementsNeeded = (j - i + 1) - maxFreq;
                if (replacementsNeeded <= k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    /**
     * Optimized sliding window solution to find the longest substring where up to `k` characters
     * can be replaced to make all characters in the window the same.
     *
     * <p><b>Intuition:</b>
     * Imagine you're dragging a window across the string. At each step, you track how many characters
     * you'd need to replace to make the entire window consist of just one character. As long as that number
     * is ≤ k, you can keep growing the window. If it exceeds k, you shrink the window from the left.
     *
     * <p>Key insight: You only need to track the <b>most frequent character count</b> in the window. The rest can be replaced.
     *
     * <p><b>Algorithm Steps:</b>
     * <ol>
     *   <li>Initialize a frequency array for 26 uppercase characters</li>
     *   <li>Start a window with left and right pointers</li>
     *   <li>For each character at `right`, update frequency and maxFreq</li>
     *   <li>While (window size - maxFreq) > k, shrink the window from left</li>
     *   <li>Update maxLength whenever window is valid</li>
     * </ol>
     *
     * <p><b>Why don’t we update maxFreq when shrinking the window?</b><br>
     * maxFreq might become stale, but that’s okay because it only causes the window to shrink more aggressively.
     * It never causes a wrong answer. We avoid recalculating it to maintain O(n) time.
     *
     * @param s the input string consisting of uppercase English letters only
     * @param k the maximum number of allowed character replacements
     * @return the length of the longest valid substring after at most k replacements
     *
     * <p><b>Time Complexity:</b> O(n) <br>
     * Each character is processed at most twice — once when expanding, once when shrinking.
     *
     * <p><b>Space Complexity:</b> O(1) <br>
     * The frequency array has a constant size of 26.
     *
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li>If s is empty, returns 0</li>
     *   <liIf k = 0, behaves like longest substring of identical characters</li>
     * </ul>
     */
    public int slidingWindow(String s, int k) {
        int maxLength = 0;
        int left = 0;
        int[] freq = new int[26]; // Frequency array for 'A' to 'Z'
        int maxFreq = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);

            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
