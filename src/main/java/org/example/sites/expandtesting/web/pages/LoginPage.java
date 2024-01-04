package org.example.sites.expandtesting.web.pages;

import org.example.core.web.BasePage;
import org.example.core.web.BaseWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final static String baseUrl = "https://practice.expandtesting.com/notes/app/login";

    @FindBy(id = "email")
    private WebElement emailTextBox;

    @FindBy(id = "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//butoon[@data-testid=\"login-submit\"]")
    private WebElement loginBtn;

    @FindBy(xpath = "//h1[contains(text(), 'Login')]")
    private WebElement loginText;

    public LoginPage(BaseWebDriver baseWebDriver) {
        super(baseWebDriver, baseUrl, "Test Automation Practice: Working with My Notes React App");
        wait.until(ExpectedConditions.visibilityOf(loginText));
    }

    public void login(String email, String password){
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        loginBtn.click();
    }

}
