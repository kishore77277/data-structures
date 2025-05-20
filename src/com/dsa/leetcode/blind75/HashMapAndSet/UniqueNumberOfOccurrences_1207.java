package com.dsa.leetcode.blind75.HashMapAndSet;


import java.util.*;

// https://leetcode.com/problems/unique-number-of-occurrences/description/?envType=study-plan-v2&envId=leetcode-75
public class UniqueNumberOfOccurrences_1207 {

    public boolean uniqueOccurrences(int[] arr) {
//        return uniqueOccurrencesBruteForce(arr);
        return uniqueOccurrencesBetter(arr);
    }


    /**
     * uniqueOccurrencesBruteForce: Check occurrences manually.
     *
     * <b>Intuition:</b>
     * Count the frequency of each element, then compare each frequency with others to ensure uniqueness.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Count occurrences of each element using a loop.
     * 2. Store frequencies in a list.
     * 3. Compare all frequencies in a nested loop to check uniqueness.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n^2) \) - Nested comparison of frequencies.
     *
     * <b>Space Complexity:</b>
     * \( O(n) \) - To store frequency counts.
     *
     * @param arr Input array.
     * @return True if all frequencies are unique, false otherwise.
     */
    public boolean uniqueOccurrencesBruteForce(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        List<Integer> frequencies = new ArrayList<>(freqMap.values());
        for (int i = 0; i < frequencies.size(); i++) {
            for (int j = i + 1; j < frequencies.size(); j++) {
                if (frequencies.get(i).equals(frequencies.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * uniqueOccurrencesBetter: Use HashSet for frequency uniqueness.
     *
     * <b>Intuition:</b>
     * Use a map to count occurrences and a set to track unique frequencies.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Count occurrences of each element using a map.
     * 2. Add all frequencies to a set.
     * 3. If the size of the set matches the size of the frequency map, return true.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n) \) - Single traversal for counting and frequency checks.
     *
     * <b>Space Complexity:</b>
     * \( O(n) \) - To store frequency counts and set.
     *
     * @param arr Input array.
     * @return True if all frequencies are unique, false otherwise.
     */
    public boolean uniqueOccurrencesBetter(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        Set<Integer> uniqueFrequencies = new HashSet<>(freqMap.values());
        return freqMap.size() == uniqueFrequencies.size();
    }

}
