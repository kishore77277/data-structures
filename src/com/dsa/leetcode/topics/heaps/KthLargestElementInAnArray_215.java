package com.dsa.leetcode.topics.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/kth-largest-element-in-an-array/description/
public class KthLargestElementInAnArray_215 {

    public int findKthLargest(int[] nums, int k) {
//        return findKthLargestBruteForceSorting(nums, k);
//        return findKthLargestUsingMaxHeap(nums, k);
        // return findKthLargestUsingMinHeap(nums, k);
        return findKthLargestUsingMinHeapII(nums, k);

    }

    public int findKthLargestBruteForceSorting(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }

    public int findKthLargestUsingMaxHeap(int[] nums, int k) {
        int n = nums.length;
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
        }
        for (int i = 0; i < k - 1; i++) {
            maxHeap.poll();
        }
        return maxHeap.peek();
    }

    public int findKthLargestUsingMinHeap(int[] nums, int k) {
        int n = nums.length;
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public int findKthLargestUsingMinHeapII(int[] nums, int k) {
        int n = nums.length;
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++)  {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < n; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }
}
