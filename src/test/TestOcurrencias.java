package test;

import java.io.IOException;
import java.util.List;

import IO.extraccion.Cita;
import IO.extraccion.ExtractorTotal;
import IO.salida.SalidasSimple;
import junit.framework.TestCase;
import model.SingleArticle;

public class TestOcurrencias extends TestCase {

	public void test() throws NumberFormatException, IOException {
		List<Cita> citas = ExtractorTotal.extraerCitas();
		System.out.println(citas.size());
		double t1 = System.currentTimeMillis();
		SalidasSimple.i = 19022;
		for (int i = 19022; i < 19023; i++) {
			System.out.println(i);
			SingleArticle.addOcurrencias(citas.get(i), citas, i);
		}
		SalidasSimple.close();
		double t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);

		/*
		 * for (int i : res) System.out.println(i);
		 */
	}
}
