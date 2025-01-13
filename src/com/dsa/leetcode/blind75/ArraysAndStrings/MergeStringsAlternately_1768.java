package com.dsa.leetcode.blind75.ArraysAndStrings;

// https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75
public class MergeStringsAlternately_1768 {

    public String mergeAlternately(String word1, String word2) {
        int i = 0;
        int j = 0;
        StringBuilder mergedString = new StringBuilder();
        while (i < word1.length() && j < word2.length()) {
            mergedString.append(word1.charAt(i));
            mergedString.append(word2.charAt(j));
            i++;
            j++;
        }
        while (i < word1.length()) {
            mergedString.append(word1.charAt(i));
            i++;
        }
        while (j < word2.length()) {
            mergedString.append(word2.charAt(j));
            j++;
        }
        return mergedString.toString();
    }
}
