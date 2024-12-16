package com.dsa.leetcode.topics.stack;

import java.util.HashMap;
import java.util.Stack;

// https://leetcode.com/problems/valid-parentheses/description/
public class ValidParentheses_20 {

    public boolean isValid(String s) {
//        return isValidBruteForceUsingReplace(s);
//        return isValidBetterUsingStacks(s);
        return isValidOptimal(s);
    }

    /**
     * Determines if the given string of parentheses is valid using a brute force approach.
     *
     * <p>Intuition: Continuously replace valid pairs of parentheses ("()", "{}", "[]") in the string
     * until no pairs are left. If the final string is empty, it is valid; otherwise, it is not.</p>
     *
     * Steps:
     * 1. Search for valid pairs of parentheses in the string.
     * 2. Replace all valid pairs with an empty string.
     * 3. Repeat until no valid pairs can be found.
     * 4. Check if the string is empty at the end.
     *
     * Time Complexity: O(n^2) - Each `replace` operation scans the string (O(n)) and can run n/2 times
     * (in the worst case, removing one pair at a time).
     * Space Complexity: O(n) - Due to the creation of new strings during replacement.
     *
     * @param s the input string containing parentheses
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValidBruteForceUsingReplace(String s) {
        // Continuously replace matching pairs of parentheses
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }
        // If the string is empty, it is valid
        return s.isEmpty();
    }

    /**
     * Determines if the given string of parentheses is valid using a stack-based approach.
     *
     * <p>Intuition: Use a stack to store opening brackets. When a closing bracket is encountered,
     * check if it matches the top of the stack. If it does, pop the stack; otherwise, return false.</p>
     *
     * Steps:
     * 1. Traverse the string character by character.
     * 2. Push opening brackets onto the stack.
     * 3. For closing brackets, check the top of the stack for a matching opening bracket.
     * 4. If mismatched or the stack is empty, return false.
     * 5. At the end, ensure the stack is empty.
     *
     * Time Complexity: O(n) - Each character is processed once.
     * Space Complexity: O(n) - Stack stores at most n/2 opening brackets.
     *
     * @param s the input string containing parentheses
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValidBetterUsingStacks(String s) {
        Stack<Character> stack = new Stack<>();

        // Traverse each character in the string
        for (char ch : s.toCharArray()) {
            // Push opening brackets onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else { // Handle closing brackets
                if (stack.isEmpty()) {
                    return false; // No matching opening bracket
                }
                char top = stack.pop(); // Pop the top element
                // Check if it matches the current closing bracket
                if ((ch == ')' && top != '(') ||
                        (ch == '}' && top != '{') ||
                        (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }
        // Ensure no unmatched opening brackets remain
        return stack.isEmpty();
    }

    /**
     * Using stack but with variation of pushing and checking the conditions
     * 1. above soultion als works well, but involves lot of conditions
     * 2. to avoid them, if we encounter open brace, we will push corresponding close brace
     * 3. if we get close brace now, we will check for popped == current or not, if not return false
     *
     */
    public boolean isValidUsingStackVariation(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                // closed brace
                // if stack is empty return false
                // is popped item is not same return false
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Determines if the given string of parentheses is valid using a stack with optimized matching.
     *
     * <p>Intuition: Use a HashMap for quick lookup of matching brackets. Push opening brackets
     * onto the stack and validate closing brackets using the map.</p>
     *
     * Steps:
     * 1. Use a HashMap to store matching pairs of parentheses.
     * 2. Traverse the string character by character.
     * 3. Push opening brackets onto the stack.
     * 4. For closing brackets, check if the stack is empty or if the top does not match.
     * 5. At the end, ensure the stack is empty.
     *
     * Time Complexity: O(n) - Each character is processed once.
     * Space Complexity: O(n) - Stack stores at most n/2 opening brackets.
     *
     * @param s the input string containing parentheses
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValidOptimal(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();

        // Traverse each character in the string
        for (char ch : s.toCharArray()) {
            if (map.containsKey(ch)) { // Closing bracket
                // Pop the top of the stack if not empty, or use a dummy value
                char top = stack.isEmpty() ? '#' : stack.pop();
                if (top != map.get(ch)) {
                    return false; // Mismatched bracket
                }
            } else { // Opening bracket
                stack.push(ch);
            }
        }
        // Ensure no unmatched opening brackets remain
        return stack.isEmpty();
    }
}
