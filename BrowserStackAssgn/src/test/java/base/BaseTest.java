package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;

    @Parameters({"browser", "os", "osVersion","device"})
    @BeforeMethod
    public void setUp(String browser, String os, String osVersion, String device) {
        try {
			driver = BrowserStackSetUp.getDriver(browser, os, osVersion, device);
		} catch (Exception e) {
			e.printStackTrace();
		}
        driver.manage().window().maximize();
        driver.get("https://elpais.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
