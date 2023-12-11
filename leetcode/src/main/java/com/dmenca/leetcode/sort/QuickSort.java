package com.dmenca.leetcode.sort;

public class QuickSort {

    /**
     * 快速排序的思想
     * 1. 从数列中挑出第一个数字作为基准
     * 2. 重新排序该数列，所有元素比基准值小的摆在基准前面，所有元素比基准大的排在基准后面，在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 3 .递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     *
     * 在待排序的数列中，我们首先要找一个数字作为基准数（这只是个专用名词）。为了方便，我们一般选择第 1 个数字作为基准数（其实选择第几个并没有关系）。接下来我们需要把这个待排序的数列中小于基准数的元素移动到待排序的数列的左边，把大于基准数的元素移动到待排序的数列的右边。这时，左右两个分区的元素就相对有序了；接着把两个分区的元素分别按照上面两种方法继续对每个分区找出基准数，然后移动，直到各个分区只有一个数时为止。
     * @param arrays
     * @param low
     * @param high
     */
    public static void quickSort(int arrays[],int low,int high){
        if (low<high){
            int i = low;
            int j = high;
            // 获取基准元素
            int pivot = arrays[low];
            while (i<j){
                // 从右往左 找到比基准要小的下标
                while (i<j && arrays[j]>=pivot){
                    j--;
                }
                //
                if (i < j) {
                    arrays[i] = arrays[j];
                    i++;
                }
                // 从左往右 找到比基准要大的下标
                while (i<j && arrays[i]<pivot){
                    i++;
                }
                if (i < j) {
                    arrays[j] = arrays[i];
                    j--;
                }
            }
            arrays[i]=pivot;
            quickSort(arrays,i+1,high);
            quickSort(arrays,low,i-1);
        }
    }
    public static void swap(int arrays[],int i,int j){
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {5, 9, 1, 9, 5, 3, 7, 6, 1};
        quickSort(array,0,array.length-1);
        for (int i = 0; i < array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
}
