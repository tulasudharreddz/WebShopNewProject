package pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class HomePage {

    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Home Page");


    public static void PageTitle(WebDriver driver) {
        //WebDriver driver = null;
        String Actualtext = driver.getTitle();

        log.info("title of the page is " + Actualtext);

        Assert.assertEquals(Actualtext, "Dimension Data Direct");

    }

    public static WebElement ShoppingCartLinkHomePage(WebDriver driver) throws IOException {

        element = driver.findElement(By.xpath("//a[contains(text(),'Shopping Cart')]"));
        //element = driver.findElement(By.JsonPath.read(jsonfile,"$."+"SHOPINGCART"));

        return element;
    }


    public static void AsertVerifyForShoppingCartLinkHomePage(WebDriver driver) throws InterruptedException, IOException {

        ShoppingCartLinkHomePage(driver).click();
        Thread.sleep(2000);

        String Title = driver.findElement(by.xpath("//span[contains(text(),'Shopping Cart')]")).getText();
        log.info("Title of the page is " + Title);

        Assert.assertEquals(Title, "Shopping Cart");

    }

    public static WebElement HomeCartLinkHomePage(WebDriver driver) {


        element = driver.findElement(By.xpath("//div[@class='header-links']/ul/li[1]/a[contains(text(),'Home')]"));
        return element;
    }
    public static WebElement AssertVerifyForHomePage(WebDriver driver) {

        List<WebElement> MenuList = driver.findElements(By.xpath("//h2[contains(text(),'Best Sellers')]"));
        if(MenuList.size()>0){
            String title = driver.findElement(By.xpath("//h2[contains(text(),'Best Sellers')]")).getText();
            Assert.assertEquals(title, "Best Sellers");
            log.info("Assert verified for Home Page ");
        }
        else{
            log.info("Failure: Assert verification is failed for Home Page ");
        }


        return element;
    }

    public static void AsertVerifyForHomeLinkHomePage(WebDriver driver) throws InterruptedException, IOException {
        AsertVerifyForShoppingCartLinkHomePage(driver);
        log.info("System is navigate to the Shipping page");
        log.info("Now clicking on Home link");

        HomeCartLinkHomePage(driver).click();
        Thread.sleep(2000);

		/*String Title = driver.findElement(by.xpath("//header[@class='content-header']/h2")).getText();
        log.info("Title of the page is "+Title);

		Assert.assertEquals(Title, "Welcome Standard User");*/

        String Title = driver.findElement(by.xpath("//h2[contains(text(),'Best Sellers')]")).getText();
        log.info("Title of the page is " + Title);

        Assert.assertEquals(Title, "Best Sellers");

    }

    public static WebElement MyAccountMenuonHomePage(WebDriver driver) {


        element = driver.findElement(By.xpath("//a[contains(text(),'My Account')]"));
        return element;
    }

    public static List<WebElement> MyAccountMenuDropDownListonHomePage(WebDriver driver) {


        List<WebElement> MenuList = driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li"));
        return MenuList;
    }

    public static WebElement ListOfOptionsMyAccountMenu(WebDriver driver) {

        String MyAccountTitle = MyAccountMenuonHomePage(driver).getText();
        Assert.assertEquals(MyAccountTitle, "My Account");
        if (MyAccountMenuonHomePage(driver).isDisplayed()){
            log.info("Assert is verified for My Account menu on Home page");
            MyAccountMenuonHomePage(driver).click();
            log.info("Clicked on My Account menu on home page");

            for(WebElement e : MyAccountMenuDropDownListonHomePage(driver)) {
                log.info("List under My Menu modules are "+ e.getText());

            }
        }
        else{
            log.info("Failed: Assert is failed for My Account menu on Home page");
        }

        return element;
    }

    public static WebElement ShopMenuOnHomePage(WebDriver driver) {


        element = driver.findElement(By.xpath("(//a[contains(text(),'Shop')])[2]"));
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

    public static WebElement VerifyDropDownListUnderShopMenu(WebDriver driver) {

        String ShopTitle = ShopMenuOnHomePage(driver).getText();
        Assert.assertEquals(ShopTitle, "Shop");
        if (ShopMenuOnHomePage(driver).isDisplayed()){
            log.info("Assert is verified for Shop menu on Home page");
            ShopMenuOnHomePage(driver).click();
            log.info("Clicked on Shop menu on home page");

            for(WebElement e : CategoryListUnderShopMenu(driver)) {
                log.info("Main Category List under Shop Menu modules are "+ e.getText());
            }
            for(WebElement Sub : SubCategoryListUnderShopMenu(driver)) {
                log.info("Sub Category list under My Menu modules are " + Sub.getText());
            }
        }
        else{
            log.info("Failed: Assert is failed for My Account menu on Home page");
        }

        return element;
    }
    public static WebElement AsertVerificationForCategoryUnderShopMenu(WebDriver driver) throws InterruptedException {

        String ShopTitle = ShopMenuOnHomePage(driver).getText();
        Assert.assertEquals(ShopTitle, "Shop");
        if (ShopMenuOnHomePage(driver).isDisplayed()){
            log.info("Assert is verified for Shop menu on Home page");
            ShopMenuOnHomePage(driver).click();
            log.info("Clicked on Shop menu on home page");

            int NoOfSubCategory = SubCategoryListUnderShopMenu(driver).size();

            log.info("Number of Sub category under shop menu is " +NoOfSubCategory);

            ShopMenuOnHomePage(driver).click();

            for(int i=1;i<=NoOfSubCategory;i++){
                ShopMenuOnHomePage(driver).click();
                String SubmenuName = driver.findElement(By.xpath("(//div[@class='row']/div/ul/li/a)["+i+"]")).getText();
                log.info("Name of Sub Category is  " +SubmenuName);
                driver.findElement(By.xpath("(//div[@class='row']/div/ul/li/a)["+i+"]")).click();
                Thread.sleep(2000);

                if(i== 4){
                    String AssertName1 = driver.findElement(By.xpath("(//h2)[1]")).getText();
                    Assert.assertEquals(AssertName1,"Applications");
                    log.info("Assert is verified for "+ AssertName1);
                }
                else if(i== 8) {
                    String AssertName1 = driver.findElement(By.xpath("(//h2)[1]")).getText();
                    Assert.assertEquals(AssertName1, "Audio & Video");
                    log.info("Assert is verified for "+ AssertName1);
                }
                else if(i== 21) {
                    String AssertName1 = driver.findElement(By.xpath("(//h2)[1]")).getText();
                    Assert.assertEquals(AssertName1, "Storage & Memory");
                    log.info("Assert is verified for "+ AssertName1);
                }
                else {
                    String AssertName = driver.findElement(By.xpath("//h2/i")).getText();
                    Assert.assertEquals(SubmenuName, AssertName);
                    log.info("Assert is verified for "+ AssertName);
                }
            }
        }
        else{
            log.info("Failed: Assert is failed for My Account menu on Home page");
        }
        return element;
    }


}
