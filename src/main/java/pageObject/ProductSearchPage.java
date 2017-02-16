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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
    static private By AvailabilityBlock = By.xpath("//span[@class='product-availability-text text-right']");
    static private By ProductPrice = By.xpath("//p[@class='product-price']");
    static private By NoOfProducts = By.xpath("//div[@class='product-row']");
    static private By Manufacturer = By.xpath("//span[contains(text(),'Manufacturer')]");
    static private By Image = By.xpath("//img[@class='img-responsive']");
    static private By MFRPart = By.xpath("//span[contains(text(),'Mfr Part')]");
    static private By CategoryName = By.xpath("//span[contains(text(),'Category:')]");
    static private By previousPage = By.xpath("(//a[@class='previous'])[1]");
    static private By nextPage = By.xpath("(//a[@class='next'])[1]");
    static private By ActivePage = By.xpath("(//ul[@class='pagination']/li[@class='active']/a)[1]");
    static private By Select10 = By.xpath("//li[@class='select2-results__option'][contains(text(),'10')]");
    static private By Select20 = By.xpath("//li[@class='select2-results__option'][contains(text(),'25')]");
    static private By NoOfSearchResults = By.xpath("//div[@class='pager-count']");
    static private By CurrentShownResults = By.xpath("(//span[@class='select2-selection__rendered'])[2]");
    static private By AvailableProduct = By.xpath("//ul[@class='product-availability available']/parent::div/parent::is-availability/preceding-sibling::div/button[contains(text(),'Add to Cart')]");
    static private By NotAvailableProduct = By.xpath("//ul[@class='product-availability not-available']/parent::div/parent::is-availability/preceding-sibling::div/button[contains(text(),'Add to Cart')]");
    static private By LimitedProduct = By.xpath("//ul[@class='product-availability delayed']/parent::div/parent::is-availability/preceding-sibling::div/button[contains(text(),'Add to Cart')]");
    static private By AvailableStatusXpath = By.xpath("//ul[@class='product-availability available']/following-sibling::span[contains(text(),'Available')]");
    static private By LearnMoreWithAvailableStatusXpath = By.xpath("//ul[@class='product-availability available']/following-sibling::span[contains(text(),'Available')]/parent::div/parent::is-availability/preceding-sibling::div/button[contains(text(),'Learn More')]");
    static private By NotAvailableStatusXpath = By.xpath("//ul[@class='product-availability not-available']/following-sibling::span[contains(text(),'Not Available')]");
    static private By LimitedAvailableStatusXpath = By.xpath("//ul[@class='product-availability delayed']/following-sibling::span[contains(text(),'Limited Availability')]");
    static private By LearnMoreWithNotAvailableStatusXpath = By.xpath("//ul[@class='product-availability not-available']/parent::div/parent::is-availability/preceding-sibling::div/button[contains(text(),'Learn More')]");
    static private By LearnMoreWithLimitedAvailableStatusXpath = By.xpath("//ul[@class='product-availability delayed']/parent::div/parent::is-availability/preceding-sibling::div/button[contains(text(),'Learn More')]");


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
    public static final AtomicInteger count6 = new AtomicInteger(-1);
    public static ArrayList<String> AddToShoppingCart(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Moving to Product Cart Page");
        String s=HomePage.ShopMenuOnHomePage(driver).getText();
        Thread.sleep(2000);
        HomePage.ClickonShopmenuonHomePage(driver);
        HomePage.ClickonCategoryinShopmenu(driver);
        Thread.sleep(2000);
        ExpectedLable("Get the Prooduct name and Part number for the first item in the list");
        String NameOfItem = ProductSearchPage.ProductNameSearchPage(driver).get(0).getText();
        String PartNumber = ProductSearchPage.PartNumber(driver).get(0).getText();
        System.out.println("Part number"+PartNumber);
        String ProductPriceValue = driver.findElements(ProductPrice).get(0).getText();
        String AvailabilityStatus = driver.findElements(AvailabilityBlock).get(0).getText();
        ActualLable("Successfully Stored the product name and part number","Pass");
        ExpectedLable("Add the same product to 'Shopping cart'");
        driver.findElements(AddToCart).get(count6.incrementAndGet()).click();
        Thread.sleep(2000);
        ActualLable("Successfully Clicked on Add to cart button","Pass");
        ArrayList<String> AssertName=new ArrayList<String>();
        AssertName.add(NameOfItem);
        AssertName.add(PartNumber);
        AssertName.add(ProductPriceValue);
        AssertName.add(AvailabilityStatus);
        return AssertName;
    }

    public static String SelectProductOnSearchResultPage(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Moving to Product Cart Page");
        Thread.sleep(2000);
        HomePage.ClickonShopmenuonHomePage(driver);
        HomePage.ClickonCategoryinShopmenu(driver);
        Thread.sleep(2000);
        ExpectedLable("Get the Product name and Part number for the first item in the list");
        String NameOfItem = ProductSearchPage.ProductNameSearchPage(driver).get(0).getText();
        String PartNumber = ProductSearchPage.PartNumber(driver).get(0).getText();
        String ProductPriceValue = driver.findElements(ProductPrice).get(0).getText();
        String AvailabilityStatus = driver.findElements(AvailabilityBlock).get(0).getText();
        ActualLable("Successfully Stored the product name and part number","Pass");
        ExpectedLable("Click on Learn more button for perticular Item ");
        ProductSearchPage.LearnMoreButtons(driver).get(0).click();
        ActualLable("Successfully clicked on Learn more button for particular Item ","Pass");
        return NameOfItem;
    }
    public static ArrayList<String> SelectProductFromSearchResultPage(WebDriver driver) throws InterruptedException, IOException, WriteException {
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
        String ProductPriceValue = driver.findElements(ProductPrice).get(0).getText();
        ActualLable("Successfully Stored the product Price, i.e : "+ProductPriceValue,"Pass");
        ExpectedLable("Get the Product Availability Status for searched Product");
        String AvailabilityStatus = driver.findElements(AvailabilityBlock).get(0).getText();
        ActualLable("Successfully Stored the product Availability Status, i.e : "+AvailabilityStatus,"Pass");
        ExpectedLable("Get the Product Quantity for searched Product");
        String Quantity="1";
        ActualLable("Successfully Stored the product Quantity, i.e : "+Quantity,"Pass");
        ArrayList<String> AssertName=new ArrayList<String>();
        AssertName.add(NameOfItem);
        AssertName.add(PartNumber);
        AssertName.add(ProductPriceValue);
        AssertName.add(AvailabilityStatus);
        AssertName.add(Quantity);
        ExpectedLable("Click on 'Add to Cart ' button for Searched Item ");
        driver.findElement(AddToCart).click();
        ActualLable("Successfully clicked on 'Add to Cart ' button on Searched Item ","Pass");
        return AssertName;
    }

    public static void MovingToProductSearch(WebDriver driver) throws InterruptedException, IOException, WriteException {

        ExpectedLable("Clicking on Shop Menu");
        Thread.sleep(2000);
        HomePage.ShopMenuOnHomePage(driver).click();
        ActualLable("Successfully clicked Shop Menu","Pass");
        log.info("Clicked on Shop menu");
        ExpectedLable("Clicking on Sub Category under Shop Menu");
        Thread.sleep(1000);
        HomePage.SubCategoryListUnderShopMenu(driver).get(0).click();
        ActualLable("Successfully clicked on Sub Category under Shop Menu","Pass");
    }

    public static void StatusVerifyForProducts(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify Availability Status for the product with respect to no of Quantity availability");
        ArrayList<String> al=new ArrayList<String>();//creating arraylist
        al.add("2200-15660-122");//adding object in arraylist
        al.add("2200-16155-015");
        al.add("2215-07155-001");
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
            if(driver.findElements(AvailabilityStatus).size()>0) {
                String status = driver.findElement(AvailabilityStatus).getText();
                ActualLable("Successfully verified Availability of the product and No of item for the part number " + al.get(i) + " is " + a3.get(i), "Pass");
                ExpectedLable("Verify Assert and Status For Product " + al.get(i));
                if(status.contentEquals(a2.get(i))){
                    ActualLable("Assert verified successfully for the product" + al.get(i), "Pass");
                }else{ActualLable("Assert verification failed for the product" + al.get(i), "Fail");}
            }else{ActualLable("Product Not found for Part number : " + al.get(i), "Fail");}
        }
    }

    public static int NoofResults(WebDriver driver) throws IOException, WriteException {
        ExpectedLable("verify total number of searched results");
        String noOfResu = driver.findElement(NoOfSearchResults).getText();
        String s= noOfResu.substring(0,noOfResu.indexOf(' '));
        int result = Integer.parseInt(s);
        log.info("Total no of searched results "+result);
        ActualLable("Total no of searched results "+result, "Pass");
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
        else if(NoOfResults<10) {
            ActualLable("Cant verify Specific page link functionality", "Fail");
        }
        else {
            ActualLable("Next Page link functionality is not working", "Fail");
        }

        ExpectedLable("verify Specific page number functionality it should navigate to Specific selected page");
        WebElement Secondpage =  driver.findElement(By.xpath("//a[@class='clickable'][contains(text(),'2')]"));
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
        else if(NoOfResults>10&&Secondpage.isEnabled()){
            String Previouspage = driver.findElement(ActivePage).getText();
            driver.findElement(By.xpath("//a[@class='clickable'][contains(text(),'2')]")).click();
            Thread.sleep(1000);
            String Prepage = driver.findElement(ActivePage).getText();
            if(Previouspage== Prepage){
                ActualLable("Specific page link functionality is not working", "Fail");
            }
            else{
                ActualLable("Successfully verified Specific page link functionality ", "Pass");
            }
        }
        else if(NoOfResults<10){
            ActualLable("Cant verify Specific page link functionality", "Fail");
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
        AssertXpath.add(CategoryName);
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

    public static void NoOfReultsChangeFunctionality(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify No of records per page change functionality");
        driver.navigate().refresh();
        Thread.sleep(2000);
        int NoOfResults = NoofResults(driver);
        ExpectedLable("Default no of Search results per page should be 10 ");
        String CurrentMaxNoOFResultsPerPage = driver.findElement(CurrentShownResults).getText();
        int NoResult = Integer.parseInt(CurrentMaxNoOFResultsPerPage);
        if(NoResult==10){
            ActualLable("Successfully verified Default no of Search results per page, no of search reults are "+NoResult, "Pass");
        }
        else{
            ActualLable("verification is Failed for Default no of Search results per page, no of search reults are "+NoResult, "Fail");
        }

        ExpectedLable("No of records per page should be equal to the value selected in display dropdown");

        if(NoOfResults>=10){
            int NoOfRecordsPerPage = driver.findElements(NoOfProducts).size();
            if(NoOfRecordsPerPage<=NoResult){
                ActualLable("Successfully verified no of Search results per page equal to the value selected in display dropdown, no of search results are "+NoOfRecordsPerPage, "Pass");
            }
            else {
                ActualLable("verification is Failed for no of Search results per page iss not equal to the value selected in display dropdown, no of search results are "+NoOfRecordsPerPage, "Fail");
            }
        }
        else{
            ActualLable("Successfully verified no of Search results per page equal or less  than to the value selected in display dropdown", "Fail");
        }
        ExpectedLable("No of records per page should be equal to the value selected in display dropdown, when no of results more than 25");
        if(NoOfResults>=25){
            driver.findElement(CurrentShownResults).click();
            driver.findElement(Select20).click();
            Thread.sleep(1000);
            int NoOfRecordsPerPage = driver.findElements(NoOfProducts).size();
            String CurrentShownResultsAfterChange = driver.findElement(CurrentShownResults).getText();
            int NoResultAfterChange = Integer.parseInt(CurrentShownResultsAfterChange);
            if(NoOfRecordsPerPage<=NoResultAfterChange){
                ActualLable("Successfully verified no of Search results per page equal to the value selected in display dropdown, no of search reults are "+NoOfRecordsPerPage, "Pass");
            }
            else {
                ActualLable("verification is Failed for no of Search results per page equal to the value selected in display dropdown, no of search reults are "+NoResult, "Fail");
            }
        }
        else {
            ActualLable("Cant verify functionality because no of search results are "+NoResult, "Fail");
        }

    }

    public static double AddAvailableProductToCart(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Adding Available Product to the cart");
        MovingToProductSearch(driver);
        Thread.sleep(2000);
        ExpectedLable("Add one Available product to cart, Product should added to cart and count should increase by '1'");
        for(int i=0;i<10;i++) {
            if (driver.findElements(AvailableProduct).size() > 0) {
                driver.findElements(AvailableProduct).get(0).click();
                break;
            } else {
                driver.findElement(nextPage).click();
            }
        }
        ActualLable("Successfully Added Available product to the cart","Pass");
        double noOfCartItemsAavailable = HomePage.VerifyCart(driver);
        return noOfCartItemsAavailable;
    }

    public static double AddLimitedProductToCart(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Adding Limited Available Product to the cart");
        MovingToProductSearch(driver);
        Thread.sleep(2000);
        ExpectedLable("Add one LImited Available product to cart, Product should added to cart and count should increase by '1'");
        for(int i=0;i<10;i++) {
            if (driver.findElements(LimitedProduct).size() > 0) {
                driver.findElements(LimitedProduct).get(0).click();
                break;
            } else {
                driver.findElement(nextPage).click();
            }
        }
        ActualLable("Successfully Added Limited Available product to the cart","Pass");
        double noOfCartItemsAavailable = HomePage.VerifyCart(driver);
        return noOfCartItemsAavailable;
    }

    public static double AddNotAvailableProductToCart(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Adding Not Available Product to the cart");
        MovingToProductSearch(driver);
        Thread.sleep(2000);
        ExpectedLable("Add one Not Available product to cart, Product should added to cart and count should increase by '1'");
        for(int i=0;i<10;i++) {
            if (driver.findElements(NotAvailableProduct).size() > 0) {
                driver.findElements(NotAvailableProduct).get(0).click();
                break;
            } else {
                driver.findElement(nextPage).click();
            }
        }
        ActualLable("Successfully Added Not Available product to the cart","Pass");
        double noOfCartItemsAavailable = HomePage.VerifyCart(driver);
        return noOfCartItemsAavailable;
    }

    public static void SearchingFunctionality(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Search engine functionality with different type of inputs");
        ArrayList<String> SearchText =new ArrayList<String>();//creating arraylist
        SearchText.add("Polycom SoundStation VTX 1000 - Conference phone with caller ID/call waiting - single-line operation");
        SearchText.add("Polycom");//adding object in arraylist
        SearchText.add("2200-07300-120");
        SearchText.add("Conference Phones");
        ArrayList<String> FieldsXpaths=new ArrayList<String>();
        FieldsXpaths.add("//p[@class='product-name clickable']");
        FieldsXpaths.add("//span[contains(text(),'Manufacturer:')]/parent::div");
        FieldsXpaths.add("//span[contains(text(),'Mfr Part#:')]/parent::div");
        FieldsXpaths.add("//span[contains(text(),'Category:')]/parent::div");
        ArrayList<String> TypeOfSearch =new ArrayList<String>();//creating arraylist
        TypeOfSearch.add("Name Of the Product");
        TypeOfSearch.add("Manufacturer");//adding object in arraylist
        TypeOfSearch.add("Mfr Part#");
        TypeOfSearch.add("Category");


        for(int i = 0;i<=3;i++) {
            ExpectedLable("Search Product with "+TypeOfSearch.get(i)+", Result should show related to search criteria");
            Thread.sleep(1000);
            SearchField(driver).clear();
            SearchField(driver).sendKeys(SearchText.get(i));
            System.out.println(2);
            Thread.sleep(1000);
            SearchField(driver).sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            if(driver.findElements(By.xpath(FieldsXpaths.get(i))).size()>0) {
                String ResultText = driver.findElements(By.xpath(FieldsXpaths.get(i))).get(0).getText();
                if (i == 0) {
                    ActualLable("Successfully with provided criteria i.e " + TypeOfSearch.get(i) + "", "Pass");
                    ExpectedLable("Verify result is related to searched criteria or not ?");
                    if (ResultText.contentEquals(SearchText.get(i))) {
                        ActualLable("Searched result matched with entered text", "Pass");
                    } else {  ActualLable("Failed to get searched result+", "Fail"); }
                } else {
                    ActualLable("Successfully with provided criteria i.e " + TypeOfSearch.get(i) + "", "Pass");
                    String[] parts = ResultText.split(": ");
                    String part1 = parts[1];
                    ExpectedLable("Verify result is related to searched criteria or not ?");
                    if (part1.contentEquals(SearchText.get(i))) {
                        ActualLable("Searched result matched with entered text", "Pass");
                    } else {  ActualLable("Failed to get searched result ", "Fail");   }
                }
            }else{ActualLable("Failed to get searched result "+ TypeOfSearch.get(i), "Fail");}
        }
    }
    public static void GetStatusProducts(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Verify Availability Status of the Products on Favorites Page");
        ArrayList<org.openqa.selenium.By> al=new ArrayList<org.openqa.selenium.By>();//creating arraylist
        al.add(AvailableStatusXpath);//adding object in arraylist
        al.add(LimitedAvailableStatusXpath);
        al.add(NotAvailableStatusXpath);

        ArrayList<org.openqa.selenium.By> a2=new ArrayList<org.openqa.selenium.By>();//creating arraylist
        a2.add(LearnMoreWithAvailableStatusXpath);//adding object in arraylist
        a2.add(LearnMoreWithLimitedAvailableStatusXpath);
        a2.add(LearnMoreWithNotAvailableStatusXpath);
        ArrayList<String> a3=new ArrayList<String>();//creating arraylist
        a3.add("Available");//adding object in arraylist
        a3.add("Limited Available");
        a3.add("Not Available");
        for(int i=0;i<=2;i++){
            HomePage.ClickonShopmenuonHomePage(driver);
            HomePage.ClickonCategoryinShopmenu(driver);
            GetS(driver,al.get(i),a2.get(i),a3.get(i));

        }
    }
    public static void GetS(WebDriver driver,By x,By x1,String status) throws InterruptedException, IOException, WriteException {
        ExpectedLable("Search for product which the status is "+status);
        if(driver.findElements(x).size()>0){
            ActualLable("Found Product with '" +status+ " '","Pass");
            ExpectedLable("Clicking on Learn More button to the same product");
            Thread.sleep(1000);
            driver.findElements(x1).get(0).click();
            ActualLable("Successfully clicked on Learn more button for selected Product ","Pass");
            Thread.sleep(2000);
            ExpectedLable("Trying to add Same Product to the ' Favorites '");
            driver.findElement(By.xpath("//button [contains(text(),'Add to Favorites')]")).click();
            ActualLable("Successfully clicked on Add to Favorites button for selected Product ","Pass");
            HomePage.ClickOnFavoritesMenu(driver);
            ExpectedLable("Assert Verify ' Availability Status ' for the product ");
            System.out.println(driver.findElement(AvailabilityStatus).getText());
            ActualLable("' Availability Status ' is verified successfully ","Pass");
            FavoriesPage.DeleteFavorites(driver);
        }
        else{
            ActualLable("Product not found with '" +status+ " '","Pass");
            ExpectedLable("Click on next page and search for the " +status+ " status product");
            driver.findElement(nextPage).click();
            ActualLable("Clicked on Next page button","Pass");
            GetS(driver,x,x1,status);
        }
    }

    public static void AddFlagOneProduct(WebDriver driver,int k) throws InterruptedException, IOException, WriteException {
        ArrayList<String> FlagOne =new ArrayList<String>();//creating arraylist
        FlagOne.add("CISCO3925E/K9");
        FlagOne.add("CISCO2951-HSEC+/K9");
        FlagOne.add("CISCO3945E-SEC/K9");
        FlagOne.add("WS-C2960S-48TD-L");
        FlagOne.add("AIR-SAP2602I-SK9-5");
        FlagOne.add("ASA5500-CF-256MB=");
        for(int i=0;i<=k-1;i++) {
            ExpectedLable("Searching for the Flag One product"+FlagOne.get(i));
            SearchField(driver).clear();
            SearchField(driver).sendKeys(FlagOne.get(i));
            SearchField(driver).sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            ActualLable("Successfully Searched for Flag One the product"+FlagOne.get(i), "Pass");
            ExpectedLable("Check Searched result showing or not ?");
            if (driver.findElements(AddToCart).size() > 0) {
                ActualLable("Successfully Searched for Flag One the product", "Pass");
                ExpectedLable("Click on 'Add to Cart' Button on product");
                driver.findElements(AddToCart).get(0).click();
                ActualLable("Successfully clicked on 'Add to Cart' button", "Pass");
            } else {
                ActualLable("Searched product not found", "Fail");
            }
        }
    }
    public static void AddFlagTwoProduct(WebDriver driver,int j) throws InterruptedException, IOException, WriteException {
        ArrayList<String> FlagTwo =new ArrayList<String>();//creating arraylist
        FlagTwo.add("2200-30900-025");
        FlagTwo.add("5593-823-109");
        FlagTwo.add("2399-829-109");
        FlagTwo.add("2215-07155-001");
        FlagTwo.add("2230-40300-122");
        FlagTwo.add("2200-07800-120");
        FlagTwo.add("2200-07300-120");
        FlagTwo.add("2200-07142-120");//adding object in arraylist
        for(int i=0;i<=j-1;i++) {
            ExpectedLable("Searching for the Flag Two product"+FlagTwo.get(i));
            Thread.sleep(1000);
            SearchField(driver).clear();
            SearchField(driver).sendKeys(FlagTwo.get(i));
            SearchField(driver).sendKeys(Keys.ENTER);
            ActualLable("Successfully Searched for Flag Two product"+FlagTwo.get(i), "Pass");
            Thread.sleep(3000);
            ExpectedLable("Check Searched result showing or not ?");
            if (driver.findElements(AddToCart).size() > 0) {
                ActualLable("Successfully Searched for Flag Two product", "Pass");
                ExpectedLable("Click on 'Add to Cart' Button on product");
                driver.findElements(AddToCart).get(0).click();
                ActualLable("Successfully clicked on 'Add to Cart' button", "Pass");
            } else {
                ActualLable("Searched product not found", "Fail");
            }
        }
    }
    public static void AddFlagThreeProduct(WebDriver driver,int j) throws InterruptedException, IOException, WriteException {
        ArrayList<String> FlagTwo =new ArrayList<String>();//creating arraylist
        FlagTwo.add("92714-01");
        FlagTwo.add("2200-07840-101");
        FlagTwo.add("92714-01");
        FlagTwo.add("2200-07840-101");//adding object in arraylist
        for(int i=0;i<=j-1;i++) {
            ExpectedLable("Searching for the Flag Two product"+FlagTwo.get(i));
            SearchField(driver).clear();
            SearchField(driver).sendKeys(FlagTwo.get(i));
            SearchField(driver).sendKeys(Keys.ENTER);
            ActualLable("Successfully Searched for Flag Two product"+FlagTwo.get(i), "Pass");
            Thread.sleep(3000);
            ExpectedLable("Check Searched result showing or not ?");
            if (driver.findElements(AddToCart).size() > 0) {
                ActualLable("Successfully Searched for Flag Two product", "Pass");
                ExpectedLable("Click on 'Add to Cart' Button on product");
                driver.findElements(AddToCart).get(0).click();
                ActualLable("Successfully clicked on 'Add to Cart' button", "Pass");
            } else {
                ActualLable("Searched product not found", "Fail");
            }
        }
    }
}
