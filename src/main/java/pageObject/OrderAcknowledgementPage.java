package pageObject;

import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.awt.*;
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
    static protected ObjectRepository obje = new ObjectRepository();


    static private By PageTitle = By.xpath("//h2[contains(text(),'Order Acknowledgement')]");
    static private By AcknowledgementLines = By.xpath("//div[@class='container-fluid']/ul/li");
    static private By EmailBlank = By.id("login");
    static private By CheckInbox = By.xpath("//input[@value='Check Inbox']");
    static private By SelectEmails = By.id("e0");
    static private By DeleteEmails = By.xpath("//div[@id='delmenu']/following-sibling::a");
    static private By SelectFirstEmails = By.id("e1");
    static private By EmailHeader = By.xpath("//div[@id='mailhaut']/div[1]");
    static private By ShowPictures = By.xpath("//a[contains(text(),'Show pictures')]");


    public static void OrderAcknowledgementPageVerify(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable("Acknowledgement Of The Order");
        Thread.sleep(5000);
        String PageAssert = driver.findElement(PageTitle).getText();
        ExpectedLable("Check 'Order Acknowledgement' page is opened or not");
        Assert.assertEquals(PageAssert, "Order Acknowledgement");
        ActualLable("'Order Acknowledgement' page verified successfully", "Pass");
    }
    public static void GetOrderAcknowledgement(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        OrderAcknowledgementPageVerify(driver);
        int size = driver.findElements(AcknowledgementLines).size();
        for(int i = 0;i<size;i++){
            ExpectedLable("Check 'Order Acknowledgement' line : " + i+1);
            String orderAcknowledgementText= driver.findElements(AcknowledgementLines).get(i).getText();
            ActualLable("line "+ i +" is "+ orderAcknowledgementText, "Pass");
        }
    }
    public static void VerifyEmailNotificationToUser(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        obje.repository(driver);
        driver.navigate().to("http://www.yopmail.com/en/");
        ExpectedLable("Check 'Email Blank' is available or not ? " );
        if(driver.findElements(EmailBlank).size()>0){
            ActualLable("Verified successfully, Email blank is available ", "Pass");
            ExpectedLable("Enter email id and Click on check inbox" );
            driver.findElement(EmailBlank).sendKeys(obje.obj.getProperty("email"));
            driver.findElement(CheckInbox).click();
            ActualLable("Verified successfully, Email entered and Clicked on Check inbox button ", "Pass");
        }
        else {
            ActualLable("Verification failed, Email blank is not Available", "Fail");
        }
    }
    public static void DeleteExistEmails(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable(" Delete Previous Emails from inbox");
        VerifyEmailNotificationToUser(driver);
        ExpectedLable("Check Emails available in inbox or not ?" );
        if(driver.findElements(SelectFirstEmails).size()>0){
            ActualLable("Verified successfully, There are some Emails in Inbox", "Pass");
            ExpectedLable("Select all Emails and Click on Delete " );
            driver.findElement(SelectEmails).click();
            Thread.sleep(2000);
            driver.findElement(DeleteEmails).click();
            Thread.sleep(2000);
            ActualLable("Verified successfully, Deleted all Emails from inbox", "Pass");
        }
        else {
            ActualLable("Verified successfully, There are No Emails in Inbox", "Pass");
        }
    }
    public static void VerifyEmailHeader(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        VerifyEmailNotificationToUser(driver);
        ExpectedLable("Check Emails available in inbox or not ?" );
        if(driver.findElements(SelectFirstEmails).size()>0){
            ActualLable("Verified successfully, There are some Emails in Inbox", "Pass");
            ExpectedLable("Select first Email from list" );
            driver.findElements(SelectFirstEmails).get(0).click();
            ActualLable("SuccessfullyClicked on First email", "Pass");
            ExpectedLable("Verify Email Header for Order mail" );
            if(driver.findElements(ShowPictures).size()>0){
                driver.findElement(ShowPictures).click();
                String HeaderTitle= driver.findElement(EmailHeader).getText();
                if(HeaderTitle.contentEquals("Your Dimension Data Order")){
                    ActualLable("Verified successfully, Assert verified for Email header", "Pass");
                }
                else {
                    ActualLable("Verification failed, Assert verification failed for header", "Fail");
                }
            }
            else{
                String HeaderTitle= driver.findElement(EmailHeader).getText();
                if(HeaderTitle.contentEquals("Your Dimension Data Order")){
                    ActualLable("Verified successfully, Assert verified for Email header", "Pass");
                }
                else {
                    ActualLable("Verification failed, Assert verification failed for header", "Fail");
                }
            }
        }
    }
}
