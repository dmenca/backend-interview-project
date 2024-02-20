package com.dmenca.java.basic.concurrent;

public class ThreadLocalTest1 {
    public static void main(String[] args) {
        ThreadLocal<Integer>threadLocal = new ThreadLocal<>();
        Thread threadA = new Thread(()->{
            threadLocal.set(1);
            System.out.println(threadLocal.get());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());
        });

        Thread threadB = new Thread(()->{
            threadLocal.set(2);
            System.out.println(threadLocal.get());
        });
        threadA.start();
        threadB.start();
    }
}
