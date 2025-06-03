package com.dsa.leetcode.misc;

public class LengthOfTheLongestAlphabeticalContinuousSubstring_2414 {

    public int longestContinuousSubstring(String s) {
        // return bruteForce(s);
        return intuition(s);
    }

    public int bruteForce(String s) {
        int n = s.length();
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int currentLength = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(j) != (s.charAt(j - 1) + 1)) {
                    break;
                }
                currentLength++;
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }

    public int intuition(String s) {
        int n = s.length();
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1) + 1) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength;
    }
}
