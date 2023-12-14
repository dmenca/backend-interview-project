package com.dmenca.java.basic.collection;

import java.util.PriorityQueue;

public class QueueTest {
    public static void main(String[] args) {
        // 优先队列 小顶堆 每个结点的值都大于或等于其左右孩子结点的值
        // PriorityQueue默认是小顶堆。要构建大顶堆，我们传递一个Comparator（比较器）来反转元素的顺序，使其按照逆序排列。
        // PriorityQueue不是线程安全的。如果你需要在多线程环境中使用堆，你可能需要考虑使用PriorityBlockingQueue。
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(3);
        minHeap.add(1);
        minHeap.add(4);
        minHeap.add(2);
        minHeap.add(5);
        System.out.println("Min heap");
        while (!minHeap.isEmpty()){
            System.out.println(minHeap.poll()+"");
        }

        // 构造大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.add(3);
        maxHeap.add(1);
        maxHeap.add(4);
        maxHeap.add(2);
        System.out.println("Max Heap (大顶堆):");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }

    }
}
