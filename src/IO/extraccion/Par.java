package IO.extraccion;

public class Par<T> {
	private T a;
	private T b;
	private Object arg;

	public Par(T a, T b, Object arg) {
		super();
		this.a = a;
		this.b = b;
		this.arg = arg;
	}

	public Par(T a, T b) {
		super();
		this.a = a;
		this.b = b;
	}

	public Object getArg() {
		return arg;
	}

	public void setArg(Object arg) {
		this.arg = arg;
	}

}
