package test;

import java.io.IOException;

import org.junit.Test;

import edu.ucla.sspace.common.Similarity.SimType;

public class multiTest {

	@Test
	public void test() throws IOException {
		new TestMedia().test();
		TestSimilitudMedidasExternasws4j.TIPO_SIM = SimType.EUCLIDEAN;
		new TestMedia().test();
		TestSimilitudMedidasExternasws4j.TIPO_SIM = SimType.LIN;
		new TestMedia().test();
		TestSimilitudMedidasExternasws4j.TIPO_SIM = SimType.PEARSON_CORRELATION;
		new TestMedia().test();

	}

}
