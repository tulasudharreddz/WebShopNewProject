package localTestCases;

import GenericLib.Browser;
import GenericLib.DataDriven;
import GenericLib.MobileBrowserStack;
import GenericLib.ObjectRepository;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObject.*;

import static GenericLib.DataDriven.ActualLable;
import static GenericLib.DataDriven.ExpectedLable;
import static GenericLib.DataDriven.StepLable;

/**
 * Created by t.mirasipally on 10/28/2016.
 */
public class BrowserStackTC  extends MobileBrowserStack {

    ObjectRepository ob = new ObjectRepository();
    //Logger log=Logger.getLogger("Testing Cases");
    static Logger log = Logger.getLogger("Testing Cases");
    static protected DataDriven excel = new DataDriven();
    static protected HomePageTC tc = new HomePageTC();
    private static Workbook book1;
    private static Sheet sheet1;

    private WebDriver driver;
    private Sheet sheet;
    private WritableSheet wsheet;
    /*private WritableWorkbook wbook;*/


    @BeforeClass
    public void setUp() {
        driver=getDriver();
    }
    //public static ArrayList<ArrayList> ProductDetailsArrayList;
    @Test
    public void DemoMobile_TC_01() throws IOException, WriteException {
        try {
            DataDriven.ReportStartup(136);
            Thread.sleep(2000);
            LoginPage.Loginfunctionality(driver);
            double noOfItems = ShoppingCart.DeleteExistItem(driver);
            StepLable("Add product to Shopping Cart from Product search page");
            ArrayList<String> AssertNameFromPSearchPage = ProductSearchPage.SelectProductFromSearchResultPage(driver);
            ArrayList<String> AssertNameFromPCartPage = ProductCartPage.SelectProductFromPCartPage(driver);
            //ArrayList<String> AssertNameFromFavoritesPage= FavoriesPage.SelectProductFromFavoritesPage(driver);
            DemoLocal.ProductDetailsArrayList= new ArrayList<ArrayList>();
            DemoLocal.ProductDetailsArrayList.add(AssertNameFromPSearchPage);
            DemoLocal.ProductDetailsArrayList.add(AssertNameFromPCartPage);
            //ProductDetailsArrayList.add(AssertNameFromFavoritesPage);
            double noOfItemsAfterAddToCart = HomePage.VerifyCart(driver);
            ExpectedLable("Verify cart count functionality by adding product to cart");
            if(noOfItemsAfterAddToCart>noOfItems){
                ActualLable("successfully verified cart Number functionality and items in cart is increased","Pass");
                ShoppingCart.VerifyItemCount(driver);
                StepLable("Verify Product Details on Shopping cart with Details on Product Cart page details");
                boolean ResultStatus = ShoppingCart.VerifyProductDetailsOnShoppingCart(driver);
                String ReferenceNumber = ReviewOrderPage.ConfirmAndPlaceOrderDemo(driver);
                OrderAcknowledgementPage.GetOrderAcknowledgement(driver);
                boolean Stuas =EmailVerificationDetails.VerifyOrderEmailInOutLook(driver);
                if(Stuas==true) {
                    EmailVerificationDetails.VerifyReferenceLink(driver, ReferenceNumber);
                    OrdersPage.VerifyRequestReturnPage(driver);
                    OrdersPage.VerifySubmitButtonFunctionalityinRequestReturnPage(driver);
                    EmailVerificationDetails.VerifyRequestReturnEmailInOutLookDemo(driver);
                }
            }else{ActualLable("verification failed for cart Number functionality","Fail");}
        }
        catch (AssertionError e){ String error ="Exception : " +  e.getClass().getSimpleName();	ActualLable(error,"Fail");}
        catch (Exception e){ String error ="Exception : " +  e.getClass().getSimpleName();ActualLable(error,"Fail"); }
    }


    @Test
    public void WS_TC_30() throws IOException, WriteException, InterruptedException, BiffException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        ob.repository(driver);
        book1 = Workbook.getWorkbook(new File("D:\\TC.xls"));
        sheet1 = book1.getSheet("Sheet2");
        for (int r = 1; r <= 6; r++) {
            String Flag = sheet1.getCell(3, r).getContents();
            log.info(Flag);
            if (Flag.equals("Yes")) {
                log.info("" + sheet1.getCell(0, r).getContents() + ": " + sheet1.getCell(1, r).getContents() + "");
                Thread.sleep(2000);
                for (int t = 4; t <= 4; t++) {
                    log.info("" + sheet1.getCell(t, r).getContents() + ";");
                    Class[] paramTypes = new Class[1];
                    paramTypes[0] = WebDriver.class;
                    Class<?> c = Class.forName(sheet1.getCell(t+1, r).getContents());
                    //java.lang.reflect.Method method = c.getMethod(sheet1.getCell(t,1).getContents());
                    java.lang.reflect.Method method = c.getDeclaredMethod(sheet1.getCell(t, r).getContents(),paramTypes);
                    log.info(method);
                    method.invoke(c.newInstance(),driver);
                    Thread.sleep(1000);
                }
            } else {
                log.info("Fail to execute");
            }

        }
    }

    @Test
    public void ExecutionWithExcel() throws IOException, BiffException, WriteException, InterruptedException {
        ob.repository(driver);
        book1 = Workbook.getWorkbook(new File("D:\\TC.xls"));
        sheet1 = book1.getSheet("Sheet2");
        String mee= sheet1.getCell(1,4).getContents();
        String cll = sheet1.getCell(1,5).getContents();


        Class cls = tc.getClass();
        Method[] me = cls.getDeclaredMethods();
        if (me.equals(mee)){

        }

    }
}
