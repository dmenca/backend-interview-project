package com.dmenca.java.basic.sort;

public class BinarySort {

    /**
     * 二分法插入排序，简称二分排序，是在插入第i个元素时，对前面的0～i-1元素进行折半，先跟他们中间的那个元素比，如果小，则对前半再进行折半，否则对后半进行折半，直到left＜right，
     * 然后再把第i个元素前1位与目标位置之间的所有元素后移，再把第i个元素放在目标位置上。
     * @param arr
     */
    public static void binaryInsertionSort(int []arr){
        int n = arr.length;
        for (int i = 1; i < n ; i++){
            int left = 0;
            int right = i - 1;
            int key = arr[i];
            int j = i - 1;
            while (left<=right){
                int mid = left + (right-left)/2;
                if (arr[mid]>key){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            while (j>=left){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        binaryInsertionSort(arr);
        System.out.println("Sorted array:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
