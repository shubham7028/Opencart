package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_VerifyLoginPage extends BaseClass {
	
	@Test(groups= {"regression","master"})
	public void verifyLoginTest()
	{
		try {
		logger.info("***Starting TC002_VerifyLoginPage***");
	//  Homepage
		HomePage hp=new HomePage(driver);
		hp.ClickAccount();
		hp.ClickLogin();
	//	LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.ClickEmailId(prop.getProperty("email"));
		lp.ClickPassId(prop.getProperty("pass"));
		lp.ClickLogin();
	//	MyAcc
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean status=myacc.isMyAccExists();
		myacc.ClickLogout();
		Assert.assertEquals(status,true,"login failed...");
	//	Assert.assertTrue(status);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***Test case finished...**");	
	}
}
