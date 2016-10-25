package IO.extraccion;

import java.io.BufferedReader;
import java.io.IOException;

import IO.salida.SalidasSimple;

public class ExtraerMejoresSimilitudes {
	private static final String OUT_FILE = "coefficients/identicalPapersList";
	private static final String IN_FILE = "coefficients/identicalPapers";
	

	public static void paresSusceptibles(double authorLimit, int inicial) throws IOException {
		SalidasSimple.setArchivo(OUT_FILE);
		final int SIZE = 20989;
		for (int i = inicial; i < SIZE; i++) {
			BufferedReader br = ExtractorTotal.getReader(IN_FILE + i);
			for (int j = 0; j < i; j++) {
				String cadena;
				String[] par = (cadena = br.readLine()).split("\t");
				System.out.println(i + "\t" + j + "\t" + cadena);
				if (par[2].trim().contentEquals("true") && Double.parseDouble(par[3]) >= authorLimit)
					SalidasSimple.escribirLinea(i + "\t" + j + "\t" + cadena);
			}
			br.close();
		}
		SalidasSimple.close();
	}
}
