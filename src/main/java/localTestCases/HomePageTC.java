package localTestCases;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import jxl.Sheet;
import jxl.write.WritableSheet;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import GenericLib.*;
import pageObject.*;
import org.apache.log4j.Logger;


/**
 * Created by t.mirasipally on 10/27/2016.
 */
public class HomePageTC {
    public static WebDriver driver;
    Browser brow = new Browser();
    DataDriven excel = new DataDriven();
    AlertHandle popup = new AlertHandle();
    ObjectRepository ob = new ObjectRepository();
    ScreenShots sc = new ScreenShots();
    WebElement element;
    Sheet sheet;
    WritableSheet wsheet;
    Logger log = Logger.getLogger("Test Cases");

    @Parameters("browser")
    @BeforeTest
    public WebDriver start(String browser) throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException {
        if (browser.equalsIgnoreCase(browser)) {
            driver = brow.selectbrowser(browser);
        }
        /*sheet = excel.ReadSheet(sheet);
        wsheet = excel.writeSheet(wsheet, "test", "TestCase1");*/
        ob.repository(driver);
        PropertyConfigurator.configure(ob.obj.getProperty("log4j"));
        driver.get(ob.obj.getProperty("url"));
        return driver;
    }



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

    WS_TC_30: Verify that Shop menu button is displaying or not and options under Shop menu are available or not

    */

    @Test
    public void WS_TC_30() throws InterruptedException, IOException {
        log.info("WS_TC_30: Verify that Shop button is displaying or not and options under Shop Menu are available or not");
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

    WS_TC_31: Verify Each menu under Shop Menu


    */

    @Test
    public void WS_TC_31() throws InterruptedException, IOException {

        log.info("WS_TC_30: Verify that Shop button is displaying or not and options under Shop Menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        log.info("Login in to the webshop application");
        HomePage.AssertVerifyForHomePage(driver);
        HomePage.AsertVerificationForCategoryUnderShopMenu(driver);


    }

     /*

    WS_TC_32: Verify that My menu button is displaying or not and options under menu are available or not

    */

    @Test
    public void WS_TC_32() throws InterruptedException, IOException {
        log.info("WS_TC_32: Verify that My menu button is displaying or not and options under menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(4000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        HomePage.AssertVerifyForHomePage(driver);
        log.info("Login in to the webshop application");
        HomePage.ListOfOptionsMyAccountMenu(driver);

        log.info("WS_TC_32: Verified");
    }

    /*WS_TC_34: Verify the GUI of  'User Profile' page
    a) Verify the title
    b) Verify the display of controls

    Steps:
    a) The title should be displayed as 'Profile'
    b) Following controls should be displayed in 'Profile Page'
    Textboxes Controls: First Name, Last Name, Email, Phone, Password, Confirm Password, Office Section
    Label Names - Department/Division, Emp ID, Roles
    Dropdown Controls - Language, Regional Setting, Font Size for Order Extracts, Lead time in order extracts
    Calendar Control - Time zone
    Button Controls - Clear All, Save*/

    @Test
    public void WS_TC_34() throws InterruptedException, IOException {

        log.info("WS_TC_30: Verify the GUI of  'User Profile' page a) Verify the title  b) Verify the display of controls");
        popup.implicitlyWait(driver);

        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        HomePage.ClickOnProfile(driver);
        log.info("Clicked on User Profile option under My Account ");
        HomePage.AssertVerifyForTitleProfile(driver);
        HomePage.AssertVerifyForFieldsOFProfile(driver);

    }


    @AfterTest
    public void Close() throws IOException
    {
        driver.quit();




    }
}
