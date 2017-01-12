package localTestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import GenericLib.AlertHandle;
import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import pageObject.ForgotPassword;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;

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
	@BeforeMethod
	public void Url() throws WriteException, IOException, BiffException {
		driver.get("https://directqa2.dimensiondata.com/Webshop/login");
		log.info("URL entered in browser");
	}
	@AfterMethod
	public void ResultStatus() throws WriteException { ReportResult();}

		/*TC01: AssertVerifyForDefaultAddress the launch of Login page & and its content

	Disc:  The 'Buyer login' page should be displayed with the following sections
		Sections:
		1. Login
		2. Reset Password
		3. Request Registration
	*/

	@Test
	public void TC01() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		//check that user able to login with valid credentials or not
		try{
			Thread.sleep(3000);
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

	/*	  
	TC02: Validate the Login functionality
	a) Select a value from "Select your language" dropdown
	b) Enter valid User Name and Password

	Expe result : Login should be successful and user should be redirected to 'Home' page
	 */
	@Test
	public void TC02() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			//check that user able to login with valid credentials or not
			Thread.sleep(2000);
			DataDriven.ReportStartup(2);
			LoginPage.PageTitle(driver);
			log.info("Assert verified");
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

	/*
	 TC03: AssertVerifyForDefaultAddress the GUI of 'Reset Password' Section
	 Below controls should be displayed in Reset Password' Page
		Textbox - User Name
		Label Text - "if you forgot your password, please enter your user name below and click on email password"
		Checkbox - I am not a Robot
		Captcha
		Button - Email Password

	 */
	@Test
	public void TC03() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			//check that user able to login with valid credentials or not
			Thread.sleep(2000);
			DataDriven.ReportStartup(3);
			LoginPage.PageTitle(driver);
			LoginPage.ResetPasswordAssert(driver);
			ExpectedLable("Click on Reset password link");
			LoginPage.ResetPasswordLink(driver).click();
			ActualLable("Successfully clicked on reset password link","Pass");
			Thread.sleep(3000);
			ExpectedLable("Enter user name into email blank");
			LoginPage.ResetPasswordEmail(driver).sendKeys("t.mirasipally@dimensiondata.com");
			ActualLable("Successfully entered user name into email blank","Pass");
			//Assert.assertNotNull(RegistrationPage.AmNotRobot(driver));
			ExpectedLable("Click on AmNotRobot check box");
			if(driver.findElements(By.xpath("//div[@class='recaptcha-checkbox-checkmark']")).size()>0){
				RegistrationPage.AmNotRobot(driver).click();
				ActualLable("Successfully clicked on AmNotRobot check box","Pass");
				ExpectedLable("Wait till robot functionality completed");
				Thread.sleep(15000);
				ActualLable("Waiting completed for robot functionality","Pass");
				ExpectedLable("Click on Reset password button check box");
				LoginPage.SubmitOnResetPassword(driver).click();
				ActualLable("Successfully clicked on Reset password button","Pass");
			}
			else{
				log.info("Robot Check is not visible, test case fail");
				ActualLable("Failed to execute Robot functionality","Fail");
			}
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
	public void TC04() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			DataDriven.ReportStartup(4);
			ExpectedLable("Click on Reset password link");
			LoginPage.ResetPasswordLink(driver).click();
			ActualLable("Successfully clicked on reset password link","Pass");
			ExpectedLable("Enter invalid user name into email blank");
			LoginPage.ResetPasswordEmail(driver).sendKeys("tt");
			ActualLable("Successfully entered invalid user name into email blank","Pass");
			ExpectedLable("Check for error message for invalid data");
			if(driver.findElements(By.xpath("//div[@class='text-danger']")).size()>0){

				//String  ErrorMessageForEmail = ForgotPassword.ErrorMessageForEmail(driver).getText();
				log.info("Error message showing for the email blank is "+ForgotPassword.ErrorMessageForEmail(driver));
				ActualLable("Successfully showing error message for email blank","Pass");
			}
			else{
				ActualLable("Error message for email blank is not showing","Fail");

			}
		} catch (AssertionError e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		} catch (Exception e) {
			String error = "Exception " + e.getClass().getSimpleName();
			ActualLable(error, "Fail");
		}
	}

	/*TC05: AssertVerifyForDefaultAddress the GUI of "Request Registration" Section

	The "Request Registration" Section should be displayed with the following
	a) Label Text - �If your company is not yet a registered client of Dimension Data, or you would like user-access to the Dimension Data Store for your registered company, click below to request registration�  
	c) Button - Request Registration

	 */

	@Test
	public void TC05() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {
		try{
			DataDriven.ReportStartup(5);
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
			StepLable("TC05: Assert Verification For the GUI of 'Request Registration'  Section is verified successfully");
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
	public void TC06() throws RowsExceededException, WriteException, IOException, InterruptedException, BiffException {DataDriven.ReportStartup(6);
		try{
			StepLable("Verify All static links on Landing page");

			LoginPage.AsertVerifyForAboutUSLinkHomePage(driver);
			log.info("AboutUS link Asert is verified");
			LoginPage.AsertVerifyForNEWSLinkHomePage(driver);
			log.info("Asert for NEWS link is verified");
			LoginPage.AsertVerifyForCareerLinkHomePage(driver);
			log.info("Asert for Career link is verified");
			LoginPage.AsertVerifyForSafeHarborPolicyLinkHomePage(driver);
			log.info("Asert for Safe Harbor Policy link is verified");
			LoginPage.AsertVerifyForServiceCenterLinkHomePage(driver);
			log.info("Asert for ServiceCenter link is verified");
			LoginPage.AsertVerifyForPrivacyPolicyLinkHomePage(driver);
			log.info("Asert for Privacy Policy link is verified");
			LoginPage.AsertVerifyForTermsConditionsLinkHomePage(driver);
			log.info("Asert for Terms Conditions link is verified");
			LoginPage.AsertVerifyForCookiePolicyLinkHomePage(driver);
			log.info("Asert for Cookie Policy link is verified");
			LoginPage.AsertVerifyForContactDDLinkHomePage(driver);
			log.info("Asert for ContactDD link is verified");
			StepLable("TC06:Successfully verified all static links");
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
