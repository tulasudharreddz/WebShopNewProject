package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;

/**
 * Created by t.mirasipally on 18-Jan-17.
 */
public class OrdersPage {

    static protected WebDriver driver;
    static Logger log = Logger.getLogger("Orders Page");


    //Page Elements
    static private By QuoteDateElement = By.xpath("//span[contains(text(),'Date :')]/following-sibling::span");
    static private By OrderPageTitleElement = By.xpath("//h2");
    static private By FirstOrderElement = By.xpath("//div[@class='panel-group panel-group-info']/div[1]");
    static private By ExpandedFirstOrderElement = By.xpath("//div[@class='panel-group panel-group-info']/div[1]/is-tabsetpanel/div/div[@class='panel-collapse collapse in']");
    static private By OrderHeaderTitle = By.xpath("//div[@class='row']/div/span[1]");
    static private By NoOfOrdersElement = By.xpath("//div[@class='panel-group panel-group-info']/div");
    static private By DocumentElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Document #')]");
    static private By SapElement = By.xpath("//div[@class='row']/div/span[contains(text(),'SAP #')]");
    static private By DateElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Date :')]");
    static private By PriceElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Price :')]");
    static private By StatusElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Status :')]");





    public static void VerifyExpandAndCollapse(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Check that the first listed order is expanded or collapsed ?");
        if(driver.findElements(ExpandedFirstOrderElement).size()>0){
            ActualLable("Verified successfully, first listed order is expanded", "Pass");
            ExpectedLable("Now try to collapse the order details ");
            driver.findElement(FirstOrderElement).click();
            if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                ActualLable("Verification failed , Collapse functionality is not working", "Fail");
            }
            else{
                ActualLable("Verified successfully, Collapse functionality is working properly", "Pass");
                ExpectedLable("Now try to Expand the first order details ");
                driver.findElement(FirstOrderElement).click();
                if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                    ActualLable("Verified successfully, Expand functionality is working properly", "Pass");
                }
                else{
                    ActualLable("Verification failed , Expand functionality is not working", "Fail");
                }
            }
        }
        else{
            ActualLable("Verified successfully, first listed order is not expanded", "Pass");
            ExpectedLable("Now try to Expand the first order details ");
            driver.findElement(FirstOrderElement).click();
            if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                ActualLable("Verified successfully, Expand functionality is working properly", "Pass");
                ExpectedLable("Now try to collapse the order details ");
                driver.findElement(FirstOrderElement).click();
                if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                    ActualLable("Verification failed , Collapse functionality is not working", "Fail");
                }
                else {
                    ActualLable("Verified successfully, Collapse functionality is working properly", "Pass");
                }
            }
            else{
                ActualLable("Verification failed , Expand functionality is not working", "Fail");
            }
        }
    }

    public static boolean VerifyTheOrdersDisplay(WebDriver driver) throws IOException, WriteException, InterruptedException, ParseException {
        boolean Status = false;
        if(driver.findElements(QuoteDateElement).size()>0){
            int NoOfOrders=driver.findElements(QuoteDateElement).size();
            String DateOfFirstOrder = driver.findElements(QuoteDateElement).get(0).getText();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date date1 = sdf.parse(DateOfFirstOrder);
            for(int i=1;i<=NoOfOrders-1;i++) {
                String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(i).getText();

                Date date2 = sdf.parse(DateOfRemainOrders);
                if (date1.compareTo(date2) > 0) {
                    Status=true;
                }
                else{
                    Status=false;
                }
            }
        }
        return Status;
    }
    public static void VerifyTheOrdersSequenceFunctionality(WebDriver driver) throws IOException, WriteException, ParseException, InterruptedException {
        ExpectedLable("Verify sequence of the listed Order should be how date to low date");
        boolean Statusresult=OrdersPage.VerifyTheOrdersDisplay(driver);
        if(Statusresult==true){
            ActualLable("Verified successfully, order sequence functionality is working properly", "Pass");
        }
        else {
            ActualLable("Failed order sequence functionality", "Fail");
        }
    }
    public static void VerifyOrderPageTitle(WebDriver driver) throws IOException, WriteException {
        ExpectedLable("Check that is displaying on all orders or not ?");
        String pageTitle= driver.findElement(OrderPageTitleElement).getText().trim();
        if(pageTitle.contentEquals("Orders")){
            ActualLable("'Orders page' Title Verified successfully", "Pass");
        }else{ActualLable("Verification failed for 'Orders page' Title", "Fail");}
    }
    public static void VerifyHeaderInformationAtOrderLevel(WebDriver driver) throws IOException, WriteException {
        VerifyOrderPageTitle(driver);
        ArrayList<By> AssertXpath=new ArrayList<org.openqa.selenium.By>();
        AssertXpath.add(DocumentElement);
        AssertXpath.add(SapElement);
        AssertXpath.add(DateElement);
        AssertXpath.add(PriceElement);
        AssertXpath.add(StatusElement);
        int noOfOrder = driver.findElements(NoOfOrdersElement).size();
        for(int i=0;i<=4;i++) {
            if (driver.findElements(AssertXpath.get(i)).size() > 0) {
                ExpectedLable("Check that ' "+driver.findElement(AssertXpath.get(i)).getText()+" ' is displaying on all orders or not ?");
                int noOfDocument = driver.findElements(AssertXpath.get(i)).size();
                if (noOfOrder == noOfDocument) {
                    ActualLable("Verified successfully, ' "+driver.findElement(AssertXpath.get(i)).getText()+" ' is displaying on all orders", "Pass");
                }
                else{
                    ActualLable("Verification Failed, ' "+driver.findElement(AssertXpath.get(i)).getText()+" ' is not displaying on all orders", "Fail");
                }
            }
        }
    }

}
