package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
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
    static private By OpenCartSummeryPannel = By.xpath("//is-tabsetpanel[@header='Cart Summary']/div/div[@class='panel-collapse collapse in']");
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
    static private By ProceedtocheckoutXpath = By.xpath("//button[contains(text(),'Checkout')]");
    static private By ContinueShoppingXpath = By.xpath("//button[contains(text(),'Continue Shopping')]");
    static private By NoOfCartProducts = By.xpath("//div[@class='product-row']");
    static private By ImageXpath = By.xpath("//img[@class='img-responsive']");
    static private By InventoryIcon = By.xpath("//is-availability[@class='hidden-xs']");
    static private By ProductName = By.xpath("//p[@class='product-name clickable']");
    static private By MfrPartText = By.xpath("//span[@class='field-name'][contains(text(),'Mfr Part#')]/parent::div");
    static private By CartSummeryLabel = By.xpath("//div[@class='row totals']/div/label");


    public static void VerifyShoppingCartPageAsserts(WebDriver driver) throws InterruptedException, IOException, WriteException {
        Thread.sleep(2000);

        ExpectedLable("Verify ' Item Details ' section available in the shopping cart or not ?");
        if (driver.findElements(ItemDetails).size() > 0) {
            ActualLable("Assert verification is successful for ' Item Details ' section", "Pass");
        } else {
            ActualLable("Failed to verify assert for ' Item Details ' section", "Fail");
        }
        ExpectedLable("Verify ' Cart SummeryPannel ' section available in the shopping cart or not ?");

        if(driver.findElements(CartSummeryPannel).size()>0){
            ActualLable("Assert verification is successful for ' Cart  SummeryPannel ' section" ,"Pass");
        }
        else{
            ActualLable("Failed to verify assert for ' Cart SummeryPannel ' section" ,"Fail");
        }
        ExpectedLable("Verify ' Proceed to checkout ' Button available in the shopping cart or not ?");
        if(driver.findElements(ProceedtocheckoutXpath).size()>0){
            ActualLable("Assert verification is successful for ' Proceed to checkout ' button" ,"Pass");
        }
        else{
            ActualLable("Failed to verify assert for ' Proceed to checkout ' Button" ,"Fail");
        }
        ExpectedLable("Verify ' Continue Shopping ' Button available in the shopping cart or not ?");
        if(driver.findElements(ContinueShoppingXpath).size()>0){
            ActualLable("Assert verification is successful for ' Continue Shopping ' Button" ,"Pass");
        }
        else{
            ActualLable("Failed to verify assert for ' Continue Shopping ' button " ,"Fail");
        }

    }
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
                Thread.sleep(2000);
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
    public static void VerifyDeleteExistItem(WebDriver driver) throws IOException, WriteException, InterruptedException {

        ExpectedLable("Verify Delete Shopping cart items functionality");
        if (driver.findElements(DeleteItem).size() > 0) {
            String partNumber = driver.findElements(MfrPartText).get(0).getText();
            System.out.println(partNumber);
            driver.findElements(DeleteItem).get(0).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
            Thread.sleep(3000);
            if(driver.findElements(By.xpath("//*[contains(text(),'"+partNumber+"')]")).size()>0){
                ActualLable("Product is not deleted ", "Fail");
            }else{ ActualLable(" product deleted successfully ", "Pass");}
        } else {
            ActualLable("Delete button is not available", "Fail");
        }

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
        if(driver.findElements(OpenCartSummeryPannel).size()>0) {
            ActualLable("Cart Summery Panel already Opened" ,"Pass");
        }else{
            driver.findElement(CartSummeryPannel).click();
            ActualLable("Cart Summery Pannel Opened successfully" ,"Pass");
        }
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
    public static void VerifyForCartSummeryPannelOpenedOrNot(WebDriver driver) throws IOException, WriteException {
        ExpectedLable("Open Cart Summery Panel in Shopping cart page");
        if(driver.findElements(OpenCartSummeryPannel).size()>0) {
            ActualLable("Cart Summery Panel already Opened" ,"Pass");
        }else{
            driver.findElement(CartSummeryPannel).click();
            ActualLable("Cart Summery Panel Opened successfully" ,"Pass");
        }
    }
    public static void ContentVerifyForCartSummery(WebDriver driver) throws IOException, WriteException {

        ArrayList<String> al=new ArrayList<String>();//creating arraylist
        al.add("Cart Subtotal");
        al.add("Shipping Charges");
        al.add("Sales VAT");
        al.add("Cart Grand Total");
        for(int j=0;j<=3;j++) {
            ExpectedLable("Verify assert for label '"+al.get(j)+"' in shopping cart");
            for (int i = 0; i <= driver.findElements(CartSummeryLabel).size()- 1; i++) {
                String LabelName = driver.findElements(CartSummeryLabel).get(i).getText();
                System.out.println(LabelName);
                if(LabelName.contentEquals(al.get(j))){
                    ActualLable("assert verified successfully for label " +al.get(j) ,"Pass");
                    break;
                }
            }
        }
    }
    public static Double VerifyCartGrandTotal(WebDriver driver) throws IOException, WriteException, InterruptedException {

        Double CartSubtotal = VerifyCartSubtotal(driver);
        StepLable("Verify Cart Grand total");
        ExpectedLable("Get Shipping charges from Shopping cart");
        //Double ExpectedShippingCharges1 = CartSubtotal*10/100;
        //double ExpectedShippingCharges = Math.round( ExpectedShippingCharges1 * 100.0 ) / 100.0;
        double ExpectedShippingCharges = 6.50;
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
        ActualLable("Cart Grand Total is "+ActualCartGrandTotalString  ,"Pass");
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
    public static void ClickonCheckout(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Check 'Proceed to checkout' button is displaying or not");
        if (driver.findElements(ProceedtocheckoutXpath).size()>0) {
            String ProceedtoCheckoutText = driver.findElement(ProceedtocheckoutXpath).getText();
            Assert.assertEquals(ProceedtoCheckoutText, "Checkout");
            ActualLable("'Checkout' button verified successfully", "Pass");
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
    public static void AssertVerifyForItemDetails(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable(" Content verification for Item details section ");
        ExpectedLable("Verify Assert for ' Item Details ' panel title ");
        String panelTitle=driver.findElement(ItemDetails).getText();
        ActualLable("Assert verified successfully and panel title is "+panelTitle, "Pass");
        ExpectedLable("Verify ' Item Details ' panel open by default when page is loaded or not ? ");
        if(driver.findElements(OpenItmDetailsPannel).size()>0) {
            ActualLable("Verification is successful, ' Item Details ' panel is opened by default", "Pass");
        }
        else{
            ActualLable("Verification failed for default open pannel for ' Item Details ' section", "Fail");
        }

        long NoOfProductAddedToCart = driver.findElements(NoOfCartProducts).size();
        ExpectedLable("Verify ' Image ' available for each product or not ?");
        if (driver.findElements(ImageXpath).size() > 0) {
            if(NoOfProductAddedToCart==driver.findElements(ImageXpath).size()){
                ActualLable("Assert verification is successful for product ' Image ' ", "Pass");
            }
            else {
                ActualLable("Number of images are not equal to no of product added in the cart", "Fail");
            }

        } else {
            ActualLable("Failed to verify assert for product ' Image ' in ' Item Details ' section", "Fail");
        }

        ExpectedLable("Verify ' DeleteItem ' available for each product or not ?");
        if (driver.findElements(DeleteItem).size() > 0) {
            if(NoOfProductAddedToCart==driver.findElements(DeleteItem).size()){
                ActualLable("Assert verification is successful for 'Delete Item' button ", "Pass");
            }
            else {
                ActualLable("Number of Delete buttons are not equal to no of product added in the cart", "Fail");
            }

        } else {
            ActualLable("Failed to verify assert for 'Delete buttons' in ' Item Details ' section", "Fail");
        }

        ExpectedLable("Verify ' Inventory  Icon ' available for each product or not ?");
        if (driver.findElements(InventoryIcon).size() > 0) {
            if(NoOfProductAddedToCart==driver.findElements(InventoryIcon).size()){
                ActualLable("Assert verification is successful for 'Inventory Icon' ", "Pass");
            }
            else {
                ActualLable("Number of Inventory Icons are not equal to no of product added in the cart", "Fail");
            }

        } else {
            ActualLable("Failed to verify assert for 'Inventory Icon' in ' Item Details ' section", "Fail");
        }
    }
    public static void VerifyEditQuantityfunctionality(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Verify Assert for ' Quantity blank ' for every product");
        long NoOfProductAddedToCart = driver.findElements(NoOfCartProducts).size();
        if (driver.findElements(Quantity).size() > 0) {
            if(NoOfProductAddedToCart==driver.findElements(Quantity).size()){
                ActualLable("Assert verification is successful for product ' Quantity blank ' ", "Pass");
            }
            else {ActualLable("Number of images are not equal to no of product added in the cart", "Fail"); }
        } else { ActualLable("Failed to verify assert for product ' Quantity blank ' in ' Item Details ' section", "Fail");}

        ExpectedLable("Verify Quantity change functionality");
        driver.findElements(Quantity).get(0).clear();
        driver.findElements(Quantity).get(0).sendKeys("12");
        String QuantityText = driver.findElements(Quantity).get(0).getAttribute("isnumericbox");
        System.out.println("text is "+QuantityText);
        int QuantityInt = Integer.parseInt(QuantityText);
        if(QuantityInt==12){
            ActualLable("Quantity changed successfully", "Pass");
        }else{ ActualLable("Edit Quantity functionality failed ", "Fail");}
    }
    public static void VerifyDisplayOfLineItem(WebDriver driver) throws IOException, WriteException, InterruptedException {

        ExpectedLable("Verify line items are arranged as different items or not");
        long NoOfProductAddedToCart = driver.findElements(NoOfCartProducts).size();
        long NoOfProductNames = driver.findElements(ProductName).size();
        if (NoOfProductNames==NoOfProductAddedToCart) {
            ActualLable("No of Products in cart is same as no of products name, every product has different blocks for pproducts", "Pass");

        } else { ActualLable("Line items are combined more than one product in one block", "Fail");}

    }
    public static void VerifyLogisticCharge(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify 'Shipping Charges' in shopping cart");
        HomePage.ClickonShoppingCart(driver);
        ExpectedLable("Open Cart Summery Panel in Shopping cart page");
        if(driver.findElements(OpenCartSummeryPannel).size()>0) {
            ActualLable("Cart Summery Panel already Opened" ,"Pass");
        }else{
            driver.findElement(CartSummeryPannel).click();
            ActualLable("Cart Summery Panel Opened successfully" ,"Pass");
        }

        ExpectedLable("Verify assert for 'Shipping Charges' in shopping cart");
        for (int i = 0; i <= driver.findElements(CartSummeryLabel).size()- 1; i++) {
            String LabelName = driver.findElements(CartSummeryLabel).get(i).getText();
            if(LabelName.contentEquals("Shipping Charges")){
                ActualLable("assert verified successfully for label "  ,"Pass");
                ExpectedLable("Get Shipping charges from Shopping cart");
                double ExpectedShippingCharges = 6.50;
                String ActualShippingChargesString =  driver.findElement(ActualShippingChargesXpath).getText();
                ActualLable("Shipping Charges are "+ActualShippingChargesString  ,"Pass");
                String s1 = ActualShippingChargesString.replaceAll("[€$£₹,]","");
                Double ActualShippingCharges = Double.parseDouble(s1);
                ExpectedLable("Verify Shipping charges in shopping cart");
                Assert.assertEquals(ActualShippingCharges, ExpectedShippingCharges);
                if(ActualShippingCharges.equals(ExpectedShippingCharges)) {
                    ActualLable("Shipping Charges verified successfully", "Pass");
                }
                else{
                    ActualLable("Shipping charges are not equals to 6.50", "Fail");
                }
                break;
            }
        }

    }
    public static void VerifySalesTax(WebDriver driver) throws IOException, WriteException, InterruptedException{
        StepLable("Verify 'Sales vat Charges' in shopping cart");
        HomePage.ClickonShoppingCart(driver);
        ShoppingCart.VerifyForCartSummeryPannelOpenedOrNot(driver);
        ExpectedLable("Verify assert for 'Sales VAT' available in shopping cart");
        for (int i = 0; i <= driver.findElements(CartSummeryLabel).size()- 1; i++) {
            String LabelName = driver.findElements(CartSummeryLabel).get(i).getText();
            if(LabelName.contentEquals("Sales VAT")){
                ActualLable("assert verified successfully for label "  ,"Pass");
                ExpectedLable("Get Cart Subtotal from Shopping cart");
                String ActualCartSubTotalString =  driver.findElement(ActualCartSubtotalXpath).getText();
                String CartSubTotalSt = ActualCartSubTotalString.replaceAll("[€$£₹,]","");
                Double ActualCartSubTotal = Double.parseDouble(CartSubTotalSt);
                ActualLable("Actual Cart subtotal is : "+ActualCartSubTotalString  ,"Pass");
                ExpectedLable("Get Shipping chargers from Shopping cart");
                String ActualShippingChargesString =  driver.findElement(ActualCartSubtotalXpath).getText();
                String ShippingChargesSt = ActualShippingChargesString.replaceAll("[€$£₹,]","");
                Double ActualShippingCharges = Double.parseDouble(ShippingChargesSt);
                ActualLable("Actual Shipping chargers are : "+ActualShippingChargesString  ,"Pass");
                ExpectedLable("Calculate Expected Sales Vat value");
                double ExpectedSalesVatCharges1 =ExpectedSalesTax(driver, i);
                double ExpectedSalesVatCharges = Math.round( ExpectedSalesVatCharges1 * 100.0 ) / 100.0;
                        ActualLable("Expected Sales VAT Charges are :  "+ExpectedSalesVatCharges  ,"Pass");
                ExpectedLable("Get Sales VAT from Shopping cart");
                String ActualSalesVATString =  driver.findElement(ActualSalesVatXpath).getText();
                ActualLable("Sales VAT Charges are "+ActualSalesVATString  ,"Pass");
                String s1 = ActualSalesVATString.replaceAll("[€$£₹,]","");
                Double ActualSalesVATCharges = Double.parseDouble(s1);
                ExpectedLable("Verify Sales VAT charges in shopping cart");
                //Assert.assertEquals(ActualSalesVATCharges, ExpectedSalesVatCharges);
                if(ActualSalesVATCharges.equals(ExpectedSalesVatCharges)) {
                    ActualLable("Sales VAT Charges verified successfully", "Pass");
                }
                else{
                    ActualLable("Sales VAT charges are not same as expected", "Fail");
                }
                break;
            }
        }
    }
    public static double ExpectedSalesTax(WebDriver driver, int NoOfEle) throws IOException, WriteException, InterruptedException{
        double ExpectedSalesVat = 0;
        for (int i = 0; i <= NoOfEle-1; i++) {
            String LabelName = driver.findElements(By.xpath("//div[@class='row totals']/div[2]")).get(i).getText();
            String CartSubTotalSt = LabelName.replaceAll("[€$£₹,]","");
            double ActualCartSubTotal = Double.parseDouble(CartSubTotalSt);
            double ActualCartSubTotal1=ActualCartSubTotal*19/100;
            ExpectedSalesVat = ExpectedSalesVat+ActualCartSubTotal1;
        }
        return ExpectedSalesVat;
    }
    public static void VerifyContinueShoppingButtonFunctionality(WebDriver driver) throws IOException, WriteException, InterruptedException{
        StepLable("Verify Continue Shopping Button functionality");
        HomePage.ClickonShoppingCart(driver);
        ExpectedLable("Verify That Continue Shopping Button is available or not ?");
        if(driver.findElements(ContinueShoppingXpath).size()>0){
            ActualLable("Continue Shopping Button Button is available on Shopping cart", "Pass");
            ExpectedLable("now click on 'Continue Shopping Button', then check that Page is navigate to Home page or not ?");
            driver.findElement(ContinueShoppingXpath).click();
            ActualLable("successfully clicked on Continue Shopping Button", "Pass");
            HomePage.VerifyHomePageAssert(driver);

        }
        else{
            ActualLable("Continue Shopping Button is not Available", "Pass");
            ProductSearchPage.AddToShoppingCart(driver);
            VerifyContinueShoppingButtonFunctionality(driver);
        }
    }
    public static void VerifyCheckOutButtonFunctionality(WebDriver driver) throws IOException, WriteException, InterruptedException{
        StepLable("Verify Check out Button functionality");
        HomePage.ClickonShoppingCart(driver);
        ExpectedLable("Verify That 'Checkout' Button is available or not ?");
        if(driver.findElements(ProceedtocheckoutXpath).size()>0){
            ActualLable("'Checkout' Button is available on Shopping cart", "Pass");
            CheckOutPage.VerifyCheckoutPageAssert(driver);
        }
        else{
            ActualLable("Checkout button is not Available", "Pass");
            ProductSearchPage.AddToShoppingCart(driver);
            VerifyCheckOutButtonFunctionality(driver);
        }
    }

}
