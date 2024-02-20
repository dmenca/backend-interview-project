package com.dmenca.java.basic.concurrent;


public class WaitNotifyTest2 {


    public static void main(String[] args) {
        // 一个线程打印单数
        // 一个线程打印双数
        // 从1打印到100
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread threadA = new Thread(()->{
            for (int i =1;i<=100;i=i+2){
                synchronized (lock1){
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i);
                synchronized (lock2){
                    System.out.println("lock2 notify");
                    lock2.notify();
                }
            }
        });

        Thread threadB = new Thread(()->{
            for (int i =2;i<=100;i=i+2){
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("threadB sync lock2");
                synchronized (lock2){
                    try {
                        lock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i);
                synchronized (lock1){
                    lock1.notify();
                }
            }
        });
        threadA.start();
        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (lock1){
            lock1.notify();
        }
    }
}
