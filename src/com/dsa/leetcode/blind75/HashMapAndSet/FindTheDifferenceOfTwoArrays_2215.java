package com.dsa.leetcode.blind75.HashMapAndSet;

import java.util.*;

// https://leetcode.com/problems/find-the-difference-of-two-arrays/description/?envType=study-plan-v2&envId=leetcode-75
public class FindTheDifferenceOfTwoArrays_2215 {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
//        return findDifferenceBruteForce(nums1, nums2);
        return findDifferenceBetter(nums1, nums2);
    }

    /**
     * findDifferenceBruteForce: Compare each element in one array with all elements in the other.
     *
     * <b>Intuition:</b>
     * Use nested loops to check if an element of one array is not present in the other.
     * Ensure uniqueness by maintaining a list and only adding if not already present.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Iterate through nums1 and for each element, check if it's in nums2.
     * 2. If not, add it to the first result list.
     * 3. Repeat the process for nums2 and check against nums1 for the second result list.
     * 4. Ensure uniqueness in the result.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n \times m) \) - Nested loop comparison.
     *
     * <b>Space Complexity:</b>
     * \( O(n + m) \) - To store the result.
     *
     * @param nums1 First input array.
     * @param nums2 Second input array.
     * @return List of two arrays with differences.
     */
    public List<List<Integer>> findDifferenceBruteForce(int[] nums1, int[] nums2) {
        List<Integer> diff1 = new ArrayList<>();
        List<Integer> diff2 = new ArrayList<>();

        for (int num : nums1) {
            boolean found = false;
            for (int n : nums2) {
                if (num == n) {
                    found = true;
                    break;
                }
            }
            if (!found && !diff1.contains(num)) {
                diff1.add(num);
            }
        }

        for (int num : nums2) {
            boolean found = false;
            for (int n : nums1) {
                if (num == n) {
                    found = true;
                    break;
                }
            }
            if (!found && !diff2.contains(num)) {
                diff2.add(num);
            }
        }

        return Arrays.asList(diff1, diff2);
    }

    /**
     * findDifferenceBetter: Use HashSet for efficient lookup and uniqueness.
     *
     * <b>Intuition:</b>
     * HashSets provide \( O(1) \) lookup time. Use two sets to store the elements of nums1 and nums2.
     * Use set operations to find differences and remove duplicates automatically.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Convert nums1 and nums2 to sets.
     * 2. Use set operations to find unique elements in nums1 and nums2.
     * 3. Convert the results back to lists.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n + m) \) - Insertions and lookups in sets.
     *
     * <b>Space Complexity:</b>
     * \( O(n + m) \) - To store the sets and result.
     *
     * @param nums1 First input array.
     * @param nums2 Second input array.
     * @return List of two arrays with differences.
     */
    public List<List<Integer>> findDifferenceBetter(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }

        // Find unique elements in nums1 not in nums2
        Set<Integer> diff1 = new HashSet<>(set1);
        diff1.removeAll(set2);

        // Find unique elements in nums2 not in nums1
        Set<Integer> diff2 = new HashSet<>(set2);
        diff2.removeAll(set1);

        return Arrays.asList(new ArrayList<>(diff1), new ArrayList<>(diff2));
    }


}
