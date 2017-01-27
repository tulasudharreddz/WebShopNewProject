package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

public class RegistrationPage {


	static protected WebDriver driver;
	static Logger log = Logger.getLogger("Check Out Page");
	public static WebElement element;
	//Elements
	static private By SingleUserRegistrationInputFields = By.xpath("//div[@class='input-group']/input");
	static private By SingleUserRegistrationDropDown = By.xpath("//span[@class='select2-selection__rendered']");
	static private By ClearAllButtonOnSingleUser = By.xpath("//button[contains(text(),'Clear All')]");
	static private By NextStepOnSingleUser = By.xpath("//button[contains(text(),'Next Step')]");
	static private By RegistrationUserListPageTitle = By.xpath("//h3");
	static private By TermsOfUse = By.id("agreeTerms");
	static private By TermsOfUseLink = By.xpath("//a[contains(text(),'Terms of Use')]");
	static private By PrivacyPolicyCheckBox = By.id("agree");
	static private By PrivacyPolicyLink = By.xpath("//a[contains(text(),'Privacy Policy')]");
	static private By AmNotRobotCaptcha = By.xpath("//is-captcha/div/div");
	static private By RequestRegistration = By.xpath("//button[contains(text(),'Request Registration')]");




	public static WebElement FirstNameUserList(WebDriver driver,int number,int field){
		ArrayList<String> AssertName=new ArrayList<String>();
		AssertName.add("firstName");
		AssertName.add("lastName");
		AssertName.add("email");
		element = driver.findElement(By.id(""+AssertName.get(field)+""+number));
		return element;
	}
	public static void SingleUserRegistration(WebDriver driver) throws IOException, WriteException, InterruptedException {
		String ReferenceNumber = null;
		ArrayList<String> AssertName=new ArrayList<String>();
		AssertName.add("Company name");
		AssertName.add("First name");
		AssertName.add("Last Name");
		AssertName.add("Email");
		AssertName.add("Language");
		AssertName.add("TimeZone");
		for(int i=0;i<=3;i++) {
			if(i==3){
				Random rand = new Random();
				int  ReferenceNumbe = rand.nextInt(999999) + 100000;
				String ReferenceNumberString=Integer.toString(ReferenceNumbe);
				ReferenceNumber = "Test"+ReferenceNumberString+"@yopmail.com";
			}
			else{
				Random rand = new Random();
				int  ReferenceNumbe = rand.nextInt(999999) + 100000;
				String ReferenceNumberString=Integer.toString(ReferenceNumbe);
				ReferenceNumber = "Test"+ReferenceNumberString;
			}
			ExpectedLable("Enter valid data in :"+AssertName.get(i));
			driver.findElements(SingleUserRegistrationInputFields).get(i).sendKeys(ReferenceNumber);
			ActualLable("Successfully Entered data in to : "+AssertName.get(i) ,"Pass");
		}
		Thread.sleep(1000);
		ExpectedLable("Select OPtion from drop down for:"+AssertName.get(4));
		driver.findElements(SingleUserRegistrationDropDown).get(0).click();
		driver.findElement(By.xpath("//li[contains(text(),'US')]")).click();
		ActualLable("Successfully Entered data in to : "+AssertName.get(4) ,"Pass");
		ExpectedLable("Select selected options for:"+AssertName.get(5));
		driver.findElements(SingleUserRegistrationDropDown).get(1).click();
		driver.findElement(By.xpath("//li[contains(text(),'GMT+05:30')]")).click();
		ActualLable("Successfully selected options for: "+AssertName.get(5) ,"Pass");
		ExpectedLable("Check that Next step button is enabled or not, if yesClick on it");
		if(driver.findElement(NextStepOnSingleUser).isEnabled()){
			driver.findElement(NextStepOnSingleUser).click();
			ActualLable("Successfully Clicked on : "+AssertName.get(5) ,"Pass");
		}else{ActualLable(AssertName.get(5)+" is not enabled" ,"Fail");}
	}
	public static void AssertVerifyForRegistrationUserList(WebDriver driver) throws IOException, WriteException, InterruptedException {
		Thread.sleep(1000);
		ExpectedLable("Verify Registration user list page title");
		String UserListPageTitle = driver.findElement(RegistrationUserListPageTitle).getText();
		if(UserListPageTitle.contentEquals("Registration - User List")){
			ActualLable("Successfully verified, Title of the page is verified successfully" ,"Pass");
		}else{ActualLable(" Failed to verify, Assert failed for Registration User list page title " ,"Fail");}

	}
	public static void ContentVerifyForRegistrationUserList(WebDriver driver) throws IOException, WriteException, InterruptedException {
		StepLable("Content verify on Request Register User list");
		ArrayList<String> AssertName=new ArrayList<String>();
		AssertName.add("firstName");
		AssertName.add("lastName");
		AssertName.add("email");
		AssertName.add("Language");
		AssertName.add("Time Zone");
		AssertName.add("Privacy policy");
		AssertName.add("Terms of use");
		AssertName.add("i'am Not Robot");
		AssertName.add("Clear All button");
		AssertName.add("Request registration Button");

		for(int i=0;i<=3;i++) {
			if(i==3){
				ExpectedLable("Verify that '"+AssertName.get(i)+"' is available on the page or not ?");
				if(driver.findElements(SingleUserRegistrationDropDown).get(i).isDisplayed()){
					ActualLable("Successfully verified, '"+AssertName.get(i)+"' is Available on Page" ,"Pass");
				}else{ActualLable("Failed to verify, Assert failed for '"+AssertName.get(i)+"' on Registration User list page " ,"Fail");}
				ExpectedLable("Verify that '"+AssertName.get(i)+"' is available on the page or not ?");
				if(driver.findElements(SingleUserRegistrationDropDown).get(i).isDisplayed()){
					ActualLable("Successfully verified, '"+AssertName.get(i)+"' is Available on Page" ,"Pass");
				}else{ActualLable("Failed to verify, Assert failed for '"+AssertName.get(i+1)+"' on Registration User list page " ,"Fail");}
			}
			else{
				ExpectedLable("Verify that '" + AssertName.get(i) + "' is available on the page or not ?");
				if (FirstNameUserList(driver, i, 0).isDisplayed()) {
					String AttributeName = FirstNameUserList(driver, 0, 0).getAttribute("placeholder");
					ActualLable("Successfully verified, '" + AssertName.get(i) + "' is Available on Page", "Pass");
				} else {
					ActualLable("Failed to verify, Assert failed for '" + AssertName.get(i) + "' on Registration User list page ", "Fail");
				}
			}
		}
		ExpectedLable("Verify that '"+AssertName.get(5)+"' is available on the page or not ?");
		if(driver.findElements(PrivacyPolicyCheckBox).size()>0){
			ActualLable("Successfully verified, '"+AssertName.get(5)+"' is Available on Page" ,"Pass");
		}else{ActualLable("Failed to verify, Assert failed for '"+AssertName.get(5)+"' on Registration User list page " ,"Fail");}
		ExpectedLable("Verify that '"+AssertName.get(6)+"' is available on the page or not ?");
		if(driver.findElements(TermsOfUse).size()>0){
			ActualLable("Successfully verified, '"+AssertName.get(6)+"' is Available on Page" ,"Pass");
		}else{ActualLable("Failed to verify, Assert failed for '"+AssertName.get(6)+"' on Registration User list page " ,"Fail");}
		ExpectedLable("Verify that '"+AssertName.get(7)+"' is available on the page or not ?");
		if(driver.findElements(AmNotRobotCaptcha).size()>0){
			ActualLable("Successfully verified, '"+AssertName.get(7)+"' is Available on Page" ,"Pass");
		}else{ActualLable("Failed to verify, Assert failed for '"+AssertName.get(7)+"' on Registration User list page " ,"Fail");}
		ExpectedLable("Verify that '"+AssertName.get(8)+"' is available on the page or not ?");
		if(driver.findElements(ClearAllButtonOnSingleUser).size()>0){
			ActualLable("Successfully verified, '"+AssertName.get(8)+"' is Available on Page" ,"Pass");
		}else{ActualLable("Failed to verify, Assert failed for '"+AssertName.get(8)+"' on Registration User list page " ,"Fail");}
		ExpectedLable("Verify that '"+AssertName.get(9)+"' is available on the page or not ?");
		if(driver.findElements(RequestRegistration).size()>0){
			ActualLable("Successfully verified, '"+AssertName.get(9)+"' is Available on Page" ,"Pass");
		}else{ActualLable("Failed to verify, Assert failed for '"+AssertName.get(9)+"' on Registration User list page " ,"Fail");}

	}
	public static void VerifyPrivacyPolicylink(WebDriver driver) throws IOException, WriteException, InterruptedException {
		StepLable("Verify Privacy Policy link");
		ExpectedLable("Verify that 'Privacy policy link' is available on the page or not ?");
		if(driver.findElements(PrivacyPolicyCheckBox).size()>0){
			ActualLable("Successfully verified, 'Privacy policy link' is Available on Page" ,"Pass");
			ExpectedLable("Click on 'Privacy policy link'");
			driver.findElement(PrivacyPolicyLink).click();
			ActualLable("Successfully Clicked on 'Privacy policy link'" ,"Pass");
			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			String Title = driver.getTitle();
			log.info("Title of the page is "+Title);
			ExpectedLable("Corresponding Page should be open and assert should verify");
			Assert.assertEquals(Title, "Dimension Data Privacy Policy");
			ActualLable("Privacy Policy page is opened and Assert verified ","Pass");
			driver.close();
			driver.switchTo().window(tabs.get(0));
		}else{ActualLable("Failed to verify, Assert failed for 'Privacy policy link' on Registration User list page " ,"Fail");}
	}

	public static void VerifyTermsOfUselink(WebDriver driver) throws IOException, WriteException, InterruptedException {
		StepLable("Verify Terms of Use link");
		ExpectedLable("Verify that 'Terms of Use link' is available on the page or not ?");
		if(driver.findElements(TermsOfUseLink).size()>0){
			ActualLable("Successfully verified, 'Terms of Use link' is Available on Page" ,"Pass");
			ExpectedLable("Click on 'Terms of Use link'");
			driver.findElements(TermsOfUseLink).get(0).click();
			ActualLable("Successfully Clicked on 'Terms of Use link'" ,"Pass");
			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			String Title = driver.getTitle();
			log.info("Title of the page is "+Title);
			ExpectedLable("Corresponding Page should be open and assert should verify");
			Assert.assertEquals(Title, "Terms and Conditions of Use of the Dimension Data Website");
			ActualLable("Terms of Use page is opened and Assert verified ","Pass");
			driver.close();
			driver.switchTo().window(tabs.get(0));
		}else{ActualLable("Failed to verify, Assert failed for 'Terms of Use link' on Registration User list page " ,"Fail");}
	}
}
