package CommonUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.UnhandledException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonMethods extends SetUp 
{
	//public static JavascriptExecutor js = (JavascriptExecutor) driver;
	public static JavascriptExecutor js ;
	public static WebDriverWait wait ;
	public static Actions a ;
	public static Logger log = LoggerFactory.getLogger(CommonMethods.class);
	

	public static void Click(WebElement element) 
	{
		try 
		{
			ExWait(element);
			element.click();
			//log.info("Clicked Sucessfully on "+element.getText());
			//TestListeners.extentInfo("Clicked Sucessfully on "+element.getText());
		} catch (Exception e) 
		{
			log.error("Not able to click on "+element.getText() +" Due to "+e.getMessage());
		}
	}

	public static void input(WebElement element, String SheetName, String ColName, int rowNum) throws IOException
	{
		try {
			Click(element);
			element.clear();
			Thread.sleep(1000);
			element.sendKeys(ExcelOperation.getCellData(SheetName,ColName,rowNum));
			log.info("Data Sucessfully entered on "+element.getAttribute("placeholder")+" = "+ExcelOperation.getCellData(SheetName,ColName,rowNum));
			TestListeners.extentInfo("Data Sucessfully entered on "+element.getAttribute("placeholder")+" = "+ExcelOperation.getCellData(SheetName,ColName,rowNum));
		}
		catch(Exception e)
		{
			log.error("Data Not Sucessfully entered on "+element.getAttribute("placeholder")+" due to :"+e.getMessage());
		}
	}

	public static void JSEInput(WebElement element, String value) throws InterruptedException
	{
		ExWait(element);
		//js.executeScript("document.getElementByXpath('" + element + "').value ='" + value + "' ;" );
		js.executeScript("document.getElement(' "+element+" ').value= ' "+value+" ' ; " );

	}
	
	public static String getElementText(WebElement element) throws InterruptedException 
	{
		ExWait(element);
		String txtMsg = element.getText();
		//log.info("Text of Web element :" +txtMsg);
		return txtMsg;
	}

	public static String getElementValue(WebElement element) throws InterruptedException
	{
		ExWait(element);
		String elementValue = element.getAttribute("value");
		//log.info("Value of WebElement :" +elementValue);
		return elementValue;

	}
	public static void ExWait(WebElement element) throws InterruptedException
	{
		wait= new WebDriverWait(driver,Duration.ofSeconds(70));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForURL(String urlContains)
	{
		wait= new WebDriverWait(driver,Duration.ofSeconds(70));
		wait.until(ExpectedConditions.urlContains(urlContains)	);
	}
	
	public static void ExWaitsForWebelements(List<WebElement> ele )
	{
		wait= new WebDriverWait(driver,Duration.ofSeconds(70));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}
	public static void ExWaitWithJS(String urlText)
	{
		//wait until the page return complete as its status
		//wait.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
		//wait.until(ExpectedConditions.stalenessOf(element));
		//wait.until(ExpectedConditions.urlContains(urlText));
		//wait.until(ExpectedConditions.);
	}

	//To highlight selected webelement
	public static void highLight(WebElement element) throws InterruptedException
	{
		ExWait(element);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
	}

	//to scroll down the page by pixel values as Y co-ordiante
	public static void scrollDown(int y) 
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,"+y+")");
	}

	//To scroll down the page by visibility of the element
	public static void scrollByVisibilityofElement(WebElement element)
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()",element);
	}

	//To scroll down the page at the bottom of page.
	public static void scrollAtBottom()
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//Return the complete height of body (page)
	}

	//To select values from dropdown by visible text
	public static void selectByText(WebElement element, String sheetName, String colName, int rowNum) throws InterruptedException 
	{
		Thread.sleep(1000);
		Select sel = new Select(element);
		try {
			//scrollByVisibilityofElement(element);
			ExWait(element);
			sel.selectByVisibleText(ExcelOperation.getCellData(sheetName,colName, rowNum));
			//sel.getFirstSelectedOption();
			log.info(sel.getFirstSelectedOption().getText()+" : Data Sucessfully Selected from dropdown ");
		} catch (Exception e) {
			log.error("Not able to select from dropdown "+element);
		} 
	}

	//To select values from dropdown by its value
	public static void selectByValue(WebElement element, String sheetName, String colName, int rowNum) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		try {
			Select sel=  new Select(element);
			ExWait(element);
			sel.selectByValue(ExcelOperation.getCellData(sheetName, colName, rowNum));
		} catch (Exception e) {
			log.error("Not able to select from dropdown due to "+e.getMessage());
		}
		
	}
	//To select values from dropdown by its index value
	public static void selectByIndex(WebElement element, int index) throws InterruptedException 
	{
		Select sel= new Select(element);
		ExWait(element);
		sel.selectByIndex(index);
	}

	//To handle mouse hover actions
	public static void mouseHover(WebElement Element) throws InterruptedException 
	{
		try {
			a = new Actions(driver);
			ExWait(Element);
			highLight(Element);
			a.moveToElement(Element).perform();	
			log.debug("Mouse hover on "+Element);
		} catch (Exception e) {
			log.error("Unable to mouse hover due to "+e.getStackTrace());
		}
		
	}
	
	//To handle mouse hover actions
		public static void mouseClick(WebElement Element) throws InterruptedException 
		{
			a = new Actions(driver);
			ExWait(Element);
			highLight(Element);
			a.moveToElement(Element).click().perform();	
			log.debug("Mouse Click on "+Element);
		}

	public static void AlertHandle(String action) 
	{
		try {
			if(action.equalsIgnoreCase("accept")) {
				driver.switchTo().alert().accept();
				log.info("Alert accepted succesfully");
			}else if(action.equalsIgnoreCase("dismiss")) {
				driver.switchTo().alert().dismiss();
				log.error("Alert dismissed succesfully");
			}
		}catch(Exception e){
			log.info("Not able to clicked on alert due to "+e.getMessage());
			}
	}
	
	public static void switchToWindow() 
	{
		try {
			//for(String winhandle:driver.getWindowHandles()) {
				//driver.switchTo().window(winhandle);
				//log.info("Swiched to window name"+driver.getTitle()+" with id "+winhandle );
			
			String parentWin = driver.getWindowHandle(); 
	        Set<String> windows = driver.getWindowHandles();	        
	        Iterator<String> it = windows.iterator();	        
	        while(it.hasNext()) 
	        {	            
	            String childWin = it.next();	            
	            if(!parentWin.equals(childWin)) 
	            {
	              	driver.switchTo().window(childWin);	            
	              	//Verify title of the child window
	              	//System.out.println("Window Title = "+driver.getTitle() +"and URL = "+driver.getCurrentUrl() );
					log.info("Swiched to Child window name : "+driver.getTitle()+" || URL :"+ driver.getCurrentUrl() );
	            }
	        }
		}catch(Exception e) {
			log.error("Not able switch to window "+e.getMessage());
		}
	}
	
	public static boolean switchToWindowByTitle(String title)
	{
	    String currentWindow = driver.getWindowHandle(); 
	    Set<String> availableWindows = driver.getWindowHandles(); 
	    if (!availableWindows.isEmpty()) { 
	         for (String windowId : availableWindows) {
	              String switchedWindowTitle=driver.switchTo().window(windowId).getTitle();
	              if ((switchedWindowTitle.equals(title))||(switchedWindowTitle.contains(title))){ 
	                  return true; 
	              } else { 
	                driver.switchTo().window(currentWindow); 
	              } 
	          } 
	     } 
	     return false;
	}
	public static void switchToParentWin() {
		
		try {
			String parentwindow=driver.getWindowHandle();
			driver.switchTo().window(parentwindow);
			log.info("Swiched to parent window "+driver.getTitle()+" with ID "+parentwindow);
			
		}catch(Exception e) {
			log.error("Not able to switch to parent window "+e.getMessage());
		}

	}

public static boolean isTestRunnable(String testName) throws Exception
{
	String scenarioSheetName= CommonMethods.readPropertyFile("SheetName");	
	int rows =ExcelOperation.getRowCount(scenarioSheetName);
		//System.out.println("No of rows : "+rows + " and test name = "+testName);
	
		for(int rNum=1; rNum<=rows; rNum++){
			
			String testCase = ExcelOperation.getCellData(scenarioSheetName, "TC Name", rNum);
			
			if(testCase.equalsIgnoreCase(testName)){
				
				String runmode = ExcelOperation.getCellData(scenarioSheetName, "RunMode", rNum);
				
				if(runmode.equalsIgnoreCase("Yes"))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public static int getTestScenarioRowNum(String testScenario) throws Exception
	{
		
		String scenarioSheetName= CommonMethods.readPropertyFile("SheetName");
		
		int rows =ExcelOperation.getRowCount(scenarioSheetName);
		int rNum=1;
		for( ;rNum<=rows; rNum++)
		{
			String testCase = ExcelOperation.getCellData(scenarioSheetName, "TC Name", rNum);
			if(testCase.equalsIgnoreCase(testScenario))
			{	
				log.info("Row num for TestScenario = "+testScenario+ " is = "+rNum);
				return rNum;
			}
		}
		return rNum;
		
	}

	public static String readPropertyFile(String propertyName)throws UnhandledException, IOException
	{
		
		Properties prop=new Properties();
		String currentDir =System.getProperty("user.dir");
		FileInputStream fis =new FileInputStream(currentDir+"\\src\\main\\resources\\Config.properties");

		prop.load(fis);
		String propertyValue=prop.getProperty(propertyName);
		
		return propertyValue;
	}
	

}