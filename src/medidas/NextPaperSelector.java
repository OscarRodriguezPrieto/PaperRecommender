package medidas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import IO.extraccion.Corpus;
import IO.extraccion.ExtractorDeCoeficientes;
import model.ResultPair;
import test.TestSimilitudMedidasExternasws4j;

public class NextPaperSelector {

	public static String findPaperRelatedWithMoreInCorpus(double sigUmbral) throws IOException {
		int n = 20;
		Corpus c = null;
		c = new Corpus();

		List<ResultPair> filteredCoefs = new ArrayList<ResultPair>(), coefs = ExtractorDeCoeficientes.getCoefficients();
		Map<String, Integer> relatedToNInCorpus = new HashMap<String, Integer>();
		File[] files = new File("abstractsBis").listFiles();
		Set<String> corpusIds = new HashSet<String>();
		for (File f : files)
			corpusIds.add(f.getName().replace(".txt", ""));
		for (ResultPair r : coefs)
			if (!(corpusIds.contains(r.getA1()) && corpusIds.contains(r.getA2())))
				filteredCoefs.add(r);

		for (File f : files) {
			String id = f.getName().replace(".txt", "");
			for (ResultPair r : TestSimilitudMedidasExternasws4j.idFilter(id, filteredCoefs, sigUmbral)) {
				String otherId = r.getA1().contentEquals(id) ? r.getA2() : r.getA1();
				if (relatedToNInCorpus.containsKey(otherId))
					relatedToNInCorpus.put(otherId, relatedToNInCorpus.get(otherId) + 1);
				else
					relatedToNInCorpus.put(otherId, 1);
			}
		}
		String bestId = null;
		int max = 0;
		for (Entry<String, Integer> entry : relatedToNInCorpus.entrySet())
			if (entry.getValue() > max) {
				max = entry.getValue();
				bestId = entry.getKey();
			}
		System.out.println("MAX: " + max + "/" + files.length);
		return bestId;
	}

	public static void main(String[] ars) throws IOException {
		System.out.println(findPaperRelatedWithMoreInCorpus(.0001));
	}
}
