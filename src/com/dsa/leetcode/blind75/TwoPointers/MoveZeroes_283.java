package com.dsa.leetcode.blind75.TwoPointers;

// https://leetcode.com/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75
public class MoveZeroes_283 {

    public void moveZeroes(int[] nums) {
        // bruteForceUsingExtraSpace(nums);
        optimalUsingTwoPointers(nums);
    }

    public void bruteForceUsingExtraSpace(int[] nums) {
        int n = nums.length;
        int[] zeroesLast = new int[n];
        int writeIndex = 0;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            zeroesLast[writeIndex++] = num;
        }
        for (int i = 0; i < writeIndex; i++) {
            nums[i] = zeroesLast[i];
        }
        while (writeIndex < n) {
            nums[writeIndex++] = 0;
        }
    }

    public void optimalUsingTwoPointers(int[] nums) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] == 0) {
                continue;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
        }
    }
}
