package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPage {


	public static WebElement element;
	public static By by;


	public static WebElement RqForRegist(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//button[contains(text(),'Request Registration')]"));
		return element;
	}

	public static WebElement UserRegist(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//ul[@class='nav nav-tabs nav-justified']/li[1]/a"));
		return element;
	}
	public static WebElement CompanyRegist(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//ul[@class='nav nav-tabs nav-justified']/li[2]/a"));
		return element;
	}
	public static WebElement CompanyName(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("company"));
		return element;
	}
	public static WebElement FirstName(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("firstName"));
		return element;
	}

	public static WebElement LastName(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("lastName"));
		return element;
	}

	public static WebElement Email(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("email"));
		return element;
	}

	public static WebElement Selectlanguage(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//span[contains(text(),'Select your language')]/parent::span"));
		return element;
	}
	public static WebElement language(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//li[contains(text(),'US')]"));
		return element;
	}

	public static WebElement TimeZone(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//span[contains(text(),'Select your timezone')]/parent::span"));
		return element;
	}
	public static WebElement SelectTimeZone(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//li[contains(text(),'GMT+05:30')]"));
		return element;
	}

	public static WebElement Robot(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[5]"));
		return element;
	}

	public static WebElement RequestRegistration(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//span[contains(text(),'Request Registration')]"));
		return element;
	}


	public static boolean UserRegistration(WebDriver driver) throws InterruptedException 
	{	  
		LoginPage.Register(driver).click();
		Thread.sleep(2000);

		RegistrationPage.RqForRegist(driver).click();
		Thread.sleep(2000);
		
		RegistrationPage.CompanyName(driver).sendKeys("");
		RegistrationPage.FirstName(driver).sendKeys("");
		RegistrationPage.LastName(driver).sendKeys("");
		RegistrationPage.Email(driver).sendKeys("");
		RegistrationPage.Selectlanguage(driver).click();
		RegistrationPage.language(driver).click();
		RegistrationPage.TimeZone(driver).click();
		RegistrationPage.SelectTimeZone(driver).click();
		RegistrationPage.Robot(driver).click();
		
		Thread.sleep(15000);

		RegistrationPage.RequestRegistration(driver).click();
		return true;
	}

	//elements on Company Registration
	By CompanyUrl = By.id("url");
	By CompanyName2 = By.id("company");
	By AddressLine1 = By.id("address1");
	By AddressLine2 = By.id("address2");
	By City = By.id("city");
	By State = By.id("state");
	By PostalCode = By.id("zip");
	By County = By.xpath("");
	By CountyName = By.xpath("");
	By Launguage = By.xpath("");
	By LaunguageName = By.xpath("");
	By ContactName = By.id("contact");
	By phone = By.id("phone");
	By email = By.id("email");


	public static WebElement CompanyUrl(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("url"));
		return element;
	}

	public static WebElement CompanyName2(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("company"));
		return element;
	}

	public static WebElement AddressLine1(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("address1"));
		return element;
	}

	public static WebElement AddressLine2(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("address2"));
		return element;
	}

	public static WebElement City(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("city"));
		return element;
	}

	public static WebElement State(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("state"));
		return element;
	}

	public static WebElement PostalCode(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("zip"));
		return element;
	}

	public static WebElement County(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--single'][1]"));
		return element;
	}

	public static WebElement CountyName(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//li[contains(text(),'United States')]"));
		return element;
	}

	public static WebElement Launguage(WebDriver driver) 
	{	  

		element = driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--single'][2]"));
		return element;
	}

	public static WebElement LaunguageName(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//li[contains(text(),'US')]"));
		return element;
	}

	public static WebElement ContactName(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("contact"));
		return element;
	}

	public static WebElement phone(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("phone"));
		return element;
	}

	public static WebElement email(WebDriver driver) 
	{	  

		element = driver.findElement(By.id("email"));
		return element;
	}

	public static WebElement AmNotRobot(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-checkmark']"));
		return element;
	}

	public static WebElement RequestRegistration2(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//button[contains(text(),'Request Registration')]"));
		return element;
	}

	public static WebElement ClearAll(WebDriver driver)
	{	  

		element = driver.findElement(By.xpath("//button[contains(text(),'Clear All')]"));
		return element;
	}


	public static Boolean CompanyRegistration(WebDriver driver) throws InterruptedException 
	{

		LoginPage.Register(driver).click();
		Thread.sleep(2000);

		RegistrationPage.RqForRegist(driver).click();
		Thread.sleep(2000);
		
		RegistrationPage.CompanyUrl(driver).sendKeys("");
		RegistrationPage.CompanyName2(driver).sendKeys("");
		RegistrationPage.AddressLine1(driver).sendKeys("");
		RegistrationPage.AddressLine2(driver).sendKeys("");
		RegistrationPage.City(driver).sendKeys("");
		RegistrationPage.State(driver).sendKeys("");
		RegistrationPage.PostalCode(driver).sendKeys("");
		RegistrationPage.County(driver).click();
		RegistrationPage.CountyName(driver).click();
		RegistrationPage.Launguage(driver).click();
		RegistrationPage.LaunguageName(driver).click();
		RegistrationPage.ContactName(driver).sendKeys("");
		RegistrationPage.phone(driver).sendKeys("");
		RegistrationPage.email(driver).sendKeys("");
		RegistrationPage.AmNotRobot(driver).click();
		Thread.sleep(15000);
		
		RegistrationPage.RequestRegistration2(driver).click();

		return null;
	}


}
