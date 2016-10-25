package IO.extraccion.lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Similitud;

public class ExtractTexts {

	private static List<String> getRasgos(BufferedReader br, List<String> list) throws IOException {
		if (br.ready()) {
			for (String s : br.readLine().split("\\b"))
				if (s.replaceAll("\\W", "").trim().length() > 0 && !Similitud.esVacia(s.toLowerCase()))
					list.add(stem(s.toLowerCase()));
			return getRasgos(br, list);
		} else
			return list;
	}

	public static List<String> getRasgos(File f) throws IOException {
		List<String> list = new ArrayList<String>();
		try {
			BufferedReader br = Extractor.getReaderFor(f);
			return getRasgos(br, list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return list;
		}

	}

	public static List<String> getRasgos(int i) {
		throw new IllegalAccessError("Not implemented method.");
	}

	public static String stem(String word) {
		Stemmer st = new Stemmer();
		for (int i = 0; i < word.length(); i++)
			st.add(word.charAt(i));
		st.stem();
		return st.toString();

	}
}
