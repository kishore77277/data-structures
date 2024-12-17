package com.dsa.leetcode.daily.year2024.december;

import java.util.*;

// https://leetcode.com/problems/construct-string-with-repeat-limit/description/?envType=daily-question&envId=2024-12-17
public class ConstructStringWithRepeatLimit_2182_Day_17 {

    public String repeatLimitedString(String s, int repeatLimit) {
//        return repeatLimitedStringUsingGreedy(s, repeatLimit);
        return repeatLimitedStringUsingMapAndHeap(s, repeatLimit);
    }

    /**
     * Constructs a string from the given input string `s` such that:
     * - The same character is repeated at most `repeatLimit` times consecutively.
     * - The resulting string is lexicographically largest.
     * <pre>
     * Steps:
     * 1. Count the frequency of each character in the input string `s` and store it in a frequency array.
     * - `freq[i]` represents the count of the character `('a' + i)`.
     * 2. Start from the largest character ('z') and attempt to append it to the result.
     * 3. Append up to `repeatLimit` instances of the current character.
     * 4. If the current character still has remaining frequency, append the next smaller available character
     * to break the repetition.
     * 5. Repeat the process until all characters are exhausted or no valid character can be appended.
     * <\pre>
     * Let N be the length of s and K be the number of unique characters in s.
     *
     * Time Complexity: O(N⋅K)
     *
     * The time complexity of the approach is O(N⋅K). The initial loop that counts character frequencies runs in O(N) time.
     *
     * The outer while loop executes at most K times, which is at most 26 times for this problem since there are at most 26 unique characters in the input string. The inner while loop, which finds the next available character with a non-zero frequency, runs at most 25 times in the worst case.
     *
     * For instance, consider the string s = "zzzzzzzaaaaaaa" with repeatLimit = 1. After exhausting the repeat limit for z, the inner loop iterates to locate a, which involves up to 25 steps. This results in an O(N⋅K) time complexity because for each character in the string of length N, we may need to perform up to K operations to find the next available character.
     *
     * Space Complexity: O(K)
     *
     * The space used by the freq array is O(K), where K is 26 characters at most.
     *
     * The result will store the final string, which in the worst case will be of size N, but this is not considered in the space complexity analysis as it is part of the output.
     *
     * Therefore, the overall space complexity is O(K).
     *
     * @param s           The input string containing lowercase English letters.
     * @param repeatLimit The maximum number of consecutive repetitions allowed for a character.
     * @return The lexicographically largest string satisfying the repeat limit constraint.
     */
    public String repeatLimitedStringUsingGreedy(String s, int repeatLimit) {
        // Frequency array to count occurrences of each character ('a' to 'z').
        int[] frequency = new int[26];
        for (char ch : s.toCharArray()) {
            frequency[ch - 'a']++;
        }

        // Start from the largest character ('z') and move towards the smallest ('a').
        int currentCharIndex = 25;
        StringBuilder result = new StringBuilder();

        // Build the resulting string.
        while (currentCharIndex >= 0) {
            if (frequency[currentCharIndex] == 0) {
                currentCharIndex--; // Move to the next smaller character if the current one is exhausted.
                continue;
            }

            // Determine the number of times to use the current character, bounded by `repeatLimit`.
            int timesToUse = Math.min(frequency[currentCharIndex], repeatLimit);
            for (int i = 0; i < timesToUse; i++) {
                result.append((char) ('a' + currentCharIndex));
            }
            frequency[currentCharIndex] -= timesToUse; // Decrease the frequency of the used character.

            // If the character still has remaining frequency, add the next smaller available character.
            if (frequency[currentCharIndex] > 0) {
                int nextSmallerCharIndex = currentCharIndex - 1;
                while (nextSmallerCharIndex >= 0 && frequency[nextSmallerCharIndex] == 0) {
                    nextSmallerCharIndex--; // Find the next smaller character with non-zero frequency.
                }
                if (nextSmallerCharIndex < 0) {
                    break; // No smaller character available; terminate the loop.
                }
                result.append((char) ('a' + nextSmallerCharIndex));
                frequency[nextSmallerCharIndex]--; // Decrease the frequency of the smaller character.
            }
        }

        return result.toString();
    }

    /**
     * Constructs a lexicographically largest string from the input string `s`
     * such that no character is repeated more than `repeatLimit` times consecutively.
     * The implementation uses a `Map` for frequency counting and a max-heap
     * (priority queue) to process characters in descending order.
     *
     * **Steps:**
     * 1. Count the frequency of each character in the input string using a `Map`.
     * 2. Insert all unique characters into a max-heap (priority queue) sorted in descending order.
     * 3. Process characters from the heap:
     *    - Append up to `repeatLimit` occurrences of the largest character.
     *    - If more occurrences remain, append the next smaller character to break the repetition.
     *    - Reinsert the characters back into the heap if they still have remaining frequency.
     * 4. Repeat until all characters are exhausted or no valid character can be appended.
     *
     * **Time Complexity:**
     * - Building the frequency map: O(n), where `n` is the length of the string.
     * - Max-heap operations: O(m log m), where `m` is the number of unique characters.
     * - Total: O(n + m log m), which simplifies to O(n) when `m` (number of unique characters) is small.
     *
     * **Space Complexity:**
     * - Frequency map: O(m), where `m` is the number of unique characters.
     * - Max-heap: O(m).
     * - Result string: O(n).
     * - Total: O(n + m).
     *
     * @param s           The input string containing lowercase English letters.
     * @param repeatLimit The maximum number of consecutive repetitions allowed for a character.
     * @return The lexicographically largest string satisfying the repeat limit constraint.
     */
    public String repeatLimitedStringUsingMapAndHeap(String s, int repeatLimit) {
        // Map to store the frequency of each character in the string.
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Max-heap (priority queue) to keep characters sorted in descending order of value.
        Queue<Character> charMaxHeap = new PriorityQueue<>((a, b) -> Character.compare(b, a));
        charMaxHeap.addAll(charFrequencyMap.keySet());

        // StringBuilder to construct the resulting string.
        StringBuilder result = new StringBuilder();

        // Process the heap to build the result string.
        while (!charMaxHeap.isEmpty()) {
            char largestChar = charMaxHeap.poll(); // Get the largest available character.
            int timesToUse = Math.min(repeatLimit, charFrequencyMap.get(largestChar)); // Limit repetitions.

            // Append the largest character up to `repeatLimit` times.
            for (int i = 0; i < timesToUse; i++) {
                result.append(largestChar);
            }

            // Update the frequency of the largest character.
            charFrequencyMap.put(largestChar, charFrequencyMap.get(largestChar) - timesToUse);

            // If more occurrences of the largest character remain:
            if (charFrequencyMap.get(largestChar) > 0 && !charMaxHeap.isEmpty()) {
                char secondLargestChar = charMaxHeap.poll(); // Get the next smaller character.

                // Append one occurrence of the smaller character.
                result.append(secondLargestChar);

                // Update its frequency and reinsert into the heap if occurrences remain.
                charFrequencyMap.put(secondLargestChar, charFrequencyMap.get(secondLargestChar) - 1);
                if (charFrequencyMap.get(secondLargestChar) > 0) {
                    charMaxHeap.offer(secondLargestChar);
                }

                // Reinsert the largest character back into the heap.
                charMaxHeap.offer(largestChar);
            }
        }

        return result.toString();
    }

}
