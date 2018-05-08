package com.thread.synchronize;

public class VolatileProcessor extends Thread {

    private volatile boolean shutDown = true;

    public void run(){
        while(shutDown){
            System.out.println("Hello");
            try{
                Thread.sleep(100);
            }catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

    public void shutDown(){
        shutDown = false;
    }
}
