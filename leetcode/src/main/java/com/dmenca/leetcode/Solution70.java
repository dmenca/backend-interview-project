package com.dmenca.leetcode;

/**
 * 爬楼梯
 */
public class Solution70 {
    /**
     * f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public static int climbStairs(int n){
        if (n==1){
            return 1;
        }
        if (n==2){
            return 2;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }

    public static int climbStairs1(int n){
        if (n==1){
            return 1;
        }
        if (n==2){
            return 2;
        }
        int a=1,b=2,sum=0;
        for (int i=3;i<=n;i++){
            sum=a+b;
            a=b;
            b=sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
        System.out.println(climbStairs1(5));
    }
}
