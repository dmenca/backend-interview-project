package com.dmenca.leetcode;

/**
 *
 * 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 */
public class Solution27 {

    /**
     * 从左向右遍历数组，当我们发现元素跟val相等时，可以将最后的元素复制给当前元素。
     * 使用双指针，两个指针分别指向数组的首和尾，两个指针向中间遍历该序列。
     * 当左指针left指向的元素等于val时，则使用右指针right指向的元素复制给左指针left位置的值。然后右指针向左移动一位，如果复制过的元素
     * 也等于val，则可以继续将右指针指向的元素复制过来。左指针left指向val的元素的位置将会被覆盖。直到左指针的元素不等于val位置。
     * 当左指针和右指针重合时，则遍历完了所有元素
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement1(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left<right){
            if (nums[left] == val){
                nums[left] = nums[right-1];
                right--;
            }else{
                left++;
            }
        }
        return left;
    }
    public static int removeElement(int[] nums, int val) {
        int count=0;
        // 计算数量
        for (int i =0;i<nums.length;i++){
            if (val == nums[i]){
                count++;
            }
        }
        int i=0,cnt=0,j=nums.length-1;
        while (cnt<count&&i<nums.length&&j>=0){
            if (nums[i]==val){
                while (nums[j]==val&&j>0){
                    j--;
                }
                nums[i]=nums[j];
                j--;
                cnt++;
            }
            i++;
        }
        return nums.length-count;
    }

    public static void main(String[] args) {
        int nums[]=new int[]{1};
        int count = removeElement(nums,1);
        System.out.println("count:"+count);
        for (int x:nums){
            System.out.println(x);
        }
    }
}
