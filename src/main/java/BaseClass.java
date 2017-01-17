import GenericLib.Browser;
import GenericLib.DataDriven;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by t.mirasipally on 09-Dec-16.
 */
public class BaseClass {
    private WebDriver driver;
    private Sheet sheet;
    private WritableSheet wsheet;
    DataDriven Baseexcel = new DataDriven();

    @Test
    public void Starttest() throws WriteException, IOException, BiffException, InterruptedException {

        System.setProperty("webdriver.chrome.driver","lib/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://accounts.google.com/AddSession?sacu=1&continue=https%3A%2F%2Fmyaccount.google.com%2F%3Futm_source%3DOGB%26pli%3D1&service=accountsettings#identifier");

        driver.findElement(By.id("Email")).sendKeys("TetingText");
        Thread.sleep(5000);
        driver.findElement(By.id("Email")).getAttribute("Value");
        System.out.println("Out put text is: " +driver.findElement(By.id("Email")).getAttribute("value"));
    }





}
