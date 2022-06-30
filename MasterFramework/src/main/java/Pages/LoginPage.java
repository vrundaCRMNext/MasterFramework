package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import CommonUtility.CommonMethods;
import CommonUtility.ScreenShot;
import CommonUtility.TestListeners;



public class LoginPage extends TestListeners
{
	public static Logger log =LogManager.getLogger(LoginPage.class.getName());
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Login Page
	
	@FindBy(xpath="//input[@id='TxtName']") 		//Username fld
	private WebElement userName;
	
	@FindBy(xpath="//input[@id='TxtPassword']")   //Password field
	private WebElement password;
	
	@FindBy(xpath= "//input[@name='command']")   //Login button
	private WebElement loginBtn;
	
	@FindBy(xpath="//span[@id='TxtPassword-error']")   //Passowrd error message when blank
	private WebElement passwordErrorMsg;
	
	@FindBy(xpath="//span[@id='TxtName-error']")   //Username error message when blank
	private WebElement usernameErrorMsg;
	
	@FindBy(xpath= "//header/div[1]/div[3]/div[3]/a[1]/img[1]")   //Profile button
	private WebElement ProfileBtn;
			
			
	
	public void CRMLogin(String sheetName) throws Exception
	{
	
				extentInfo("Login for "+ sheetName +" Intiated");
				ScreenShot.takeSnapShot("LoginPage", "Pass");
				CommonMethods.input(userName,sheetName,"Username", 1);
				CommonMethods.input(password,sheetName ,"Password", 1);
				CommonMethods.Click(loginBtn);
				extentInfo("Sucessfully Login");
	}
	
	/******************LOGOUT*************************/
	
	@FindBy(xpath="//div[@data-autoid='Logout']/a")
	private WebElement logOutBtn;
	public WebElement logOutBtn()
	{
		return logOutBtn;
	}
	
	public void Logout() throws Exception
	{
			CommonMethods.highLight(ProfileBtn);
			CommonMethods.Click(ProfileBtn);
			CommonMethods.highLight(logOutBtn);
			CommonMethods.Click(logOutBtn);
			log.info("Sucessfully logout..");
		
	}
	
}
