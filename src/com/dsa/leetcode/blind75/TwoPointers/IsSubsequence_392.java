package com.dsa.leetcode.blind75.TwoPointers;

// https://leetcode.com/problems/is-subsequence/description/?envType=study-plan-v2&envId=leetcode-75
public class IsSubsequence_392 {

    public boolean isSubsequenceTwoPointers(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) { // if matches the char, will increase the s index
                sIndex++;
            }
            tIndex++;
        }
        return sIndex == s.length(); // finally we should have s index as length of s string, that means we have found
    }

    public boolean isSubsequenceForLoop(String s, String t) {
        int sIndex = 0;
        for (char ch : t.toCharArray()) {
            if (sIndex < s.length() && ch == s.charAt(sIndex)) {
                sIndex++;
            }
            if (sIndex == s.length()) {
                return true;
            }
        }
        return sIndex == s.length();
    }
}
