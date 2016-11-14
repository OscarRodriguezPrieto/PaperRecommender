package IO.extraccion;

import test.TestSimilitudMedidasExternasws4j;
import test.medidasWS4J;

public class Articulo extends Cita implements Comparable {
	private String titulo;
	private final int anyo;
	private final String[] autores;
	private final String pubVenue;
	private String abstrac;
	
	
	public Articulo(final String id, final String titulo, final int anyo, final String[] autores) {
		super(id);
		this.titulo = titulo;
		this.anyo = anyo;
		this.autores = autores;
		pubVenue = "UNKNOWN";
		abstrac="UNKNOWN";
	}

	public Articulo(final String id, final String titulo, final int anyo, final String[] autores, String pubVenue,String abstrac) {
		super(id);
		this.titulo = titulo;
		this.anyo = anyo;
		this.autores = autores;
		this.pubVenue = pubVenue;
		this.abstrac=abstrac;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnyo() {
		return anyo;
	}

	public String getPubVenue() {
		return pubVenue;
	}

	public String[] getAutores() {
		return autores;
	}

	@Override
	public String toString() {
		String autores = ":";
		for (final String i : this.autores)
			autores += i + ";";
		return /* getId() + "\t" + */titulo/* + "\t(" + anyo + ")" + autores */;
	}

	@Override
	public String getId() {
		return super.getId();
	}

	public void setTitulo(String string) {
		this.titulo = string;
	}

	@Override
	public int compareTo(Object o) {
		double s1 = medidasWS4J.countSinonyms(titulo, TestSimilitudMedidasExternasws4j.comparingTitle);
		double s2 = medidasWS4J.countSinonyms(((Articulo) o).titulo, TestSimilitudMedidasExternasws4j.comparingTitle);
		return s1 < s2 ? 1 : s1 == s2 ? 0 : -1;
	}
}
