package medidas.funcionesDePesado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FuncionDePesadoGlobal implements Comparable, FuncionPesado {
	private String name;
	private Map<String, Double> ponderaciones;
	private double simValue;

	public FuncionDePesadoGlobal(List<FuncionDePesadoLocal> corpus, int documentIndex) {
		name = corpus.get(documentIndex).getName();
		ponderaciones = new HashMap<String, Double>();
		for (Entry<String, Double> entry : corpus.get(documentIndex).getMap().entrySet())
			ponderaciones.put(entry.getKey(), entry.getValue() * getIDF(entry.getKey(), corpus));

	}

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof FuncionDePesadoGlobal))
			throw new IllegalStateException();
		FuncionDePesadoGlobal otra = (FuncionDePesadoGlobal) o;

		return simValue > otra.simValue ? -1 : simValue == otra.simValue ? 0 : 1;
	}

	@Override
	public double get(String term) {
		return ponderaciones.containsKey(term) ? ponderaciones.get(term) : 0;
	}

	private int getDocumentFrecuency(String term, List<FuncionDePesadoLocal> corpus) {
		int total = 0;
		for (FuncionDePesadoLocal d : corpus)
			if (d.get(term) > 0)
				total++;
		return total;
	}

	private double getIDF(String term, List<FuncionDePesadoLocal> corpus) {

		return Math.log(corpus.size() / (1.0 * getDocumentFrecuency(term, corpus)));
	}

	public Map<String, Double> getMap() {
		return ponderaciones;
	}

	public String getName() {
		return name;
	}

	public double getSimValue() {
		return simValue;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSimValue(double simValue) {
		this.simValue = simValue;
	}

}
