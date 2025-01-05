package com.dsa.leetcode.daily.year2025.january;
// https://leetcode.com/problems/shifting-letters-ii/description/?envType=daily-question&envId=2025-01-05
public class Day_05_ShiftingLetters_II_2381 {

    public String shiftingLetters(String s, int[][] shifts) {
        // return bruteForce(s, shifts);
        return optimalUsingPrefixSum(s, shifts);
    }

    /**
     * bruteForce: Applies each shift operation directly on the substring specified in the queries.
     *
     * <b>Intuition:</b>
     * For each query, iterate through the specified range and apply the shift operation.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. For each query:
     *    a. Extract the substring for the given range.
     *    b. Shift the characters by the specified amount.
     * 2. Update the string after processing each query.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(m * n) - Where `m` is the number of queries and `n` is the average length of the substring for each query.
     *
     * <b>Space Complexity:</b>
     * O(n) - To store the string and intermediate substrings.
     *
     * @param s The input string.
     * @param shifts The queries containing start index, end index, and shift amount.
     * @return The modified string after applying all shifts.
     */
    public String bruteForce(String s, int[][] shifts) {
        char[] chars = s.toCharArray();
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];
            int value = direction == 1 ? 1 : -1;

            for (int i = start; i <= end; i++) {
                chars[i] = (char) ('a' + (chars[i] - 'a' + value + 26) % 26);
            }
        }
        return new String(chars);
    }

    /**
     * optimalUsingPrefixSum: Applies range shifts to the input string using prefix sum for efficiency.
     *
     * <b>Intuition:</b>
     * Instead of shifting characters for each query, use a difference array to mark the start and end
     * of shifts and compute cumulative shifts in a single pass.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Initialize a difference array `shiftDifferences` of size `n`.
     * 2. For each query, update the difference array:
     *    a. Add `+1` or `-1` at the start and end of the range.
     * 3. Compute the cumulative shifts using prefix sum.
     * 4. Apply the cumulative shifts to each character in the string.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n + m) - Where `n` is the length of the string and `m` is the number of queries.
     *
     * <b>Space Complexity:</b>
     * O(n) - Space used for the difference array.
     *
     * @param s The input string.
     * @param shifts The array of shift queries [startIndex, endIndex, direction].
     * @return The modified string after applying all shifts.
     */
    public String optimalUsingPrefixSum(String s, int[][] shifts) {
        int n = s.length();
        int[] shiftDifferences = new int[n];

        // Populate the difference array based on the shifts.
        for (int[] shift : shifts) {
            int startIndex = shift[0];
            int endIndex = shift[1];
            int direction = shift[2];

            // Determine the shift value (1 for right shift, -1 for left shift).
            int shiftValue = direction == 1 ? 1 : -1;

            shiftDifferences[startIndex] += shiftValue;
            if (endIndex + 1 < n) {
                shiftDifferences[endIndex + 1] -= shiftValue;
            }
        }

        // Calculate cumulative shifts and build the resulting string.
        int cumulativeShifts = 0;
        StringBuilder result = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            cumulativeShifts = (cumulativeShifts + shiftDifferences[i]) % 26;
            result.append(shiftChar(s.charAt(i), cumulativeShifts));
        }

        return result.toString();
    }

    public char shiftChar(char ch, int noOfShifts) {
        if (noOfShifts < 0) noOfShifts = noOfShifts + 26;
        return (char) ('a' + ((ch - 'a' + noOfShifts) % 26));
    }

}
