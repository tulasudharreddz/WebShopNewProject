package pageObject;

import jxl.write.WriteException;
import localTestCases.DemoLocal;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;

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
    static private By ReferenceNumberXpath = By.xpath("//div[@class='form-group']");
    static private By TermsAndConditionsXpath = By.xpath("//label[@class='is-check-radio-label']");
    static private By PlaceOrderButton = By.xpath("//is-cart/is-review/div/div[1]/div/button");
    static private By ProductName = By.xpath("//p[@class='product-name clickable']/a");
    static private By MfrPartText = By.xpath("//span[@class='field-name'][contains(text(),'Mfr Part#')]/parent::div");
    static private By AvailabilityBlock = By.xpath("//is-availability[@class='hidden-xs']/div/span[@class='product-availability-text text-right']");
    static private By UnitPrice = By.xpath("//div[@class='col-md-2 col-md-push-0 col-sm-push-3 col-sm-9 col-xs-7 vertical-margin-auto']/p[@class='product-price']");
    static private By ActualShippingChargesXpath = By.xpath("//label[contains(text(),'Shipping Charges')]/parent::div/following-sibling::div");
    static private By ActualSalesVatXpath = By.xpath("//label[contains(text(),'Sales VAT')]/parent::div/following-sibling::div");
    static private By ActualCartGrandTotalXpath = By.xpath("//label[contains(text(),'Cart Grand Total')]/parent::div/following-sibling::div");
    static private By ActualInstallationChargesXpath = By.xpath("//label[contains(text(),'Installation Services')]/parent::div/following-sibling::div");
    static private By ActualCartSubtotalXpath = By.xpath("//label[contains(text(),'Cart Subtotal')]/parent::div/following-sibling::div");



    public static void ReviewOrderPageVerify(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Review Order Page Verification");
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
            ActualLable("'Terms And Conditions' Check box verified successfully", "Pass");
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
        Thread.sleep(3000);
        ExpectedLable("Check 'Place Order' Button is displaying or not");
        if (driver.findElements(PlaceOrderButton).size()>0) {
            String PlaceOrderString = driver.findElement(PlaceOrderButton).getText();
            Assert.assertEquals(PlaceOrderString, "Place Order");
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
    public static ArrayList<String> GetProductDetailsFromReviewOrderPage(WebDriver driver, int ProductNumber) throws IOException, WriteException, InterruptedException{
        String ProductNameSt = driver.findElements(ProductName).get(ProductNumber).getText();
        String  PartNumbertotal1= driver.findElements(MfrPartText).get(ProductNumber).getText();
        String MfrPartNumberSt= FavoriesPage.TrimMfrNumber(driver,PartNumbertotal1);
        String AvailabilityStatus = driver.findElements(AvailabilityBlock).get(ProductNumber).getText();
        String UnitPriceSt = driver.findElements(UnitPrice).get(ProductNumber).getText();
        String QuantityST;
        if(ProductNumber == 1){   QuantityST="5";  }
        else{QuantityST="1";}
        ArrayList<String> AssertNamesSC=new ArrayList<String>();
        AssertNamesSC.add(ProductNameSt);
        AssertNamesSC.add(MfrPartNumberSt);
        AssertNamesSC.add(UnitPriceSt);
        AssertNamesSC.add(AvailabilityStatus);
        AssertNamesSC.add(QuantityST);
        return AssertNamesSC;
    }
    protected static ArrayList<String> ExpectedCartSummeryArray;
    public static String ConfirmAndPlaceOrderDemo(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedCartSummeryArray = CheckOutPage.GetInstallChargesIntoArray(driver);
        String ExpectedReferenceNumber = CheckOutPage.CompleteCheckOutPage(driver);
        ReviewOrderPageVerify(driver);
        StepLable("Verify Product Details on Review Order Page");
        VerifyProductDetailsOnReviewOrderPage(driver);
        StepLable("Verify OrderReference Number on Review Page");
        String t =driver.findElement(ReferenceNumberXpath).getText();
        String[] terms = t.split("Number");
        String s = terms[1];
        String t1 = s.replaceAll(" ","");
        String ActualReferenceNumber = t1.replaceAll("\n","");
        ExpectedLable("Verify reference number is same as Provided ?");
        if(ExpectedReferenceNumber.contentEquals(ActualReferenceNumber)){
            ActualLable("Reference number is verified successfully, Expected Value : "+ExpectedReferenceNumber+" Actual Value :"+ActualReferenceNumber, "Pass");
        }else{ActualLable("Reference number is not matched, Expected Value : "+ExpectedReferenceNumber+" Actual Value :"+ActualReferenceNumber, "Fail");}
        StepLable("Verify CartSummery Details on Order Review Page");
        VerifyCartSummeryDetailsOnReviewOrderPage(driver);

        AcceptTermsAndConditions(driver);
        Thread.sleep(3000);
        ExpectedLable("Check 'Place Order' Button is displaying or not");
        if (driver.findElements(PlaceOrderButton).size()>0) {
            String PlaceOrderString = driver.findElement(PlaceOrderButton).getText();
            Assert.assertEquals(PlaceOrderString, "Place Order");
            ActualLable("'Place Order' button verified successfully", "Pass");
            Thread.sleep(1000);
            ExpectedLable("Click 'Place Order' button to confirm ");
            driver.findElement(PlaceOrderButton).click();
            ActualLable("'Place Order' Button clicked successfully", "Pass");
        } else {
            ActualLable("'Place Order' Button is not available", "Fail");
        }
        return ActualReferenceNumber;
    }
    public static boolean VerifyProductDetailsOnReviewOrderPage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        boolean Status = true;
        ArrayList<String> AssertNamesText = new ArrayList<String>();
        AssertNamesText.add("Name Of Item");
        AssertNamesText.add("MFR Part Number");
        AssertNamesText.add("Product Price");
        AssertNamesText.add("Availability Status");
        AssertNamesText.add("Quantity Of the Product");
        for (int i = 0; i <= 2; i++) {
            ArrayList<String> AssertNameFromPSearchPage1 = DemoLocal.ProductDetailsArrayList.get(i);
            ArrayList<String> ActualValue = GetProductDetailsFromReviewOrderPage(driver, i);
            for (int j = 0; j <= 4; j++) {
                String ExpectedText = AssertNameFromPSearchPage1.get(j);
                String ActualText = ActualValue.get(j);
                ExpectedLable("Verify ' " + AssertNamesText.get(j) + " On Review order page ");
                if (ExpectedText.contentEquals(ActualText)) {
                    ActualLable("Expected and Actual values are same, Expected Value : "+ExpectedText+" Actual Value :"+ActualText, "Pass");
                } else {
                    ActualLable("Failed to verify ' " + AssertNamesText.get(j) +" ' On Review order page, Expected Value : "+ExpectedText+" Actual Value :"+ActualText, "Fail");
                    Status = false;
                }
            }
        }
        return Status;
    }
    public static ArrayList<String> GetCartSummeryDetailsOnReviewOrderPage(WebDriver driver) throws IOException, WriteException, InterruptedException{
        String ActualCartSubtotalString = driver.findElement(ActualCartSubtotalXpath).getText();
        String ActualShippingChargesString =  driver.findElement(ActualShippingChargesXpath).getText();
        String ActualSalesVatChargesString =  driver.findElement(ActualSalesVatXpath).getText();
        String ActualCartGrandTotalString =  driver.findElement(ActualCartGrandTotalXpath).getText();
        String ActualInstallationChargesString =  driver.findElement(ActualInstallationChargesXpath).getText();

        ArrayList<String> AssertName1=new ArrayList<String>();
        AssertName1.add(ActualCartSubtotalString);
        AssertName1.add(ActualShippingChargesString);
        AssertName1.add(ActualSalesVatChargesString);
        AssertName1.add(ActualCartGrandTotalString);
        AssertName1.add(ActualInstallationChargesString);

        return AssertName1;
    }
    public static boolean VerifyCartSummeryDetailsOnReviewOrderPage(WebDriver driver) throws IOException, WriteException, InterruptedException{
        boolean Status = true;
        ArrayList<String> AssertNamesText = new ArrayList<String>();
        AssertNamesText.add("Cart Subtotal");
        AssertNamesText.add("Shipping Charges");
        AssertNamesText.add("Sales VAT");
        AssertNamesText.add("Cart Grand Total");
        AssertNamesText.add("Installation Services");
        for (int j = 0; j <= 4; j++) {
            String ExpectedText = ExpectedCartSummeryArray.get(j);
            String ActualText = GetCartSummeryDetailsOnReviewOrderPage(driver).get(j);
            ExpectedLable("Verify ' " + AssertNamesText.get(j) + " ' on Review order Page ");
            if (ExpectedText.contentEquals(ActualText)) {
                ActualLable(" Expected and Actual values of ' "+AssertNamesText.get(j)+" ' are same, Expected Value : "+ExpectedText+" Actual Value :"+ActualText, "Pass");
            } else {
                ActualLable("Failed to verify "+AssertNamesText.get(j)+", Expected Value : "+ExpectedText+" Actual Value :"+ActualText, "Fail");
                Status = false;
            }
        }
        return Status;
    }

}
