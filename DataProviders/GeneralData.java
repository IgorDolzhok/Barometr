package DataProviders;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class GeneralData {

	@DataProvider(name = "Navigation")
	public static Object[][] getControlValuesForPages(){
		return new Object[][] {
			{1, "https://barometr.info/", "�������� �������������", "�������"},
			{2, "https://barometr.info/news/n_yu/", "������� � ������������� � �������� �������������",
			"�������"},
			{3, "https://barometr.info/stat/", "������ � �������� �������������", "������"},
			{4, "https://barometr.info/news/sport/", "����� � �������� �������������", "�����"},
			{5, "https://barometr.info/news/polit/", "�������� � �������� �������������", "��������"},
			{6, "https://barometr.info/news/kylt/", "�������� � �������� �������������", "��������"},
			{7, "https://barometr.info/news/foto/", "������������ � �������� �������������", "������������"},
			{8, "https://barometr.info/video/", "����� � �������� �������������", "�����"},
			{9, "https://barometr.info/blog/", "���� � �������� �������������", "����"},
			{10, "https://barometr.info/yuaes/", "�� ��� � �������� �������������", "�� ���"},
			
		};
		
	}
	
	@DataProvider(name="Authorization")
	public static Object [][] getAutthorizationData() {
		return new Object [][] {
			{"ϳ����Ϻ��", "ujdbylf1998", true},
			{"�������������", "������", true},
			{"������������", "ujdbylf1998", false}			
		};
	}
	
	/**
	 *  
	 * @return
	 */
	@DataProvider(name= "Dates")
	public static Object[][] getDates(){
		return new Object[][] {
			//{2018, 1, 7},
			{2018, 1, 25},
			//{2018, 2, 7}			 
		};
	}
	
	@DataProvider(name= "CrossBrowser")
	public static Object[][] getBrowsers(){
		return new Object[][] {
			{"webdriver.gecko.driver", "Browsers/geckodriver.exe", new FirefoxDriver()},
			{"webdriver.chrome.driver", "Browsers/chromedriver.exe", new ChromeDriver()}
		};
	}
	
	
	
}
