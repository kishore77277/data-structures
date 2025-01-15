package com.dsa.leetcode.blind75.SlidingWindow;

// https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/?envType=study-plan-v2&envId=leetcode-75
public class MaximumNumberOfVowelsInASubstringOfGivenLength_1456 {

    public int maxVowels(String s, int k) {
//         return bruteForceMaxVowels(s, k);
        return slidingWindowMaxVowels(s, k);
    }

    /**
     * bruteForceMaxVowels: Finds the maximum number of vowels in any substring of length `k`.
     *
     * <b>Intuition:</b>
     * Check every substring of length `k`, count its vowels, and track the maximum count.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Iterate through the string.
     * 2. For each index, extract the substring of length `k`.
     * 3. Count vowels in the substring and update the maximum count.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n \times k) \) - For every substring of size `k`, we check its vowels.
     *
     * <b>Space Complexity:</b>
     * \( O(1) \) - No additional space used.
     *
     * @param s Input string.
     * @param k Size of the substring.
     * @return Maximum number of vowels in any substring of length `k`.
     */
    public int bruteForceMaxVowels(String s, int k) {
        int maxVowels = 0;
        int n = s.length();
        for (int i = 0; i <= n - k; i++) {
            int count = 0;
            for (int j = i; j < i + k; j++) {
                if (isVowel(s.charAt(j))) {
                    count++;
                }
            }
            maxVowels = Math.max(maxVowels, count);
        }
        return maxVowels;
    }

    // Helper function to check if a character is a vowel
    private boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }


    /**
     * slidingWindowMaxVowels: Finds the maximum number of vowels using a sliding window.
     *
     * <b>Intuition:</b>
     * Maintain a count of vowels in the current window of size `k`.
     * Slide the window by one character at a time, adjusting the count as needed.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Initialize the vowel count for the first `k` characters.
     * 2. Slide the window:
     *    - Subtract the effect of the character leaving the window.
     *    - Add the effect of the character entering the window.
     * 3. Track the maximum vowel count.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n) \) - Single traversal of the string.
     *
     * <b>Space Complexity:</b>
     * \( O(1) \) - Constant space used.
     *
     * @param s Input string.
     * @param k Size of the substring.
     * @return Maximum number of vowels in any substring of length `k`.
     */
    public int slidingWindowMaxVowels(String s, int k) {
        int currentVowelCount = 0, maxVowelCount = 0;

        // Step 1: Count vowels in the first window
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentVowelCount++;
            }
        }
        maxVowelCount = currentVowelCount;

        // Step 2: Slide the window
        for (int i = k; i < s.length(); i++) {
            if (isVowel(s.charAt(i - k))) {
                currentVowelCount--; // Remove the effect of the outgoing character
            }
            if (isVowel(s.charAt(i))) {
                currentVowelCount++; // Add the effect of the incoming character
            }
            maxVowelCount = Math.max(maxVowelCount, currentVowelCount);
        }

        return maxVowelCount;
    }
}
