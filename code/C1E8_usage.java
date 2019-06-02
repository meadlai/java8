package de.galperin.javase8.capitel1;

import de.galperin.javase8.Exercise;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: eugen Date: 27.10.14
 */
public class C1E8_usage implements Exercise {

	@Test
	@Override
	public void perform() {
		String[] names = { "Peter", "Paul", "Mary" };
		List<Runnable> runners = new ArrayList<>();
//		for (String name : names) {
//			runners.add(() -> System.out.println("running: " + name));
//			System.out.println("Add task p = " + p);
//		}
		Arrays.asList(names).forEach(p -> {
			runners.add(() -> System.out.println("running: " + p));
			System.out.println("Add task p = " + p);
		});
//		for (Runnable runner : runners) {
//			new Thread(runner).start();
//		}
		runners.forEach(t -> new Thread(t).start());

		runners.clear();

	}
}
