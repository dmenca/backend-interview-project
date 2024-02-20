package com.dmenca.java.basic.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在这个示例中，Producer 类负责生产数据，Consumer 类负责消费数据。队列 queue 是一个共享的有界队列，当队列满时生产者线程需要等待，当队列空时消费者线程需要等待。
 * Producer 和 Consumer 线程都需要获取 ReentrantLock 对象的锁，在队列满或空时分别调用 notFull.await() 和 notEmpty.await() 方法进入等待状态。
 * 当生产者生产了一个数据时，会调用 notEmpty.signalAll() 方法唤醒消费者线程，告诉它们可以消费数据了。当消费者消费了一个数据时，会调用 notFull.signalAll() 方法唤醒生产者线程，告诉它们可以继续生产数据。
 */
public class ProducerConsumerExample {
    private static final int CAPACITY = 5;
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final ReentrantLock lock = new ReentrantLock();
    // 未满条件
    private static final Condition notFull = lock.newCondition();
    // 未空条件
    private static final Condition notEmpty = lock.newCondition();

    static class Producer implements Runnable{

        @Override
        public void run() {
            while (true){
                try{
                    lock.lock();
                    while (queue.size() == CAPACITY){
                        // 线程池满了需要等待 不需要再生产
                        notFull.await();
                    }
                    int value = (int)(Math.random()*100);
                    queue.offer(value);
                    System.out.println("Produced:"+value);
                    // 唤醒消费者线程
                    notEmpty.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            while (true){
                try{
                    lock.lock();
                    while (queue.isEmpty()){
                        notEmpty.await();
                    }
                    int value = queue.poll();
                    System.out.println("Consumed:" + value);
                    // 唤醒消费者线程
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());
        producerThread.start();
        consumerThread.start();
    }

}
