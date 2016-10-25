package IO.salida;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SalidasSimple {
	private static BufferedWriter bw;
	public static int i;
	private static String archivo;

	public static void setArchivo(String archivo) {
		try {
			if (bw != null)
				bw.close();
			SalidasSimple.archivo = archivo;
			bw = new BufferedWriter(new FileWriter(archivo + i++));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void escribirLinea(String linea) {
		try {
			bw.write(linea + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
