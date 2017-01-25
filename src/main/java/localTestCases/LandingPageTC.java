package localTestCases;

import java.io.IOException;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import pageObject.ForgotPassword;
import pageObject.HomePage;
import pageObject.LoginPage;

import static GenericLib.DataDriven.*;


public class LandingPageTC extends Browser
{

	DataDriven excel = new DataDriven();
	ObjectRepository ob = new ObjectRepository();
	Logger log=Logger.getLogger("Testing Cases");

	private WebDriver driver;
	private Sheet sheet;
	private WritableSheet wsheet;

	@BeforeClass
	public void setUp() throws WriteException, IOException, BiffException {
		driver=getDriver();
		//sheet = excel.ReadSheet(sheet);
	}
		/*WS_TC_01: AssertVerifyForDefaultAddress the launch of Login page & and its content

	Disc:  The 'Buyer login' page should be displayed with the following sections
		Sections:
		1. Login
		2. Reset Password
		3. Request Registration
	*/

	@Test
	public void WS_TC_01() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		//check that user able to login with valid credentials or not
		try{
			DataDriven.ReportStartup(1);
			LoginPage.PageTitle(driver);
			StepLable("Verify links available on landing page");
			LoginPage.LoginPageTitle(driver);
			LoginPage.ResetPasswordAssert(driver);
			LoginPage.RegisterAsert(driver);
		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}

	/*WS_TC_02:Verify the GUI of "Login" Section
	The controls mentioned below should be displayed in 'Buyer Login' Page
	a) Dropdown- Language
	b) Textbox - User Name & Password
	c) Button - Login*/
	@Test
	public void WS_TC_02() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			//check that user able to login with valid credentials or not
			DataDriven.ReportStartup(2);
			LoginPage.LoginFieldsAssertVerify(driver);

		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}

	/*	  
	WS_TC_03: Validate the Login functionality
	a) Select a value from "Select your language" dropdown
	b) Enter valid User Name and Password

	Expe result : Login should be successful and user should be redirected to 'Home' page
	 */
	@Test
	public void WS_TC_03() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			//check that user able to login with valid credentials or not
			Thread.sleep(2000);
			DataDriven.ReportStartup(3);
			LoginPage.PageTitle(driver);
			LoginPage.Loginfunctionality(driver);
			HomePage.PageTitle(driver);
		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}

	/* WS_TC_04: AssertVerifyForDefaultAddress the GUI of 'Reset Password' Section
	 	Below controls should be displayed in Reset Password' Page
		Textbox - User Name
		Label Text - "if you forgot your password, please enter your user name below and click on email password"
		Checkbox - I am not a Robot
		Captcha
		Button - Email Password	 */
	@Test
	public void WS_TC_04() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			//check that user able to login with valid credentials or not
			DataDriven.ReportStartup(4);
			LoginPage.PageTitle(driver);
			LoginPage.ResetPasswordAssert(driver);
			LoginPage.ClickOnResetPasswordLink(driver);
			ForgotPassword.AssertVerifyForResetPassword(driver);

		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}
	/*
	Validate the Email Password functionality
	a) Enter the valid 'User Name' & valid 'Captcha' 
	b) Enter the valid 'User Name' & Invalid 'Captcha' 
	c) Enter the Invalid 'User Name' & valid 'Captcha'
	d) Enter the Invalid 'User Name' & Invalid 'Captcha'
	Note: - Valid User Name  = user�s email address

	 */
	@Test
	public void WS_TC_05() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			DataDriven.ReportStartup(5);
			LoginPage.ClickOnResetPasswordLink(driver);
			ForgotPassword.ErrorMessageForEmail(driver);
		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}

	/*WS_TC_06: AssertVerifyForDefaultAddress the GUI of "Request Registration" Section

	The "Request Registration" Section should be displayed with the following
	a) Label Text - �If your company is not yet a registered client of Dimension Data, or you would like user-access to the Dimension Data Store for your registered company, click below to request registration�  
	c) Button - Request Registration

	 */

	@Test
	public void WS_TC_06() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			DataDriven.ReportStartup(6);
			Thread.sleep(2000);
			StepLable("Assert Verify For the GUI of 'Request Registration'  Section");
			ExpectedLable("Check Label Text available on Landing page");
			String labeltext= LoginPage.LableForRegistration(driver).getText();

			log.info("Label Text for Request registration  is  " +labeltext);
			ActualLable("Successfully verified Label Text "+labeltext,"Pass");
			//check that Button for Request Registration is exist or not.
			ExpectedLable("Check Registration link is available on Landing page");
			log.info("Request Registration is exist with link " + LoginPage.Register(driver).getText());
			ActualLable("Successfully verified Registration link on home page ","Pass");
			log.info("test cases is verified and Passed");

		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}

	// AssertVerifyForDefaultAddress that all the static links are working or not.
	@Test
	public void WS_TC_07() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {DataDriven.ReportStartup(6);
		try{

			DataDriven.ReportStartup(7);
			StepLable("Verify All static links on Landing page");

			LoginPage.AsertVerifyForAboutUSLinkHomePage(driver);
			LoginPage.AsertVerifyForNEWSLinkHomePage(driver);
			LoginPage.AsertVerifyForCareerLinkHomePage(driver);
			log.info("Asert for Career link is verified");
			LoginPage.AsertVerifyForSafeHarborPolicyLinkHomePage(driver);
			log.info("Asert for Safe Harbor Policy link is verified");
			LoginPage.AsertVerifyForFAQLinkHomePage(driver);
			log.info("Asert for ServiceCenter link is verified");
			LoginPage.AsertVerifyForPrivacyPolicyLinkHomePage(driver);
			log.info("Asert for Privacy Policy link is verified");
			LoginPage.AsertVerifyForTermsConditionsLinkHomePage(driver);
			log.info("Asert for Terms Conditions link is verified");
			LoginPage.AsertVerifyForCookiePolicyLinkHomePage(driver);
			log.info("Asert for Cookie Policy link is verified");
			LoginPage.AsertVerifyForContactDDLinkHomePage(driver);
			log.info("Asert for ContactDD link is verified");
			StepLable("WS_TC_07:Successfully verified all static links");
			//LoginPage.AsertVerifyForFooterLinksHomePage(driver);
		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}

}
