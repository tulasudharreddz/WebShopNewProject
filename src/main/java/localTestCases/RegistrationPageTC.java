package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.RegistrationPage;

import java.awt.*;
import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;

/**
 * Created by t.mirasipally on 25-Jan-17.
 */
public class RegistrationPageTC extends Browser {

    static ObjectRepository obje = new ObjectRepository();
    static Logger log = Logger.getLogger("Testing Cases");

    protected WebDriver driver;
    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException { driver=getDriver();   }

    @Test
    public void WS_TC_80() throws IOException, WriteException, InterruptedException, AWTException {
        try {
            DataDriven.ReportStartup(80);
            HomePage.ClickOnRegisterHereLink(driver);
            RegistrationPage.SingleUserRegistration(driver);
            RegistrationPage.AssertVerifyForRegistrationUserList(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_81() throws IOException, WriteException, InterruptedException, AWTException {
        try {
            DataDriven.ReportStartup(81);
            HomePage.ClickOnRegisterHereLink(driver);
            RegistrationPage.SingleUserRegistration(driver);
            RegistrationPage.AssertVerifyForRegistrationUserList(driver);
            RegistrationPage.ContentVerifyForRegistrationUserList(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_83() throws IOException, WriteException, InterruptedException, AWTException {
        try {
            DataDriven.ReportStartup(83);
            HomePage.ClickOnRegisterHereLink(driver);
            RegistrationPage.SingleUserRegistration(driver);
            RegistrationPage.AssertVerifyForRegistrationUserList(driver);
            RegistrationPage.VerifyPrivacyPolicylink(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }

    @Test
    public void WS_TC_84() throws IOException, WriteException, InterruptedException, AWTException {
        try {
            DataDriven.ReportStartup(84);
            HomePage.ClickOnRegisterHereLink(driver);
            RegistrationPage.SingleUserRegistration(driver);
            RegistrationPage.AssertVerifyForRegistrationUserList(driver);
            RegistrationPage.VerifyTermsOfUselink(driver);
        } catch (AssertionError e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        } catch (Exception e) {
            String error = "Exception " + e.getClass().getSimpleName();
            ActualLable(error, "Fail");
        }
    }


}
