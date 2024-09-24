package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Searchproduct;
import testBase.BaseClass;

public class TC004_SearchProduct extends BaseClass {
	
	@Test(groups="master")
	public void SearchingProduct()
	{
		try {
		Searchproduct sp= new Searchproduct(driver);
		sp.SetSearchproduct("iPhone");
		sp.ClickSearchButton();
		String product=sp.IsProductExist();
		if(product.equalsIgnoreCase("iPhone"))
		{
			sp.ClickProduct();
			Assert.assertTrue(true);
		}	
		}catch(Exception e)
		{
			Assert.fail();
		}
	}
}
