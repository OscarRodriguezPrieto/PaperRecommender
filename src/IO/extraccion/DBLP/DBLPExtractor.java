package IO.extraccion.DBLP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import IO.extraccion.Articulo;
import IO.extraccion.ArticuloGCEfficient;
import IO.extraccion.ExtractorACL;
import IO.extraccion.lex.Tuple;

public class DBLPExtractor {
	private static final String IN_FILE = "dblp.txt";

	private static Map<String, Tuple<ArticuloGCEfficient,String []>> refsMap = new HashMap<String, Tuple<ArticuloGCEfficient, String[]>>();

	
	private static String[] getLines(int nLines) throws IOException{
		String[] lines=new String[nLines];
		BufferedReader br = ExtractorACL.getReader(IN_FILE);
		for(int i=0;i<nLines;i++)
			lines[i]=br.readLine();
		return lines;
	}
	
	public static ArticuloGCEfficient getPaperById(String id){
		return refsMap.get(id).first;
	}
	public static List<ArticuloGCEfficient> getPaperList() throws IOException {
	//	int totalLines=28028094;
	//	String[] lines=getLines(totalLines);
		BufferedReader br = ExtractorACL.getReader(IN_FILE);
	//	System.out.println("LINES EXTRACTED");
		List<ArticuloGCEfficient> paperList = new ArrayList<ArticuloGCEfficient>();
		
		while (br.ready())
			paperList.add(extractArticle(br));
		for (ArticuloGCEfficient a : paperList) {
			Tuple<ArticuloGCEfficient,String[]> paperInfo = refsMap.get(a.getId());
			ArticuloGCEfficient[] citas = new ArticuloGCEfficient[paperInfo.second.length];
			//System.out.println("Citas de :"+paperInfo.first.getId());
			for (int i = 0; i < citas.length; i++){
				//System.out.println(i+":"+paperInfo.second.get(i));
				citas[i] = refsMap.get(paperInfo.second[i]).first;
				
			}
			a.setCitas(citas);
		}

		return paperList;
	}

	private static ArticuloGCEfficient extractArticle(BufferedReader br) throws IOException {
		
		String line = br.readLine();
		//System.out.println(line);
		String title = line.substring(2);
		//System.out.println(title);
		line = br.readLine();
		String[] authors = line.contains("@") ? line.substring(2).trim().split(",") : new String[] {};
		if (authors.length > 0)
			line = br.readLine();
		//System.out.println(line);
		int year = Integer.parseInt(line.substring(2));
		//System.out.println(year);
		line = br.readLine();
		String pubVenue = line.contains("#c") ? line.substring(2) : "UNKNOWN";
		if (line.contains("#c"))
			line = br.readLine();
		String id = line.substring(6);

		List<String> refs = new ArrayList<String>();

		while ((line = br.readLine()).contains("#%"))
			refs.add(line.substring(2));

		String abstrac = "UNKNOWN";
		if (line.contains("#!")) {
			abstrac = line.substring(2);
			line = br.readLine();
		}

		ArticuloGCEfficient articulo = new ArticuloGCEfficient(title, year, authors, pubVenue, abstrac, id);
		String[] aux=new String[refs.size()];
		refsMap.put(id, new Tuple<ArticuloGCEfficient, String[]>(articulo,refs.toArray(aux)));
		return articulo;
	}
}
