package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Basepage {
	public HomePage(WebDriver d) 
	{
		super(d);
	}

	@FindBy(xpath = "//ul[@class='list-inline']//li[@class='dropdown']")
	WebElement account;
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//li[1]")
	WebElement Register;
	@FindBy(xpath="//a[text()='Login']")
	WebElement login;

	public void ClickAccount() {
		account.click();
	}
	public void ClickRegister() {
		Register.click();
	}
	public void ClickLogin()
	{
		login.click();
	}
}
