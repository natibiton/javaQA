package org.example.sites.expandtesting.web.pages;

import org.example.core.web.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IntroPage extends BasePage {
    private final static String baseUrl = "https://practice.expandtesting.com/notes/app/";

    @FindBy(xpath = "//a[@href=\"/notes/app/login\"]")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[@data-testid=\"open-register-view\"]")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//a[@data-testid=\"forgot-password-view\"]")
    private WebElement forgotPasswordBtn;

    @FindBy(xpath = "//h1[contains(text(), 'Welcome to Notes App')]")
    private WebElement welcomeText;

    public IntroPage(WebDriver webDriver) {
        super(webDriver, baseUrl, "Test Automation Practice: Working with My Notes React App");
        browseToBasePage(); //Since this is the initial window, we would like to browse here first
        validateBasePage(welcomeText);
    }

    public LoginPage clickLoginButton(){
        loginBtn.click();
        return new LoginPage(this.webDriver);
    }

    public void createAccount(){
        createAccountBtn.click();
    }

    public void forgotPassword(){
        forgotPasswordBtn.click();
    }

    @Override
    public void validateBasePage(WebElement elementToFind) {
        wait.until(ExpectedConditions.visibilityOf(elementToFind));
    }
}
