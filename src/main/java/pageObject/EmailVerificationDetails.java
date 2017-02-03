package pageObject;

import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 30-Jan-17.
 */
public class EmailVerificationDetails {

    static protected WebDriver driver;
    static Logger log = Logger.getLogger("Review Order Page");
    static protected ObjectRepository obje = new ObjectRepository();

    //PageElements
    static private By OrderEmailLink = By.xpath("//img[@src='14.3.319.2/themes/basic/msg-unrd.png']/parent::td/following-sibling::td[@class='frst']/h1/a[contains(text(),'Your Dimension Data Order')]");
    static private By ProductReturnEmailLink = By.xpath("//img[@src='14.3.319.2/themes/basic/msg-unrd.png']/parent::td/following-sibling::td[@class='frst']/h1/a[contains(text(),'Product Return Request')]");
    static private By EmailSubject = By.xpath("//td[@class='sub']");
    static private By ReLoadEmails = By.xpath("//*[@id='lnkHdrcheckmessages']/img");
    static private By ViewAllFolders = By.id("lnkBrwsAllFldrs");
    static private By SelectFolderName = By.id("selbrfld");
    static private By EnterIntoFolder = By.xpath("//a[@id='lnkGotoFldr']/img");
    static private By ReferenceNumberLink = By.xpath("//div[@class='bdy']/div/div[3]/div/a");
    static private By SelectGlobalDirectFolder = By.xpath("//a[@name='lnkFldr'][@title='Global.direct']");



    public static void OutLookAccess(WebDriver driver) throws InterruptedException, AWTException, IOException, WriteException {
        obje.repository(driver);
        ExpectedLable("Launching Outlook on web");
        driver.get("https://webemail.ap.dimensiondata.com/owa/auth/logon.aspx?replaceCurrent=1&url=https%3a%2f%2fwebemail.ap.dimensiondata.com%2fowa%2f");
        Thread.sleep(1000);
        driver.findElement(By.id("rdoPrvt")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("chkBsc")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(obje.obj.getProperty("OLemail"));
        driver.findElement(By.id("password")).sendKeys(obje.obj.getProperty("OLpassword"));
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        ActualLable("Outlook is Successfully launched ", "Pass");
    }
    public static boolean VerifyOrderEmailInOutLook(WebDriver driver) throws InterruptedException, AWTException, IOException, WriteException {
        boolean Status = true;
        StepLable(" Email Verification for Order Details Email ");
        OutLookAccess(driver);
        Thread.sleep(2000);
        if(driver.findElements(SelectGlobalDirectFolder).size()>0){
            driver.findElement(SelectGlobalDirectFolder).click();
            Thread.sleep(5000);
            driver.findElement(ReLoadEmails).click();
            Thread.sleep(3000);
            ExpectedLable("Verify email Received for Order Details Email");
            if (driver.findElements(OrderEmailLink).size() > 0) {
                ActualLable("Email received successfully", "Pass");
                ExpectedLable("Now verify Email subject in Order email");
                driver.findElement(OrderEmailLink).click();
                String Subject = driver.findElement(EmailSubject).getText();
                Assert.assertEquals(Subject, "Your Dimension Data Order");
                ActualLable("Verified successfully, Email subject is verified", "Pass");
            } else {
                driver.findElement(ReLoadEmails).click();
                if (driver.findElements(OrderEmailLink).size() > 0) {
                    ActualLable("Email received successfully", "Pass");
                    ExpectedLable("Now verify Email subject in Order email");
                    driver.findElement(OrderEmailLink).click();
                    String Subject = driver.findElement(EmailSubject).getText();
                    Assert.assertEquals(Subject, "Your Dimension Data Order");
                    ActualLable("Verified successfully, Email subject is verified", "Pass");
                }else{  Status=false; ActualLable("Email Not received and Verification is failed", "Fail"); }
            }
        }
        else if(driver.findElements(ViewAllFolders).size()>0) {
            driver.findElement(ViewAllFolders).click();
            Thread.sleep(2000);
            Select dropdown = new Select(driver.findElement(SelectFolderName));
            dropdown.selectByVisibleText(". Global.direct(1)");
            Thread.sleep(2000);
            driver.findElement(EnterIntoFolder).click();
            Thread.sleep(5000);
            driver.findElement(ReLoadEmails).click();
            Thread.sleep(3000);
            ExpectedLable("Verify email Received for Order Details Email");
            if (driver.findElements(OrderEmailLink).size() > 0) {
                ActualLable("Email received successfully", "Pass");
                ExpectedLable("Now verify Email subject in Order email");
                driver.findElement(OrderEmailLink).click();
                String Subject = driver.findElement(EmailSubject).getText();
                Assert.assertEquals(Subject, " Your Dimension Data Order ");
                ActualLable("Verified successfully, Email subject is verified", "Pass");
            } else { Status=false; ActualLable("Email Not received and Verification is failed", "Fail");  }
        }
        return Status;
    }
    public static void VerifyReferenceLink(WebDriver driver, String RefNumber) throws InterruptedException, AWTException, IOException, WriteException{
        ExpectedLable("Check that the Reference Number Link Available on OrderEmail or not ?");
        if(driver.findElements(ReferenceNumberLink).size()>0){
            String ActualReferenceNumber = driver.findElement(ReferenceNumberLink).getText();
            ActualLable("Reference Number link available on Order email", "Pass");
            ExpectedLable("Verify Reference Number in the Order email is same as provided in Order page ?");
            String ExpectedReferenceNumer  = RefNumber;
            if(ActualReferenceNumber.contentEquals(ExpectedReferenceNumer)) {
                ActualLable("Reference number is same as provided, Expected Ref no: "+ExpectedReferenceNumer+"Actual Ref No :"+ActualReferenceNumber, "Pass");
                StepLable("Verify Reference Link functionality");
                Thread.sleep(3000);
                ExpectedLable("Click on Reference number to verify.?");
                driver.findElement(ReferenceNumberLink).click();
                Thread.sleep(2000);
                ActualLable("Successfully Clicked on Reference Number link on Order email", "Pass");
                VerifyReferenceLinkFunctionality(driver,ExpectedReferenceNumer);
            }else{ActualLable("Ref Number verification failed, Expected Ref no: "+ExpectedReferenceNumer+"Actual Ref No :"+ActualReferenceNumber, "Pass");}
        }else {ActualLable("Reference Number Link is not available in order email. ", "Fail");}
    }
    public static void VerifyReferenceLinkFunctionality(WebDriver driver,String RefNumbe) throws InterruptedException, AWTException, IOException, WriteException{
        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        /*LoginPage.LoginPageTitle(driver);
        LoginPage.Loginfunctionality(driver);*/
        String ExpectedRefNumber = RefNumbe;
        String ActualRefNumber =  OrdersPage.VerifyOrderDetailsFromEmail(driver);
        ExpectedLable("Verify Reference Number in the Order Page is same as provided ?");
        if(ActualRefNumber.contentEquals(ExpectedRefNumber)){
            ActualLable("Reference number is same as provided, Expected Ref no: "+ExpectedRefNumber+"Actual Ref No :"+ActualRefNumber, "Pass");
            StepLable("Verify Product details on Orders page after return from email");
            OrdersPage.VerifyProductDetailsOnOrderPageFromMailReturn(driver);
            StepLable("Verify Cart summery details on Orders page after return from email");
            OrdersPage.VerifyCartSummeryDetailsOnOrderPageFromMailReturn(driver);
        }else{ActualLable("Ref Number verification failed, Expected Ref no: "+ExpectedRefNumber+"Actual Ref No :"+ActualRefNumber, "Pass");}

    }
    public static boolean VerifyRequestReturnEmailInOutLook(WebDriver driver) throws InterruptedException, AWTException, IOException, WriteException {
        boolean Status = true;
        StepLable(" Email Verification for Request Return Email ");
        OutLookAccess(driver);
            Thread.sleep(5000);
            driver.findElement(ReLoadEmails).click();
            Thread.sleep(3000);
            ExpectedLable("Verify email Received for Order Details Email");
            if (driver.findElements(ProductReturnEmailLink).size() > 0) {
                ActualLable("Email received successfully", "Pass");
                ExpectedLable("Now verify Email subject in Order email");
                driver.findElement(ProductReturnEmailLink).click();
                String Subject = driver.findElement(EmailSubject).getText();
                Assert.assertEquals(Subject, "Product Return Request");
                ActualLable("Verified successfully, Email subject is verified", "Pass");
            } else {
                driver.findElement(ReLoadEmails).click();
                if (driver.findElements(ProductReturnEmailLink).size() > 0) {
                    ActualLable("Email received successfully", "Pass");
                    ExpectedLable("Now verify Email subject in Order email");
                    driver.findElement(ProductReturnEmailLink).click();
                    String Subject = driver.findElement(EmailSubject).getText();
                    Assert.assertEquals(Subject, "Product Return Request");
                    ActualLable("Verified successfully, Email subject is verified", "Pass");
                }else{  Status=false; ActualLable("Email Not received and Verification is failed", "Fail"); }
            }
        return Status;
    }
}
