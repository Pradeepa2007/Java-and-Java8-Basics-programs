package com.java8.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class EvenOrOddNumersJava8Streams {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		IntStream.rangeClosed(1, 10)
		.forEach(num -> {
			CompletableFuture<Integer> oddcompletableFuture = CompletableFuture.completedFuture(num)
					.thenApplyAsync(x -> {
						if(x % 2 != 0) {
							System.out.println("Thread Name :"+ Thread.currentThread().getName()+" : "+x);
						}
						return num;
					}, executorService);
			oddcompletableFuture.join();
			
			CompletableFuture<Integer> evencompletableFuture = CompletableFuture.completedFuture(num)
					.thenApplyAsync(x -> {
						if(x % 2 == 0) {
							System.out.println("Thread Name :"+ Thread.currentThread().getName()+" : "+x);
						}
						return num;
					}, executorService);
			evencompletableFuture.join();
		});
		executorService.shutdown();

	}

}
