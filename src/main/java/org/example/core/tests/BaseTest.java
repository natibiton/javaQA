package org.example.core.tests;

import org.example.core.report.Report;
import org.example.core.web.BaseWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Optional;

public class BaseTest {
    private BaseWebDriver baseWebDriver;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        Report.log("About to run " + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method) {
        closeBrowser();
        Report.log("Done running " + method.getName() + "\n");
    }

    /**
     * Call this method when we need to run Web UI in our test method
     * @return The WebDriver
     */
    protected WebDriver createWebDriver() {
        closeBrowser();
        baseWebDriver = new BaseWebDriver();
        return baseWebDriver.getWebDriver();
    }

    private void closeBrowser() {
        Optional.ofNullable(baseWebDriver)
                .ifPresent(BaseWebDriver::quitDriver);
        baseWebDriver = null;
    }
}
