package com.java8.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class OddOrEvenNumberFunctionInterface {
	
	/* This program used pure java8 streams with the @functionalInterface */
	
	private static Object object = new Object();
	
	// Here IntPredicate is the @FunctionalInterface
	private static IntPredicate evenCondition = e -> e % 2 == 0;
	private static IntPredicate oddCondition = e -> e % 2 != 0;
	
	
	public static void printResult(IntPredicate intPredicate) {
		IntStream.rangeClosed(1, 10)
		.filter(intPredicate)
		.forEach(OddOrEvenNumberFunctionInterface :: excute);
		
	}
	
	public static void excute(int i)  {
		synchronized (object) {
			System.out.println("Thread Name :"+ Thread.currentThread().getName()+" : "+i);
			object.notify();
			try {
				object.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		CompletableFuture.runAsync(() -> OddOrEvenNumberFunctionInterface.printResult(evenCondition));
		CompletableFuture.runAsync(() -> OddOrEvenNumberFunctionInterface.printResult(oddCondition));
		Thread.sleep(1000);

	}

}
