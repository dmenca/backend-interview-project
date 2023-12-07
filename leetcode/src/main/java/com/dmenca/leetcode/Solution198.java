package com.dmenca.leetcode;

/**
 * 打家劫舍
 */
public class Solution198 {
    // 当前位置的偷钱等于前n-1个位置能偷到的最大的钱+当前位置的钱
    // f(n) = Max(f(n-2),f(n-3)) + val
    public int rob(int []nums){
        int maxMoney = 0;
        for(int i=0;i<nums.length;i++){
            if(i == 0 || i==1){
                continue;
            }
            if(i==2){
                nums[i] = nums[i-2] + nums[i];
            }
            nums[i] = Math.max(nums[i-2],nums[i-3]) + nums[i];
        }
        for(int i=0;i<nums.length;i++){
            if(maxMoney<nums[i]){
                maxMoney = nums[i];
            }
        }
        return maxMoney;
    }

}
