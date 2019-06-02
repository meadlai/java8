package de.galperin.javase8.capitel1;

import java.io.File;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

import de.galperin.javase8.Exercise;

/**
 * User: eugen Date: 27.10.14
 */
public class C1E2_method_references implements Exercise {

	@Test
	@Override
	public void perform() {
		Arrays.asList(getChildDirsWithLambda(".")).forEach(System.out::println);
		System.out.println("---------------------------------------------");
		Arrays.asList(getChildDirsWithMethodReference(".")).forEach(System.out::println);
		//
		//
		IntStream.range(1, 10).forEach(System.out::println);
		long a = IntStream.range(0, 100).mapToLong(x -> {
			for (int i = 0; i < 100; i++) {
				System.out.print("X:" + i);
			}
			return x;
		}).sum();
		System.out.println();
		System.out.println(" ==== ");
		System.out.println("a = " + a);
	}

	private static File[] getChildDirsWithLambda(String dir) {
		File dirFile = new File(dir);
		return dirFile.listFiles(d -> d.isDirectory());
	}

	private static File[] getChildDirsWithMethodReference(String dir) {
		File dirFile = new File(dir);
		return dirFile.listFiles(File::isDirectory);
	}

}
