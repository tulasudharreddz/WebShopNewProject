package pageObject;

import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 18-Jan-17.
 */
public class OrdersPage {

    static protected WebDriver driver;
    static Logger log = Logger.getLogger("Orders Page");


    //Page Elements
    static private By QuoteDateElement = By.xpath("//span[contains(text(),'Date :')]/following-sibling::span");
    static private By OrderPageTitleElement = By.xpath("//h2");
    static private By FirstOrderElement = By.xpath("//div[@class='panel-group panel-group-info']/div[1]");
    static private By CollapseBlock = By.xpath("//a[@class='accordion-toggle']");
    static private By ExpandedFirstOrderElement = By.xpath("//div[@class='panel-group panel-group-info']/div[1]/is-tabsetpanel/div/div[@class='panel-collapse collapse in']");
    static private By OrderHeaderTitle = By.xpath("//div[@class='row']/div/span[1]");
    static private By NoOfOrdersElement = By.xpath("//div[@class='panel-group panel-group-info']/div");
    static private By DocumentElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Document #')]");
    static private By SapElement = By.xpath("//div[@class='row']/div/span[contains(text(),'SAP #')]");
    static private By DateElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Date :')]");
    static private By PriceElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Price :')]");
    static private By StatusElement = By.xpath("//div[@class='row']/div/span[contains(text(),'Status :')]");
    static private By ImageElement = By.xpath("//img[@class='img-responsive']");
    static private By NameElement = By.xpath("//p[@class='product-name clickable']/a");
    static private By FieldMfrElement = By.xpath("//span[@class='field-name'][contains(text(),'Mfr Part#')]");
    static private By FieldManufacturerElement = By.xpath("//span[@class='field-name'][contains(text(),'Manufacturer:')]");
    static private By FieldQuantityElement = By.xpath("//div[contains(text(),'Quantity')]");
    static private By UnitPriceElement = By.xpath("//div[contains(text(),'Unit Price')]");
    static private By ExtendedPriceElement = By.xpath("//div[contains(text(),'Extended Price')]");
    static private By SerialElement = By.xpath("//div[contains(text(),'Serial #')]");
    static private By FielldStatusElement = By.xpath("//div[contains(text(),'Status')]");
    static private By RequestReturnElement = By.xpath("//a[contains(text(),'Request Return')]");
    static private By FilterBlockElement = By.xpath("//a[contains(text(),'Filter')]");
    static private By DateRangeFilterElement = By.xpath("//span[@class='select2-selection__rendered']");
    static private By Last7DaysElement = By.xpath("//li[contains(text(),'Last 7 Days')]");
    static private By Last30DaysElement = By.xpath("//li[contains(text(),'Last 30 Days')]");
    static private By Last60DaysElement = By.xpath("//li[contains(text(),'Last 60 Days')]");
    static private By NeverDaysElement = By.xpath("//li[contains(text(),'Custom')][@data-index='3']");
    static private By TotalNoOfOrdersElement = By.xpath("//p[contains(text(),'Total Orders')]");
    static private By LastPageElement = By.xpath("//a[@class='next']/parent::li/preceding-sibling::li[1]/a");
    static private By StartDateElement = By.xpath("//is-datepicker[@id='startDate']/div/input");
    static private By EndDateElement = By.xpath("//is-datepicker[@id='endDate']/div/input");
    static private By PreviousMonthElement = By.xpath("//a[@class='datepicker-prev']");
    static private By SelectFstStartDateElement = By.xpath("(//is-datepicker[@id='startDate']/div/div/ul/li/div/div/table/tbody/tr/td[contains(text(),'4')])[1]");
    static private By GetMonthStartDateElement = By.xpath("//*[@id='startDate']/div/div/ul/li/div/div/table/thead/tr[1]/th[2]");
    static private By GetMonthEndDateElement = By.xpath("//*[@id='endDate']/div/div/ul/li/div/div/table/thead/tr[1]/th[2]");
    static private By SelectSecondEndDateElement = By.xpath("(//is-datepicker[@id='endDate']/div/div/ul/li/div/div/table/tbody/tr/td[contains(text(),'24')])[1]");
    static private By DocumentLink = By.xpath("//span[contains(text(),'Document #')]/following-sibling::span");
    static private By StatusLink = By.xpath("//span[contains(text(),'Status :')]/following-sibling::span");
    static private By  DocumentBreadCrumb= By.xpath("//ol[@class='breadcrumb']/li[3]/span");




    public static void VerifyExpandAndCollapse(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Check that the first listed order is expanded or collapsed ?");
        if(driver.findElements(ExpandedFirstOrderElement).size()>0){
            ActualLable("Verified successfully, first listed order is expanded", "Pass");
            ExpectedLable("Now try to collapse the order details ");
            driver.findElement(CollapseBlock).click();
            if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                ActualLable("Verification failed , Collapse functionality is not working", "Fail");
            }
            else{
                ActualLable("Verified successfully, Collapse functionality is working properly", "Pass");
                ExpectedLable("Now try to Expand the first order details ");
                driver.findElement(FirstOrderElement).click();
                if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                    ActualLable("Verified successfully, Expand functionality is working properly", "Pass");
                }
                else{
                    ActualLable("Verification failed , Expand functionality is not working", "Fail");
                }
            }
        }
        else{
            ActualLable("Verified successfully, first listed order is not expanded", "Pass");
            ExpectedLable("Now try to Expand the first order details ");
            driver.findElement(FirstOrderElement).click();
            if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                ActualLable("Verified successfully, Expand functionality is working properly", "Pass");
                ExpectedLable("Now try to collapse the order details ");
                driver.findElement(CollapseBlock).click();
                if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                    ActualLable("Verification failed , Collapse functionality is not working", "Fail");
                }
                else {
                    ActualLable("Verified successfully, Collapse functionality is working properly", "Pass");
                }
            }
            else{
                ActualLable("Verification failed , Expand functionality is not working", "Fail");
            }
        }
    }
    public static boolean VerifyTheOrdersDisplay(WebDriver driver) throws IOException, WriteException, InterruptedException, ParseException {
        boolean Status = true;
        if(driver.findElements(QuoteDateElement).size()>0){
            int NoOfOrders=driver.findElements(QuoteDateElement).size();
            String DateOfFirstOrder = driver.findElements(QuoteDateElement).get(0).getText();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date date1 = sdf.parse(DateOfFirstOrder);
            for(int i=1;i<=NoOfOrders-1;i++) {
                String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(i).getText();
                Date date2 = sdf.parse(DateOfRemainOrders);
                if (date1.compareTo(date2) >= 0) {
                }
                else{
                    Status=false;
                }
            }
        }
        return Status;
    }
    public static void VerifyTheOrdersSequenceFunctionality(WebDriver driver) throws IOException, WriteException, ParseException, InterruptedException {
        ExpectedLable("Verify sequence of the listed Order should be how date to low date");
        boolean Statusresult=OrdersPage.VerifyTheOrdersDisplay(driver);
        if(Statusresult==true){
            ActualLable("Verified successfully, order sequence functionality is working properly", "Pass");
        }
        else {
            ActualLable("Failed order sequence functionality", "Fail");
        }
    }
    public static void VerifyOrderPageTitle(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Check that Title for Page is displaying or not ?");
        if(driver.findElements(OrderPageTitleElement).size()>0) {
            Thread.sleep(2000);
            String pageTitle = driver.findElement(OrderPageTitleElement).getText();
            if (pageTitle.contentEquals("Orders")) {
                ActualLable("'Orders page' Title Verified successfully", "Pass");
            } else {
                ActualLable("Verification failed for 'Orders page' Title", "Fail");
            }
        }
        else{ ActualLable("Verification failed for 'Orders page' Title", "Fail");}
    }
    public static void VerifyHeaderInformationAtOrderLevel(WebDriver driver) throws IOException, WriteException, InterruptedException {
        StepLable("Verify Orders page contents");
        VerifyOrderPageTitle(driver);
        VerifyIndividualItemLevel(driver);
        ArrayList<By> AssertXpath=new ArrayList<org.openqa.selenium.By>();
        AssertXpath.add(DocumentElement);
        AssertXpath.add(SapElement);
        AssertXpath.add(DateElement);
        AssertXpath.add(PriceElement);
        AssertXpath.add(StatusElement);
        int noOfOrder = driver.findElements(NoOfOrdersElement).size();
        for(int i=0;i<=4;i++) {
            if (driver.findElements(AssertXpath.get(i)).size() > 0) {
                ExpectedLable("Check that ' "+driver.findElement(AssertXpath.get(i)).getText()+" ' is displaying on all orders or not ?");
                int noOfDocument = driver.findElements(AssertXpath.get(i)).size();
                if (noOfOrder == noOfDocument) {
                    ActualLable("Verified successfully, ' "+driver.findElement(AssertXpath.get(i)).getText()+" ' is displaying on all orders", "Pass");
                }
                else{
                    ActualLable("Verification Failed, ' "+driver.findElement(AssertXpath.get(i)).getText()+" ' is not displaying on all orders", "Fail");
                }
            }
        }
    }
    public static void VerifyIndividualItemLevel(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ArrayList<By> AssertXpath=new ArrayList<org.openqa.selenium.By>();
        AssertXpath.add(ImageElement);
        AssertXpath.add(NameElement);
        AssertXpath.add(FieldMfrElement);
        AssertXpath.add(FieldQuantityElement);
        AssertXpath.add(UnitPriceElement);
        AssertXpath.add(ExtendedPriceElement);
        AssertXpath.add(SerialElement);
        AssertXpath.add(FielldStatusElement);
        AssertXpath.add(RequestReturnElement);
        ArrayList<String> Assert1Xpath=new ArrayList<String>();
        Assert1Xpath.add("Image");
        Assert1Xpath.add("Name");
        Assert1Xpath.add("Mfr");
        Assert1Xpath.add("Quantity");
        Assert1Xpath.add("UnitPrice");
        Assert1Xpath.add("Extended Price");
        Assert1Xpath.add("Serial");
        Assert1Xpath.add("Fielld Status");
        Assert1Xpath.add("Request Return button");

        for(int i=0;i<=8;i++) {
            ExpectedLable("Check that '" +Assert1Xpath.get(i)+ "' is displaying on orders or not ?");
            Thread.sleep(1000);
            if (driver.findElements(AssertXpath.get(i)).size() > 0) {
                ActualLable("Verification Successful, '" +Assert1Xpath.get(i)+ "' is displaying on orders", "Pass");
            }
            else{
                ActualLable("Verification Failed, '" +Assert1Xpath.get(i)+ "' is not displaying", "Fail");
            }
        }

    }
    public static Date PrevoiusDateFromToday(int noOfDays){

        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        Date now = new Date();
        System.out.println(dateFormat.format(now));
        Date addedDate = addDays(now, noOfDays);
        System.out.println(dateFormat.format(addedDate));
        return addedDate;
    }
    public static Date addDays(Date d, int days){
        d.setTime(d.getTime() - (long)days * 1000 * 60 * 60 * 24);
        return d;
    }
    public static boolean VerifyFilterWithDaterange(WebDriver driver) throws IOException, WriteException, ParseException, InterruptedException {
        StepLable("Verify Filter Functionality ");
        boolean Status = true;
        ArrayList<By> AssertXpath=new ArrayList<org.openqa.selenium.By>();
        AssertXpath.add(Last7DaysElement);
        AssertXpath.add(Last30DaysElement);
        AssertXpath.add(Last60DaysElement);
        AssertXpath.add(NeverDaysElement);
        Thread.sleep(5000);
        ExpectedLable("Check that Filter block is available on Orders page.? if yes click on it.");
        if(driver.findElements(FilterBlockElement).size()>0){
            driver.findElement(FilterBlockElement).click();
            ActualLable("Filter block is available and clicked on it", "Pass");
            ExpectedLable("Now Check for Date Range Filter block is available on Orders page.? if yes click on it.");
            if(driver.findElements(DateRangeFilterElement).size()>0){
                ActualLable("Date Range Filter block is available", "Pass");

                for(int i=0;i<=3;i++){
                    ExpectedLable("Now Click on Date Range Filter");
                    driver.findElements(DateRangeFilterElement).get(0).click();
                    ActualLable("Successfully clicked on Date Range Filter", "Pass");
                    ExpectedLable("Now Select Date Range from drop down ");
                    Thread.sleep(2000);
                    driver.findElement(AssertXpath.get(i)).click();
                    ActualLable("Successfully Selected Date Range '"+driver.findElements(DateRangeFilterElement).get(0).getText()+" '", "Pass");
                    //int noOfPages= TotalNoOfOrders();
                    if(i==0){
                        int NoOfOrders=driver.findElements(QuoteDateElement).size();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date date1 = new Date();
                        Date date3 =PrevoiusDateFromToday(10);
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+date1+" and "+date3+" in first page of order list " );
                        for(int j=1;j<=NoOfOrders-1;j++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(j).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((date1.compareTo(date2) >= 0)&& (date3.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by ' Past 7 Days' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        ExpectedLable("Now Click on last page of orders after sorting by ' Past 7 Days'" );
                        driver.findElement(LastPageElement).click();
                        ActualLable("Successfully verified filtering orders by ' Past 7 Days' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        int NoOfOrders1=driver.findElements(QuoteDateElement).size();
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+date1+" and "+date3+" in Last page of order list " );
                        for(int k=1;k<=NoOfOrders1-1;k++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(k).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((date1.compareTo(date2) >= 0)&& (date3.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by ' Past 7 Days' on Last page of result page", "Pass");

                        ExpectedLable("Now Verify the result for filtering orders by ' Past 7 Days' " );
                        if(Status==true){
                            ActualLable("Successfully verified filtering orders by ' Past 7 Days' ", "Pass");
                        }else {  ActualLable("Verification Failed for filtering orders by ' Past 7 Days'", "Fail");}
                    }
                    if(i==1){
                        Status = true;
                        int NoOfOrders=driver.findElements(QuoteDateElement).size();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date date1 = new Date();
                        Date date3 =PrevoiusDateFromToday(30);
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+date1+" and "+date3+" in first page of order list " );
                        for(int j=1;j<=NoOfOrders-1;j++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(j).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((date1.compareTo(date2) >= 0)&& (date3.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by ' Past 30 Days' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        ExpectedLable("Now Click on last page of orders after sorting by ' Past 7 Days'" );
                        driver.findElement(LastPageElement).click();
                        ActualLable("Successfully verified filtering orders by ' Past 30 Days' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        int NoOfOrders1=driver.findElements(QuoteDateElement).size();
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+date1+" and "+date3+" in Last page of order list " );
                        for(int k=1;k<=NoOfOrders1-1;k++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(k).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((date1.compareTo(date2) >= 0)&& (date3.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by ' Past 30 Days' on Last page of result page", "Pass");
                        ExpectedLable("Now Verify the result for filtering orders by ' Past 30 Days' " );
                        if(Status==true){
                            ActualLable("Successfully verified filtering orders by ' Past 30 Days' ", "Pass");
                        }else {  ActualLable("Verification Failed for filtering orders by ' Past 30 Days'", "Fail");}
                    }
                    if(i==2){
                        Status = true;
                        int NoOfOrders=driver.findElements(QuoteDateElement).size();
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date date1 = new Date();
                        Date date3 =PrevoiusDateFromToday(60);
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+date1+" and "+date3+" in first page of order list " );
                        for(int j=1;j<=NoOfOrders-1;j++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(j).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((date1.compareTo(date2) >= 0)&& (date3.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by ' Past 60 Days' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        ExpectedLable("Now Click on last page of orders after sorting by ' Past 60 Days'" );
                        driver.findElement(LastPageElement).click();
                        ActualLable("Successfully verified filtering orders by ' Past 60 Days' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        int NoOfOrders1=driver.findElements(QuoteDateElement).size();
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+date1+" and "+date3+" in Last page of order list " );
                        for(int k=1;k<=NoOfOrders1-1;k++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(k).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((date1.compareTo(date2) >= 0)&& (date3.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by ' Past 60 Days' on Last page of result page", "Pass");
                        ExpectedLable("Now Verify the result for filtering orders by ' Past 60 Days' " );
                        if(Status==true){
                            ActualLable("Successfully verified filtering orders by ' Past 60 Days' ", "Pass");
                        }else {  ActualLable("Verification Failed for filtering orders by ' Past 60 Days'", "Fail");}
                    }
                    if(i==3){
                        Status=true;;
                        Thread.sleep(1000);
                        ExpectedLable("Now CLick on Start Date Blank to select manual search." );
                        driver.findElement(StartDateElement).click();
                        Thread.sleep(1000);
                        ActualLable("Successfully Clicked on Start Date Blank to select manual search. ", "Pass");
                        ExpectedLable("Now select Start date from calendar" );
                        driver.findElements(PreviousMonthElement).get(0).click();
                        Thread.sleep(1000);
                        String MonthAndYearStartDate = driver.findElement(GetMonthStartDateElement).getText();
                        String[] terms = MonthAndYearStartDate.split("\\,");
                        String t= terms[1];
                        String Year = t.replaceAll(" ", "");
                        String t1= terms[0];
                        String Month = t1.replaceAll(" ", "");
                        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(Month);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int month = cal.get(Calendar.MONTH)+1;
                        System.out.println(month);
                        String FinalStartDate = +month+"/4/"+Year;

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date startDate = sdf.parse(FinalStartDate);
                        driver.findElement(SelectFstStartDateElement).click();
                        //String FinalStartDate = "04, "+MonthAndYearStartDate;
                        System.out.println(startDate);
                        ActualLable("Successfully selected start date from calendar i.e "+FinalStartDate, "Pass");
                        ExpectedLable("Now select End date from calendar" );
                        Thread.sleep(1000);
                        driver.findElement(EndDateElement).click();
                        Thread.sleep(1000);
                        driver.findElements(PreviousMonthElement).get(1).click();
                        Thread.sleep(1000);
                        String MonthAndYearEndDate = driver.findElement(GetMonthEndDateElement).getText();
                        String[] terms1 = MonthAndYearEndDate.split("\\,");
                        String t2= terms1[1];
                        String EndYear = t2.replaceAll(" ", "");
                        String t3= terms1[0];
                        String EndMonth = t3.replaceAll(" ", "");
                        Date date1 = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(EndMonth);
                        Calendar cal1 = Calendar.getInstance();
                        cal1.setTime(date1);
                        int month1 = cal1.get(Calendar.MONTH)+1;
                        System.out.println(month1);
                        String FinalEndDate = month1+"/24/"+EndYear;
                        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                        Date EndDate = sdf1.parse(FinalEndDate);
                        driver.findElement(SelectSecondEndDateElement).click();
                        System.out.println(EndDate);
                        ActualLable("Successfully selected End date from calendar i.e "+FinalEndDate, "Pass");
                        Thread.sleep(5000);
                        int NoOfOrders=driver.findElements(QuoteDateElement).size();
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+startDate+" and "+EndDate+" in first page of order list " );
                        for(int j=1;j<=NoOfOrders-1;j++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(j).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((EndDate.compareTo(date2) >= 0)&& (startDate.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by 'Custom Search' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        ExpectedLable("Now Click on last page of orders after sorting by 'Custom Search'" );
                        driver.findElement(LastPageElement).click();
                        ActualLable("Successfully verified filtering orders by 'Custom Search' on first page of result page", "Pass");
                        Thread.sleep(2000);
                        int NoOfOrders1=driver.findElements(QuoteDateElement).size();
                        ExpectedLable("Now Verify all 'Order dates' are there in b/w "+startDate+" and "+EndDate+" in Last page of order list " );
                        for(int k=1;k<=NoOfOrders1-1;k++) {
                            String DateOfRemainOrders=driver.findElements(QuoteDateElement).get(k).getText();
                            Date date2 = sdf.parse(DateOfRemainOrders);
                            if ((EndDate.compareTo(date2) >= 0)&& (startDate.compareTo(date2) <= 0)){
                            }
                            else{
                                Status=false;
                            }
                        }
                        ActualLable("Successfully verified filtering orders by 'Custom Search' on Last page of result page", "Pass");
                        ExpectedLable("Now Verify the result for filtering orders by 'Custom Search' " );
                        if(Status==true){
                            ActualLable("Successfully verified filtering orders by 'Custom Search' ", "Pass");
                        }else {  ActualLable("Verification Failed for filtering orders by 'Custom Search'", "Fail");}
                    }
                }
            }
            else{ ActualLable("Date Range Filter block is not available ", "Fail");     }
        }
        else{ ActualLable("Filter block is not available ", "Fail"); }
        return Status;
    }
    public static int TotalNoOfOrders() throws InterruptedException {
        Thread.sleep(1000);
        String text = driver.findElement(TotalNoOfOrdersElement).getText();
        //String text="Total Orders : 99 found";
        String[] terms = text.split("\\:");
        String s= terms[1];
        String t = s.replaceAll("[\\D]", "");
        String NoResults = t.replaceAll(" ", "");
        int InstallCost = Integer.parseInt(NoResults);
        //int noOfPages= InstallCost/10+1;
        return InstallCost;
    }
    public static void VerificationOfLinksInOrdersPage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        Thread.sleep(1000);
        ExpectedLable("Check that the first listed order is expanded or collapsed ?");
        if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
            ActualLable("Verified successfully, first listed order is expanded", "Pass");
            VerifyLinksInOrdersPage(driver);
        }
        else{
            ActualLable("Verified successfully, first listed order is not expanded", "Pass");
            ExpectedLable("Now try to Expand the first order details ");
            driver.findElement(FirstOrderElement).click();
            if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                ActualLable("Verified successfully, Expand functionality is working properly", "Pass");
                VerifyLinksInOrdersPage(driver);
            }
            else{ ActualLable("Verification failed , Expand functionality is not working", "Fail"); }
        }
    }
    public static void VerifyLinksInOrdersPage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Verify the link is available on 'Document Number' or not ..?");
        String ActualDocumentNumber = driver.findElements(DocumentLink).get(0).getText();
        if (driver.findElements(DocumentLink).get(0).isEnabled()) {
            ActualLable("Verified successfully, Link is available for document link ", "Pass");
            ExpectedLable("Now Click on 'Document Number link' and check that the user should navigate to order page");
            driver.findElements(DocumentLink).get(0).click();
            Thread.sleep(2000);
            ActualLable("Clicked on document link successfully", "Pass");
            ExpectedLable("Now check that the Document link is navigated to particular link details page or not");
            String ExpectedDocumentNumber = driver.findElement(DocumentBreadCrumb).getText();
            if (ActualDocumentNumber.contentEquals(ExpectedDocumentNumber)) {
                ActualLable("Verification is successful, Document link working Properly ", "Pass");
            } else { ActualLable("Verification failed for Document link ", "Fail"); }
        } else { ActualLable("Document number link is not working", "Fail"); }

        driver.navigate().to("https://directqa2.dimensiondata.com/Webshop/orders");

        ExpectedLable("Verify the link is available on 'Product Name' or not ..?");
        String ActualProductname = driver.findElements(NameElement).get(0).getText();
        if (driver.findElements(NameElement).get(0).isEnabled()) {
            ActualLable("Verified successfully, Link is available for Product Name ", "Pass");
            ExpectedLable("Now Click on 'Product Name link' and check that the user should navigate to Item Details page");
            driver.findElements(NameElement).get(0).click();
            Thread.sleep(2000);
            ActualLable("Clicked on Product Name link successfully", "Pass");
            ExpectedLable("Now check that the 'Product Name link' is navigated to particular Product details page or not");
            String ExpectedProductName = driver.findElement(By.xpath("//h2")).getText();
            if (ActualProductname.contentEquals(ExpectedProductName)) {
                ActualLable("Verification is successful, Product Name link working Properly ", "Pass");
            } else {ActualLable("Verification failed for Product name link ", "Fail");}
        } else {ActualLable("Product Name link is not working", "Fail");}

        driver.navigate().to("https://directqa2.dimensiondata.com/Webshop/orders");

        ExpectedLable("Verify the link is available on 'Product Name' or not ..?");
        String ActualStatusName =driver.findElements(StatusLink).get(0).getText();
        if(driver.findElements(StatusLink).get(0).isEnabled()) {
            ActualLable("Verified successfully, Link is available for 'Status Of order' ", "Pass");
            ExpectedLable("Now Click on 'Status link' and check that the user should navigate to Item Details page");
            driver.findElements(StatusLink).get(0).click();
            Thread.sleep(2000);
            ActualLable("Clicked on Status link successfully", "Pass");
            ExpectedLable("Now check that the 'Status link' is navigated to particular Product details page or not");
            String ExpectedStatusName=driver.findElement(By.xpath("//h2")).getText();
            if(ActualStatusName.contentEquals(ExpectedStatusName)){
                ActualLable("Verification is successful, Status link working Properly ", "Pass");
            }
            else{ ActualLable("Verification failed for Staus link ", "Fail"); }
        }
        else{   ActualLable("'Status link' is not working", "Fail");}

    }

}
