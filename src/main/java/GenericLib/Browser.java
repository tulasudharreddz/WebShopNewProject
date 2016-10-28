package GenericLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;




public class Browser {

	WebDriver driver;

	@Parameters("browser")
	public WebDriver selectbrowser(String browser){

		if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();


		} 		

		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\chromedriver.exe");
			driver = new ChromeDriver();

		}

		else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		return driver;

	}




}
