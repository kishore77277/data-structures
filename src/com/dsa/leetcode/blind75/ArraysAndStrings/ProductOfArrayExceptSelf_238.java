package com.dsa.leetcode.blind75.ArraysAndStrings;

// https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=leetcode-75
public class ProductOfArrayExceptSelf_238 {

    public int[] productExceptSelf(int[] nums) {
        // return bruteForce(nums);
        // return betterUsingPrefixSum(nums);
        // return better2UsingPrefixSum(nums);
        return optimal(nums);
    }

    public int[] bruteForce(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                product = product * nums[j];
            }
            result[i] = product;
        }
        return result;
    }

    public int[] betterUsingPrefixSum(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = prefix[i] * suffix[i];
        }
        return result;
    }

    public int[] better2UsingPrefixSum(int[] nums) {
        int n = nums.length;
        int[] suffix = new int[n];
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        int prefix = 1;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = prefix * suffix[i];
            prefix = prefix * nums[i];
        }
        return result;
    }

    public int[] optimal(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        result[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            result[i] = result[i + 1] * nums[i + 1];
        }
        int prefix = 1;
        for (int i = 0; i < n; i++) {
            result[i] = result[i] * prefix;
            prefix = prefix * nums[i];
        }
        return result;
    }

    // using division operator
    public int[] basedOnZeroCount(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int zeroCount = 0;
        int totalProduct = 1;
        // find zero count and total product in one pass
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else {
                totalProduct = totalProduct * num;
            }
        }
        // if zero count == 0, then only it will have the number, others all are zero
        if (zeroCount == 1) {
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    result[i] = totalProduct;
                    return result;
                }
            }
        }
        // if zero count >= 2, then all will be zero
        // because for every element either left will become 0 or right will become 0
        if (zeroCount >= 2) {
            return result;
        }
        // if no zeroes present use divide operator
        for (int i = 0; i < n; i++) {
            result[i] = totalProduct / nums[i];
        }
        return result;
    }
}
