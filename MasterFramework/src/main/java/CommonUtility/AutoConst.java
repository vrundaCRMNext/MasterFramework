package CommonUtility;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.support.ui.WebDriverWait;

//AutoConst interface include all constant variables
public interface AutoConst 
{
	String ExcelPATH = System.getProperty("user.dir")+"\\TestData\\AutomationTestData.xlsx";
	String SheetName = "TestScenario";	
	String folderDate = new SimpleDateFormat("dd-MM-yyyy HH").format(new Date());
}
