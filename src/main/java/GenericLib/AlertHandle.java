package GenericLib;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class AlertHandle {


	Browser brow = new Browser();
    static Logger log = Logger.getLogger("Alert Handle page");

	public static void acceptAlert(WebDriver driver){

		Alert alt=driver.switchTo().alert();
		log.info(alt.getText());
		alt.accept();
	}

	//dismiss alert
	public void dismissAlert(WebDriver driver){


		Alert alt=driver.switchTo().alert();
		log.info(alt.getText());
		alt.dismiss();
	}

	//Get Text from the perticuler Page
	public void getText(WebDriver driver){
		Alert alt=driver.switchTo().alert();
		log.info(alt.getText());
		alt.getText();
	}

}
