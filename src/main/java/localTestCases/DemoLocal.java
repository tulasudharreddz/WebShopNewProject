package localTestCases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import GenericLib.*;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;

public class DemoLocal {

	public static WebDriver driver;
	Browser brow = new Browser();
	DataDriven excel = new DataDriven();
	AlertHandle popup = new AlertHandle();
	ObjectRepository ob = new ObjectRepository();
	ScreenShots sc = new ScreenShots();
	WebElement element;
	Sheet sheet;
	WritableSheet wsheet;
	Logger log = Logger.getLogger("Test Cases");

	@Parameters("browser")

	public WebDriver start(String browser) throws BiffException, IOException, RowsExceededException, WriteException, InterruptedException {
		if (browser.equalsIgnoreCase(browser)) {
			driver = brow.selectbrowser(browser);
		}
		ob.repository(driver);
		PropertyConfigurator.configure(ob.obj.getProperty("log4j"));
		driver.get(ob.obj.getProperty("url"));
		return driver;
	}
	@BeforeTest

	public WebDriver startB() throws Exception {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("browserName", "iPhone");
		capability.setCapability("platform", "MAC");
		capability.setCapability("device", "iPhone 5");
		capability.setCapability("project", "P1");
		capability.setCapability("build", "1.0");

		driver = new RemoteWebDriver(
				new URL("https://tulasidhar1:hM4bFqpv5Lo5Vqf4XyuB@hub-cloud.browserstack.com/wd/hub"),
				//new URL("https://tulasidharreddy1:f31sxqeNs6UPCinLrkD1@hub-cloud.browserstack.com/wd/hub"),
				//new URL("https://sreenipoc1:ajhxhQxrzzx482CY3RqQ@hub-cloud.browserstack.com/wd/hub" ),
				capability);

		ob.repository(driver);
		PropertyConfigurator.configure(ob.obj.getProperty("log4j"));
		//driver.get(ob.obj.getProperty("url"));
		return driver;
	}

	@Test
	public void TC_Home_01() throws Exception {

		popup.implicitlyWait(driver);
		Thread.sleep(2000);
		LoginPage.Loginfunctionality(driver);
		log.info("Login in to the webshop application");
		HomePage.ShopMenuOnHomePage(driver).click();
		log.info("Clicked on Shop menu");
		HomePage.SubCategoryListUnderShopMenu(driver).get(4).click();
		log.info("Clicked on Accesseries Sub category");
		HomePage.SearchField(driver).sendKeys("2486-825-109");
		log.info("Searched for the product with part number '2486-825-109'");
		//HomePage.SearchField(driver).submit();
		//log.info("Entered the text");
		Thread.sleep(5000);
		String PartNumber =HomePage.PartNumber(driver).get(0).getText();
		log.info("Partnumber for the product is: "+ PartNumber);
		HomePage.LearnMoreButtons(driver).get(0).click();
		log.info("Clicked on Learn more button");
		String UnitCost= HomePage.UnitCost(driver).getText();
		Thread.sleep(3000);
		log.info("Unit cost of the Selected item is : "+ UnitCost);




	}

	@Test
	public void MobileAutomation() throws Exception {
		driver.get("https://directqa2.dimensiondata.com/Webshop/login");

		popup.implicitlyWait(driver);
		Thread.sleep(2000);
		LoginPage.Loginfunctionality(driver);
		log.info("Login in to the webshop application");
		Thread.sleep(5000);
		HomePage.ClicoOnShopMenuMobile(driver);
		Thread.sleep(2000);
		String PartNumber =HomePage.PartNumber(driver).get(0).getText();
		log.info("Partnumber for the product is: "+ PartNumber);
		HomePage.LearnMoreButtons(driver).get(0).click();
		log.info("Clicked on Learn more button");
		String UnitCost= HomePage.UnitCost(driver).getText();
		Thread.sleep(3000);
		log.info("Unit cost of the Selected item is : "+ UnitCost);




	}

	@AfterTest
	public void Close() throws IOException
	{
		driver.quit();

	}

}
