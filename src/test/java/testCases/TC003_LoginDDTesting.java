package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviderPage;

public class TC003_LoginDDTesting extends BaseClass {
	
	@Test(dataProvider="dp",dataProviderClass=DataProviderPage.class)//getting DataProvider from diff package...
	public void verifyLoginPage(String email,String pass,String expected)
	{
		try {
			logger.info("***Starting TC003_LoginDDTesting***");
		// Homepage:
			HomePage hp=new HomePage(driver);
			hp.ClickAccount();
			hp.ClickLogin();
		//LoginPage:
			LoginPage lp=new LoginPage(driver);
			lp.ClickEmailId(email);
			lp.ClickPassId(pass);
			lp.ClickLogin();
		//MyAcc:
			MyAccountPage myacc=new MyAccountPage(driver);
			boolean displayStatus=myacc.isMyAccExists();
			
			if(expected.equalsIgnoreCase("valid")) 	//data valid
			{	
				if(displayStatus==true)				//login success
				{
					myacc.ClickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);	//login failed
				}
			}
			
			if(expected.equalsIgnoreCase("invalid"))	//data Invalid
			{
				if(displayStatus==true)
				{
					myacc.ClickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
			catch(Exception e)
			{
				Assert.fail();
			}
			logger.info("***TC Finished***...");
	}
}
