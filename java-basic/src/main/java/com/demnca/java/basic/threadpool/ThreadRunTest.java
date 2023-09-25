package com.demnca.java.basic.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author caoan
 * @create 2022/3/22 17:48
 **/
public class ThreadRunTest {
    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    static class Thread1 extends Thread{
        @Override
        public void run(){
            localVariable.set("xxxx");
            System.out.println(Thread.currentThread().getName()+" extends thread test");
        }
    }

    static class RunnableThread2 implements Runnable{
        @Override
        public void run(){
            localVariable.set("sss");
            String value = localVariable.get();
            System.out.println(Thread.currentThread().getName()+" implements runnable thread test "+value);

        }
    }

    static class CallableThread3 implements Callable<String>{
        @Override
        public String call() throws Exception {
            String value = localVariable.get();
            System.out.println(Thread.currentThread().getName()+" implements callable thread test"+value);
            Thread.sleep(10000);
            return "execute OK";
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread1();
        thread1.start();
        Thread thread2 = new Thread(new RunnableThread2());
        thread2.start();
        FutureTask<String> future = new FutureTask<String>(new CallableThread3());
        Thread thread3 = new Thread(future);
        thread3.start();
        try {
            String s = future.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
