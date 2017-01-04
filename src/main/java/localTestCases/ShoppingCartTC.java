package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
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
        driver=getDriver();
    }

    @BeforeMethod
    public void Url() throws IOException, BiffException, WriteException {
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        log.info("URL entered in browser");
    }


    /*
   WS_TC_61:  Validate the display of 'Inventory Color' against the products in search results page
   a. Vendor Inventory level > 10 units
   b. Vendor Inventory level <= 10 units
   c. Vendor Inventory level = 0 units
   */
    @Test
    public void WS_TC_61() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(61);
            LoginPage.Loginfunctionality(driver);
            log.info("Login in to the webshop application");
            double InitialnoOfProducts = HomePage.VerifyCart(driver);
            double noOfItemsafterAddedNotAv =ProductSearchPage.AddNotAvailableProductToCart(driver);
            ExpectedLable("Verify cart count functionality by adding Not Available product to cart");
            if(noOfItemsafterAddedNotAv-InitialnoOfProducts==1){
                ActualLable("successfully verified cart Count functionality for Not Available product and items in cart is increased by '1'","Pass");
            }else{
                ActualLable("Failed to Verify cart Count functionality for Not Available product","Fail");
            }
            double noOfItemsafterAddedAvailableProduct =ProductSearchPage.AddAvailableProductToCart(driver);
            ExpectedLable("Verify cart count functionality by adding Available product to cart");
            if(noOfItemsafterAddedAvailableProduct-noOfItemsafterAddedNotAv==1){
                ActualLable("successfully verified cart Count functionality for Available product and items in cart is increased by '1'","Pass");
            }else{
                ActualLable("Failed to Verify cart Count functionality for Available product","Fail");
            }
            double noOfItemsafterAddedLimitedProduct =ProductSearchPage.AddLimitedProductToCart(driver);
            ExpectedLable("Verify cart count functionality by adding Limited Available product to cart");
            if(noOfItemsafterAddedLimitedProduct-noOfItemsafterAddedAvailableProduct==1){
                ActualLable("successfully verified cart Count functionality for Limited Available product and items in cart is increased by '1'","Pass");
            }else{
                ActualLable("Failed to Verify cart Count functionality for Limited Available product","Fail");
            }
            ReportResult("Pass");
        }
        catch (AssertionError e){
            log.info("Exception for the product is " + e);
            String error =  "Exception " +  e.getClass().getSimpleName();
            //ScreenShots.screenshots(driver);
            ActualLable(error,"Fail");
            ReportResult("Fail");
        }
        catch (Exception e){
            log.info("Exception for the product is " + e);
            String error =  "Exception " +  e.getClass().getSimpleName();
            //ScreenShots.screenshots(driver);
            ActualLable(error,"Fail");
            ReportResult("Fail");
        }
    }
}
