package browserStackTC;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import jxl.Sheet;
import jxl.write.WritableSheet;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import GenericLib.*;

import pageObject.*;
import org.apache.log4j.Logger;


/**
 * Created by t.mirasipally on 10/27/2016.
 */
public class HomePageBSTC extends BrowserStack{

    Browser brow = new Browser();
    DataDriven excel = new DataDriven();
    AlertHandle popup = new AlertHandle();
    ObjectRepository ob = new ObjectRepository();
    WebElement element;
    Sheet sheet;
    WritableSheet wsheet;
    Logger log = Logger.getLogger("Testing Cases");

    /*public HomePageBSTC(WebDriver driver) {
        this.driver=driver;
    }*/

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver=getDriver();
    }

   /* public WebDriver startUrl() throws IOException {
        ob.repository(driver);
        PropertyConfigurator.configure(ob.obj.getProperty("log4j"));
        //driver.get(ob.obj.getProperty("url"));
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        popup.implicitlyWait(driver);
        return driver;

    }*/

    public void TC_Home_01() throws InterruptedException, WriteException, IOException, BiffException {
        popup.implicitlyWait(driver);
        Thread.sleep(2000);
        log.info("URL entered and page is loaded");

        LoginPage.Loginfunctionality(driver);
        log.info("Log into the application successfully");
        Thread.sleep(2000);
        //To verify Shoping cart link
        HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
        log.info("Shopping Cart link Asert is verified");

        //To verify Home link
        HomePage.AsertVerifyForHomeLinkHomePage(driver);
        log.info("Home link on home page Asert is verified");

    }


@Test
    public void WS_TC_31() throws InterruptedException, IOException {

        log.info("WS_TC_30: Verify that Shop button is displaying or not and options under Shop Menu are available or not");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        log.info("Login in to the webshop application");
        //HomePage.AssertVerifyForHomePage(driver);
        Thread.sleep(1000);
        HomePage.AsertVerificationForCategoryUnderShopMenu(driver);


    }

}
