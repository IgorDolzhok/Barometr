package Suites;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Common.Parser;
import Common.Result;
import Database.QueryHandler;
import Pages.Article;
import Pages.GeneralPage;
import Pages.SetOfArticles;
/*
 * надо написать:
 *  - булевый метод для проверки правильности возвращаемых статей
 */
@Test
public class General {
  
	WebDriver driver;
	GeneralPage general;
	Article article;
	SetOfArticles selectedArticles;
	Parser parser;
	String currentDriver;
	String reportPath;
	Result result;
	//ExtentReports extentReport;
	ExtentTest logger;
	
	 
	@BeforeTest 
	@Parameters({"driverName", "driverPath", "browser"})
	public void settings(String driverName, String driverPath, String browser) throws InterruptedException, AWTException {
		System.setProperty(driverName, driverPath);
		if(browser.equalsIgnoreCase("chrome")) {		 
		driver =  new ChromeDriver();
		//reportPath="test-output/customExtentReportChrome.html";
		}else if(browser.equalsIgnoreCase("firefox")){			 
			driver =  new FirefoxDriver();
			//reportPath="test-output/customExtentReportFirefox.html";
		}		
	    general = new GeneralPage(driver);
	    article= new Article(driver);
	    selectedArticles = new SetOfArticles(driver);	  
	    parser=new Parser();
	    general.start();
	    currentDriver = browser;
	   // Starter.extentReportChrome = new ExtentReports(reportPath, true);
	    //extentReport.addSystemInfo("BrowserName", browser);
	    //extentReport.addSystemInfo("TestedPage", general.getUrl());
	    //extentReport.loadConfig(new File("extent-config.xml"));
	    
	}
    
	
	@Test(dataProvider ="Navigation", dataProviderClass = DataProviders.GeneralData.class)
    public void test1_NavigationTop(int x, String expectedUrl, String expectedTitle, String buttonText) throws InterruptedException {
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test1_NavigationTop");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test1_NavigationTop");	
		}	
        List<String> inputParameters = new ArrayList();
        String xString = x+"";
        inputParameters.add(xString); inputParameters.add(expectedUrl); inputParameters.add(expectedTitle); inputParameters.add(buttonText);
		boolean resultOfTest = general.navigationTopMenu(x, expectedUrl, expectedTitle, buttonText);
		result = new Result(currentDriver, "test1_NavigationTop", resultOfTest, "Click on the button of header", inputParameters);
		Starter.results.add(result);//result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription()+buttonText+" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+buttonText+" failed");
		} 
	}
	
	
	@Test(dataProvider ="Navigation", dataProviderClass = DataProviders.GeneralData.class)
    public void test2_NavigationBottom(int x, String expectedUrl, String expectedTitle, String buttonText) {
       // logger = extentReport.startTest("test2_NavigationBottom");
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test2_NavigationBottom");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test2_NavigationBottom");	
		}			
		List<String> inputParameters = new ArrayList();
        String xString = x+" ";
        inputParameters.add(xString); inputParameters.add(expectedUrl); inputParameters.add(expectedTitle); inputParameters.add(buttonText);
		boolean resultOfTest = general.navigationBottomMenu(x, expectedUrl, expectedTitle, buttonText);
		result = new Result(currentDriver, "test2_NavigationBottom", resultOfTest, "Click on the button of footer", inputParameters);
		Starter.results.add(result);//result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription()+buttonText+" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+buttonText+" unsuccesfully");
		}
	}
	
	/*@Test(dataProvider ="Authorization", dataProviderClass = DataProviders.GeneralData.class)
	public void test3_Authorization(String user, String password, boolean ability) {
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test3_Authorization");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test3_Authorization");	
		}				
		List<String> inputParameters = new ArrayList();
		inputParameters.add("Пользователь "+user); inputParameters.add("Пароль "+password);
		if(ability==true) {
			inputParameters.add("Wrong login and password");
		}else{
			inputParameters.add("Right login and password");
		}
		general.authorization(user, password);
		boolean resultOfTest =general.userUnauthorizated()==ability;
		result = new Result(currentDriver, "test3_Authorization", resultOfTest, "Attemption of authorization", inputParameters);
		result.toPrint();
		if(resultOfTest==true) {
			logger.log(LogStatus.PASS, result.getDescription()+" succesfully");
		}else {
			logger.log(LogStatus.FAIL, result.getDescription()+" failed");
		}
		driver.navigate().to(general.getUrl());
	}*/ 
	
	@Test(dataProvider="Dates", dataProviderClass=DataProviders.GeneralData.class)
	public void test4_DataPicker(int year1, int month1, int day1) throws InterruptedException, IOException {
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	logger = Starter.extentReportChrome.startTest("test4_DataPicker");		
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    logger = Starter.extentReportFirefox.startTest("test4_DataPicker");	
	    }				 
		 List<String> inputParameters = new ArrayList();
		 inputParameters.add(Integer.toString(day1));  inputParameters.add(Integer.toString(month1)+" month"); 
		 inputParameters.add(Integer.toString(year1)); 
		 general.toData(year1, month1, day1);
		 System.out.println("Метод тоДата отработал");
		 boolean resultOfTest = selectedArticles.checkSelectedArticlesData(parser, day1, month1, year1);
		 result = new Result(currentDriver, "test4_DataPicker", resultOfTest, "Wiewing news from direct data", inputParameters);
		 Starter.results.add(result);//result.toPrint();
		 if(resultOfTest==true) {
				logger.log(LogStatus.PASS, result.getDescription()+" succesfully");
			}else {
				logger.log(LogStatus.FAIL, result.getDescription()+" failed");
			}
		 driver.navigate().to(general.getUrl());
	}
	
	@AfterMethod
	public void afterMethod() {		 		
		if(currentDriver.equalsIgnoreCase("chrome")){
	    	Starter.extentReportChrome.endTest(logger);	
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    Starter.extentReportFirefox.endTest(logger);	
	    }	
	// extentReport.endTest(logger);
	}
	
	
	@AfterTest
	public void output() {
	  // extentReport.endTest(logger);
	   driver.close();
	   /*if(currentDriver.equalsIgnoreCase("chrome")){
	    	Starter.extentReportChrome.flush();	
	    	Starter.extentReportChrome.close();
		}else if(currentDriver.equalsIgnoreCase("firefox")) {
		    Starter.extentReportFirefox.flush();
		    Starter.extentReportFirefox.close();
	    }*/		   
	   // extentReport.flush();
	   //extentReport.close();
	}
		
}	

/*
 * System.setProperty("webdriver.ie.driver", "D:/Step/Java2/SoftwareTesting/DriversST/IEDriverServer.exe");
 * driver = new InternetExplorerDriver();
 * */
