package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static util.DriverFactory.getDriver;
import static util.PropertyFileReader.getProperty;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void before() {
        driver = getDriver(getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(getProperty("application-url"));
    }

    @AfterMethod
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}
