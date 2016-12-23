package GenericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import jxl.Cell;
import jxl.CellReferenceHelper;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.w3c.dom.css.RGBColor;
import pageObject.ProductSearchPage;

import javax.swing.tree.FixedHeightLayoutCache;


public class DataDriven {

	private static Workbook book;
	private static Workbook TestCaseBook;
	private static Workbook book1;
	private static WritableWorkbook wbook;
	private static Sheet sheet;
	private static Sheet sheet1;
	private static Sheet TestCasesheet;
	private static WritableSheet wsheet;
	static int SCcount=0;

	static ObjectRepository obr = new ObjectRepository();
	static ScreenShots Scr = new ScreenShots();
	//Properties obj;
	FileInputStream objFile;
	static private WebDriver driver;
	public static WritableCellFormat CellFormat2() throws WriteException {
		//int counted = 14;
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.ARIAL, 9);
		cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setWrap(true);
		//cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		cellFormat.setAlignment(Alignment.CENTRE);
		return cellFormat;
	}
	public static WritableCellFormat CellFormat3() throws WriteException {
		//int counted = 14;
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.ARIAL,22,WritableFont.BOLD);
		cellFont.setColour(Colour.WHITE);
		cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setWrap(true);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THICK);
		cellFormat.setAlignment(Alignment.CENTRE);
		//Colour colour = Colour.getInternalColour(RGBColor(255,0,0));
		cellFormat.setBackground(Colour.BRIGHT_GREEN);//BRIGHT_GREEN

		return cellFormat;
	}

	public Sheet ReadSheet(Sheet sheet) throws BiffException, IOException, WriteException {
		obr.repository(driver);
		book = Workbook.getWorkbook(new File(obr.obj.getProperty("testData")));
		sheet = book.getSheet("ResultSheet");
		wbook = Workbook.createWorkbook(new File("./TestData/testCases/"+"Detailed Test Report_" +ObjectRepository.dateString()+".xls"), book);
		wsheet = wbook.getSheet("TestCaseDiscription");
		wsheet.addCell(new Label(0 , 0 ,"DD WebShop Test Report",CellFormat3()));
		wsheet.addCell(new Label(1 , 4 ,ObjectRepository.DateSt(),CellFormat2()));
		return sheet;
	}
	public static Sheet ReadTestCases(Sheet TestCasesheet) throws BiffException, IOException, WriteException {
		obr.repository(driver);
		TestCaseBook = Workbook.getWorkbook(new File(obr.obj.getProperty("testData2")));
		TestCasesheet = TestCaseBook.getSheet("TestCases");
		return TestCasesheet;
	}

	public static WritableSheet writeSheet(WebDriver driver) throws IOException{

		wsheet = wbook.getSheet("ResultSheet");

		return wsheet;
	}
	private static final AtomicInteger count = new AtomicInteger(3);
	private static final AtomicInteger count1 = new AtomicInteger(3);
	private static final AtomicInteger count2 = new AtomicInteger(3);
	private static final AtomicInteger count3 = new AtomicInteger(3);
	private static final AtomicInteger count4 = new AtomicInteger(3);
	private static final AtomicInteger count5 = new AtomicInteger(6);
	private static final AtomicInteger count6 = new AtomicInteger(6);

	public static int DataDriven(){
		//int counted = 14;
		int expcounted = count.incrementAndGet();
		return expcounted;
	}
	public static int DataDriven1(){
		//int counted = 14;
		int expcounted = count1.incrementAndGet();
		return expcounted;
	}
	public static int DataDriven2(){
		//int counted = 14;
		int Actucounted = count2.incrementAndGet();
		return Actucounted;
	}
	public static int DataDriven3(){
		//int counted = 14;
		int Actucounted = count3.incrementAndGet();
		return Actucounted;
	}
	public static int DataDriven4(){
		//int counted = 14;
		int Actucounted = count4.incrementAndGet();
		return Actucounted;
	}
	public static int DataDriven5(){
		//int counted = 14;
		int Actucounted = count5.incrementAndGet();
		return Actucounted;
	}
	public static int DataDriven6(){
		//int counted = 14;
		int Actucounted = count6.incrementAndGet();
		return Actucounted;
	}

	public static WritableCellFormat CellFormat() throws WriteException {
		//int counted = 14;
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.ARIAL, 9);
		cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setWrap(true);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		return cellFormat;
	}
	public static WritableCellFormat CellFormat1() throws WriteException {
		//int counted = 14;
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.ARIAL, 9);
		cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setWrap(true);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		cellFormat.setAlignment(Alignment.CENTRE);
		return cellFormat;
	}
	static int counting=1;
	public static void StepLable(String resu) throws IOException, WriteException {
		int i = DataDriven();DataDriven1();DataDriven2();DataDriven3();DataDriven4();
		wsheet = wbook.getSheet("ResultSheet");
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.ARIAL, 9);
		cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setWrap(true);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
		cellFormat.setBackground(Colour.PERIWINKLE);
		wsheet.mergeCells(0, i, 4, i);
		wsheet.addCell(new Label(0 , i , resu, cellFormat));
		counting=1;
	}

	public static void ExpectedLable(String resu) throws IOException, WriteException {

		wsheet = wbook.getSheet("ResultSheet");
		wsheet.addCell(new Label(1 , DataDriven() , resu, CellFormat()));
		String numberAsString = Integer.toString(counting);
		wsheet.addCell(new Label(0 , DataDriven4() , numberAsString,CellFormat1()));
		counting++;
	}


	public static void ActualLable(String ACText,String result) throws IOException, WriteException {

		wsheet = wbook.getSheet("ResultSheet");
		wsheet.addCell(new Label(2 , DataDriven1() , ACText,CellFormat()));
		if(result=="Fail"){
			WritableCellFormat cellFormat = null;
			WritableFont cellFont = null;
			cellFont = new WritableFont(WritableFont.ARIAL, 9);
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setWrap(true);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setBackground(Colour.RED);
			int num = DataDriven2();

			Browser.screenshots();
			WritableHyperlink hlk =new WritableHyperlink(3 , num ,new File("D:\\Projects_Idea\\WebShopNewProject\\screenshots\\screen-"+SCcount+".jpeg"));
			wsheet.addHyperlink(hlk);
			//wsheet.addCell(new Label(3 , DataDriven2() , result,cellFormat));
			wsheet.addCell(new Label(3 , num , result,cellFormat));
			SCcount++;
		}
		else if(result=="Pass"){
			WritableCellFormat cellFormat = null;
			WritableFont cellFont = null;
			cellFont = new WritableFont(WritableFont.ARIAL, 9);
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setWrap(true);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setBackground(Colour.GREEN);
			wsheet.addCell(new Label(3 , DataDriven2() , result,cellFormat));
		}
		else{
			wsheet.addCell(new Label(3 , DataDriven2() , result,CellFormat1()));
		}
		wsheet.addCell(new Label(4 , DataDriven3() , ObjectRepository.TimeSt(),CellFormat1()));
		/*String numberAsString = Integer.toString(counting);
		wsheet.addCell(new Label(0 , DataDriven4() , numberAsString,CellFormat1()));
		counting++;*/
	}

	public static void ReportStartup(int j) throws IOException, WriteException, BiffException {
		int k = DataDriven();DataDriven1();DataDriven2();DataDriven3();DataDriven4();
		String ScID= ReadTestCases(TestCasesheet).getCell(0,j).getContents();
		String ScName= ReadTestCases(TestCasesheet).getCell(1,j).getContents();
		String ScDis= ReadTestCases(TestCasesheet).getCell(2,j).getContents();
		String text = ScID+";"+ScName;
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.ARIAL, 9);
		cellFormat = new WritableCellFormat(cellFont);
		cellFormat.setWrap(true);
		cellFormat.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
		cellFormat.setBackground(Colour.PINK);
		wsheet = wbook.getSheet("ResultSheet");
		wsheet.mergeCells(0, k, 4, k);
		wsheet.addCell(new Label(0 , k , text, cellFormat));
		//wsheet = wbook.getSheet("TestCaseDiscription");
		int i = DataDriven5();

		WritableHyperlink hlk =new WritableHyperlink(0,i,ScID,wsheet = wbook.getSheet("ResultSheet"),0,k);

		wsheet = wbook.getSheet("TestCaseDiscription");
		wsheet.addHyperlink(hlk);
		//wsheet.addCell(new Label(0 , i, ScID,CellFormat1()));
		wsheet.addCell(new Label(1 , i, ScName, CellFormat()));
		wsheet.addCell(new Label(2 , i, ScDis, CellFormat()));
		counting=1;
	}

	public static void ReportResult(String Scresult) throws WriteException {
		int ResultColumn= DataDriven6();
		wsheet = wbook.getSheet("TestCaseDiscription");
		if(Scresult=="Fail"){
			WritableCellFormat cellFormat = null;
			WritableFont cellFont = null;
			cellFont = new WritableFont(WritableFont.ARIAL, 9);
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setWrap(true);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setBackground(Colour.RED);
			int num = DataDriven2();
			wsheet.addCell(new Label(3 , ResultColumn , Scresult,cellFormat));
		}
		else if(Scresult=="Pass"){
			WritableCellFormat cellFormat = null;
			WritableFont cellFont = null;
			cellFont = new WritableFont(WritableFont.ARIAL, 9);
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setWrap(true);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setBackground(Colour.GREEN);
			wsheet.addCell(new Label(3 , ResultColumn , Scresult,cellFormat));
		}
		else{
			wsheet.addCell(new Label(3 , ResultColumn, Scresult,CellFormat1()));
		}
		wsheet.addCell(new Label(4 , ResultColumn , ObjectRepository.dateString(),CellFormat1()));
	}

	public void closedoc() throws IOException, WriteException{

		count.getAndSet(3);
		count1.getAndSet(3);
		count2.getAndSet(3);
		count3.getAndSet(3);
		count4.getAndSet(3);
		count5.getAndSet(6);
		pageObject.ProductSearchPage.count6.getAndSet(0);

		wsheet = wbook.getSheet("ResultSheet");
		wsheet.getSettings().setProtected(true);
		wbook.setProtected(true);
		wsheet = wbook.getSheet("TestCaseDiscription");
		wsheet.getSettings().setProtected(true);
		wbook.setProtected(true);
		wbook.write();
		wbook.close();
		book.close();
	}
	public static Sheet TCSheet(Sheet sheet1) throws BiffException, IOException, WriteException {
		obr.repository(driver);
		book1 = Workbook.getWorkbook(new File("D:\\TC.xls"));
		sheet1 = book1.getSheet("Sheet1");
		return sheet1;
	}


}
