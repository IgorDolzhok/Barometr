package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common.Parser;

public class SetOfArticles {
	WebDriver driver;
	private String url;
	
	@FindBy(xpath="//*[@id=\"dle-content\"]")
    WebElement selectedContent;	
	
	public SetOfArticles(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void start() {
		driver.get(url);
	}
	
	public List<WebElement> getArticlesList () {
		List<WebElement> selectedArticles = selectedContent.findElements(By.tagName("a"));// - неправильный селектор
		//System.out.println(selectedArticles.size());
		return selectedArticles;
	}
	
	public String [][] getArticleAttributes() {
		String [][] articlesAttributes = new String[getArticlesList().size()][2];
		for(int x=0; x<getArticlesList().size(); x++) {
			articlesAttributes [x][0]= getArticlesList().get(x).getAttribute("href");
			articlesAttributes [x][1]= getArticlesList().get(x).getText();
		}
		return articlesAttributes;
	}
	
	public void toStringPresenceArticles(Article article, Parser parser) throws IOException {
				 
		String [][] articlesAttributes = getArticleAttributes();
		for(int x = 0; x<articlesAttributes.length; x++) {
			System.out.println("Стаья №"+(x+1)+" :\n");
			System.out.println("Ссылка: "+ articlesAttributes[x][0]);
			System.out.println("Название статьи: "+ articlesAttributes[x][1]);
			parser.getData(articlesAttributes[x][0]);
		}			
	}
	
	public boolean checkSelectedArticlesData(Parser parser, int day, int month, int year) throws IOException {
		boolean result=true;
		int count = 0;
		String [][] articlesAttributes = getArticleAttributes();
		for(int x=0; x<articlesAttributes.length; x++) {
			if(parser.getDataDay(articlesAttributes[x][0]).get(0).equals(Integer.toString(day))       ||
				parser.getDataDay(articlesAttributes[x][0]).get(1).equals(Integer.toString(month))    ||
				parser.getDataDay(articlesAttributes[x][0]).get(2).equals(Integer.toString(year))) {
				System.err.println("Статья "+ articlesAttributes[x][1]+" за "+day+"-"+month+"-"+year+" содержит неправильную дату");
				count++;
			};
		}
		if(count>0) {
			result=false;
		}
		return result;
	}
	
	
	
}
