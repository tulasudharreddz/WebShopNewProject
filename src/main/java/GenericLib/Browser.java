package GenericLib;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;




public class Browser {

	WebDriver driver;
	Workbook book;
	WritableWorkbook wbook;
	Sheet sheet;
	WritableSheet wsheet;
	DataDriven excel = new DataDriven();

	@Parameters("browser")
	public WebDriver selectbrowser(String browser) throws IOException, BiffException {
		sheet = excel.ReadSheet(sheet);
		//wsheet = excel.writeSheet(wsheet, "test", "TestCase1");
		int num_rows = sheet.getRows();
		System.out.println("Number of rows in sheet: " + num_rows);

		if (browser.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();


		}

		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"lib/chromedriver.exe");
			driver = new ChromeDriver();

		}

		else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "D:\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if (browser.equalsIgnoreCase("BrowserStack")) {

			DesiredCapabilities capability = new DesiredCapabilities();

			for(int i = 7;i<=9;i++) {
				capability.setCapability("os", sheet.getCell(1, i).getContents());
				capability.setCapability("os_version", sheet.getCell(2, i).getContents());
				capability.setCapability("browserName", sheet.getCell(3, i).getContents());
				capability.setCapability("browserVersion", sheet.getCell(4, i).getContents());
				capability.setCapability("resolution", sheet.getCell(5, i).getContents());
				capability.setCapability("project", sheet.getCell(6, i).getContents());
				capability.setCapability("build", sheet.getCell(7, i).getContents());



			driver = new RemoteWebDriver(
					new URL("https://tulasidhar1:hM4bFqpv5Lo5Vqf4XyuB@hub-cloud.browserstack.com/wd/hub"),
					//new URL("https://tulasidharreddy1:f31sxqeNs6UPCinLrkD1@hub-cloud.browserstack.com/wd/hub"),
					//new URL("https://sreenipoc1:ajhxhQxrzzx482CY3RqQ@hub-cloud.browserstack.com/wd/hub" ),
					capability);
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		return driver;

	}




}
