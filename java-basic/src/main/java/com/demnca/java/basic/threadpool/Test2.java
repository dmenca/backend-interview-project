package com.demnca.java.basic.threadpool;

/**
 * @author caoan
 * @Description:
 * @create 2022/8/11 16:01
 **/
public class Test2 {
    public static void main(String[] args) {
        DaemonThreadInterrupt daemonThreadInterrupt = new DaemonThreadInterrupt();
        daemonThreadInterrupt.execute(()->{
            while (true){
                System.out.println("ddddddd");
            }
        });
        daemonThreadInterrupt.stop();
        while (true){
            System.out.println("gggg");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
