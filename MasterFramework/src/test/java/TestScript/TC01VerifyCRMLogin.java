package TestScript;

import org.testng.SkipException;
import org.testng.annotations.Test;

import CommonUtility.CommonMethods;
import CommonUtility.SetUp;
import Pages.LoginPage;


public class TC01VerifyCRMLogin extends SetUp
{
	public LoginPage login;
	@Test
	public void VerifyCRMLogin() throws Exception
	{
	
	//sheetName from Excel to pass the testdata
	String sheetName = "VerifyCRMLogin";
	  
	 //To check  testcase runmode from excel (Yes/No)
	  if (!(CommonMethods.isTestRunnable("VerifyCRMLogin"))) {

			throw new SkipException(
					"Skipping the test VerifyCRMLogin as the Run mode is NO");
		}
	   
	   		//setUpTest to launch browser
				setUpTest(sheetName);
	   		//login to CRM
				login = new LoginPage(driver);
				login.CRMLogin(sheetName);
				
				login.Logout();
	  
	  
	}
}
