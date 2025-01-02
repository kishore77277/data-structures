package com.dsa.leetcode.topics.stack;

// https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater_42 {

    public int trap(int[] height) {
        return trapBruteForce(height);
    }

    public int trapBruteForce(int[] height) {
        int n = height.length;
        int trappedWater = 0;
        for (int i = 0; i < n; i++) {
            int waterTrapped = Math.min(leftMax(height, i), rightMax(height, i))  - height[i];
            if (waterTrapped > 0) {
                trappedWater = trappedWater + waterTrapped;
            }
        }
        return trappedWater;
    }

    public int leftMax(int[] height, int index) {
        int leftMax = 0;
        for (int i = 0; i < index; i++) {
            if (leftMax < height[i]) {
                leftMax = height[i];
            }
        }
        return leftMax;
    }

    public int rightMax(int[] height, int index) {
        int rightMax = 0;
        for (int i = index + 1; i < height.length; i++) {
            if (rightMax < height[i]) {
                rightMax = height[i];
            }
        }
        return rightMax;
    }

    public static void main(String[] args) {
        System.out.println(new TrappingRainWater_42().trapBruteForce(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

    }
}
