package com.dmenca.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution200 {
    public static int numIslands(char [][]grid){
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        // 将所有元素置为 false
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                visited[i][j] = false;
            }
        }
        int land =0;
        for (int i =0;i<grid.length;i++){
            for (int j =0;j<grid[i].length;j++){
                if (grid[i][j]=='1' && visited[i][j] == false){
                    Queue<String>queue=new LinkedList<String>();
                    queue.add((i)+"_"+(j));
                    while (!queue.isEmpty()){
                        String poll = queue.poll();
                        int x = Integer.valueOf(poll.split("_")[0]);
                        int y = Integer.valueOf(poll.split("_")[1]);
                        if (x<0||y<0||x>=grid.length||y>=grid[0].length){
                            continue;
                        }
                        if (visited[x][y]==true||grid[x][y]=='0'){
                            continue;
                        }
                        queue.add((x)+"_"+(y+1));
                        queue.add((x)+"_"+(y-1));
                        queue.add((x+1)+"_"+(y));
                        queue.add((x-1)+"_"+(y));
                        visited[x][y]=true;
                    }
                    land++;
                }
            }
        }
        return land;
    }

    public static void main(String[] args) {
        char[][]grid = {
           {'1','1','1','1',0},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        char[][]grid1 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
//        System.out.println(numIslands(grid));
        System.out.println(numIslands(grid1));
    };



}
