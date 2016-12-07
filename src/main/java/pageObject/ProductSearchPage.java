package pageObject;

import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 22-Nov-16.
 */
public class ProductSearchPage {

    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Product search page");
    static protected WebDriver driver;
    static protected ObjectRepository obje = new ObjectRepository();


    //Page Elements
    static private By PartNumber = By.xpath("//div[@class='m-t-b-15']/div[1]");
    static private By LearnMore = By.xpath("//button[contains(text(),'Learn More')]");
    static private By AddToCart = By.xpath("//button[contains(text(),'Add to Cart')]");
    static private By ProductName = By.xpath("//p[@class='product-name clickable']/a");
    static private By Category = By.xpath("//ol/li/a[contains(text(),'Categories')]");
    static private By SearchField = By.xpath("//is-typeahead/span/input");
    static private By AvailabilityStatus = By.xpath("//is-availability/div/span");
    static private By AvailabilityBlock = By.xpath("//is-availability");
    static private By ProductPrice = By.xpath("//p[@class='product-price']");
    static private By NoOfProducts = By.xpath("//div[@class='product-row']");
    static private By Manufacturer = By.xpath("//span[contains(text(),'Manufacturer')]");
    static private By Image = By.xpath("//img[@class='img-responsive']");
    static private By MFRPart = By.xpath("//span[contains(text(),'Mfr Part')]");
    static private By previousPage = By.xpath("(//a[@class='previous'])[1]");
    static private By nextPage = By.xpath("(//a[@class='next'])[1]");
    static private By ActivePage = By.xpath("(//ul[@class='pagination']/li[@class='active']/a)[1]");
    static private By Select10 = By.xpath("//li[@class='select2-results__option'][contains(text(),'10')]");
    static private By Select20 = By.xpath("//li[@class='select2-results__option'][contains(text(),'20')]");
    static private By NoOfSearchResults = By.xpath("//div[@class='pager-count']");



    public static WebElement SearchField(WebDriver driver) {
        element = driver.findElement(SearchField);
        return element;
    }

    public static List<WebElement> PartNumber(WebDriver driver) {

        List<WebElement> PartNumberResult = driver.findElements(PartNumber);
        return PartNumberResult;
    }

    public static List<WebElement> LearnMoreButtons(WebDriver driver) {

        List<WebElement> LearnMoreButton = driver.findElements(LearnMore);
        return LearnMoreButton;
    }

    public static List<WebElement> ProductNameSearchPage(WebDriver driver) {

        List<WebElement> Product = driver.findElements(ProductName);
        return Product;
    }

    public static String SelectProductOnSearchResultPage(WebDriver driver) throws InterruptedException, IOException, WriteException {

        StepLable("Moving to Product Cart Page");
        String s=HomePage.ShopMenuOnHomePage(driver).getText();
        ExpectedLable("Click on "+ s);
        Thread.sleep(2000);
        HomePage.ShopMenuOnHomePage(driver).click();
        log.info("Clicked on Shop menu");
        ActualLable("Successfully clicked on Product","Pass");
        ExpectedLable("Click on sub category item ");
        HomePage.SubCategoryListUnderShopMenu(driver).get(0).click();
        log.info("Clicked on Accesseries Sub category");
        Thread.sleep(2000);
        ActualLable("Successfully clicked on sub category item ","Pass");

        String NameOfItem = ProductSearchPage.ProductNameSearchPage(driver).get(0).getText();
        log.info("Name of the for the product is: "+ NameOfItem);
        String PartNumber = ProductSearchPage.PartNumber(driver).get(0).getText();
        log.info("Partnumber for the product is: "+ PartNumber);
        ExpectedLable("Click on Learn more button for perticular Item ");
        ProductSearchPage.LearnMoreButtons(driver).get(0).click();
        ActualLable("Successfully clicked on Learn more button for perticular Item ","Pass");
        log.info("Clicked on Learn more button");
        return NameOfItem;
    }
    public static void MovingToCategory(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Moving to Product Search Page");
        ExpectedLable("Clicking on Shop Menu");
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        Thread.sleep(2000);
        HomePage.ShopMenuOnHomePage(driver).click();
        ActualLable("Successfully clicked Shop Menu","Pass");
        log.info("Clicked on Shop menu");
        ExpectedLable("Clicking on Sub Category under Shop Menu");
        Thread.sleep(1000);
        HomePage.SubCategoryListUnderShopMenu(driver).get(0).click();
        ActualLable("Successfully clicked on Sub Category under Shop Menu","Pass");
        log.info("Clicked on Accesseries Sub category");
        Thread.sleep(2000);
        driver.findElement(Category).click();

    }
    public static void StatusVerifyForProducts(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify Availability Status for the product with respect to no of Quantity availability");
        ArrayList<String> al=new ArrayList<String>();//creating arraylist
        al.add("130113");//adding object in arraylist
        al.add("105155");
        al.add("130459");
        ArrayList<String> a2=new ArrayList<String>();
        a2.add("Available");
        a2.add("Limited Availability");
        a2.add("Not Available");
        ArrayList<String> a3=new ArrayList<String>();
        a3.add("25");
        a3.add("7");
        a3.add("0");
        for(int i=0;i<=2;i++){
            ExpectedLable("Searching for the product"+al.get(i) );
            Thread.sleep(1000);
            SearchField(driver).clear();
            SearchField(driver).sendKeys(al.get(i));
            SearchField(driver).sendKeys(Keys.ENTER);
            ActualLable("Successfully Searched for the product"+al.get(i),"Pass");
            ExpectedLable("Verify Availability Status For Product "+al.get(i));
            Thread.sleep(2000);
            String status = driver.findElement(AvailabilityStatus).getText();
            log.info("Actual Status of product for Part number " + al.get(i)+" is "+ status);
            log.info("No of item for the part nuber " + al.get(i)+" is " + a3.get(i));
            ActualLable("Successfully verified Availability of the product and No of item for the part number " + al.get(i)+" is " + a3.get(i),"Pass");

            ExpectedLable("Verify Assert and Status For Product "+al.get(i));
            Assert.assertEquals(status, a2.get(i));
            log.info("Assert is verified for the product with part number " + al.get(i));
            ActualLable("Assert verified successfully for the product"+al.get(i),"Pass");
        }


    }

    public static int NoofResults(WebDriver driver){
        String noOfResu = driver.findElement(NoOfSearchResults).getText();
        String s= noOfResu.substring(0,noOfResu.indexOf(' '));
        int result = Integer.parseInt(s);
        log.info(result);
        return result;
    }

    public static void PaginationFunctionality(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Pagination Functionality on Search result page");
        //Verify no of products in result page
        int NoOfResults = NoofResults(driver);
        ExpectedLable("verify Next Page icon should navigate to next page");
        if(NoOfResults>10){
            String currentpage = driver.findElement(ActivePage).getText();
            driver.findElement(nextPage).click();
            Thread.sleep(1000);
            String Presentpage = driver.findElement(ActivePage).getText();
            if(currentpage==Presentpage){
                ActualLable("next Page link functionality is not working", "Fail");
            }
            else{

                ActualLable("Successfully verified next Page link functionality ", "Pass");
            }

            ExpectedLable("verify Previous Page link should navigate to Previous page");

            String Presenpage1 = driver.findElement(ActivePage).getText();
            driver.findElement(previousPage).click();
            Thread.sleep(1000);
            String Presenpage2 = driver.findElement(ActivePage).getText();
            if(Presenpage1==Presenpage2){
                ActualLable("Previous Page link functionality is not working", "Fail");
            }
            else{
                ActualLable("Successfully verified Previous Page link functionality ", "Pass");
            }
        }
        else{
            ActualLable("Next Page link functionality is not working", "Fail");
        }
        ExpectedLable("verify Specific page number functionality it should navigate to Specific selected page");
        if(NoOfResults>20){
            String Previouspage = driver.findElement(ActivePage).getText();
            driver.findElement(By.xpath("//a[@class='clickable'][contains(text(),'3')]")).click();
            Thread.sleep(1000);
            String Prepage = driver.findElement(ActivePage).getText();
            if(Previouspage==Prepage){
                ActualLable("Specific page link functionality is not working", "Fail");
            }
            else{
                ActualLable("Successfully verified Specific page link functionality ", "Pass");
            }
        }


    }

    public static void ContentinSearchReasult(WebDriver driver) throws IOException, WriteException, InterruptedException {
        Thread.sleep(3000);
        StepLable("Content verification on Search result page");
        //Verify no of products in result page
        ExpectedLable("verify No of products on product search page");
        int NoProducts = driver.findElements(NoOfProducts).size();
        ActualLable("Successfully verified no of products, products available on the result page is " + NoProducts, "Pass");

        ArrayList<String> AssertName=new ArrayList<String>();//creating arraylist
        AssertName.add("Product Name");//adding object in arraylist
        AssertName.add("MFR Part number");
        AssertName.add("Manufacturer name");
        AssertName.add("Category name");
        AssertName.add("ProductPrice name");
        AssertName.add("Image");
        AssertName.add("Learn More");
        AssertName.add("AddTo Cart");
        AssertName.add("Availability of product");

        ArrayList<org.openqa.selenium.By> AssertXpath=new ArrayList<org.openqa.selenium.By>();
        AssertXpath.add(ProductName);
        AssertXpath.add(MFRPart);
        AssertXpath.add(Manufacturer);
        AssertXpath.add(Category);
        AssertXpath.add(ProductPrice);
        AssertXpath.add(Image);
        AssertXpath.add(LearnMore);
        AssertXpath.add(AddToCart);
        AssertXpath.add(AvailabilityBlock);

        for(int i=0; i<=8; i++) {
            ExpectedLable("verify "+AssertName.get(i)+" for each product is displaying on product search page");
            if (driver.findElement(AssertXpath.get(i)).isDisplayed()) {
                ActualLable(""+AssertName.get(i)+" for each product is displaying on product search page ", "Pass");
                int NOProductName = driver.findElements(AssertXpath.get(i)).size();
                ExpectedLable("verify "+AssertName.get(i)+"  for each product is displaying for each product or not");
                if (NoProducts == NOProductName) {
                    ActualLable(""+AssertName.get(i)+" is displaying for all Products ", "Pass");
                } else {
                    ActualLable("no of "+AssertName.get(i)+"  for each product is not same as no of products", "Fail");
                }
            } else {
                ActualLable(""+AssertName.get(i)+" for each product is not displaying on product search page ", "Fail");
            }
        }
    }
}
