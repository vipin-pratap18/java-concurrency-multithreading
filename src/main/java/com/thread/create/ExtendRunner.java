package com.thread.create;

public class ExtendRunner extends Thread {
	
	public void run(){
		for(int i=1; i<=10; i++){
			System.out.println("Hello from extending class");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
