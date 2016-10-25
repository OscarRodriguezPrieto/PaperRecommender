package test;

import java.io.IOException;

import org.junit.Test;

import IO.extraccion.Corpus;
import edu.ucla.sspace.common.Similarity.SimType;

public class nuevoTest {

	double getSim(SimType s, double[] t1, double[] t2) {
		TestSimilitudMedidasExternasws4j.TIPO_SIM = s;
		return TestSimilitudMedidasExternasws4j.getSim(t1, t2);

	}

	@Test
	public void test() throws NumberFormatException, IOException {
		Corpus c = new Corpus();
		int a1 = 0, a2 = 1;
		double[] t1 = TestTFIDFAbstracts.getArray(c.funcionesGolbales.get(a1), c.vocabulario),
				t2 = TestTFIDFAbstracts.getArray(c.funcionesGolbales.get(a2), c.vocabulario);
		SimType[] tipos = new SimType[] { SimType.COSINE, SimType.PEARSON_CORRELATION, SimType.EUCLIDEAN };
		System.out.println("Vocabulario:\t"+c.vocabulario.size());
		System.out.println(c.funcionesGolbales.get(a1).getName() + " vs " + c.funcionesGolbales.get(a2).getName());
		String[] lines=new String[c.vocabulario.size()];
		for(int i=0;i<lines.length;i++)
			lines[i]="";
		for (int i=0;i<lines.length;i++)
			System.out.println(c.vocabulario.toArray()[i]+("\t"+t1[i]+"\t"+t2[i]).replace(".", ","));
		System.out.println();
		
		for (SimType s : tipos) {
			System.out.print(s + "\t");
			System.out.print((getSim(s, t1, t2) + "\t").replace(".", ","));
		}
	}

}
