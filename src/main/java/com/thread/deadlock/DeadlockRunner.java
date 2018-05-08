package com.thread.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockRunner {
	
	private AccountDeadlock ac1 = new AccountDeadlock();
	private AccountDeadlock ac2 = new AccountDeadlock();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	
	private void acquireLock(Lock firstLock, Lock secondLock)throws InterruptedException
	{
		while(true){
			boolean getFirstLock = false;
			boolean getSecondLock = false;
			try{
				getFirstLock = firstLock.tryLock();
				getSecondLock = secondLock.tryLock();
			}finally{
				if(getFirstLock && getSecondLock){
					return;
				}
				if(getFirstLock){
					firstLock.unlock();
				}
				if(getSecondLock){
					secondLock.unlock();
				}
			}
		}
	}
	public void firstThread() throws InterruptedException
	{
		Random random = new Random();
		for(int i=0; i<100; i++){
			/*lock1.lock();
			lock2.lock();*/
			acquireLock(lock1, lock2);
			try{
				AccountDeadlock.transfer(ac1, ac2, random.nextInt(1000));
			}
			finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void secondThread() throws InterruptedException
	{
		Random random = new Random();
		for(int i=0; i<100; i++){
			/*lock2.lock(); //Reversing the lock acquiring order will result in the deadlock
			lock1.lock();*/
			acquireLock(lock2, lock1);
			try{
				AccountDeadlock.transfer(ac1, ac2, random.nextInt(1000));
			}
			finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void finished(){
		System.out.println(" Account1 balance " + ac1.getBalance());
		System.out.println(" Account2 balance " + ac2.getBalance());
		System.out.println(" Total balance " + (ac1.getBalance() + ac2.getBalance()));
	}

}
