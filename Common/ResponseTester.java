package Common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResponseTester {
	
	public boolean checkURLsOfPage(WebDriver driver, String baseUrl) throws IOException {
		boolean result1 = true;
		String result = new String();
		String url1 = "";
		int emptyLinks = 0;
		int badResponseLinks = 0;
		int linksForeign = 0;
		int respCode = 0;
		HttpURLConnection huc = null;
		List <WebElement> links = driver.findElements(By.tagName("a"));
		Iterator<WebElement> it = links.iterator();
		while(it.hasNext()) {
			url1= it.next().getAttribute("href");
			if(url1==null||url1.isEmpty()) {
				emptyLinks++;
				continue;
			}
			if(!url1.startsWith(baseUrl)) {
				System.out.println("URL belongs to another domain, fuck it");
				linksForeign++;
				continue;
			}
			try {
				huc = (HttpURLConnection)(new URL(url1).openConnection());
				huc.setRequestMethod("HEAD");
				respCode = huc.getResponseCode();
				if(respCode>=400) {
					System.err.println(url1+ " is a broken link with code "+respCode);
					result1=false;
					//badResponseLinks++;
				} 
			}catch(MalformedURLException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
			//if(!it.hasNext()) {
			//result = "Due testing "+baseUrl+" were found "+emptyLinks+" empty links, "+linksForeign+" links belonged to another domain, "+
	          //      badResponseLinks+" links with bad response";
		    
			//}
		}
		return  result1;
		
	}

	public ResponseTester() {
		super();
		 
	}  

}
