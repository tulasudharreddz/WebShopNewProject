package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {


	public static WebElement element;
	public static By by;
	public static String PageTitle(WebDriver driver) 
	{
		//WebDriver driver = null;
		String Actualtext= driver.getTitle();
		Assert.assertEquals(Actualtext, "Dimension Data Direct");

		System.out.println("title of the page is " + Actualtext);



		return null;
	}


	public static WebElement LoginPageTitle(WebDriver driver) 
	{	  

		String LoginTitle = driver.findElement(By.xpath("//span[@class='login-header']")).getText();

		Assert.assertEquals(LoginTitle, "Log In");
		System.out.println("Title of the Log in page is " + LoginTitle);
		return element;
	}

	public static WebElement ResetPasswordAssert(WebDriver driver) 
	{	  

		if(driver.findElements(By.xpath("//a[@routerlink='resetpassword']")).size() > 0){

			String ResetPassword = driver.findElement(By.xpath("//a[@routerlink='resetpassword']")).getText();

			Assert.assertEquals(ResetPassword, "Reset Password.");
			//Assert.assertEquals(>0, driver.findElements(By.xpath("//a[@routerlink='resetpassword']")).size());
			System.out.println("Reset Password link available on landing page");
		}
		else{
			System.out.println("Failed : Reset Password link is not available");
		}
		return element;
	}
	
	public static WebElement RegisterAsert(WebDriver driver) 
	{	  

		if(driver.findElements(By.xpath("//a[contains(text(),'Register here')]")).size() > 0){

			String RegisterAsert = driver.findElement(By.xpath("//a[contains(text(),'Register here')]")).getText();

			Assert.assertEquals(RegisterAsert, "Register here");
			System.out.println("Register here link available on landing page");
		}
		else{
			System.out.println("Failed : Register here link is not available");
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

		element = driver.findElement(By.xpath("//a[contains(text(),'Register here')]"));
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


	public static boolean Loginfunctionality(WebDriver driver) 
	{	  

		LoginPage.Language(driver).click();
		LoginPage.Selectlanguage(driver).click();
		LoginPage.UserName(driver).sendKeys("t.mirasipally@dimensiondata.com");
		LoginPage.Password(driver).sendKeys("abcd12345");

		LoginPage.ClickLogin(driver).click();
		return true;
	}





}
