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

    // a class that extends thread that is to be called when program is exiting
    static class Message extends Thread {

        public void run() {
            System.out.println("Bye.");
        }
    }

    public static void main(String[] args) {
        try {

            // register Message as shutdown hook
            Runtime.getRuntime().addShutdownHook(new Message());

            // print the state of the program
            System.out.println("Program is starting...");

            // cause thread to sleep for 3 seconds
            System.out.println("Waiting for 3 seconds...");
            Thread.sleep(3000);

            // print that the program is closing
            System.out.println("Program is closing...");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
