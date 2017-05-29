package localTestCases;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GenericLib.*;
import TestRail_Inte.APIException;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObject.*;

import static GenericLib.DataDriven.*;

public class DemoLocal extends Browser{

	Logger log = Logger.getLogger("Testing Demo");
	private WebDriver driver;

	@BeforeClass
	public void setUp() throws WriteException, IOException, BiffException {driver=getDriver();}

	public static ArrayList<ArrayList> ProductDetailsArrayList;
	@Test
	public void Demo_TC_01() throws IOException, WriteException, InterruptedException, AWTException, APIException, BiffException {
		//try {
			DataDriven.ReportStartup(136);
			Thread.sleep(2000);
			LoginPage.Loginfunctionality(driver);
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
					EmailVerificationDetails.VerifyRequestReturnEmailInOutLookDemo(driver);
				}
			}else{ActualLable("verification failed for cart Number functionality","Fail");}
		/*}
		catch (AssertionError e){ String error ="Exception : " +  e.getClass().getSimpleName();	ActualLable(error,"Fail");}
		catch (Exception e){ String error ="Exception : " +  e.getClass().getSimpleName();ActualLable(error,"Fail"); }*/
	}
	@Test
	public void MobileAutomation() throws IOException, WriteException, InterruptedException, AWTException {
		try {
			DataDriven.ReportStartup(136);
			Thread.sleep(2000);
			LoginPage.Loginfunctionality(driver);
			UITesting.HomePageUIVerification(driver);
			/*String path ="D:\\Projects_Idea\\WebShopNewProject\\ResultReports\\21-04-2017\\WS_TC_136-05-39-screen-7.jpeg";
			JiraAccess.JiraFunctionality(driver,path);*/
			/*LoginPage.Loginfunctionality(driver);*/

		}
		catch (AssertionError e){ String error ="Exception : " +  e.getClass().getSimpleName();	ActualLable(error,"Fail");}
		catch (Exception e){ String error ="Exception : " +  e.getClass().getSimpleName();ActualLable(error,"Fail"); }

	}




}
