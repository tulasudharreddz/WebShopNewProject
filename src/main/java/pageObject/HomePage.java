package pageObject;

import GenericLib.ObjectRepository;
import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePage {

    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Home Page");
    static FileInputStream jsonfile;


    public void pathJson()  throws IOException{

        jsonfile = new FileInputStream(System.getProperty("user.dir") + "D:\\Projects_Idea\\WebShopNewProject\\src\\Resources\\Elementslocators\\HomePageElements.json");
    }


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


}
