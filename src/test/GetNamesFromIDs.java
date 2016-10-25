package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import IO.extraccion.Cita;
import IO.extraccion.ExtractorACL;
import IO.extraccion.ExtractorTotal;

public class GetNamesFromIDs {
	private static final String FILE_IN = "acl_data/ids.txt", FILE_OUT = "acl_data/paperNameList.txt";

	@Test
	public void test() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(FILE_IN)));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(FILE_OUT)));
		List<Cita> citas = ExtractorTotal.extraerCitas();
		while (br.ready()) {
			String[] line = br.readLine().split("\t");
			bw.write(ExtractorACL.getArticulo(citas, line[0]) + "\t" + ExtractorACL.getArticulo(citas, line[1]) + "\n");
		}
		bw.close();
		br.close();
	}

}
