package model;

import java.util.List;

import IO.extraccion.Articulo;
import IO.salida.SalidasSimple;

public class MedidasSimilitud {
	private static final String FILE = "coefficients/identicalPapers";

	public static void medidasSimilitudPara(List<Articulo> articulos, int inicial, int ini2) {
		for (Articulo a : articulos)
			a.setTitulo(Similitud.sinPalabrasVacias(a.getTitulo()));
		SalidasSimple.i = inicial;
		for (int n = inicial; n < articulos.size(); n++) {
			SalidasSimple.setArchivo(FILE);
			for (int i = ini2; i < n; i++) {
				SalidasSimple.escribirLinea(Similitud.toString(articulos.get(n), articulos.get(i)));
				System.out.println(n + " " + i);
			}
		}

		SalidasSimple.close();
	}

}
