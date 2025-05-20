package com.dsa.leetcode.blind75.Stack;

import java.util.Stack;

// https://leetcode.com/problems/removing-stars-from-a-string/description/?envType=study-plan-v2&envId=leetcode-75
public class RemovingStarsFromAString_2390 {

    public String removeStars(String s) {
//        return usingStack(s);
        return usingStringBuilder(s);
    }

    public String usingStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }
        return result.toString();
    }

    public String usingStringBuilder(String s) {
        StringBuilder result = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '*') {
                if (!result.isEmpty()) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
