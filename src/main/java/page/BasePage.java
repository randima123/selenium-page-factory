package page;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BasePage {

    public static WebDriver driver;
    private static final String fileSeparator = File.separator;
    private static final String rootDirectory = System.getProperty("user.dir");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilElementVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilElementClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void click(WebElement webElement) {
        waitUntilElementClickable(webElement);
        webElement.click();
    }

    public WebElement getElement(WebElement webElement) {
        waitUntilElementVisible(webElement);
        return webElement;
    }

    public List<WebElement> getElements(List<WebElement> webElements) {
        for(WebElement element : webElements) {
            waitUntilElementVisible(element);
        }
        return webElements;
    }

    public void sendKeys(WebElement webElement, String text) {
        waitUntilElementVisible(webElement);
        webElement.sendKeys(text);
    }

    public static String takeScreenshot(String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = rootDirectory + fileSeparator + "reports" + fileSeparator +
                "html-report" + fileSeparator + "screenshots" + fileSeparator +
                screenshotName + " - " + timestamp + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
