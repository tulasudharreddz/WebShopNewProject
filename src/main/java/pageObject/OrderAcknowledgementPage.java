package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 19-Dec-16.
 */
public class OrderAcknowledgementPage {


    static protected WebDriver driver;
    static Logger log = Logger.getLogger("Review Order Page");


    static private By PageTitle = By.xpath("//h2");
    static private By AcknowledgementLines = By.xpath("//div[@class='container-fluid']/ul/li");


    public static void OrderAcknowledgementPageVerify(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Acknowledgement Of The Order");
        Thread.sleep(5000);
        String PageAssert = driver.findElement(PageTitle).getText();
        ExpectedLable("Check 'Order Acknowledgement' page is opened or not");
        Assert.assertEquals(PageAssert, "Order Acknowledgement");
        ActualLable("'Order Acknowledgement' page verified successfully", "Pass");
    }

    public static void GetOrderAcknowledgement(WebDriver driver) throws IOException, WriteException, InterruptedException {
        OrderAcknowledgementPageVerify(driver);
        int size = driver.findElements(AcknowledgementLines).size();
        for(int i = 0;i<size;i++){
            ExpectedLable("Check 'Order Acknowledgement' line " + i);
            String orderAcknowledgementText= driver.findElements(AcknowledgementLines).get(i).getText();
            ActualLable("line"+ i +" "+ orderAcknowledgementText, "Pass");
        }

    }
}
