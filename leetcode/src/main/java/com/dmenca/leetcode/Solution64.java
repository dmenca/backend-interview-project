package com.dmenca.leetcode;

/**
 * 最小路径和
 */
public class Solution64 {
    /**
     * path(x,y) = Math.min(path(x-1,y), path(x,y-1)) + grid[i][j]
     * @param grid
     * @return
     */
    public int minPathSum(int [][]grid){
        int [][]paths = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                paths[i][j] = Math.min(i==0?0:paths[i-1][j],j==0?0:paths[i][j-1])+grid[i][j];
            }
        }
        return paths[grid.length-1][grid[0].length-1];
    }
}
