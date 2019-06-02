package de.galperin.javase8.capitel1;

import de.galperin.javase8.Exercise;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: eugen Date: 27.10.14
 */
public class C1E1_lambda_usage implements Exercise {

	@Test
	@Override
	public void perform() {
		final long currentThreadId = Thread.currentThread().getId();
		String[] array = getWordsAsArray();
		// sort
		CopyOnWriteArraySet<Long> threadIds = new CopyOnWriteArraySet<>();
		Arrays.sort(array, (a, b) -> {
			threadIds.add(Thread.currentThread().getId());
			return a.compareTo(b);
		});
		assertEquals(1, threadIds.size());
		assertEquals(currentThreadId, threadIds.toArray()[0]);
		// parallelSort
		threadIds.clear();
		Arrays.parallelSort(array, (a, b) -> {
			threadIds.add(Thread.currentThread().getId());
			return a.compareTo(b);
		});
		assertTrue(threadIds.size() > 1);
		//
		Runnable sleeper = () -> {
			System.out.println("this is a function lambda");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		new Thread(sleeper).start();
		new Thread(() -> {
			System.out.println("this is a function lambda");
		}).start();
		
		Arrays.sort(array, (v1, v2) -> {
			return v1.compareTo(v2);
		});
		
		Arrays.sort(array, (v1, v2) -> v1.compareTo(v2));
	}

}
