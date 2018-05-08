package com.thread.create;

import org.apache.tomcat.jni.Thread;

public class ImplementingRunner implements Runnable {

	public void run(){
		for(int i=1; i<=10; i++){
			System.out.println("Hello from implementing class");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
