package test;

import org.junit.Test;

import edu.ucla.sspace.common.Similarity.SimType;

public class TestPearson {

	@Test
	public void test() {
		TestSimilitudMedidasExternasws4j.TIPO_SIM = SimType.PEARSON_CORRELATION;
		
		System.out.println(TestSimilitudMedidasExternasws4j.getSim(new double[] { -1, 1 }, new double[] { -1, 1 }));
		System.out.println(TestSimilitudMedidasExternasws4j.getSim(new double[] { 1, 0 }, new double[] { 0, 1 }));
		System.out.println(TestSimilitudMedidasExternasws4j.getSim(new double[] { 1, 0 }, new double[] { 1, 1 }));

	}

}
