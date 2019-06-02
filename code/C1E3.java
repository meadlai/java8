package de.galperin.javase8.capitel1;

import de.galperin.javase8.Exercise;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * User: eugen Date: 27.10.14
 */

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 * 
 * <br>
 * Intermediate： map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、
 * limit、 skip、 parallel、 sequential、 unordered
 * 
 * Terminal： forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、
 * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
 * 
 * Short-circuiting： anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
 *
 */
public class C1E3 implements Exercise {

	@Test
	@Override
	public void perform() {
		File[] files = list(".", "xml");
		Arrays.asList(files).forEach(System.out::println);
		//
		IntStream.range(1, 10).filter(p -> p > 5).map(w -> w + 1).forEach(System.out::println);
	}

	private static File[] list(String dir, String ext) {
		File dirFile = new File(dir);
		return dirFile.listFiles(f -> f.getName().endsWith(ext));
	}
}
