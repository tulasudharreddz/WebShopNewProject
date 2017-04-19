package pageObject;

import GenericLib.ObjectRepository;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;

/**
 * Created by t.mirasipally on 11/9/2016.
 */
public class UITesting {

    public static WebElement element;
    public static By by;
    static protected WebDriver driver;
    static protected ObjectRepository obje = new ObjectRepository();

    //Page Elements
    static private By HeaderOne = By.xpath("//h1");
    static private By HeaderTwo = By.xpath("//h2");
    static private By HeaderThree = By.xpath("//h3");
    static private By Breadcrumb = By.xpath("//ol[@class='breadcrumb']/li/a");
    static private By FooterLink = By.xpath("//is-footer/div/div[2]/ul/li[1]/a");


    public static void HomePageUIVerification(WebDriver driver) throws IOException, InterruptedException, WriteException {

        ArrayList<By> Elements=new ArrayList<By>();
        Elements.add(HeaderOne);
        Elements.add(HeaderTwo);
        Elements.add(HeaderThree);
        Elements.add(Breadcrumb);
        Elements.add(FooterLink);

        ArrayList<String> ElementNames=new ArrayList<String>();
        ElementNames.add("Header One");
        ElementNames.add("Header Two");
        ElementNames.add("Header Three");
        ElementNames.add("Breadcrumb");
        ElementNames.add("Footer Link");

        ArrayList<String> GetAttribute=new ArrayList<String>();
        GetAttribute.add("color");
        GetAttribute.add("font-family");
        GetAttribute.add("font-size");//text-align

        ArrayList<String> ExpectedFontColor=new ArrayList<String>();
        ExpectedFontColor.add("#455565");
        ExpectedFontColor.add("#455565");
        ExpectedFontColor.add("#455565");
        ExpectedFontColor.add("#0784c1");

        ArrayList<String> ExpectedFontSize=new ArrayList<String>();
        ExpectedFontSize.add("32px");
        ExpectedFontSize.add("24px");
        ExpectedFontSize.add("21px");
        ExpectedFontSize.add("13px");

        ArrayList<String> ExpectedFontType=new ArrayList<String>();
        ExpectedFontType.add("Source Sans Pro");
        ExpectedFontType.add("Source Sans Pro");
        ExpectedFontType.add("Source Sans Pro");
        ExpectedFontType.add("Source Sans Pro");

        for(int i=0;i<=3;i++) {
            ExpectedLable("Check the ' "+ElementNames.get(i)+" ' is available or not?");
            if (driver.findElements(Elements.get(i)).size() > 0) {
                ActualLable("' "+ElementNames.get(i)+" ' found successfully", "Pass");
                //Fount Color Verification
                ExpectedLable("Get the value of "+GetAttribute.get(0));
                String ActualFontColor = driver.findElements(Elements.get(i)).get(0).getCssValue(GetAttribute.get(0));
                String s1 = ActualFontColor.substring(5);
                StringTokenizer st = new StringTokenizer(s1);
                int r = Integer.parseInt(st.nextToken(",").trim());
                int g = Integer.parseInt(st.nextToken(",").trim());
                int b = Integer.parseInt(st.nextToken(",").trim());
                Color c = new Color(r, g, b);
                String hex = "#"+Integer.toHexString(c.getRGB()).substring(2);
                System.out.println(hex);
                ActualLable("Actual value of "+GetAttribute.get(0)+" is "+ hex, "Pass");
                ExpectedLable("Compare Attribute value of ' "+GetAttribute.get(0)+" ' with expected value");
                if (ExpectedFontColor.get(i).contentEquals(hex)) {
                    ActualLable("Successfully verified,Expected value : ' " + ExpectedFontColor.get(i) + " ' and Actual Value : " + hex + "", "Pass");
                }else{ActualLable("Verification failed, Expected value : ' " + ExpectedFontColor.get(i) + " ' and Actual Value : " + hex + "","Fail");}

                //Font Family
                ExpectedLable("Get the value of "+GetAttribute.get(1));
                String ActualFontFamily = driver.findElements(Elements.get(i)).get(0).getCssValue(GetAttribute.get(1));
                ActualLable("Actual value of ' "+GetAttribute.get(1)+" ' is "+ ActualFontFamily, "Pass");
                String resultActualFontFamily = ActualFontFamily.replace("\"","");
                ExpectedLable("Compare Attribute value of ' "+GetAttribute.get(1)+" ' with expected value");
                if (ExpectedFontType.get(i).contentEquals(resultActualFontFamily)) {
                    ActualLable("Successfully verified,Expected value : ' " + ExpectedFontType.get(i) + " ' and Actual Value : ' " + resultActualFontFamily + " '", "Pass");
                }else{ActualLable("Verification failed, Expected value :' " + ExpectedFontType.get(i) + " ' and Actual Value : ' " + resultActualFontFamily + " '","Fail");}

                //Font Size
                ExpectedLable("Get the value of "+GetAttribute.get(2));
                String ActualFontSize = driver.findElements(Elements.get(i)).get(0).getCssValue(GetAttribute.get(2));
                ActualLable("Actual value of ' "+GetAttribute.get(2)+" ' is : "+ ActualFontSize, "Pass");
                ExpectedLable("Compare Attribute value of ' "+GetAttribute.get(2)+" ' with expected value");
                if (ExpectedFontSize.get(i).contentEquals(ActualFontSize)) {
                    ActualLable("Successfully verified,Expected value : '" + ExpectedFontSize.get(i) + " ' and Actual Value :' " + ActualFontSize + " '", "Pass");
                }else{ActualLable("Verification failed, Expected value :' " + ExpectedFontSize.get(i) + " ' and Actual Value :' " + ActualFontSize + " '","Fail");}

            }else{ ActualLable("Element Not found","Fail");}

        }
    }


}
