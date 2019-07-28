package com.kmboot.multipleThread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");
		Calculation c = new Calculation();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 1000; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.add();
			});
			c.add();
			executor.execute(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.add();
			});
			executor.execute(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.add();
			});
		}
		//
		for (int i = 0; i < 1000; i++) {
			executor.execute(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.substract();
			});
			executor.execute(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.substract();
			});
			executor.execute(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c.substract();
			});
		}

		Thread.sleep(5000);
		System.err.println("!!!!!!##########");
		System.err.println("!!!!!!##########");
		System.err.println("!!!!!!##########");
		c.print();
		executor.shutdown();
	}

	private static ExecutorService executor = Executors.newFixedThreadPool(2000, new ThreadFactory() {
		private AtomicInteger count = new AtomicInteger();

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setName("T:" + count.incrementAndGet());
			thread.setDaemon(true);
			thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					System.out.println(t.getName() + e.getMessage());
				}
			});
			return thread;
		}
	});
}
