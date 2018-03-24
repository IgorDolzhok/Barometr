package Suites;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Common.Result;
import Common.Runner;
import Common.Table;

public class Starter {
   public static ExtentReports extentReportFirefox;
   public static ExtentReports extentReportChrome;
   public static List<Result> results;
   
	
	    
	public static void main(String[] args) throws IOException {
		
		Starter.extentReportChrome = new ExtentReports("test-output/customExtentReportChrome.html", true);
		Starter.extentReportFirefox = new ExtentReports("test-output/customExtentReportFirefox.html",true);
		Starter.results = new ArrayList();
		Table table= new Table();
		Starter.extentReportChrome.loadConfig(new File("extent-config.xml"));
		Starter.extentReportFirefox.loadConfig(new File("extent-config.xml"));
		System.out.println("Поехали");
		Runner runner = new Runner();
		runner.runner();
		//TestNG testng = new TestNG();
		
		
		 
		//List<String> suites = new ArrayList();
		//suites.add("Crossbrowser.xml");//path to xml..
		//testng.setTestSuites(suites);
		//testng.run();
		Starter.extentReportChrome.flush();
		Starter.extentReportFirefox.flush();
		Starter.extentReportChrome.close();
		Starter.extentReportFirefox.close();
		table.createTetable(Starter.results);
	}
}



/*if(Desktop.isDesktopSupported()) {
desktop = Desktop.getDesktop();
}

if(desktop!=null) {
try {
	desktop.open(new File("/test-output/emailable-report.html"));
} catch (IOException e) {
	e.printStackTrace();
}
}

System.out.println("Приехали");*/