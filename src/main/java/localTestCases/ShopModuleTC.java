package localTestCases;

import GenericLib.Browser;
import GenericLib.*;
import jxl.write.Label;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductCartPage;
import pageObject.ProductSearchPage;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;


/**
 * Created by t.mirasipally on 22-Nov-16.
 */
public class ShopModuleTC extends Browser {
    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");
    static protected DataDriven excel = new DataDriven();

    private WebDriver driver;
    private Sheet sheet;
    private WritableSheet wsheet;
    /*private WritableWorkbook wbook;*/






    @BeforeClass
    public void setUp() {
        driver=getDriver();
    }

    @BeforeMethod
    public void Url() throws IOException, BiffException, WriteException {
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        //driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        log.info("URL entered in browser");
        sheet = excel.ReadSheet(sheet);
    }
    @AfterMethod
    public void EndMethod() throws IOException, BiffException, WriteException {
        excel.closedoc();

    }
    /*

    WS_TC_59:  Validate the 'Learn More' button functionality - Click on the Learn More button against a selected record

    */
    @Test
    public void WS_TC_59() throws IOException, InterruptedException, WriteException {
        try {
        DataDriven.ImplicitWait(driver);
        obje.repository(driver);
        log.info("WS_TC_59: Validate the 'Learn More' button functionality");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        log.info("Login in to the webshop application");
        String NameonSearchPage = ProductSearchPage.SelectProductOnSearchResultPage(driver);
        ExpectedLable("Expected Assert Name "+NameonSearchPage);
        Thread.sleep(1000);
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
            String error =  "Exception " +  e;
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
