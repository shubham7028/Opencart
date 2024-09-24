package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AddToCart;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.RegistrationPage;
import pageObjects.Searchproduct;
import testBase.BaseClass;

public class TC007_EndToEndTesting extends BaseClass {
	
	@Test(groups="master")
	public void EndToEndTesting() throws InterruptedException
	{
//Registration Page:-
		
	System.out.println("Registration process started");		
		HomePage hp=new HomePage(driver);
		hp.ClickAccount();
		hp.ClickRegister();
		RegistrationPage rp=new RegistrationPage(driver);
		rp.Setfirstname(randomstring());//Calling another method if same Class...
		rp.Setlastname(randomstring());
		rp.Setemail(randomstring()+"@gamil.com");
		rp.Setmobile(randomNunmber());
		String password=randomPass();	
		rp.Setpassword(password);
		rp.SetcnfmPass(password);
		rp.Setprivacypolicy();
		Thread.sleep(2000);
		rp.Clickcontinuebutton();
		String msg=rp.GetconfirmationMsg();
		Assert.assertEquals(msg,"Your Account Has Been Created!");
		rp.out();
		
		MyAccountPage mp=new MyAccountPage(driver);
		Thread.sleep(2000);
		mp.ClickLogout();
		Thread.sleep(5000);
	System.out.println("Registration process completed");
	
//Login page:-
	
	System.out.println("Login process started");
		hp.ClickAccount();
		hp.ClickLogin();
		Thread.sleep(2000);
		LoginPage lp=new LoginPage(driver);
		lp.ClickEmailId(prop.getProperty("email"));
		lp.ClickPassId(prop.getProperty("pass"));
		Thread.sleep(2000);
		lp.ClickLogin();
		Thread.sleep(3000);
	System.out.println("Login process Completed");
		
//Verify Login page:-
	System.out.println("Verify Loging...");
		MyAccountPage myacc=new MyAccountPage(driver);
		//myacc.ClickContinue();
		Thread.sleep(2000);
		boolean status=myacc.isMyAccExists();
		Assert.assertEquals(status,true,"login failed...");
		myacc.ClickLogout();
		Thread.sleep(4000);
	System.out.println("Verification Completed");

//Search, Add Product & Checkout:-
	System.out.println("AddtoCart Process started...");	
	
		Searchproduct sp=new Searchproduct(driver);
		sp.SetSearchproduct("iPhone");
		sp.ClickSearchButton();
		Thread.sleep(2000);
		String msgg=sp.IsProductExist();
		if(msgg.equalsIgnoreCase("iPhone"))
		{
			sp.ClickProduct();
		}
		Thread.sleep(3000);
	//Shopping Cart:
		AddToCart ac=new AddToCart(driver);
		ac.SetQuantity("5");
		ac.ClickCartButton();
		Thread.sleep(2000);
		System.out.println("Added iphone:"+ac.GetCnfmMessage());
		Thread.sleep(3000);
		ac.GoToShoppingCart(); //get text
		Thread.sleep(3000);
		if(ac.totalCartPrice().equalsIgnoreCase("$616.00"))
		{
			ac.ClickCheckout();
		}
		Thread.sleep(3000);
	System.out.println("AddtoCart Process Completed");	
	System.out.println(" TC007_EndToEndTestingCompleted");
	}
	
}
