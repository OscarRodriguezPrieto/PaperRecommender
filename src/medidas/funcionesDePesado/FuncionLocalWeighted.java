package medidas.funcionesDePesado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FuncionLocalWeighted extends FuncionDePesadoLocal {

	public FuncionLocalWeighted(String name, List<String> documentTerms) {
		super(name, documentTerms);
		Map<String, Double> nuevo = new HashMap<String, Double>();
		for (Entry<String, Double> e : getMap().entrySet())
			nuevo.put(e.getKey(), e.getValue() / documentTerms.size());
		getMap().clear();
		getMap().putAll(nuevo);
	}

}
