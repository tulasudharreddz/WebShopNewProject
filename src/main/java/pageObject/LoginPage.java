package pageObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import GenericLib.ObjectRepository;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {

	static ObjectRepository obje = new ObjectRepository();
	public static WebElement element;
	public static By by;
	static Logger log = Logger.getLogger("Login Page");
	public static String PageTitle(WebDriver driver) 
	{
		//WebDriver driver = null;
		String Actualtext= driver.getTitle();
		Assert.assertEquals(Actualtext, "Dimension Data Direct");


		log.info("title of the page is " + Actualtext);



		return null;
	}


	public static WebElement LoginPageTitle(WebDriver driver) 
	{	  

		String LoginTitle = driver.findElement(By.xpath("//span[@class='login-header']")).getText();

		Assert.assertEquals(LoginTitle, "Log In");
		log.info("Title of the Log in page is " + LoginTitle);
		return element;
	}

	public static WebElement ResetPasswordAssert(WebDriver driver) 
	{	  

		if(driver.findElements(By.xpath("//a[contains(text(),'Reset Password.')]")).size() > 0){

			String ResetPassword = driver.findElement(By.xpath("//a[contains(text(),'Reset Password.')]")).getText();

			Assert.assertEquals(ResetPassword, "Reset Password.");
			//Assert.assertEquals(>0, driver.findElements(By.xpath("//a[@routerlink='resetpassword']")).size());
			log.info("Reset Password link available on landing page");
		}
		else{
			log.info("Failed : Reset Password link is not available");
		}
		return element;
	}
	
	public static WebElement RegisterAsert(WebDriver driver) 
	{	  

		if(driver.findElements(By.xpath("//a[contains(text(),'Register Here')]")).size() > 0){

			String RegisterAsert = driver.findElement(By.xpath("//a[contains(text(),'Register Here')]")).getText();

			Assert.assertEquals(RegisterAsert, "Register Here");
			log.info("Register here link available on landing page");
		}
		else{
			log.info("Failed : Register here link is not available");
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
	
	public static WebElement AboutUSLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'About Us')]"));
		return element;
	}
	
	public static void AsertVerifyForAboutUSLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		AboutUSLinkHomePage(driver).click();
		Thread.sleep(3000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "About");
		
		driver.close();
		
		driver.switchTo().window(tabs.get(0));		
		
	}
	
	public static WebElement NEWSLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'News')]"));
		return element;
	}
	
	
	
	public static void AsertVerifyForNEWSLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		NEWSLinkHomePage(driver).click();
		Thread.sleep(3000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "News and Events");
		
		driver.close();
		
		driver.switchTo().window(tabs.get(0));		
		
	}
	
	public static WebElement CareerLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'Careers')]"));
		return element;
	}
	
	
	
	public static void AsertVerifyForCareerLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		CareerLinkHomePage(driver).click();
		Thread.sleep(3000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "Careers | Dimension Data");
		
		driver.close();
		
		driver.switchTo().window(tabs.get(0));		
		
	}
	
	public static WebElement SafeHarborPolicyLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'Safe Harbor Policy')]"));
		return element;
	}
	
	
	
	public static void AsertVerifyForSafeHarborPolicyLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		SafeHarborPolicyLinkHomePage(driver).click();
		Thread.sleep(3000);		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "U.S. Safe Harbor Policy");
		
		driver.close();
		
		driver.switchTo().window(tabs.get(0));
		
	}
	
	public static WebElement ServiceCenterLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'Service Center')]"));
		return element;
	}

	public static void AsertVerifyForServiceCenterLinkHomePage(WebDriver driver) throws InterruptedException
	{
		
		String HREFValue = ServiceCenterLinkHomePage(driver).getAttribute("href");
		
		log.info("Service center link will navigate to  "+HREFValue);
		
		Assert.assertEquals(HREFValue, "mailto:client.contact@dimensiondata.com");
		
	}
	
	public static WebElement PrivacyPolicyLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'Privacy')]"));
		return element;
	}
	
	
	
	public static void AsertVerifyForPrivacyPolicyLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		PrivacyPolicyLinkHomePage(driver).click();
		Thread.sleep(3000);		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "Dimension Data Privacy Policy");
		
		driver.close();
		
		driver.switchTo().window(tabs.get(0));
		
	}//	Terms and Conditions of Use of the Dimension Data Website
	
	
	public static WebElement TermsConditionsLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'Terms of Use')]"));
		return element;
	}
	
	
	
	public static void AsertVerifyForTermsConditionsLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		TermsConditionsLinkHomePage(driver).click();
		Thread.sleep(3000);		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "Terms and Conditions of Use of the Dimension Data Website");
		
		driver.close();
		
		driver.switchTo().window(tabs.get(0));
		
	}//Cookie Policy
	
	
	public static WebElement CookiePolicyLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'Cookie Policy')]"));
		return element;
	}
	
	
	
	public static void AsertVerifyForCookiePolicyLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		CookiePolicyLinkHomePage(driver).click();
		Thread.sleep(3000);		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		String Title = driver.getTitle();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "Dimension Data Cookie Policy");
		
		driver.close();
		
		driver.switchTo().window(tabs.get(0));
		
	}
	
	
	//Contact Dimension Data
	
	
	public static WebElement ContactDDLinkHomePage(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//a[contains(text(),'Contact Dimension Data')]"));
		return element;
	}
	
	
	
	public static void AsertVerifyForContactDDLinkHomePage(WebDriver driver) throws InterruptedException
	{	  
				
		ContactDDLinkHomePage(driver).click();
		Thread.sleep(3000);		
		String Title = driver.findElement(By.xpath("//div[contains(text(),'Contact Dimension Data')]")).getText();
		log.info("Title of the page is "+Title);
		
		Assert.assertEquals(Title, "Contact Dimension Data");	
		
		driver.findElement(By.xpath("//button[@class='is-alert-close']")).click();
		
		Thread.sleep(2000);
	}

	public static void Loginfunctionality(WebDriver driver) throws IOException, InterruptedException {
		WebDriverWait waitCulture = new WebDriverWait(driver, 40);
		waitCulture.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='select2-selection select2-selection--single']")));
		Thread.sleep(2000);
		LoginPage.Language(driver).click();
		Thread.sleep(5000);
		LoginPage.Selectlanguage(driver).click();
		obje.repository(driver);
		LoginPage.UserName(driver).sendKeys(obje.obj.getProperty("email"));
		LoginPage.Password(driver).sendKeys(obje.obj.getProperty("password"));

		LoginPage.ClickLogin(driver).click();

	}





}
