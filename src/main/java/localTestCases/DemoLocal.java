package localTestCases;

import java.io.IOException;
import java.util.Random;

import GenericLib.*;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.*;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

public class DemoLocal extends Browser{

	AlertHandle popup = new AlertHandle();
	Logger log = Logger.getLogger("Testing Cases");

	private WebDriver driver;

	@BeforeClass
	public void setUp() throws WriteException, IOException, BiffException {
		driver=getDriver();
	}

	@BeforeMethod
	public void Url() throws IOException, BiffException, WriteException {
		driver.get("https://directqa2.dimensiondata.com/Webshop/login");
		log.info("URL entered in browser");
	}

	@Test
	public void DemoTC() throws Exception ,WriteException {
		try {
			Thread.sleep(2000);
			LoginPage.Loginfunctionality(driver);
			log.info("Login in to the webshop application");
			ShoppingCart.DeleteExistItem(driver);
			double noOfItems = HomePage.VerifyCart(driver);
			ProductSearchPage.AddToShoppingCart(driver);
			ProductSearchPage.AddToShoppingCart(driver);
			double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
			ExpectedLable("Verify cart count functionality by adding product to cart");
			if(noOfItemsafterAddtoCart>noOfItems){
				ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
				ShoppingCart.VerifyItemCount(driver);
				ShoppingCart.VerifyCartGrandTotal(driver);
				CheckOutPage.ClickonProceedtoCheckout(driver);
				ReviewOrderPage.COnfirmAndPlaceOrder(driver);
				OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
			}
			else{
				ActualLable("verification failed for cart Number functionality","Fail");
			}

		/*Double UnitPrice = HomePage.UnitPrice(driver);
		Assert.assertEquals(UnitCost, "$"+UnitPrice);*/
		}
		catch (AssertionError e){
			log.info("Exception for the product is " + e);
			String error =  "Exception " +  e.getClass().getSimpleName();
			ActualLable(error,"Fail");
		}
		catch (Exception e){
			log.info("Exception for the product is " + e);
			String error =  "Exception " +  e.getClass().getSimpleName();
			ActualLable(error,"Fail");
		}
	}

	@Test
	public void MobileAutomation() throws Exception ,WriteException{
		//driver.get("https://directqa2.dimensiondata.com/Webshop/login");

		Thread.sleep(1000);
		LoginPage.Loginfunctionality(driver);
		log.info("Login in to the webshop application");
		Thread.sleep(3000);
		HomePage.ClicoOnShopMenuMobile(driver);

		Thread.sleep(2000);
		String PartNumber =HomePage.PartNumber(driver).get(0).getText();
		log.info("Partnumber for the product is: "+ PartNumber);
		HomePage.LearnMoreButtons(driver).get(0).click();
		log.info("Clicked on Learn more button");
		String UnitCost= HomePage.UnitCost(driver).get(0).getText();
		Thread.sleep(3000);
		log.info("Unit cost of the Selected item is : "+ UnitCost);

		Double UnitPrice = HomePage.UnitPrice(driver);
		Assert.assertEquals(UnitCost, "$"+UnitPrice);


	}



}
