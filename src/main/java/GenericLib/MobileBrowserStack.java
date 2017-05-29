package GenericLib;

import TestRail_Inte.APIException;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pageObject.HomePage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static GenericLib.DataDriven.ReportResult;
import static GenericLib.ObjectRepository.TimeConstatnt;

/**
 * Created by t.mirasipally on 11/10/2016.
 */
public class MobileBrowserStack {

    private static WebDriver driver;
    DataDriven Broexcel = new DataDriven();
    private static int SCcount=1;
    public WebDriver getDriver() {
        return driver;
    }

    static ObjectRepository obr = new ObjectRepository();

    private static void setDriver(String browserName, String platform, String device) throws Exception{

        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("browserName", browserName);
        capability.setCapability("platform", platform);
        capability.setCapability("device", device);
        capability.setCapability("project", "P1");
        capability.setCapability("build", "1.0");

        AlertHandle.BrowserNameForSuite = "iPhone";
        driver = new RemoteWebDriver(
                new URL("https://anagani1:g6GPQvPnHxUusvQFqJZc@hub-cloud.browserstack.com/wd/hub"),
                capability);
        /*driver = new RemoteWebDriver(
                //new URL("https://tulasidhar1:hM4bFqpv5Lo5Vqf4XyuB@hub-cloud.browserstack.com/wd/hub"),
                //new URL("https://password395:xzEpbNtzmWrgnBAQPA1W@hub-cloud.browserstack.com/wd/hub"),
                new URL("https://sreenipoc1:ajhxhQxrzzx482CY3RqQ@hub-cloud.browserstack.com/wd/hub" ),
                capability);*/

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.get("https://directqa2.dimensiondata.com/Webshop/login");

    }
    private Sheet sheet;
    @Parameters(value = {"browserName", "platform", "device"})
    @BeforeClass
    public void initializeTestBaseSetup(String browserName, String platform, String device) {
        try {
            setDriver(browserName,  platform, device);

        } catch (Exception e) {
            System.out.println("Error....." + e.getStackTrace());
        }
    }
    @AfterClass
    public void tearDown() {
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
        HomePage.count7.getAndSet(-1);}
}
