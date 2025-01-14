package com.dsa.leetcode.blind75.TwoPointers;

// https://leetcode.com/problems/container-with-most-water/description/?envType=study-plan-v2&envId=leetcode-75
public class ContainerWithMostWater_11 {

    public int maxArea(int[] height) {
        // return bruteForce(height);
        return usingTwoPointers(height);
    }

    public int bruteForce(int[] height) {
        int n = height.length;
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int length = Math.min(height[i], height[j]);
                int width = j - i;
                int currentArea = length * width;
                maxArea = Math.max(currentArea, maxArea);
            }
        }
        return maxArea;
    }

    public int usingTwoPointers(int[] height) {
        int n = height.length;
        int maxArea = 0;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int length = Math.min(height[left], height[right]);
            int width = right - left;
            int currentArea = length * width;
            maxArea = Math.max(currentArea, maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
