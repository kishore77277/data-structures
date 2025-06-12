package com.dsa.leetcode.topics.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderSuccessor {

    public TreeNode levelOrderSuccessor(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
            // first adding the children then check for below condition
            // why : think like 3 nodes, with 1, 2, 3 and your key is 1
            // answer should be 2, but you have not added into queue
            // so, first add, then check
            if (currentNode.val == key) {
                break;
            }
        }
        return queue.peek(); // returns null if queue is empty
    }
}
