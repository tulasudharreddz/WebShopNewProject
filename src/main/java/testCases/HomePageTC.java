package testCases;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import jxl.Sheet;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.testng.annotations.Parameters;

import java.io.IOException;
import GenericLib.*;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Created by t.mirasipally on 10/27/2016.
 */
public class HomePageTC {
    WebDriver driver;
    Browser brow = new Browser();
    DataDriven excel = new DataDriven();
    AlertHandle popup = new AlertHandle();
    ObjectRepository ob = new ObjectRepository();
    WebElement element;
    Sheet sheet;
    WritableSheet wsheet;
    Logger log=Logger.getLogger("Test Cases");


    @Parameters("browser")
    @BeforeTest
    public void start(String browser) throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException {
        if (browser.equalsIgnoreCase(browser)) {
            driver = brow.selectbrowser(browser);
        }

        sheet = excel.ReadSheet(sheet);
        wsheet = excel.writeSheet(wsheet, "test", "TestCase1");
        PropertyConfigurator.configure(ob.obj.getProperty("log4j"));

        ob.repository(driver);
        driver.get(ob.obj.getProperty("url"));
    }

    @Test
    public void TC_Home_01()throws InterruptedException{

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
