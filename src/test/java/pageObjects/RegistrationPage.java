package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends Basepage {
	public RegistrationPage(WebDriver dr)
	{
		super(dr);
	}
//Locators:
	@FindBy(xpath = "//input[@name='firstname']")
	WebElement firstname;
	@FindBy(xpath = "//input[@name='lastname']")
	WebElement lastname;
	@FindBy(xpath = "//input[@name='email']")
	WebElement email;
	@FindBy(xpath = "//input[@name='telephone']")
	WebElement mobile;
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	@FindBy(xpath = "//input[@name='confirm']")
	WebElement cnfmPass;
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement privacypolicy;

	@FindBy(xpath = "//input[@type='submit' and @value='Continue']")
	WebElement continuebutton;
	@FindBy(xpath="//a[text()='Continue']")
	WebElement OutContinue;

	@FindBy(xpath = "//div[@class='col-sm-9']//h1")
	// @FindBy(xpath="//div[@id='content']//h1[text()='Your Account Has Been
	// Created!']")
	WebElement confirmationMsg;
	

//Action
	public void Setfirstname(String firstn) {
		firstname.sendKeys(firstn);
	}

	public void Setlastname(String lastn) {
		lastname.sendKeys(lastn);
	}

	public void Setemail(String Email) {
		email.sendKeys(Email);
	}

	public void Setmobile(String number) {
		mobile.sendKeys(number);
	}

	public void Setpassword(String passw) {
		password.sendKeys(passw);
	}

	public void SetcnfmPass(String cnfm) {
		cnfmPass.sendKeys(cnfm);
	}

	public void Setprivacypolicy() {
		privacypolicy.click();
	}

	public void Clickcontinuebutton() {
		continuebutton.click();
		/*
		 * confirmationMsg.submit(); 
		 * Actions act=new Actions(driver);
		 * act.moveToElement(continuebutton).click().perform();
		 * 
		 * JavascriptExecutor js=(JavascriptExecutor)driver;
		 * js.executeScript("arguments[0].click()", continuebutton);
		 */
	}
	public String GetconfirmationMsg() {
		try 
			{
				return (confirmationMsg.getText());
			}
		catch (Exception e) 
		{
			return (e.getMessage());
		}
	}
	public void out()
	{
		OutContinue.click();
	}
}
