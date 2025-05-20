package com.dsa.leetcode.topics.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://www.naukri.com/code360/problems/distinct-characters_2221410?leftPanelTabValue=PROBLEM
public class LongestSubstringWithAtMostKDistinctCharacters {

    public static int kDistinctChars(int k, String str) {
//        return bruteForce(str, k);
//        return betterUsingSlidingWindow(str, k);
        return optimalUsingSlidingWindow(str, k);
    }

    public static int bruteForce(String s, int k) {
        int maxLength = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j));
                if (set.size() > k) {
                    break;
                }
                maxLength = Math.max(j - i + 1, maxLength);
            }
        }
        return maxLength;
    }

    public static int betterUsingSlidingWindow(String s, int k) {
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> freq = new HashMap<>();
        int left = 0;
        for (int right = 0; right < n; right++) {
            freq.put(s.charAt(right), freq.getOrDefault(s.charAt(right), 0) + 1);
            while (freq.size() > k) {
                freq.put(s.charAt(left), freq.get(s.charAt(left)) - 1);
                if (freq.get(s.charAt(left)) == 0) {
                    freq.remove(s.charAt(left));
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static int optimalUsingSlidingWindow(String s, int k) {
        int n = s.length();
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> freq = new HashMap<>();
        for (int right = 0; right < n; right++) {
            char rightChar = s.charAt(right);
            freq.put(rightChar, freq.getOrDefault(rightChar, 0) + 1);
            if (freq.size() > k) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++;
            }
            if (freq.size() <= k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }
        return maxLength;
    }


}
