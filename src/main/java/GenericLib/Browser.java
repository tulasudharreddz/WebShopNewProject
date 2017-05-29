package GenericLib;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import TestRail_Inte.APIException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pageObject.HomePage;

import static GenericLib.DataDriven.ReportResult;
import static GenericLib.ObjectRepository.TimeConstatnt;
import static GenericLib.ObjectRepository.timeString;


public class Browser {

	private static WebDriver driver;
	DataDriven Broexcel = new DataDriven();
	private static int SCcount=1;
	public WebDriver getDriver() {
		return driver;
	}

	static ObjectRepository obr = new ObjectRepository();

	private void setDriver(String browser) throws Exception{
		AlertHandle.BrowserNameForSuite = browser;
		if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver","lib/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver","lib/chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "lib/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		obr.repository(driver);
		String folderName = ObjectRepository.DateSt();
		//new File(".\\ResultReports\\" + folderName + "").mkdir();
		new File(obr.obj.getProperty("CreateWorkBookPath")+"//" + folderName + "").mkdir();
		sheet = Broexcel.ReadSheet(sheet);
	}
	@AfterSuite
	public void CloseExcel() throws IOException, WriteException {
		Broexcel.closedoc();

	}
	public static String screenshots() throws IOException, WriteException {
		obr.repository(driver);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Folder = DataDriven.FolderName();
		String folderName = ObjectRepository.DateSt();
		//String name = ".\\ResultReports\\" + folderName + "\\"+Folder+"-"+TimeConstatnt()+"-screen-"+SCcount+".jpeg";
		String name = obr.obj.getProperty("CreateWorkBookPath") +"\\"+ folderName + "\\"+Folder+"-"+TimeConstatnt()+"-screen-"+SCcount+".jpeg";
		FileUtils.copyFile(scrFile, new File(name));
		SCcount++;
		return name;
	}

	@BeforeMethod
	public void Url() throws IOException, BiffException, WriteException, InterruptedException {
		obr.repository(driver);
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		driver.get(obr.obj.getProperty("url"));
	}
	@AfterMethod
	public void ResultStatus() throws WriteException, IOException, APIException { boolean testRailTCStatus =ReportResult();
		TestRail_Integration.UploadResults(testRailTCStatus);
		HomePage.count7.getAndSet(-1);
	}
}
