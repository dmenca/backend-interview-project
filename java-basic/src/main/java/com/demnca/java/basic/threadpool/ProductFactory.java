package com.demnca.java.basic.threadpool;

import java.net.URLConnection;
import java.util.HashMap;

/**
 * @author caoan
 * @create 2022/3/22 23:42
 **/
public class ProductFactory {
    private int maxSize = 20;
    private int currentSize = 0;

    public synchronized void product(){
        if (currentSize > maxSize){
            System.out.println("车间产品达到最大值，等到消费...");
            notifyAll();
        }else{
            currentSize++;
            System.out.println(Thread.currentThread().getName()+" 生成一个产品,当前产品数量 : "+ currentSize);
        }
    }

    public synchronized void consume(){
        if (currentSize==0){
            System.out.println("车间产品全部消费完毕，等待生产");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            currentSize -- ;
            System.out.println(Thread.currentThread().getName()+" 消费一个产品,当前产品数量 : "+ currentSize);
        }
    }
}
