package test;

import java.io.IOException;
import java.util.List;

import model.MedidasSimilitud;
import model.Similitud;

import org.junit.Test;

import IO.extraccion.Articulo;
import IO.extraccion.ExtraerSoloArticulos;

public class TestSimilitud {

	@Test
	public void test() throws NumberFormatException, IOException {
		System.out.println(Similitud
				.sinPalabrasVacias("I am with you Structure Ana"));
		List<Articulo> art = ExtraerSoloArticulos.extraerArticulos();
		System.out.println(Similitud.semejanzaTitulo(art.get(2), art.get(1)));
		System.out.println(art.size());
		MedidasSimilitud.medidasSimilitudPara(art, 1, 0);
	}
}
