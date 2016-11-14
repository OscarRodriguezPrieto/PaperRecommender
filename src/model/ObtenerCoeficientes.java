package model;

import java.io.BufferedReader;
import java.io.IOException;

import IO.extraccion.ExtractorTotalACL;
import IO.salida.SalidasSimple;

public class ObtenerCoeficientes {
	private static final int COOCURRENCE = 2;
	private static final int SOLO_A1 = 3;
	private static final int SOLO_A2 = 4;

	private final static int TOTAL_SIZE = 20997;
	private final static String FILE = "coefficients/ALL_PVALUES";

	public static void getCoeficientes(int inicial) throws IOException {
		SalidasSimple.i = inicial;
		SalidasSimple.setArchivo("coeficientes/TODOS_PVALUES_ESCEPTICO");
		for (int i = inicial; i < TOTAL_SIZE; i++) {
			BufferedReader br = ExtractorTotalACL.getReader("resultados/resultado" + i);

			String cadena;
			if (!(cadena = br.readLine()).contains("NaN"))
			// La linea de mas
			{

				String[] par = cadena.split("\t");

				System.out.print(i + "\t" + 0 + "\t" + cadena);
				System.out.println("\t" + getDouble(par));
				if (Integer.parseInt(par[COOCURRENCE]) > 0)
					SalidasSimple.escribirLinea(i + "\t" + 0 + "\t" + cadena + "\t" + getDouble(par));
				for (int j = 1; j < i; j++) {
					cadena = br.readLine();
					par = cadena.split("\t");
					System.out.println(i + "\t" + j + "\t" + cadena + "\t" + getDouble(par));
					if (Integer.parseInt(par[COOCURRENCE]) > 0)
						SalidasSimple.escribirLinea(i + "\t" + j + "\t" + cadena + "\t" + getDouble(par));
				}
			}
			br.close();
		}
		SalidasSimple.close();
	}

	private static String getDouble(String[] par) {
		return (ModeloCombinatorio.getPValor(new int[] { Integer.parseInt(par[COOCURRENCE]),
				Integer.parseInt(par[SOLO_A1]), Integer.parseInt(par[SOLO_A2]) }, TOTAL_SIZE) + "").replace('.', ',');
	}

	private static String tieneRepetidos(int i, String cadena, BufferedReader br) throws IOException {
		String siguiente = br.readLine();
		return siguiente.contentEquals(cadena) && i > 1 ? br.readLine() : siguiente;
	}
}
