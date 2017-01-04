package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

import GenericLib.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created by t.mirasipally on 09-Dec-16.
 */
public class ShoppingCart {
    static protected WebDriver driver;
    static Logger log = Logger.getLogger("Testing Cases");

    //Page Elements
    static private By OpenItmDetailsPannel = By.xpath("//is-tabsetpanel[@header='Item Details']/div/div[@class='panel-collapse collapse in']");
    static private By ItemDetails = By.xpath("//a[contains(text(),'Item Details')]");
    static private By DeleteItem = By.xpath("//a[@class='anchor-delete']");
    static private By Quantity = By.xpath("//div[@class='text-center']/input");
    static private By UnitPrice = By.xpath("//div[@class='col-md-2 col-md-push-0 col-sm-push-3 col-sm-9 col-xs-7 vertical-margin-auto']/p[@class='product-price']");
    static private By ExtendedPrice = By.xpath("//div[@class='col-md-2 col-md-push-0 col-sm-push-3 col-sm-9 hidden-xs vertical-margin-auto price-column']/p[@class='product-price']");
    static private By CartSummeryPannel = By.xpath("//a[contains(text(),'Cart Summary')]");
    static private By ActualCartSubtotalXpath = By.xpath("//label[contains(text(),'Cart Subtotal')]/parent::div/following-sibling::div");
    static private By ActualShippingChargesXpath = By.xpath("//label[contains(text(),'Shipping Charges')]/parent::div/following-sibling::div");
    static private By ActualSalesVatXpath = By.xpath("//label[contains(text(),'Sales VAT')]/parent::div/following-sibling::div");
    static private By ActualCartGrandTotalXpath = By.xpath("//label[contains(text(),'Cart Grand Total')]/parent::div/following-sibling::div");
    static private By ProceedtocheckoutXpath = By.xpath("//button[contains(text(),'Proceed to checkout')]");


    public static void OpenItemDetails(WebDriver driver){
        if(driver.findElements(OpenItmDetailsPannel).size()>0){
        }
        else {
            driver.findElement(ItemDetails).click();
        }
    }
    public static void DeleteItem(WebDriver driver) throws InterruptedException, IOException, WriteException {
        Thread.sleep(2000);
        ExpectedLable("Verify number of items available in the shopping cart");
        if(driver.findElements(DeleteItem).size()>0){
            double noOfItem = driver.findElements(DeleteItem).size();
            ActualLable("Number of items available in the shopping cart are "+noOfItem ,"Pass");
            ExpectedLable("Now Delete all items from shopping cart");
            //for(double i=1; i<=noOfItem; i++) {
            do{
                driver.findElements(DeleteItem).get(0).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
                Thread.sleep(1000);
            }
            while (driver.findElements(DeleteItem).size()>0);
            ActualLable("All products are deleted, items are not available in shopping cart" ,"Pass");
        }
        else{
            ActualLable("Products are not available in shopping cart " ,"Pass");
        }
    }
    public static double DeleteExistItem(WebDriver driver) throws InterruptedException, IOException, WriteException {
        Thread.sleep(1000);
        StepLable("Checking cart is already having products or not if yes delete all products");
        double noOfCartItemsAavailable = HomePage.VerifyCart(driver);
        if(noOfCartItemsAavailable>0){
            HomePage.ClickonShoppingCart(driver);
            ShoppingCart.DeleteItem(driver);
            noOfCartItemsAavailable=0;
        }
        else{
            noOfCartItemsAavailable=0;
        }
        return noOfCartItemsAavailable;
    }
    public static List<WebElement> QuantityBlank(WebDriver driver) {

        List<WebElement> noQuantity = driver.findElements(Quantity);
        return noQuantity;
    }
    public static int VerifyItemCount(WebDriver driver) throws IOException, WriteException, InterruptedException {
        HomePage.ClickonShoppingCart(driver);
        int noQuantity = QuantityBlank(driver).size();
        int Totalsum = 0;
        ExpectedLable("Verify Quantity available in the shopping cart");
        if(noQuantity>0) {
            for (int i = 1; i <= noQuantity; i++) {
                String ValueInBlank = QuantityBlank(driver).get(noQuantity-1).getAttribute("value");
                log.info(ValueInBlank);
                Thread.sleep(5000);
                int QuantityperBlank = Integer.parseInt(ValueInBlank);
                Totalsum = Totalsum+QuantityperBlank;
            }
            log.info("Total number of items are "+Totalsum);
            if(HomePage.VerifyCart(driver)==Totalsum) {
                ActualLable("Successfully Verified Total Quantity, Total Quantity is " + Totalsum, "Pass");
            }
            else{
                ActualLable("Verification failed for Quantity in cart ", "Fail");
            }
        }
        else{
            ActualLable("There are no items available in Shoping cart" ,"Pass");
        }
        return Totalsum;
    }

    public static List<WebElement> UnitPrice(WebDriver driver) {

        List<WebElement> noUnitPrice = driver.findElements(UnitPrice);
        return noUnitPrice;
    }
    public static List<WebElement> ExtendedPrice(WebDriver driver) {

        List<WebElement> noExtendedPrice = driver.findElements(ExtendedPrice);
        return noExtendedPrice;
    }
    public static Double VerifyCartSubtotal(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify Cart Sub total");
        int noUnitPrice = UnitPrice(driver).size();
        Double CartSubtotal = 0.00;
        ExpectedLable("Verify No of products available in the shopping cart");
        if(noUnitPrice>0) {
            ActualLable("Successfully Verified No of products, There are ' "+noUnitPrice+ " ' available in cart" ,"Pass");
            for (int i = 1; i <= noUnitPrice; i++) {
                Thread.sleep(2000);
                ExpectedLable("Get the Unit Price for the product no: "+i);
                String UnitCastSt = UnitPrice(driver).get(i-1).getText();
                //Double UnitCast = Double.parseDouble(UnitCastSt);
                String s = UnitCastSt.replaceAll("[€,$,£,₹]","");
                Double UnitCast = Double.parseDouble(s);
                ActualLable("Unit price for the product no: "+i+" is "+UnitCast ,"Pass");
                String ValueInBlank = QuantityBlank(driver).get(i-1).getAttribute("value");
                int QuantityperBlank = Integer.parseInt(ValueInBlank);

                Double ExpectedExtendedPrice = UnitCast*QuantityperBlank;
                ExpectedLable("Get the Extended Price for the product no: "+1);
                //String ActualExtendedPriceStrinng = ExtendedPrice(driver).get(noUnitPrice-1).getText();
                String s1 = ExtendedPrice(driver).get(i-1).getText().replaceAll("[€$£₹,]","");
                Double ActualExtendedPrice = Double.parseDouble(s1);
                ActualLable("Extended price for the product no: "+i+" is "+ActualExtendedPrice ,"Pass");
                Assert.assertEquals(ExpectedExtendedPrice, ActualExtendedPrice);
                CartSubtotal = CartSubtotal+ActualExtendedPrice;
            }
            ExpectedLable("Get the Actual Cart Sub total : ");

            ActualLable("Total actual Cart Sub total is "+CartSubtotal ,"Pass");
        }
        else{
            ActualLable("There are no items available in Shoping cart" ,"Pass");
        }
        ExpectedLable("Open Cart Summery Pannel in Shopping cart page");
        driver.findElement(CartSummeryPannel).click();
        ActualLable("Cart Summery Pannel Opened successfully" ,"Pass");
        Thread.sleep(1000);
        String ActualCartSubtotalString = driver.findElement(ActualCartSubtotalXpath).getText();
        String s2 = ActualCartSubtotalString.replaceAll("[€$£₹,]","");
        Double ActualCartSubtotal = Double.parseDouble(s2);
        ExpectedLable("Verify Actual Cart sub total value is same as expected subtitle");
        double ExpectedCartSubtotal = Math.round( CartSubtotal * 100.0 ) / 100.0;
        Assert.assertEquals(ActualCartSubtotal, ExpectedCartSubtotal);
        ActualLable("Cart Subtotal is Verified successfully and Actual Cart sub total value is same as expected subtitle" ,"Pass");

        return ActualCartSubtotal;
    }

    public static Double VerifyCartGrandTotal(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify Cart Grand total");
        Double CartSubtotal = VerifyCartSubtotal(driver);
        ExpectedLable("Get Shipping charges from Shopping cart");
        Double ExpectedShippingCharges1 = CartSubtotal*10/100;
        double ExpectedShippingCharges = Math.round( ExpectedShippingCharges1 * 100.0 ) / 100.0;
        String ActualShippingChargesString =  driver.findElement(ActualShippingChargesXpath).getText();
        ActualLable("Shipping Charges are "+ActualShippingChargesString  ,"Pass");
        String s1 = ActualShippingChargesString.replaceAll("[€$£₹,]","");
        Double ActualShippingCharges = Double.parseDouble(s1);
        ExpectedLable("Verify Shipping charges in shopping cart");
        Assert.assertEquals(ActualShippingCharges, ExpectedShippingCharges);
        ActualLable("Shipping Charges verified successfully" ,"Pass");

        ExpectedLable("Get Sales Vat charges from Shopping cart");
        Double ExpectedSalesVatCharges1 = (CartSubtotal*19/100)+(ActualShippingCharges*19/100);
        double ExpectedSalesVatCharges = Math.round( ExpectedSalesVatCharges1 * 100.0 ) / 100.0;
        String ActualSalesVatChargesString =  driver.findElement(ActualSalesVatXpath).getText();
        ActualLable("Sales Vat Charges are "+ActualSalesVatChargesString  ,"Pass");
        String s2 = ActualSalesVatChargesString.replaceAll("[€$£₹,]","");
        Double ActualSalesVatCharges = Double.parseDouble(s2);
        ExpectedLable("Verify Sales Vat charges in shopping cart");
        Assert.assertEquals(ActualSalesVatCharges, ExpectedSalesVatCharges);
        ActualLable("Sales Vat Charges verified successfully" ,"Pass");

        ExpectedLable("Get Cart Grand Total from Shopping cart");
        Double ExpectedCartGrandTotal1 = CartSubtotal+ExpectedShippingCharges+ExpectedSalesVatCharges;
        double ExpectedCartGrandTotal = Math.round( ExpectedCartGrandTotal1 * 100.0 ) / 100.0;
        String ActualCartGrandTotalString =  driver.findElement(ActualCartGrandTotalXpath).getText();
        ActualLable("Cart Grand Total is "+ActualShippingChargesString  ,"Pass");
        String s3 = ActualCartGrandTotalString.replaceAll("[€$£₹,]","");
        Double ActualCartGrandTotal = Double.parseDouble(s3);
        ExpectedLable("Verify Cart Grand Total in shopping cart");
        Assert.assertEquals(ActualCartGrandTotal, ExpectedCartGrandTotal);
        ActualLable("Cart Grand Total verified successfully" ,"Pass");
        ExpectedLable("Get expected Cart Grand Total");
        ActualLable("expected Cart Grand Total is "+ ExpectedCartGrandTotal,"Pass");
        ExpectedLable("Get Actual Cart Grand Total");
        ActualLable("Actual Cart Grand Total is "+ ActualCartGrandTotal,"Pass");
        return ActualCartGrandTotal;
    }

    public static void ClickonProceedtoCheckout(WebDriver driver) throws IOException, WriteException, InterruptedException {
        String ProceedtoCheckoutText = driver.findElement(ProceedtocheckoutXpath).getText();
        ExpectedLable("Check 'Proceed to checkout' button is displaying or not");
        Assert.assertEquals(ProceedtoCheckoutText, "Proceed to checkout");
        if (driver.findElement(ProceedtocheckoutXpath).isDisplayed()) {
            ActualLable("'Proceed to checkout' button verified successfully", "Pass");
            log.info("Assert is verified for 'Proceed to checkout' button");
            Thread.sleep(1000);
            ExpectedLable("Click on 'Proceed to checkout' button ");
            driver.findElement(ProceedtocheckoutXpath).click();
            ActualLable("'Proceed to checkout' button clicked successfully", "Pass");
        }
        else{
            ActualLable("'Proceed to checkout' button is not available", "Fail");
        }
    }

}
