package pageObject;

import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

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
    static private By Favorites = By.xpath("//a[@href='/Webshop/users/favorites']");
    static private By DeleteItem = By.xpath("//a[@class='anchor-delete']");

    public static void DeleteFavoritesItem(WebDriver driver) throws IOException, WriteException, InterruptedException {

        HomePage.ClickOnFavoritesMenu(driver);
        DeleteFavorites(driver);
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


}
