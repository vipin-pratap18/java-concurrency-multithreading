package com.thread.pool;

import org.apache.tomcat.jni.Thread;

public class PoolProcesorThread implements Runnable {
	
	private int id;
	
	public PoolProcesorThread(int id){
		this.id = id;
	}

	public void run() {

		System.out.println("Starting " + id);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed " + id);
	}

}
