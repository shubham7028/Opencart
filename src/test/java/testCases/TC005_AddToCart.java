package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AddToCart;
import pageObjects.Searchproduct;
import testBase.BaseClass;

public class TC005_AddToCart extends BaseClass {
	
	@Test(groups="master")
	public void AddToCart()
	{
		try {
		Searchproduct sp= new Searchproduct(driver);
		sp.SetSearchproduct("iPhone");
		sp.ClickSearchButton();
		String product=sp.IsProductExist();
		if(product.equalsIgnoreCase("iPhone"))
		{
			sp.ClickProduct();
		}	
		AddToCart ac=new AddToCart(driver);
		ac.SetQuantity("5");
		ac.ClickCartButton();
	//Shopping Cart:
		System.out.println(ac.GetCnfmMessage());
		ac.GoToShoppingCart();
		String price=ac.totalCartPrice();
		if(price.equalsIgnoreCase("$616.00")) //error IF
		{
			ac.ClickCheckout();
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
}
