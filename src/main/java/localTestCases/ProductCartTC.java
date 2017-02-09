package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.*;

import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.ReportResult;

/**
 * Created by t.mirasipally on 02-Jan-17.
 */
public class ProductCartTC extends Browser {

    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");
    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
    }

    /*
  WS_TC_67:  Verify the content in Product Card Page
  */
    @Test
    public void WS_TC_67() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(67);
            LoginPage.Loginfunctionality(driver);
            String NameonSearchPage=ProductSearchPage.SelectProductOnSearchResultPage(driver);
            ExpectedLable("Expected Assert Name: "+NameonSearchPage);
            String NameonProductCartPage= ProductCartPage.AssertVerifyForProduct(driver);
            ActualLable("Actual assert name: "+ NameonProductCartPage,"Pass");
            ExpectedLable("Verify Assert for Name Of the product");
            Assert.assertEquals(NameonSearchPage, NameonProductCartPage);
            ActualLable("Successfully verified for Name of the product","Pass");
            ProductCartPage.ContentVerifyForPCart(driver);
            
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        }
    }
    /*
  WS_TC_68:  Validate the "Add to Cart" button functionality
  */
    @Test
    public void WS_TC_68() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(68);
            LoginPage.Loginfunctionality(driver);
            double noOfItems = ShoppingCart.DeleteExistItem(driver);
            log.info("Login in to the webshop application");
            ProductSearchPage.SelectProductOnSearchResultPage(driver);
            ProductCartPage.AddToCartFunctionalityPCart(driver);
            double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
            ExpectedLable("Product Count in Shoping cart should increase by one");
            if(noOfItemsafterAddtoCart-noOfItems==1){
                ActualLable("successfully verified ' Add To Cart ' Button functionality and items in cart is increased by one ","Pass");
            }
            else{
                ActualLable("verification failed for ' Add To Cart ' Button functionality","Fail");
            }
            
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        }
    }

    /*
    WS_TC_69:Validate the "Add to Favorites" button functionality
    */
    @Test
    public void WS_TC_69() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(69);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            FavoriesPage.DeleteFavorites(driver);
            ProductSearchPage.SelectProductOnSearchResultPage(driver);
            FavoriesPage.VerifyFavoritesFunctionality(driver);
            
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        }
    }

    /*
    WS_TC_70:  Validate the "Add to Cart" button functionality
    */
    @Test
    public void WS_TC_70() throws IOException, WriteException, InterruptedException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(69);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            FavoriesPage.DeleteFavorites(driver);
            ProductSearchPage.SelectProductOnSearchResultPage(driver);
            ProductCartPage.AddToFavoritesWithSameProductPCart(driver);
            
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
            
        }
    }
}
