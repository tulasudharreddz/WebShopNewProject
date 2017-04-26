package localTestCases;

import GenericLib.Browser;
import GenericLib.*;
import jxl.write.Label;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObject.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static GenericLib.DataDriven.*;


/**
 * Created by t.mirasipally on 22-Nov-16.
 */
public class ShopModuleTC extends Browser {
    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");
    static protected DataDriven excel = new DataDriven();

    protected WebDriver driver;
    private Sheet sheet;
    private WritableSheet wsheet;


    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
    }

    /*
    WS_TC_57: Verify the display of search results page

    1. Click on category menu and Enter search criteria
    2. Go to the Search bar in Home page and enter search criteria
    a) Search by MFR Part#
    b) Search By Product Name
    c) Wild Card/ Like Search
    d) Search by Manfacturer
    e) Search by Family
    f) Search by Category */

    @Test
    public void WS_TC_57() throws IOException, InterruptedException, WriteException, AWTException {
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(57);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            log.info("Cliked on Shop menu");
            HomePage.MovingToCategory(driver);
            ProductSearchPage.SearchingFunctionality(driver);
            
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }

    }

    /*1. Verify the content in search results page
      2. Validate the Pagination Functionality in search results page
        a. Next Page -Click on Next Icon
        b. Previous Page -Click on PreviousICON
        c. Selected Page Navigation - Click on a specific page number
        d. No of Search Results Per Page - Select a value from Display dropdown*/
    @Test
    public void WS_TC_58() throws IOException, InterruptedException, WriteException, AWTException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(58);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonShopmenuonHomePage(driver);
            HomePage.ClickonCategoryinShopmenu(driver);
            ProductSearchPage.ContentinSearchReasult(driver);
            ProductSearchPage.PaginationFunctionality(driver);
            ProductSearchPage.NoOfReultsChangeFunctionality(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    /*
    WS_TC_59:  Validate the 'Learn More' button functionality - Click on the Learn More button against a selected record
    */
    @Test
    public void WS_TC_59() throws IOException, InterruptedException, WriteException, AWTException {
        try {
            DataDriven.ReportStartup(59);
            obje.repository(driver);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            String NameonSearchPage = ProductSearchPage.SelectProductOnSearchResultPage(driver);
            ExpectedLable("Expected Assert Name: "+NameonSearchPage);
            String NameonProductCartPage= ProductCartPage.AssertVerifyForProduct(driver);
            ActualLable("Actual assert name: "+ NameonProductCartPage,"Pass");
            ExpectedLable("Verify Assert for Learn more");
            Assert.assertEquals(NameonSearchPage, NameonProductCartPage);
            ActualLable("Successfully verified for Learn more button functionality ","Pass");
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    /*
    WS_TC_60:  Validate the display of 'Inventory Color' against the products in search results page
    a. Vendor Inventory level > 10 units
    b. Vendor Inventory level <= 10 units
    c. Vendor Inventory level = 0 units
    */
    @Test
    public void WS_TC_60() throws IOException, WriteException, InterruptedException, AWTException {

        try {
            obje.repository(driver);
            DataDriven.ReportStartup(60);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            //HomePage.MovingToCategory(driver);
            Thread.sleep(1000);
            ProductSearchPage.StatusVerifyForProducts(driver);
            
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    @Test
    public void WS_TC_61() throws IOException, WriteException, InterruptedException, AWTException {
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

}
