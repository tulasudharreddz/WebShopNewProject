package localTestCases;

import GenericLib.Browser;
import GenericLib.ObjectRepository;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductCartPage;
import pageObject.ProductSearchPage;

import java.io.IOException;

/**
 * Created by t.mirasipally on 22-Nov-16.
 */
public class ShopModuleTC extends Browser {
    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver=getDriver();
    }

    @BeforeMethod
    public void Url(){
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        log.info("URL entered in browser");
    }

    /*

    WS_TC_59:  Validate the 'Learn More' button functionality - Click on the Learn More button against a selected record

    */

    @Test
    public void WS_TC_59() throws IOException, InterruptedException {
        try {
            obje.repository(driver);
            log.info("WS_TC_59: Validate the 'Learn More' button functionality");
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            log.info("Login in to the webshop application");
            String NameonSearchPage = ProductSearchPage.SelectProductOnSearchResultPage(driver);
            Thread.sleep(1000);
            String NameonProductCartPage= ProductCartPage.AssertVerifyForProduct(driver);
            Assert.assertEquals(NameonSearchPage, NameonProductCartPage);
            log.info("Assert Verified for the selected Product ");
            log.info("WS_TC_59: Learn More button functionality is verified");
        }
        catch (Exception e){
            log.info("Exception for the product is " + e);
        }

    }

    /*
    WS_TC_61:  Validate the 'Add to Cart' button functionality
    1. Select the Product having inventory color as Green and Click on the "Add to Cart" button
    2. Select the Product having inventory color as Yellow and Click on the "Add to Cart" button
    3. Select the Product having inventory color as Red and Click on the "Add to Cart" button
    */

    @Test
    public void WS_TC_61(){

        try {
            obje.repository(driver);
            log.info("WS_TC_59: Validate the 'Learn More' button functionality");
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            log.info("Login in to the webshop application");
            ProductSearchPage.MovingToCategory(driver);
            Thread.sleep(1000);
            ProductSearchPage.StatusVerifyForProducts(driver);
        }
        catch (Exception e){
            log.info("Exception for the product is " + e);
        }

    }

}
