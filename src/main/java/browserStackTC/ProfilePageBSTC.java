package browserStackTC;

import GenericLib.Browser;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.*;
import sun.net.www.content.text.Generic;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import GenericLib.*;

/**
 * Created by t.mirasipally on 11/9/2016.
 */
public class ProfilePageBSTC extends BrowserStack{
    public WebElement element;

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver=getDriver();
    }


    @Test
    public void WS_TC_31() throws InterruptedException, IOException {

        driver.get("https://directqa2.dimensiondata.com/Webshop/login");

        //log.info("WS_TC_30: AssertVerifyForDefaultAddress that Shop button is displaying or not and options under Shop Menu are available or not");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(2000);
        //log.info("Login in to the webshop application");
        HomePage.AssertVerifyForHomePage(driver);
        Thread.sleep(1000);
        HomePage.AsertVerificationForCategoryUnderShopMenu(driver);
    }
    /*@AfterTest
    public void Close() throws IOException
    {
        driver.quit();

    }*/


}
