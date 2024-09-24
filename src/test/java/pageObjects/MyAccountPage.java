package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Basepage {
	public MyAccountPage(WebDriver dg)
	{
		super(dg);
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement MyAcc;
	@FindBy(xpath="//a[@class='list-group-item' and text()='Logout']")
	WebElement logout;
	@FindBy(xpath="//div[@class='pull-right']//a[text()='Continue']")
	WebElement continueClick;
	
	public boolean isMyAccExists()	//boolean method...
	{	
		try 
		{
		return (MyAcc.isDisplayed());
		}
		catch(Exception e)
		{
		return false;
	  //return e.getMessage(); not string Method
		}
	}
	public void ClickLogout()
	{
		logout.click();
		continueClick.click();
	}
	public void ClickContinue()
	{
		continueClick.click();
	}

}
