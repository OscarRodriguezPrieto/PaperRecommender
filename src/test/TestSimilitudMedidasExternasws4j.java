package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import IO.extraccion.Articulo;
import IO.extraccion.Cita;
import IO.extraccion.ExtractorACL;
import IO.extraccion.ExtractorDeCoeficientes;
import IO.extraccion.ExtractorTotal;
import edu.ucla.sspace.common.Similarity;
import edu.ucla.sspace.common.Similarity.SimType;
import model.ResultPair;
import model.Similitud;;

public class TestSimilitudMedidasExternasws4j {
	public static String comparingTitle = "Bleu: A Method For Automatic Evaluation Of Machine Translation";
	public static boolean medida=false;
public static SimType TIPO_SIM = SimType.COSINE;
	public static double getSentenceSimilarity(String s1, String s2) {
		double[][] termMatrix = getTF(getWords(s1), getWords(s2));
		return Similarity.getSimilarity(SimType.COSINE, termMatrix[0], termMatrix[1]);
	}

	public static double getSim(double[] s1, double[] s2) {
		if (TIPO_SIM == SimType.PEARSON_CORRELATION && !medida)
			return Math.abs(Similarity.getSimilarity(TIPO_SIM, s1, s2));
		else
			return Similarity.getSimilarity(TIPO_SIM, s1, s2);
	}

	private static double[][] getTF(String[] words, String[] words2) {
		List<String> vocabulary = new ArrayList<String>();
		for (String s : words)
			if (!vocabulary.contains(s))
				vocabulary.add(s);
		for (String s : words2)
			if (!vocabulary.contains(s))
				vocabulary.add(s);
		double[][] res = new double[2][vocabulary.size()];
		for (String s : words)
			res[0][vocabulary.indexOf(s)] += 1.0 / words.length;
		for (String s : words2)
			res[1][vocabulary.indexOf(s)] += 1.0 / words2.length;
		return res;
	}

	public static String getTitle(List<Cita> lista, String id) {
		Cita a = ExtractorACL.getArticulo(lista, id);
		return a instanceof Articulo ? ((Articulo) a).getTitulo() : "CITA-> NO_TITLE";
	}

	private static String[] getWords(String s1) {
		return Similitud.sinPalabrasVacias(s1.toLowerCase()).split("\\W+");
	}


	public static List<ResultPair> idFilter(String id, List<ResultPair> list, double significance) {
		List<ResultPair> res = new ArrayList<ResultPair>();
		for (ResultPair pair : list)
			if ((pair.getA1().contentEquals(id) || pair.getA2().contentEquals(id))
					&& pair.getCoefficient() < significance)
				res.add(pair);
		return res;
	}


	public List<Articulo> getTitles(List<Cita> citas) {
		List<Articulo> titulos = new ArrayList<Articulo>();
		for (Cita cita : citas)
			if (cita instanceof Articulo)
				titulos.add((Articulo) cita);
		return titulos;
	}

	@Test
	public void test() throws IOException {
		// for (String s : JAWJAW.findSynonyms("book", POS.v))
		// System.out.println(s);
		// getSentenceSimilarityMean("Minimum Error Rate Training In Statistical
		// Machine Translation", comparingTitle);

		List<Cita> citas = ExtractorTotal.extraerCitas();
		List<ResultPair> pairForId = idFilter("W96-0213", ExtractorDeCoeficientes.getCoefficients(), 0.001);
		Collections.sort(pairForId);
		for (int i = 0; i < 20 && i < pairForId.size(); i++)
			System.out.println(pairForId.get(i).getA1() + "\t" + pairForId.get(i).getA2() + "\t"
					+ pairForId.get(i).getCoefficient());
		// List<Articulo> articulos = getTitles(citas);
		// Collections.sort(articulos);
		// SalidasSimple.setArchivo("S1");
		// for (Articulo a : articulos)
		// SalidasSimple.escribirLinea(a.getId() + "\t" + a.getTitulo() + "\t"
		// + (getSentenceSimilarityMean(a.getTitulo(), comparingTitle) +
		// "").replace('.', ','));
		// SalidasSimple.close();
	}
}
