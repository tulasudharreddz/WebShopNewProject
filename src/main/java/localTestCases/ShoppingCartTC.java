package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductSearchPage;
import pageObject.ShoppingCart;

import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.ReportResult;

/**
 * Created by t.mirasipally on 02-Jan-17.
 */
public class ShoppingCartTC extends Browser {
    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver = getDriver();
    }


    @Test
    public void WS_TC_98() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(98);
            LoginPage.Loginfunctionality(driver);
            HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
            ShoppingCart.VerifyShoppingCartPageAsserts(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_102() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(102);
            LoginPage.Loginfunctionality(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            if (noOfItemsafterAddtoCart == 0) {
                ProductSearchPage.AddToShoppingCart(driver);
            }
            HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
            ShoppingCart.AssertVerifyForItemDetails(driver);
            ShoppingCart.VerifyEditQuantityfunctionality(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_103() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(103);
            LoginPage.Loginfunctionality(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            if (noOfItemsafterAddtoCart==0) {
                ProductSearchPage.AddToShoppingCart(driver);
                ProductSearchPage.AddToShoppingCart(driver);
            }
            if (noOfItemsafterAddtoCart==1) {
                ProductSearchPage.AddToShoppingCart(driver);
            }
            HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
            ShoppingCart.VerifyDisplayOfLineItem(driver);
            ShoppingCart.VerifyDeleteExistItem(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    @Test
    public void WS_TC_105() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(105);
            LoginPage.Loginfunctionality(driver);
            double noOfItems = ShoppingCart.DeleteExistItem(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart count functionality by adding product to cart");
            if(noOfItemsafterAddtoCart>noOfItems){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyForCartSummeryPannelOpenedOrNot(driver);
                ShoppingCart.ContentVerifyForCartSummery(driver);
            }
            else{
                ActualLable("verification failed for cart Number functionality","Fail");
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_106() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(106);
            LoginPage.Loginfunctionality(driver);
            double noOfItems = ShoppingCart.DeleteExistItem(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart count functionality by adding product to cart");
            if(noOfItemsafterAddtoCart>noOfItems){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyItemCount(driver);
                ShoppingCart.VerifyCartGrandTotal(driver);
            }
            else{
                ActualLable("verification failed for cart Number functionality","Fail");
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_108() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(108);
            LoginPage.Loginfunctionality(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart is having Products or not");
            if(noOfItemsafterAddtoCart>0){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyLogisticCharge(driver);
            }
            else{
                ActualLable("Products are not available in Shopping cart, now add products to cart","Pass");
                ProductSearchPage.AddToShoppingCart(driver);
                ShoppingCart.VerifyLogisticCharge(driver);
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_109() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(109);
            LoginPage.Loginfunctionality(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart is having Products or not");
            if(noOfItemsafterAddtoCart>0){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifySalesTax(driver);
            }
            else{
                ActualLable("Products are not available in Shopping cart, now add products to cart","Pass");
                ProductSearchPage.AddToShoppingCart(driver);
                ShoppingCart.VerifySalesTax(driver);
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_112() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(112);
            LoginPage.Loginfunctionality(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart is having Products or not");
            if(noOfItemsafterAddtoCart>0){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyContinueShoppingButtonFunctionality(driver);
            }
            else{
                ActualLable("Products are not available in Shopping cart, now add products to cart","Pass");
                ProductSearchPage.AddToShoppingCart(driver);
                ShoppingCart.VerifyContinueShoppingButtonFunctionality(driver);
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_113() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(113);
            LoginPage.Loginfunctionality(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart is having Products or not");
            if(noOfItemsafterAddtoCart>0){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyCheckOutButtonFunctionality(driver);
            }
            else{
                ActualLable("Products are not available in Shopping cart, now add products to cart","Pass");
                ProductSearchPage.AddToShoppingCart(driver);
                ShoppingCart.VerifyCheckOutButtonFunctionality(driver);
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
}
