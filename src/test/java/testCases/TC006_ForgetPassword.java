package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.ForgetPass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC006_ForgetPassword extends BaseClass {

	@Test(groups="master")
	public void forgetPassPage()
	{
		try {
		HomePage hp=new HomePage(driver);
		hp.ClickAccount();
		hp.ClickLogin();
		LoginPage lp=new LoginPage(driver);
		lp.ClickEmailId("pavanoltraining@gmail.com");
		lp.ClickPassId("test@12");
		lp.ClickLogin();
		Thread.sleep(4000);
		ForgetPass fp=new ForgetPass(driver);
		boolean msg=fp.ForgetMessage();
		if(msg==true)
		{
			fp.ClickForgetButton();
			Assert.assertTrue(msg);
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
	}
	
}
