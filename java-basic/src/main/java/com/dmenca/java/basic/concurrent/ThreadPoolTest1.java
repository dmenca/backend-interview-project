package com.dmenca.java.basic.concurrent;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest1 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>(2));
        for (int i=0;i<10;i++){
            // 由于最大的线程数是1，且抛弃策略为AbortPolicy，后面submit的线程会报错RejectedExecutionException
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
