package IO.extraccion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.ResultPair;
import model.compareResults.WorstTF_IDF;

public class ExtractorDeCoeficientes {

	private static final String FILE = "coefficients/ALL_PVALUES";

	public static List<ResultPair> getCoefficients() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE));
		List<ResultPair> res = new ArrayList<ResultPair>();
		while (br.ready())
			res.add(getLine(br.readLine()));
		return res;

	}

	private static ResultPair getLine(String readLine) {
		String[] terms = readLine.split("\\s+");
		return new ResultPair(terms[2], terms[3], Integer.parseInt(terms[0]), Integer.parseInt(terms[1]),
				Integer.parseInt(terms[5]), Integer.parseInt(terms[6]), Integer.parseInt(terms[4]),
				Double.parseDouble(terms[7].replace(",", ".")), new WorstTF_IDF());
	}

}
