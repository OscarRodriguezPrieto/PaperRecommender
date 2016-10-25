package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import IO.extraccion.Corpus;
import IO.extraccion.ExtractorDeCoeficientes;
import medidas.funcionesDePesado.FuncionDePesadoGlobal;
import medidas.funcionesDePesado.FuncionPesado;
import model.ResultPair;

public class TestTFIDFAbstracts {

	public static double[] getArray(FuncionPesado f, Set<String> vocabulario) {
		double[] res = new double[vocabulario.size()];
		int i = 0;
		for (String s : vocabulario)
			res[i++] = f.get(s);
		return res;
	}
	List<String> linesCoeffs = new ArrayList<String>();
	List<String> linesSim = new ArrayList<String>();

	private Set<String> vocabulario;

	@Test
	public void test() throws IOException {
		Corpus c = new Corpus();
		vocabulario = c.vocabulario;
		for (int i = 0; i < c.funciones.size(); i++)
			c.funcionesGolbales.get(i).setName(c.funciones.get(i).getName());

		List<ResultPair> coefs = ExtractorDeCoeficientes.getCoefficients();

		Map<String, Integer> moreOcc = TestTodosLosResultados.getNWithMoreOcurrences(20);

	}

	void writeCoeffs(String title, int n, List<ResultPair> coefs) throws IOException {
		List<ResultPair> pairForId = TestSimilitudMedidasExternasws4j.idFilter(title, coefs, 0.001);
		Collections.sort(pairForId);
		for (int i = 0; i < n; i++) {
			ResultPair r = pairForId.get(i);
			linesCoeffs.add(r.getA1().contains(title.trim()) ? r.getA2() : r.getA1() + "\t" + r.getCoefficient());
		}
	}

	public void writeForTitle(Corpus c, String comparingTitle, int n) {

		FuncionDePesadoGlobal referenceF = null;
		for (FuncionDePesadoGlobal f : c.funcionesGolbales)
			if (f.getName().contains(comparingTitle))
				referenceF = f;
		for (FuncionDePesadoGlobal f : c.funcionesGolbales)
			f.setSimValue(TestSimilitudMedidasExternasws4j.getSim(getArray(f, vocabulario),
					getArray(referenceF, vocabulario)));
		Collections.sort(c.funcionesGolbales);
		for (int i = 0; i < n; i++)
			linesSim.add(c.funcionesGolbales.get(i).getName() + "\t" + c.funcionesGolbales.get(i).getSimValue() + "\n");

	}

}
