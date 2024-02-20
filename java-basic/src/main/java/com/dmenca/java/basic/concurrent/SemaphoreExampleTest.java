package com.dmenca.java.basic.concurrent;
import java.util.concurrent.Semaphore;
public class SemaphoreExampleTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker(semaphore,i));
            thread.start();
        }
    }
    static class Worker implements Runnable{
        private Semaphore semaphore;
        private int id;
        public Worker(Semaphore semaphore,int id){
            this.semaphore = semaphore;
            this.id = id;
        }
        @Override
        public void run() {
            // 获取许可证
            try {
                semaphore.acquire();
                System.out.println("Worker "+ id+" is working");
                Thread.sleep(2000);
                System.out.println("Worker "+ id+" finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 释放许可证
                semaphore.release();
            }
        }
    }
}
