package com.dmenca.leetcode;

/**
 * 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n
 */
class Solution {
    /**
     * 解决思路：逆向双指针，nums1数组从m下标到最后的结尾都是空的，因此值被覆盖没有关系。
     * 用两个指针分别指向两个数组的尾部，从后往前的遍历，取两者之间的较大值放进nums1的最后面。
     * 时间复杂度O(m+n) 空间复杂度O(1)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m-1,j=n-1,k=m+n-1;
        while (i>=0&&j>=0){
            if (nums1[i]>=nums2[j]){
                nums1[k]=nums1[i];
                i--;
            }else{
                nums1[k]=nums2[j];
                j--;
            }
            k--;
        }
        while (j>=0&&k>=0){
            nums1[k]=nums2[j];
            j--;
            k--;
        }
    }

    public static void main(String[] args) {
        int nums1[]=new int[]{1,2,3,0,0,0};
        int nums2[]=new int[]{2,5,6};
        merge(nums1,3,nums2,3);
        for (int x:nums1){
            System.out.println(x);
        }

        nums1 = new int[1];
        nums2 =new int[]{1};
        merge(nums1,0,nums2,1);
        for (int x:nums1){
            System.out.println(x);
        }
    }
}
