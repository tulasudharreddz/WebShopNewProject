package pageObject;

import GenericLib.DataDriven;
import GenericLib.ObjectRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import GenericLib.*;
import pageObject.*;

/**
 * Created by t.mirasipally on 14-Apr-17.
 */
public class JiraAccess {
    static protected ObjectRepository obrJira = new ObjectRepository();
    public static WebElement element;
    static protected WebDriver second_driver;
    static protected WebDriver driver;
    public static By by;

    //Page Elements
    static private By UserName = By.xpath("//input[@id='username']");
    static private By Password = By.xpath("//input[@id='password']");
    static private By LoginButton = By.xpath("//button[@id='login']");
    static private By Header = By.xpath("//div[@class='aui-page-header-main']/h1");
    static private By CreateIssueButton = By.xpath("//a[@id='create_link']");
    static private By ProjectNameDD = By.xpath("//input [@id='project-field']");
    static private By IssueTypeDD = By.xpath("//input[@id='issuetype-field']");
    static private By NextButton = By.xpath("//input[@id='issue-create-submit']");
    static private By Projectname = By.xpath("//h5[contains(text(),'All projects')]/following-sibling::ul/li/a[contains(text(),'Agile automation')]");
    static private By IssueType = By.xpath("//div[@id='issuetype-suggestions']/div/ul/li/a[contains(text(),'Bug')]");
    static private By IssueSummery = By.xpath("//input[@id='summary']");
    static private By IssueDescription = By.xpath("//textarea[@id='description']");
    static private By IssueEnvironment = By.xpath("//textarea[@id='environment']");
    static private By browseSc = By.xpath("//button[contains(text(),'browse')]");
    static private By AssignToMe = By.xpath("//a[@id='assign-to-me-trigger']");
    static private By ConfirmCreateIssueButton = By.xpath("//input[@id='issue-create-submit']|//*[@id='create-issue-submit']");
    static private By IssueNumber = By.xpath("//a[@class='issue-created-key issue-link']");
    static private By IssueNumber1 = By.xpath("//a[@class='issue-created-key issue-link']");
    static private By UserProfileIcon = By.xpath("//a[@id='header-details-user-fullname']");
    static private By LogOutButton = By.xpath("//a[@id='log_out']");
    static private By ConfirmLogOut = By.xpath("//button[@id='logout']");


    public static String JiraFunctionality(WebDriver driver,String ScPath) throws InterruptedException, AWTException {
        //String Parent_Window = driver.getWindowHandle();
        second_driver = new ChromeDriver();
        second_driver.manage().window().maximize();
        second_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String IssueNumber = null;
        //Keys.chord(Keys.CONTROL,"N");
        IssueNumber= CreateIssue(second_driver,ScPath);
        //driver.(Keys.CONTROL + 'N')
        //Keys.chord(Keys.CONTROL,"t");
        // Switching from parent window to child window
        /*for (String Child_Window : driver.getWindowHandles()) {
            driver.switchTo().window(Child_Window);
            // Performing actions on child window
            IssueNumber= CreateIssue(driver);
        }
        //driver.quit();
        driver.switchTo().window(Parent_Window);*/
        second_driver.quit();
        return IssueNumber;
    }

    public static void JiraLoginFun(WebDriver second_driver)  {
        //second_driver.get(obrJira.obj.getProperty("JiraUrl"));
        second_driver.get("https://demoversion3.atlassian.net/login");
        second_driver.findElement(UserName).sendKeys("admin");
        second_driver.findElement(Password).sendKeys("abcd1234");
        /*second_driver.findElement(UserName).sendKeys(obrJira.obj.getProperty("JiraUserName"));
        second_driver.findElement(Password).sendKeys(obrJira.obj.getProperty("JiraPassword"));*/
        second_driver.findElement(LoginButton).click();
    }
    public static ArrayList<String> JiraIssueArray=new ArrayList<String>();
    public static String CreateIssue(WebDriver second_driver,String Path) throws InterruptedException, AWTException {
        String IssueAttr = null;
        JiraLoginFun(second_driver);
        if(second_driver.findElement(Header).getText().contentEquals("System dashboard")){
            second_driver.findElement(CreateIssueButton).click();

            /*second_driver.findElement(ProjectNameDD).clear();
            second_driver.findElement(ProjectNameDD).click();
            second_driver.findElement(Projectname).click();*/
            second_driver.findElement(IssueTypeDD).clear();
            second_driver.findElement(IssueTypeDD).sendKeys("Bug");
            second_driver.findElement(IssueTypeDD).sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            //String IssueSummeryTxt= "Verification failed for "+ DataDriven.LetestStep()+" Functionality";
            String IssueSummeryTxt= "Verification failed for "+ DataDriven.ScenarioNameForJira;
            String IssueDescriptionTxt =  IssueSummeryTxt +"\n"+"Expected result is :"+DataDriven.LetestExpected()+ "\n"+" Actual result is: "+DataDriven.LetestActual();
            String IssueEnvironmentTxt= "OS: Windows"+"\n"+" Application: QA2, "+"\n"+" Browser: "+AlertHandle.BrowserNameForSuite;
            if(second_driver.findElements(IssueSummery).size()>0){
                second_driver.findElement(IssueSummery).clear();
                Thread.sleep(1000);
                second_driver.findElement(IssueSummery).sendKeys(IssueSummeryTxt);
                Thread.sleep(1000);
                second_driver.findElement(IssueDescription).clear();
                Thread.sleep(1000);
                second_driver.findElement(IssueDescription).sendKeys(IssueDescriptionTxt);
                Thread.sleep(1000);
                second_driver.findElement(IssueEnvironment).clear();
                Thread.sleep(1000);
                second_driver.findElement(IssueEnvironment).sendKeys(IssueEnvironmentTxt);
                //Screen shot attachment
                Thread.sleep(1000);
                second_driver.findElement(browseSc).click();
                Thread.sleep(2000);
                StringSelection ss = new StringSelection(Path);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                //imitate mouse events like ENTER, CTRL+C, CTRL+V
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(10000);
                second_driver.findElement(AssignToMe).click();
                Thread.sleep(1000);
                second_driver.findElement(ConfirmCreateIssueButton).click();
                Thread.sleep(1000);
                if(second_driver.findElements(IssueNumber).size()>0){
                    IssueAttr = second_driver.findElement(IssueNumber).getAttribute("data-issue-key");
                    System.out.println("Issue ID attr"+IssueAttr);
                    JiraIssueArray.add(IssueAttr);
                    /*IssueID = second_driver.findElement(IssueNumber).getText();
                    System.out.println("Issue ID "+IssueID);*/
                }else{System.out.println("Issue ID not found");}
                //second_driver.findElement(UserProfileIcon).click();
                //driver.findElement(LogOutButton).click();
                //driver.findElement(ConfirmLogOut).click();
            }
            else if(second_driver.findElements(NextButton).size()>0){
                second_driver.findElement(NextButton).click();
                second_driver.findElement(IssueSummery).clear();
                second_driver.findElement(IssueSummery).sendKeys(IssueSummeryTxt);
                second_driver.findElement(IssueDescription).clear();
                second_driver.findElement(IssueDescription).sendKeys(IssueDescriptionTxt);
                second_driver.findElement(IssueEnvironment).clear();
                second_driver.findElement(IssueEnvironment).sendKeys(IssueEnvironmentTxt);
                second_driver.findElement(AssignToMe).click();
                second_driver.findElement(ConfirmCreateIssueButton).click();
                if(second_driver.findElements(IssueNumber).size()>0){
                    IssueAttr = second_driver.findElement(IssueNumber).getAttribute("data-issue-key");
                    System.out.println("Issue ID attr"+IssueAttr);
                    JiraIssueArray.add(IssueAttr);
                    /*IssueID = second_driver.findElement(IssueNumber).getText();
                    System.out.println("Issue ID "+IssueID);*/
                }else{System.out.println("Issue ID not found");}
                /*second_driver.findElement(UserProfileIcon).click();
                second_driver.findElement(LogOutButton).click();
                second_driver.findElement(ConfirmLogOut).click();*/
            }else{System.out.println("summery not found");}
        }else{System.out.println("Login failed to jira");}
        return IssueAttr;
    }

    public static String TestRailDescriptionText(){
        int NoofIssues=JiraIssueArray.size();
        String  TestRailDescription = "TestCase Verification failed and Issue list raised in Jira is : ";
       for(int i=0;i<NoofIssues;i++){
           String text = JiraIssueArray.get(i);
           TestRailDescription = TestRailDescription+ text+", ";
       }
        return TestRailDescription;
    }


}
