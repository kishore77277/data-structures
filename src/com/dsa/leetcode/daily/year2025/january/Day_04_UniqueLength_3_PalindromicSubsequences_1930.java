package com.dsa.leetcode.daily.year2025.january;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day_04_UniqueLength_3_PalindromicSubsequences_1930 {

    public int countPalindromicSubsequence(String s) {
        // return bruteForce(s);
        return optimalUsingPreComputation(s);
    }

    /**
     * bruteForce: Calculates the count of unique palindromic subsequences using a brute force approach.
     *
     * <b>Intuition:</b>
     * The idea is to iterate over each distinct character in the string and consider it as the first and
     * last character of a palindrome. Then, count the distinct characters in the substring between the
     * first and last occurrence of this character.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Identify all distinct characters in the string.
     * 2. For each distinct character:
     *    a. Find the first and last occurrence of the character in the string.
     *    b. Collect all distinct characters in the substring between these two indices.
     *    c. Add the count of these distinct characters to the result.
     * 3. Return the total count.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n^2) - For each character, finding its first and last occurrence, and counting
     * distinct characters in-between takes O(n).
     *
     * <b>Space Complexity:</b>
     * O(n) - Space used by the HashSet to store distinct characters in-between.
     *
     * @param s The input string.
     * @return The count of unique palindromic subsequences.
     */
    public int bruteForce(String s) {
        int n = s.length();
        Set<Character> allLetters = new HashSet<>();
        for (int i = 0; i < n; i++) {
            allLetters.add(s.charAt(i));
        }
        int result = 0;
        for (Character letter : allLetters) {
            int first = -1;
            int last = -1;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == letter) {
                    if (first == -1) {
                        first = i;
                    }
                    last = i;
                }
            }
            Set<Character> distinctLettersInBetween = new HashSet<>();
            for (int i = first + 1; i < last; i++) {
                distinctLettersInBetween.add(s.charAt(i));
            }
            result += distinctLettersInBetween.size();
        }
        return result;
    }


    /**
     * optimalUsingPreComputation: Calculates the count of unique palindromic subsequences using
     * precomputation for efficiency.
     *
     * <b>Intuition:</b>
     * By precomputing the first and last occurrence of each character in the string, we can
     * reduce redundant operations and directly count the distinct characters between these indices.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Initialize two arrays, `first` and `last`, of size 26 to store the first and last
     *    occurrence of each character.
     * 2. Iterate over the string:
     *    a. Update the `first` and `last` arrays for each character.
     * 3. For each character in the alphabet:
     *    a. If the character exists in the string (first[i] != -1):
     *       i. Count all distinct characters in the substring between `first[i]` and `last[i]`.
     *       ii. Add the count of these characters to the result.
     * 4. Return the total count.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n) - The precomputation and counting operations are linear with respect to the input size.
     *
     * <b>Space Complexity:</b>
     * O(n) - Space used by the HashSet to store distinct characters.
     *
     * @param s The input string.
     * @return The count of unique palindromic subsequences.
     */
    public int optimalUsingPreComputation(String s) {
        int n = s.length();
        int result = 0;
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        for (int i = 0; i < n; i++) {
            int current = s.charAt(i) - 'a';
            if (first[current] == -1) {
                first[current] = i;
            }
            last[current] = i;
        }
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) {
                continue;
            }
            Set<Character> distinctLettersInBetween = new HashSet<>();
            for (int j = first[i] + 1; j < last[i]; j++) {
                distinctLettersInBetween.add(s.charAt(j));
            }
            result += distinctLettersInBetween.size();
        }
        return result;
    }

}
