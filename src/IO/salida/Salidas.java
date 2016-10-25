package IO.salida;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Salidas {
	private static BufferedWriter bw;
	private static String archivo;
	private static int cuenta = 0;
	private static int limite;
	private static int i = 0;

	public static void setArchivo(String archivo, int limite) {
		Salidas.archivo = archivo;
		Salidas.limite = limite;
		cuenta = 0;
		try {
			bw = new BufferedWriter(new FileWriter(archivo + i++));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escribirLinea(String linea) {
		try {
			if (cuenta++ > limite) {
				bw.close();
				bw = new BufferedWriter(new FileWriter(archivo + i++ + ".txt"));
				limite *= 2;
			}
			bw.write(linea + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
