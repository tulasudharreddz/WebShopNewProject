package pageObject;

import GenericLib.ObjectRepository;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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




    public static String  AssertVerifyForProduct(WebDriver driver){

        String SelectedProductName = driver.findElement(ProductName).getText();
        log.info("Product name on Cart page is : "+ SelectedProductName);

        return SelectedProductName;

    }


}
