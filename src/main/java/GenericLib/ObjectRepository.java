package GenericLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

public class ObjectRepository {
	public Properties obj;
	FileInputStream objFile;
	static private WebDriver driver;

	public void repository(WebDriver driver) throws IOException{

		obj= new Properties();
		objFile = new FileInputStream(System.getProperty("user.dir") + "./utility/objects.properties");
		obj.load(objFile);
		PropertyConfigurator.configure(obj.getProperty("log4j"));
	}

	public static String dateString(){
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd_MM_yyyy_hh_mm_ss");
		String finaldate = ft.format(dNow);
		return finaldate;
	}
	public static String TimeSt(){
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
		String finalTime = ft.format(dNow);
		return finalTime;
	}
	public static String DateSt(){
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
		String finalDate = ft.format(dNow);
		return finalDate;
	}
	public static String timeString;
	public static String TimeFOrScreenShot(){
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("hh-mm");
		timeString = ft.format(dNow);
		return timeString;
	}
	public static String TimeConstatnt() throws WriteException, IOException {
		String FolderNameS = TimeFOrScreenShot();
		return FolderNameS;

	}
}
