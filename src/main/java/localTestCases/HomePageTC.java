package localTestCases;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import jxl.Sheet;
import jxl.write.WritableSheet;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import GenericLib.*;
import pageObject.*;
import org.apache.log4j.Logger;


/**
 * Created by t.mirasipally on 10/27/2016.
 */
public class HomePageTC extends Browser{
    Browser brow = new Browser();
    DataDriven excel = new DataDriven();
    AlertHandle popup = new AlertHandle();
    ObjectRepository ob = new ObjectRepository();
    ScreenShots sc = new ScreenShots();
    WebElement element;
    Sheet sheet;
    WritableSheet wsheet;
    Logger log = Logger.getLogger("Testing Cases");

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver=getDriver();
    }


    @BeforeMethod
    public void Url(){
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        log.info("URL entered in browser");
    }

    /*@Parameters("browser")
    @BeforeTest
    public WebDriver start(String browser) throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException {
        *//*if (browser.equalsIgnoreCase(browser)) {
            driver = brow.selectbrowser(browser);
        }
        *//**//*sheet = excel.ReadSheet(sheet);
        wsheet = excel.writeSheet(wsheet, "test", "TestCase1");*//*
        ob.repository(driver);
        PropertyConfigurator.configure(ob.obj.getProperty("log4j"));
        driver.get(ob.obj.getProperty("url"));
        return driver;
    }*/



    @Test
    public void TC_Home_01() throws Exception {
        popup.implicitlyWait(driver);
        sc.screenshots(driver ,"HomePage","TC_Home_01");
        log.info("URL entered and page is loaded");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        sc.screenshots(driver ,"HomePage","TC_Home_01");
        Thread.sleep(2000);
        //To verify Shoping cart link
        HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
        sc.screenshots(driver ,"HomePage","TC_Home_01");
        log.info("Shopping Cart link Asert is verified");

        //To verify Home link
        HomePage.AsertVerifyForHomeLinkHomePage(driver);
        log.info("Home link on home page Asert is verified");
        sc.screenshots(driver ,"HomePage","TC_Home_01");


    }

    /*

    WS_TC_30: AssertVerifyForDefaultAddress that Shop menu button is displaying or not and options under Shop menu are available or not

    */

    @Test
    public void WS_TC_30() throws InterruptedException, IOException {
        log.info("WS_TC_30: AssertVerifyForDefaultAddress that Shop button is displaying or not and options under Shop Menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        log.info("Login in to the webshop application");
        HomePage.AssertVerifyForHomePage(driver);
        HomePage.VerifyDropDownListUnderShopMenu(driver);
        log.info("WS_TC_30: Verified");
    }

    /*

    WS_TC_31: AssertVerifyForDefaultAddress Each menu under Shop Menu


    */

    @Test
    public void WS_TC_31() throws InterruptedException, IOException {

        log.info("WS_TC_30: AssertVerifyForDefaultAddress that Shop button is displaying or not and options under Shop Menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        log.info("Login in to the webshop application");
        HomePage.AssertVerifyForHomePage(driver);
        HomePage.AsertVerificationForCategoryUnderShopMenu(driver);


    }

     /*

    WS_TC_32: AssertVerifyForDefaultAddress that My menu button is displaying or not and options under menu are available or not

    */

    @Test
    public void WS_TC_32() throws InterruptedException, IOException {
        log.info("WS_TC_32: AssertVerifyForDefaultAddress that My menu button is displaying or not and options under menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(4000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        HomePage.AssertVerifyForHomePage(driver);
        log.info("Login in to the webshop application");
        HomePage.ListOfOptionsMyAccountMenu(driver);

        log.info("WS_TC_32: Verified");
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
    f) Search by Category
    */

    @Test
    public void WS_TC_57() throws IOException, InterruptedException {
        ob.repository(driver);
        log.info("WS_TC_43: Verify the GUI of Request new address page");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);


    }

}
