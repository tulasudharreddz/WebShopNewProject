package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by t.mirasipally on 09-Dec-16.
 */
public class ShoppingCart {
    static protected WebDriver driver;

    //Page Elements
    static private By OpenItmDetailsPannel = By.xpath("//is-tabsetpanel[@header='Item Details']/div/div[@class='panel-collapse collapse in']");
    static private By ItemDetails = By.xpath("//a[contains(text(),'Item Details')]");
    static private By DeleteItem = By.xpath("//a[@class='anchor-delete']");


    public static void OpenItemDetails(WebDriver driver){
        if(driver.findElements(OpenItmDetailsPannel).size()>0){
        }
        else {
            driver.findElement(ItemDetails).click();
        }
    }
    public static void DeleteItem(WebDriver driver){
        if(driver.findElements(DeleteItem).size()>0){
            int noOfItem = driver.findElements(DeleteItem).size();
            for(int i=0; i<=noOfItem; i++) {
                driver.findElement(DeleteItem).click();
            }
        }
    }


}
