package com.dmenca.leetcode;

import java.util.Stack;

/**
 翻转二叉树
 */
public class Solution226 {

    /**
     * 调转树的left跟right，递归的翻转子树的左节点和右节点，直到叶子节点的值为空
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        // 交换当前的节点的左子树跟右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 递归的将当前节点的左子树当作目标传入做交换
        invertTree(root.left);
        // 递归的将当前节点的右子树当作目标传入做交换
        invertTree(root.right);
        return root;
    }

    public TreeNode invertTree1(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode head = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.right = left;
            node.left = right;
            if (node.left!=null){
                stack.push(node.left);
            }
            if (node.right!=null){
                stack.push(node.right);
            }
        }
        return head;
    }
}
