package IO.extraccion.lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Extractor {

	public static BufferedReader getReaderFor(File file) throws FileNotFoundException {
		return new BufferedReader(new FileReader(file));
	}

	public static BufferedReader getReaderFor(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
