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
	private static int count=0;
	//static protected WebDriver driver;
Browser brow =  new Browser();
	static private WebDriver driver;

	public static String screenshots() throws IOException {

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String name = ".\\screenshots\\screen-"+count+".jpeg";
		FileUtils.copyFile(scrFile, new File(name));
		count++;
		return name;

	}



}
