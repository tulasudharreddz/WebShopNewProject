package GenericLib;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import static GenericLib.ObjectRepository.TimeConstatnt;

public class ScreenShots {
	private static int count=0;
	//static protected WebDriver driver;
	Browser brow =  new Browser();
	static private WebDriver driver;
	static ObjectRepository obr = new ObjectRepository();
	private static int SCcount=1;

	public static String screenshots(WebDriver driver) throws IOException, WriteException {

		obr.repository(driver);
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Folder = DataDriven.FolderName();
		String folderName = ObjectRepository.DateSt();

		//String name = ".\\ResultReports\\" + folderName + "\\"+Folder+"-"+TimeConstatnt()+"-screen-"+SCcount+".jpeg";
		String name = obr.obj.getProperty("CreateWorkBookPath") +"\\"+ folderName + "\\"+Folder+"-"+TimeConstatnt()+"-screen-"+SCcount+".jpeg";
		FileUtils.copyFile(scrFile, new File(name));
		SCcount++;
		return name;

	}



}
