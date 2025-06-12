package com.dsa.leetcode.topics.binarytrees;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal_103 {

    public List<List<Integer>> bruteForce(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            List<Integer> currentLevelList = new ArrayList<>(currentLevelSize);
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelList.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            result.add(currentLevelList);
        }
        // for every off index, reverse the list
        for (int i = 1; i < result.size(); i = i + 2) {
            Collections.reverse(result.get(i));
        }
        return result;
    }

    public List<List<Integer>> optimal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        boolean isForward = true;
        while (!deque.isEmpty()) {
            int currentLevelSize = deque.size();
            List<Integer> currentLevelList = new ArrayList<>(currentLevelSize);
            for (int i = 0; i < currentLevelSize; i++) {
                if (isForward) {
                    TreeNode currentNode = deque.pollFirst();
                    if (currentNode.left != null) {
                        deque.offerLast(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        deque.offerLast(currentNode.right);
                    }
                    currentLevelList.add(currentNode.val);
                } else {
                    TreeNode currentNode = deque.pollLast();
                    if (currentNode.right != null) {
                        deque.offerFirst(currentNode.right);
                    }
                    if (currentNode.left != null) {
                        deque.offerFirst(currentNode.left);
                    }
                    currentLevelList.add(currentNode.val);
                }
            }
            result.add(currentLevelList);
            isForward = !isForward;
        }
        return result;
    }

    public List<List<Integer>> usingLinkedListOnePassWithoutReversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            LinkedList<Integer> currentLevelList = new LinkedList<>();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (leftToRight) {
                    currentLevelList.offerLast(currentNode.val);
                } else {
                    currentLevelList.offerFirst(currentNode.val);
                }
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            leftToRight = !leftToRight;
            result.add(currentLevelList);
        }
        return result;
    }
}
