package IO.extraccion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Extractor {
	private static final String ID_FILE = "acl_data/paper_ids.txt";
	private static final int ID = 0;
	private static final int TITULO = 1;
	private static final int ANYO = 2;
	private static final int AUTOR = 1;
	private static final String ARTICULOS_AUTORES_FILE = "acl_data/paper_author_affiliations.txt";

	public static List<Articulo> extraerArticulos()
			throws NumberFormatException, IOException {
		final BufferedReader br = getReader(ID_FILE);
		final List<Articulo> lista = new ArrayList<Articulo>();
		final Map<String, Articulo> mapa = new HashMap<String, Articulo>();
		while (br.ready()) {
			final String line = br.readLine();
			final String[] atributos = line.split("\t");
			/*
			 * System.out.println(line); System.out.println(atributos[ID] + "\t"
			 * + atributos[TITULO] + "\tSEP-" + atributos[ANYO]);
			 */
			final Articulo nuevoArticulo = new Articulo(atributos[ID],
					atributos[TITULO], Integer.parseInt(atributos[ANYO]), null);
			if (nuevoArticulo.getAnyo() != 2013) {
				lista.add(nuevoArticulo);
				mapa.put(nuevoArticulo.getId(), nuevoArticulo);
			}
		}
		br.close();
		extraerAutores(mapa);
		return lista;
	}

	private static void extraerAutores(final Map<String, Articulo> mapa)
			throws IOException {
		final BufferedReader br = getReader(ARTICULOS_AUTORES_FILE);
		String line = br.readLine();
		while (br.ready()) {
			line = br.readLine();
			final String[] atributos = line.split("\t");
			// mapa.get(atributos[ID])
			// .addAutor(
			// Integer.parseInt(atributos[AUTOR]) + "");
		}
	}

	private static BufferedReader getReader(final String file)
			throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}
}
