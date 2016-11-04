package GenericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;



public class DataDriven {
	
	public static Workbook book;
	public static WritableWorkbook wbook;
	public static Sheet sheet;
	public static WritableSheet wsheet;
	
	
	ObjectRepository obr = new ObjectRepository();

	Properties obj;
	FileInputStream objFile;
	WebDriver driver;
	
	public Sheet ReadSheet(Sheet sheet) throws BiffException, IOException{
		/*obj= new Properties();
		objFile = new FileInputStream(System.getProperty("user.dir") + "./utility/objects.properties");
		obj.load(objFile);*/
		
		obr.repository(driver);
		book = Workbook.getWorkbook(new File(obr.obj.getProperty("testData")));
		sheet = book.getSheet("BrowserStac");
		
		return sheet;
		
		
	}
	
	public WritableSheet writeSheet(WritableSheet wsheet,String moduleName, String testcase) throws IOException{
		wbook = Workbook.createWorkbook(new File("./TestData/" + moduleName + "/" + testcase +".xls"), book);
		wsheet = wbook.getSheet("test");
		
		return wsheet;
	}
	
	public void closedoc() throws IOException, WriteException{
		wbook.write();
		wbook.close();
		book.close();
		
	}
	
	
	
	
	

}
