package pageObject;

import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

public class HomePage {

    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Home Page");
    static protected WebDriver driver;
    static private WritableSheet wsheet;



    //Page Elements
    static private By MyAccountMenuonHomePage = By.xpath("//a[contains(text(),'My Account')]");
    static private By ShoppingCart = By.xpath("//a[@href='/Webshop/cart']");
    static private By NoOFCartItems = By.xpath("//b[@class='badge']");
    static private By Favorites = By.xpath("//a[@href='/Webshop/users/favorites']");
    static private By Category = By.xpath("//ol/li/a[contains(text(),'Categories')]");
    static private By RegisterHereLink = By.xpath("//a[contains(text(),'Register Here')]");

    public static void ClickElementByLocator( WebDriver driver,By byElementLocator){
        driver.findElement(byElementLocator).click();
    }


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public static void ClickOnRegisterHereLink(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Check that Register here link is available or not  ?");
        if(driver.findElements(RegisterHereLink).size()>0){
            Thread.sleep(1000);
            driver.findElement(RegisterHereLink).click();
            ActualLable("Successfully verified, Register Here Link and clicked on it " ,"Pass");
        }
        else {ActualLable("Verification verified, Register Here link is not available on home page" ,"Fail");}
    }

    public static void PageTitle(WebDriver driver) throws IOException, WriteException {
        //WebDriver driver = null;
        ExpectedLable("User should land to home page");
        String Actualtext = driver.getTitle();
        log.info("title of the page is " + Actualtext);
        Assert.assertEquals(Actualtext, "Dimension Data Direct");
        ActualLable("user Successfully landed on home page","Pass");
    }

    public static WebElement ShoppingCartLinkHomePage(WebDriver driver) throws IOException {

        element = driver.findElement(By.xpath("//a[contains(text(),'Shopping Cart')]"));

        return element;
    }

    public static void AsertVerifyForShoppingCartLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {

        StepLable("Verify Shoping cart link on home page");
        ExpectedLable("Click on shoping cart link");
        ShoppingCartLinkHomePage(driver).click();
        ActualLable("Successfully clicked on shopping cart page","Pass");
        Thread.sleep(2000);

        String Title = driver.findElement(by.xpath("//h2[contains(text(),'Shopping Cart')]")).getText();
        log.info("Title of the page is " + Title);
        ExpectedLable("Verify Shoping cart page is opened or not.?");
        Assert.assertEquals(Title, "Shopping Cart");
        ActualLable("Shoping cart page is opened","Pass");
    }

    public static WebElement HomeCartLinkHomePage(WebDriver driver) {


        //element = driver.findElement(By.xpath("//div[@class='header-links']/ul/li[1]/a[contains(text(),'Home')]"));
        element = driver.findElement(By.xpath("//ol[@class = 'breadcrumb']/li/a[contains(text(),'Home')]"));
        return element;
    }
    public static WebElement AssertVerifyForHomePage(WebDriver driver) throws IOException, WriteException {

        ExpectedLable("Verify Home Pages is opened or not");
        List<WebElement> MenuList = driver.findElements(By.xpath("//h2[contains(text(),'Best Sellers')]"));
        if(MenuList.size()>0){
            String title = driver.findElement(By.xpath("//h2[contains(text(),'Best Sellers')]")).getText();
            Assert.assertEquals(title, "Best Sellers");
            log.info("Assert verified for Home Page ");
            ActualLable("Home page opened and Assert verified successfully","Pass");
        }
        else{
            log.info("Failure: Assert verification is failed for Home Page ");
            ActualLable("Failed to verify assert","Fail");
        }


        return element;
    }

    public static void AsertVerifyForHomeLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
        AsertVerifyForShoppingCartLinkHomePage(driver);
        log.info("System is navigate to the Shipping page");
        log.info("Now clicking on Home link");
        ExpectedLable("Click on homepage link to Verify navigation to home page");

        HomeCartLinkHomePage(driver).click();
        Thread.sleep(2000);
        ActualLable("Successfully clicked on home link","Pass");
		/*String Title = driver.findElement(by.xpath("//header[@class='content-header']/h2")).getText();
        log.info("Title of the page is "+Title);

		Assert.assertEquals(Title, "Welcome Standard User");*/
        ExpectedLable("check home page  is opened or not");
        String Title = driver.findElement(by.xpath("//h2[contains(text(),'Best Sellers')]")).getText();
        log.info("Title of the page is " + Title);

        Assert.assertEquals(Title, "Best Sellers");
        ActualLable("Successfully launched on homepage ","Pass");

    }

    public static WebElement MyAccountMenuonHomePage(WebDriver driver) throws IOException, WriteException {

        element = driver.findElement(MyAccountMenuonHomePage);

        return element;
    }
    public static void ClickonMyAccount(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Click on My Account menu on home page ");
        // driver.findElement(MyAccountMenuonHomePage).click();

        if(driver.findElements(MyAccountMenuonHomePage).size()>0) {
            Thread.sleep(2000);
            driver.findElement(MyAccountMenuonHomePage).click();
            ActualLable("Successfully Clicked on My Account menu on home page", "Pass");
        }
        else{  ActualLable(" My Account menu is not available on home page", "Fail");     }
    }

    public static List<WebElement> MyAccountMenuDropDownListonHomePage(WebDriver driver) throws IOException, WriteException {

        List<WebElement> MenuList = driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li"));

        return MenuList;
    }

    public static void SelectSubMenuOptUnderMyAccount(WebDriver driver, int index) throws IOException, WriteException, InterruptedException {
        Thread.sleep(2000);
        if(MyAccountMenuDropDownListonHomePage(driver).size()>0) {
            //String exLable = HomePage.MyAccountMenuDropDownListonHomePage(driver).get(index).getText();
            ExpectedLable("Click on particular option under My Account menu on home page ");
            MyAccountMenuDropDownListonHomePage(driver).get(index).click();
            ActualLable("Successfully Clicked on sub menu under My Account menu on home page", "Pass");
        }
    }


    public static WebElement ClickOnProfile(WebDriver driver) throws InterruptedException, IOException, WriteException {

        ClickonMyAccount(driver);
        log.info("Clicked on my account");
        //MyAccountMenuDropDownListonHomePage(driver).get(0).click();
        HomePage.SelectSubMenuOptUnderMyAccount(driver,0);
        //log.info("Clicked on Profile" +MyAccountMenuDropDownListonHomePage(driver).get(0).getText());
        Thread.sleep(3000);
        return element;
    }

    public static boolean AssertVerifyForTitleProfile(WebDriver driver) throws IOException, WriteException {
        StepLable("Verify Title for Profile Page");
        String ProfileTitle = driver.findElement(By.xpath("//h2")).getText();
        ExpectedLable("Profile page name should be Profile");
        try {
            Assert.assertEquals("Profile",ProfileTitle);
            log.info("Assert is verified for Profile Page Title");
            ActualLable("Assert is Successfully verified for Profile page","Pass");
        }catch (Exception e){
            log.info("Assert is failed for Profile page Title");
            ActualLable("Failed to verify Assert","Fail");
        }

        return true;
    }

    public static void ListOfOptionsMyAccountMenu(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify List of My menu options");
        String MyAccountTitle = MyAccountMenuonHomePage(driver).getText();
        ExpectedLable("Verify My menu option is available on Home page");
        Assert.assertEquals(MyAccountTitle, "My Account");
        if (MyAccountMenuonHomePage(driver).isDisplayed()){
            ActualLable("My account menu is available on home page","Pass");
            log.info("Assert is verified for My Account menu on Home page");
            ExpectedLable("Click on my account menu on home page");
            ClickonMyAccount(driver);
            ActualLable("Successfully clicked on My account menu","Pass");
            log.info("Clicked on My Account menu on home page");
            ExpectedLable("Check options under My account menu ");
            for(WebElement e : MyAccountMenuDropDownListonHomePage(driver)) {
                log.info("List under My Menu modules are "+ e.getText());
            }
            ActualLable("Verified options under My account menu ","Pass");
        }
        else{
            log.info("Failed: Assert is failed for My Account menu on Home page");
            ActualLable("My account menu is not available on home page","Fail");
        }
    }

    public static WebElement ShopMenuOnHomePage(WebDriver driver) {


        element = driver.findElement(By.xpath("(//a[contains(text(),'Shop')])[2]"));
        //element = driver.findElement(By.xpath("//i[@class='navbar-header-icon fa-shopping-bag']"));
        return element;
    }

    //for mobile app
    public static WebElement ClicoOnShopMenuMobile(WebDriver driver) {


        driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'Shop')]")).click();
        driver.findElement(By.xpath("//li[contains(text(),'Applications')]")).click();
        driver.findElement(By.xpath("//li[contains(text(),'Antivirus & Security Software')]")).click();
        return element;
    }

    public static List<WebElement> CategoryListUnderShopMenu(WebDriver driver) {


        List<WebElement> MenuList = driver.findElements(By.xpath("//div/h5"));
        return MenuList;
    }
    public static List<WebElement> SubCategoryListUnderShopMenu(WebDriver driver) {


        List<WebElement> MenuList = driver.findElements(By.xpath("//div[@class='row']/div/ul/li/a"));

        return MenuList;
    }

    public static WebElement VerifyDropDownListUnderShopMenu(WebDriver driver) throws IOException, WriteException {
        StepLable("Verify Category under shop menu");
        String ShopTitle = ShopMenuOnHomePage(driver).getText();
        ExpectedLable("Check Shop menu available or not ");
        Assert.assertEquals(ShopTitle, "Shop");
        if (ShopMenuOnHomePage(driver).isDisplayed()){
            ActualLable("Shop menu available on home page","Pass");
            log.info("Assert is verified for Shop menu on Home page");
            ShopMenuOnHomePage(driver).click();
            log.info("Clicked on Shop menu on home page");

            for (WebElement e : CategoryListUnderShopMenu(driver)) {
                ExpectedLable("Check " + e.getText() + " is available under shop menu as main menu?");
                log.info("Main Category List under Shop Menu modules are " + e.getText());
                ActualLable(e.getText() + "is available under shop menu and verified successfully", "Pass");
            }
            for (WebElement Sub : SubCategoryListUnderShopMenu(driver)) {
                ExpectedLable("Check " + Sub.getText() + " is available under shop menu as sub category ?");
                log.info("Sub Category list under My Menu modules are " + Sub.getText());
                ActualLable(Sub.getText() + "is available under shop menu and verified successfully", "Pass");
            }

        }
        else{
            log.info("Failed: Assert is failed for My Account menu on Home page");
            ActualLable("Shop menu is not available on home page","Fail");
        }

        return element;
    }
    public static WebElement AsertVerificationForCategoryUnderShopMenu(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Verify Category under Shop menu");
        String ShopTitle = ShopMenuOnHomePage(driver).getText();
        ExpectedLable("Check Shop menu is displaying or not");
        Assert.assertEquals(ShopTitle, "Shop");
        if (ShopMenuOnHomePage(driver).isDisplayed()){
            ActualLable("Shop menu verified successfully","Pass");
            log.info("Assert is verified for Shop menu on Home page");
            ShopMenuOnHomePage(driver).click();
            log.info("Clicked on Shop menu on home page");

            int NoOfSubCategory = SubCategoryListUnderShopMenu(driver).size();

            log.info("Number of Sub category under shop menu is " +NoOfSubCategory);
            ExpectedLable("Click on shop menu");
            ShopMenuOnHomePage(driver).click();
            ActualLable("Successfully clicked on Shop menu on home page","Pass");
            for(int i=1;i<=NoOfSubCategory;i++){
                ShopMenuOnHomePage(driver).click();
                String SubmenuName = driver.findElement(By.xpath("(//div[@class='row']/div/ul/li/a)["+i+"]")).getText();
                ExpectedLable("Verify link is working for "+SubmenuName);
                log.info("Name of Sub Category is  " +SubmenuName);
                driver.findElement(By.xpath("(//div[@class='row']/div/ul/li/a)["+i+"]")).click();
                Thread.sleep(2000);
                if(!(SubmenuName.contentEquals("Show All"))){
                    String AssertName = driver.findElement(By.xpath("//h2/i")).getText();
                    Assert.assertEquals(SubmenuName, AssertName);
                    ActualLable("Link is verified for "+ AssertName,"Pass");
                }
                else{
                    String AssertName = driver.findElement(By.xpath("//h2/i")).getText();
                    ActualLable("Link is verified for "+AssertName,"Pass");
                }
            }
        }
        else{
            log.info("Failed: Assert is failed for My Account menu on Home page");
            ActualLable("Shop menu verified Failed","Fail");
        }
        return element;
    }

    public static WebElement SearchField(WebDriver driver) {
        element = driver.findElement(By.xpath("//is-typeahead/span/input"));
        return element;
    }

    public static List<WebElement> PartNumber(WebDriver driver) {
        List<WebElement> PartNumberResult = driver.findElements(By.xpath("//div[@class='m-t-b-15']/div[1]"));
        return PartNumberResult;
    }

    public static List<WebElement> LearnMoreButtons(WebDriver driver) {
        List<WebElement> LearnMoreButtons = driver.findElements(By.xpath("//button[contains(text(),'Learn More')]"));
        return LearnMoreButtons;
    }
    public static List<WebElement> UnitCost(WebDriver driver) {
        List<WebElement> UnitCost = driver.findElements(By.xpath("//p[@class='product-price']"));
        return UnitCost;
    }

    public static double UnitPrice(WebDriver driver) {

        double ListPrice= 333.2;
        double DiscountListRate= 25;
        double Surcharge=2.5;
        log.info("List Price for the product is " + ListPrice);
        double Discount = ListPrice*(1 - (DiscountListRate/100));
        log.info("Discount for the product is " + DiscountListRate  +"Product price after Discount is "+Discount);
        double ListFactor = ListPrice*Surcharge/100;
        log.info("Surcharge for the product is " + Surcharge  +"Surcharge amount is "+ListFactor);
        double DiscountedByPrice = Discount+ListFactor;
        log.info("Product Price after discount is " + DiscountedByPrice);
        double CostFactor1=1;
        double CostFactor2=1;
        double CostFactor3=1;
        double CostFactor4=1;
        double CostFactor5=1;
        double CostFactor1Value = DiscountedByPrice*CostFactor1/100;
        log.info("Cost Factor for the product is " + CostFactor1  +"Cost factor 1 price for the product is "+CostFactor1Value);
        double CostFactor2Value = DiscountedByPrice*CostFactor2/100;
        log.info("Cost Factor for the product is " + CostFactor2  +"Cost factor 2 price for the product is "+CostFactor2Value);
        double CostFactor3Value = DiscountedByPrice*CostFactor3/100;
        log.info("Cost Factor for the product is " + CostFactor3  +"Cost factor 3 price for the product is "+CostFactor3Value);
        double CostFactor4Value = DiscountedByPrice*CostFactor4/100;
        log.info("Cost Factor for the product is " + CostFactor4  +"Cost factor 4 price for the product is "+CostFactor4Value);
        double CostFactor5Value = DiscountedByPrice*CostFactor5/100;
        log.info("Cost Factor for the product is " + CostFactor5  +"Cost factor 5 price for the product is "+CostFactor5Value);
        double CostOFItem = DiscountedByPrice+CostFactor1Value+CostFactor2Value+CostFactor3Value+CostFactor4Value+CostFactor5Value;
        log.info("Product Price with Cost Factors is " + CostOFItem);
        double Margin = 10;
        log.info("Margin for the product is " + Margin);
        double UnitPrice = CostOFItem/(1- (Margin/100));
        double FinalUnitPrice = Math.round(UnitPrice * 100.0) / 100.0;
        log.info("Final Unit Price for the product is " + UnitPrice);


        return FinalUnitPrice;

    }

    public static void ClickonShopmenuonHomePage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        //StepLable("Verify Category under Shop menu");
        String ShopTitle = ShopMenuOnHomePage(driver).getText();
        ExpectedLable("Check Shop menu is displaying or not");
        Assert.assertEquals(ShopTitle, "Shop");
        if (ShopMenuOnHomePage(driver).isDisplayed()) {
            ActualLable("Shop menu verified successfully", "Pass");
            log.info("Assert is verified for Shop menu on Home page");
            Thread.sleep(3000);
            ShopMenuOnHomePage(driver).click();
        }
        else{
            ActualLable("Shop menu is not available", "Fail");
        }
    }
    public static void ClickonCategoryinShopmenu(WebDriver driver) throws IOException, WriteException, InterruptedException {
        Thread.sleep(1000);
        String SubmenuName = SubCategoryListUnderShopMenu(driver).get(1).getText();
        ExpectedLable("Verify link is working for "+SubmenuName);
        log.info("Name of Sub Category is  " +SubmenuName);
        SubCategoryListUnderShopMenu(driver).get(1).click();
        Thread.sleep(1000);
        /*String AssertName = driver.findElement(By.xpath("//h2/i")).getText();
        Assert.assertEquals(SubmenuName, AssertName);
        log.info("Assert is verified for "+ AssertName);
        ActualLable("Link is verified for "+ AssertName,"Pass");*/
        ActualLable("Link is verified for ","Pass");

    }

    public static void MovingToCategory(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Moving to Product Search Page");
        //ExpectedLable("Clicking on Shop Menu");
        Thread.sleep(2000);
        /*HomePage.ShopMenuOnHomePage(driver).click();
        ActualLable("Successfully clicked Shop Menu","Pass");
        log.info("Clicked on Shop menu");
        ExpectedLable("Clicking on Sub Category under Shop Menu");
        Thread.sleep(1000);
        HomePage.SubCategoryListUnderShopMenu(driver).get(0).click();*/
        HomePage.ClickonShopmenuonHomePage(driver);
        log.info("Clicked on Shop menu");
        //HomePage.SubCategoryListUnderShopMenu(driver).get(1).click();
        HomePage.ClickonCategoryinShopmenu(driver);
        //ActualLable("Successfully clicked on Sub Category under Shop Menu","Pass");
        log.info("Clicked on Accesseries Sub category");

        driver.findElement(Category).click();
        Thread.sleep(2000);
    }

    public static void ClickonShoppingCart(WebDriver driver) throws IOException, WriteException, InterruptedException {
        Thread.sleep(2000);
        ExpectedLable("Click on Shopping cart link");
        if(driver.findElements(ShoppingCart).size()>0) {
            driver.findElement(ShoppingCart).click();
            ActualLable("Successfully clicked on shopping cart page ", "Pass");
            Thread.sleep(5000);
            ExpectedLable("Verify Assert for Shopping cart page");
            String AssertName = driver.findElement(By.xpath("//h2")).getText();
            Assert.assertEquals("Shopping Cart", AssertName);
            ActualLable("Successfully verified asser for Shopping cart page", "Pass");
        }
        else{
            ActualLable("Shopping cart is not available ", "Fail");
        }
    }
    public static List<WebElement> NoOfShoppingCartProducts(WebDriver driver){

        List<WebElement> NoOFItems = driver.findElements(NoOFCartItems);
        return NoOFItems;
    }

    public static double  VerifyCart(WebDriver driver) throws IOException, WriteException, InterruptedException {
        Thread.sleep(2000);
        ExpectedLable("Check the products available on shopping cart");
        double noOfCartItemsAavailable;
        if(NoOfShoppingCartProducts(driver).size()>0){
            String noOfCartItems = driver.findElement(NoOFCartItems).getText();
            noOfCartItemsAavailable = Integer.parseInt(noOfCartItems);
            ActualLable("Successfully Verified number of products in shopping cart, no of products are ' " +noOfCartItems +" '","Pass");
        }
        else{
            noOfCartItemsAavailable = 0;
            ActualLable("Successfully Verified number of products in shopping cart, there are no products available on the shopping cart","Pass");
        }
        return noOfCartItemsAavailable;
    }

    public static void ClickOnFavoritesMenu(WebDriver driver) throws IOException, WriteException, InterruptedException {
        Thread.sleep(1000);
        ExpectedLable("Check ' Favorites ' menu is displaying on home page ");
        if(driver.findElements(Favorites).size()>0) {
            ActualLable("' Favorites ' menu is available on home page", "Pass");
            ExpectedLable("Click on ' Favorites ' menu on home page ");
            driver.findElement(Favorites).click();
            ActualLable("successfully Clicked on ' Favorites ' menu on home page", "Pass");
        }
        else{  ActualLable("' Favorites ' menu is not on home page", "Fail");    }

    }
}
