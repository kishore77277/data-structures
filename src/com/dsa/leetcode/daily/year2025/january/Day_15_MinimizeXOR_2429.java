package com.dsa.leetcode.daily.year2025.january;

// https://leetcode.com/problems/minimize-xor/description/?envType=daily-question&envId=2025-01-15
public class Day_15_MinimizeXOR_2429 {

    /**
     * 1. we must use num2 #set bits
     * 2. count them.
     * 3. our aim is to use all the set bits
     * 4. we need to perform xor of the target number and num1
     *      0 ^ 1 = 1 ^ 0 = 1
     * 5. and moreover we need a minimum number
     * 6. so we first use all MSBs, wherever num1 has set bits, I will make my result also there set.
     * 7. if my count is still there, I will set from the right this time (LSBs)
     */
    public int minimizeXor(int num1, int num2) {
        int setBitsCount = Integer.bitCount(num2);
        int result = 0;
        for (int i = 31; i >= 0 && setBitsCount > 0; i--) {
            if ((num1 & (1 << i)) > 0) {
                result = result | (1 << i);
                setBitsCount--;
            }

        }
        for (int i = 0; i <= 31 && setBitsCount > 0; i++) {
            if ((num1 & (1 << i)) == 0) {
                result = result | (1 << i);
                setBitsCount--;
            }
        }
        return result;
    }

}
