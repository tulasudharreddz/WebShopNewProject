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

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ReportResult;
import static GenericLib.DataDriven.StepLable;


/**
 * Created by t.mirasipally on 10/27/2016.
 */
public class HomePageTC extends Browser{
    private Browser brow = new Browser();
    private DataDriven excel = new DataDriven();
    private AlertHandle popup = new AlertHandle();
    private ObjectRepository ob = new ObjectRepository();
    private ScreenShots sc = new ScreenShots();
    private WebElement element;
    private Sheet sheet;
    private WritableSheet wsheet;
    Logger log = Logger.getLogger("Testing Cases");

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
    }


    @Test
    public void TC_Home_01() throws Exception {
        try {
            DataDriven.ReportStartup(1);
            log.info("URL entered and page is loaded");
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(2000);
            //To verify Shoping cart link
            HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
            log.info("Shopping Cart link Asert is verified");
            //To verify Home link
            HomePage.AsertVerifyForHomeLinkHomePage(driver);
            log.info("Home link on home page Asert is verified");
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    /*

    WS_TC_30: AssertVerifyForDefaultAddress that Shop menu button is displaying or not and options under Shop menu are available or not

    */

    @Test
    public void WS_TC_30() throws InterruptedException, IOException, WriteException, BiffException {
        try{
            log.info("WS_TC_30: AssertVerifyForDefaultAddress that Shop button is displaying or not and options under Shop Menu are available or not");

            DataDriven.ReportStartup(30);

            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(2000);
            log.info("Login in to the webshop application");
            HomePage.AssertVerifyForHomePage(driver);
            HomePage.VerifyDropDownListUnderShopMenu(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    /*

    WS_TC_31: AssertVerifyForDefaultAddress Each menu under Shop Menu


    */

    @Test
    public void WS_TC_31() throws InterruptedException, IOException, WriteException, BiffException {
        try {
            DataDriven.ReportStartup(31);
            log.info("WS_TC_30: AssertVerifyForDefaultAddress that Shop button is displaying or not and options under Shop Menu are available or not");
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(2000);
            log.info("Login in to the webshop application");
            HomePage.AssertVerifyForHomePage(driver);
            HomePage.AsertVerificationForCategoryUnderShopMenu(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    /*
   WS_TC_32: AssertVerifyForDefaultAddress that My menu button is displaying or not and options under menu are available or not
   */
    @Test
    public void WS_TC_32() throws InterruptedException, IOException, WriteException, BiffException {
        try {
            log.info("WS_TC_32: Assert verify that My menu button is displaying or not and options under menu are available or not");
            DataDriven.ReportStartup(32);
            Thread.sleep(4000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(2000);
            HomePage.AssertVerifyForHomePage(driver);
            log.info("Login in to the webshop application");
            HomePage.ListOfOptionsMyAccountMenu(driver);
            //StepLable("WS_TC_32: Successfully Verified");
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
        }
    }


}
