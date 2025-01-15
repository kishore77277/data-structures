package com.dsa.leetcode.blind75.PrefixSum;

// https://leetcode.com/problems/find-the-highest-altitude/description/?envType=study-plan-v2&envId=leetcode-75
public class FindTheHighestAltitude_1732 {

    public int largestAltitude(int[] gain) {
        // return bruteForce(gain);
        return usingPrefixSum(gain);
    }

    public int bruteForce(int[] gain) {
        int n = gain.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + gain[i - 1];
        }
        int maxAltitude = 0;
        for (int alt : prefix) {
            maxAltitude = Math.max(maxAltitude, alt);
        }
        return maxAltitude;
    }

    public int usingPrefixSum(int[] gain) {
        int maxAltitude = 0;
        int currentAltitude = 0;
        for (int alt : gain) {
            currentAltitude = currentAltitude + alt;
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        return maxAltitude;
    }
}
