package IO.extraccion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtraerStopWords {
	private static final String FICHERO_STOP = "useful_files/stopwords-english";
	public static List<String> STOP_WORDS = getStopWords();

	private static List<String> getStopWords() {
		List<String> res = new ArrayList<String>();
		try {
			BufferedReader br = ExtractorTotalACL.getReader(FICHERO_STOP);
			while (br.ready())
				res.add(br.readLine().trim());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
