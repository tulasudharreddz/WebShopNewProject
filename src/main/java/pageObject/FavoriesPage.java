package pageObject;

import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 03-Jan-17.
 */
public class FavoriesPage {


    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Product Cart page");
    static protected WebDriver driver;
    static protected ObjectRepository obje = new ObjectRepository();

    //Page Elements
    static private By DeleteItem = By.xpath("//a[@class='anchor-delete']");
    static private By PageHeader = By.xpath("//h2");
    static private By ProductName = By.xpath("//p[@class='product-name clickable']/a");
    static private By MFRPart = By.xpath("//span[contains(text(),'Mfr Part')]");
    static private By ProductPrice = By.xpath("//p[@class='product-price']");
    static private By NoOfProducts = By.xpath("//div[@class='product-row']");
    static private By Manufacturer = By.xpath("//span[contains(text(),'Manufacturer')]");
    static private By Image = By.xpath("//img[@class='img-responsive']");
    static private By LearnMore = By.xpath("//button[contains(text(),'Learn More')]");
    static private By AddToCart = By.xpath("//button[contains(text(),'Add to Cart')]");
    static private By CategoryName = By.xpath("//span[contains(text(),'Category:')]");
    static private By AvailabilityBlock = By.xpath("//span[@class='product-availability-text text-right']");
    static private By PartNumber = By.xpath("//div[@class='m-t-b-15']/div[1]");


    public static void FavoritesPageVerify(WebDriver driver) throws InterruptedException, IOException, WriteException {
        Thread.sleep(1000);
        ExpectedLable("Verify Assert for ' Favorites ' Page, Page Header Text Should be ' Favorites '");
        String PageHeaderText = driver.findElement(PageHeader).getText();
        if(PageHeaderText.contentEquals("Favorites")){
            ActualLable("Successfully verified Assert for ' Favorites ' Page" ,"Pass");
        }
        else{
            ActualLable("Failed to verify Assert for ' Favorites ' Page ","Fail");
        }
    }

    public static void FavoritesPageContentVerify(WebDriver driver) throws InterruptedException, IOException, WriteException {
        Thread.sleep(2000);
        StepLable("Content verification on ' Favorites ' page");
        //Verify no of products in result page
        ExpectedLable("verify No of products on ' Favorites ' page");
        int NoProducts = driver.findElements(NoOfProducts).size();
        ActualLable("Successfully verified no of products available on ' Favorites ' page is " + NoProducts, "Pass");

        ArrayList<String> AssertName=new ArrayList<String>();//creating arraylist
        AssertName.add("Image");
        AssertName.add("Product Name");//adding object in arraylist
        AssertName.add("ProductPrice");
        AssertName.add("AddTo Cart");
        AssertName.add("Learn More");
        AssertName.add("Delete Button");
        AssertName.add("Availability of product");

        ArrayList<org.openqa.selenium.By> AssertXpath=new ArrayList<org.openqa.selenium.By>();
        AssertXpath.add(Image);
        AssertXpath.add(ProductName);
        AssertXpath.add(ProductPrice);
        AssertXpath.add(AddToCart);
        AssertXpath.add(LearnMore);
        AssertXpath.add(DeleteItem);
        AssertXpath.add(AvailabilityBlock);


        for(int i=0; i<=6; i++) {
            ExpectedLable("verify ' "+AssertName.get(i)+" ' is displaying for every Product on ' Favorites ' page");
            if (driver.findElement(AssertXpath.get(i)).isDisplayed()) {
                ActualLable(" ' "+AssertName.get(i)+" ' for each product is displaying on ' Favorites ' page ", "Pass");
                int NOProductName = driver.findElements(AssertXpath.get(i)).size();
                ExpectedLable("verify '"+AssertName.get(i)+"'  is displaying for each product or not");
                if (NoProducts == NOProductName) {
                    ActualLable(" ' "+AssertName.get(i)+" ' is displaying for all Products ", "Pass");
                } else {
                    ActualLable("' "+AssertName.get(i)+" ' is not displaying for every Product ", "Fail");
                }
            } else {
                ActualLable("' "+AssertName.get(i)+" ' is not displaying for every product on ' Favorites ' page ", "Fail");
            }
        }


    }

    public static void DeleteFavorites(WebDriver driver) throws InterruptedException, IOException, WriteException {
        Thread.sleep(2000);
        ExpectedLable("Verify number of items available in the ' Favorites ' menu");
        if(driver.findElements(DeleteItem).size()>0){
            double noOfItem = driver.findElements(DeleteItem).size();
            ActualLable("Number of items available in the ' Favorites ' list are "+noOfItem ,"Pass");
            ExpectedLable("Now Delete all items from ' Favorites ' menu");
            do{
                driver.findElements(DeleteItem).get(0).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
                Thread.sleep(1000);
            }
            while (driver.findElements(DeleteItem).size()>0);
            ActualLable("All products are deleted, items are not available in ' Favorites ' menu" ,"Pass");
        }
        else{
            ActualLable("Products are not available in ' Favorites ' menu " ,"Pass");
        }
    }

    public static void VerifyFavoritesFunctionality(WebDriver driver) throws InterruptedException, IOException, WriteException {

        String ExpectPartNum = ProductCartPage.AddToFavoritesFunctionalityPCart(driver);
        StepLable("Now Verify that the Product is added to Favorites or not");
        HomePage.ClickOnFavoritesMenu(driver);
        Thread.sleep(5000);

        ExpectedLable("Search added Product with its ' MFR Part Number '"+ExpectPartNum+", Product should listed in Favorites");
        if(driver.findElements(By.xpath("//a[contains(text(),'"+ExpectPartNum+"')]")).size()>0){
            ActualLable("Product with MFR Part Number ' "+ExpectPartNum+" ' is listed successfully in Favorites" ,"Pass");
        }else{ActualLable("Product with MFR Part Number ' "+ExpectPartNum+" is not showing in Favorites list" ,"Fail");}
    }

    public static void VerifyAddToCartOnFavoritesPage(WebDriver driver) throws InterruptedException, IOException, WriteException {
        ExpectedLable("Check 'Add to Cart' button is available on Favorites Page");
        if(driver.findElements(AddToCart).size()>0) {
            ActualLable("Successfully Verified 'Add to Cart' is available in Favorites Page", "Pass");
            ExpectedLable("Add one product to 'Shopping cart'");
            driver.findElements(AddToCart).get(0).click();
            Thread.sleep(2000);
            ActualLable("Successfully Clicked on Add to cart button for first product", "Pass");
        }
        else{
            ActualLable("Products are not available in Favorites Page", "Fail");
        }
    }

    public static String SelectProductOnFavoritesPage(WebDriver driver) throws InterruptedException, IOException, WriteException {

        ExpectedLable("Get the Prooduct name and Part number for the first item in the list");
        String NameOfItem = driver.findElements(ProductName).get(0).getText();
        log.info("Name of the for the product is: "+ NameOfItem);
        String PartNumberStr = driver.findElements(PartNumber).get(0).getText();
        log.info("Partnumber for the product is: "+ PartNumberStr);
        ActualLable("Successfully Stored the product name and part number","Pass");
        ExpectedLable("Click on Learn more button for perticular Item ");
        driver.findElements(LearnMore).get(0).click();
        ActualLable("Successfully clicked on Learn more button for perticular Item ","Pass");
        log.info("Clicked on Learn more button");
        return NameOfItem;
    }
}
