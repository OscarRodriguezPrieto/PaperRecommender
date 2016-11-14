package test.dblp;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import IO.extraccion.ArticuloGCEfficient;
import IO.extraccion.DBLP.DBLPExtractor;

public class DBLPExtractionTest {

	@Test
	public void test() throws IOException {
		DBLPExtractor.getPaperList();
		ArticuloGCEfficient art = DBLPExtractor.getPaperById("53e9b91eb7602d9704505777");
		System.out.println(art.getTitulo());
		for (ArticuloGCEfficient a : art.getCitas())
			System.out.println(a.getTitulo() + "(" + a.getId() + ")");
	}

}
