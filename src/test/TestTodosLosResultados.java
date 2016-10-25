package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import IO.extraccion.ExtractorDeCoeficientes;
import model.ResultPair;

public class TestTodosLosResultados {

	@Test
	public void test() throws IOException {

		// Sacar 20 documentos con más ocurrencias
		Map<String, Integer> bests = getNWithMoreOcurrences(20);
		for (Entry<String, Integer> e : bests.entrySet())
			System.out.println(e.getKey() + "\t" + e.getValue());
	}

	public static Map<String, Integer> getNWithMoreOcurrences(int n) throws IOException {
		Map<String, Integer> bests = new HashMap<String, Integer>();
		List<ResultPair> results = ExtractorDeCoeficientes.getCoefficients();
		for (ResultPair r : results) {
			add(r.getA1(), r.getN1(), bests, n);
			add(r.getA2(), r.getN2(), bests, n);
			// if (r.getA1().contentEquals("J93-2003") ||
			// r.getA2().contentEquals("J-93-2003"))
			// System.out.println(r.getN1() + " & " + r.getN2());
		}
		return bests;
	}

	private static void add(String id, int occ, Map<String, Integer> bests, int n) {
		if (!bests.containsKey(id))
			if (bests.size() >= n) {
				Entry<String, Integer> remove = null;
				int menosOcurrencias = Integer.MAX_VALUE;
				for (Entry<String, Integer> e2 : bests.entrySet())
					if (e2.getValue() < menosOcurrencias) {
						menosOcurrencias = e2.getValue();
						remove = e2;
					}

				if (remove.getValue() < occ) {
					bests.remove(remove.getKey());

					bests.put(id, occ);

				}

			} else {
				bests.put(id, occ);
			}
	}
}
