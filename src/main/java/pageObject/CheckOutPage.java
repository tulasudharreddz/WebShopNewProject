package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Random;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 16-Dec-16.
 */
public class CheckOutPage {
    static protected WebDriver driver;
    static Logger log = Logger.getLogger("Check Out Page");

    static private By SelectAddressXpath = By.xpath("//div[@class='col-sm-4 form-group']/is-xeditable/a");
    static private By BillingAddressXpath = By.xpath("//label[contains(text(),'Billing Address')]/parent::div/is-xeditable/a");
    static private By ShippingAddressXpath = By.xpath("//label[contains(text(),'Shipping Address')]/parent::div/is-xeditable/a");
    static private By InstallAddressXpath = By.xpath("//label[contains(text(),'Install Address')]/parent::div/is-xeditable/a");
    static private By CheckOutPageTitleXpath = By.xpath("//header[@class='content-header']/h2");
    static private By BillingAddressDropdown = By.xpath("(//span[@class='select2-selection select2-selection--single'])[1]");
    static private By ShippingAddressDropdown = By.xpath("(//span[@class='select2-selection select2-selection--single'])[1]");
    static private By InstallAddressDropdown = By.xpath("(//span[@class='select2-selection select2-selection--single'])[1]");
    static private By SelectAddress = By.xpath("//span[@class='select2-dropdown select2-dropdown--below']/span/ul/li[@data-index='1']");
    static private By ConfirmAddress = By.xpath("//button[@type='submit']");
    static private By refNumXpath = By.id("refNum");
    static private By InstallationServiceCostText = By.xpath("//span[@class='label-link-color']");
    static private By ReviewOrderXpath = By.xpath("//button[contains(text(),'Review Order')]");


    public static void VerifyCheckoutPageTitle(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ShoppingCart.ClickonCheckout(driver);
        Thread.sleep(2000);
        ExpectedLable("Check 'Proceed to checkout' button is displaying or not");
        if (driver.findElement(CheckOutPageTitleXpath).isDisplayed()) {
            String CheckOutPageTitle = driver.findElement(CheckOutPageTitleXpath).getText();
            Assert.assertEquals(CheckOutPageTitle, "Checkout");
            ActualLable("'Proceed to checkout' button verified successfully", "Pass");
            log.info("Assert is verified for 'Proceed to checkout' button");
        }
        else{
            ActualLable("'Proceed to checkout' button is not available", "Fail");
        }
    }
    public static void SelectingAddress(WebDriver driver) throws IOException, WriteException, InterruptedException {
        VerifyCheckoutPageTitle(driver);

        for(int i =0; i<3;i++) {
            ExpectedLable("Select"+ i +"from Drop down");
            log.info(driver.findElements(SelectAddressXpath).get(i).getText());
            if(driver.findElements(SelectAddressXpath).get(i).getText().contentEquals("Select an address")) {
                driver.findElements(SelectAddressXpath).get(i).click();
                driver.findElement(InstallAddressDropdown).click();
                driver.findElement(SelectAddress).click();
                driver.findElement(ConfirmAddress).click();
                ActualLable( i+"address selected successfully ", "Pass");
            }
            else{
                driver.findElements(SelectAddressXpath).get(i).click();
                Thread.sleep(3000);
                driver.findElement(InstallAddressDropdown).click();
                driver.findElement(SelectAddress).click();
                driver.findElement(ConfirmAddress).click();
                ActualLable(i+" selected successfully ", "Pass");
            }
        }
    }

    public static void SelectAddress(WebDriver driver) throws IOException, WriteException, InterruptedException {
        VerifyCheckoutPageTitle(driver);
        //select Billing Address
        ExpectedLable("Select Billing address from Drop down");
        driver.findElement(BillingAddressXpath).click();
        driver.findElement(BillingAddressDropdown).click();
        Thread.sleep(2000);
        driver.findElement(BillingAddressDropdown).click();
        driver.findElement(SelectAddress).click();
        driver.findElements(By.xpath("//div[@class='editable-buttons']/button[@type='submit']")).get(0).click();
        ActualLable("Billing address selected successfully ", "Pass");
        Thread.sleep(2000);

        //select Shipping Address
        ExpectedLable("Select Shipping address from Drop down");
        driver.findElement(ShippingAddressXpath).click();
        driver.findElement(ShippingAddressDropdown).click();
        Thread.sleep(1000);
        driver.findElement(ShippingAddressDropdown).click();
        driver.findElement(By.xpath("//li[@data-index='1']")).click();
        driver.findElement(ConfirmAddress).click();
        ActualLable("Shipping address selected successfully ", "Pass");
        Thread.sleep(3000);

        //select Install Address
        ExpectedLable("Select install address from Drop down");
        driver.findElement(InstallAddressXpath).click();
        driver.findElement(InstallAddressDropdown).click();
        Thread.sleep(1000);
        driver.findElement(InstallAddressDropdown).click();
        driver.findElement(SelectAddress).click();
        driver.findElement(ConfirmAddress).click();
        ActualLable("install address selected successfully ", "Pass");

    }

    public static void ClickonProceedtoCheckout(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Complete Check out page action");
        SelectingAddress(driver);
        ExpectedLable("Provide Reference Number in the blank");
        Random rand = new Random();
        int  ReferenceNumber = rand.nextInt(9999) + 1000;
        String ReferenceNumberString=Integer.toString(ReferenceNumber);
        driver.findElement(refNumXpath).sendKeys("IRN"+ReferenceNumberString);
        ActualLable("Successfully provided Refference number in the blank ", "Pass");
        String ProceedtoCheckoutText = driver.findElement(ReviewOrderXpath).getText();

        ExpectedLable("Check 'Proceed to checkout' button is displaying or not");
        Assert.assertEquals(ProceedtoCheckoutText, "Review Order");
        if (driver.findElement(ReviewOrderXpath).isEnabled()) {
            ActualLable("'Proceed to checkout' button verified successfully", "Pass");
            log.info("Assert is verified for 'Proceed to checkout' button");
            Thread.sleep(1000);
            ExpectedLable("Click on 'Proceed to checkout' button ");
            driver.findElement(ReviewOrderXpath).click();
            ActualLable("'Proceed to checkout' button clicked successfully", "Pass");
        }
        else{
            ActualLable("'Proceed to checkout' button is not available", "Fail");
        }
    }

    public static boolean CheckInstallationServiceCostMessage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        HomePage.ClickonShoppingCart(driver);
        ShoppingCart.ClickonCheckout(driver);
        ExpectedLable(" Check Installation Service Cost Message is displaying or not");
        boolean status;
        if(driver.findElements(InstallationServiceCostText).size()>0){
            status=true;
            ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
        }else{
            ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            status=false;
        }
        return status;
    }
    public static double GetInstallationCost(WebDriver driver){
        String Mesage = driver.findElement(InstallationServiceCostText).getText();
        String[] terms = Mesage.split("\\€");
        String s= terms[1];
        String t = s.replaceAll(" ", "");
        String t1 = t.replaceAll("[?]", "");
        double InstallCost = Double.parseDouble(t1);
        return InstallCost;
    }

}
