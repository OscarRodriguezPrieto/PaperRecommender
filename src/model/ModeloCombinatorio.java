package model;

import java.util.List;

import IO.extraccion.Cita;

public class ModeloCombinatorio {
	public static final int CO_OCURRENCIA = 0;
	public static final int A1_COUNTS = 1;
	public static final int A2_COUNTS = 2;

	public static double getCoeficientePara(List<Cita> citas, int a, int b) {
		return getCoeficientePara(citas, citas.get(a), citas.get(b));
	}

	public static double getCoeficientePara(List<Cita> citas, Cita a, Cita b) {
		return getPValor(getCounts(citas, a, b), citas.size());
	}

	public static int[] getCounts(List<Cita> citas, Cita a, Cita b) {
		int[] res = new int[3];
		for (int i = 0; i < citas.size(); i++) {
			List<Cita> citasDe = citas.get(i).getCitas();
			boolean contieneA1 = citasDe.contains(a);
			boolean contieneA2 = citasDe.contains(b);
			if (contieneA1) {
				res[A1_COUNTS]++;
				if (contieneA2) {
					res[CO_OCURRENCIA]++;
				}
			}
			if (contieneA2)
				res[A2_COUNTS]++;
		}
		return res;
	}

	public static double getPValor(int[] res, int N) {
		int minimo = min(res[A1_COUNTS], res[A2_COUNTS]);
		double total = 0;
		for (int i = res[CO_OCURRENCIA]; i <= minimo; i++)
			total += getProbability(i, min(-res[A1_COUNTS], -res[A2_COUNTS])
					* -1, minimo, N);
		return total;
	}

	private static double getProbability(int k, int n1, int n2, int N) {
		if (k > n2 || k < -1 * min(0, -(n1 + n2 - N)))
			throw new IllegalStateException();
		double total = 1;
		// System.out.println("FOR j=0 si j<" + (n2 - k - 1));
		for (int j = 0; j <= n2 - k - 1; j++)
			total *= 1 - n1 / (N * 1.0 - j);
		// System.out.println("FOR j=0 si j<" + (k - 1));
		for (int j = 0; j <= k - 1; j++)
			total *= (((n1 - j) * (n2 - j)) * 1.0)
					/ ((N - n2 + k - j) * (k - j));
		return total;
	}

	private static int min(int a, int b) {
		return a > b ? b : a;
	}
}
