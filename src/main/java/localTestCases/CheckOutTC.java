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
    public void WS_TC_115() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(115);
            LoginPage.Loginfunctionality(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart is having Products or not");
            if(noOfItemsafterAddtoCart>0){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyCheckOutButtonFunctionality(driver);
                CheckOutPage.ClickonProceedtoCheckout(driver);
                ReviewOrderPage.COnfirmAndPlaceOrder(driver);
                OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
            }
            else{
                ActualLable("Products are not available in Shopping cart, now add products to cart","Pass");
                ProductSearchPage.AddToShoppingCart(driver);
                ShoppingCart.VerifyCheckOutButtonFunctionality(driver);
                CheckOutPage.ClickonProceedtoCheckout(driver);
                ReviewOrderPage.COnfirmAndPlaceOrder(driver);
                OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }

    @Test
    public void WS_TC_116() throws IOException, InterruptedException, WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(116);
            LoginPage.Loginfunctionality(driver);
            log.info("Login in to the webshop application");
            double noOfItems = ShoppingCart.DeleteExistItem(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart count functionality by adding product to cart");
            if(noOfItemsafterAddtoCart>noOfItems){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyItemCount(driver);
                ShoppingCart.VerifyCartGrandTotal(driver);
                CheckOutPage.ClickonProceedtoCheckout(driver);
                ReviewOrderPage.COnfirmAndPlaceOrder(driver);
                OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
                EmailVerificationDetails.VerifyOrderEmailInOutLook(driver);
            }
            else{  ActualLable("verification failed for cart Number functionality","Fail");     }
        } catch (AssertionError e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }

    @Test
    public void WS_TC_117() throws IOException, InterruptedException, WriteException {
        try {
            DataDriven.ReportStartup(117);
            LoginPage.Loginfunctionality(driver);
            double noOfItems = ShoppingCart.DeleteExistItem(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            ProductSearchPage.AddToShoppingCart(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart count functionality by adding product to cart");
            if(noOfItemsafterAddtoCart>noOfItems){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyItemCount(driver);
                ShoppingCart.VerifyCartGrandTotal(driver);
                String ReferenceNumber = CheckOutPage.ClickonProceedtoCheckout(driver);
                ReviewOrderPage.COnfirmAndPlaceOrder(driver);
                OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
                boolean Stuas = EmailVerificationDetails.VerifyOrderEmailInOutLook(driver);
                if(Stuas==true) {
                    EmailVerificationDetails.VerifyReferenceLink(driver, ReferenceNumber);
                }
            } else{  ActualLable("verification failed for cart Number functionality","Fail");     }
        } catch (AssertionError e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
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
            ProductSearchPage.AddFlagOneProduct(driver,1);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){   ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass"); }
            else{ ActualLable("'Installation Service Cost ' Message is Displaying", "Fail"); }

        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
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
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
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
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
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
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Fail");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }
    @Test
    public void WS_TC_145() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(145);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagOneProduct(driver,2);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Fail");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }

    @Test
    public void WS_TC_146() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(146);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagOneProduct(driver,1);
            ProductSearchPage.AddFlagTwoProduct(driver,1);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if(messageStatus==false){
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            }
            else{
                ActualLable("'Installation Service Cost ' Message is Displaying", "Fail");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }
    @Test
    public void WS_TC_147() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(147);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagOneProduct(driver, 1);
            ProductSearchPage.AddFlagThreeProduct(driver, 1);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if (messageStatus == false) {
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            } else {
                ActualLable("'Installation Service Cost ' Message is Displaying", "Fail");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }

    @Test
    public void WS_TC_149() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(149);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagTwoProduct(driver, 2);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if (messageStatus == false) {
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
            } else {
                ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }

    @Test
    public void WS_TC_150() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(150);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagTwoProduct(driver, 1);
            ProductSearchPage.AddFlagThreeProduct(driver, 1);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if (messageStatus == false) {
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
            } else {
                ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }

    @Test
    public void WS_TC_152() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(152);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagThreeProduct(driver,2);
            boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
            ExpectedLable(" Check Installation Service Cost message, status message should not display");
            if (messageStatus == false) {
                ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            } else {
                ActualLable("'Installation Service Cost ' Message is Displaying", "Fail");
                Double InstallationCost = CheckOutPage.GetInstallationCost(driver);
            }
        } catch (AssertionError e) { ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");
        } catch (Exception e) {  ActualLable("Exception " + e.getClass().getSimpleName(), "Fail");  }
    }
}
