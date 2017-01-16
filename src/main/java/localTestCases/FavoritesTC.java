package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
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
 * Created by t.mirasipally on 05-Jan-17.
 */
public class FavoritesTC extends Browser {

    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");
    static protected DataDriven excel = new DataDriven();

    protected WebDriver driver;
    private Sheet sheet;
    private WritableSheet wsheet;


    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
    }
    @BeforeMethod
    public void Url() throws IOException, BiffException, WriteException {
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        log.info("URL entered in browser");
    }
    @AfterMethod
    public void ResultStatus() throws WriteException { ReportResult();}

    @Test
    public void WS_TC_90() throws IOException, InterruptedException ,WriteException{
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(90);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            FavoriesPage.FavoritesPageVerify(driver);
            
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_91() throws IOException, InterruptedException ,WriteException{
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(91);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            FavoriesPage.FavoritesPageVerify(driver);
            if(FavoriesPage.NoOfFavoritesProducts(driver)>0){
                FavoriesPage.FavoritesPageContentVerify(driver);
            }
            else {
                FavoriesPage.AddToFavoritesFunctionality(driver);
                HomePage.ClickOnFavoritesMenu(driver);
                FavoriesPage.FavoritesPageContentVerify(driver);
            }

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    @Test
    public void WS_TC_92() throws IOException, InterruptedException ,WriteException{
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(92);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            double noOfItems =ShoppingCart.DeleteExistItem(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            if(FavoriesPage.NoOfFavoritesProducts(driver)>0){
                FavoriesPage.VerifyAddToCartOnFavoritesPage(driver);
                double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
                ExpectedLable("Verify cart count functionality by adding product to cart");
                if(noOfItemsafterAddtoCart>noOfItems){
                    ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                }
                else{ ActualLable("verification failed for cart Number functionality","Fail"); }
            }
            else {
                FavoriesPage.AddToFavoritesFunctionality(driver);
                HomePage.ClickOnFavoritesMenu(driver);
                FavoriesPage.VerifyAddToCartOnFavoritesPage(driver);
                double noOfItemsafterAddtoCart = HomePage.VerifyCart(driver);
                ExpectedLable("Verify cart count functionality by adding product to cart");
                if(noOfItemsafterAddtoCart>noOfItems){
                    ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                }
                else{ ActualLable("verification failed for cart Number functionality","Fail"); }
            }

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_93() throws IOException, InterruptedException ,WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(93);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            if(FavoriesPage.NoOfFavoritesProducts(driver)<=0){
                FavoriesPage.AddToFavoritesFunctionality(driver);
                HomePage.ClickOnFavoritesMenu(driver);
            }
            String NameOnSearchPage = FavoriesPage.SelectProductOnFavoritesPage(driver);
            ExpectedLable("Expected Assert Name: "+NameOnSearchPage);
            String NameonProductCartPage= ProductCartPage.AssertVerifyForProduct(driver);
            ActualLable("Actual assert name: "+ NameonProductCartPage,"Pass");
            ExpectedLable("Verify Assert for Learn more");
            Assert.assertEquals(NameOnSearchPage, NameonProductCartPage);
            ActualLable("Successfully verified for Learn more button functionality ","Pass");

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_94() throws IOException, InterruptedException ,WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(94);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            if(FavoriesPage.NoOfFavoritesProducts(driver)<=0){
                FavoriesPage.AddToFavoritesFunctionality(driver);
                HomePage.ClickOnFavoritesMenu(driver);
            }
            FavoriesPage.DeleteFavoritesFunctionality(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_95() throws IOException, InterruptedException ,WriteException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(95);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickOnFavoritesMenu(driver);
            FavoriesPage.DeleteFavorites(driver);
            FavoriesPage.StatusVerifyForProducts(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
}
