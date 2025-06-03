package com.dsa.leetcode.topics.slidingwindow.additional;

public class MinimumRecolorsToGetKConsecutiveBlackBlocks_2379 {

    public int minimumRecolors(String blocks, int k) {
        // return bruteForce(blocks, k);
        return slidingWindow(blocks, k);
    }

    public int bruteForce(String blocks, int k) {
        int n = blocks.length();
        int minReplacements = k;
        for (int i = 0; i <= n - k; i++) {
            int blackCount = 0;
            for (int j = i; j < i + k; j++) {
                if (blocks.charAt(j) == 'B') {
                    blackCount++;
                }
            }
            int replacementsNeeded = k - blackCount;
            minReplacements = Math.min(minReplacements, replacementsNeeded);
            if (minReplacements == 0) {
                return minReplacements;
            }
        }
        return minReplacements;
    }

    public int slidingWindow(String s, int k) {
        int n = s.length();
        int minReplacements = k;
        int blackCount = 0;
        for (int i = 0; i < k; i++) {
            if (s.charAt(i) == 'B') {
                blackCount++;
            }
        }
        minReplacements = k - blackCount;
        for (int i = k; i < n; i++) {
            if (s.charAt(i - k) == 'B') {
                blackCount--;
            }
            if (s.charAt(i) == 'B') {
                blackCount++;
            }
            int replacementsNeeded = k - blackCount;
            minReplacements = Math.min(minReplacements, replacementsNeeded);
            if (minReplacements == 0) {
                return minReplacements;
            }
        }
        return minReplacements;
    }
}
