package test;

import org.junit.Test;

import IO.salida.SalidasSimple;
import model.ObtenerCoeficientes;

public class TestCoeficientes {

	@Test
	public void test() {
		try {
			ObtenerCoeficientes.getCoeficientes(1);
		} catch (Exception e) {
			e.printStackTrace();
			SalidasSimple.close();
		}
	}
}
