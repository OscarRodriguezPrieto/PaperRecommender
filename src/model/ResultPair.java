package model;

import model.compareResults.Comparator;

public class ResultPair implements Comparable<ResultPair> {

	private String A1;
	private String A2;
	private double coefficient;
	private Comparator<ResultPair> comparator;
	private int coocurrences;
	private int i1;
	private int i2;
	private int N1;
	private int N2;

	private double semantic_similarity;

	public ResultPair(String a1, String a2, int i1, int i2, int n1, int n2, int coocurrences, double coefficient) {
		super();
		A1 = a1;
		A2 = a2;
		this.i1 = i1;
		this.i2 = i2;
		N1 = n1;
		N2 = n2;
		this.coocurrences = coocurrences;
		this.coefficient = coefficient;
	}

	public ResultPair(String a1, String a2, int i1, int i2, int n1, int n2, int coocurrences, double coefficient,
			Comparator<ResultPair> comparator) {
		super();
		A1 = a1;
		A2 = a2;
		this.i1 = i1;
		this.i2 = i2;
		N1 = n1;
		N2 = n2;
		this.coocurrences = coocurrences;
		this.coefficient = coefficient;
		this.comparator = comparator;
	}

	@Override
	public int compareTo(ResultPair r) {
		if (comparator != null)
			return comparator.compareTo(this, r);
		double c2 = r.coefficient;
		return c2 < coefficient ? 1 : c2 == coefficient ? 0 : -1;
	}

	public String getA1() {
		return A1;
	}

	public String getA2() {
		return A2;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public int getCoocurrences() {
		return coocurrences;
	}

	public int getI1() {
		return i1;
	}

	public int getI2() {
		return i2;
	}

	public int getN1() {
		return N1;
	}

	public int getN2() {
		return N2;
	}

	public double getSim() {
		return semantic_similarity;
	}

	public void setSimilarity(double newSim) {
		this.semantic_similarity = newSim;
	}
}
