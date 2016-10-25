package model;

import java.util.List;

import IO.extraccion.Cita;
import IO.salida.Salidas;

public class ProcesarOcurrencias {
	private static final String FILE = "coefficients/res.cvs";

	public static void addOcurrencias(List<Cita> citas) {
		Salidas.setArchivo(FILE, (int) Math.pow(citas.size() / 50, 2));
		final int N = citas.size();
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				Salidas.escribirLinea(citas.get(i).getId() + "\t" + citas.get(j).getId() + "\t"
						+ ModeloCombinatorio.getCoeficientePara(citas, i, j));
			}
		}
	}
}
