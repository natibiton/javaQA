package org.example.core.web;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public BasePage(WebDriver webDriver, String baseUrl, String expectedTitle){
        this(webDriver);

        this.baseUrl = baseUrl;
        this.expectedTitle = expectedTitle;
    }

    public BasePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this); // Initializing all the web elements located by @FindBy
        wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(20));
    }

    /**
     * Will browse to base page and validate its title<br>
     * Note: The baseUrl and expectedTitle members should be populated.
     */
    public void browseToBasePage(){
        if(baseUrl == null){
            throw new RuntimeException("User did not set the base url!!!");
        }
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }

    /**
     * Will validate that we are on the base page if the element to find is indeed visible
     * @param elementToFind The element to find in the base page
     * <br>Note: We can assume that at the moment of this method invocation the web browser is on the base page already.</>
     */
    public abstract void validateBasePage(WebElement elementToFind);
}
