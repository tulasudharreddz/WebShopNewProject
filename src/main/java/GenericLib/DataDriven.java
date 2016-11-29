package GenericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import org.openqa.selenium.WebDriver;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class DataDriven {

	private static Workbook book;
	private static WritableWorkbook wbook;
	private static Sheet sheet;
	private static WritableSheet wsheet;
	
	ObjectRepository obr = new ObjectRepository();
	//Properties obj;
	FileInputStream objFile;
	WebDriver driver;
	
	public Sheet ReadSheet(Sheet sheet) throws BiffException, IOException{
		obr.repository(driver);
		book = Workbook.getWorkbook(new File(obr.obj.getProperty("testData")));
		sheet = book.getSheet("ResultSheet");
		wbook = Workbook.createWorkbook(new File("./TestData/testCases/"+"WS_TC_59" +ObjectRepository.dateString(driver)+".xls"), book);
		return sheet;
	}
	
public static WritableSheet writeSheet(WebDriver driver) throws IOException{

		wsheet = wbook.getSheet("ResultSheet");
		
		return wsheet;
	}
	private static final AtomicInteger count = new AtomicInteger(13);
	private static final AtomicInteger count1 = new AtomicInteger(13);

	public static int DataDriven(){
		//int counted = 14;
		int expcounted = count.incrementAndGet();
		return expcounted;
	}
	public static int DataDriven1(){
		//int counted = 14;
		int Actucounted = count1.incrementAndGet();
		return Actucounted;
	}

	public static void ExpectedLable(String resu) throws IOException, WriteException {

		wsheet = wbook.getSheet("ResultSheet");
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.TIMES, 12);
		cellFont.setBoldStyle(WritableFont.BOLD);
		cellFormat = new WritableCellFormat(cellFont);
		wsheet.addCell(new Label(1 , DataDriven() , resu));
		//cellFormat.setBorder(Border.ALL, BorderLineStyle.THICK);
		cellFormat.setBorder(Border.BOTTOM,BorderLineStyle.THICK);
		cellFormat.setBorder(Border.TOP,BorderLineStyle.THICK);
		cellFormat.setBorder(Border.RIGHT,BorderLineStyle.THICK);
		cellFormat.setBorder(Border.LEFT,BorderLineStyle.THICK);
	}

	public static void ActualLable(String resu) throws IOException, WriteException {

		wsheet = wbook.getSheet("ResultSheet");
		WritableCellFormat cellFormat = null;
		WritableFont cellFont = null;
		cellFont = new WritableFont(WritableFont.TIMES, 12);
		cellFont.setBoldStyle(WritableFont.BOLD);
		cellFormat = new WritableCellFormat(cellFont);
		//cellFormat.setBorder(Border.ALL, BorderLineStyle.THICK);
		cellFormat.setBorder(Border.BOTTOM,BorderLineStyle.THICK);
		cellFormat.setBorder(Border.TOP,BorderLineStyle.THICK);
		cellFormat.setBorder(Border.RIGHT,BorderLineStyle.THICK);
		cellFormat.setBorder(Border.LEFT,BorderLineStyle.THICK);

		wsheet.addCell(new Label(2 , DataDriven1() , resu));
	}

	public void closedoc() throws IOException, WriteException{
		wbook.write();
		wbook.close();
		book.close();
	}

}
