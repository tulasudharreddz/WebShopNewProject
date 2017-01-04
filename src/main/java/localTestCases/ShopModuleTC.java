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
    /*private WritableWorkbook wbook;*/


    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
        //sheet = excel.ReadSheet(sheet);
    }

    @BeforeMethod
    public void Url() throws IOException, BiffException, WriteException {
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");

        log.info("URL entered in browser");

    }

    /*1. Verify the content in search results page
      2. Validate the Pagination Functionality in search results page
        a. Next Page -Click on Next Icon
        b. Previous Page -Click on PreviousICON
        c. Selected Page Navigation - Click on a specific page number
        d. No of Search Results Per Page - Select a value from Display dropdown*/
    @Test
    public void WS_TC_58() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(58);
            log.info("WS_TC_58: 1. Verify the content in search results page, 2. Validate the Pagination Functionality in search results page");
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            log.info("Login in to the webshop application");
            HomePage.ClickonShopmenuonHomePage(driver);
            log.info("Cliked on Shop menu");
            HomePage.ClickonCategoryinShopmenu(driver);
            log.info("Clicked on sub menu");
            ProductSearchPage.ContentinSearchReasult(driver);
            log.info("Content verified on search result page");
            ProductSearchPage.PaginationFunctionality(driver);
            log.info("pagination verified on search result page");
            ProductSearchPage.NoOfReultsChangeFunctionality(driver);
            StepLable("WS_TC_58: Successfully verified 1. content in search results page, 2. Pagination Functionality in search results page");

        }catch (Exception e){
            log.info("Exception for the product is " + e);
            String error =  "Exception " +  e.getClass().getSimpleName();;
            ActualLable(error,"Fail");

        }
    }
    /*
    WS_TC_59:  Validate the 'Learn More' button functionality - Click on the Learn More button against a selected record
    */
    @Test
    public void WS_TC_59() throws IOException, InterruptedException, WriteException {
        try {

            DataDriven.ReportStartup(59);
            obje.repository(driver);
            log.info("WS_TC_59: Validate the 'Learn More' button functionality");
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            log.info("Login in to the webshop application");
            String NameonSearchPage = ProductSearchPage.SelectProductOnSearchResultPage(driver);
            ExpectedLable("Expected Assert Name: "+NameonSearchPage);
            String NameonProductCartPage= ProductCartPage.AssertVerifyForProduct(driver);
            ActualLable("Actual assert name: "+ NameonProductCartPage,"Pass");
            ExpectedLable("Verify Assert for Learn more");
            Assert.assertEquals(NameonSearchPage, NameonProductCartPage);
            ActualLable("Successfully verified for Learn more button functionality ","Pass");
            log.info("Assert Verified for the selected Product ");

            StepLable("WS_TC_59: Learn More button functionality is verified");
        }
        catch (Exception e){
            log.info("Exception for the product is " + e);
            String error =  "Exception " +  e.getClass().getSimpleName();;
            ActualLable(error,"Fail");
        }
    }
    /*
    WS_TC_60:  Validate the display of 'Inventory Color' against the products in search results page
    a. Vendor Inventory level > 10 units
    b. Vendor Inventory level <= 10 units
    c. Vendor Inventory level = 0 units
    */
    @Test
    public void WS_TC_60() throws IOException, WriteException, InterruptedException {

        try {
            obje.repository(driver);
            DataDriven.ReportStartup(60);
            log.info("WS_TC_60: Validate the display of 'Inventory Color' against the products in search results page");
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            log.info("Login in to the webshop application");
            ProductSearchPage.MovingToCategory(driver);
            Thread.sleep(1000);
            ProductSearchPage.StatusVerifyForProducts(driver);
            StepLable("WS_TC_60: Successfully Validated the display of 'Inventory Color' against the products in search results page");
        }
        catch (Exception e){
            log.info("Exception for the product is " + e);
        }
    }



}
