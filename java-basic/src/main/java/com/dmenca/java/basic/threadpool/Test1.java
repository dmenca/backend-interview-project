package com.dmenca.java.basic.threadpool;

import java.util.concurrent.*;

/**
 * @author caoan
 * @create 2022/3/21 10:29
 **/
public class Test1 {
    public static void main(String[] args) {

//        ExecutorService threadPoolExecutor = Executors.newScheduledThreadPool(5);
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(1,Integer.MAX_VALUE,0, TimeUnit.SECONDS,new LinkedBlockingQueue<>(10),
                r -> new Thread(r, "BatchFeatureGetConcurrent-" + (int)(Math.random()*8)),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0 ;i < 1000; i++){
            Integer x = i ;
            threadPoolExecutor.submit(()->{
                System.out.println(Thread.currentThread()+"  -  i is :" + x);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
//            System.out.println("threadPoolExecutor submit " + i);
        }
        System.out.println("end");
    }
}
