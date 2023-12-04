package com.dmenca.leetcode;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class Solution100 {


    /**
     * 解决思路：递归的判断p的左子树是否等于q的左子树，q的右子树是否等于p的右子树
     * 当前节点的判断条件为 p，q同为null且值相等则当前节点相等递归判断叶子节点是否相等，否则返回false
     * 即节点的value是否相同
     *
     * 如果两个二叉树都为空，则两个二叉树相同。如果两个二叉树中有且只有一个为空，则两个二叉树一定不相同。
     * 如果两个二叉树都不为空，那么首先判断它们的根节点的值是否相同，若不相同则两个二叉树一定不同，若相同，再分别判断两个二叉树的左子树是否相同以及右子树是否相同。这是一个递归的过程，因此可以使用深度优先搜索，递归地判断两个二叉树是否相同。
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null&&q==null){
            return true;
        }
        if (p==null&&q!=null){
            return false;
        }
        if (p!=null&&q==null){
            return false;
        }
        if (p.val==q.val){
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }else{
            return false;
        }
    }
}
