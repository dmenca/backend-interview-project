package com.dmenca.leetcode;

/**
 * 找到数组中的第K个最大元素
 */
public class Solution215 {

    public static int findKthLargest(int nums[],int k){
        return quickSort(nums,0,nums.length-1,k);
    }

    /**
     * 通过快速排序的思想找到基准的下标是第K个大的数即可，如果基准的下标比nums.length-K小，则只需要遍历右边的序列做快速排序，否则只需要遍历左边的序列做快速排序，最终返回下标对应的元素即可
     * @param nums
     * @param low
     * @param high
     * @param k
     * @return
     */
    public static int quickSort(int nums[],int low,int high,int k){
        int i = low;
        int j = high;
        int pivot = nums[i];
        while (i<j){
            while (i<j&&nums[j]>=pivot){
                j--;
            }
            if (i<j){
                nums[i] = nums[j];
                i++;
            }
            while (i<j&&nums[i]<pivot){
                i++;
            }
            if (i<j){
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i]=pivot;
        if (i == nums.length-k){
            return nums[i];
        }
        if (i<nums.length-k){
            return quickSort(nums,i+1,high,k);
        }else{
            return quickSort(nums,low,i-1,k);
        }

    }

    public static void main(String[] args) {
        int nums[]={3,2,1,5,6,4};
        System.out.println(findKthLargest(nums,2));

        int nums2[]={3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums2,4));
    }


}
