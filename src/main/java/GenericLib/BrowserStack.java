package GenericLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by t.mirasipally on 11/9/2016.
 */
public class BrowserStack {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }


    private void setDriver(String browserN, String version, String os, String osVersion, String resolution) throws Exception{

        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("os", os);
        capability.setCapability("os_version", osVersion);
        capability.setCapability("browserName", browserN);
        capability.setCapability("browserVersion", version);
        capability.setCapability("resolution", resolution);
        capability.setCapability("project", "P1");
        capability.setCapability("build", "1.0");


        driver = new RemoteWebDriver(
                //new URL("https://tulasidhar1:hM4bFqpv5Lo5Vqf4XyuB@hub-cloud.browserstack.com/wd/hub"),
                //new URL("https://password395:xzEpbNtzmWrgnBAQPA1W@hub-cloud.browserstack.com/wd/hub"),

                new URL("https://sreenipoc1:ajhxhQxrzzx482CY3RqQ@hub-cloud.browserstack.com/wd/hub" ),
                capability);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("https://directqa2.dimensiondata.com/Webshop/login");

    }
    @Parameters(value = {"browserN", "version", "os", "osVersion","resolution"})
    @BeforeClass
    public void initializeTestBaseSetup(String browserN, String version, String os, String osVersion, String resolution) {
        try {
            setDriver(browserN, version,os, osVersion, resolution);

        } catch (Exception e) {
            System.out.println("Error....." + e.getStackTrace());
        }
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
