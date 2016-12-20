package pageObject;

import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 22-Nov-16.
 */
public class ProductCartPage {
    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Product Cart page");
    static protected WebDriver driver;
    static protected ObjectRepository obje = new ObjectRepository();


    //Page Elements
    static private By ProductName = By.xpath("//div[@class='product-name']");




    public static String  AssertVerifyForProduct(WebDriver driver) throws IOException, WriteException, InterruptedException {

        obje.repository(driver);
        //StepLable("Verify Learn More Button Functionality");
        Thread.sleep(2000);
        String SelectedProductName = driver.findElement(ProductName).getText();
        log.info("Product name on Cart page is : "+ SelectedProductName);

        return SelectedProductName;

    }


}
