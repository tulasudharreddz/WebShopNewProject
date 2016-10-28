package GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ObjectRepository {
	public Properties obj;
	FileInputStream objFile;

	public void repository(WebDriver driver) throws IOException{

		obj= new Properties();
		objFile = new FileInputStream(System.getProperty("user.dir") + "./utility/objects.properties");
		obj.load(objFile);
		PropertyConfigurator.configure(obj.getProperty("log4j"));
	}


}
