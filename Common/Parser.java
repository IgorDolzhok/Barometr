package Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {

	public Parser() {
		super();		 
	}
	
	public String getTitleOfArticle(String url/*, String selector, String expectedTitle*/) throws IOException {
		 Document doc = Jsoup.connect(url).get();
		 return doc.select("#dle-content > div > h1").text();		 
	}
	
	public void getData(String url) throws IOException{
		Document doc = Jsoup.connect(url).get();
		String data = doc.select("#dle-content > div.short-block > div.article_info.group > div.date_info").text();
		System.out.println(data);
	} 
	
	public List<String> getDataDay(String url) throws IOException{
		List<String> dD_MM_YYYY = new ArrayList();
		Document doc = Jsoup.connect(url).get();
		String data = doc.select("#dle-content > div.short-block > div.article_info.group > div.date_info").text();
		dD_MM_YYYY.add(data.substring(0, 1));
		dD_MM_YYYY.add(data.substring(3, 4));
		dD_MM_YYYY.add(data.substring(6, 9));
		return dD_MM_YYYY;
	}
	
	
	 

}
