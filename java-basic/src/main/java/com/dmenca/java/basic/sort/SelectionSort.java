package com.dmenca.java.basic.sort;

public class SelectionSort {

    /**
     * 选择排序
     * 在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。 以此类推，直到所有元素均排序完毕
     * @param arr
     */
    public static void selectionSort(int []arr){
        int n = arr.length;
        for (int i=0;i<n;i++){
            int minIndex = i;
            for (int j=i+1;j<n;j++){
                if (arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int [] arr = {64,25,12,22,11};
        selectionSort(arr);
        System.out.println("Sorted array:");
        for (int i :arr){
            System.out.print(i+" ");
        }
    }
}
