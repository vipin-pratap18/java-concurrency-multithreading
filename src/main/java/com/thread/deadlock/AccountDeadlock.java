package com.thread.deadlock;

public class AccountDeadlock {

	private int balance = 10000;
	
	public void deposit(int amount){
		balance += amount;
	}
	
	public void withdraw(int amount){
		balance -= amount;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public static void transfer(AccountDeadlock ac1, AccountDeadlock ac2, int amount){
		ac1.withdraw(amount);
		ac2.deposit(amount);
	}
}
