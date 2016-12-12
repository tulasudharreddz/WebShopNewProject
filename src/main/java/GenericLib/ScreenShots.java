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

public class ScreenShots {
	static int count=0;

	public static void screenshots(WebDriver driver,String moduleName,String testcase) throws Exception{

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(".\\screenshots\\"+moduleName+"\\"+testcase+"\\screen-"+count+".jpeg"));
		count++;

	}



}
