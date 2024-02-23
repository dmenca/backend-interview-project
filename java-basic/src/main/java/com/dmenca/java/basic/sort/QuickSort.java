package com.dmenca.java.basic.sort;

public class QuickSort {
    /**
     * 在一个无序的序列中选取一个任意的基准元素pivot，利用pivot将待排序的序列分成两部分，前面部分元素均小于或等于基准元素，后面部分均大于或等于基准元素，
     * 然后采用递归的方法分别对前后两部分重复上述操作，直到将无序序列排列成有序序列
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int arr[],int low,int high){
        if (low<high){
            int pi = partition(arr,low,high);
            quickSort(arr,low,pi-1);
            quickSort(arr,pi+1,high);
        }
    }
    public static int partition(int[] arr, int low, int high) {
        int pivot  = arr[high];
        int i = low - 1;
        for (int j = low;j<high;j++){
            if (arr[j]<pivot){
                i++;
                // 将小于枢轴的元素交换到索引i的位置
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 将枢轴放置到其最终位置，即索引i+1的位置
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1; // 返回枢轴的最终位置
    }


    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int n = arr.length;
        quickSort(arr, 0, n - 1);
        System.out.println("Sorted array:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
