package com.dmenca.java.basic.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private static final int NUM_THREADS = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, new BarrierAction());

    public static void main(String[] args) {
        // 创建并启动多个线程
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new Worker());
            thread.start();
        }
    }

    static class Worker implements Runnable {
        @Override
        public void run() {
            try {
                // 模拟线程工作
                System.out.println(Thread.currentThread().getName() + " is working...");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " has finished its work and waiting at the barrier.");
                barrier.await(); // 等待其他线程
                System.out.println(Thread.currentThread().getName() + " resumes execution after barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class BarrierAction implements Runnable {
        @Override
        public void run() {
            // 当所有线程都到达 barrier 时执行的动作
            System.out.println("All threads have reached the barrier. Performing the barrier action.");
        }
    }
}
