package com.dmenca.java.basic.concurrent;


public class WaitNotifyTest3 {

    private static int count = 1;
    private static Object object = new Object();

    public static void printNumber(int number){
            while (number<=100){
                synchronized (object){
                    System.out.println(number);
                    number = number + 2;
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            printNumber(1);
        });

        Thread threadB = new Thread(()->{
            printNumber(2);
        });
        threadA.start();
        threadB.start();;
    }
}
