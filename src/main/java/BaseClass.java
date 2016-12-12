import GenericLib.Browser;
import GenericLib.DataDriven;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

/**
 * Created by t.mirasipally on 09-Dec-16.
 */
public class BaseClass extends Browser{
    private WebDriver driver;
    private Sheet sheet;
    private WritableSheet wsheet;
    DataDriven Baseexcel = new DataDriven();

    @BeforeClass
    public void setUp() throws WriteException, IOException, BiffException {
        driver=getDriver();

    }





}
