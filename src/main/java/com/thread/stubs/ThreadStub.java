package com.thread.stubs;

import com.thread.create.ExtendRunner;
import com.thread.create.ImplementingRunner;
import com.thread.deadlock.DeadlockRunner;
import com.thread.pool.PoolProcesorThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadStub {

    public void threadCreateStub() {
        Thread t1 = new Thread(new Runnable() {

            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Hello from Anonymous");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        ExtendRunner runner = new ExtendRunner();


        ImplementingRunner implRunner = new ImplementingRunner();
        Thread t2 = new Thread(implRunner);
        t1.start();
        runner.start();
        t2.start();
        try {
            t1.join();
            runner.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deadLockStub(){
        final DeadlockRunner runner = new DeadlockRunner();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runner.finished();
    }


    public void threadPoolStub(){
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i = 1; i<=5; i++){
            executor.submit(new PoolProcesorThread(i));
        }
        System.out.println("All tasks are submitted");
        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks are completed");
    }
}
