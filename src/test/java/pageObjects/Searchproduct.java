package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Searchproduct  extends Basepage{

	public Searchproduct(WebDriver d) 
	{
		super(d);
	}
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement EnterProduct;
	@FindBy(xpath="//button[@type='button' and @class='btn btn-default btn-lg']")
	WebElement ClickSearch;
	@FindBy(xpath="//div[@class='caption']//a")
	WebElement productText;
	//iPhone
	@FindBy(xpath="//div[@class='image']//img")
	WebElement Selectproduct;
	
	public void SetSearchproduct(String search)
	{
		EnterProduct.sendKeys(search);
	}
	public void ClickSearchButton()
	{
		ClickSearch.click();
	}
	public String IsProductExist()
	{
		return(productText.getText());
	}
	public void ClickProduct()
	{
		Selectproduct.click();
	}
}
