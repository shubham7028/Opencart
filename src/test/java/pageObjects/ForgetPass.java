package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPass  extends Basepage{

	public ForgetPass(WebDriver d)
	{
		super(d);
	}
	
	@FindBy(xpath="//*[text()='Warning: No match for E-Mail Address and/or Password.']")
	WebElement forgetMsg;
	@FindBy(xpath="//a[text()='Forgotten Password']")
	WebElement forgetPassword;

	public boolean ForgetMessage()
	{
		try
		{
			return(forgetMsg.isDisplayed());
		}
		catch(Exception e)
		{
			//return (e.getMessage());	//Method is boolean...
			return false;
		}
	}
	public void ClickForgetButton()
	{
		forgetPassword.click();
	}
}

