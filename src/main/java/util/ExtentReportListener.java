package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static page.BasePage.takeScreenshot;

public class ExtentReportListener implements ITestListener {

    private static final String fileSeparator = File.separator;
    private static final String rootDirectory = System.getProperty("user.dir");
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(rootDirectory + fileSeparator + "reports" + fileSeparator +
                "html-report" + fileSeparator + "execution-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle("Test Execution Report - Bookings.com");
        htmlReporter.config().setReportName("Test Execution Report - Bookings.com");
        htmlReporter.config().setTheme(Theme.DARK);

        extent.setSystemInfo("Application Name", "Bookings.com");
        extent.setSystemInfo("Test Developer", "Randima Senanayake");
        extent.setSystemInfo("Environment", "Production");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Operating System", "Ubuntu 18.04.5 LTS - 64 Bit");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.RED));
        test.log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getThrowable().getMessage(), ExtentColor.RED));
        try {
            test.fail("Screenshot at the failed moment is below.");
            test.addScreenCaptureFromPath(takeScreenshot(iTestResult.getName()), iTestResult.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName(), ExtentColor.GREY));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
