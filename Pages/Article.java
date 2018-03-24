package Pages;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common.Parser;

public class Article {
	
 	WebDriver driver; 	 
 	
 	String link;
 	
 	List<String> imagesLinks;
	
	@FindBy(css = "#dle-content > div.short-block > h1") //#dle-content > div.short-block > h1
	WebElement title;
	
	@FindBy(xpath="//*[@id=\"dle-content\"]/div[1]/div[2]/div[3]/a/img")
	WebElement picture;                 
	
	@FindBy(css="#dle-content > div.short-block > div.full-text")
	WebElement text;
	
	@FindBy(css="#dle-content > div.short-block > div.article_info.group > div.comments_info > span")
	WebElement commentsQuantity;
	
	@FindBy(css="#dle-comments-list")
	WebElement commentBlock;	
	
	@FindBy(css="#dle-content > div.short-block > div.article_info.group > div.date_info")
	WebElement date;
	
	@FindBy(css="#dle-content > div > div.article_info.group > div.comments_info")
	WebElement commentsQuontity;
	
	@FindBy(css="#dle-comments-form > table > tbody > tr:nth-child(3) > td > input[type=\"image\"]")
	WebElement addCommentButton;
	
	@FindBy(xpath="//*[@id=\"dle-content\"]/div[2]")
	WebElement noComment;	
	
	public Article(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	public String getTextOfTitle() {
		return title.getText();
	}
	
	public String getTextOfArticle() {
		return text.getText();
	}
	
	public String getImageHref() {
		return picture.getAttribute("src");
	}
	
	public List<WebElement> getComments() {
		return commentBlock.findElements(By.xpath("//*[starts-with(@id, 'comment-id')]"));
	}
	
	public String getLink() {
		return driver.getCurrentUrl();
	}
	
	public List<String> getImagesLinks(){
		 List<WebElement> images = driver.findElements(By.xpath("//*[@class=\"full-text\"]//img"));
		 List<String> imagesLinks = new ArrayList();
		 for(WebElement e: images) {
			 imagesLinks.add(e.getAttribute("src"));
		 }
		 return imagesLinks;
	}
	
	public void toPrintImagesLinks() {
		for(String e: getImagesLinks()) {
			System.out.println(e);
		}
	}
	
	public boolean checkWord(String word) {
		boolean result=false;
		String fullText = getTextOfTitle()+getTextOfArticle();
		if(fullText.toLowerCase().contains(word.toLowerCase())==true) {
			result=true;
			System.out.println(getTextOfTitle()+" соответствует критериям поиска");
		}
		System.out.println(getTextOfTitle()+" не соответствует критериям поиска");
		return result;		
	}
	 
	public String getDate() {
		String day;
		try {
		if(date.getText().substring(1, 3).equalsIgnoreCase("ег")==false) {
			day = date.getText().substring(6,10)+"-"+date.getText().substring(3,5)+"-"+date.getText().substring(0,2);
		}else {
			day = LocalDate.now().toString();
		}
		}catch(NoSuchElementException nse) {
			day="2000-01-01";
		}
		 
		return day;
	}

	public WebElement getPicture() {
		return picture;
	}
	
	public WebElement getTitle() {
		return title;
	}

	public String getQuantityOfComments() {
		//int x= Integer.parseInt(commentsQuontity.getText().substring(12));
		String quantity = commentsQuontity.getText().substring(13);
		return quantity; 
	}

	public void createComment() {
		driver.switchTo().frame("idContentoEdit0");
		driver.findElement(By.cssSelector("body")).sendKeys("Да");
		driver.switchTo().defaultContent();
		addCommentButton.click();
	} 
	
	public boolean notAvailableComments() {
		try { 
		return noComment.isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean checkTitleWiaParser(String url, String expectedTitle) throws IOException {
		boolean result=true;
		Parser parser = new Parser();
		 if(expectedTitle.equals(parser.getTitleOfArticle(url))==false) {
			 System.out.println("Found discrepancy of titles.\n Actual result: "+getTextOfTitle()+"\n"
		     +"Expected results: "+expectedTitle+"\n\n");
			 result=false;
		 }		  
		return result;
	}
	
	public void checkDataViaParser(String url, int day, int month, int year) throws IOException {
		Parser parser = new Parser();
		List<String> dayMonthYear = parser.getDataDay(url);
		System.out.println(dayMonthYear.get(0)+" = "+Integer.toString(day)+" ; "+dayMonthYear.get(1)+" = "
		                  +Integer.toString(month)+" ; "+dayMonthYear.get(2)+" = "+Integer.toString(year)+" ; ");
		
		
	}
	  
	
	
	 
 	
	

}
