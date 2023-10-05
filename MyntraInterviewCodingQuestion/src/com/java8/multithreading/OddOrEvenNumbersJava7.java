package com.java8.multithreading;

public class OddOrEvenNumbersJava7 implements Runnable {
	
	static int count = 1;
	Object object;
	
	public OddOrEvenNumbersJava7(Object object) {
		this.object = object;
	}

	public static void main(String[] args) {
		Object lock = new Object();
		Runnable runnable = new OddOrEvenNumbersJava7(lock);
		Runnable runnable1 = new OddOrEvenNumbersJava7(lock);
		new Thread(runnable, "even").start();
		new Thread(runnable1, "odd").start();

	}

	@Override
	public void run() {
		while(count <= 100) {
			if(count % 2 == 0 && Thread.currentThread().getName().equals("even")) {
				synchronized (object) {
					System.out.println("Thread Nmae :"+Thread.currentThread().getName()+" value :"+count);
					count++;
					try {
						object.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
				}
			}
			if(count % 2 != 0 && Thread.currentThread().getName().equals("odd")) {
				synchronized (object) {
					System.out.println("Thread Nmae :"+Thread.currentThread().getName()+" value :"+count);
					count++;
					object.notify();
				}
			}
		}
		
	}

}
