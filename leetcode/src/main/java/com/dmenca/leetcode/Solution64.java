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
    public static int minPathSum(int [][]grid){
        int [][]paths = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(i==0){
                    paths[i][j] = (j==0?0:paths[i][j-1])+grid[i][j];
                }else if(j==0){
                    paths[i][j] = paths[i-1][j]+grid[i][j];
                }else{
                    paths[i][j] = Math.min(paths[i-1][j],paths[i][j-1])+grid[i][j];
                }

            }
        }
        return paths[grid.length-1][grid[0].length-1];
    }

    public static void main(String[] args) {
        int [][]grid={{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(grid));
    }
}
