package test;

import model.ModeloCombinatorio;

import org.junit.Test;

public class testBestCoeficientesComparation {

	@Test
	public void test() {
		System.out.println(ModeloCombinatorio.getPValor(new int[] { 1, 2, 2 },
				20997));

	}
}
