package com.dsa.leetcode.blind75.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/number-of-recent-calls/description/?envType=study-plan-v2&envId=leetcode-75
public class NumberOfRecentCalls_933 {

    // bruteForce
//    class RecentCounter {
//
//        private List<Integer> recentCalls;
//        public RecentCounter() {
//            recentCalls = new ArrayList<>();
//        }
//
//        public int ping(int t) {
//            recentCalls.add(t);
//            int min = t - 3000;
//            int max = t;
//            int count = 0;
//            for (int task : recentCalls) {
//                if (task >= min && task <= max) {
//                    count++;
//                }
//            }
//            return count;
//        }
//    }


    // optimal
    class RecentCounter {

        Queue<Integer> queue;
        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            // given that everytime ping will be strictly increasing in order,
            // so we can remove the older entries if any.
            int min = t - 3000;
            while (!queue.isEmpty() && queue.peek() < min) {
                queue.poll();
            }
            queue.offer(t);
            return queue.size();
        }
    }
}
