package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Datahandler;



public class GeneralPage {
	
	WebDriver driver;
	private String url = "https://barometr.info/";
	private String topMenuXpath = "/html/body/div/nav[1]/div/ul/li[";  
	private String buttomMenuXpath = "/html/body/div/nav[2]/div/ul/li[";
	
	public GeneralPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	

	@FindBy(xpath= "/html/body/div/header/div/a")
	WebElement logo;
	
	@FindBy(xpath="/html/body/div/header/div/div/div[1]")
	WebElement live;
	
	@FindBy(xpath="/html/body/div/header/div/div/div[2]")
	WebElement weather;
	
	@FindBy(className = "mainmenu")
	WebElement mainMenu;
	
	@FindBy(className = "bottom-menu")
	WebElement bottomMenu;
	
	@FindBy(css="input.form-text")
	WebElement search;
	
	@FindBy(css="body > div > section.maincontent > div > aside.rightcol > div:nth-child(7) > div > div > form > input.enter.btn")
	WebElement enterButton;
	
	@FindBy(css="body > div > section.maincontent > div > aside.rightcol > div:nth-child(7) > div > div > form > div:nth-child(1) > input")
	WebElement inputName;
	
	@FindBy(css="body > div > section.maincontent > div > aside.rightcol > div:nth-child(7) > div > div > form > div:nth-child(2) > input")
	WebElement inputPassword;
	
	@FindBy(xpath="/html/body/div/section[2]/div/aside[2]/div[7]/h3")
	WebElement personalCabinetButton;
	
	@FindBy(xpath="/html/body/div/section[2]/div/aside[2]/div[7]/div/div/a[2]")
	WebElement informationAboutMe;
	
	@FindBy(xpath="/html/body/div/section[2]/div/aside[2]/div[7]/div/div/a[4]")
	WebElement exitFromPersonalCabinet;
	
	@FindBy(xpath="//*[@id=\"calendar\"]")
	WebElement calendarTable;
	
	@FindBy(xpath="/html/body/div[1]/section[2]/div/aside/div[6]")
	WebElement archiveByMonth;
	
	@FindBy(xpath="//*[@id=\"dle_news_archive_link\"]/a")
	WebElement openArchivebyMonth;
		
		
	
	public void start()  throws InterruptedException {
		driver.get(url);	
		Thread.sleep(5000);
	}
	
	public List<WebElement> getButtonsOfMainMenu (){
		return mainMenu.findElements(By.tagName("li"));
	}
	
	public List<WebElement> getButtonsOfBottomMenu(){
		return bottomMenu.findElements(By.tagName("li"));
	}
	
	public boolean navigationTopMenu(int x, String expectedUrl, String expectedTitle, String buttonText) throws InterruptedException {
		boolean result = true;
		System.out.println("Push the button "  +driver.findElement(By.xpath(topMenuXpath+x+"]")).getText()+" of TopPanel");
		driver.findElement(By.xpath(topMenuXpath+x+"]")).click();
		if(driver.getCurrentUrl().equals(expectedUrl)==false) {
			result=false;			 
		}
		if(driver.getTitle().equals(expectedTitle)==false) {
			result=false;
		}
		if(driver.findElement(By.xpath(topMenuXpath+x+"]")).getText().equals(buttonText)==false) {
			result=false;
		}
		driver.findElement(By.xpath(topMenuXpath+1+"]")).click();
		Thread.sleep(500);
		return result;
	}
	
	public boolean navigationBottomMenu(int x, String expectedUrl, String expectedTitle, String buttonText) {
        boolean result = true;
        String description;
		System.out.println("Push the button "  +driver.findElement(By.xpath(buttomMenuXpath+x+"]")).getText()+" of BottomPanel");
		driver.findElement(By.xpath(buttomMenuXpath+x+"]")).click();
		if(driver.getCurrentUrl().equals(expectedUrl)==false) {
			result=false;
			System.out.println("After click on "+x+"st button we went to "+driver.getCurrentUrl()+"instead "
			+expectedUrl);
		}
		if(driver.getTitle().equals(expectedTitle)==false) {
			result=false;
			System.out.println("After click on "+x+"st button the page we went to has title "+driver.getTitle()+
			"instead " +expectedTitle);
		}
		if(driver.findElement(By.xpath(buttomMenuXpath+x+"]")).getText().equals(buttonText)==false) {
		result=false;
		System.out.println(x+"st button has a text "+driver.findElement(By.xpath(buttomMenuXpath+x+"]")).getText()+
		"instead " +buttonText);
		}
		System.out.println("We are on the page "+driver.getCurrentUrl());
		driver.navigate().back();
		return result;
	}
	
    public List<String> searching(String keyword) throws AWTException{
    	boolean result=true;
    	search.sendKeys(keyword);
    	WebElement content = driver.findElement(By.cssSelector("body > div > section.maincontent > div > div > div"));
		List<WebElement> searchedArticles = content.findElements(By.tagName("a"));		 
		List<String> searchedHrefs = new ArrayList<String>();		
		for(WebElement e:searchedArticles) {		 
			searchedHrefs.add(e.getAttribute("href"));			 
		}
				 
		return searchedHrefs;
    }
	
	public void authorization(String username, String password) {
		inputName.sendKeys(username);
		inputPassword.sendKeys(password);
		enterButton.click();
	}	
		
	public boolean userUnauthorizated() {
		try { 
		return enterButton.isDisplayed(); 
		}catch(NoSuchElementException e) {
		return false;	
		}          
	} 
	
	/**
	 * метод применяется в тест-кейсе General.test4_DataPicker
	 * внутри использует методы: Datahandler.getmonthsBetween2dates и backMonthsInCalendar для того чтобы перейти к нужному месяцу
	 *                           Datahandler.getvalueOfDay() - для получения номера строчки(неделя) и дня недели в строчке для формирования xpath 
	 * этот метод надо сделать булевым после того, как напишу парсер страниц
	 * @param year1 - берется из GeneralData.getDates - дата провайдера
	 * @param month1
	 * @param day1
	 * @throws InterruptedException
	 */
	public void toData(int year1, int month1, int day1) throws InterruptedException {
		Datahandler datahandler = new Datahandler();
		LocalDate today = LocalDate.now();
		LocalDate dateTo = LocalDate.of(year1, month1, day1);
		int x = Math.abs(datahandler.getMonthsBetween2dates(dateTo, today));
		backMonthsInCalendar(x);
		Thread.sleep(1000);
		int [] xpath = datahandler.getValueOfDay(dateTo);
		System.out.println("Хpath: //*[@id=\"calendar\"]/tbody/tr["+xpath[0]+"]/td["+xpath[1]+"]");
		driver.findElement(By.xpath("//*[@id=\"calendar\"]/tbody/tr["+xpath[0]+"]/td["+xpath[1]+"]")).click();		 
	}	
	
	/**
	 * пролистывает месяцы календаря назад в том количестве, которое составляет разница месяцев между сегодняшней датой и заданной датой
	 * Данный метод используется в GeneralPage.toData
	 * @param clicks - разница месяцев между сегодняшней датой и заданной датой. Берется из DataHandler.getMonthsBetween2dates
	 * @throws InterruptedException
	 */
	public void backMonthsInCalendar(int clicks) throws InterruptedException {
		for(int x=0; x<clicks; x++) {
			System.out.println(calendarTable.findElement(By.xpath("//*[@id=\"calendar\"]/tbody/tr[1]/th")).getText());
			driver.findElement(By.xpath("//*[@id=\"calendar\"]/tbody/tr[1]/th/a")).click();
			Thread.sleep(1500);			 
		}
	}
	 	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}	
}



 