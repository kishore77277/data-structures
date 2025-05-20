package com.dsa.leetcode.topics.slidingwindow;

// https://leetcode.com/problems/longest-repeating-character-replacement/description/
public class LongestRepeatingCharacterReplacement_424 {


    public int characterReplacement(String s, int k) {
//        return bruteForce(s, k);
//        return betterUsingSlidingWindow(s, k);
        return optimalUsingSlidingWindow(s, k);
    }

    /**
     * 1. ide is to generate all the sub strings and find the maxFreq in that substring
     * 2. if in that length j - i + 1, if we have difference of length and maxFreq less then k, it can be valid
     * 3. if it exceeds k, then we can break out, anyhow adding chars does not gonna change the maxfreq
     */
    public int bruteForce(String s, int k) {
        int n = s.length();
        int maxLength = 0;
        int maxFreq;
        int[] freq;
        for (int i = 0; i < n; i++) {
            freq = new int[26]; // for every sub string, we will calculate the freq
            maxFreq = 0; // in this substring, max freq count
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'A']++;
                maxFreq = Math.max(freq[s.charAt(j) - 'A'], maxFreq);
                if ((j - i + 1) - maxFreq <= k) { // if length of sub string - max freq = other chars other than max freq char
                    maxLength = Math.max(maxLength, j - i + 1);
                } else { // even if we keep going, maxfreq may increase and the other chars count also increases, so never comes down to k again
                    break;
                }
            }
        }
        return maxLength;
    }

    public int betterUsingSlidingWindow(String s, int k) {
        int n = s.length();
        int maxLength = 0;
        int left = 0;
        int maxFreq = 0;
        int[] freq = new int[26];
        for (int right = 0; right < n; right++) {
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);
            while (right - left + 1 - maxFreq > k) { // if we have more chars than k, to replace
                freq[s.charAt(left) - 'A']--;
                for (int count : freq) {
                    maxFreq = Math.max(maxFreq, count);
                }
                left++;
            }
            maxLength = Math.max(right - left + 1, maxLength);
        }
        return maxLength;
    }

    public int optimalUsingSlidingWindow(String s, int k) {
        int n = s.length();
        int maxLength = 0;
        int maxFreq = 0;
        int[] freq = new int[26];
        int left = 0;
        for (int right = 0; right < n; right++) {
            freq[s.charAt(right) - 'A']++;
            maxFreq = Math.max(maxFreq, freq[s.charAt(right) - 'A']);
            if (right - left + 1 - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            if (right - left + 1 - maxFreq <= k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        return maxLength;
    }
}
