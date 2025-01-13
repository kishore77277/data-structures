package com.dsa.leetcode.topics.prefixsum;

// https://leetcode.com/problems/shifting-letters/description/
public class ShiftingLetters_848 {

    public String shiftingLetters(String s, int[] shifts) {
        // return bruteForce(s, shifts);
        return optimalUsingPrefixSum(s, shifts);
    }

    public String bruteForce(String s, int[] shifts) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                arr[j] = shiftChar(arr[j], shifts[i]);
            }
        }
        return new String(arr);
    }

    public char shiftChar(char ch, int noOfShifts) {
        if (noOfShifts < 0) {
            noOfShifts += 26;
        }
        return (char) ('a' + (ch - 'a' + noOfShifts) % 26);
    }

    public String optimalUsingPrefixSum(String s, int[] shifts) {
        int n = s.length();
        StringBuilder result = new StringBuilder();
        int cumulativeShifts = 0;
        for (int i = 0; i < n; i++) {
            cumulativeShifts = (cumulativeShifts + shifts[i]) % 26;
        }
        for (int i = 0; i < n; i++) {
            result.append(shiftChar(s.charAt(i), cumulativeShifts));
            cumulativeShifts = (cumulativeShifts - shifts[i]) % 26;
        }
        return result.toString();
    }

    public String optimalFormingStringFromRevreseDirection(String s, int[] shifts) {
        int n = s.length();
        char[] result = new char[n];
        int cumulativeShifts = 0;
        for (int i = n - 1; i >= 0; i--) {
            cumulativeShifts = (cumulativeShifts + shifts[i]) % 26;
            result[i] = shiftChar(s.charAt(i), cumulativeShifts);
        }
        return new String(result);
    }
}
