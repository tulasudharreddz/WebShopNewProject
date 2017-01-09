package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductSearchPage;
import pageObject.ShoppingCart;

import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.ReportResult;

/**
 * Created by t.mirasipally on 02-Jan-17.
 */
public class ShoppingCartTC extends Browser {
    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
    }

    @BeforeMethod
    public void Url() throws IOException, BiffException, WriteException {
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        log.info("URL entered in browser");
    }

    @Test
    public void WS_TC_98() throws IOException, InterruptedException ,WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(98);
            LoginPage.Loginfunctionality(driver);
            HomePage.AsertVerifyForShoppingCartLinkHomePage(driver);
            ShoppingCart.VerifyShoppingCartPageAsserts(driver);
            ReportResult("Pass");
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            ReportResult("Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            ReportResult("Fail");
        }
    }

    @Test
    public void WS_TC_99() throws IOException, InterruptedException ,WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(99);
            LoginPage.Loginfunctionality(driver);


            ReportResult("Pass");
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            ReportResult("Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            ReportResult("Fail");
        }
    }

}
