package com.dsa.leetcode.blind75.ArraysAndStrings;

import java.util.*;

// https://leetcode.com/problems/reverse-vowels-of-a-string/description/?envType=study-plan-v2&envId=leetcode-75
public class ReverseVowelsOfAString_345 {

    public String reverseVowels(String s) {
//        return bruteForce(s);
//        return betterUsingStack(s);
        return optimalUsingTwoPointers(s);
    }

    public String bruteForce(String s) {
        Set<Character> VOWELS = Set.of('a', 'e','i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        List<Integer> vowelsIndies = new ArrayList<>();
        List<Character> vowels = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (VOWELS.contains(arr[i])) {
                vowels.add(arr[i]);
                vowelsIndies.add(i);
            }
        }
        Collections.reverse(vowels);
        for (int i = 0; i < vowelsIndies.size(); i++) {
            arr[vowelsIndies.get(i)] = vowels.get(i);
        }
        return new String(arr);
    }

    public String betterUsingStack(String s) {
        Set<Character> VOWELS = Set.of('a', 'e','i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (char ch : arr) {
            if (VOWELS.contains(ch)) {
                stack.push(ch);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (VOWELS.contains(ch)) {
                arr[i] = stack.pop();
            }
        }
        return new String(arr);
    }

    public String optimalUsingTwoPointers(String s) {
        Set<Character> VOWELS = Set.of('a', 'e','i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        char[] arr = s.toCharArray();
        int n = s.length();
        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (left < n && !VOWELS.contains(arr[left])) {
                left++;
            }
            while (right >= 0 && !VOWELS.contains(arr[right])) {
                right--;
            }
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
            left++;
            right--;
        }
        return new String(arr);
    }
}
