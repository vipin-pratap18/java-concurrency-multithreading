package com.thread.stubs;

import com.thread.create.ExtendRunner;
import com.thread.create.ImplementingRunner;
import com.thread.deadlock.DeadlockRunner;
import com.thread.pool.PoolProcesorThread;
import com.thread.synchronize.SynchronizeWorker;
import com.thread.synchronize.SynchronizedCount;
import com.thread.synchronize.VolatileProcessor;

import java.util.Scanner;
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

    public void volatileStub(){
        VolatileProcessor processor = new VolatileProcessor();
        processor.start();

        System.out.println("Press return to shutdown");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        processor.shutDown();
    }

    public void synchronizedCountStub(){
        SynchronizedCount count = new SynchronizedCount();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<1000; i++){
                    count.increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<1000; i++){
                    count.increment();
                }
            }
        });

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        System.out.println("Count is : " + count.getCount());
    }

    public void synchronizedWorkerStub(){
        System.out.println("**** Starting ****");
        SynchronizeWorker worker = new SynchronizeWorker();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                worker.process();
            }
        });

        t1.start();
        try{
            t1.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                worker.process();
            }
        });

        t2.start();
        try{
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        double time = (double) (end - start)/1000d;

        System.out.println("List one size : " + worker.getList1().size());
        System.out.println("List Two size : " + worker.getList2().size());
        System.out.println("And running time : " + time);
    }
}
