package pageObject;

import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;

public class ForgotPassword {
	
	public static WebElement element;
	public static By by;

	static private By ResetEmailElement = By.id("email");
	static private By LabeltextElement = By.xpath("//p[@class='login-desc']");
	static private By ImNotRobotElement = By.xpath("//is-captcha/div/div/div");
	static private By LoginButtonXpath = By.xpath("//button[contains(text(),'Email Password')]");
	static private By ErrorMessageElement = By.xpath("//div[@class='text-danger']");


	
	public static String ErrorMessageForEmail(WebDriver driver) throws IOException, WriteException {
		ExpectedLable("Enter invalid user name in email blank");
		LoginPage.ResetPasswordEmail(driver).sendKeys("tt");
		ActualLable("Successfully entered invalid user name into email blank","Pass");
		LoginPage.ResetPasswordEmail(driver).sendKeys(Keys.TAB);
		String errormessage = null;
		ExpectedLable("Check for error message for invalid data");
		if(driver.findElements(By.xpath("//div[@class='text-danger']")).size()>0){
		element = driver.findElement(ErrorMessageElement);
		errormessage = element.getText();
			ActualLable("Successfully showing error message for email blank","Pass");
		}
		else{			ActualLable("Error message for email blank is not showing","Fail");		}
		return errormessage;
	}

	public static void AssertVerifyForResetPassword(WebDriver driver) throws IOException, WriteException {

		ExpectedLable("Verify 'Label text' is available or not ?");
		if(driver.findElements(LabeltextElement).size()>0){
			ActualLable("Successfully verified 'Label text' is available on Reset Password page" ,"Pass");
		}
		else{	ActualLable("'Label text' is not available on Reset Password page" ,"Fail");		}

		ExpectedLable("Verify 'Email field' is available or not ?");
		if(driver.findElements(ResetEmailElement).size()>0){
			ActualLable("Successfully verified 'Email field' is available on Reset Password page" ,"Pass");
		}
		else{	ActualLable("'Email field' is not available on Reset Password page" ,"Fail");		}

		ExpectedLable("Verify 'Am not Robot' is available or not ?");
		if(driver.findElements(ImNotRobotElement).size()>0){
			ActualLable("Successfully verified 'Am not Robot' is available on Reset Password page" ,"Pass");
		}
		else{	ActualLable("'Am not Robot' is not available on Reset Password page" ,"Fail");		}

		ExpectedLable("Verify 'Email Password button' is available or not ?");
		if(driver.findElements(LoginButtonXpath).size()>0){
			ActualLable("Successfully verified 'Email Password button' is available on Reset Password page" ,"Pass");
		}
		else{	ActualLable("'Email Password button' is not available on Reset Password page" ,"Fail");		}
	}

}
