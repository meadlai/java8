package com.kmboot.multipleThread;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Calculation {
	private Integer total = 0;
	private BigDecimal aDouble = BigDecimal.ZERO;
	private AtomicInteger aint = new AtomicInteger();
	private AtomicReference<BigDecimal> abd = new AtomicReference<>();
	private AtomicReference<BigDecimal> abd2 = new AtomicReference<>();
	private AtomicReference<Integer> arint = new AtomicReference<>();
	private AtomicReference<Integer> arint2 = new AtomicReference<>();

	public Calculation() {
		abd.set(BigDecimal.ZERO);
		abd2.set(BigDecimal.ZERO);
		arint.set(0);
		arint2.set(0);
	}

	public void add() {
		System.out.println("1total = " + total);
		System.out.println("1aDouble = " + aDouble.toPlainString());

		System.out.println("1aint = " + aint.get());

		System.out.println("1abd = " + abd.get().toPlainString());
		System.out.println("1abd2 = " + abd.get().toPlainString());

		System.out.println("1arint = " + arint.get());
		System.out.println("1arint2 = " + arint.get());

		synchronized (total) {
			total++;
		}
		synchronized (aDouble) {
			aDouble = aDouble.add(BigDecimal.ONE);
		}
		aint.getAndIncrement();

		abd.accumulateAndGet(BigDecimal.ONE, (x, y) -> x.add(y));
		synchronized (arint2.get()) {
			abd2.getAndSet(abd2.get().add(BigDecimal.ONE));
		}

		arint.accumulateAndGet(1, (x, y) -> {
			return x + y;
		});
		synchronized (arint2.get()) {
			arint2.getAndSet(arint2.get() + 1);
		}

		System.out.println("##########");
		System.out.println("2total = " + total);
		System.out.println("2aDouble = " + aDouble.toPlainString());

		System.out.println("2aint = " + aint.get());

		System.out.println("2abd = " + abd.get().toPlainString());
		System.out.println("2abd2 = " + abd2.get().toPlainString());

		System.out.println("2arint = " + arint.get());
		System.out.println("2arint2 = " + arint2.get());
		System.out.println("##########");

	}

	public void substract() {

		System.out.println("1total = " + total);
		System.out.println("1aDouble = " + aDouble.toPlainString());

		System.out.println("1aint = " + aint.get());

		System.out.println("1abd = " + abd.get().toPlainString());
		System.out.println("1abd2 = " + abd.get().toPlainString());

		System.out.println("1arint = " + arint.get());
		System.out.println("1arint2 = " + arint.get());
		synchronized (total) {
			total--;
		}
		synchronized (aDouble) {
			aDouble = aDouble.subtract(BigDecimal.ONE);
		}
		aint.getAndDecrement();

		abd.accumulateAndGet(BigDecimal.ONE, (x, y) -> x.subtract(y));
		synchronized (abd2.get()) {
			abd2.getAndSet(abd2.get().subtract(BigDecimal.ONE));
		}

		arint.accumulateAndGet(1, (x, y) -> {
			return x - y;
		});
		synchronized (arint2.get()) {
			arint2.getAndSet(arint2.get() - 1);
		}
		System.out.println("##########");
		System.out.println("2total = " + total);
		System.out.println("2BigDecimal = " + aDouble.toPlainString());

		System.out.println("2aint = " + aint.get());

		System.out.println("2abd = " + abd.get().toPlainString());
		System.out.println("2abd2 = " + abd2.get().toPlainString());

		System.out.println("2arint = " + arint.get());
		System.out.println("2arint2 = " + arint2.get());
		System.out.println("##########");

	}

	public void print() {
		System.err.println("##########");
		System.err.println("final int total = " + total);
		System.err.println("final BigDecimal = " + aDouble.toPlainString());

		System.err.println("final aint AtomicInteger = " + aint.get());

		System.err.println("final abd AtomicReference<BigDecimal>= " + abd.get().toPlainString());
		System.err.println("final abd2 AtomicReference<BigDecimal>= " + abd2.get().toPlainString());

		System.err.println("final arint AtomicReference<Integer> = " + arint.get());
		System.err.println("final arint2 AtomicReference<Integer>= " + arint2.get());
		System.err.println("##########");
	}

//	private int total = 0;
//	private BigDecimal aDouble = BigDecimal.ZERO;
//	private AtomicInteger aint = new AtomicInteger();
//	private AtomicReference<BigDecimal> abd = new AtomicReference<>();
//	private AtomicReference<BigDecimal> abd2 = new AtomicReference<>();
//	private AtomicReference<Integer> arint = new AtomicReference<>();
//	private AtomicReference<Integer> arint2 = new AtomicReference<>();

}
