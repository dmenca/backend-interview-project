package com.dmenca.java.basic.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(3);
        // 创建三个线程并启动
        Thread worker1 = new Thread(new Worker(latch, "Worker 1"));
        Thread worker2 = new Thread(new Worker(latch, "Worker 2"));
        Thread worker3 = new Thread(new Worker(latch, "Worker 3"));

        worker1.start();
        worker2.start();
        worker3.start();

        // 主线程等待所有工作线程完成任务
        latch.await();

        // 所有工作线程完成任务后，主线程继续执行
        System.out.println("All workers have finished their tasks. Main thread resumes.");
    }

    static class Worker implements Runnable {
        private final CountDownLatch latch;
        private final String name;

        public Worker(CountDownLatch latch, String name) {
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " is working...");
                Thread.sleep(2000); // 模拟工作时间
                System.out.println(name + " has finished the task.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 任务完成时，调用 countDown() 方法来减少 CountDownLatch 的计数器
                latch.countDown();
            }
        }
    }
}
