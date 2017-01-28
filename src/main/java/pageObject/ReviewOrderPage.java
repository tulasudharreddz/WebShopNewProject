package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 19-Dec-16.
 */
public class ReviewOrderPage {

    static protected WebDriver driver;
    static Logger log = Logger.getLogger("Review Order Page");


    static private By PageTitle = By.xpath("//h2");
    static private By TermsAndConditionsXpath = By.xpath("//label[@class='is-check-radio-label']");
    static private By PlaceOrderButton = By.xpath("//button[Contains(text(),'Place Order')]");


    public static void ReviewOrderPageVerify(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Review Order");
        Thread.sleep(2000);
        String PageAssert = driver.findElement(PageTitle).getText();
        ExpectedLable("Check 'Review Order' page is opened or not");
        Assert.assertEquals(PageAssert, "Review");
        ActualLable("'Review Order' page verified successfully", "Pass");
    }

    public static void AcceptTermsAndConditions(WebDriver driver) throws IOException, WriteException, InterruptedException {
        String TermsAndConditionsString = driver.findElement(TermsAndConditionsXpath).getText();
        ExpectedLable("Check 'Terms And Conditions' check box is displaying or not");
        Assert.assertEquals(TermsAndConditionsString, "I have read and accept the terms and conditions of sale.");
        if (driver.findElement(TermsAndConditionsXpath).isDisplayed()) {
            ActualLable("'Proceed to checkout' button verified successfully", "Pass");
            log.info("Assert is verified for 'Terms And Conditions'");
            Thread.sleep(1000);
            ExpectedLable("Click 'Terms And Conditions' Check box ");
            driver.findElement(TermsAndConditionsXpath).click();
            ActualLable("'Terms And Conditions' Check box clicked successfully", "Pass");
        } else {
            ActualLable("'Terms And Conditions' Check box is not available", "Fail");
        }
    }

    public static void COnfirmAndPlaceOrder(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ReviewOrderPageVerify(driver);
        AcceptTermsAndConditions(driver);
        ExpectedLable("Check 'Place Order' Button is displaying or not");
        String PlaceOrderString = driver.findElement(PlaceOrderButton).getText();
        Assert.assertEquals(PlaceOrderString, "Place Order");
        if (driver.findElement(PlaceOrderButton).isEnabled()) {
            ActualLable("'Place Order' button verified successfully", "Pass");
            log.info("Assert is verified for 'Place Order'");
            Thread.sleep(1000);
            ExpectedLable("Click 'Place Order' button to confirm ");
            driver.findElement(PlaceOrderButton).click();
            ActualLable("'Place Order' Button clicked successfully", "Pass");
        } else {
            ActualLable("'Place Order' Button is not available", "Fail");
        }

    }

}
