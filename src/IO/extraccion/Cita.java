package IO.extraccion;

import java.util.ArrayList;
import java.util.List;

public class Cita implements Referencer {

	private String id;
	private List<Referencer> citas;
	
	
	public Cita(String id) {
		super();
		this.id = id;
		citas = new ArrayList<Referencer>();
	}

	public String getId() {
		return id;
	}

	public void addCita(Referencer a) {
		citas.add(a);
	}

	public List<Referencer> getCitas() {
		return citas;
	}

}
