package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Basepage {
	public LoginPage(WebDriver d)
	{
		super(d);
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailid;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;
	@FindBy(xpath="//input[@value='Login']")
	WebElement login;

	public void ClickEmailId(String email) {
		emailid.sendKeys(email);
	}
	public void ClickPassId(String pass) {
		password.sendKeys(pass);
	}
	public void ClickLogin()
	{
		login.click();
	}
}
