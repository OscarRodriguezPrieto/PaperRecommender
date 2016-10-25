package model.compareResults;

import java.io.IOException;

import IO.extraccion.Corpus;
import model.ResultPair;
import test.TestSimilitudMedidasExternasws4j;
import test.TestTFIDFAbstracts;

public class WorstTF_IDF implements Comparator<ResultPair> {
	private static Corpus c;
	// private static Map<String, Double> medias = new HashMap<String,
	// Double>();

	static {
		try {
			c = new Corpus();
			/*
			 * for (int i = 0; i < c.funcionesGolbales.size(); i++) { double
			 * media = 0; for (FuncionDePesadoGlobal f2 : c.funcionesGolbales)
			 * if (c.funcionesGolbales.get(i) != f2) media +=
			 * TestSimilitudMedidasExternasws4j.getSim(
			 * TestTFIDFAbstracts.getArray(c.funcionesGolbales.get(i),
			 * c.vocabulario), TestTFIDFAbstracts.getArray(f2, c.vocabulario)) /
			 * (c.funcionesGolbales.size() - 1);
			 * 
			 * medias.put(c.funcionesGolbales.get(i).getName(), media); }
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int compareTo(ResultPair a, ResultPair b) {
		double simB = TestSimilitudMedidasExternasws4j.getSim(
				TestTFIDFAbstracts.getArray(c.getLocalFuncsByName.get(b.getA1()), c.vocabulario),
				TestTFIDFAbstracts.getArray(c.getLocalFuncsByName.get(b.getA2()), c.vocabulario)),
				simA = TestSimilitudMedidasExternasws4j.getSim(
						TestTFIDFAbstracts.getArray(c.getLocalFuncsByName.get(a.getA1()), c.vocabulario),
						TestTFIDFAbstracts.getArray(c.getLocalFuncsByName.get(a.getA2()), c.vocabulario));

		// double simMenosMediaA = (simA * 2 - medias.get(a.getA1()) -
		// medias.get(a.getA2())) / 2;
		// double simMenosMediaB = (simB * 2 - medias.get(b.getA1()) -
		// medias.get(b.getA2())) / 2;

		a.setSimilarity(simA);
		b.setSimilarity(simB);
		return simB < simA ? 1 : simA == simB ? 0 : -1;
	}

}
