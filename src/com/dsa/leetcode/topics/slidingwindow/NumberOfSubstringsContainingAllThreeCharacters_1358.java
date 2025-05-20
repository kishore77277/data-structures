package com.dsa.leetcode.topics.slidingwindow;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
public class NumberOfSubstringsContainingAllThreeCharacters_1358 {

    public int numberOfSubstrings(String s) {
//        return bruteForce(s);
//        return optimalUsingSlidingWindow(s);
        return optimalUsingSlidingWindowSuffix(s);
    }

    public int bruteForce(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j));
                if (set.size() == 3) {
                    // starting from this index, to n,
                    // we will have set of size 3, means we have all the chars
                    count = count + n - j;
                    break; // so we do not need to go further
                }
            }
        }
        return count;
    }

    /**
     * prefixCountSubstrings: Count substrings using prefix sums for character tracking.
     *
     * <b>Intuition:</b>
     * Use prefix sums to track the last occurrence of each character and calculate the count directly.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Maintain an array to store the last occurrence of each character ('a', 'b', 'c').
     * 2. Traverse the string and update the array as you encounter characters.
     * 3. For each position, find the minimum last occurrence index among 'a', 'b', and 'c'.
     * 4. Add the contribution of valid substrings to the count.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n) - Single pass through the string.
     *
     * <b>Space Complexity:</b>
     * O(1) - Constant space for tracking character occurrences.
     *
     * @param s Input string.
     * @return Number of substrings containing 'a', 'b', and 'c'.
     */
    public int optimalUsingSlidingWindow(String s) {
        int n = s.length(), count = 0;
        int[] lastOccurrence = {-1, -1, -1}; // Last index of 'a', 'b', and 'c'

        for (int i = 0; i < n; i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;

            // Find the earliest last occurrence among 'a', 'b', and 'c'
            int minLastOccurrence = Math.min(lastOccurrence[0], Math.min(lastOccurrence[1], lastOccurrence[2]));
            if (minLastOccurrence != -1) {
                count += minLastOccurrence + 1;
            }
        }
        return count;
    }

    /**
     * 1. in above approach, we have a intuition to count the strs what are valid ending at that index
     * 2. in this approach we will count the valid ones from that index to right of it
     * 3. any how, if index right satisfies our conditions, we can say that right of it always satisfies    *
     *
     * slidingWindowCountSubstrings: Count substrings containing 'a', 'b', and 'c' using a sliding window.
     *
     * <b>Intuition:</b>
     * Use two pointers (`left` and `right`) to maintain a window that contains at least one 'a', 'b', and 'c'.
     * For every valid window, all substrings starting at the current `left` to the end are valid.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Use a frequency map to count characters in the current window.
     * 2. Expand the window by moving the `right` pointer and updating the map.
     * 3. Shrink the window from the left as long as it contains all three characters.
     * 4. Add the count of substrings ending at `right` that are valid.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n) - Each pointer traverses the string once.
     *
     * <b>Space Complexity:</b>
     * O(1) - Constant space for the frequency map.
     *
     * @param s Input string.
     * @return Number of substrings containing 'a', 'b', and 'c'.
     */
    public int optimalUsingSlidingWindowSuffix(String s) {
        int count = 0;
        int n = s.length();
        int[] freq = new int[3];
        int left = 0;
        for (int right = 0; right < n; right++) {
            freq[s.charAt(right) - 'a']++;
            while (freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {
                count = count + n - right;
                freq[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return count;
    }
}
