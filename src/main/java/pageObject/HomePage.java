package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;

public class HomePage {

	public static WebElement element;
	public static By by;
	
	
	public static void PageTitle(WebDriver driver)
	{
		//WebDriver driver = null;
		String Actualtext= driver.getTitle();
		
		System.out.println("title of the page is " + Actualtext);
		
		Assert.assertEquals(Actualtext, "Dimension Data Direct");

	}

	public static WebElement ShoppingCartLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//a[contains(text(),'Shopping Cart')]"));
		return element;
	}

	public static void AsertVerifyForShoppingCartLinkHomePage(WebDriver driver) throws InterruptedException
	{

		ShoppingCartLinkHomePage(driver).click();
		Thread.sleep(2000);

		String Title = driver.findElement(by.xpath("//span[contains(text(),'Shopping Cart')]")).getText();
		System.out.println("Title of the page is "+Title);

		Assert.assertEquals(Title, "Shopping Cart");

	}
	public static WebElement HomeCartLinkHomePage(WebDriver driver)
	{

		element = driver.findElement(By.xpath("//div[@class='header-links']/ul/li[1]/a[contains(text(),'Home')]"));
		return element;
	}

	public static void AsertVerifyForHomeLinkHomePage(WebDriver driver) throws InterruptedException
	{
		AsertVerifyForShoppingCartLinkHomePage(driver);
		System.out.println("System is navigate to the Shipping page");
		System.out.println("Now clicking on Home link");

		HomeCartLinkHomePage(driver).click();
		Thread.sleep(2000);

		String Title = driver.findElement(by.xpath("//header[@class='content-header']/h2")).getText();
		System.out.println("Title of the page is "+Title);

		Assert.assertEquals(Title, "Welcome Standard User");

	}





	
}
