package Suites;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import Common.Parser;
import Common.ResponseTester;
import Common.Result;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Article;
import Pages.StartPage;

public class StartPageTest {

	WebDriver driver;
	StartPage startPage;
	Article article;
	String browserName;
	ResponseTester responseTester;
	Result result;
	String currentDriver;
	//String reportPath;
	//ExtentReports extentReport;
	ExtentTest logger; 
	
	@BeforeTest
	@Parameters({"driverName", "driverPath", "browser"})
	public void settings(String driverName, String driverPath, String browser) throws AWTException {
		//System.setProperty("webdriver.chrome.driver", "Browsers/chromedriver.exe" );
		System.setProperty(driverName, driverPath);	
		browserName = browser;
		if(browser.equalsIgnoreCase("chrome")) {
		driver =new ChromeDriver();		
		//reportPath="test-output/customExtentReportChrome.html";
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();			 
			//reportPath="test-output/customExtentReportFirefox.html";
		}
		startPage = new StartPage(driver);
		article = new Article(driver);	
		responseTester = new ResponseTester();
		currentDriver = browserName;
		startPage.start();
		// extentReport = new ExtentReports(reportPath, true);
	    // extentReport.addSystemInfo("BrowserName", browser);
	    // extentReport.addSystemInfo("TestedPage", startPage.getUrl());
	    // extentReport.loadConfig(new File("extent-config.xml"));
	}		
	
	/*
	@Test
	public void test1_TopNews() throws InterruptedException, IOException {
		//logger= extentReport.startTest("test2_Photoreports");
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test1_TopNews");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test1_TopNews");	
		}	
		boolean resultOfTest = startPage.checkBlockContent(article, startPage.getAttributesOfTopNews());		
		result = new Result(currentDriver, "test1_TopNews", resultOfTest, "Checking of TOP-news content");
		result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription() +" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+" failed");
		} 
	}	
	
	
	@Test
	public void test2_Photoreports() throws InterruptedException, IOException{
		//logger= extentReport.startTest("test2_Photoreports");
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test2_Photoreports");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test2_Photoreports");	
		}	
		boolean resultOfTest = startPage.checkBlockContent(article, startPage.getAttributesOfPhotoreport());
		result = new Result(currentDriver, "test2_Photoreports", resultOfTest, "Checking of Photoreports content");
		result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription() +" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+" failed");
		} 
	}
	
	@Test
	public void test3_VideoBlock() throws InterruptedException, IOException{
		//logger= extentReport.startTest("test3_Videoblock");
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test3_VideoBlock");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test3_VideoBlock");	
		}	
		boolean resultOfTest= startPage.checkBlockContent(article, startPage.getAttributesOfVideoBlock());
	    result = new Result(currentDriver, "test3_VideoBlock", resultOfTest, "Checking of Videonews content");
		result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription() +" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+" failed");
		} 
	}*/
	
	@Test(dataProvider="Links", dataProviderClass = DataProviders.StartDataProvider.class)
	public void test4_links(String xpath, String expectedUrl, String expectedTitle) throws InterruptedException {
		//logger= extentReport.startTest("test4_Links");
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test4_Links");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test4_Links");	
		}	
		List<String> inputParameters = new ArrayList();
		inputParameters.add(xpath);  inputParameters.add(expectedUrl); inputParameters.add(expectedTitle);
		boolean resultOfTest =startPage.checklinks(xpath, expectedUrl, expectedTitle);
		result= new Result(currentDriver, "test4_Links", resultOfTest, "Checking of main links", inputParameters);
		Starter.results.add(result);//result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription() +" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+" failed");
		} 
	}	 
	
	@Test(dataProvider = "Coordinates", dataProviderClass = DataProviders.StartDataProvider.class)	
	public void test5_coordinates(String elementName, String xpath, int chromeY, int chromeX, int firefoxX, int firefoxY) throws InterruptedException {
		//logger= extentReport.startTest("test5_coordinates");
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test5_coordinates");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test5_coordinates");	
		}	
		List<String> inputParameters = new ArrayList();
		inputParameters.add(elementName); inputParameters.add(xpath);
		boolean resultOfTest=true;		
		if(browserName.equalsIgnoreCase("chrome")) {
			 resultOfTest= startPage.checkCoordinates(elementName, xpath, chromeY, chromeX);
		}else if (browserName.equalsIgnoreCase("firefox")) {
			 resultOfTest=startPage.checkCoordinates(elementName, xpath, firefoxX, firefoxY);
		}
		result= new Result(currentDriver, "test5_coordinates", resultOfTest, "Checking coordinates of main webelements in "+currentDriver,
				           inputParameters);
		Starter.results.add(result);//result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription() +" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+" failed");
		} 
	}
	
	
	@Test
	public void test6_URL() throws IOException {
		//logger= extentReport.startTest("test6_url");
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test6_URL");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test6_URL");	
		}	
		boolean resultOfTest = responseTester.checkURLsOfPage(driver, startPage.getUrl());
	    result = new Result(currentDriver, "test6_URL", resultOfTest, "проверка ссылок");
	    if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription() +" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+" failed");
		} 
	    Starter.results.add(result);//result.toPrint();
	}
	
	@AfterMethod
	public void afterMethod() {		 		
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	Starter.extentReportChrome.endTest(logger);	
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    Starter.extentReportFirefox.endTest(logger);	
	    }			
		//extentReport.endTest(logger);
	}	
	
	@AfterTest
	public void output() {
		driver.close();
	}
}
