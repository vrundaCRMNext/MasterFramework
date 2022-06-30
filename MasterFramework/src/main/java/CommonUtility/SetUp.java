package CommonUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SetUp 
{
	public static WebDriver driver;
	static Logger log = LoggerFactory.getLogger(SetUp.class);

	public void setUpTest(String sheetName)throws Exception
	{
	
		String browserName=CommonMethods.readPropertyFile("Browser");
		log.info("Browser = "+browserName);
		//Using WebDriver manager
		if(browserName.equalsIgnoreCase("Chrome"))
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			log.info("Chrome Browser Launched..");
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(firefoxOptions);
			log.info("Firefox Browser Launched..");
		}
		
		String URL =ExcelOperation.getCellData(sheetName, "URL", 1);
		driver.get(URL);
		log.info("URL : "+URL);
		driver.manage().window().maximize();
		
	}
	
	//@AfterTest
	public void tearDownTest() 
	{
		//Close Browser		
		  driver.close();
		  driver.quit(); 
		  log.info("Browser Closed..");
	}


}
