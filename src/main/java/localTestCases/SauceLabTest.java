package localTestCases;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;

public class SauceLabTest {
	
	
	WebDriver driver;
	String baseUrl, nodeURL;

	@BeforeTest
	public void test() throws MalformedURLException{
		
		

		baseUrl ="https://directqa2.dimensiondata.com/Webshop/login";
		nodeURL = "http://tulasi1:7e0073b9-e50c-478d-9b1d-e1542bea4bc4@ondemand.saucelabs.com:80/wd/hub";

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("chrome");
		
		
		

		driver = new RemoteWebDriver(new URL(nodeURL), capability);
	}


	@AfterTest
	public void test2(){

		driver.quit();


	}

	@Test
	public void test3() throws InterruptedException, IOException {

		driver.get(baseUrl);
		
		Thread.sleep(3000);		

		LoginPage.PageTitle(driver);
		
		LoginPage.Loginfunctionality(driver);

		HomePage.PageTitle(driver);

		
		


	}

}
