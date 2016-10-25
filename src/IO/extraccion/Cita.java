package IO.extraccion;

import java.util.ArrayList;
import java.util.List;

public class Cita {

	private String id;
	private List<Cita> citas;

	public Cita(String id) {
		super();
		this.id = id;
		citas = new ArrayList<Cita>();
	}

	public String getId() {
		return id;
	}

	public void addCita(Cita a) {
		citas.add(a);
	}

	public List<Cita> getCitas() {
		return citas;
	}

}
