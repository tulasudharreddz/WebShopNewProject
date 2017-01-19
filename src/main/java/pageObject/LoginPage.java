package pageObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import jxl.write.WritableSheet;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import jxl.write.WriteException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

public class LoginPage {

	static protected ObjectRepository obje = new ObjectRepository();
	static protected DataDriven excel = new DataDriven();
	public static WebElement element;
	public static By by;
	static Logger log = Logger.getLogger("Login Page");
	static private WritableSheet wsheet;

	static protected WebDriver driver;
	//Page Elements
	static private By ResetPasswordLinkXpath = By.xpath("//a[contains(text(),'Reset Password')]");
	static private By LanguageDropDownXpath = By.xpath("//span[@class='select2-selection select2-selection--single']");
	static private By UserNameXpath = By.id("email");
	static private By PasswordXpath = By.id("password");
	static private By LoginButtonXpath = By.xpath("//button[contains(text(),'Login')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	public static void PageTitle(WebDriver driver)
	{
		//WebDriver driver = null;
		String Actualtext= driver.getTitle();
		Assert.assertEquals(Actualtext, "Dimension Data Direct");
		log.info("title of the page is " + Actualtext);
	}


	public static void LoginPageTitle(WebDriver driver) throws IOException, WriteException {
		String LoginTitle = driver.findElement(By.xpath("//span[@class='login-header']")).getText();
		ExpectedLable("Login fields should available");
		Assert.assertEquals(LoginTitle, "Log In");
		log.info("Title of the Log in page is " + LoginTitle);
		ActualLable("Successfully verified Login fields and Title of the Log in page is " + LoginTitle,"Pass");
	}

	public static void LoginFieldsAssertVerify(WebDriver driver) throws IOException, WriteException {

		ExpectedLable("Verify 'Drop down for language' is available or not ?");
		if(driver.findElements(LanguageDropDownXpath).size()>0){
			ActualLable("Successfully verified 'Drop down for language' is available on login page" ,"Pass");
		}
		else{			ActualLable("'Drop down for language' is not available on login page" ,"Fail");		}

		ExpectedLable("Verify 'User name field' is available on login page or not ?");
		if(driver.findElements(UserNameXpath).size()>0){
			ActualLable("Successfully verified 'User name field' is available on login page" ,"Pass");
		}
		else{			ActualLable("'User name field' is not available on login page" ,"Fail");		}

		ExpectedLable("Verify 'Password field' is available or not ?");
		if(driver.findElements(PasswordXpath).size()>0){
			ActualLable("Successfully verified 'Password field' is available on login page" ,"Pass");
		}
		else{			ActualLable("'Password field' is not available on login page" ,"Fail");		}
		ExpectedLable("Verify 'Log in button' is available or not ?");
		if(driver.findElements(LoginButtonXpath).size()>0){
			ActualLable("Successfully verified 'Log in button' is available on login page" ,"Pass");
		}
		else{			ActualLable("'Log in button' is not available on login page" ,"Fail");		}

	}


	public static void ResetPasswordAssert(WebDriver driver) throws IOException, WriteException {
		ExpectedLable("Reset password link should available on home page");
		if(driver.findElements(By.xpath("//a[contains(text(),'Reset Password.')]")).size() > 0){
			String ResetPassword = driver.findElement(By.xpath("//a[contains(text(),'Reset Password.')]")).getText();
			Assert.assertEquals(ResetPassword, "Reset Password.");
			ActualLable("Reset password link on home page is successfully verified","Pass");
			//Assert.assertEquals(>0, driver.findElements(By.xpath("//a[@routerlink='resetpassword']")).size());
			log.info("Reset Password link available on landing page");
		}
		else{
			ActualLable("Failed : Reset Password link is not available","Fail");
			log.info("Failed : Reset Password link is not available");
		}
	}

	public static WebElement RegisterAsert(WebDriver driver) throws IOException, WriteException {
		ExpectedLable("Register Here link should available on home page");
		if(driver.findElements(By.xpath("//a[contains(text(),'Register Here')]")).size() > 0){

			String RegisterAsert = driver.findElement(By.xpath("//a[contains(text(),'Register Here')]")).getText();

			Assert.assertEquals(RegisterAsert, "Register Here");
			ActualLable("Register Here link on home page is successfully verified","Pass");
			log.info("Register here link available on landing page");
		}
		else{
			log.info("Failed : Register here link is not available");
			ActualLable("Failed : Register Here link is not available","Fail");
		}
		return element;
	}


	//	for Language
	public static WebElement Language(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--single']"));
		return element;
	}

	//for selecting language
	public static WebElement Selectlanguage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//li[contains(text(),'US')]"));
		return element;
	}

	//	for Language
	public static WebElement UserName(WebDriver driver)
	{

		element = driver.findElement(By.id("email"));
		return element;
	}

	//for selecting language
	public static WebElement Password(WebDriver driver)
	{

		element = driver.findElement(By.id("password"));
		return element;
	}

	public static WebElement ClickLogin(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
		return element;
	}

	public static WebElement Register(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Register Here')]"));
		return element;
	}

	public static WebElement ResetPasswordLink(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[contains(text(),'Reset Password')]"));

		return element;
	}
	public static void ClickOnResetPasswordLink(WebDriver driver) throws IOException, WriteException {
		ExpectedLable("Click on Reset password link");
		LoginPage.ResetPasswordLink(driver).click();
		ActualLable("Successfully clicked on reset password link","Pass");
	}
	public static void ResetPasswordFunctionality(WebDriver driver) throws IOException, WriteException, InterruptedException {
		ClickOnResetPasswordLink(driver);
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
	}

	public static WebElement ResetPasswordEmail(WebDriver driver)
	{

		element = driver.findElement(By.id("email"));
		return element;
	}

	public static WebElement SubmitOnResetPassword(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//button[contains(text(),'Email Password')]"));
		return element;
	}
	public static WebElement AmNotRobot(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[5]"));
		return element;
	}

	public static WebElement LableForRegistration(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//p[@class='login-desc']"));
		return element;
	}
	public static List<WebElement> FooterLinks(WebDriver driver) {

		List<WebElement> FooterLink = driver.findElements(By.xpath("//div[@class='footer-links col-xl-9 col-lg-12 col-md-12 col-sm-12']/ul/li/a"));

		return FooterLink;
	}
	public static void AsertVerifyForFooterLinksHomePage(WebDriver driver) throws InterruptedException
	{
		int noOfLinks= FooterLinks(driver).size();
		for (int i=0;i<=noOfLinks;i++) {
			String linkName = FooterLinks(driver).get(i).getText();
			FooterLinks(driver).get(i).click();
			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			String Title = driver.getTitle();
			log.info("Title of the page is " + Title);
			Assert.assertEquals(Title, linkName);
			driver.close();
			driver.switchTo().window(tabs.get(0));
		}
	}

	public static WebElement AboutUSLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'About Us')]"));
		return element;
	}

	public static void AsertVerifyForAboutUSLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on About us link on landing page");
		AboutUSLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on About us link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "About");
		ActualLable("About us page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public static WebElement NEWSLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'News')]"));
		return element;
	}

	public static void AsertVerifyForNEWSLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on News and Events link on landing page");
		NEWSLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on News and Events link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "News and Events");
		ActualLable("News and Events page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public static WebElement CareerLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Careers')]"));
		return element;
	}
	public static void AsertVerifyForCareerLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on Careers | Dimension Data link on landing page");
		CareerLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on Careers | Dimension Data link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "Careers | Dimension Data");
		ActualLable("Careers | Dimension Data page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public static WebElement SafeHarborPolicyLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Safe Harbor Policy')]"));
		return element;
	}
	public static void AsertVerifyForSafeHarborPolicyLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on U.S. Safe Harbor Policy link on landing page");
		SafeHarborPolicyLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on U.S. Safe Harbor Policy link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "U.S. Safe Harbor Policy");
		ActualLable("U.S. Safe Harbor Policy page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public static WebElement FAQLinkHomePage(WebDriver driver)
	{
		element = driver.findElement(By.xpath("//a[contains(text(),'FAQ')]"));
		return element;
	}

	public static void AsertVerifyForFAQLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on FAQ link on landing page");
		FAQLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on FAQ link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "FAQ");
		ActualLable("FAQ page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public static WebElement PrivacyPolicyLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Privacy')]"));
		return element;
	}
	public static void AsertVerifyForPrivacyPolicyLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on Dimension Data Privacy Policy link on landing page");
		PrivacyPolicyLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on Dimension Data Privacy Policy link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "Dimension Data Privacy Policy");
		ActualLable("Dimension Data Privacy Policy page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}//	Terms and Conditions of Use of the Dimension Data Website

	public static WebElement TermsConditionsLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Terms of Use')]"));
		return element;
	}
	public static void AsertVerifyForTermsConditionsLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on Terms and Conditions link on landing page");
		TermsConditionsLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on Terms and Conditions link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "Terms and Conditions of Use of the Dimension Data Website");
		ActualLable("Terms and Conditions page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}//Cookie Policy

	public static WebElement CookiePolicyLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Cookie Policy')]"));
		return element;
	}
	public static void AsertVerifyForCookiePolicyLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on Cookie Policy link on landing page");
		CookiePolicyLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on Cookie Policy link","Pass");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "Dimension Data Cookie Policy");
		ActualLable("Cookie Policy page is opened and Assert verified ","Pass");
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	public static WebElement ContactDDLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Contact Dimension Data')]"));
		return element;
	}

	public static void AsertVerifyForContactDDLinkHomePage(WebDriver driver) throws InterruptedException, IOException, WriteException {
		ExpectedLable("Click on Contact Dimension Data link on landing page");
		ContactDDLinkHomePage(driver).click();
		Thread.sleep(3000);
		ActualLable("Successfully clicked on Contact Dimension Data link","Pass");
		String Title = driver.findElement(By.xpath("//div[contains(text(),'Contact Dimension Data')]")).getText();
		log.info("Title of the page is "+Title);
		ExpectedLable("Corresponding Page should be open and assert should verify");
		Assert.assertEquals(Title, "Contact Dimension Data");
		ActualLable("Contact Dimension Data page is opened and Assert verified ","Pass");
		ExpectedLable("Close the alert");
		driver.findElement(By.xpath("//button[@class='is-alert-close']")).click();
		ActualLable("Alert successfully closed","Pass");
		Thread.sleep(2000);
	}

	public static void Loginfunctionality(WebDriver driver) throws IOException, InterruptedException, WriteException {
			WebDriverWait waitCulture = new WebDriverWait(driver, 40);
			waitCulture.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-selection select2-selection--single']")));
			Thread.sleep(2000);
			StepLable("Verify User Login");
			LoginPage.Language(driver).click();
			ExpectedLable("Set Language as:"+ Selectlanguage(driver).getText());
			Thread.sleep(2000);
			LoginPage.Selectlanguage(driver).click();
			ActualLable("Successfully Placed/Selected Language ","Pass");
			obje.repository(driver);
			ExpectedLable("Set value as: "+obje.obj.getProperty("email")+"for User name");
			LoginPage.UserName(driver).sendKeys(obje.obj.getProperty("email"));
			ActualLable("Successfully Placed/Selected value as:"+ obje.obj.getProperty("email"),"Pass");

			ExpectedLable("Set value as: "+obje.obj.getProperty("password")+"for User name");
			LoginPage.Password(driver).sendKeys(obje.obj.getProperty("password"));
			ActualLable("Successfully Placed/Selected value as:"+ obje.obj.getProperty("password"),"Pass");

			ExpectedLable("Verify User Login function");
			LoginPage.ClickLogin(driver).click();
			ActualLable("Successfully Loged into the application","Pass");
	}

}
