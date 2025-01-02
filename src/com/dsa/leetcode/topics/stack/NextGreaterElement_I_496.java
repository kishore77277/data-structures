package com.dsa.leetcode.topics.stack;

import java.util.*;

// https://leetcode.com/problems/next-greater-element-i/description/
public class NextGreaterElement_I_496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
//        return nextGreaterElementBruteForce(nums1, nums2);
        return nextGreaterElementUsingMonotonicStack(nums1, nums2);
    }

    /**
     * Finds the next greater elements for nums1 in nums2 using brute force.
     *
     * @param nums1 the subset array
     * @param nums2 the main array
     * @return an array of next greater elements for nums1 in nums2
     *
     * Time Complexity: O(m * n) where m is the length of nums1 and n is the length of nums2.
     * Space Complexity: O(1), ignoring the output array.
     */
    public int[] nextGreaterElementBruteForce(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        // Iterate over each element in nums1
        for (int i = 0; i < nums1.length; i++) {
            boolean found = false;
            result[i] = -1; // Default to -1 if no greater element is found

            // Find the position of nums1[i] in nums2
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    found = true;
                }

                // After finding nums1[i] in nums2, look for the next greater element
                if (found && nums2[j] > nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
            }
        }
        return result;
    }


    /**
     * Finds the next greater elements for nums1 in nums2 using a HashMap.
     *
     * @param nums1 the subset array
     * @param nums2 the main array
     * @return an array of next greater elements for nums1 in nums2
     *
     * Time Complexity: O(m + n^2)
     * Space Complexity: O(n), for the HashMap.
     */
    public int[] nextGreaterElementBetter(int[] nums1, int[] nums2) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            indexMap.put(nums2[i], i);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int startIndex = indexMap.get(nums1[i]); // Find nums1[i] in nums2
            result[i] = -1; // Default value
            for (int j = startIndex + 1; j < nums2.length; j++) {
                if (nums2[j] > nums1[i]) {
                    result[i] = nums2[j];
                    break;
                }
            }
        }
        return result;
    }


    /**
     * Finds the next greater element for each number in nums1 from nums2 using a monotonic stack approach.
     *
     * <p>**Intuition:**
     * The problem is about efficiently finding the next greater element for each number in nums1,
     * by leveraging the properties of a decreasing monotonic stack:
     * - Traverse nums2 from right to left.
     * - Use a stack to maintain a list of potential "next greater elements" in decreasing order.
     * - For each number, remove smaller elements from the stack since they cannot be "next greater elements."
     * - The top of the stack (if not empty) will be the next greater element for the current number.
     *
     * This avoids redundant comparisons and ensures a linear time complexity.
     *
     * <p>**Algorithm Steps:**
     * 1. Traverse nums2 from right to left.
     * 2. Maintain a stack of decreasing elements:
     *     - Remove elements from the stack if they are smaller or equal to the current number.
     *     - If the stack is non-empty, the top element is the next greater element for the current number.
     *     - Push the current number onto the stack for future comparisons.
     * 3. Use a HashMap to store the next greater element for each number in nums2.
     * 4. For each number in nums1, retrieve its next greater element from the HashMap.
     *
     * <p>**Time Complexity:** O(n + m)
     * - O(n): Traverse nums2 once to compute the next greater elements.
     * - O(m): Build the result array for nums1 by querying the HashMap.
     * - Overall, the stack ensures each element is pushed and popped at most once.
     *
     * <p>**Space Complexity:** O(n)
     * - The stack and HashMap both store at most n elements from nums2.
     *
     * @param nums1 The subset array for which we find the next greater elements.
     * @param nums2 The superset array containing the full range of elements.
     * @return An array where each index corresponds to the next greater element of nums1.
     */
    public int[] nextGreaterElementUsingMonotonicStack(int[] nums1, int[] nums2) {
        // Map to store the next greater element for each number in nums2.
        Map<Integer, Integer> map = new HashMap<>();
        // Monotonic decreasing stack to keep potential next greater elements.
        Stack<Integer> stack = new Stack<>();

        // Traverse nums2 from right to left to find next greater elements.
        for (int i = nums2.length - 1; i >= 0; i--) {
            // Remove smaller or equal elements from the stack.
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }

            // If the stack is empty, no greater element exists for nums2[i].
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());

            // Push the current element onto the stack.
            stack.push(nums2[i]);
        }

        // Build the result array for nums1 by looking up the next greater element from the map.
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }

}
