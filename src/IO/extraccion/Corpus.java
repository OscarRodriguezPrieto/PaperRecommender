package IO.extraccion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import IO.extraccion.lex.ExtractTexts;
import medidas.funcionesDePesado.FuncionDePesadoGlobal;
import medidas.funcionesDePesado.FuncionDePesadoLocal;

public class Corpus {
	public List<FuncionDePesadoLocal> funciones = new ArrayList<FuncionDePesadoLocal>();
	public List<FuncionDePesadoGlobal> funcionesGolbales = new ArrayList<FuncionDePesadoGlobal>();
	public Map<String, FuncionDePesadoGlobal> getGlobalFuncsByName = new HashMap<String, FuncionDePesadoGlobal>();
	public Map<String, FuncionDePesadoLocal> getLocalFuncsByName = new HashMap<String, FuncionDePesadoLocal>();
	public Set<String> vocabulario = new HashSet<String>();

	public Corpus() throws IOException {
		for (File f : new File("abstracts").listFiles()) {
			List<String> terms = ExtractTexts.getRasgos(f);
			FuncionDePesadoLocal func = new FuncionDePesadoLocal(f.getName().replace(".txt", ""), terms);
			funciones.add(func);
			getLocalFuncsByName.put(func.getName(), func);
			vocabulario.addAll(terms);
		}
		for (int i = 0; i < funciones.size(); i++) {
			FuncionDePesadoGlobal f = new FuncionDePesadoGlobal(funciones, i);
			funcionesGolbales.add(f);
			getGlobalFuncsByName.put(f.getName(), f);
		}
	}

	public Corpus(int size) {
		throw new IllegalStateException();
	}

}
