package DataProviders;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class GeneralData {

	@DataProvider(name = "Navigation")
	public static Object[][] getControlValuesForPages(){
		return new Object[][] {
			{1, "https://barometr.info/", "Барометр Южноукраинска", "ГЛАВНАЯ"},
			{2, "https://barometr.info/news/n_yu/", "Новости в Южноукраинске » Барометр Южноукраинска",
			"НОВОСТИ"},
			{3, "https://barometr.info/stat/", "Статья » Барометр Южноукраинска", "СТАТЬИ"},
			{4, "https://barometr.info/news/sport/", "Спорт » Барометр Южноукраинска", "СПОРТ"},
			{5, "https://barometr.info/news/polit/", "политика » Барометр Южноукраинска", "ПОЛИТИКА"},
			{6, "https://barometr.info/news/kylt/", "Культура » Барометр Южноукраинска", "КУЛЬТУРА"},
			{7, "https://barometr.info/news/foto/", "Фоторепортаж » Барометр Южноукраинска", "ФОТОРЕПОРТАЖ"},
			{8, "https://barometr.info/video/", "Видео » Барометр Южноукраинска", "ВИДЕО"},
			{9, "https://barometr.info/blog/", "Блог » Барометр Южноукраинска", "БЛОГ"},
			{10, "https://barometr.info/yuaes/", "ЮУ АЭС » Барометр Южноукраинска", "ЮУ АЭС"},
			
		};
		
	}
	
	@DataProvider(name="Authorization")
	public static Object [][] getAutthorizationData() {
		return new Object [][] {
			{"ПівникПєтя", "ujdbylf1998", true},
			{"МухтарСаханов", "голова", true},
			{"МухамедАлыча", "ujdbylf1998", false}			
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
