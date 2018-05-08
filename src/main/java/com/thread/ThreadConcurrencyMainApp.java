package com.thread;

import com.thread.stubs.ThreadStub;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadConcurrencyMainApp {

	public static void main(String[] args) {

		ThreadStub stub = new ThreadStub();
		//stub.threadCreateStub();
        //stub.deadLockStub();
        //stub.threadPoolStub();
        //stub.volatileStub();
        //stub.synchronizedCountStub();
        stub.synchronizedWorkerStub();

	}
}
