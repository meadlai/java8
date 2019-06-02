package de.galperin.javase8.capitel1;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

import com.mysql.cj.jdbc.MysqlDataSource;

import de.galperin.javase8.Exercise;

/**
 * User: eugen Date: 27.10.14
 */
public class C1E9_function_interface implements Exercise {

	@Test
	@Override
	public void perform() {
		Collection2<Integer> c = new ArrayList2<>();
		c.add(100);
		c.add(-5);
		CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
		c.forEachIf(set::add, e -> e > 0);
		assertEquals(1, set.size());
		assertEquals(100, set.toArray()[0]);
	}

	@Test
	public void test1() {
		String name = "";
		String name1 = "12345";
		System.out.println(validInput(name, inputStr -> inputStr.isEmpty() ? "名字不能为空" : inputStr));
		System.out.println(validInput(name1, inputStr -> inputStr.length() > 3 ? "名字过长" : inputStr));
	}

	@Test
	public void test2() {
		String name = "";
		String name1 = "12345";
		validInput2(name, inputStr -> {
			System.out.println(inputStr.isEmpty() ? "名字不能为空" : "名字正常");
		});
		validInput2(name1, inputStr -> System.out.println(inputStr.isEmpty() ? "名字不能为空" : "名字正常"));
	}

	public static void validInput2(String name, Consumer<String> function) {
		function.accept(name);
	}

	public static String validInput(String name, Function<String, String> function) {
		return function.apply(name);
	}

	public static void withResource(Consumer<Connection> consumer) {
		MysqlDataSource dataSource = new MysqlDataSource();
		Connection resource = null;
		try {
			resource = dataSource.getConnection();
			consumer.accept(resource);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void withResource(Consumer<Statement> consumer, String sql) {
		MysqlDataSource dataSource = new MysqlDataSource();
		try (Connection conn = dataSource.getConnection(); Statement st = conn.prepareStatement(sql)) {
			consumer.accept(st);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}

	@Test
	public void testResource() {
		withResource(conn -> {
			try (Statement st = conn.prepareStatement("select * from dual")) {
				ResultSet rs = st.getResultSet();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	public void testResource2() {
		withResource(st -> {
			try {
				ResultSet rs = st.getResultSet();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}, "select * from db");
	}

	@Test
	public void testPredicate() {
		printIf(Arrays.asList(1, 2, 3, 4, 5, 9), p -> p > 8);

		printIf(Arrays.asList(1, 2, 3, 4, 5, 9), p -> {
			return p % 2 == 0;
		});

		int count = sumIf(Arrays.asList(1, 2, 3, 4, 5, 9), p -> {
			return p % 2 == 0;
		});
		System.out.println("sum = " + count);
	}
	
	@Test
	public void testCollection2() {
		Collection2<String> list = new ArrayList2<>();
		list.add("max");
		list.add("mix");
		list.add("tiny");
		
		list.forEachIf(e -> {
		}, string -> string.startsWith("m"));
		//
		Collection2.staticMethod();
	}

	void printIf(List<Integer> list, Predicate<Integer> predicate) {
		list.forEach(n -> {
			if (predicate.test(n)) {
				System.out.println(n + " ");
			}
		});
		//
		list.stream().filter(predicate).forEach(System.out::println);
	}

	Integer sumIf(List<Integer> list, Predicate<Integer> predicate) {
		return list.stream().filter(predicate).mapToInt(Integer::intValue).sum();
	}
}

interface Collection2<T> extends Collection<T> {
	default void forEachIf(Consumer<T> action, Predicate<T> filter) {
		forEach(e -> {
			if (filter.test(e)) {
				action.accept(e);
			}
		});
	}

	static void staticMethod() {
		System.out.println("I am a static method!!!");
	}
}

interface Demo {
	default void defaultMethod() {
		System.out.println("I am a default method!!!");
	}
	static void staticMethod() {
		System.out.println("I am a static method!!!");
	}
}

class ArrayList2<T> extends ArrayList<T> implements Collection2<T> {
}
