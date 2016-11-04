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

        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
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

    TC_Home_02: Verify that My menu button is displaying or not and options under menu are available or not

    */

    @Test
    public void TC_Home_02() throws InterruptedException, IOException {
        log.info("TC_Home_02: Verify that My menu button is displaying or not and options under menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(4000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        HomePage.AssertVerifyForHomePage(driver);
        log.info("Login in to the webshop application");
        HomePage.ListOfOptionsMyAccountMenu(driver);

        log.info("TC_Home_02: Verified");
    }

    /*

    TC_Home_03: Verify that Shop menu button is displaying or not and options under Shop menu are available or not

    */

    @Test
    public void TC_Home_03() throws InterruptedException, IOException {
        log.info("TC_Home_03: Verify that Shop button is displaying or not and options under Shop Menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        log.info("Login in to the webshop application");
        HomePage.AssertVerifyForHomePage(driver);
        HomePage.VerifyDropDownListUnderShopMenu(driver);
        log.info("TC_Home_03: Verified");
    }

    /*

    TC_Home_04: Verify Each menu under Shop Menu

    */

    @Test
    public void TC_Home_04() throws InterruptedException, IOException {

        log.info("TC_Home_03: Verify that Shop button is displaying or not and options under Shop Menu are available or not");
        popup.implicitlyWait(driver);

        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        log.info("Login in to the webshop application");
        HomePage.AssertVerifyForHomePage(driver);
        HomePage.AsertVerificationForCategoryUnderShopMenu(driver);


    }
    @Test
    public void TC_Home_05() throws InterruptedException, IOException {

        List<WebElement> MenuList = driver.findElements(By.id("email"));
        int NoOfSubMenuList = MenuList.size();
        log.info("Number of Sub menu under shop button " + NoOfSubMenuList);



    }


    @AfterTest
    public void Close() throws IOException
    {
        driver.quit();




    }
}
