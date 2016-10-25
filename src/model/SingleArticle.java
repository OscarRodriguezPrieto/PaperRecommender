package model;

import java.util.List;

import IO.extraccion.Cita;
import IO.salida.SalidasSimple;

public class SingleArticle {

	public static final String FILE = "coefficients/ocurrences";

	public static void addOcurrencias(Cita cita, List<Cita> citas, int limit) {
		SalidasSimple.setArchivo(FILE);
		int[] res;
		Cita citado = citas.get(0);
		res = ModeloCombinatorio.getCounts(citas, cita, citado);
		if (res[ModeloCombinatorio.A1_COUNTS] > 0) {
			SalidasSimple.escribirLinea(
					cita.getId() + "\t" + citado.getId() + "\t" + res[0] + "\t" + res[1] + "\t" + res[2]);
			for (int i = 1; i < limit; i++) {
				citado = citas.get(i);
				res = ModeloCombinatorio.getCounts(citas, cita, citado);
				SalidasSimple.escribirLinea(
						cita.getId() + "\t" + citado.getId() + "\t" + res[0] + "\t" + res[1] + "\t" + res[2]);

			}
		} else 
			SalidasSimple.escribirLinea(cita.getId() + "\tNaN");
		
	}
}
