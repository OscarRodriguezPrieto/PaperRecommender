package test;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import org.junit.Test;

import IO.extraccion.Corpus;
import medidas.funcionesDePesado.FuncionDePesadoGlobal;
import medidas.funcionesDePesado.FuncionDePesadoLocal;

public class TestTFIDFAbstractsLocal {

	private static final String comparingTitle = "J93-2004";
	private Set<String> vocabulario;

	private double[] getArray(FuncionDePesadoGlobal f) {
		double[] res = new double[vocabulario.size()];
		int i = 0;
		for (String s : vocabulario)
			res[i++] = f.get(s);
		return res;
	}

	private double[] getArray(FuncionDePesadoLocal f) {
		double[] res = new double[vocabulario.size()];
		int i = 0;
		for (String s : vocabulario)
			res[i++] = f.get(s);
		return res;
	}

	@Test
	public void test() throws IOException {

		Corpus c = new Corpus();
		vocabulario = c.vocabulario;
		FuncionDePesadoLocal referenceF = null;
		for (int i = 0; i < c.funciones.size(); i++) {
			if (c.funciones.get(i).getName().contains(comparingTitle))
				referenceF = c.funciones.get(i);
		}
		for (FuncionDePesadoLocal f : c.funciones)
			f.setSimValue(TestSimilitudMedidasExternasws4j.getSim(getArray(f), getArray(referenceF)));
		Collections.sort(c.funciones);
		for (int i = 0; i < 20; i++)
			System.out.println(c.funciones.get(i).getName() + "\t" + c.funciones.get(i).getSimValue());
 
	}
}
