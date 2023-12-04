package com.dmenca.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 *
 * 输入：root = [1,null,2]
 * 输出：2
 */
public class Solution104 {



    public int maxDepth(TreeNode root) {
        return depth(root,1);
    }

    public int depth(TreeNode treeNode,int depth){
        if (treeNode == null){
            return depth-1;
        }
        return Math.max(depth(treeNode.left,depth+1),depth(treeNode.right,depth+1));
    }

    /**
     * 深度优先遍历
     * 左子树和右子数的最大深度为l，r那么该二叉树的最大深度为max（l,r）+1
     * 而左子树和右子树的最大深度又可以以同样的方式进行计算。因此我们可以用「深度优先搜索」的方法来计算二叉树的最大深度。具体而言，在计算当前二叉树的最大深度时，可以先递归计算出其左子树和右子树的最大深度，然后在 O(1)
     * 时间内计算出当前二叉树的最大深度。递归在访问到空节点时退出。
     * @param root
     * @return
     */

    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth1(root.left);
            int rightHeight = maxDepth1(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * 广度优先遍历
     * 用一个队列来存放当前层的所有节点，每次扩展下一层的时候，需要将当前层的所有节点全部取出来进行扩展，
     * 这样能保证每次扩展完的时候队列里存放的时当前层的所有节点。即一层一层的去扩展，最后用一个ans变量来维护扩展的次数，
     * 该二叉树的最大深度为ans
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans=0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size>0){
                TreeNode treeNode = queue.poll();
                if (treeNode.left!=null){
                    queue.offer(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.offer(treeNode.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

}
