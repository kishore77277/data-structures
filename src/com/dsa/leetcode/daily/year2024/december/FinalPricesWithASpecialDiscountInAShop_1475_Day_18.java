package com.dsa.leetcode.daily.year2024.december;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/?envType=daily-question&envId=2024-12-18
public class FinalPricesWithASpecialDiscountInAShop_1475_Day_18 {

    public int[] finalPrices(int[] prices) {
//        return finalPricesBruteForce(prices);
        return finalPricesUsingStack(prices);
    }


    /**
     * Computes the final prices using a brute force approach.
     *
     * Intuition:
     * - For each item, traverse its right side to find the first price that is less than or equal to it.
     * - Subtract the found discount from the item's price.
     *
     * Steps:
     * 1. Initialize an array `finalPrices` of the same size as `prices`.
     * 2. For each item at index `i`, check all items to the right:
     *    - If a smaller or equal price is found, subtract it and break.
     *    - If no discount is found, subtract 0.
     * 3. Return the finalPrices array.
     *
     * Time Complexity: O(n^2)
     * - Each element requires traversing the rest of the array in the worst case.
     * Space Complexity: O(1)
     * - No extra space is used apart from the output array.
     *
     * @param prices Array of item prices.
     * @return Array of final prices after applying discounts.
     */
    public int[] finalPricesBruteForce(int[] prices) {
        int n = prices.length;
        int[] finalPrices = new int[n];

        for (int i = 0; i < n; i++) {
            int discount = 0;
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    discount = prices[j];
                    break;
                }
            }
            finalPrices[i] = prices[i] - discount;
        }

        return finalPrices;
    }


    /**
     * Computes the final prices using a monotonic stack.
     *
     * Intuition:
     * - A stack helps efficiently find the next smaller or equal price to the right.
     * - The stack maintains indices of prices in decreasing order.
     * - While processing an item, compare it with the top of the stack to apply the discount.
     *
     * Steps:
     * 1. Initialize a stack to store indices and an array `finalPrices` of the same size as `prices`.
     * 2. Traverse the array from left to right:
     *    - While the stack is not empty and the current price is less than or equal to the price at the index stored on the stack:
     *        - Pop the index from the stack and update the corresponding price with the discount.
     *    - Push the current index onto the stack.
     * 3. For remaining indices in the stack, no discount is applied.
     * 4. Return the `finalPrices` array.
     *
     * Time Complexity: O(n)
     * - Each element is pushed and popped from the stack at most once.
     * Space Complexity: O(n)
     * - The stack stores at most n indices.
     *
     * @param prices Array of item prices.
     * @return Array of final prices after applying discounts.
     */
    public int[] finalPricesUsingStack(int[] prices) {
        int n = prices.length;
        int[] finalPrices = prices.clone();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int index = stack.pop();
                finalPrices[index] -= prices[i];
            }
            stack.push(i);
        }

        return finalPrices;
    }

    public int[] usingMonotonicStack(int[] prices) {
        int n = prices.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = prices[i] - stack.peek();
            } else {
                result[i] = prices[i];
            }
            stack.push(prices[i]);
        }
        return result;
    }

}
