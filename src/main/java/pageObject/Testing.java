package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by t.mirasipally on 11/9/2016.
 */
public class Testing {

    static protected WebDriver driver;

    public Testing(WebDriver driver) {
        this.driver = driver;
    }

    public static void Loginfunctionality(WebDriver driver) throws IOException, InterruptedException {

        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--single']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//li[contains(text(),'US')]")).click();

        driver.findElement(By.id("email")).sendKeys("tulasiautomation@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("abcd1234");

        driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

    }
}
