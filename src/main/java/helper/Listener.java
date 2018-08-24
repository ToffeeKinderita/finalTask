package helper;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    private WebDriver driver;

    public Listener(WebDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Attachment
    private String additionalInfoForFailedTest(WebDriver webdriver) {
        if (webdriver instanceof HasCapabilities) {
            Capabilities capabilities = ((HasCapabilities) webdriver).getCapabilities();
            String info = "Browser = " + capabilities.getBrowserName() + " Version = " + capabilities.getVersion() + " Platform = " + capabilities.getPlatform();
            System.out.println(info);
            return info;
        } else
            return null;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        additionalInfoForFailedTest(driver);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}
