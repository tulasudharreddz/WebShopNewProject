package browserStackTC;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import jxl.Sheet;
import jxl.write.WritableSheet;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

import GenericLib.*;

import pageObject.*;
import org.apache.log4j.Logger;


/**
 * Created by t.mirasipally on 10/27/2016.
 */
public class HomePageBSTC {
    public static WebDriver driver;
    Browser brow = new Browser();
    DataDriven excel = new DataDriven();
    AlertHandle popup = new AlertHandle();
    ObjectRepository ob = new ObjectRepository();
    WebElement element;
    Sheet sheet;
    WritableSheet wsheet;
    Logger log = Logger.getLogger("Test Cases");
    @BeforeTest

    @Parameters(value = {"browserN", "version", "os", "osVersion","resolution"})
    public WebDriver startB(String browserN, String version, String os, String osVersion, String resolution) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("os", os);
        capability.setCapability("os_version", osVersion);
        capability.setCapability("browserName", browserN);
        capability.setCapability("browserVersion", version);
        capability.setCapability("resolution", resolution);
        capability.setCapability("project", "P1");
        capability.setCapability("build", "1.0");

        driver = new RemoteWebDriver(
                new URL("https://tulasidhar1:hM4bFqpv5Lo5Vqf4XyuB@hub-cloud.browserstack.com/wd/hub"),
                //new URL("https://tulasidharreddy1:f31sxqeNs6UPCinLrkD1@hub-cloud.browserstack.com/wd/hub"),
                //new URL("https://sreenipoc1:ajhxhQxrzzx482CY3RqQ@hub-cloud.browserstack.com/wd/hub" ),
                capability);

        ob.repository(driver);
        PropertyConfigurator.configure(ob.obj.getProperty("log4j"));
        driver.get(ob.obj.getProperty("url"));
        return driver;
    }

    //@Parameters(value = {"browserN", "version", "os", "osVersion","resolution"})
    public WebDriver startB() throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("os", "WINDOWS");
        capability.setCapability("os_version", "8");
        capability.setCapability("browserName", "chrome");
        capability.setCapability("browserVersion", "53");
        capability.setCapability("resolution", "2048x1536");
        capability.setCapability("project", "P1");
        capability.setCapability("build", "1.0");

        driver = new RemoteWebDriver(
                new URL("https://tulasidhar1:hM4bFqpv5Lo5Vqf4XyuB@hub-cloud.browserstack.com/wd/hub"),
                //new URL("https://tulasidharreddy1:f31sxqeNs6UPCinLrkD1@hub-cloud.browserstack.com/wd/hub"),
                //new URL("https://sreenipoc1:ajhxhQxrzzx482CY3RqQ@hub-cloud.browserstack.com/wd/hub" ),
                capability);

        ob.repository(driver);
        PropertyConfigurator.configure(ob.obj.getProperty("log4j"));
        driver.get(ob.obj.getProperty("url"));
        return driver;
    }

    @Test
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


    public void TC_Home_02() throws InterruptedException, IOException {

        log.info("Second method");
    }

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

    @AfterTest
    public void Close() throws IOException
    {
        driver.quit();

    }
}
