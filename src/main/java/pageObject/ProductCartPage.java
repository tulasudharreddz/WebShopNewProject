package pageObject;

import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 22-Nov-16.
 */
public class ProductCartPage {
    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Product Cart page");
    static protected WebDriver driver;
    static protected ObjectRepository obje = new ObjectRepository();


    //Page Elements
    static private By ProductName = By.xpath("//div[@class='product-name']");
    static private By MfrPart = By.xpath("//span[contains(text(),'Mfr Part#')]");
    static private By MfrPartNumber = By.xpath("//span[contains(text(),'Mfr Part#')]/parent::div");
    static private By PRODUCTName = By.xpath("//div[@class='product-name']");
    static private By Manufacturer = By.xpath("//span[contains(text(),'Manufacturer:')]");
    static private By Discription = By.xpath("//ul[@class='key-selling-points']");
    static private By UnitPrice = By.xpath("//p[@class='product-price']");
    static private By Quantity = By.xpath("//span[contains(text(),'Qty ')]");
    static private By AddToCart = By.xpath("//button[contains(text(),'Add to Cart')]");
    static private By AddToFavorites = By.xpath("//button [contains(text(),'Add to Favorites')]");
    static private By AvailabilityCheck = By.xpath("//is-availability");
    static private By Specifications = By.xpath("//i[@class='ion-ios-list']/parent::a");
    static private By Feature = By.xpath("//i[@class='ion-ios-gear']/parent::a");
    static private By OverView = By.xpath("//i[@class='ion-ios-world']/parent::a");
    static private By ActiveOverView = By.xpath("//i[@class='ion-ios-world']/parent::a/parent::li[@class='active']");
    static private By ConfirmationAlert = By.xpath("//div[@class='is-notify-msg']");
    static private By AvailabilityBlock = By.xpath("//span[@class='product-availability-text text-right']");
    static private By LearnMore = By.xpath("//button[contains(text(),'Learn More')]");




    public static String  AssertVerifyForProduct(WebDriver driver) throws IOException, WriteException, InterruptedException {
        obje.repository(driver);
        Thread.sleep(2000);
        String SelectedProductName = driver.findElement(ProductName).getText();
        log.info("Product name on Cart page is : "+ SelectedProductName);
        return SelectedProductName;
    }
    public static void  ContentVerifyForPCart(WebDriver driver) throws InterruptedException, IOException, WriteException {

        Thread.sleep(2000);
        ArrayList<String> AssertName=new ArrayList<String>();//creating arraylist
        AssertName.add("Mfr Part#");//adding object in arraylist
        AssertName.add("Manufacturer name");
        AssertName.add("Product Description");
        AssertName.add("UnitPrice");
        AssertName.add("Quantity");
        AssertName.add("Add To Cart");
        AssertName.add("Add To Favorites");
        AssertName.add("Availability of product");
        AssertName.add("Specifications");
        AssertName.add("Feature");
        AssertName.add("OverView");

        ArrayList<org.openqa.selenium.By> AssertXpath=new ArrayList<org.openqa.selenium.By>();
        AssertXpath.add(MfrPart);
        AssertXpath.add(Manufacturer);
        AssertXpath.add(Discription);
        AssertXpath.add(UnitPrice);
        AssertXpath.add(Quantity);
        AssertXpath.add(AddToCart);
        AssertXpath.add(AddToFavorites);
        AssertXpath.add(AvailabilityCheck);
        AssertXpath.add(Specifications);
        AssertXpath.add(Feature);
        AssertXpath.add(OverView);

        for(int i=0; i<=10; i++) {
            ExpectedLable("' "+AssertName.get(i)+" ' Should available on Product cart page");
            if(driver.findElements(AssertXpath.get(i)).size()>0){
                ActualLable("Content verified successfully for ' "+AssertName.get(i)+" '","Pass");
                if(driver.findElements(AssertXpath.get(10)).size()>0) {
                    ExpectedLable("' Over view ' Should expand and should show product details");
                    if(driver.findElement(ActiveOverView).isDisplayed()) {
                        ActualLable("Verification successful and Over view section is active default", "Pass");
                    }
                    else{ ActualLable("Verification Failed and Over view section is not active default", "Fail"); }
                }
            }
            else{ ActualLable("Assert is not verified for ' "+AssertName.get(i)+" '","Pass"); }
        }
    }
    public static void  AddToCartFunctionalityPCart(WebDriver driver) throws InterruptedException, IOException, WriteException {

        ExpectedLable("' Add To Cart ' Button Should available on Product cart page");
        if(driver.findElements(AddToCart).size()>0){
            ActualLable("Assert verified successfully for ' Add To Cart '","Pass");
            if(driver.findElement(AddToCart).isDisplayed()) {
                ExpectedLable("Click on  Add To Cart Button");
                driver.findElement(AddToCart).click();
                ActualLable("Successful clicked on Add To Cart Button", "Pass");
            }
        }
        else{ ActualLable("Verification failed for ' Add To Cart ' Button","Fail"); }
    }

    public static String  AddToFavoritesFunctionalityPCart(WebDriver driver) throws InterruptedException, IOException, WriteException {
        String MFRPartNumberText = null;
        ExpectedLable("' Add To Favorites ' Button Should available on Product cart page");
        if(driver.findElements(AddToFavorites).size()>0){
            ActualLable("Assert verified successfully for ' Add To Favorites '","Pass");
            if(driver.findElement(AddToFavorites).isDisplayed()){
                ExpectedLable("Click on  Add To Favorites Button");
                driver.findElement(AddToFavorites).click();
                MFRPartNumberText= driver.findElement(PRODUCTName).getText();
                ActualLable("Successful clicked on Add To Favorites Button", "Pass");
                ExpectedLable("Confirmation Popup Should show ");
                Thread.sleep(2000);
                if(driver.findElements(ConfirmationAlert).size()>0) {
                    String AlertText = driver.findElement(ConfirmationAlert).getText();
                    if (AlertText.contentEquals("Product has been added to your favorites")) {
                        ActualLable("Successful Message shown ", "Pass");
                    } else if (AlertText.contentEquals("This product already exists in your favorites")) {
                        ActualLable("Warning message shown that the product is already added ", "Pass");
                    }   else{ ActualLable("Confirmation message is displayed with error message", "Fail");  }
                } else{   ActualLable("Confirmation message is not displayed", "Fail");  }
            }
        }  else{ ActualLable("Verification failed for ' Add To Favorites ' Button","Fail"); }
        return MFRPartNumberText;
    }
    public static String  AddToFavoritesWithSameProductPCart(WebDriver driver) throws InterruptedException, IOException, WriteException {
        String MFRPartNumberText = null;
        StepLable("Validate the Add to Favorites functionality; which is already added to Favorites. ");
        ExpectedLable("' Add To Favorites ' Button Should available on Product cart page");
        if(driver.findElements(AddToFavorites).size()>0){
            ActualLable("Assert verified successfully for ' Add To Favorites '","Pass");
            if(driver.findElement(AddToFavorites).isDisplayed()) {
                ExpectedLable("Click on  Add To Favorites Button");
                driver.findElement(AddToFavorites).click();
                ActualLable("Successful clicked on Add To Favorites Button", "Pass");
                Thread.sleep(1000);
                ExpectedLable("Confirmation Popup Should show ");
                String AlertText = driver.findElement(ConfirmationAlert).getText();
                if(AlertText.contentEquals("Product has been added to your favorites")){
                    ActualLable("Successful Message shown ", "Pass");
                }else if(AlertText.contentEquals("This product already exists in your favorites")){
                    ActualLable("Warning message shown that the product is already added ", "Pass");
                }
                else{
                    ActualLable("Confirmation message is not displayed", "Fail");
                }
                Thread.sleep(10000);
                ExpectedLable("Click Again on  Add To Favorites Button");
                driver.findElement(AddToFavorites).click();
                ActualLable("Successful clicked on Add To Favorites Button for second time", "Pass");
                Thread.sleep(2000);
                ExpectedLable("Confirmation Popup Should show");
                String AlertText1 = driver.findElement(ConfirmationAlert).getText();
                System.out.println(AlertText1);
                if(AlertText1.contentEquals("Product has been added to your favorites")){
                    ActualLable("Successful Message shown ", "Pass");
                }else if(AlertText1.contentEquals("This product already exists in your favorites")){
                    ActualLable("Warning message shown that the product is already added ", "Pass");
                }
                else{
                    ActualLable("Confirmation message is not displayed", "Fail");
                }
            }
        }
        else{ ActualLable("Verification failed for ' Add To Favorites ' Button","Fail"); }
        return MFRPartNumberText;
    }
    public static ArrayList<String> SelectProductFromPCartPage(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Adding Product From Product Cart page");
        HomePage.SearchProductFromHomePage(driver);
        Thread.sleep(2000);
        ExpectedLable("Get the Product name for searched Product");
        String NameOfItem = ProductSearchPage.ProductNameSearchPage(driver).get(0).getText();
        ActualLable("Successfully Stored the product Name, i.e : "+NameOfItem,"Pass");
        ExpectedLable("Get the Product Part Number for searched Product");
        String PartNumbertotal = ProductSearchPage.PartNumber(driver).get(0).getText();
        String PartNumber= FavoriesPage.TrimMfrNumber(driver,PartNumbertotal);
        ActualLable("Successfully Stored the product Part Number, i.e : "+PartNumber,"Pass");
        ExpectedLable("Get the Price for searched Product");
        String ProductPriceValue = driver.findElements(UnitPrice).get(0).getText();
        ActualLable("Successfully Stored the product Price, i.e : "+ProductPriceValue,"Pass");
        ExpectedLable("Get the Product Availability Status for searched Product");
        String AvailabilityStatus = driver.findElements(AvailabilityBlock).get(0).getText();
        ActualLable("Successfully Stored the product Availability Status, i.e : "+AvailabilityStatus,"Pass");
        ExpectedLable("Get the Product Quantity for searched Product");
        String Quant ="5";
        ActualLable("Successfully Stored the product Quantity, i.e : "+Quant,"Pass");
        ArrayList<String> AssertName=new ArrayList<String>();
        AssertName.add(NameOfItem);
        AssertName.add(PartNumber);
        AssertName.add(ProductPriceValue);
        AssertName.add(AvailabilityStatus);
        AssertName.add(Quant);
        ExpectedLable("Click on Learn more button for particular Item ");
        Thread.sleep(1000);
        driver.findElement(LearnMore).click();
        Thread.sleep(2000);
        ActualLable("Successfully clicked on Learn more button for particular Item ","Pass");

        String NameOfItem1 = driver.findElements(ProductName).get(0).getText();
        String PartNumbertotal1 = driver.findElements(MfrPartNumber).get(0).getText();
        String PartNumber1= FavoriesPage.TrimMfrNumber(driver,PartNumbertotal1);
        String ProductPriceValue1 = driver.findElements(UnitPrice).get(0).getText();
        String AvailabilityStatus1 = driver.findElements(AvailabilityBlock).get(0).getText();
        driver.findElement(Quantity).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@data-index='4']")).click();
        ArrayList<String> AssertName1=new ArrayList<String>();
        AssertName1.add(NameOfItem1);
        AssertName1.add(PartNumber1);
        AssertName1.add(ProductPriceValue1);
        AssertName1.add(AvailabilityStatus1);
        AssertName1.add(Quant);
        ArrayList<String> AssertNames=new ArrayList<String>();
        AssertNames.add("Name Of Item");
        AssertNames.add("MFR Part Number");
        AssertNames.add("Product Price");
        AssertNames.add("Availability Status");
        AssertNames.add("Quantity Of the Product");
        StepLable("Verify Product Details on Product Cart page are same as on Product Search Page");
        boolean StatusOfProduct = true;
        for(int i=0;i<=4;i++){
            ExpectedLable("Verify ' "+AssertNames.get(i)+" ' on Product Cart page is same on Product Search Page or not..?");
            if(AssertName1.get(i).contentEquals(AssertName.get(i))){
                ActualLable("Successfully Verified, ' "+AssertNames.get(i)+" ' on Product Cart page is same as on Product Search Page.","Pass");
            }
            else{ StatusOfProduct =false; ActualLable("Failed to Verify, ' "+AssertNames.get(i)+" ' on Product Cart page is not same on Product Search Page.","Fail");  }
        }
        Thread.sleep(1000);
        ExpectedLable("Click on ' Add To Cart ' Button if All the details Are matched on Both the pages..? ");
        if(StatusOfProduct ==true) {
            driver.findElement(AddToCart).click();
            ActualLable("Successfully Clicked on ' Add To Cart ',  All the details Are matched on Both the pages","Pass");
        }
        else{  ActualLable("Failed to Verify, Details Are not matched on Both the pages","Fail");     }
        return AssertName;
    }
}
