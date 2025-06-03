package com.dsa.leetcode.topics.slidingwindow.additional;

public class MaximumNumberOfVowelsInASubstringOfGivenLength_1456 {
    public int maxVowels(String s, int k) {
        // return bruteForce(s, k);
        return slidingWindow(s, k);
    }

    public int bruteForce(String s, int k) {
        int n = s.length();
        int maxCount = 0;
        for (int i = 0; i <= n - k; i++) {
            int count = 0;
            for (int j = 0; j < k; j++) {
                if (isVowel(s.charAt(i + j))) {
                    count++;
                }
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public int slidingWindow(String s, int k) {
        int n = s.length();
        int maxCount = 0;
        int currentCount = 0;
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                currentCount++;
            }
        }
        maxCount = currentCount;
        for (int i = k; i < n; i++) {
            if (isVowel(s.charAt(i - k))) {
                currentCount--;
            }
            if (isVowel(s.charAt(i))) {
                currentCount++;
            }
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }
}
