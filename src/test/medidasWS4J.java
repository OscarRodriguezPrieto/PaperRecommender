package test;

import org.junit.Test;

import edu.cmu.lti.ws4j.WS4J;

public class medidasWS4J {

	@Test
	public void test() {
		printMatrix(WS4J.getSynonymyMatrix(new String[] { "The", "black", "dog", "is", "barking" },
				new String[] { "a", "white", "hound", "is", "awakening" }));
	}

	public static int countSinonyms(double[][] matrix) {
		int counts = 0;
		for (double[] d : matrix)
			for (double val : d)
				if (val >= 1.0)
					counts++;
		return counts;

	}

	private void printMatrix(double[][] matrix) {
		for (double[] d : matrix) {
			for (double val : d)
				System.out.print(val + "\t");
			System.out.println("");
		}
	}

	public static double countSinonyms(String titulo, String comparingTitle) {
		return countSinonyms(
				WS4J.getSynonymyMatrix(titulo.toLowerCase().split("\\s+"), comparingTitle.toLowerCase().split("\\s+")));
	}

}
