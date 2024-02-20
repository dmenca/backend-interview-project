package com.dmenca.java.basic.concurrent;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;


public class WaitNotifyTest1 {
    /**
     * 主线程没有获取lock的锁，调用notify方法报错
     */
    public static void waitNotifyTest1(){
        Object lock = new Object();
        Thread threadA = new Thread(()->{
           synchronized (lock){
               System.out.println("get lock ");
               try {
                   Thread.sleep(1000);
                   System.out.println("调用wait");
                   lock.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("线程被唤醒");
           }
        });
        threadA.start();
        // 主线程没有获取到lock的锁则会报错
        lock.notify();
    }

    /**
     * 线程A获取到lock的锁之后调用wait方法释放锁，最后被线程Bnotify方法唤醒重新获得锁后继续执行
     */
    public static void waitNotifyTest2(){
        Object lock = new Object();
        Thread threadA = new Thread(()->{
            synchronized (lock){
                System.out.println("get lock ");
                try {
                    Thread.sleep(1000);
                    System.out.println("调用wait 前状态 "+Thread.currentThread().getState());
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程被唤醒 "+Thread.currentThread().getState());
            }
        });
        threadA.start();

        Thread threadB = new Thread(()-> {
            synchronized (lock) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
            }
        });
        threadB.start();

    }

    public static void main(String[] args) {
        //waitNotifyTest1();
        waitNotifyTest2();
    }
}
