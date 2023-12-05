package com.dmenca.java.basic.threadpool;


/**
 * @author caoan
 * @Description:
 * @create 2022/8/11 9:26
 **/
public class DaemonThreadInterrupt {

    private Thread thread;

    public void execute(Runnable runnable){
        thread = new Thread(()->{
            Thread newThread = new Thread(runnable);
            newThread.setDaemon(true);
            newThread.start();
            try {
                newThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                newThread.stop();
//                this.stop();
            }
        });
        thread.start();

    }

    public void stop(){
        thread.interrupt();
    }


}
