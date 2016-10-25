package model;

import IO.extraccion.Articulo;
import IO.extraccion.ExtraerStopWords;

public class Similitud {
	// public static
	public static boolean anyo(final Articulo a, final Articulo b) {

		return b.getAnyo() == a.getAnyo();
	}

	public static double autores(final Articulo a, final Articulo b) {
		int coincidencias = 0;
		double maximo = Math.min(a.getAutores().length, b.getAutores().length);
		for (final String autor1 : a.getAutores()) {
			boolean salida = false;
			for (int i = 0; i < b.getAutores().length && !salida; i++)
				if (salida = autor1.trim().contentEquals(b.getAutores()[i].trim()))
					coincidencias++;
		}
		return coincidencias / maximo;
	}

	public static int semejanzaTitulo(final Articulo a, final Articulo b) {
		return LevenshteinDistance.computeLevenshteinDistance(sinPalabrasVacias(a.getTitulo()),
				sinPalabrasVacias(b.getTitulo()));
	}

	public static String sinPalabrasVacias(String cadena) {
		String[] palabras = cadena.split("\\W+");
		String res = "";
		for (int i = 0; i < palabras.length; i++) {
			palabras[i] = palabras[i].trim();
			res += esVacia(palabras[i].toLowerCase()) ? "" : " " + palabras[i];
		}
		return res;
	}

	public static boolean esVacia(String string) {
		for (String stopWord : ExtraerStopWords.STOP_WORDS)
			if (stopWord.contentEquals(string))
				return true;
		return false;
	}

	public static String toString(Articulo articulo, Articulo articulo2) {
		return articulo.getId() + "\t" + articulo2.getId() + "\t" + anyo(articulo, articulo2) + "\t"
				+ autores(articulo, articulo2) + "\t" + semejanzaTitulo(articulo, articulo2) + "\t"
				+ articulo.getTitulo() + "\t" + articulo2.getTitulo();
	}
}
