package com.dsa.leetcode.blind75.ArraysAndStrings;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

// https://leetcode.com/problems/reverse-words-in-a-string/description/?envType=study-plan-v2&envId=leetcode-75
public class ReverseWordsInAString_151 {

    public String reverseWords(String s) {
//        return bruteForceUsingBuiltInMethods(s);
        //        return betterUsingStack(s);
        return optimal(s);
    }

    public String betterUsingStack(String s) {
        Stack<String> stack = new Stack<>();
        int n = s.length();
        int i = 0;
        while (i < n) {
            StringBuilder current = new StringBuilder();
            while (i < n && s.charAt(i) == ' ') {
                i++;
            }
            while (i < n && s.charAt(i) != ' ') {
                current.append(s.charAt(i));
                i++;
            }
            if (!current.isEmpty()) {
                stack.push(current.toString());
            }
            i++;
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
            if (!stack.isEmpty()) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public String bruteForceUsingBuiltInMethods(String s) {
        String[] words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public String optimal(String s) {
        int n = s.length();
        StringBuilder result = new StringBuilder();
        int i = n - 1;
        while (i >= 0) {
            StringBuilder current = new StringBuilder();
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            while (i >= 0 && s.charAt(i) != ' ') {
                current.append(s.charAt(i));
                i--;
            }
            if (!current.isEmpty()) {
                if (!result.isEmpty()) {
                    result.append(" ");
                }
                result.append(current.reverse());
            }
        }

        return result.toString();
    }
}
