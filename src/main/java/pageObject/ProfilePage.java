package pageObject;

import GenericLib.ObjectRepository;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }


    public static void AssertVerifyForFieldsOFProfile(WebDriver driver) {

        if (driver.findElements(FirstName).size()>0){
            log.info("Assert is verified for first Name Field");
        }
        else{
            log.info("Assert failed for first Name Field");
        }
        if (driver.findElements(Email_Field).size()>0){
            log.info("Assert is verified for User ID Field");
        }
        else{
            log.info("Assert failed for User ID Field");
        }
        if (driver.findElements(LastName).size()>0){
            log.info("Assert is verified for last Name Field");
        }
        else{
            log.info("Assert failed for last Name Field");
        }
        if (driver.findElements(password).size()>0){
            log.info("Assert is verified for password Field");
        }
        else{
            log.info("Assert failed for password Field");
        }
        if (driver.findElements(phone).size()>0){
            log.info("Assert is verified for phone Field");
        }
        else{
            log.info("Assert failed for phone Field");
        }
        if (driver.findElements(confirmPassword).size()>0){
            log.info("Assert is verified for confirm Password Field");
        }
        else{
            log.info("Assert failed for confirm Password Field");
        }
        if (driver.findElements(Office).size()>0){
            log.info("Assert is verified for Office drop down");
        }
        else{
            log.info("Assert failed for Office drop down");
        }
        if (driver.findElements(Language).size()>0){
            log.info("Assert is verified for Language Field");
        }
        else{
            log.info("Assert failed for Language Field");
        }
        if (driver.findElements(Timezone).size()>0){
            log.info("Assert is verified for Timezone Field");
        }
        else{
            log.info("Assert failed for Timezone Field");
        }
        if (driver.findElements(Save).size()>0){
            log.info("Assert is verified for Save Button");
        }
        else{
            log.info("Assert failed for Save Button");
        }
        if (driver.findElements(ClearAll).size()>0){
            log.info("Assert is verified for Clear All Field");
        }
        else{
            log.info("Assert failed for Clear All Field");
        }

    }

    public static List<WebElement> NoOfMandatoryFields(WebDriver driver) {


        List<WebElement> NoOfMandatoryFields=  driver.findElements(MandatoryFields);
        return NoOfMandatoryFields;
    }
    public static void MandatoryFieldsProfilePage(WebDriver driver) {

        int NoOfMandatory = NoOfMandatoryFields(driver).size();
        log.info("No of mandatory fields in Profile page is " +NoOfMandatory);

        for(int i=0;i<=NoOfMandatory;i++){
            NoOfMandatoryFields(driver).get(i).clear();
            NoOfMandatoryFields(driver).get(i).sendKeys(Keys.TAB);
            List<WebElement> Lablename =driver.findElements(By.xpath("//label"));
            String Lablenames = Lablename.get(i).getText();
            if (driver.findElements(ErrorMessage).size()>0){

                String ErrorMessageFor = driver.findElement(By.xpath("//div[@class='text-danger']")).getText();

                log.info("Error Message for the field "+Lablenames+" is " + ErrorMessageFor);
            }
            else
            {
                log.info("Error message is not shown for the field "+Lablenames);
                log.info("Test case is failed for the field "+Lablenames);
            }

        }

    }

    public static List<WebElement> AddressHeaders(WebDriver driver) {

        List<WebElement> AddressHeaders=  driver.findElements(Address);
        return AddressHeaders;
    }

    public static void VerifyBillingAddress(WebDriver driver) {

        int noOfHeaders = AddressHeaders(driver).size();

        for (int i=0;i<=noOfHeaders-1;i++){

            String HeaderTitle = AddressHeaders(driver).get(i).getText();

            if(i==0){
                try {
                    Assert.assertEquals(HeaderTitle, "Billing Addresses");
                    log.info("Assert is verified for " + HeaderTitle);
                }
                catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                }

            }
            else if(i==1){
                try{
                    Assert.assertEquals(HeaderTitle , "Shipping Addresses");
                    log.info("Assert is verified for " + HeaderTitle);
                }
                catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                }
            }
            else if(i==2){
                try {
                    Assert.assertEquals(HeaderTitle, "Install Addresses");
                    log.info("Assert is verified for " + HeaderTitle);
                }

                catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                }
            }
            else
            {
                log.info("Assert verification is failed for "+ HeaderTitle );
            }
        }


    }

    public static List<WebElement> HeaderOpeninAddress(WebDriver driver){

        List<WebElement> OpeninAddress =  driver.findElements(OpenAddress);
        return OpeninAddress;
    }
    public static void VerifyDefaultOpenBlocks(WebDriver driver) {

        int NoOfOpenBlocks = HeaderOpeninAddress(driver).size();

        if (NoOfOpenBlocks >1){
            log.info("Verification failed for Number of open Addres blockes" );
            log.info("Number of open Addres blockes are " + NoOfOpenBlocks);
            for (int i=0;i<=NoOfOpenBlocks;i++){
                String TitleOfOpenBlock = driver.findElements(TitleForOenBlock).get(i).getText();
                log.info("Title for opened blocks are " + TitleOfOpenBlock);
            }

        }
        else
        {
            String TitleOfOpenBlock = driver.findElement(TitleForOenBlock).getText();
            log.info("Title for opened blocks are " + TitleOfOpenBlock);
        }

    }

    public static List<WebElement> PannelHeading(WebDriver driver) {

        List<WebElement> PannelHeaders=  driver.findElements(PannelHeading);
        return PannelHeaders;
    }

    public static void VerifyExpandCollapse(WebDriver driver){

        int noOfHeaders = AddressHeaders(driver).size();

        for (int i=0;i<=noOfHeaders-1;i++) {

            String HeaderTitle = AddressHeaders(driver).get(i).getText();

            if (i == 0) {
                try {
                    Assert.assertEquals(HeaderTitle, "Billing Addresses");

                    if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                        log.info("Address block for ' " + HeaderTitle + " ' is already opened");
                        log.info("Now checking to Collapse Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            log.info("Expand and collapse functionality is not working");
                        } else {
                            log.info("Expand and collapse functionality is working properly");

                        }
                    } else {
                        log.info("Address block for '  " + HeaderTitle + " ' is not opened");
                        log.info("Now checking to Expand Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                            //log.info("Expand and collapse functionality is working properly");
                            log.info("Address block for '  " + HeaderTitle + " ' is now opened");
                            log.info("Now checking to Collapse Address block");
                            AddressHeaders(driver).get(i).click();
                            Thread.sleep(1000);
                            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                                log.info("Expand and collapse functionality is not working");
                            } else {
                                log.info("Expand and collapse functionality is working properly");
                            }

                        } else {
                            log.info("Expand and collapse functionality is not working");

                        }
                    }

                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                }
            }

            else if (i == 1) {
                try {
                    Assert.assertEquals(HeaderTitle, "Shipping Addresses");

                    if(driver.findElements(By.xpath("//div[contains(text(),'"+HeaderTitle+"')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size()>0){
                        log.info("Address block for ' "+HeaderTitle +  " ' is already opened");
                        log.info("Now checking to Collapse Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if(driver.findElements(By.xpath("//div[contains(text(),'"+HeaderTitle+"')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size()>0){
                            log.info("Expand and collapse functionality is not working");
                        }
                        else{
                            log.info("Expand and collapse functionality is working properly");

                        }
                    }
                    else{
                        log.info("Address block for ' "+HeaderTitle +  " ' is not opened");
                        log.info("Now checking to Expand Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if(driver.findElements(By.xpath("//div[contains(text(),'"+HeaderTitle+"')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size()>0){
                            log.info("Address block for '  " + HeaderTitle + " ' is now opened");
                            log.info("Now checking to Collapse Address block");
                            AddressHeaders(driver).get(i).click();
                            Thread.sleep(1000);
                            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                                log.info("Expand and collapse functionality is not working");
                            } else {
                                log.info("Expand and collapse functionality is working properly");
                            }
                        }
                        else{
                            log.info("Expand and collapse functionality is not working for " + HeaderTitle);

                        }
                    }

                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                }

            }

            else if (i == 2) {
                try {
                    Assert.assertEquals(HeaderTitle, "Install Addresses");

                    if(driver.findElements(By.xpath("//div[contains(text(),'"+HeaderTitle+"')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size()>0){
                        log.info("Address block for ' "+HeaderTitle +  " ' is already opened");
                        log.info("Now checking to Collapse Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if(driver.findElements(By.xpath("//div[contains(text(),'"+HeaderTitle+"')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size()>0){
                            log.info("Expand and collapse functionality is not working");
                        }
                        else{
                            log.info("Expand and collapse functionality is working properly");

                        }
                    }
                    else{
                        log.info("Address block for ' "+HeaderTitle +  " ' is not opened");
                        log.info("Now checking to Expand Address block");
                        AddressHeaders(driver).get(i).click();
                        Thread.sleep(1000);
                        if(driver.findElements(By.xpath("//div[contains(text(),'"+HeaderTitle+"')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size()>0){
                            log.info("Address block for '  " + HeaderTitle + " ' is now opened");
                            log.info("Now checking to Collapse Address block");
                            AddressHeaders(driver).get(i).click();
                            Thread.sleep(1000);
                            if (driver.findElements(By.xpath("//div[contains(text(),'" + HeaderTitle + "')]/parent::div/parent::div/parent::a/parent::div/following-sibling::div[@class='panel-collapse collapse in']")).size() > 0) {
                                log.info("Expand and collapse functionality is not working");
                            } else {
                                log.info("Expand and collapse functionality is working properly");
                            }
                        }
                        else{
                            log.info("Expand and collapse functionality is not working");

                        }
                    }

                } catch (Exception e) {
                    log.info("Assert verification is failed for " + HeaderTitle);
                }

            }
            else
            {
                log.info("Assert verification is failed for "+ HeaderTitle );
            }
        }
    }

    public static void OutLookAccess(WebDriver driver) throws InterruptedException, AWTException{

        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        driver.get("https://webemail.ap.dimensiondata.com/owa/auth/logon.aspx?replaceCurrent=1&url=https%3a%2f%2fwebemail.ap.dimensiondata.com%2fowa%2f");

        Thread.sleep(1000);
        driver.findElement(By.id("rdoPrvt")).click();
        driver.findElement(By.id("chkBsc")).click();
        driver.findElement(By.id("username")).sendKeys("t.mirasipally");
        driver.findElement(By.id("password")).sendKeys("Chaitu@0068");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public static void VerifyEmailInOutLook(WebDriver driver) throws InterruptedException, AWTException {
        OutLookAccess(driver);
        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='lnkHdrcheckmessages']/img")).click();
        if(driver.findElements(By.xpath("//td[@class='frst']/h1/a[contains(text(),'Address Delete Request')]")).size()>0){
            driver.findElement(By.xpath("//td[@class='frst']/h1/a")).click();
            String Subject = driver.findElement(By.xpath("//td[@class='sub']")).getText();
            try{
                Assert.assertEquals(Subject , "Address Delete Request");
                log.info("Assert is verified " );
                String MailSender=driver.findElement(By.xpath("//span[@class='rwRRO']")).getText();
                String[] splited = MailSender.split("\\s+");
                String split_one= splited[0];
                log.info("Email sender is  " + split_one);
                obje.repository(driver);
                String email = obje.obj.getProperty("email");
                Assert.assertEquals(split_one , email);
                log.info("Assert Verified for Email sender" );

            }
            catch (Exception e){

                log.info("Assert verification failed " );
            }
        }
        else{
            log.info("Email is not received for address request" );
        }

    }

    public static List<WebElement> RequestDeleteElement(WebDriver driver) {

        List<WebElement> DeleteButton=  driver.findElements(By.xpath("//button[contains(text(),'Request Delete')]"));
        return DeleteButton;
    }

    public static void RequestDeleteFunctionality(WebDriver driver){

        RequestDeleteElement(driver).get(1).click();
        log.info("Clicked on Request Delete button " );
        driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        log.info("Clicked on OK button to confirm " );

    }

    public static List<WebElement> LabelSymbol(WebDriver driver) {

        List<WebElement> Label=  driver.findElements(By.xpath("//label[@class='is-check-radio-label']"));
        return Label;
    }

    public static void AssertVerifyForDefaultAddress(WebDriver driver){
        //String Defaultxpath =LabelSymbol(driver).get(0)+"/i[@class='is-check-radio-helper on']";
        String Defaultxpath = "(//label[@class='is-check-radio-label'])[1]/i[@class='is-check-radio-helper on']";
        By PannelHeading = By.xpath(Defaultxpath);
        log.info("Xpath" + PannelHeading);
        if (driver.findElements(PannelHeading).size() > 0) {
            log.info("Default Address is located First in the address list");
            String NextItemXpath = "(//label[@class='is-check-radio-label'])[2]";
            log.info("Clicking on next item to make default address");
            By NextItem = By.xpath(NextItemXpath);
            driver.findElement(NextItem).click();
            log.info("Clicked on Second address to make Default");
            boolean selectStatus = driver.findElement(NextItem).isEnabled();
            if (selectStatus == true) {
                log.info("Default address functionallity is verified");
            } else {
                log.info("Failed: Default address functionality is not working");
            }
        } else {
            log.info(" Default Address is not located at First in the address list ");
            log.info(" Making first Address as Default  ");
            driver.findElement(By.xpath("(//label[@class='is-check-radio-label'])[1]")).click();
            log.info("Clicked on First address to make Default");
            boolean selectStatus = driver.findElement(By.xpath("(//label[@class='is-check-radio-label'])[1]")).isEnabled();
            if (selectStatus == true) {
                log.info("Default address functionallity is verified");
            } else {
                log.info("Failed: Default address functionality is not working");
            }


        }


    }
}
