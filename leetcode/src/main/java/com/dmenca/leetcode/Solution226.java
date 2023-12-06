package com.dmenca.leetcode;
/**
 翻转二叉树
 */
public class Solution226 {

    public TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
