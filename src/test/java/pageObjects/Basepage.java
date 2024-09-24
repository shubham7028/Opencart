package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Basepage {
	public WebDriver driver;
	public Basepage(WebDriver d)
	{
		this.driver = d;
		PageFactory.initElements(driver, this);
	}
}
