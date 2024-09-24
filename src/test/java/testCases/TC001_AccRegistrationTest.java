package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccRegistrationTest extends BaseClass{
//Value that passed in Test case class should not be Hardcode, 
//either we should maintain it in Excel or Properties file...	
	
	@Test(groups={"sanity","master"})
	void testAccountReg() 														throws InterruptedException 
	{
		logger.info("***test case TC001_AccRegistrationTest....started***");
	try {
		HomePage hp = new HomePage(driver);
		hp.ClickAccount();
		logger.info("..click my account..");
		hp.ClickRegister();
		logger.info("..click register..");
		
		RegistrationPage rp = new RegistrationPage(driver);
		logger.info("..Filling customer details..");
		rp.Setfirstname(randomstring());//Calling another method if same Class...
		rp.Setlastname(randomstring());
		rp.Setemail(randomstring()+"@gamil.com");
		rp.Setmobile(randomNunmber());
		String password=randomPass();	
		rp.Setpassword(password);
		rp.SetcnfmPass(password);
		rp.Setprivacypolicy();
		rp.Clickcontinuebutton();
		logger.info("..Click continue button**");
	//  rp.GetconfirmationMsg();
		logger.info("..Validating message...");
		String msg=rp.GetconfirmationMsg();
	//	Assert.assertEquals(msg,true);	--Hard assertion...
	//  Assert.assertEquals(msg,"Your Account Has Been Created!");--Hard assertion
		if(msg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.debug("debug logs...");
			logger.error("error logs...");
			Assert.assertTrue(false);
		}
		//Thread.sleep(3000);
	}
		catch(Exception e)
		{
			Assert.fail();
			System.out.println(e.getMessage());;
		}
		logger.info("***test case TC001_AccRegistrationTest finished***");
	}
}
