package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.ForgotPassword;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;


public class WebSharpTestCase
{
	Logger log=Logger.getLogger("Test Cases");
	WebDriver driver;
	Workbook book;
	WritableWorkbook wbook;
	Sheet sheet;
	WritableSheet wsheet;
	static int count = 1;
	int num_rows;
	int reg_value = (8 + 1), flag = 0, sacFlag = 0;
	WebElement culture1;
	DateFormat dateFormat;
	Date date;
	Properties obj;
	FileInputStream objFile;
	String packageName = "testCases";
	String className = "WebSharpTestCase";	



	@BeforeTest
	public void start() throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException
	{
		// Configure Objects properties.
		obj = new Properties();
		objFile = new FileInputStream(System.getProperty("user.dir") + "./utility/objects.properties");
		obj.load(objFile);

		// Configure log4j property file
		PropertyConfigurator.configure(obj.getProperty("log4j"));

		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");

		driver = new ChromeDriver();
		log.info("Chrome Browser Opened");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get(obj.getProperty("url"));
		log.info("URL: " + driver.getCurrentUrl());

		// Configured excel sheet for input data
		book = Workbook.getWorkbook(new File("./testData.xls"));
		sheet = book.getSheet("Sheet1");

		// Create a new excel sheet for output result
		wbook = Workbook.createWorkbook(new File("./TestData/" + packageName + "/" + className + ".xls"), book);
		wsheet = wbook.getSheet("Sheet1");

		// Count number of scenario in excel sheet.
		num_rows = sheet.getRows();
		log.info("Number of rows in sheet: " + num_rows);


	}



	/*
	TC01: Verify the launch of Login page & and its content

	Disc:  The 'Buyer login' page should be displayed with the following sections
		Sections:
		1. Login
		2. Reset Password
		3. Request Registration*/



	public void TC01() throws RowsExceededException, WriteException, IOException, InterruptedException
	{
		//check that user able to login with valid credentials or not
		Thread.sleep(3000);		

		LoginPage.PageTitle(driver);
		LoginPage.LoginPageTitle(driver);
		LoginPage.ResetPasswordAssert(driver);
		LoginPage.RegisterAsert(driver);

		screenShot();


	}



	/*	  
	TC01: Validate the Login functionality
	a) Select a value from "Select your language" dropdown
	b) Enter valid User Name and Password

	Expe result : Login should be successful and user should be redirected to 'Home' page
	 */

	public void TC02() throws RowsExceededException, WriteException, IOException, InterruptedException
	{
		//check that user able to login with valid credentials or not
		Thread.sleep(2000);

		LoginPage.PageTitle(driver);
		log.info("Assert verified");
		LoginPage.Loginfunctionality(driver);

		HomePage.PageTitle(driver);

		screenShot();


	}

	/*
	 TC03: Verify the GUI of 'Reset Password' Section
	 Below controls should be displayed in Reset Password' Page
		Textbox - User Name
		Label Text - "if you forgot your password, please enter your user name below and click on email password"
		Checkbox - I am not a Robot
		Captcha
		Button - Email Password

	 */

	public void TC03() throws RowsExceededException, WriteException, IOException, InterruptedException
	{
		//check that user able to login with valid credentials or not
		Thread.sleep(2000);

		LoginPage.PageTitle(driver);
		LoginPage.ResetPasswordAssert(driver);
		LoginPage.ResetPasswordLink(driver).click();
		Thread.sleep(3000);

		LoginPage.ResetPasswordEmail(driver).sendKeys("t.mirasipally@dimensiondata.com");
		//Assert.assertNotNull(RegistrationPage.AmNotRobot(driver));
		if(driver.findElements(By.xpath("//*[@id='recaptcha-anchor']/div[5]")).size()>0){
			RegistrationPage.AmNotRobot(driver).click();
			Thread.sleep(15000);
			LoginPage.SubmitOnResetPassword(driver).click();
		}
		else{
			log.info("Robot Check is not visible, test case fail");
		} 	
		screenShot();
	}
	/*

	Validate the Email Password functionality
	a) Enter the valid 'User Name' & valid 'Captcha' 
	b) Enter the valid 'User Name' & Invalid 'Captcha' 
	c) Enter the Invalid 'User Name' & valid 'Captcha'
	d) Enter the Invalid 'User Name' & Invalid 'Captcha'
	Note: - Valid User Name  = user�s email address

	 */


	
	public void TC04() throws RowsExceededException, WriteException, IOException, InterruptedException
	{

		LoginPage.ResetPasswordLink(driver).click();

		LoginPage.ResetPasswordEmail(driver).sendKeys("tt");


		if(driver.findElements(By.xpath("//div[@class='text-danger']")).size()>0){

			//String  ErrorMessageForEmail = ForgotPassword.ErrorMessageForEmail(driver).getText();
			log.info("Error message showing for the email blank is "+ForgotPassword.ErrorMessageForEmail(driver));			

		}

		else{



		}

	}

	/*TC05: Verify the GUI of "Request Registration" Section

	The "Request Registration" Section should be displayed with the following
	a) Label Text - �If your company is not yet a registered client of Dimension Data, or you would like user-access to the Dimension Data Store for your registered company, click below to request registration�  
	c) Button - Request Registration

	 */

	@Test
	public void TC05() throws RowsExceededException, WriteException, IOException, InterruptedException
	{

		String labeltext= LoginPage.LableForRegistration(driver).getText();

		log.info("Label Text for Request registration  is " +labeltext);

		//check that Button for Request Registration is exist or not.
		
		log.info("Request Registration is exist with link " + LoginPage.Register(driver).getText());
		
		log.info("test cases is verified and Passed");

	}







	@AfterTest
	public void Close() throws IOException
	{
		driver.quit();

		driver.close();


	}

	public void screenShot() throws IOException
	{

		File screen = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("./ScreenShot/" + packageName + "/" + className + "/screen-" + count + ".jpg"));
		log.info("Screen: " + "./ScreenShot/" + packageName + "/" + className + "/screen-" + count + ".jpg");
		count++;
	}


}
