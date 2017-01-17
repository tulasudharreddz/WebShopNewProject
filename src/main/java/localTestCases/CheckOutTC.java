package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.*;

import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.ReportResult;

/**
 * Created by t.mirasipally on 16-Jan-17.
 */
public class CheckOutTC extends Browser {
    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");

    protected WebDriver driver;
    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
    }

    @Test
    public void WS_TC_140() throws IOException, WriteException, InterruptedException {

        try {
            obje.repository(driver);
            DataDriven.ReportStartup(140);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagOneProduct(driver);
            Thread.sleep(2000);
            HomePage.ClickonShoppingCart(driver);
            ShoppingCart.ClickonCheckout(driver);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Fail");
            }

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    @Test
    public void WS_TC_141() throws IOException, WriteException, InterruptedException {

        try {
            obje.repository(driver);
            DataDriven.ReportStartup(141);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagTwoProduct(driver,1);
            Thread.sleep(2000);
            HomePage.ClickonShoppingCart(driver);
            ShoppingCart.ClickonCheckout(driver);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    @Test
    public void WS_TC_142() throws IOException, WriteException, InterruptedException {

        try {
            obje.repository(driver);
            DataDriven.ReportStartup(142);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagTwoProduct(driver,2);
            Thread.sleep(2000);
            HomePage.ClickonShoppingCart(driver);
            ShoppingCart.ClickonCheckout(driver);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
                ExpectedLable(" Get Installation charges from aplication");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
                ActualLable("'Installation Service Cost ' is '€"+InstallationCost+" '", "Pass");
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_143() throws IOException, WriteException, InterruptedException {

        try {
            obje.repository(driver);
            DataDriven.ReportStartup(143);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagThreeProduct(driver,1);
            Thread.sleep(2000);
            HomePage.ClickonShoppingCart(driver);
            ShoppingCart.ClickonCheckout(driver);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
                ExpectedLable(" Get Installation charges from aplication");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
                ActualLable("'Installation Service Cost ' is '€"+InstallationCost+" '", "Pass");
            }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
}
