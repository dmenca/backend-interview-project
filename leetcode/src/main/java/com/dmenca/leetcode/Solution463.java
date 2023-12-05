package com.dmenca.leetcode;

/**
 * 岛屿的周长
 */
public class Solution463 {
    public int islandPerimeter(int[][] grid) {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    return dfs(grid,i,j);
                }
            }
        }
        return 0;
    }

    /**
     * 向四个方向深度遍历，结束条件为数组越界，则返回边长1，如果节点已经被访问过则返回0
     * 周长等于四个方向的结果之和
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public int dfs(int [][]grid,int i,int j){
        // 函数因为「坐标 (r, c) 超出网格范围、或者当前格子为海洋返回，对应一条黄色的边
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==0){
            return 1;
        }
        // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
        if(grid[i][j]==2){
            return 0;
        }
        grid[i][j]=2;
        return dfs(grid,i+1,j) + dfs(grid,i-1,j)+dfs(grid,i,j+1)+dfs(grid,i,j-1);
    }
}
