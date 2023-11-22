package org.example.sites.demoqa.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseBrowserPage {
    protected final WebDriver webDriver;
    protected WebDriverWait wait;

    public BaseBrowserPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this); // Initializing all the web elements located by @FindBy
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
    }

    public void browseToPage(String url, String expectedTitle){
        webDriver.get(url);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }
}
