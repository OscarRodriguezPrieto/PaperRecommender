package IO.extraccion;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtraerSoloArticulos {
	public static List<Articulo> extraerArticulos()
			throws NumberFormatException, IOException {
		final BufferedReader br = ExtractorTotalACL
				.getReader(ExtractorTotalACL.METADATA_FILE);
		final List<Articulo> lista = new ArrayList<Articulo>();
		final Map<EnvoltorioCadena, Articulo> mapa = new HashMap<EnvoltorioCadena, Articulo>();
		Articulo a = null;
		while (br.ready())
			if ((a = ExtractorTotalACL.leerArticulo(br)) != null)
				lista.add(a);

		br.close();
		return lista;
	}
}
