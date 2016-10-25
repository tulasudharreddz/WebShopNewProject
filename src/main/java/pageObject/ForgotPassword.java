package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPassword {
	
	public static WebElement element;
	public static By by;
	
	
	public static String ErrorMessageForEmail(WebDriver driver){
		
		element = driver.findElement(By.xpath("//div[@class='text-danger']"));
		String errormessage = element.getText();
		return errormessage;
		
	}


	
	

}
