package com.dmenca.leetcode;
/**
 判断二叉树是否轴对称
 */

public class Solution101 {
    public boolean isSymmetric(TreeNode root){
        if(root == null){
            return true;
        }
        // 判断左子树、右子树是否对称
        return isSymmetric(root.left,root.right);
    }

    /**
     * 遇到树的问题首先想到就是递归，判断两个树是否轴对称，需要判断递归的判断l1左子树与l2右子树是否相等,l1的右子树跟l2的左子树是否相等
     * 相等的条件
     * 1。是否都为空，则相等
     * 2。两者只有一个为空，则不相等
     * 3。两者的val值不相等则不相等
     * @param l1
     * @param l2
     * @return
     */
    public boolean isSymmetric(TreeNode l1,TreeNode l2){
        // 左节点和右节点都为空 则相等
        if(l1==null && l2 == null){
            return true;
        }
        // 左节点和右节点只有一个为空 则不相等
        if((l1 == null && l2!=null) || (l1!=null && l2 == null)){
            return false;
        }
        // 左节点和右节点的值不同 则不相等
        if (l1.val != l2.val){
            return false;
        }
        // 递归循环判断左子树与右子树是否相等、以及右子树和左子树是否相等
        return isSymmetric(l1.left,l2.right) && isSymmetric(l1.right,l2.left);
    }
}
