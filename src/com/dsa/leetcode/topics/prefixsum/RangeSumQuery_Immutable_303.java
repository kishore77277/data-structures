package com.dsa.leetcode.topics.prefixsum;

public class RangeSumQuery_Immutable_303 {

    class BruteForce {
        /**
         * BruteForce: Calculates the range sum by iterating through the array each time.
         *
         * <p><b>Intuition:</b></p>
         * For each `sumRange` query, loop through the array from `left` to `right` and calculate the sum directly.
         * This approach works but can be slow if there are many queries.
         *
         * <p><b>Algorithm:</b></p>
         * 1. Store the input array in the `nums` field.
         * 2. For each `sumRange` call, iterate from `left` to `right` and calculate the sum.
         *
         * <p><b>Time Complexity:</b></p>
         * O(n) per query - Iterates through the array segment for each `sumRange` call.
         *
         * <p><b>Space Complexity:</b></p>
         * O(1) - No additional space is used apart from storing the array.
         *
         */
        class NumArray {
            private int[] nums;

            public NumArray(int[] nums) {
                this.nums = nums;
            }

            public int sumRange(int left, int right) {
                int sum = 0;
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }
                return sum;
            }
        }

    }


    class BetterUsingPrefixSum {
        /**
         * PrefixSum: Calculates the range sum using precomputed prefix sums for efficiency.
         *
         * <p><b>Intuition:</b></p>
         * If we precompute the cumulative sums (prefix sums) of the array, the sum of any subarray
         * can be obtained in constant time by subtracting two prefix sums.
         *
         * <p><b>Algorithm:</b></p>
         * 1. Precompute the prefix sum array `prefixSum` where `prefixSum[i]` is the sum of the first `i` elements.
         * 2. For a `sumRange` query, calculate the sum using:
         *    `prefixSum[right + 1] - prefixSum[left]`.
         *
         * <p><b>Time Complexity:</b></p>
         * O(1) per query - Sum is calculated using subtraction.
         * O(n) for preprocessing - To compute the prefix sums.
         *
         * <p><b>Space Complexity:</b></p>
         * O(n) - Additional space for the prefix sum array.
         *
         *
         */
        class NumArray {
            private int[] prefixSum;

            public NumArray(int[] nums) {
                prefixSum = new int[nums.length + 1];
                for (int i = 0; i < nums.length; i++) {
                    prefixSum[i + 1] = prefixSum[i] + nums[i];
                }
            }

            public int sumRange(int left, int right) {
                return prefixSum[right + 1] - prefixSum[left];
            }
        }

    }

    class OptimalUsingCache {
        /**
         * CachedSums: Precomputes all possible subarray sums for constant-time queries.
         *
         * <p><b>Intuition:</b></p>
         * By precomputing the sums of all possible subarrays, we can directly retrieve the result for any query.
         * This trades off space for faster query times.
         *
         * <p><b>Algorithm:</b></p>
         * 1. Precompute a 2D array `sumCache` where `sumCache[i][j]` is the sum of elements from index `i` to `j`.
         * 2. For a `sumRange` query, return `sumCache[left][right]`.
         *
         * <p><b>Time Complexity:</b></p>
         * O(1) per query - Sum is retrieved directly.
         * O(n^2) for preprocessing - To compute all subarray sums.
         *
         * <p><b>Space Complexity:</b></p>
         * O(n^2) - Additional space for the `sumCache` array.
         *
         *
         */
        class NumArray {
            private int[][] sumCache;

            public NumArray(int[] nums) {
                int n = nums.length;
                sumCache = new int[n][n];
                for (int i = 0; i < n; i++) {
                    int sum = 0;
                    for (int j = i; j < n; j++) {
                        sum += nums[j];
                        sumCache[i][j] = sum;
                    }
                }
            }

            public int sumRange(int left, int right) {
                return sumCache[left][right];
            }
        }

    }
}
