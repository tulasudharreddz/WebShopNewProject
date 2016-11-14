package localTestCases;

import GenericLib.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProfilePage;

import java.io.IOException;

/**
 * Created by t.mirasipally on 14-Nov-16.
 */
public class MyAccountModuleTC extends Browser {


    Logger log = Logger.getLogger("Testing Cases");

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver=getDriver();
    }


    /*WS_TC_34: Verify the GUI of  'User Profile' page
    a) Verify the title
    b) Verify the display of controls

    Steps:
    a) The title should be displayed as 'Profile'
    b) Following controls should be displayed in 'Profile Page'
    Textboxes Controls: First Name, Last Name, Email, Phone, Password, Confirm Password, Office Section
    Label Names - Department/Division, Emp ID, Roles
    Dropdown Controls - Language, Regional Setting, Font Size for Order Extracts, Lead time in order extracts
    Calendar Control - Time zone
    Button Controls - Clear All, Save*/

    @Test
    public void WS_TC_34() throws InterruptedException, IOException {

        log.info("WS_TC_30: Verify the GUI of  'User Profile' page a) Verify the title  b) Verify the display of controls");
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
    public void WS_TC_35() throws InterruptedException, IOException {

        log.info("WS_TC_35: Validate the Save button functionality in Profile Page");
        Thread.sleep(2000);
        LoginPage.Loginfunctionality(driver);
        HomePage.ClickOnProfile(driver);
        log.info("Clicked on User Profile option under My Account ");
        HomePage.AssertVerifyForTitleProfile(driver);
        ProfilePage.AssertVerifyForFieldsOFProfile(driver);
        ProfilePage.MandatoryFieldsProfilePage(driver);

    }


   /* WS_TC_37: Verify the GUI of  'Addresses' page
    a) The title should be displayed as 'Addresses'
    b) Following accordion headers should be displayed in 'Addresses' page
    1. Billing Addresses  2. Delivery Addresses  3.Install Addresses
    c) By default the billing address header should be in open state
   */

    @Test
    public void WS_TC_37() throws InterruptedException, IOException {
        log.info("WS_TC_37: Verify the GUI of  'Addresses' page");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.MyAccountMenuonHomePage(driver).click();
        Thread.sleep(1000);
        HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.VerifyBillingAddress(driver);
        Thread.sleep(1000);
        ProfilePage.VerifyDefaultOpenBlocks(driver);
        log.info("WS_TC_37: The GUI of  'Addresses' page is verified");

    }

    /*
    WS_TC_38: Validate the expand & collapse functionality for the following headers
    1. Click on Billing Address
    2. Click on Delivery Address
    3. Click on Installation Address
    */

    @Test
    public void WS_TC_38() throws InterruptedException, IOException {

        log.info("WS_TC_38: Validate the expand & collapse functionality for the following headers");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.MyAccountMenuonHomePage(driver).click();
        Thread.sleep(1000);
        HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);
        ProfilePage.VerifyExpandCollapse(driver);
        log.info("WS_TC_38: Expand & Collapse functionality is verified");

    }
    /*
    WS_TC_41: Validate the 'Request Delete' button functionality
    a. Click on Request Delete button
    b. Verify the 'Delete Request email' content
    */
    @Test
    public void WS_TC_41() throws InterruptedException, IOException {

        log.info("WS_TC_38: Validate the expand & collapse functionality for the following headers");
        Thread.sleep(1000);
        LoginPage.Loginfunctionality(driver);
        Thread.sleep(1000);
        HomePage.MyAccountMenuonHomePage(driver).click();
        Thread.sleep(1000);
        HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
        Thread.sleep(1000);


    }




}
