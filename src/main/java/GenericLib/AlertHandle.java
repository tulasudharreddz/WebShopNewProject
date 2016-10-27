package GenericLib;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHandle {

	WebDriver driver;
	Browser brow = new Browser();

	public void acceptAlert(WebDriver driver){		

		Alert alt=driver.switchTo().alert();
		System.out.println(alt.getText());
		alt.accept();
	}

	//dismiss alert
	public void dismissAlert(WebDriver driver){


		Alert alt=driver.switchTo().alert();
		System.out.println(alt.getText());
		alt.dismiss();
	}

	//Get Text from the perticuler Page
	public void getText(WebDriver driver){
		Alert alt=driver.switchTo().alert();
		System.out.println(alt.getText());
		alt.getText();
	}


}
