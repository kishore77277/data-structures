package com.dsa.leetcode.topics.prefixsum;

import java.util.HashMap;
import java.util.Map;

// TODO : try dry run : arr = [1, 2, 3, -3, 1, 1, 1, 4, 2, -3], k = 3
public class SubarraySumEqualsK_560 {

    public int subarraySum(int[] nums, int K) {
        // return bruteForce(nums, K);
        // return better(nums, K);
        return optimalUsingPrefixSum(nums, K);
    }

    /**
     * bruteForce: Counts the number of subarrays whose sum equals K using a brute force approach.
     *
     * <p><b>Intuition:</b></p>
     * The idea is to consider every possible subarray in the array and compute its sum.
     * If the sum equals `K`, we increment the count. This approach checks all combinations,
     * ensuring no subarray is missed.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Iterate through the array starting at each index `i`.
     * 2. For each index `i`, iterate again from `i` to the end to consider all subarrays starting from `i`.
     * 3. For each subarray, calculate its sum by iterating from the start of the subarray to its end.
     * 4. If the sum equals `K`, increment the count.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n^3) - Three nested loops are used to calculate all possible subarray sums.
     *
     * <p><b>Space Complexity:</b></p>
     * O(1) - No additional space is used apart from variables for iteration.
     *
     * @param nums Array of integers.
     * @param K Target sum to find in subarrays.
     * @return Count of subarrays whose sum equals K.
     */
    public int bruteForce(int[] nums, int K) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum == K) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * better: Counts the number of subarrays whose sum equals K using a cumulative sum approach.
     *
     * <p><b>Intuition:</b></p>
     * Instead of recalculating the sum for every subarray, use a running sum for each starting index `i`.
     * This avoids redundant calculations, making the algorithm more efficient.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Iterate through the array starting at each index `i`.
     * 2. Maintain a running sum variable `sum` initialized to 0.
     * 3. For each starting index `i`, add each element to `sum` and check if `sum` equals `K`.
     * 4. Increment the count if the condition is satisfied.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n^2) - Two nested loops are used, but the inner loop avoids recalculating sums.
     *
     * <p><b>Space Complexity:</b></p>
     * O(1) - No additional space is used apart from variables for iteration.
     *
     * @param nums Array of integers.
     * @param K Target sum to find in subarrays.
     * @return Count of subarrays whose sum equals K.
     */
    public int better(int[] nums, int K) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == K) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * optimalUsingPrefixSum: Counts the number of subarrays whose sum equals K using a prefix sum and hashmap.
     *
     * <p><b>Intuition:</b></p>
     * The prefix sum of an array up to index `i` helps in determining the sum of any subarray ending at `i`.
     * If the prefix sum at `i` minus `K` exists in a hashmap, it means there exists a subarray ending at `i`
     * whose sum equals `K`. This allows us to solve the problem in linear time.
     *
     * <p><b>Algorithm:</b></p>
     * 1. Initialize a hashmap to store prefix sums and their frequencies, starting with (0, 1).
     * 2. Iterate through the array and calculate the running prefix sum.
     * 3. Check if `(prefixSum - K)` exists in the hashmap. If it does, increment the count by its frequency.
     * 4. Update the hashmap with the current prefix sum.
     *
     * <p><b>Time Complexity:</b></p>
     * O(n) - Each element is processed once, and hashmap operations are O(1) on average.
     *
     * <p><b>Space Complexity:</b></p>
     * O(n) - The hashmap stores at most `n` prefix sums.
     *
     * @param nums Array of integers.
     * @param K Target sum to find in subarrays.
     * @return Count of subarrays whose sum equals K.
     */
    public int optimalUsingPrefixSum(int[] nums, int K) {
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();
        freqMap.put(0, 1);
        int prefixSum = 0;
        int subArrayCount = 0;
        for (int num : nums) {
            prefixSum += num;
            subArrayCount += freqMap.getOrDefault(prefixSum - K, 0);
            freqMap.put(prefixSum, freqMap.getOrDefault(prefixSum, 0) + 1);
        }
        return subArrayCount;
    }

}
