package org.example.demoqa.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseWebDriver {
    @Getter
    private final WebDriver webDriver;

    /**
     * At the current moment, only creates a new chrome driver, can modify to a browser factory in the future
     */
    public BaseWebDriver(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        // For the issue of SocketException connection reset
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    /**
     * Quits the driver, closing every associated window
     */
    public void quitDriver(){
        webDriver.quit();
    }
}
