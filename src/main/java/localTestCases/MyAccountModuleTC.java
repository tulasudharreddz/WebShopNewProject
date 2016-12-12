package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProfilePage;

import java.awt.*;
import java.io.IOException;

import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 14-Nov-16.
 */
public class MyAccountModuleTC extends Browser {
    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");
    static protected DataDriven excel = new DataDriven();

    private WebDriver driver;
    private Sheet sheet;
    private WritableSheet wsheet;

    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();
    }


    @BeforeMethod
    public void Url() throws WriteException, IOException, BiffException {
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");
        log.info("URL entered in browser");

    }


    /*WS_TC_34: AssertVerifyForDefaultAddress the GUI of  'User Profile' page
    a) AssertVerifyForDefaultAddress the title
    b) AssertVerifyForDefaultAddress the display of controls

    Steps:
    a) The title should be displayed as 'Profile'
    b) Following controls should be displayed in 'Profile Page'
    Textboxes Controls: First Name, Last Name, Email, Phone, Password, Confirm Password, Office Section
    Label Names - Department/Division, Emp ID, Roles
    Dropdown Controls - Language, Regional Setting, Font Size for Order Extracts, Lead time in order extracts
    Calendar Control - Time zone
    Button Controls - Clear All, Save*/

    @Test
    public void WS_TC_34() throws InterruptedException, IOException, WriteException, BiffException {
        obje.repository(driver);
        DataDriven.ReportStartup(34);
        log.info("WS_TC_30: AssertVerifyForDefaultAddress the GUI of  'User Profile' page a) AssertVerifyForDefaultAddress the title  b) AssertVerifyForDefaultAddress the display of controls");
        log.debug("Expected Result");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        HomePage.ClickOnProfile(driver);
        log.info("Clicked on User Profile option under My Account ");
        HomePage.AssertVerifyForTitleProfile(driver);
        ProfilePage.AssertVerifyForFieldsOFProfile(driver);

    }

    /*
    WS_TC_35: Validate the Save button functionality in Profile Page
    a) Click on Save button after enetring data in all mandatory fields
    b) Click on Save button without entering any of the mandatory fields
    */


    @Test
    public void WS_TC_35() throws InterruptedException, IOException, WriteException, BiffException {
        obje.repository(driver);
        DataDriven.ReportStartup(35);
        log.info("WS_TC_35: Validate the Save button functionality in Profile Page");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.ClickOnProfile(driver);
        log.info("Clicked on User Profile option under My Account ");
        Thread.sleep(1000);
        HomePage.AssertVerifyForTitleProfile(driver);
        Thread.sleep(1000);
        ProfilePage.AssertVerifyForFieldsOFProfile(driver);
        Thread.sleep(1000);
        ProfilePage.MandatoryFieldsProfilePage(driver);

    }


   /* WS_TC_37: AssertVerifyForDefaultAddress the GUI of  'Addresses' page
    a) The title should be displayed as 'Addresses'
    b) Following accordion headers should be displayed in 'Addresses' page
    1. Billing Addresses  2. Delivery Addresses  3.Install Addresses
    c) By default the billing address header should be in open state
   */

    @Test
    public void WS_TC_37() throws InterruptedException, IOException, WriteException, BiffException {
        obje.repository(driver);
        DataDriven.ReportStartup(37);
        log.info("WS_TC_37: AssertVerifyForDefaultAddress the GUI of  'Addresses' page");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.ClickonMyAccount(driver);
        Thread.sleep(1000);
        HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
        //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.VerifyAddressHeaders(driver);
        Thread.sleep(1000);
        ProfilePage.VerifyDefaultOpenBlocks(driver);
        log.info("WS_TC_37: The GUI of  'Addresses' page is verified");
        StepLable("WS_TC_37: is successfully verified");

    }

    /*
    WS_TC_38: Validate the expand & collapse functionality for the following headers
    1. Click on Billing Address
    2. Click on Delivery Address
    3. Click on Installation Address
    */

    @Test
    public void WS_TC_38() throws InterruptedException, IOException, WriteException, BiffException {
        obje.repository(driver);
        DataDriven.ReportStartup(38);
        log.info("WS_TC_38: Validate the expand & collapse functionality for the following headers");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.ClickonMyAccount(driver);
        Thread.sleep(1000);
        HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
        //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.VerifyExpandCollapse(driver);
        log.info("WS_TC_38: Expand & Collapse functionality is verified");
        StepLable("WS_TC_38: is successfully verified");

    }
    /*
    WS_TC_41: Validate the 'Request Delete' button functionality
    a. Click on Request Delete button
    b. AssertVerifyForDefaultAddress the 'Delete Request email' content
    */
    @Test
    public void WS_TC_41() throws InterruptedException, IOException, AWTException, WriteException, BiffException {
        obje.repository(driver);
        DataDriven.ReportStartup(41);
        log.info("WS_TC_41: Validate the 'Request Delete' button functionality");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.ClickonMyAccount(driver);
        Thread.sleep(1000);
        HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
        //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.RequestDeleteFunctionality(driver);
        Thread.sleep(1000);
        ProfilePage.VerifyEmailInOutLook(driver);
        log.info("WS_TC_41: 'Request Delete' button functionality is verified");
        StepLable("WS_TC_41: is successfully verified");
    }
    /*
    WS_TC_42: Validate the default billing address setup functionality
    a) Setup the  default address using radio button
    b) Setup up default address without selecting any of the radio buttons against billing address
    */

    @Test
    public void WS_TC_42() throws IOException, InterruptedException, WriteException, BiffException {

        obje.repository(driver);
        DataDriven.ReportStartup(42);
        log.info("WS_TC_42: Validate the default billing address setup functionality");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.ClickonMyAccount(driver);
        Thread.sleep(1000);
        HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
        //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.AssertVerifyForDefaultAddress(driver);
        StepLable("WS_TC_42: is successfully verified");
    }
    /*
    WS_TC_43: Verify the GUI of "Request new address page
    a) Click on the Request New address button against a billing
    b) Verify the GUI of Create New Billing Address Page
    */
    @Test
    public void WS_TC_43() throws IOException, InterruptedException, WriteException, BiffException {
        obje.repository(driver);
        DataDriven.ReportStartup(43);
        log.info("WS_TC_43: Verify the GUI of Request new address page");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.ClickonMyAccount(driver);
        Thread.sleep(1000);
        HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
        //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.ClickonNewBillingAddress(driver);
        Thread.sleep(1000);
        ProfilePage.AssertVerifyForLable(driver);
        log.info("WS_TC_43: GUI of Request new address lables are successfully verified");
        StepLable("WS_TC_43: is successfully verified");
    }

    /*
    WS_TC_43: Validate the Create New Billing Address functionality
    a) Click on Save button by entering all mandatory fields.
    b) Click on Save button without enetering any of the mandatory fields
    */

    @Test
    public void WS_TC_44() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        obje.repository(driver);
        DataDriven.ReportStartup(44);
        log.info("WS_TC_43: Verify the GUI of Request new address page");
        StepLable("WS_TC_44: Validate the Create New Billing Address functionality");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        log.info("Login Functionality is completed");
        HomePage.ClickonMyAccount(driver);
        log.info("Clicked on My Account");
        Thread.sleep(1000);
        HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
        //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.ClickonNewBillingAddress(driver);
        Thread.sleep(1000);
        ProfilePage.VerifySaveNewAddressFunctionalityWithOutData(driver);
        Thread.sleep(1000);
        ProfilePage.VerifySaveNewAddressFunctionalityWithData(driver);
        Thread.sleep(1000);
        ProfilePage.EmailVerificationForNewAddress(driver);
        StepLable("WS_TC_44: Successfully verified Create New Billing Address functionality");

    }


}
