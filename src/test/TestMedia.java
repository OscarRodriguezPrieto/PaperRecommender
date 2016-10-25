package test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import IO.extraccion.Corpus;
import IO.extraccion.ExtractorDeCoeficientes;
import edu.ucla.sspace.common.Similarity.SimType;
import medidas.funcionesDePesado.FuncionDePesadoGlobal;
import model.ResultPair;

public class TestMedia {

	@SuppressWarnings("unchecked")
	private void extracted(List<ResultPair> pairForId) {
		Collections.sort(pairForId);
	}

	private double[] getTerms(Corpus c, String id) {
		for (FuncionDePesadoGlobal f : c.funcionesGolbales)
			if (f.getName().contains(id.trim()))
				return TestTFIDFAbstracts.getArray(f, c.vocabulario);
		throw new IllegalArgumentException(id);
	}

	@Test
	public void test() throws IOException { 
		int n = 20;
		Corpus c = new Corpus();
		Map<String, Double> mapMedia = new HashMap<String, Double>();
		for (int i = 0; i < c.funcionesGolbales.size(); i++) {
			double media = 0;
			c.funcionesGolbales.get(i).setName(c.funciones.get(i).getName());
			for (FuncionDePesadoGlobal f2 : c.funcionesGolbales)
				if (c.funcionesGolbales.get(i) != f2)
					media += TestSimilitudMedidasExternasws4j.getSim(
							TestTFIDFAbstracts.getArray(c.funcionesGolbales.get(i), c.vocabulario),
							TestTFIDFAbstracts.getArray(f2, c.vocabulario)) / (c.funcionesGolbales.size() - 1);

			mapMedia.put(c.funcionesGolbales.get(i).getName(), media);
		}

		Map<String, Integer> moreOcc = TestTodosLosResultados.getNWithMoreOcurrences(n);
		int j = 0;
		List<ResultPair> coefs = ExtractorDeCoeficientes.getCoefficients();
		double[] nuevosInc = new double[100];
		while (j < n) {
			j++;
			Entry<String, Integer> best = null;
			int mostOcc = Integer.MIN_VALUE;
			for (Entry<String, Integer> e : moreOcc.entrySet())
				if (e.getValue() > mostOcc) {
					mostOcc = e.getValue();
					best = e;
				}
			moreOcc.remove(best.getKey());
			List<ResultPair> pairForId = TestSimilitudMedidasExternasws4j.idFilter(best.getKey(), coefs, 0.001);
			extracted(pairForId);
			System.out.println();
			double totalMedidas = 0;
			double[] medidasSim = new double[5];
			double[] coefSis = new double[5];
			for (int i = 0; i < 5 && i < pairForId.size(); i++) {

				String otra = pairForId.get(i).getA1().contains(best.getKey().trim()) ? pairForId.get(i).getA2()
						: pairForId.get(i).getA1();
				medidasSim[i] = TestSimilitudMedidasExternasws4j.getSim(getTerms(c, best.getKey()), getTerms(c, otra));
				coefSis[i] = pairForId.get(i).getCoefficient();
				if (coefSis[i] == 0)
					coefSis[i] = Double.MIN_VALUE;
				totalMedidas += medidasSim[i];
				System.out.println((i == 0 ? j + "º" : "") + "\t" + best.getKey() + "\t" + otra + "\t"
						+ ("" + coefSis[i]).replace('.', ',') + ("\t" + mapMedia.get(best.getKey())).replace('.', ',')
						+ "\t" + ("" + medidasSim[i]).replace('.', ',')
						+ ("\t" + medidasSim[i] / mapMedia.get(best.getKey()) * 100).replace('.',
								','));
			}
			double media = totalMedidas / 5;
			totalMedidas = 0;
			System.out.println("Nueva media (n=5)\t" + (media + "").replace(".", ","));
			for (int i = 0; i < 5 && i < pairForId.size(); i++) {
				totalMedidas += Math.pow(medidasSim[i] - media, 2);
				nuevosInc[(j - 1) * 5 + i] = medidasSim[i] / media * 100;
			}
			System.out.println("Nueva desviacion (n=5)\t" + (Math.sqrt(totalMedidas / 5) + "").replace(".", ","));
			SimType similarityFunc = TestSimilitudMedidasExternasws4j.TIPO_SIM;
			TestSimilitudMedidasExternasws4j.medida = true;
			TestSimilitudMedidasExternasws4j.TIPO_SIM = SimType.PEARSON_CORRELATION;
			System.out.println("Correlación (n=5)\t"
					+ (TestSimilitudMedidasExternasws4j.getSim(medidasSim, coefSis) + "").replace(".", ","));
			TestSimilitudMedidasExternasws4j.medida = false;
			TestSimilitudMedidasExternasws4j.TIPO_SIM = similarityFunc;
		}

		for (int i = 0; i < nuevosInc.length; i++) {
			System.out.println((nuevosInc[i] + "").replace(".", ","));
			if ((i + 1) % 5 == 0)
				System.out.println("\n\n\n");
		}
	}
}
