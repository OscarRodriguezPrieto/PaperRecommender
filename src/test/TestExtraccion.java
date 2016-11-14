package test;

import java.io.IOException;
import java.util.List;

import IO.extraccion.Articulo;
import IO.extraccion.Cita;
import IO.extraccion.ExtractorTotalACL;
import IO.extraccion.Referencer;
import junit.framework.TestCase;

public class TestExtraccion extends TestCase {

	public void test() throws NumberFormatException, IOException {
		final List<Referencer> articulos = ExtractorTotalACL.extraerCitas();
		int i = 0;
		for (Referencer c : articulos)
			for (Referencer c2 : c.getCitas())
				if (c2.getId().contentEquals("J93-2003"))
					for (Referencer c3 : c.getCitas())
						if (c3.getId().contentEquals("J90-2002"))
							System.out.println(++i + "º\t" + c.getId() + "\t" + ((Articulo) c).getTitulo());

	}
}
