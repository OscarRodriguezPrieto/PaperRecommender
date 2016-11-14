package IO.extraccion.lex;

public class Tuple<T1, T2> {

	public T1 first;
	public T2 second;

	public Tuple(T1 el1, T2 el2) {
		this.first = el1;
		this.second = el2;
	}
}
