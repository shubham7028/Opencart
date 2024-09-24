package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToCart extends Basepage{
	public AddToCart(WebDriver f)
	{
		super(f);
	}

	@FindBy(xpath="//input[@id='input-quantity']")
	WebElement SelectQuantity;
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement ButtonCart;
	@FindBy(xpath="//div[text()='Success: You have added ']")
	WebElement Confirmmsg;
	@FindBy(xpath="//a[text()='shopping cart']")
	WebElement GotoCart;
	@FindBy(xpath="((//table[@class='table table-bordered'])[3]//td)[8]")
	WebElement TotalPrice;
	@FindBy(xpath="//div[@class='buttons clearfix']//a[text()='Checkout']")
	WebElement checkout;

	public void SetQuantity(String quantity)
	{	
		SelectQuantity.clear();
		SelectQuantity.sendKeys(quantity);
	}
	public void ClickCartButton()
	{
		ButtonCart.click();
	}
	public String GetCnfmMessage()
	{
		return(Confirmmsg.getText());
	}
	public void GoToShoppingCart()
	{
		GotoCart.click();
	}
	public String totalCartPrice()
	{
		return(TotalPrice.getText());
	}
	public void ClickCheckout()
	{
		checkout.click();
	}
}
