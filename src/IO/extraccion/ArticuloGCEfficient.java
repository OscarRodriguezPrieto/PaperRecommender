package IO.extraccion;


public class ArticuloGCEfficient {
	
	private String titulo;
	private final int anyo;
	private final String[] autores;
	private final String pubVenue;
	private String abstrac;
	private String id;
	private ArticuloGCEfficient[] citas;
	
	public ArticuloGCEfficient(String titulo, int anyo, String[] autores, String pubVenue, String abstrac, String id) {
		super();
		this.titulo = titulo;
		this.anyo = anyo;
		this.autores = autores;
		this.pubVenue = pubVenue;
		this.abstrac = abstrac;
		this.id = id;
		this.citas = citas;
	}

	public ArticuloGCEfficient[] getCitas() {
		return citas;
	}

	public void setCitas(ArticuloGCEfficient[] citas) {
		this.citas = citas;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnyo() {
		return anyo;
	}

	public String[] getAutores() {
		return autores;
	}

	public String getPubVenue() {
		return pubVenue;
	}

	public String getAbstrac() {
		return abstrac;
	}

	public String getId() {
		return id;
	}
	
}
