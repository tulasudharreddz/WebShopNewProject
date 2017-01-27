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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.*;

import java.awt.*;
import java.io.IOException;

import static GenericLib.DataDriven.*;

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
        try{
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
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    /*
    WS_TC_35: Validate the Save button functionality in Profile Page
    a) Click on Save button after enetring data in all mandatory fields
    b) Click on Save button without entering any of the mandatory fields
    */


    @Test
    public void WS_TC_35() throws InterruptedException, IOException, WriteException, BiffException {
        try{
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
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }


   /* WS_TC_37: AssertVerifyForDefaultAddress the GUI of  'Addresses' page
    a) The title should be displayed as 'Addresses'
    b) Following accordion headers should be displayed in 'Addresses' page
    1. Billing Addresses  2. Delivery Addresses  3.Install Addresses
    c) By default the billing address header should be in open state
   */

    @Test
    public void WS_TC_37() throws InterruptedException, IOException, WriteException, BiffException {
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(37);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(1000);
            HomePage.ClickonMyAccount(driver);
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);

            Thread.sleep(1000);
            ProfilePage.VerifyAddressHeaders(driver);
            Thread.sleep(1000);
            ProfilePage.VerifyDefaultOpenBlocks(driver);
            log.info("WS_TC_37: The GUI of  'Addresses' page is verified");
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }

    }

    /*
    WS_TC_38: Validate the expand & collapse functionality for the following headers
    1. Click on Billing Address
    2. Click on Delivery Address
    3. Click on Installation Address
    */

    @Test
    public void WS_TC_38() throws InterruptedException, IOException, WriteException, BiffException {
        try{
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
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }

    }
    /*
    WS_TC_41: Validate the 'Request Delete' button functionality
    a. Click on Request Delete button
    b. AssertVerifyForDefaultAddress the 'Delete Request email' content
    */
    @Test
    public void WS_TC_41() throws InterruptedException, IOException, AWTException, WriteException, BiffException {
        try {
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

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }
    /*
    WS_TC_42: Validate the default billing address setup functionality
    a) Setup the  default address using radio button
    b) Setup up default address without selecting any of the radio buttons against billing address
    */

    @Test
    public void WS_TC_42() throws IOException, InterruptedException, WriteException, BiffException {
        try {
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

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }
    /*
    WS_TC_43: Verify the GUI of "Request new address page
    a) Click on the Request New address button against a billing
    b) Verify the GUI of Create New Billing Address Page
    */
    @Test
    public void WS_TC_43() throws IOException, InterruptedException, WriteException, BiffException {
        try{
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
            ProfilePage.ClickonNewAddress(driver,0);
            Thread.sleep(1000);
            ProfilePage.AssertVerifyForLable(driver,"Billing Address");

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }

    /*
    WS_TC_43: Validate the Create New Billing Address functionality
    a) Click on Save button by entering all mandatory fields.
    b) Click on Save button without enetering any of the mandatory fields
    */

    @Test
    public void WS_TC_44() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try{
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
            ProfilePage.ClickonNewAddress(driver,0);
            Thread.sleep(1000);
            ProfilePage.VerifySaveNewAddressFunctionalityWithOutData(driver);
            Thread.sleep(1000);
            ProfilePage.VerifySaveNewAddressFunctionalityWithData(driver);
            Thread.sleep(1000);
            ProfilePage.EmailVerificationForNewAddress(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }

    }

    @Test
    public void WS_TC_47() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(47);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(1000);
            HomePage.ClickonMyAccount(driver);
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            ProfilePage.ExpandAddress(driver,"Shipping Addresses");
            ProfilePage.RequestDeleteFunctionality(driver);
            Thread.sleep(1000);
            ProfilePage.VerifyEmailInOutLook(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }

    @Test
    public void WS_TC_48() throws IOException, InterruptedException, WriteException, BiffException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(48);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(1000);
            HomePage.ClickonMyAccount(driver);
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
            ProfilePage.ExpandAddress(driver,"Shipping Addresses");
            Thread.sleep(1000);
            ProfilePage.AssertVerifyForDefaultAddress(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }

    @Test
    public void WS_TC_49() throws IOException, InterruptedException, WriteException, BiffException {
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(49);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(1000);
            HomePage.ClickonMyAccount(driver);
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
            Thread.sleep(1000);
            ProfilePage.ClickonNewAddress(driver,1);
            Thread.sleep(1000);
            ProfilePage.AssertVerifyForLable(driver,"Shipping Address");

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_50() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(50);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            log.info("Login Functionality is completed");
            HomePage.ClickonMyAccount(driver);
            log.info("Clicked on My Account");
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
            Thread.sleep(1000);
            ProfilePage.ClickonNewAddress(driver,1);
            Thread.sleep(1000);
            ProfilePage.VerifySaveNewAddressFunctionalityWithOutData(driver);
            Thread.sleep(1000);
            ProfilePage.VerifySaveNewAddressFunctionalityWithData(driver);
            Thread.sleep(1000);
            ProfilePage.EmailVerificationForNewAddress(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_53() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(53);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(1000);
            HomePage.ClickonMyAccount(driver);
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            ProfilePage.ExpandAddress(driver,"Install Addresses");
            ProfilePage.RequestDeleteFunctionality(driver);
            Thread.sleep(1000);
            ProfilePage.VerifyEmailInOutLook(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }

    @Test
    public void WS_TC_54() throws IOException, InterruptedException, WriteException, BiffException {
        try {
            obje.repository(driver);
            DataDriven.ReportStartup(54);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(1000);
            HomePage.ClickonMyAccount(driver);
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
            ProfilePage.ExpandAddress(driver,"Install Addresses");
            Thread.sleep(1000);
            ProfilePage.AssertVerifyForDefaultAddress(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }

    @Test
    public void WS_TC_55() throws IOException, InterruptedException, WriteException, BiffException {
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(55);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            Thread.sleep(1000);
            HomePage.ClickonMyAccount(driver);
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
            Thread.sleep(1000);
            ProfilePage.ClickonNewAddress(driver,2);
            Thread.sleep(1000);
            ProfilePage.AssertVerifyForLable(driver,"Install Address");

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }

    @Test
    public void WS_TC_56() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try{
            obje.repository(driver);
            DataDriven.ReportStartup(56);
            Thread.sleep(1000);
            LoginPage.Loginfunctionality(driver);
            log.info("Login Functionality is completed");
            HomePage.ClickonMyAccount(driver);
            log.info("Clicked on My Account");
            Thread.sleep(1000);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,1);
            //HomePage.MyAccountMenuDropDownListonHomePage(driver).get(1).click();
            Thread.sleep(1000);
            ProfilePage.ClickonNewAddress(driver,2);
            Thread.sleep(1000);
            ProfilePage.VerifySaveNewAddressFunctionalityWithOutData(driver);
            Thread.sleep(1000);
            ProfilePage.VerifySaveNewAddressFunctionalityWithData(driver);
            Thread.sleep(1000);
            ProfilePage.EmailVerificationForNewAddress(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");

        }
    }

    @Test
    public void WS_TC_155() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try{
            DataDriven.ReportStartup(155);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,2);
            OrdersPage.VerifyHeaderInformationAtOrderLevel(driver);
            OrdersPage.VerifyExpandAndCollapse(driver);
            OrdersPage.VerifyTheOrdersSequenceFunctionality(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_156() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try{
            DataDriven.ReportStartup(156);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,2);
            OrdersPage.VerifyFilterWithDaterange(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_157() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try{
            DataDriven.ReportStartup(157);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver,2);
            OrdersPage.VerificationOfOrderExpandForFirstProduct(driver);
            OrdersPage.VerifyLinksInOrdersPage(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_158() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try{
            DataDriven.ReportStartup(158);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            ShoppingCart.DeleteExistItem(driver);
            HomePage.MovingToCategory(driver);
            ProductSearchPage.AddFlagTwoProduct(driver,1);
            Double ExpectedInstallationCost  =CheckOutPage.VerifyStatusForInstallationServices(driver);
            Double ActualInstallationCost  =OrdersPage.SearchCreatedOrderInOrdersPage(driver);
            ExpectedLable("Now Verify 'Installation Charges' are same on both the pages or not ..?");
            if(ExpectedInstallationCost==ActualInstallationCost){
                ActualLable("Verification is successful, 'Installation Charges' are same on both the pages  ", "Pass");
            }else{ ActualLable("Verification Failed, 'Installation Charges' are different in both the pages  ", "Fail"); }
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_160() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try {
            DataDriven.ReportStartup(160);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver, 2);
            OrdersPage.VerificationOfOrderExpandForFirstProduct(driver);
            //OrdersPage.VerificationOfRequestReturnButtonAssert(driver);
            OrdersPage.VerifyRequestReturnPage(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_161() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try {
            DataDriven.ReportStartup(161);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver, 2);
            OrdersPage.VerificationOfOrderExpandForFirstProduct(driver);
            OrdersPage.VerifyRequestReturnPage(driver);
            OrdersPage.VerifyRequestReturnPageContent(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_163() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try {
            DataDriven.ReportStartup(163);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver, 2);
            OrdersPage.VerificationOfOrderExpandForFirstProduct(driver);
            OrdersPage.VerifyRequestReturnPage(driver);
            OrdersPage.VerifyQuantityFunctionalityinRequestReturn(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    @Test
    public void WS_TC_164() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try {
            DataDriven.ReportStartup(164);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver, 2);
            OrdersPage.VerificationOfOrderExpandForFirstProduct(driver);
            OrdersPage.VerifyRequestReturnPage(driver);
            OrdersPage.VerifyCloseButtonFunctionalityinRequestReturn(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
    @Test
    public void WS_TC_165() throws IOException, InterruptedException, AWTException, WriteException, BiffException {
        try {
            DataDriven.ReportStartup(165);
            LoginPage.Loginfunctionality(driver);
            HomePage.ClickonMyAccount(driver);
            HomePage.SelectSubMenuOptUnderMyAccount(driver, 2);
            OrdersPage.VerificationOfOrderExpandForFirstProduct(driver);
            OrdersPage.VerifyRequestReturnPage(driver);
            OrdersPage.VerifySubmitButtonFunctionalityinRequestReturnPage(driver);

        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }
}
