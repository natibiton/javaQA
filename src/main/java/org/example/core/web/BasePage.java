package org.example.core.web;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver webDriver;
    protected WebDriverWait wait;

    @Getter
    private String baseUrl;

    @Getter
    private String expectedTitle;

    public BasePage(BaseWebDriver baseWebDriver, String baseUrl, String expectedTitle){
        this.webDriver = baseWebDriver.getWebDriver();
        PageFactory.initElements(this.webDriver, this); // Initializing all the web elements located by @FindBy
        wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(60));

        this.baseUrl = baseUrl;
        this.expectedTitle = expectedTitle;
    }

    public void browseToBasePage(){
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }
}
