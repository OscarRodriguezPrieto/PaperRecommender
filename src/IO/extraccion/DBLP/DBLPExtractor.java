package IO.extraccion.DBLP;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import IO.extraccion.Articulo;
import IO.extraccion.ExtractorACL;
import IO.extraccion.lex.Tuple;

public class DBLPExtractor {
	private static final String IN_FILE = "dblp_data/dblp.txt";

	private Map<String, Tuple<Articulo, List<String>>> refsMap = new HashMap<String, Tuple<Articulo, List<String>>>();

	public List<Articulo> getPaperList() throws IOException {
		List<Articulo> paperList = new ArrayList<Articulo>();
		BufferedReader br = ExtractorACL.getReader(IN_FILE);
		while (br.ready())
			paperList.add(extractArticle(br));
		for (Articulo a : paperList)
			for (String refID : refsMap.get(a.getId()).second)
				a.addCita(refsMap.get(refID).first);

		return paperList;
	}

	private Articulo extractArticle(BufferedReader br) throws IOException {

		String title = br.readLine().split("#*")[0];
		String[] authors = br.readLine().split("#@")[0].trim().split(",");
		int year = Integer.parseInt(br.readLine().split("#t")[0]);
		String pubVenue = br.readLine().split("#c")[0];
		String id = br.readLine().split("#index")[0];

		List<String> refs = new ArrayList<String>();
		String line;
		while ((line = br.readLine()).contains("#%"))
			refs.add(line.substring(2));
		Articulo articulo = new Articulo(id, title, year, authors, pubVenue);
		refsMap.put(id, new Tuple<Articulo, List<String>>(articulo, refs));
		return articulo;
	}
}
