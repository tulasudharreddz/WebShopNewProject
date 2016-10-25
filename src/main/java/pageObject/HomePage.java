package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HomePage {

	public static WebElement element;
	public static By by;
	
	
	public static String PageTitle(WebDriver driver) 
	{
		//WebDriver driver = null;
		String Actualtext= driver.getTitle();
		
		System.out.println("title of the page is " + Actualtext);
		
		Assert.assertEquals(Actualtext, "Dimension Data Direct");



		return null;
	}
	
	
}
