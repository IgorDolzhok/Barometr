package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Common.Datahandler;
import Common.Parser;

public class StartPage {

	WebDriver driver;
	private String url = "https://barometr.info/";
	private String xpathForTopNews = "/html/body/div/section[2]/div/aside[1]/div[1]/div/div[1]/div[1]/div/div[";
	private String xpathForPhotoreport = "/html/body/div[1]/section[2]/div/aside[2]/div[4]/div/div[1]/div[1]/div/div[";
	private String xpathForVideoBlock ="/html/body/div[1]/section[2]/div/aside[1]/div[4]/div/div[1]/div[1]/div/div[";
	
	public StartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="/html/body/div/section[2]/div/aside[1]/div[1]/div")
	WebElement topNewsBlock;
	
	@FindBy(xpath="/html/body/div/section[2]/div/aside[2]/div[4]")//(xpath="/html/body/div[1]/section[2]/div/aside/div[4]")
	WebElement photoreportBlock;
	
	@FindBy(xpath="/html/body/div/section[2]/div/aside[1]/div[4]")
	WebElement videoBlock;
	
	public WebElement getTopNewsBlock() {
		return topNewsBlock;
	}
	public void setTopNewsBlock(WebElement topNewsBlock) {
		this.topNewsBlock = topNewsBlock;
	}
	public WebElement getPhotoreportBlock() {
		return photoreportBlock;
	}
	public void setPhotoreportBlock(WebElement photoreportBlock) {
		this.photoreportBlock = photoreportBlock;
	}
	public WebElement getVideoBlock() {
		return videoBlock;
	}
	public void setVideoBlock(WebElement videoBlock) {
		this.videoBlock = videoBlock;
	}
	public void start( ) throws AWTException {
		driver.get(url);		 
	}
	/**
	 * 
	 * @return List of button for click-viewing TopNews
	 */
	public List<WebElement> getSquares(WebElement block){
		return block.findElements(By.className("bx-pager-item"));
	}
	
	/**
	 * нужно придумать аналогию этому методу, чтобы проверять методом клика, но пока проверяем парсером
	 * @param block
	 * @param article
	 * @param blockAtributes
	 * @return
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public boolean checkBlockContent (Article article, String [][] blockAtributes) throws InterruptedException, IOException {
		boolean result;		
		int count=0;
		for(int x=0; x<blockAtributes.length; x++) {			 
			//driver.navigate().to(blockAtributes[x][2]);	
			if(article.checkTitleWiaParser(blockAtributes[x][2], blockAtributes[x][0])==false) {
				count++;
			}			 
		}
		driver.navigate().to(url);
		if(count>0) {
			
			result=false;
		}else {
			result=true;
		}
		return result;
	 }
	
	public String[][] getAttributesOfBlock(WebElement block, String xpath) throws InterruptedException{
		List<WebElement> buttons = getSquares(block);
		String [][] topNews = new String [buttons.size()][3];
		for(int x=0; x<buttons.size(); x++) {
			buttons.get(x).click();
			Thread.sleep(500);
			topNews[x][0] = driver.findElement(By.xpath(xpath+(x+2)+"]/div/div[2]")).getText();
			topNews[x][1]= driver.findElement(By.xpath(xpath+(x+2)+"]/div/div[1]/a/img")).getAttribute("src");
			topNews[x][2]= driver.findElement(By.xpath(xpath+(x+2)+"]/div/div[2]/a")).getAttribute("href");
			 
			}	
		return topNews;
	}

	public String[][] getAttributesOfTopNews() throws InterruptedException{
		return getAttributesOfBlock(topNewsBlock, xpathForTopNews);
	}
	
	public String[][] getAttributesOfVideoBlock() throws InterruptedException{
		return getAttributesOfBlock(videoBlock, xpathForVideoBlock);		 	
	}
	
	public String[][] getAttributesOfPhotoreport() throws InterruptedException{
		return getAttributesOfBlock(photoreportBlock, xpathForPhotoreport);		 
	}
	
	public boolean checklinks(String xpath, String expectedUrl, String expectedtitle) throws InterruptedException {
		boolean result= true;
		driver.findElement(By.xpath(xpath)).click();
		Thread.sleep(500);
		if(driver.getCurrentUrl().equals(expectedUrl)==false||driver.getTitle().equals(expectedtitle)==false) {
			System.out.println("Link "+driver.findElement(By.xpath(xpath)).getText()+ "lead to wrong page. \nExpected url: "+expectedUrl+" ;\n Actual url: "+driver.getCurrentUrl()
			+";\nExpected title: "+expectedtitle+ ";\nActual title"+driver.getTitle());
			result=false;
		}

		driver.navigate().to(url);
		return result;
	}
	
	public boolean checkDataColorInCurrentCalendar() {
		boolean result=true;
		Datahandler datahandler = new Datahandler();		 
		int [] xpathYesterday = datahandler.getValueOfDay(LocalDate.now().minusDays(1));
		int [] xpathToday = datahandler.getValueOfDay(LocalDate.now());
		int [] xpathTomorrow = datahandler.getValueOfDay(LocalDate.now().plusDays(1));
		System.out.println(xpathYesterday[0]+ " "+xpathYesterday[1]+"\n"+xpathToday[0]+" "+xpathToday[1]+" "
				+xpathTomorrow[0]+" "+xpathTomorrow[1]);
		
		String yesterdayColor = driver.findElement(By.xpath("//*[@id=\"calendar\"]/tbody/tr["+xpathYesterday[0]+"]/td["+xpathYesterday[1]+"]"))
        		.getCssValue("background-color");
		String todayColor = driver.findElement(By.xpath("//*[@id=\"calendar\"]/tbody/tr["+xpathToday[0]+"]/td["+xpathToday[1]+"]"))
        		.getCssValue("background-color");
		String tomorrowColor = driver.findElement(By.xpath("//*[@id=\"calendar\"]/tbody/tr["+xpathTomorrow[0]+"]/td["+xpathTomorrow[1]+"]"))
        		.getCssValue("background-color");
		if(yesterdayColor.equals("rgba(217, 217, 217, 1)")==false) {
			result=false;
			System.out.println("Вчера неправильный цвет");
		}
		if(todayColor.equals("rgba(51, 0, 204, 1)")==false) {
			result=false;
			System.out.println("Сегодня неправильный цвет");
		}
		if(tomorrowColor.equals("rgba(0, 0, 0, 0)")==false) {
			result=false;
			System.out.println("Завтра неправильный цвет");
		}
        return result;
	}
	
	public boolean checkCoordinates(String webelement, String xpath, int y, int x) {
		boolean result = true;
		if(driver.findElement(By.xpath(xpath)).getLocation().getY()!= y||driver.findElement(By.xpath(xpath)).getLocation().getX()!=x) {
			System.out.println(webelement+" находится в неправильных координатах: "+driver.findElement(By.xpath(xpath)).getLocation().getY() +" , "+
					driver.findElement(By.xpath(xpath)).getLocation().getX());
			result = false;
		}
		return result;
	}
	/**
	 * экспериментирую с парсингом страницы по её адресу
	 * @param article
	 * @param blockAtributes
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public void parseBlockContent(Parser parser, String [][] blockAtributes) throws InterruptedException, IOException {
				
		int count=0;
		for(int x=0; x<blockAtributes.length; x++) {
			 parser.getTitleOfArticle(blockAtributes[x][2]);
			 
		}		 
	 }
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	}
	
	
	 
	
	 
	
	


