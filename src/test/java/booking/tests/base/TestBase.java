package booking.tests.base;

import booking.utility.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod
    public void setupDriver() {
        Driver.getDriver();
    }

    @AfterMethod
    public void closeDriver() {
        //close the browser
        Driver.closeBrowser();
    }
}

