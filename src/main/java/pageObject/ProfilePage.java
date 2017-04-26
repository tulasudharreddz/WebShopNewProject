package pageObject;

import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 11/10/2016.
 */
public class ProfilePage {

    public static WebElement element;
    public static By by;
    static Logger log = Logger.getLogger("Profile Page");
    static protected WebDriver driver;
    static protected ObjectRepository obje = new ObjectRepository();


    //Page Elements
    static private By Email_Field = By.id("email");
    static private By FirstName = By.id("firstName");
    static private By LastName = By.id("lastName");
    static private By password = By.id("password");
    static private By phone = By.id("phone");
    static private By confirmPassword = By.id("confirmPassword");
    static private By Office = By.xpath("//label[contains(text(),'Office')]/following-sibling::div/is-select/span/span/span/span");
    static private By Language = By.xpath("//label[contains(text(),'Language')]/following-sibling::div/is-select/span/span/span/span");
    static private By Timezone = By.xpath("//label[contains(text(),'Timezone')]/following-sibling::div/is-select/span/span/span/span");
    static private By Save = By.xpath("//button[contains(text(),'Save')]");
    static private By ClearAll = By.xpath("//label[contains(text(),'Clear All')]");
    static private By MandatoryFields = By.xpath("//span[@class='text-danger']/parent::label/following-sibling::div/input");
    static private By ErrorMessage = By.xpath("//div[@class='text-danger']");
    static private By Address = By.xpath("//div[@class='accordion-header']/div/div[1]");
    static private By OpenAddress = By.xpath("//div[@class='panel-heading']/following-sibling::div[@class='panel-collapse collapse in']");
    static private By TitleForOenBlock = By.xpath("//div[@class='panel-collapse collapse in']/preceding-sibling::div/a/div/div/div[1]");
    static private By PannelHeading = By.xpath("//div[@class='panel-heading']");
    static private By RequestNewAddress = By.xpath("//button[contains(text(),'Request New Address')]");
    static private By InboxFolder = By.xpath("//a[@title='Inbox']");
    static private By ReLoadEmails = By.xpath("//*[@id='lnkHdrcheckmessages']/img");




    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public static void AssertVerifyForFieldsOFProfile(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        ExpectedLable("Verify first Name fields are availabel or not");
        if (driver.findElements(FirstName).size() > 0) {
            log.info("Assert is verified for first Name Field");
            ActualLable("Assert is successfully verified for First name", "Pass");
        } else {
            log.info("Assert failed for first Name Field");
            ActualLable("Assert is verification is failed for First name", "Fail");
        }
        ExpectedLable("Verify Email Field fields are availabel or not");
        if (driver.findElements(Email_Field).size() > 0) {
            log.info("Assert is verified for User ID Field");
            ActualLable("Assert is successfully verified for Email Field", "Pass");
        } else {
            log.info("Assert failed for User ID Field");
            ActualLable("Assert is verification is failed for Email Field", "Fail");
        }
        ExpectedLable("Verify Last Name fields are availabel or not");
        if (driver.findElements(LastName).size() > 0) {
            log.info("Assert is verified for last Name Field");
            ActualLable("Assert is successfully verified for Last name", "Pass");
        } else {
            log.info("Assert failed for last Name Field");
            ActualLable("Assert is verification is failed for Last name", "Fail");
        }
        ExpectedLable("Verify password fields are availabel or not");
        if (driver.findElements(password).size() > 0) {
            log.info("Assert is verified for password Field");
            ActualLable("Assert is successfully verified for password", "Pass");
        } else {
            log.info("Assert failed for password Field");
            ActualLable("Assert is verification is failed for password", "Fail");
        }
        ExpectedLable("Verify phone fields are availabel or not");
        if (driver.findElements(phone).size() > 0) {
            log.info("Assert is verified for phone Field");
            ActualLable("Assert is successfully verified for phone", "Pass");
        } else {
            log.info("Assert failed for phone Field");
            ActualLable("Assert is verification is failed for phone", "Fail");
        }
        ExpectedLable("Verify confirmPassword fields are availabel or not");
        if (driver.findElements(confirmPassword).size() > 0) {
            log.info("Assert is verified for confirm Password Field");
            ActualLable("Assert is successfully verified for confirmPassword", "Pass");
        } else {
            log.info("Assert failed for confirm Password Field");
            ActualLable("Assert is verification is failed for confirmPassword", "Fail");
        }
        ExpectedLable("Verify Office fields are availabel or not");
        if (driver.findElements(Office).size() > 0) {
            log.info("Assert is verified for Office drop down");
            ActualLable("Assert is successfully verified for Office", "Pass");
        } else {
            log.info("Assert failed for Office drop down");
            ActualLable("Assert is verification is failed for Office", "Fail");
        }
        ExpectedLable("Verify Language fields are availabel or not");
        if (driver.findElements(Language).size() > 0) {
            log.info("Assert is verified for Language Field");
            ActualLable("Assert is successfully verified for Language", "Pass");
        } else {
            log.info("Assert failed for Language Field");
            ActualLable("Assert is verification is failed for Language", "Fail");
        }
        ExpectedLable("Verify Timezone fields are availabel or not");
        if (driver.findElements(Timezone).size() > 0) {
            log.info("Assert is verified for Timezone Field");
            ActualLable("Assert is successfully verified for Timezone", "Pass");
        } else {
            log.info("Assert failed for Timezone Field");
            ActualLable("Assert is verification is failed for Timezone", "Fail");
        }
        ExpectedLable("Verify Save button are availabel or not");
        if (driver.findElements(Save).size() > 0) {
            log.info("Assert is verified for Save Button");
            ActualLable("Assert is successfully verified for Save", "Pass");
        } else {
            log.info("Assert failed for Save Button");
            ActualLable("Assert is verification is failed for Save", "Fail");
        }

    }
    public static List<WebElement> NoOfMandatoryFields(WebDriver driver) {


        List<WebElement> NoOfMandatoryFields = driver.findElements(MandatoryFields);
        return NoOfMandatoryFields;
    }
    public static void MandatoryFieldsProfilePage(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable("Verify Mandatory fields functionality");
        int NoOfMandatory = NoOfMandatoryFields(driver).size();
        for (int i = 0; i <= NoOfMandatory - 1; i++) {
            //NoOfMandatoryFields(driver).get(i).clear();
            NoOfMandatoryFields(driver).get(i).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            NoOfMandatoryFields(driver).get(i).sendKeys(Keys.BACK_SPACE);
            NoOfMandatoryFields(driver).get(i).sendKeys(Keys.TAB);
            List<WebElement> Lablename = driver.findElements(By.xpath("//label"));
            String Lablenames = Lablename.get(i).getText();
            ExpectedLable("Check the error message for Mandatory field" + Lablenames);
            if (driver.findElements(ErrorMessage).size() > 0) {
                String ErrorMessageFor = driver.findElement(By.xpath("//div[@class='text-danger']")).getText();
                ActualLable("Successfully verified for " + Lablenames, "Pass");
                log.info("Error Message for the field " + Lablenames + " is " + ErrorMessageFor);
            } else {
                log.info("Error message is not shown for the field " + Lablenames);
                ActualLable("Failed to verify for " + Lablenames, "Fail");
                log.info("Test case is failed for the field " + Lablenames);
            }
        }
    }
    public static List<WebElement> AddressHeaders(WebDriver driver) {
        List<WebElement> AddressHeaders = driver.findElements(Address);
        return AddressHeaders;
    }
    public static void VerifyAddressHeaders(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable("Verify Available address blocks");
        int noOfHeaders = AddressHeaders(driver).size();
        for (int i = 0; i <= noOfHeaders - 1; i++) {
            String HeaderTitle = AddressHeaders(driver).get(i).getText();
            if (i == 0) {
                try {
                    ExpectedLable("Verify " + HeaderTitle + " is Displayed or not");
                    Assert.assertEquals(HeaderTitle, "Billing Addresses");
                    log.info("Assert is verified for " + HeaderTitle);
                    ActualLable("Assert is verified for " + HeaderTitle, "Pass");
                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                    ActualLable("verification is failed for " + HeaderTitle, "Fail");
                }
            } else if (i == 1) {
                try {
                    ExpectedLable("Verify " + HeaderTitle + " is Displayed or not");
                    Assert.assertEquals(HeaderTitle, "Shipping Addresses");
                    log.info("Assert is verified for " + HeaderTitle);
                    ActualLable("Assert is verified for " + HeaderTitle, "Pass");
                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                    ActualLable("verification is failed for " + HeaderTitle, "Fail");
                }
            } else if (i == 2) {
                try {
                    ExpectedLable("Verify " + HeaderTitle + " is Displayed or not");
                    Assert.assertEquals(HeaderTitle, "Install Addresses");
                    log.info("Assert is verified for " + HeaderTitle);
                    ActualLable("Assert is verified for " + HeaderTitle, "Pass");
                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                    ActualLable("verification is failed for " + HeaderTitle, "Fail");
                }
            } else {
                log.info("Assert verification is failed for " + HeaderTitle);
            }
        }


    }
    public static List<WebElement> HeaderOpeninAddress(WebDriver driver) {
        List<WebElement> OpeninAddress = driver.findElements(OpenAddress);
        return OpeninAddress;
    }
    public static void VerifyDefaultOpenBlocks(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable("Verify Default no of open Address blocks");
        int NoOfOpenBlocks = HeaderOpeninAddress(driver).size();
        ExpectedLable("Default no of open Address blocks Should be only one");
        if (NoOfOpenBlocks > 1) {
            for (int i = 0; i <= NoOfOpenBlocks; i++) {
                String TitleOfOpenBlock = driver.findElements(TitleForOenBlock).get(i).getText();
                ActualLable("Verification is failed and Opened blocks are " + TitleOfOpenBlock, "Fail");
            }
        } else {
            String TitleOfOpenBlock = driver.findElement(TitleForOenBlock).getText();
            ActualLable("Successfully verified and No of opened blocks are " + NoOfOpenBlocks, "Pass");
        }
    }
    public static List<WebElement> PannelHeading(WebDriver driver) {
        List<WebElement> PannelHeaders = driver.findElements(PannelHeading);
        return PannelHeaders;
    }
    public static void VerifyExpandCollapse(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable(" Verify Expand and collapse functionality for address blocks");
        int noOfHeaders = AddressHeaders(driver).size();
        for (int i = 0; i <= noOfHeaders - 1; i++) {
            String HeaderTitle = AddressHeaders(driver).get(i).getText();
            if (i == 0) {
                try {
                    ExpectedLable("Verify assert for the block" + HeaderTitle);
                    Assert.assertEquals(HeaderTitle, "Billing Addresses");
                    ActualLable("Successfully verified for " + HeaderTitle, "Pass");
                    ExpectedLable("Verify Billing Addresses block is already expanded or not");
                    if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                        ActualLable("Address block for ' " + HeaderTitle + " ' is already opened", "Pass");
                        ExpectedLable("Now checking to Collapse Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            ActualLable("Expand and collapse functionality is not working " + HeaderTitle, "Fail");
                        } else {  ActualLable("Expand and collapse functionality is working properly for " + HeaderTitle, "Pass");                      }
                    } else {
                        ActualLable("Address block for ' " + HeaderTitle + " ' is not opened", "Pass");
                        ExpectedLable("Now checking to Expand Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is now opened" + HeaderTitle, "Pass");
                            ExpectedLable("Now checking to Collapse Address block");
                            AddressHeaders(driver).get(i).click();
                            Thread.sleep(1000);
                            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                                ActualLable("Expand and collapse functionality is not working " + HeaderTitle, "Fail");
                            } else {
                                ActualLable("Expand and collapse functionality is working properly for " + HeaderTitle, "Pass");
                            }
                        } else {  ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is not opened" + HeaderTitle, "Fail");  }
                    }
                } catch (Exception e) {  ActualLable("Assert verification is failed for " + HeaderTitle, "Fail");    }
            } else if (i == 1) {
                try {
                    ExpectedLable("Verify assert for the block" + HeaderTitle);
                    Assert.assertEquals(HeaderTitle, "Shipping Addresses");
                    ActualLable("Successfully verified for " + HeaderTitle, "Pass");
                    ExpectedLable("Verify " + HeaderTitle + " block is already expanded or not");
                    if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                        log.info("Address block for ' " + HeaderTitle + " ' is already opened");
                        ActualLable("Address block for ' " + HeaderTitle + " ' is already opened", "Pass");
                        ExpectedLable("Now checking to Collapse Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            log.info("Expand and collapse functionality is not working");
                            ActualLable("Expand and collapse functionality is not working " + HeaderTitle, "Fail");
                        } else {
                            log.info("Expand and collapse functionality is working properly");
                            ActualLable("Expand and collapse functionality is working properly for " + HeaderTitle, "Pass");
                        }
                    } else {
                        log.info("Address block for ' " + HeaderTitle + " ' is not opened");
                        ActualLable("Address block for ' " + HeaderTitle + " ' is not opened", "Pass");
                        ExpectedLable("Now checking to Expand Address block");
                        log.info("Now checking to Expand Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            log.info("Address block for '  " + HeaderTitle + " ' is now opened");
                            ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is now opened" + HeaderTitle, "Pass");
                            log.info("Now checking to Collapse Address block");
                            ExpectedLable("Now checking to Collapse Address block");
                            AddressHeaders(driver).get(i).click();
                            Thread.sleep(1000);
                            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                                log.info("Expand and collapse functionality is not working");
                                ActualLable("Expand and collapse functionality is not working " + HeaderTitle, "Fail");
                            } else {
                                log.info("Expand and collapse functionality is working properly");
                                ActualLable("Expand and collapse functionality is working properly for " + HeaderTitle, "Pass");
                            }
                        } else {
                            log.info("Expand and collapse functionality is not working for " + HeaderTitle);
                            ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is not opened" + HeaderTitle, "Fail");

                        }
                    }

                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                    ActualLable("Assert verification is failed for " + HeaderTitle, "Fail");
                }

            } else if (i == 2) {
                try {
                    ExpectedLable("Verify assert for the block" + HeaderTitle);
                    Assert.assertEquals(HeaderTitle, "Install Addresses");
                    ActualLable("Successfully verified for " + HeaderTitle, "Pass");

                    if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                        log.info("Address block for ' " + HeaderTitle + " ' is already opened");
                        ActualLable("Address block for ' " + HeaderTitle + " ' is already opened", "Pass");
                        log.info("Now checking to Collapse Address block");
                        ExpectedLable("Now checking to Collapse Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            log.info("Expand and collapse functionality is not working");
                            ActualLable("Expand and collapse functionality is not working " + HeaderTitle, "Fail");
                        } else {
                            log.info("Expand and collapse functionality is working properly");
                            ActualLable("Expand and collapse functionality is working properly for " + HeaderTitle, "Pass");
                        }
                    } else {
                        log.info("Address block for ' " + HeaderTitle + " ' is not opened");
                        log.info("Now checking to Expand Address block");
                        ActualLable("Address block for ' " + HeaderTitle + " ' is not opened", "Pass");
                        ExpectedLable("Now checking to Expand Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            log.info("Address block for '  " + HeaderTitle + " ' is now opened");
                            ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is now opened" + HeaderTitle, "Pass");
                            log.info("Now checking to Collapse Address block");
                            ExpectedLable("Now checking to Collapse Address block");
                            AddressHeaders(driver).get(i).click();
                            Thread.sleep(1000);
                            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                                log.info("Expand and collapse functionality is not working");
                                ActualLable("Expand and collapse functionality is not working " + HeaderTitle, "Fail");
                            } else {
                                log.info("Expand and collapse functionality is working properly");
                                ActualLable("Expand and collapse functionality is working properly for " + HeaderTitle, "Pass");
                            }
                        } else {
                            log.info("Expand and collapse functionality is not working");
                            ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is not opened" + HeaderTitle, "Fail");
                        }
                    }

                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                    ActualLable("Assert verification is failed for " + HeaderTitle, "Fail");
                }

            } else {
                log.info("Assert verification is failed for " + HeaderTitle);
            }
        }
    }
    public static void OutLookAccess(WebDriver driver) throws InterruptedException, AWTException, IOException, WriteException {
        ExpectedLable("Launching Outlook on web");
        driver.get("https://webemail.ap.dimensiondata.com/owa/auth/logon.aspx?replaceCurrent=1&url=https%3a%2f%2fwebemail.ap.dimensiondata.com%2fowa%2f");
        Thread.sleep(1000);
        driver.findElement(By.id("rdoPrvt")).click();
        driver.findElement(By.id("chkBsc")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("t.mirasipally");
        driver.findElement(By.id("password")).sendKeys("Tulasi@0068");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        ActualLable("Outlook is Successfully launched ", "Pass");
    }
    public static void VerifyEmailInOutLook(WebDriver driver) throws InterruptedException, AWTException, IOException, WriteException {
        EmailVerificationDetails.OutLookAccess(driver);
        Thread.sleep(5000);
        StepLable(" Email Verification for Create New address ");
        if(driver.findElements(InboxFolder).size()>0) {
            driver.findElement(InboxFolder).click();
            Thread.sleep(5000);
            driver.findElement(ReLoadEmails).click();
            Thread.sleep(3000);
            ExpectedLable("Verify email Received for Address Delete Request");
            if (driver.findElements(By.xpath("//td[@class='frst']/h1/a[contains(text(),'Address Delete Request')]")).size() > 0) {
                ActualLable("Email received successfully", "Pass");
                driver.findElement(By.xpath("//td[@class='frst']/h1/a[contains(text(),'Address Delete Request')]")).click();
                String Subject = driver.findElement(By.xpath("//td[@class='sub']")).getText();
                if(Subject.contentEquals("Address Delete Request")) {
                    ExpectedLable("Verify Email sender Name");
                    log.info("Assert is verified ");
                    String MailSender = driver.findElement(By.xpath("//span[@class='rwRRO']")).getText();
                    String[] splited = MailSender.split("\\s+");
                    String split_one = splited[0];
                    log.info("Email sender is  " + split_one);
                    obje.repository(driver);
                    String email = obje.obj.getProperty("email");
                    Assert.assertEquals(split_one, email);
                    ActualLable("Successfully verified Email sender", "Pass");
                    //driver.findElement(By.id("lo")).click();
                }else{     ActualLable("Email sender name Verification is failed", "Fail");}
            } else {driver.findElement(ReLoadEmails).click();
                if (driver.findElements(By.xpath("//td[@class='frst']/h1/a[contains(text(),'Address Delete Request')]")).size() > 0) {
                    ActualLable("Email received successfully", "Pass");
                    driver.findElement(By.xpath("//td[@class='frst']/h1/a[contains(text(),'Address Delete Request')]")).click();
                    String Subject = driver.findElement(By.xpath("//td[@class='sub']")).getText();
                    if(Subject.contentEquals("Address Delete Request")) {
                        ExpectedLable("Verify Email sender Name");
                        log.info("Assert is verified ");
                        String MailSender = driver.findElement(By.xpath("//span[@class='rwRRO']")).getText();
                        String[] splited = MailSender.split("\\s+");
                        String split_one = splited[0];
                        log.info("Email sender is  " + split_one);
                        obje.repository(driver);
                        String email = obje.obj.getProperty("email");
                        Assert.assertEquals(split_one, email);
                        ActualLable("Successfully verified Email sender", "Pass");
                        //driver.findElement(By.id("lo")).click();
                    }else{    ActualLable("Email sender name Verification is failed", "Fail");}
                } else{   ActualLable("Email Not received and Verification is failed", "Fail");}
            }
        }
    }
    public static List<WebElement> RequestDeleteElement(WebDriver driver) {
        //List<WebElement> DeleteButton = driver.findElements(By.xpath("//button[contains(text(),'Request Delete')]"));
        List<WebElement> DeleteButton = driver.findElements(By.xpath("//div[@class='panel-collapse collapse in']/div/is-addresses/div/div/div/p/button[contains(text(),'Request Delete')]"));
        return DeleteButton;
    }
    public static void RequestDeleteFunctionality(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable("Verify Request delete address functionality");
        ExpectedLable("Click on Request delete address for the first address");
        if(RequestDeleteElement(driver).size()>0){
            RequestDeleteElement(driver).get(1).click();
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
            log.info("Clicked on OK button to confirm ");
            ActualLable("Address delete request successfully", "Pass");
        } else {
            ActualLable("Failed to send address delete request", "Fail");
        }
    }
    public static List<WebElement> LabelSymbol(WebDriver driver) {

        List<WebElement> Label = driver.findElements(By.xpath("//label[@class='is-check-radio-label']"));
        return Label;
    }
    public static void AssertVerifyForDefaultAddress(WebDriver driver) throws IOException, WriteException, InterruptedException, AWTException {
        //String Defaultxpath =LabelSymbol(driver).get(0)+"/i[@class='is-check-radio-helper on']";
        StepLable("Verify Default address is marked as Star ");
        //String Defaultxpath = "(//label[@class='is-check-radio-label'])[1]/i[@class='is-check-radio-helper on']";
        String Defaultxpath = "(//div[@class='panel-collapse collapse in']/div/is-addresses/div/div/div/is-radio/div/label[@class='is-check-radio-label'])[1]/i[@class='is-check-radio-helper on']";
        By PannelHeading = By.xpath(Defaultxpath);
        ExpectedLable("Check Default address is available at first position or not");
        if (driver.findElements(PannelHeading).size() > 0) {
            ActualLable("Default address is available at first position", "Pass");
            //String NextItemXpath = "(//label[@class='is-check-radio-label'])[2]";
            String NextItemXpath = "(//div[@class='panel-collapse collapse in']/div/is-addresses/div/div/div/is-radio/div/label[@class='is-check-radio-label'])[2]";
            By NextItem = By.xpath(NextItemXpath);
            ExpectedLable("Make Second item as Default address");
            driver.findElement(NextItem).click();
            boolean selectStatus = driver.findElement(NextItem).isEnabled();
            if (selectStatus == true) {
                ActualLable("Default address changed successfully", "Pass");
            } else {    ActualLable("Failed Default address is not changed", "Fail");            }
        } else {
            ActualLable("Default address is not available at first position ", "Pass");
            ExpectedLable("Making First item as Default address");
            driver.findElement(By.xpath("(//div[@class='panel-collapse collapse in']/div/is-addresses/div/div/div/is-radio/div/label[@class='is-check-radio-label'])[1]")).click();
            boolean selectStatus = driver.findElement(By.xpath("(//div[@class='panel-collapse collapse in']/div/is-addresses/div/div/div/is-radio/div/label[@class='is-check-radio-label'])[1]")).isEnabled();
            if (selectStatus == true) {
                ActualLable("Default address changed successfully", "Pass");
            } else {    ActualLable("Failed Default address is not changed", "Fail");     }
        }
    }
    public static List<WebElement> RequestNewAdd(WebDriver driver) {
        List<WebElement> NewAddress = driver.findElements(RequestNewAddress);
        return NewAddress;
    }
    public static void ClickonNewAddress(WebDriver driver,int indexNum) throws IOException, WriteException, InterruptedException, AWTException {
        String st = RequestNewAdd(driver).get(indexNum).getText();
        ExpectedLable("Click on " + st + " under My Account menu on home page ");
        RequestNewAdd(driver).get(indexNum).click();
        ActualLable("Successfully Clicked on sub menu under My Account menu on home page", "Pass");
    }
    public static List<WebElement> LableForNewAddress(WebDriver driver) {
        List<WebElement> Lable = driver.findElements(By.xpath("//label"));
        return Lable;
    }
    public static void AssertVerifyForLable(WebDriver driver,String AddressName) throws IOException, WriteException, InterruptedException, AWTException {
        StepLable("Verify All Labels are Available or not");
        int noOfLable = LableForNewAddress(driver).size();

        for (int i = 0; i <= noOfLable - 1; i++) {

            ArrayList<String> al = new ArrayList<String>();//creating arraylist

            al.add(""+AddressName+" Name *");//adding object in arraylist
            al.add("Address Line 1 *");
            al.add("Address Line 2");
            al.add("City *");
            al.add("State/Province");
            al.add("Postal Code");
            al.add("Country *");
            al.add("Contact Name");
            al.add("Phone");

            String ActualLableName = LableForNewAddress(driver).get(i).getText();
            ExpectedLable("Verify " + ActualLableName + " Label is Exist or not");
            log.info("Actual Lablename is " + ActualLableName);
            log.info("Expected Lablename is " + al.get(i));
            try {
                Assert.assertEquals(ActualLableName, al.get(i));
                log.info("Assert is successfully verified for " + ActualLableName);
                ActualLable("Label " + ActualLableName + " is Successfully Verified ", "Pass");
            } catch (Exception e) {
                log.info("Assert verification is failed for " + ActualLableName);
                ActualLable("Verification is failed for " + ActualLableName, "Pass");
            }

        }

    }
    private static boolean statusOfSaveButton(WebDriver driver) {
        boolean status = driver.findElement(by.xpath("//button[contains(text(),'Create')]")).isEnabled();
        return status;
    }
    public static void VerifySaveNewAddressFunctionalityWithOutData(WebDriver driver) throws InterruptedException, IOException, WriteException, AWTException {
        StepLable("Verifying New billing address functionality With out data");

        ExpectedLable("Verify Save button is enabled with out enter any mandatory fileds");
        if (statusOfSaveButton(driver) == true) {
            log.info("Verification is faiiiled for save button functionality");
            ActualLable("Failed to Verify and save button is enabled", "Fail");
        } else {
            log.info("Verification is successful for save button functionality");
            ActualLable("Successfully Verified and save button is not enabled", "Pass");
        }
        ExpectedLable("Verify Save button is enabled with only Name field data");
        driver.findElement(by.id("name")).sendKeys("test");
        if (statusOfSaveButton(driver) == true) {
            ActualLable("Failed to Verify and save button is enabled", "Fail");
        } else {
            driver.findElement(by.id("name")).clear();
            ActualLable("Successfully Verified and save button is not enabled with only Name data", "Pass");
        }
        ExpectedLable("Verify Save button is enabled with only Address Line1 field data");
        driver.findElement(by.id("line1")).sendKeys("test");
        if (statusOfSaveButton(driver) == true) {
            log.info("Verification is faiiiled for save button functionality");
            ActualLable("Failed to Verify and save button is not enabled", "Fail");
        } else {
            log.info("Verification is successful for save button functionality");
            driver.findElement(by.id("line1")).clear();
            ActualLable("Successfully Verified and save button is not enabled with only Address line1 data", "Pass");
        }
        ExpectedLable("Verify Save button is enabled with only Clity field data");
        driver.findElement(by.id("city")).sendKeys("test");
        if (statusOfSaveButton(driver) == true) {
            log.info("Verification is faiiiled for save button functionality");
            ActualLable("Failed to Verify and save button is not enabled", "Fail");
        } else {
            driver.findElement(by.id("city")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
            driver.findElement(by.id("city")).sendKeys(Keys.BACK_SPACE);
            driver.findElement(by.id("city")).sendKeys(Keys.TAB);
            ActualLable("Successfully Verified and save button is not enabled with only City Name data", "Pass");
        }
        ExpectedLable("Verify Save button is enabled with only Country field data");
        driver.findElement(by.xpath("//span[@class='select2-selection select2-selection--single']")).click();
        Thread.sleep(1000);
        driver.findElement(by.xpath("//li[contains(text(),'India')]")).click();
        if (statusOfSaveButton(driver) == true) {
            log.info("Verification is faiiiled for save button functionality");
            ActualLable("Failed to Verify and save button is not enabled", "Fail");
        } else {
            log.info("Verification is successful for save button functionality");
            ActualLable("Successfully Verified and save button is not enabled with only Country Name data", "Pass");
        }

    }
    public static void VerifySaveNewAddressFunctionalityWithData(WebDriver driver) throws InterruptedException, IOException, WriteException, AWTException {
        StepLable("Verifying New billing address functionality with mandatory fields ");

        ExpectedLable("Set data into Mandatory fields ");
        driver.navigate().refresh();
        if (statusOfSaveButton(driver) == true) {
            log.info("Verification is faiiiled for save button functionality");
        } else {
            log.info("Verification is successful for save button functionality");
        }
        driver.findElement(by.id("name")).sendKeys("test Address Name");
        driver.findElement(by.id("line1")).sendKeys("test Line 1");
        driver.findElement(by.id("city")).sendKeys("test City");
        driver.findElement(by.xpath("//span[@class='select2-selection select2-selection--single']")).click();
        Thread.sleep(1000);
        driver.findElement(by.xpath("//li[contains(text(),'India')]")).click();
        ActualLable("Successfully Entered and data in to Mandatory fields", "Pass");
        ExpectedLable("Verify Save button is enabled or not");
        if (statusOfSaveButton(driver) == true) {
            log.info("Verification is successful for save button functionality");
            ActualLable("Successfully verified and Save button is enabled and data entered in to mandatory fields", "Pass");
        } else {
            log.info("Verification is failed for save button functionality");
            ActualLable("Verifiecationis failed and Save button is not enabled ", "Fail");
        }
        //ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        //driver.switchTo().window(tabs.get(1));
        ExpectedLable("Click on Create button to create new address");
        driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
        Thread.sleep(5000);
        if (driver.findElements(By.xpath("//h2[contains(text(),'Addresses')]")).size() > 0) {

            ActualLable("Successfully Verified and New Address created successfully", "Pass");
        } else {
            ActualLable("Verification is failed and failed to create New Address ", "Fail");
        }

    }
    public static void EmailVerificationForNewAddress(WebDriver driver) throws AWTException, InterruptedException, IOException, WriteException {
        StepLable(" Email Verification for Create New address ");
        EmailVerificationDetails.OutLookAccess(driver);
        if(driver.findElements(InboxFolder).size()>0) {
            driver.findElement(InboxFolder).click();
            Thread.sleep(5000);
            driver.findElement(ReLoadEmails).click();
            Thread.sleep(3000);
            ExpectedLable("Verify email Received for Address Create Request");
            if (driver.findElements(By.xpath("//td[@class='frst']/h1/a[contains(text(),'Address Create Request')]")).size() > 0) {
                ActualLable("Email received successfully", "Pass");
                driver.findElement(By.xpath("//td[@class='frst']/h1/a")).click();
                String Subject = driver.findElement(By.xpath("//td[@class='sub']")).getText();
                try {
                    Assert.assertEquals(Subject, "Address Create Request");
                    /*ExpectedLable("Verify Email sender Name");
                    String MailSender = driver.findElement(By.xpath("//span[@class='rwRRO']")).getText();
                    String[] splited = MailSender.split("\\s+");
                    String split_one = splited[0];
                    obje.repository(driver);
                    String email = obje.obj.getProperty("email");
                    Assert.assertEquals(split_one, email);
                    ActualLable("Successfully verified Email sender", "Pass");*/
                    //driver.findElement(By.id("lo")).click();
                } catch (Exception e) { ActualLable("Email sender name Verification is failed", "Fail");     }
            } else {
                driver.findElement(ReLoadEmails).click();
                Thread.sleep(2000);
                if (driver.findElements(By.xpath("//td[@class='frst']/h1/a[contains(text(),'Address Create Request')]")).size() > 0) {
                    ActualLable("Email received successfully", "Pass");
                    driver.findElement(By.xpath("//td[@class='frst']/h1/a")).click();
                    String Subject = driver.findElement(By.xpath("//td[@class='sub']")).getText();
                    try {
                        ExpectedLable("Verify Email sender Name");
                        Assert.assertEquals(Subject, "Address Create Request");
                        String MailSender = driver.findElement(By.xpath("//span[@class='rwRRO']")).getText();
                        String[] splited = MailSender.split("\\s+");
                        String split_one = splited[0];
                        obje.repository(driver);
                        String email = obje.obj.getProperty("email");
                        Assert.assertEquals(split_one, email);
                        ActualLable("Successfully verified Email sender", "Pass");
                        //driver.findElement(By.id("lo")).click();
                    } catch (Exception e) {   ActualLable("Email sender name Verification is failed", "Fail");    }
                }  else{ActualLable("Email Not received and Verification is failed", "Fail");  }
            }
        }
    }
    public static void ExpandAddress(WebDriver driver,String HeaderTitle) throws IOException, WriteException, InterruptedException, AWTException {
        int i = 0;
        if(HeaderTitle.contentEquals("Billing Addresses")){i = 0;}
        else if (HeaderTitle.contentEquals("Shipping Addresses")){i = 1;}
        else if (HeaderTitle.contentEquals("Install Addresses")){i = 2;}
        if(HeaderTitle.contentEquals("Billing Addresses")) {
            ExpectedLable("Verify assert for the block" + HeaderTitle);
            Assert.assertEquals(HeaderTitle, "Shipping Addresses");
            ActualLable("Successfully verified for " + HeaderTitle, "Pass");
            ExpectedLable("Verify " + HeaderTitle + " block is already expanded or not");
            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                ActualLable("Address block for ' " + HeaderTitle + " ' is already opened", "Pass");
            } else {
                log.info("Address block for ' " + HeaderTitle + " ' is not opened");
                ActualLable("Address block for ' " + HeaderTitle + " ' is not opened", "Pass");
                ExpectedLable("Now checking to Expand Address block");
                log.info("Now checking to Expand Address block");
                AddressHeaders(driver).get(i).click();
                Thread.sleep(1000);
                if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                    log.info("Address block for '  " + HeaderTitle + " ' is now opened");
                    ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is now opened" + HeaderTitle, "Pass");
                    log.info("Now checking to Collapse Address block");
                } else {
                    log.info("Expand and collapse functionality is not working for " + HeaderTitle);
                    ActualLable("Address block for '  " + HeaderTitle + " ' is not opened", "Fail");
                }
            }
        }
        else  {
            AddressHeaders(driver).get(0).click();
            Thread.sleep(1000);
            /*ExpectedLable("Verify assert for the block" + HeaderTitle);
            Assert.assertEquals(HeaderTitle, "Shipping Addresses");
            ActualLable("Successfully verified for " + HeaderTitle, "Pass");*/
            ExpectedLable("Verify " + HeaderTitle + " block is already expanded or not");
            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                ActualLable("Address block for ' " + HeaderTitle + " ' is already opened", "Pass");
            } else {
                log.info("Address block for ' " + HeaderTitle + " ' is not opened");
                ActualLable("Address block for ' " + HeaderTitle + " ' is not opened", "Pass");
                ExpectedLable("Now checking to Expand Address block");
                log.info("Now checking to Expand Address block");
                AddressHeaders(driver).get(i).click();
                Thread.sleep(1000);
                if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                    log.info("Address block for '  " + HeaderTitle + " ' is now opened");
                    ActualLable("Successful, Address block for '  " + HeaderTitle + " ' is now opened" + HeaderTitle, "Pass");
                    log.info("Now checking to Collapse Address block");
                } else {
                    log.info("Expand and collapse functionality is not working for " + HeaderTitle);
                    ActualLable("Address block for '  " + HeaderTitle + " ' is not opened", "Fail");

                }
            }
        }
    }

}
