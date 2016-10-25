package test;

import java.io.IOException;

import org.junit.Test;

import IO.extraccion.ExtraerMejoresSimilitudes;
import IO.salida.SalidasSimple;

public class TestSimilitudRanking {

	@Test
	public void test() throws IOException {

		double t1 = System.currentTimeMillis();
		try {
			ExtraerMejoresSimilitudes.paresSusceptibles(0.1, 1);
		} catch (Exception e) {
			e.printStackTrace();
			SalidasSimple.close();
		}
		double t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);
	}

}
