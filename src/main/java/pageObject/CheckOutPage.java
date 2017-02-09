package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
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
    static private By SelectAddress = By.xpath("//span[@class='select2-dropdown select2-dropdown--below']/span/ul/li[@data-index='0']");
    static private By ConfirmAddress = By.xpath("//button[@type='submit']");
    static private By refNumXpath = By.id("refNum");
    static private By InstallationServiceCostText = By.xpath("//span[@class='label-link-color']");
    static private By ReviewOrderXpath = By.xpath("//button[contains(text(),'Review Order')]");


    public static void VerifyCheckoutPageAssert(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ShoppingCart.ClickonCheckout(driver);
        Thread.sleep(2000);
        ExpectedLable("Check 'checkout' title is displaying or not");
        if (driver.findElement(CheckOutPageTitleXpath).isDisplayed()) {
            String CheckOutPageTitle = driver.findElement(CheckOutPageTitleXpath).getText();
            Assert.assertEquals(CheckOutPageTitle, "Checkout");
            ActualLable("'checkout' Page title verified successfully", "Pass");
        }
        else{
            ActualLable("'Proceed to checkout' button is not available", "Fail");
        }
    }
    public static void SelectingAddress(WebDriver driver) throws IOException, WriteException, InterruptedException {
        VerifyCheckoutPageAssert(driver);
        StepLable("Complete Check out page action");
        ArrayList<String> AddressNames=new ArrayList<String>();
        AddressNames.add("Billing Addresses");
        AddressNames.add("Shipping Addresses");
        AddressNames.add("Install Addresses");
        for(int i =0; i<3;i++) {
            ExpectedLable("Select "+AddressNames.get(i)+" from drop down ");
            log.info(driver.findElements(SelectAddressXpath).get(i).getText());
            if(driver.findElements(SelectAddressXpath).get(i).getText().contentEquals("Select an address")) {
                driver.findElements(SelectAddressXpath).get(i).click();
                driver.findElement(InstallAddressDropdown).click();
                driver.findElement(SelectAddress).click();
                driver.findElement(ConfirmAddress).click();
                ActualLable( AddressNames.get(i)+"address selected successfully ", "Pass");
            }
            else{
                driver.findElements(SelectAddressXpath).get(i).click();
                Thread.sleep(3000);
                driver.findElement(InstallAddressDropdown).click();
                driver.findElement(SelectAddress).click();
                driver.findElement(ConfirmAddress).click();
                ActualLable(AddressNames.get(i)+" selected successfully ", "Pass");
            }
        }
    }
    public static void SelectAddress(WebDriver driver) throws IOException, WriteException, InterruptedException {
        //VerifyCheckoutPageAssert(driver);
        //select Billing Address
        StepLable("Complete Check out page action");
        ArrayList<String> AddressNames=new ArrayList<String>();
        AddressNames.add("Billing Addresses");
        AddressNames.add("Shipping Addresses");
        AddressNames.add("Install Addresses");
        for(int i =0; i<3;i++) {
            ExpectedLable("Select "+AddressNames.get(i)+" from drop down ");
            log.info(driver.findElements(SelectAddressXpath).get(i).getText());
            if(driver.findElements(SelectAddressXpath).get(i).getText().contentEquals("Select an address")) {
                driver.findElements(SelectAddressXpath).get(i).click();
                driver.findElement(InstallAddressDropdown).click();
                driver.findElement(SelectAddress).click();
                driver.findElement(ConfirmAddress).click();
                ActualLable( AddressNames.get(i)+"address selected successfully ", "Pass");
            }
            else{
                driver.findElements(SelectAddressXpath).get(i).click();
                Thread.sleep(3000);
                driver.findElement(InstallAddressDropdown).click();
                driver.findElement(SelectAddress).click();
                driver.findElement(ConfirmAddress).click();
                ActualLable(AddressNames.get(i)+" selected successfully ", "Pass");
            }
        }

    }
    public static String ClickonProceedtoCheckout(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Complete Check out page action");
        SelectingAddress(driver);
        ExpectedLable("Provide Reference Number in the blank");
        Random rand = new Random();
        int  ReferenceNumbe = rand.nextInt(9999) + 1000;
        String ReferenceNumberString=Integer.toString(ReferenceNumbe);
        String ReferenceNumber = "IRNQATEAMTESTORDER"+ReferenceNumberString;
        driver.findElement(refNumXpath).sendKeys(ReferenceNumber);
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
        return ReferenceNumber;
    }

    public static String CompleteCheckOut(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Complete Check out page action");
        SelectAddress(driver);
        ExpectedLable("Provide Reference Number in the blank");
        Random rand = new Random();
        int  ReferenceNumbe = rand.nextInt(9999) + 1000;
        String ReferenceNumberString=Integer.toString(ReferenceNumbe);
        String ReferenceNumber = "IRNQATEAMTESTORDER"+ReferenceNumberString;
        driver.findElement(refNumXpath).sendKeys(ReferenceNumber);
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
        return ReferenceNumber;
    }
    public static boolean CheckInstallationServiceCostMessage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        HomePage.ClickonShoppingCart(driver);
        ShoppingCart.ClickonCheckout(driver);
        ExpectedLable(" Check Installation Service Cost Message is displaying or not");
        boolean status;
        if(driver.findElements(InstallationServiceCostText).size()>0){
            status=true;
            Thread.sleep(1000);
            //driver.findElement(InstallationServiceCostText).click();
            ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
        }else{
            ActualLable("'Installation Service Cost ' Message is not Displaying", "Pass");
            status=false;
        }
        return status;
    }
    public static double GetInstallationCost(WebDriver driver) throws IOException, WriteException {
        double InstallCost = 0.00;
        ExpectedLable(" Get Installation charges from aplication");
        if(driver.findElements(InstallationServiceCostText).size()>0) {
            String Mesage = driver.findElement(InstallationServiceCostText).getText();
            String[] terms = Mesage.split("\\€");
            String s = terms[1];
            String t = s.replaceAll(" ","");
            String t1 = t.replaceAll("[?]", "");
            InstallCost = Double.parseDouble(t1);
            driver.findElement(InstallationServiceCostText).click();
            ActualLable("'Installation Service Cost ' is '€"+InstallCost+" '", "Pass");

        }
        return InstallCost;
    }
    public static Double VerifyStatusForInstallationServices(WebDriver driver) throws IOException, WriteException, InterruptedException {
        boolean messageStatus = CheckOutPage.CheckInstallationServiceCostMessage(driver);
        Double InstallationCost = null;
        ExpectedLable(" Check Installation Service Cost message, status message should not display");
        if(messageStatus==false){
            ActualLable("'Installation Service Cost ' Message is not Displaying", "Fail");
        }
        else{
            ActualLable("'Installation Service Cost ' Message is Displaying", "Pass");
            InstallationCost = CheckOutPage.GetInstallationCost(driver);
        }
        return InstallationCost;
    }
    public static ArrayList<String> GetInstallChargesIntoArray(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ArrayList<String> CartSummeryValues = ShoppingCart.VerifyCartSummeryTotalDemo(driver);
        SelectingAddress(driver);
        double InstallationCost = GetInstallationCost(driver);
        String InstallationCostSt = "€" + InstallationCost;
        CartSummeryValues.add(InstallationCostSt);
        String SalesVat = CartSummeryValues.get(2);
        String s2 = SalesVat.replaceAll("[€$£₹,]","");
        Double UpdatedSalesVat = Double.parseDouble(s2);
        UpdatedSalesVat= InstallationCost*19/100 + UpdatedSalesVat;
        double UpdatedSalesVat1 = Math.round( UpdatedSalesVat * 100.0 ) / 100.0;
        String UpdatedSalesVatFinal=String.format("%,.2f", UpdatedSalesVat1);
        String UpdatedSalesVatSt = "€"+UpdatedSalesVatFinal;
        //String UpdatedSalesVatSt = Double.toString(UpdatedSalesVat);
        CartSummeryValues.get(2).replace(SalesVat,UpdatedSalesVatSt);
        String CartGrandTotalSt = CartSummeryValues.get(3);
        String s3 = CartGrandTotalSt.replaceAll("[€$£₹,]","");
        Double UpdatedGrandTotal = Double.parseDouble(s3);
        UpdatedGrandTotal=UpdatedGrandTotal+InstallationCost*19/100+InstallationCost;
        double UpdatedGrandTotal1 = Math.round( UpdatedGrandTotal * 100.0 ) / 100.0;
        String UpdatedGrandTotalFinal=String.format("%,.2f", UpdatedGrandTotal1);
        String UpdatedGrandTotalSt = "€"+UpdatedGrandTotalFinal;
        //String UpdatedGrandTotalSt = Double.toString(UpdatedGrandTotal);
        CartSummeryValues.get(3).replace(CartGrandTotalSt,UpdatedGrandTotalSt);
        ArrayList<String> CartSummeryUpdated=new ArrayList<String>();
        CartSummeryUpdated.add(CartSummeryValues.get(0));
        CartSummeryUpdated.add(CartSummeryValues.get(1));
        CartSummeryUpdated.add(UpdatedSalesVatSt);
        CartSummeryUpdated.add(UpdatedGrandTotalSt);
        CartSummeryUpdated.add(InstallationCostSt);

        return CartSummeryUpdated;
    }
    public static String CompleteCheckOutPage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Provide Reference Number in the blank");
        Random rand = new Random();
        int  ReferenceNumbe = rand.nextInt(9999) + 1000;
        String ReferenceNumberString=Integer.toString(ReferenceNumbe);
        String ReferenceNumber = "IRNQATEAMTESTORDER"+ReferenceNumberString;
        driver.findElement(refNumXpath).sendKeys(ReferenceNumber);
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
        return ReferenceNumber;
    }
}
