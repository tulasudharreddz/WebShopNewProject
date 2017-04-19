package pageObject;

import jxl.write.WriteException;
import localTestCases.DemoLocal;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    static private By FieldMfrElementValue = By.xpath("//span[@class='field-name'][contains(text(),'Mfr Part#')]/parent::div");
    static private By FieldManufacturerElement = By.xpath("//span[@class='field-name'][contains(text(),'Manufacturer:')]");
    static private By FieldQuantityElement = By.xpath("//div[contains(text(),'Quantity')]");
    static private By UnitPriceElement = By.xpath("//div[contains(text(),'Unit Price')]");
    static private By UnitPrice = By.xpath("//div[@class='col-md-1 col-md-push-0 col-sm-push-3 col-sm-9 col-xs-7 small vertical-margin-auto']/p[@class='product-price']");
    static private By QuantityValue = By.xpath("//div[@class='col-md-1 col-md-push-0 col-sm-push-3 col-sm-9 col-xs-push-0 col-xs-5 small vertical-margin-auto text-center product-row-qty']/div");
    static private By ExtendedPriceElement = By.xpath("//div[contains(text(),'Extended Price')]");
    static private By SerialElement = By.xpath("//div[contains(text(),'Serial #')]");
    static private By FielldStatusElement = By.xpath("//div[contains(text(),'Status')]");
    static private By RequestReturnElement = By.xpath("//a[contains(text(),'Request a Return')]");
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
    static private By  InstallationChargesElement= By.xpath("//label[contains(text(),'Installation Services')]/parent::div/following-sibling::div");
    static private By  RequestReturnPageTitleElement= By.xpath("(//div[@class='is-alert-header'][contains(text(),'Request a Product Return')])[1]");
    static private By  NonEditableFields= By.xpath("//div[@class='row']/div/div/label");
    static private By  EditableFields= By.xpath("//div[@class='row']/is-input/div/label");
    static private By  NoOfQuantity= By.xpath("(//div[@class='product-row']/div[3]/div)[1]");
    static private By  ReturnQuantityElement= By.id("returnQty");
    static private By  ErrorMessageQuantity= By.xpath("//input[@id='returnQty']/following-sibling::div[@class='text-danger']");
    static private By  CloseRequestReturnButtonElement= By.xpath("(//button[@class='is-alert-close'])[1]");
    static private By  ReasonRequestReturnButtonDropDownElement= By.xpath("//span[@class='select2-selection__rendered']");
    static private By  ReasonForReturnButtonElement= By.xpath("//li[contains(text(),'Item arrived too late')]");
    static private By  CommentForReturnButtonElement= By.id("comments");
    static private By  SubmitOnReturnButtonElement= By.xpath("(//button[contains(text(),'Submit')])[1]");
    static private By  PoNumberXpath= By.xpath("//label[contains(text(),'PO Number')]/parent::div");
    //Changing for shipping charges
    static private By ActualShippingChargesXpath = By.xpath("//label[contains(text(),'Logistikgebühr')]/parent::div/following-sibling::div");
    static private By ActualSalesVatXpath = By.xpath("//label[contains(text(),'Mehrwertsteuer')]/parent::div/following-sibling::div");
    static private By ActualCartGrandTotalXpath = By.xpath("//label[contains(text(),'Cart Grand Total')]/parent::div/following-sibling::div");
    static private By ActualInstallationChargesXpath = By.xpath("//label[contains(text(),'Installation Services')]/parent::div/following-sibling::div");
    static private By ActualCartSubtotalXpath = By.xpath("//label[contains(text(),'Cart Subtotal')]/parent::div/following-sibling::div");
    static private By NoOfCartProducts = By.xpath("//div[@class='product-row']");
    static private By ProductDetails = By.xpath("//div[@class='form-group col-md-6']/div");
    static private By RequestReturnValues = By.xpath("//div[@class='form-group col-md-6']/div[@class='text-value-label']");




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
            } else {     ActualLable("Verification failed for 'Orders page' Title", "Fail");      }
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
        //AssertXpath.add(SerialElement);
        AssertXpath.add(FielldStatusElement);
        AssertXpath.add(RequestReturnElement);
        ArrayList<String> Assert1Xpath=new ArrayList<String>();
        Assert1Xpath.add("Image");
        Assert1Xpath.add("Name");
        Assert1Xpath.add("Mfr");
        Assert1Xpath.add("Quantity");
        Assert1Xpath.add("UnitPrice");
        Assert1Xpath.add("Extended Price");
        //Assert1Xpath.add("Serial");
        Assert1Xpath.add("Fielld Status");
        Assert1Xpath.add("Request Return button");

        for(int i=0;i<=7;i++) {
            ExpectedLable("Check that '" +Assert1Xpath.get(i)+ "' is displaying on orders or not ?");
            Thread.sleep(1000);
            if (driver.findElements(AssertXpath.get(i)).size() > 0) {
                ActualLable("Verification Successful, '" +Assert1Xpath.get(i)+ "' is displaying on orders", "Pass");
            }
            else{    ActualLable("Verification Failed, '" +Assert1Xpath.get(i)+ "' is not displaying", "Fail");      }
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
    public static void VerificationOfOrderExpandForFirstProduct(WebDriver driver) throws IOException, WriteException, InterruptedException {
        Thread.sleep(1000);
        ExpectedLable("Check that the first listed order is expanded or collapsed ?");
        if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
            ActualLable("Verified successfully, first listed order is expanded", "Pass");
            QuantityOfProduct=driver.findElement(NoOfQuantity).getText();
            //VerifyLinksInOrdersPage(driver);
        }
        else{
            ActualLable("Verified successfully, first listed order is not expanded", "Pass");
            ExpectedLable("Now try to Expand the first order details ");
            driver.findElement(FirstOrderElement).click();
            if(driver.findElements(ExpandedFirstOrderElement).size()>0) {
                ActualLable("Verified successfully, Expand functionality is working properly", "Pass");
                //VerifyLinksInOrdersPage(driver);
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
    public static Double SearchCreatedOrderInOrdersPage(WebDriver driver) throws InterruptedException, IOException, WriteException {
        Double ActualInstallationCharges = null;
        //String ReferenceNumber =CheckOutPage.ClickonProceedtoCheckout(driver);
        String ReferenceNumber =CheckOutPage.CompleteCheckOut(driver);
        ReviewOrderPage.COnfirmAndPlaceOrder(driver);
        OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
        HomePage.ClickonMyAccount(driver);
        HomePage.SelectSubMenuOptUnderMyAccount(driver,2);
        Thread.sleep(3000);
        ExpectedLable("Check that Created 'Order Reference number' is displaying in Orders page or not  ..?");
        if(driver.findElements(By.xpath("//span[contains(text(),'Document #')]/following-sibling::span/a[contains(text(),'"+ReferenceNumber+"')]")).size()>0){
            ActualLable("Verification is successful, Created 'Order Reference number' is displaying in Orders page ", "Pass");
            driver.findElement(By.xpath("//span[contains(text(),'Document #')]/following-sibling::span/a[contains(text(),'"+ReferenceNumber+"')]")).click();
            ExpectedLable("Check that 'Installation Charges' is displaying in Orders summery page or not  ..?");
            if(driver.findElements(InstallationChargesElement).size()>0){
                ActualLable("Verification is successful, 'Installation Charges' is displaying in Orders summery page ", "Pass");
                ExpectedLable("Get the 'Installation Charges' frfom page");
                String InstallationChargesString =driver.findElement(InstallationChargesElement).getText();
                String s1 = InstallationChargesString.replaceAll("[€$£₹,]","");
                ActualInstallationCharges = Double.parseDouble(s1);
                ActualLable("Verification is successful, 'Installation Charges' are : "+ActualInstallationCharges, "Pass");
            }
            else{ ActualLable("Verification Failed, 'Installation Charges' is not displaying  in Orders summery page ", "Fail"); }
        }
        else{ ActualLable("Verification Failed, Created 'Order Reference number' is not displaying in Orders page ", "Fail");}
        return ActualInstallationCharges;
    }
    static String QuantityOfProduct;
    public static void VerifyRequestReturnPage(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Verify Request&Return Form");
        ExpectedLable("Check that the 'Request Return' button is available in Order or not ?");
        if(driver.findElements(RequestReturnElement).size()>0){
            ActualLable("Verification is successful , 'Request Return' button is available in Order", "Pass");
            ExpectedLable("Click on 'Request Return' button ?");
            driver.findElements(RequestReturnElement).get(0).click();
            ActualLable("Successfully Clicked on 'Request Return' button ", "Pass");
            Thread.sleep(2000);
            ExpectedLable("Verify'Request Return Form' is opened or not ?");
            if(driver.findElements(RequestReturnPageTitleElement).size()>0) {
                ActualLable("Verified successfully, 'Request Return Form' is opened ", "Pass");
                String RequestReturnTitleText=driver.findElement(RequestReturnPageTitleElement).getText();
                ExpectedLable("Verify page title for Request Return Form");
                if(RequestReturnTitleText.contentEquals("Request a Product Return")){
                    ActualLable("Verified successfully, Title of 'Request Return' Form ", "Pass");
                }else{ActualLable("Verification failed, Title is not available of 'Request Return' Form ", "Fail");  }
            }
            else{ActualLable("Verification failed , 'Request Return' form is not Opened", "Fail");}
        }
        else{ ActualLable("Verification failed , 'Request Return' button is not available in Order", "Fail"); }
    }
    public static void VerifyRequestReturnPageContent(WebDriver driver) throws InterruptedException, IOException, WriteException {
        StepLable("Verify content on Request Return page ");
        ArrayList<String> AssertName=new ArrayList<String>();//creating arraylist
        AssertName.add("Reason for Return *");
        AssertName.add("Quantity to be returned *");
        AssertName.add("Please provide details about the reason for return *");
        AssertName.add("Order Number");//adding object in arraylist
        AssertName.add("Product Name");
        AssertName.add("Manufacturer Part #");
        AssertName.add("Client-Company Name");
        AssertName.add("Client-User Name");
        AssertName.add("Installation Address");

        for(int j=0;j<=2;j++){
            ExpectedLable("Verify '"+AssertName.get(j)+"' is available on Request return page or not ?");
            String FieldsTitle=driver.findElements(EditableFields).get(j).getText();
            if(FieldsTitle.contentEquals(AssertName.get(j))) {
                ActualLable("Verification is successful , '" + AssertName.get(j) + "' is available on Request return page", "Pass");
            }
            else{     ActualLable("Verification failed , '" + AssertName.get(j) + "' is not available on Request return page", "Fail");     }
        }
        for(int i=3;i<=8;i++){
            ExpectedLable("Verify '"+AssertName.get(i)+"' is available on Request return page or not ?");
            String FieldsTitle=driver.findElements(NonEditableFields).get(i).getText();
            if(FieldsTitle.contentEquals(AssertName.get(i))) {
                ActualLable("Verification is successful , '" + AssertName.get(i) + "' is available on Request return page", "Pass");
            }
            else{     ActualLable("Verification failed , '" + AssertName.get(i) + "' is not available on Request return page", "Fail");     }
        }
    }
    public static void VerifyQuantityFunctionalityinRequestReturn(WebDriver driver) throws InterruptedException, IOException, WriteException {
        ExpectedLable("Get the Exist Quantity value for the product ");
        int QuantityOfProductint= Integer.parseInt(QuantityOfProduct);
        ActualLable("Verification is successful , Exist Quantity value for the product is :  ' "+QuantityOfProductint+" '", "Pass");
        ArrayList<Integer> AssertName=new ArrayList<Integer>();//creating arraylist
        AssertName.add(QuantityOfProductint-1);
        AssertName.add(QuantityOfProductint);
        AssertName.add(QuantityOfProductint+1);
        for(int i=0;i<=2;i++) {
            String QuantityOfProductStr = Integer.toString(AssertName.get(i));
            driver.findElement(ReturnQuantityElement).clear();
            driver.findElement(ReturnQuantityElement).sendKeys(QuantityOfProductStr);
            driver.findElement(ReturnQuantityElement).sendKeys(Keys.TAB);
            Thread.sleep(1000);
            ExpectedLable("Check for error message for Quantity field with the quantity Value :"+QuantityOfProductStr);
            if(driver.findElements(ErrorMessageQuantity).size()>0){
                if(i==2||AssertName.get(i)==0){  ActualLable("Verification is successful , Error message is showing for invalid input", "Pass");  }
                else{ ActualLable("Verification Failed , Error message is showing for valid input", "Fail"); }
            }
            else{
                if(i==2||AssertName.get(i)==0){ActualLable("Verification Failed , Error message is not showing for invalid input", "Fail"); }
                else{ActualLable("Verification is successful , Error message is not showing for Valid input", "Pass");}
            }
        }
    }
    public static void VerifyCloseButtonFunctionalityinRequestReturn(WebDriver driver) throws InterruptedException, IOException, WriteException{
        ExpectedLable("Verify that 'Close button' is Available on 'Request return' Page or not ?");
        if(driver.findElements(CloseRequestReturnButtonElement).size()>0){
            ActualLable("Verification is successful , 'Close button' is available on 'Request Return' page ", "Pass");
            ExpectedLable("Click on 'Close button' on 'Request return' Page, and check that 'Close button' is working or not ?");
            driver.findElement(CloseRequestReturnButtonElement).click();
            if(driver.findElements(ProductDetails).size()>0){
                ActualLable("Verification Failed , 'Close button' is not Working properly on 'Request Return' page ", "Fail");}
            else{ ActualLable("Verification is successful , 'Close button' is Working Properly on 'Request Return' page ", "Pass");}
        }else{  ActualLable("Verification Failed , 'Close button' is not available on 'Request Return' page ", "Fail");}
    }
    public static void VerifySubmitButtonFunctionalityinRequestReturnPage(WebDriver driver) throws InterruptedException, IOException, WriteException{
        ExpectedLable("Verify that 'Reason for Return' dropdown displaying or not ?, if yes select option from drop down.");
        if(driver.findElements(ReasonRequestReturnButtonDropDownElement).size()>0){
            driver.findElement(ReasonRequestReturnButtonDropDownElement).click();
            Thread.sleep(1000);
            driver.findElement(ReasonForReturnButtonElement).click();
            ActualLable("Verification is successful , Reason selected from drop down ", "Pass");
        }
        else{ ActualLable("Verification Failed , Requesr Reason drop down not found", "Fail"); }
        ExpectedLable("Verify that 'Return Quantity' blank is displaying or not ?, if yes Enter valid data.");
        if(driver.findElements(ReturnQuantityElement).size()>0){
            driver.findElement(ReturnQuantityElement).clear();
            //driver.findElement(ReturnQuantityElement).sendKeys(QuantityOfProduct);
            driver.findElement(ReturnQuantityElement).sendKeys("1");
            ActualLable("Verification is successful , entered valid data ", "Pass");
        }
        else{ ActualLable("Verification Failed , 'Return Quantity' blank is not available on 'Request Return' page ", "Fail"); }
        ExpectedLable("Verify that 'Reason Comment' dropdown displaying or not ?, if yes enter valid reason in it");
        if(driver.findElements(CommentForReturnButtonElement).size()>0){
            driver.findElement(CommentForReturnButtonElement).sendKeys("Test");
            ActualLable("Verification is successful , 'Reason Comment' entered successfully ", "Pass");
        }
        else{ ActualLable("Verification Failed , 'Reason Comment' is not available on 'Request Return' page ", "Fail"); }
        ExpectedLable("Verify that 'Submit' button on 'Reason for Return' dropdown displaying or not ?, if yes click on Submit");
        if(driver.findElement(SubmitOnReturnButtonElement).isEnabled()){
            driver.findElement(SubmitOnReturnButtonElement).click();
            ActualLable("Verification is successful , clicked on Submit button ", "Pass");
        }
        else{ ActualLable("Verification Failed , 'Submit' button is not Enabled on 'Request Return' page ", "Fail"); }

    }
    public static String VerifyOrderDetailsFromEmail(WebDriver driver) throws InterruptedException, IOException, WriteException{
        Thread.sleep(2000);
        VerifyOrderPageTitle(driver);
        ExpectedLable("Get Reference Number from Orders Page");
        String PoNumberTotal = driver.findElement(PoNumberXpath).getText();
        String[] terms = PoNumberTotal.split("Number");
        String s = terms[1];
        String t1 = s.replaceAll(" ","");
        String PoNumber = t1.replaceAll("\n","");
        ActualLable("Reference Number on Orders Page is: "+ PoNumber, "Pass");
        return PoNumber;
    }
    public static ArrayList<String> GetProductDetailsFromOrderPage(WebDriver driver, int ProductNumber) throws IOException, WriteException, InterruptedException{
        String ProductNameSt = driver.findElements(NameElement).get(ProductNumber).getText();
        String PartNumber1 = driver.findElements(FieldMfrElementValue).get(ProductNumber).getText();
        String MfrPartNumberSt= FavoriesPage.TrimMfrNumber(driver,PartNumber1);
        String AvailabilityStatus = "Not Available";
        String UnitPriceSt = driver.findElements(UnitPrice).get(ProductNumber).getText();
        String QuantityST = driver.findElements(QuantityValue).get(ProductNumber).getText();
        /*if(ProductNumber == 1){   QuantityST="5";  }
        else{QuantityST="1";}*/
        ArrayList<String> AssertNamesSC=new ArrayList<String>();
        AssertNamesSC.add(ProductNameSt);
        AssertNamesSC.add(MfrPartNumberSt);
        AssertNamesSC.add(UnitPriceSt);
        AssertNamesSC.add(AvailabilityStatus);
        AssertNamesSC.add(QuantityST);
        return AssertNamesSC;
    }
    public static boolean VerifyProductDetailsOnOrderPageFromMailReturn(WebDriver driver) throws IOException, WriteException, InterruptedException {
        boolean Status = true;
        ArrayList<String> AssertNamesText = new ArrayList<String>();
        AssertNamesText.add("Name Of Item");
        AssertNamesText.add("MFR Part Number");
        AssertNamesText.add("Product Price");
        AssertNamesText.add("Availability Status");
        AssertNamesText.add("Quantity Of the Product");
        long NoOfProductAddedToCart = driver.findElements(NoOfCartProducts).size();
        for (int i = 0; i <= NoOfProductAddedToCart-1; i++) {
            ArrayList<String> AssertNameFromPSearchPage1 = DemoLocal.ProductDetailsArrayList.get(i);
            ArrayList<String> ActualValue = GetProductDetailsFromOrderPage(driver, i);
            for (int j = 0; j <= 4; j++) {
                if(j==3){ActualLable("Availability status will not available on Orders page","Pass");}
                else {
                    String ExpectedText = AssertNameFromPSearchPage1.get(j);
                    String ActualText = ActualValue.get(j);
                    ExpectedLable("Verify ' " + AssertNamesText.get(j) + " On Review order page ");
                    if (ExpectedText.contentEquals(ActualText)) {
                        ActualLable("Expected and Actual values are same, Expected Value : " + ExpectedText + " Actual Value :" + ActualText, "Pass");
                    } else {
                        ActualLable("Failed to verify ' " + AssertNamesText.get(j) + " ' On Review order page, Expected Value : " + ExpectedText + " Actual Value :" + ActualText, "Fail");
                        Status = false;
                    }
                }
            }
        }
        return Status;
    }
    public static boolean VerifyCartSummeryDetailsOnOrderPageFromMailReturn(WebDriver driver) throws IOException, WriteException, InterruptedException{
        boolean Status = true;
        ArrayList<String> AssertNamesText = new ArrayList<String>();
        AssertNamesText.add("Cart Subtotal");
        AssertNamesText.add("Shipping Charges");
        AssertNamesText.add("Sales VAT");
        AssertNamesText.add("Cart Grand Total");
        AssertNamesText.add("Installation Services");
        for (int j = 0; j <= 4; j++) {
            String ExpectedText = ReviewOrderPage.ExpectedCartSummeryArray.get(j);
            String ActualText = GetCartSummeryDetailsOnReviewOrderPage(driver).get(j);
            ExpectedLable("Verify ' " + AssertNamesText.get(j) + " ' on Review order Page ");
            if (ExpectedText.contentEquals(ActualText)) {
                ActualLable(" Expected and Actual values of ' "+AssertNamesText.get(j)+" ' are same, Expected Value : "+ExpectedText+" Actual Value :"+ActualText, "Pass");
            } else {
                ActualLable("Failed to verify "+AssertNamesText.get(j)+", Expected Value : "+ExpectedText+" Actual Value :"+ActualText, "Fail");
                Status = false;
            }
        }
        return Status;
    }
    public static ArrayList<String> GetCartSummeryDetailsOnReviewOrderPage(WebDriver driver) throws IOException, WriteException, InterruptedException{
        String ActualCartSubtotalString = driver.findElement(ActualCartSubtotalXpath).getText();
        String ActualShippingChargesString =  driver.findElement(ActualShippingChargesXpath).getText();
        String ActualSalesVatChargesString =  driver.findElement(ActualSalesVatXpath).getText();
        String ActualCartGrandTotalString =  driver.findElement(ActualCartGrandTotalXpath).getText();
        String ActualInstallationChargesString =  driver.findElement(ActualInstallationChargesXpath).getText();

        ArrayList<String> AssertName1=new ArrayList<String>();
        AssertName1.add(ActualCartSubtotalString);
        AssertName1.add(ActualShippingChargesString);
        AssertName1.add(ActualSalesVatChargesString);
        AssertName1.add(ActualCartGrandTotalString);
        AssertName1.add(ActualInstallationChargesString);

        return AssertName1;
    }
    public static boolean VerifyProductDetailsOnRequestReturnPage(WebDriver driver, String OrderNo) throws IOException, WriteException, InterruptedException{
        boolean Status = true;
        ArrayList<String> AssertNamesText = new ArrayList<String>();
        AssertNamesText.add("Order No");
        AssertNamesText.add("Product Name");
        AssertNamesText.add("Manufacturer Part #");
        ArrayList<String> AssertNameFromPSearchPage1 = DemoLocal.ProductDetailsArrayList.get(0);
        ExpectedLable("Verify ' Order No' On Review order page ");
        String ActualOrderNum = driver.findElements(ProductDetails).get(0).getText();
        if(ActualOrderNum.contentEquals(OrderNo)){
            ActualLable("Order number verified successfully, Expected OrderNum : '"+OrderNo+"' Actual Order No : "+ActualOrderNum,"Pass");
        }else{ Status = false; ActualLable("Order number Not Matched, Expected OrderNum : '"+OrderNo+"' Actual Order No : "+ActualOrderNum,"Fail"); }
        ExpectedLable("Verify ' Product Name' On Review order page ");
        String ActualProductName = driver.findElements(ProductDetails).get(1).getText();
        if(ActualProductName.contentEquals(AssertNameFromPSearchPage1.get(0))){
            ActualLable("Product Name verified successfully, Expected OrderNum : '"+ActualProductName+"' Actual Order No : "+AssertNameFromPSearchPage1.get(0),"Pass");
        }else{ Status = false; ActualLable("Product Name Not Matched, Expected OrderNum : '"+ActualProductName+"' Actual Order No : "+AssertNameFromPSearchPage1.get(0),"Fail"); }
        ExpectedLable("Verify ' Manufacturer Part #' On Review order page ");
        String ActualMFR = driver.findElements(ProductDetails).get(2).getText();
        if(ActualMFR.contentEquals(AssertNameFromPSearchPage1.get(1))){
            ActualLable("Manufacturer Part # verified successfully, Expected OrderNum : '"+ActualMFR+"' Actual Order No : "+AssertNameFromPSearchPage1.get(1),"Pass");
        }else{ Status = false; ActualLable("Manufacturer Part # Not Matched, Expected OrderNum : '"+ActualMFR+"' Actual Order No : "+AssertNameFromPSearchPage1.get(1),"Fail"); }
        return Status;
    }
    public static ArrayList<String> GetProductDetailsFromRReturnPage(WebDriver driver) throws IOException, WriteException, InterruptedException {
        ExpectedLable("Get All the values and Store");
        ArrayList<String> ValueOfRReturn=new ArrayList<String>();
        for(int i=0;i<=driver.findElements(RequestReturnValues).size()-1;i++) {
            String ValueOfDetails = driver.findElements(RequestReturnValues).get(i).getText();
            ValueOfRReturn.add(ValueOfDetails);
        }
        ActualLable("Successfully Stored all the details in Product Request Return Page", "Pass");
        return ValueOfRReturn;
    }
}
