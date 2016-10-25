package IO.extraccion;

public class EnvoltorioCadena {

	private String cadena;

	public EnvoltorioCadena(String cadena) {
		super();
		this.cadena = cadena;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadena == null) ? 0 : cadena.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnvoltorioCadena other = (EnvoltorioCadena) obj;
		if (cadena == null) {
			if (other.cadena != null)
				return false;
		} else if (!cadena.trim().contentEquals(other.cadena.trim()))
			return false;
		return true;
	}

}
