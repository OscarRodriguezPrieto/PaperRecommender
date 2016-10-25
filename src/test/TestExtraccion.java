package test;

import java.io.IOException;
import java.util.List;

import IO.extraccion.Articulo;
import IO.extraccion.Cita;
import IO.extraccion.ExtractorTotal;
import junit.framework.TestCase;

public class TestExtraccion extends TestCase {

	public void test() throws NumberFormatException, IOException {
		final List<Cita> articulos = ExtractorTotal.extraerCitas();
		int i = 0;
		for (Cita c : articulos)
			for (Cita c2 : c.getCitas())
				if (c2.getId().contentEquals("J93-2003"))
					for (Cita c3 : c.getCitas())
						if (c3.getId().contentEquals("J90-2002"))
							System.out.println(++i + "º\t" + c.getId() + "\t" + ((Articulo) c).getTitulo());

	}
}
