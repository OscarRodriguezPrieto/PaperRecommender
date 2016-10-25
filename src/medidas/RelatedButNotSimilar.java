package medidas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import IO.extraccion.ExtractorDeCoeficientes;
import model.ResultPair;

public class RelatedButNotSimilar {

	public static List<ResultPair> bothIdsInSetFilter(Set<String> ids, List<ResultPair> list, double significance) {
		List<ResultPair> res = new ArrayList<ResultPair>();
		for (ResultPair pair : list)
			if (ids.contains(pair.getA1()) && ids.contains(pair.getA2()) && pair.getCoefficient() < significance)
				res.add(pair);
		return res;
	}

	public static void main(String[] ars) throws IOException {
		double sigUmbral = 0.000001;
		File[] files = new File("abstracts").listFiles();
		Set<String> corpusIds = new HashSet<String>();
		for (File f : files)
			corpusIds.add(f.getName().replace(".txt", ""));
		List<ResultPair> coefs = ExtractorDeCoeficientes.getCoefficients(),
				filteredCoefs = bothIdsInSetFilter(corpusIds, coefs, sigUmbral);
		Collections.sort(filteredCoefs);
		for (int i = 0; i < 150; i++)
			System.out.println((i + 1 + "\t" + filteredCoefs.get(i).getA1() + "\t"
					+ filteredCoefs.get(i).getA2() + "\t"
					+ filteredCoefs.get(i).getCoefficient() + "\t" + filteredCoefs.get(i).getSim()).replace(".", ","));
	}
}
