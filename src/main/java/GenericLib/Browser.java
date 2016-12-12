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
import jxl.write.WriteException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;


public class Browser {

	private WebDriver driver;
	DataDriven Broexcel = new DataDriven();

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browser) throws Exception{

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

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		//driver.get("https://directqa2.dimensiondata.com/Webshop/login");
	}

	private Sheet sheet;

	@Parameters("browser")
	@BeforeClass
	public void initializeTestBaseSetup(String browser) {
		try {
			setDriver(browser);


		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	@AfterClass
	public void Close() throws IOException, WriteException {
		//Broexcel.closedoc();
		driver.quit();

	}

	@BeforeSuite
	public void initializeexc() throws WriteException, IOException, BiffException {
		sheet = Broexcel.ReadSheet(sheet);
	}
	@AfterSuite
	public void CloseExcel() throws IOException, WriteException {
		Broexcel.closedoc();

	}
}
