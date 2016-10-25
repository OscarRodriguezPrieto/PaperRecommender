package IO.extraccion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractorTotal {
	static final String METADATA_FILE = "acl_data/acl-metadata.txt";
	private static final int VALOR = 1;
	private static final int CITA = 0;
	private static final int CITADO = 1;

	private static final String CITAS_FILE = "acl_data/acl.txt";

	public static List<Cita> extraerCitas() throws NumberFormatException,
			IOException {
		final BufferedReader br = getReader(METADATA_FILE);
		final List<Cita> lista = new ArrayList<Cita>();
		final Map<EnvoltorioCadena, Articulo> mapa = new HashMap<EnvoltorioCadena, Articulo>();
		Articulo a = null;
		while (br.ready())
			if ((a = leerArticulo(br)) != null)
				lista.add(a);

		br.close();
		System.out.println(lista.size());
		extraerCitas(lista);
		return lista;
	}

	private static void extraerCitas(List<Cita> lista) throws IOException {
		BufferedReader br = getReader(CITAS_FILE);
		while (br.ready()) {
			String[] cita = br.readLine().split("==>");
			getArticulo(lista, cita[CITA]).addCita(
					getArticulo(lista, cita[CITADO]));
		}
	}

	static Articulo leerArticulo(BufferedReader br) throws IOException {
		String id = br.readLine();
		if (id.trim().length() < 2)
			return null;
		id = getValue(id);
		String[] autores = getAutores(br.readLine());
		String titulo = getValue(br.readLine());
		br.readLine();
		int anyo = Integer.parseInt(getValue(br.readLine()));
		return new Articulo(id, titulo, anyo, autores);
	}

	private static String getValue(String readLine) {
		String val = readLine.split("=")[VALOR].trim();
		return val.substring(1, val.length() - 1);
	}

	private static String[] getAutores(String readLine) {
		return getValue(readLine).split(";");
	}

	public static Cita getArticulo(List<Cita> lista, String id) {
		return lista.get(getIndex(lista, id));
	}

	public static int getIndex(List<Cita> lista, String id) {
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getId().contentEquals(id.trim()))
				return i;
		System.err.println(id);
		Cita nuevaCita = new Cita(id.trim());
		lista.add(nuevaCita);
		return lista.size() - 1;
	}

	public static BufferedReader getReader(final String file)
			throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}

	public static List<Articulo> extraerArticulos() {
		// TODO Auto-generated method stub
		return null;
	}

}
