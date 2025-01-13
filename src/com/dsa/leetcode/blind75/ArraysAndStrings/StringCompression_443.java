package com.dsa.leetcode.blind75.ArraysAndStrings;

// https://leetcode.com/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75
public class StringCompression_443 {

    public int compress(char[] chars) {
//         return bruteForceUsingExtraSpace(chars);
        return optimalInPlace(chars);
    }

    /**
     * bruteForceUsingExtraSpace: Compress using StringBuilder for efficiency.
     *
     * <b>Intuition:</b>
     * Use a single traversal to compress and a StringBuilder to handle counts.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Traverse the input array and count consecutive characters.
     * 2. Append the character and count (if > 1) to the StringBuilder.
     * 3. Copy the result back to the input array.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * O(n) - Single traversal of the array.
     *
     * <b>Space Complexity:</b>
     * O(n) - Space used by the StringBuilder.
     *
     * @param chars Input character array.
     * @return Length of the compressed string.
     */
    public int bruteForceUsingExtraSpace(char[] chars) {
        int n = chars.length;
        StringBuilder compressed = new StringBuilder();
        int i = 0;
        while (i < n) {
            char currentChar = chars[i];
            int count = 0;
            while (i < n && chars[i] == currentChar) {
                count++;
                i++;
            }
            compressed.append(currentChar);
            if (count > 1) {
                for (char ch : Integer.toString(count).toCharArray()) {
                    compressed.append(ch);
                }
            }
        }
        for (i = 0; i < compressed.length(); i++) {
            chars[i] = compressed.charAt(i);
        }
        return compressed.length();
    }

    public int optimalInPlace(char[] chars) {
        int n = chars.length;
        int writeIndex = 0;
        int readIndex = 0;
        while (readIndex < n) {
            char currentChar = chars[readIndex];
            int count = 0;
            while (readIndex < n && chars[readIndex] == currentChar) {
                readIndex++;
                count++;
            }
            chars[writeIndex++] = currentChar;
            if (count > 1) {
                for (char ch : Integer.toString(count).toCharArray()) {
                    chars[writeIndex++] = ch;
                }
            }
        }
        return writeIndex;

    }

}
