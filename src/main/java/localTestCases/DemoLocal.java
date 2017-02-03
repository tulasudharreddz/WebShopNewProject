package localTestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GenericLib.*;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.*;

import static GenericLib.DataDriven.*;

public class DemoLocal extends Browser{

	AlertHandle popup = new AlertHandle();
	Logger log = Logger.getLogger("Testing Cases");

	private WebDriver driver;

	@BeforeClass
	public void setUp() throws WriteException, IOException, BiffException {
		driver=getDriver();
	}
	public static ArrayList<ArrayList> ProductDetailsArrayList;
	@Test
	public void Demo_TC_01() throws IOException, WriteException {
		try {
			DataDriven.ReportStartup(136);
			Thread.sleep(2000);
			LoginPage.Loginfunctionality(driver);
			log.info("Login in to the webshop application");
			double noOfItems = ShoppingCart.DeleteExistItem(driver);
			StepLable("Add product to Shopping Cart from Product search page");
			ArrayList<String> AssertNameFromPSearchPage =ProductSearchPage.SelectProductFromSearchResultPage(driver);
			ArrayList<String> AssertNameFromPCartPage = ProductCartPage.SelectProductFromPCartPage(driver);
			ArrayList<String> AssertNameFromFavoritesPage= FavoriesPage.SelectProductFromFavoritesPage(driver);
			ProductDetailsArrayList= new ArrayList<ArrayList>();
			ProductDetailsArrayList.add(AssertNameFromPSearchPage);
			ProductDetailsArrayList.add(AssertNameFromPCartPage);
			ProductDetailsArrayList.add(AssertNameFromFavoritesPage);
			double noOfItemsAfterAddToCart = HomePage.VerifyCart(driver);
			ExpectedLable("Verify cart count functionality by adding product to cart");
			if(noOfItemsAfterAddToCart>noOfItems){
				ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
				ShoppingCart.VerifyItemCount(driver);
				StepLable("Verify Product Details on Shopping cart with Details on Product Cart page details");
				boolean ResultStatus = ShoppingCart.VerifyProductDetailsOnShoppingCart(driver);
				String ReferenceNumber = ReviewOrderPage.ConfirmAndPlaceOrderDemo(driver);
				OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
				boolean Stuas =EmailVerificationDetails.VerifyOrderEmailInOutLook(driver);
				if(Stuas==true) {
					EmailVerificationDetails.VerifyReferenceLink(driver, ReferenceNumber);
					OrdersPage.VerifyRequestReturnPage(driver);
					OrdersPage.VerifySubmitButtonFunctionalityinRequestReturnPage(driver);
					EmailVerificationDetails.VerifyRequestReturnEmailInOutLook(driver);
				}
			}else{ActualLable("verification failed for cart Number functionality","Fail");}
		}
		catch (AssertionError e){ String error ="Exception : " +  e.getClass().getSimpleName();	ActualLable(error,"Fail");}
		catch (Exception e){ String error ="Exception : " +  e.getClass().getSimpleName();ActualLable(error,"Fail"); }
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
