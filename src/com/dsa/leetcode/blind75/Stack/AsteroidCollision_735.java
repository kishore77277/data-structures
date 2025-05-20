package com.dsa.leetcode.blind75.Stack;

import java.util.Stack;

// https://leetcode.com/problems/asteroid-collision/description/?envType=study-plan-v2&envId=leetcode-75
public class AsteroidCollision_735 {

    public int[] asteroidCollision(int[] asteroids) {
        return asteroidCollisionOptimal(asteroids);
    }

    /**
     * asteroidCollisionOptimal: Use a stack to resolve asteroid collisions efficiently.
     *
     * <b>Intuition:</b>
     * A stack can simulate the process of resolving asteroid collisions.
     *
     * <b>Algorithm:</b>
     * <pre>
     * 1. Traverse the asteroids array.
     * 2. Push right-moving asteroids onto the stack.
     * 3. When encountering a left-moving asteroid:
     *    - Pop from the stack to simulate collisions.
     *    - Only add the left-moving asteroid if it survives all collisions.
     * </pre>
     *
     * <b>Time Complexity:</b>
     * \( O(n) \) - Single traversal of the array.
     *
     * <b>Space Complexity:</b>
     * \( O(n) \) - Stack space for storing active asteroids.
     *
     * @param asteroids Array of integers representing asteroids.
     * @return Array after resolving all collisions.
     */
    public int[] asteroidCollisionOptimal(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                stack.push(asteroid);
            } else {
                boolean isExploded = false;
                while (!stack.isEmpty() && stack.peek() > 0) {
                    int top = stack.peek();
                    if (top < Math.abs(asteroid)) {
                        stack.pop();
                    } else if (top > Math.abs(asteroid)) {
                        isExploded = true;
                        break;
                    } else {
                        stack.pop();
                        isExploded = true;
                        break;
                    }
                }
                if (!isExploded) {
                    stack.push(asteroid);
                }
            }
        }
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

}
