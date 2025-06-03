package com.dsa.leetcode.topics.slidingwindow.additional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString_438 {

    public List<Integer> findAnagrams(String s, String p) {
        // return bruteForce(s, p);
        return slidingWindow(s, p);
    }

    public List<Integer> slidingWindow(String s, String p) {
        int n = s.length();
        int k = p.length();
        List<Integer> result = new ArrayList<>();
        if (k > n) {
            return result;
        }
        int[] pFreq = new int[26];
        int[] sFreq = new int[26];
        for (int i = 0; i < k; i++) {
            pFreq[p.charAt(i) - 'a']++;
            sFreq[s.charAt(i) - 'a']++;
        }
        if (isAnagram(sFreq, pFreq)) {
            result.add(0);
        }
        for (int i = k; i < n; i++) {
            sFreq[s.charAt(i) - 'a']++;
            sFreq[s.charAt(i - k) - 'a']--;
            if (isAnagram(sFreq, pFreq)) {
                result.add(i - k + 1);
            }
        }
        return result;
    }

    public List<Integer> bruteForce(String s, String p) {
        int n = s.length();
        int k = p.length();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= n - k; i++) {
            if (isAnagram(s, p, i)) {
                result.add(i);
            }
        }
        return result;
    }

    public boolean isAnagram(String s, String p, int startIndex) {
        int[] freq = new int[26];
        for (int i = 0; i < p.length(); i++) {
            freq[s.charAt(i + startIndex) - 'a']++;
            freq[p.charAt(i) - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }
}
