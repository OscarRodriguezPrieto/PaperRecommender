package medidas.funcionesDePesado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FuncionDePesadoLocal implements Comparable, FuncionPesado {

	private String name;
	private double simValue;

	private Map<String, Double> termFrecuency;

	public FuncionDePesadoLocal(String name, List<String> documentTerms) {
		this.name = name;
		termFrecuency = new HashMap<String, Double>();
		for (String term : documentTerms)
			termFrecuency.put(term, (termFrecuency.containsKey(term) ? termFrecuency.get(term) : 0) + 1.0);
		Map<String, Double> nuevo = new HashMap<String, Double>();
		for (Entry<String, Double> e : getMap().entrySet())
			nuevo.put(e.getKey(), e.getValue() /* / documentTerms.size() */);
		getMap().clear();
		getMap().putAll(nuevo);
	}

	@Override
	public int compareTo(Object o) {

		if (!(o instanceof FuncionDePesadoLocal))
			throw new IllegalStateException();
		FuncionDePesadoLocal otra = (FuncionDePesadoLocal) o;

		return simValue > otra.simValue ? -1 : simValue == otra.simValue ? 0 : 1;
	}

	@Override
	public double get(String term) {
		return termFrecuency.containsKey(term) ? termFrecuency.get(term) : 0;
	}

	public Map<String, Double> getMap() {
		return termFrecuency;
	}

	public String getName() {
		return name;
	}

	public double getSimValue() {
		return simValue;
	}

	public void setSimValue(double simValue) {
		this.simValue = simValue;
	}

}
