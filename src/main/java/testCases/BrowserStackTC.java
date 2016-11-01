package testCases;

import GenericLib.ObjectRepository;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;

/**
 * Created by t.mirasipally on 10/28/2016.
 */
public class BrowserStackTC {
    WebDriver driver;
    ObjectRepository ob = new ObjectRepository();
    Logger log=Logger.getLogger("Test Cases");


    @BeforeClass
    @org.testng.annotations.Parameters(value={"browser","version","os","os_version"})
    //@org.testng.annotations.Parameters(value={"browser","os","device"})
    public void setUp(String browser, String version, String os,String osVersion) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("os",os);
        capability.setCapability("os_version", osVersion);
        capability.setCapability("browserName", browser);
        capability.setCapability("browserVersion", version);
        capability.setCapability("project", "P1");
        capability.setCapability("build", "1.0");
        driver = new RemoteWebDriver(
                new URL("https://tulasidharreddy1:f31sxqeNs6UPCinLrkD1@hub-cloud.browserstack.com/wd/hub"),
                //new URL("https://sreenipoc1:ajhxhQxrzzx482CY3RqQ@hub-cloud.browserstack.com/wd/hub" ),
                capability);
    }

    @Test
    public void TC_Home_01()throws InterruptedException{

        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        //To verify Shoping cart link
        HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
        log.info("Shopping Cart link Asert is verified");

        //To verify Home link
        HomePage.AsertVerifyForHomeLinkHomePage(driver);
        log.info("Home link on home page Asert is verified");

    }



}
