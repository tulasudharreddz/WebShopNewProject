package browserStackTC;

import java.awt.*;
import java.io.IOException;

import GenericLib.*;
import jxl.Sheet;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import pageObject.ForgotPassword;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;


public class LandingPageBSTC extends BrowserStack
{

	Browser brow = new Browser();
	DataDriven excel = new DataDriven();
	AlertHandle popup = new AlertHandle();
	ObjectRepository ob = new ObjectRepository();
	WebElement element;
	Sheet sheet;
	WritableSheet wsheet;
	Logger log = Logger.getLogger("Testing Cases");

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}

	/*WS_TC_01: AssertVerifyForDefaultAddress the launch of Login page & and its content

	Disc:  The 'Buyer login' page should be displayed with the following sections
		Sections:
		1. Login
		2. Reset Password
		3. Request Registration
	*/

@Test
	public void TC01() throws RowsExceededException, WriteException, IOException, InterruptedException, AWTException {
		//check that user able to login with valid credentials or not
		Thread.sleep(3000);		

		LoginPage.PageTitle(driver);
		LoginPage.LoginPageTitle(driver);
		LoginPage.ResetPasswordAssert(driver);
		LoginPage.RegisterAsert(driver);


	}

	/*	  
	WS_TC_03: Validate the Login functionality
	a) Select a value from "Select your language" dropdown
	b) Enter valid User Name and Password

	Expe result : Login should be successful and user should be redirected to 'Home' page
	 */

	public void TC02() throws RowsExceededException, WriteException, IOException, InterruptedException, AWTException {
		//check that user able to login with valid credentials or not
		Thread.sleep(2000);

		LoginPage.PageTitle(driver);
		log.info("Assert verified");
		LoginPage.Loginfunctionality(driver);

		HomePage.PageTitle(driver);




	}

	/*
	 WS_TC_04: AssertVerifyForDefaultAddress the GUI of 'Reset Password' Section
	 Below controls should be displayed in Reset Password' Page
		Textbox - User Name
		Label Text - "if you forgot your password, please enter your user name below and click on email password"
		Checkbox - I am not a Robot
		Captcha
		Button - Email Password

	 */

	public void TC03() throws RowsExceededException, WriteException, IOException, InterruptedException, AWTException {
		//check that user able to login with valid credentials or not
		Thread.sleep(2000);

		LoginPage.PageTitle(driver);
		LoginPage.ResetPasswordAssert(driver);
		LoginPage.ResetPasswordLink(driver).click();
		Thread.sleep(3000);

		LoginPage.ResetPasswordEmail(driver).sendKeys("t.mirasipally@dimensiondata.com");
		//Assert.assertNotNull(RegistrationPage.AmNotRobot(driver));
		if(driver.findElements(By.xpath("//*[@id='recaptcha-anchor']/div[5]")).size()>0){
			//RegistrationPage.AmNotRobot(driver).click();
			Thread.sleep(15000);
			LoginPage.SubmitOnResetPassword(driver).click();
		}
		else{
			log.info("Robot Check is not visible, test case fail");
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


	
	public void TC04() throws RowsExceededException, WriteException, IOException, InterruptedException, AWTException {

		LoginPage.ResetPasswordLink(driver).click();

		LoginPage.ResetPasswordEmail(driver).sendKeys("tt");


		if(driver.findElements(By.xpath("//div[@class='text-danger']")).size()>0){

			//String  ErrorMessageForEmail = ForgotPassword.ErrorMessageForEmail(driver).getText();
			log.info("Error message showing for the email blank is "+ForgotPassword.ErrorMessageForEmail(driver));			

		}

		else{



		}

	}

	/*WS_TC_06: AssertVerifyForDefaultAddress the GUI of "Request Registration" Section

	The "Request Registration" Section should be displayed with the following
	a) Label Text - �If your company is not yet a registered client of Dimension Data, or you would like user-access to the Dimension Data Store for your registered company, click below to request registration�  
	c) Button - Request Registration

	 */

	@Test
	public void TC05() throws RowsExceededException, WriteException, IOException, InterruptedException
	{
		Thread.sleep(2000);
		String labeltext= LoginPage.LableForRegistration(driver).getText();

		log.info("Label Text for Request registration  is  " +labeltext);

		//check that Button for Request Registration is exist or not.
		
		log.info("Request Registration is exist with link " + LoginPage.Register(driver).getText());
		
		log.info("test cases is verified and Passed");

	}
	// AssertVerifyForDefaultAddress that all the static links are working or not.
	@Test
	public void TC06() throws RowsExceededException, WriteException, IOException, InterruptedException, AWTException {

		LoginPage.AsertVerifyForAboutUSLinkHomePage(driver);

		log.info("AboutUS link Asert is verified");

		LoginPage.AsertVerifyForNEWSLinkHomePage(driver);

		log.info("Asert for NEWS link is verified");

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
	}


}
