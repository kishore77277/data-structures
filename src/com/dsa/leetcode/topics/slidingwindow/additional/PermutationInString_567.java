package com.dsa.leetcode.topics.slidingwindow.additional;

import java.util.Arrays;

public class PermutationInString_567 {

    public boolean checkInclusion(String s1, String s2) {
        // return bruteForce(s1, s2);
        return slidingWindow(s1, s2);
    }

    public boolean bruteForce(String s1, String s2) {
        int n = s2.length();
        int k = s1.length();
        if (k > n) {
            return false;
        }
        for (int i = 0; i <= n - k; i++) {
            if (isAnagram(s1, s2, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnagram(String s1, String s2, int startIndex) {
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i + startIndex) - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean slidingWindow(String s1, String s2) {
        int n = s2.length();
        int k = s1.length();
        if (k > n) {
            return false;
        }
        int[] s1Freq = new int[26];
        int[] s2Freq = new int[26];
        for (int i = 0; i < k; i++) {
            s1Freq[s1.charAt(i) - 'a']++;
            s2Freq[s2.charAt(i) - 'a']++;
        }
        for (int i = k; i < n; i++) {
            if (Arrays.equals(s1Freq, s2Freq)) {
                return true;
            }
            s2Freq[s2.charAt(i - k) - 'a']--;
            s2Freq[s2.charAt(i) - 'a']++;
        }
        // for last window check
        return Arrays.equals(s1Freq, s2Freq);
    }
}
