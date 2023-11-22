package org.example.core.report;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Goal of this class:
 * 1. Enable us to add log lines to the tests with Allure.
 * 2. Make sure that we add this to TestNG reporter in case some future component will need it
 */
@Slf4j
public class Report {

    @Step("{s}")
    public static void log(String s) {
        org.testng.Reporter.log(s);
        log.info(s);
    }

    @Step("{s}")
    public static void error(String s) {
        org.testng.Reporter.log("Error: " + s);
        log.error(s);
    }

    public static void addScreenShot(WebDriver driver, String label) {
        if (driver instanceof TakesScreenshot) {
            log.info("Adding screenshot to " + label);
            takeScreenShort((TakesScreenshot) driver, label);
        } else {
            log.warn("WebDriver doesn't support taking screenshots");
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    //return value is used by the annotation. suppressing warning for static code analysis
    @Attachment(value = "Screenshot {label}", type = "image/png")
    private static byte[] takeScreenShort(TakesScreenshot driver, String label) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

}
