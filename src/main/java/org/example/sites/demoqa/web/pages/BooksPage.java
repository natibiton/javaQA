package org.example.sites.demoqa.web.pages;

import lombok.Getter;
import org.example.sites.demoqa.modules.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BooksPage extends BaseBrowserPage{
    @Getter
    private final String baseUrl = "https://demoqa.com/books";
    @FindBy(id = "login")
    private WebElement loginBtn;
    @FindBy(id = "userName")
    private WebElement userNameTextBox;
    @FindBy(id = "password")
    private WebElement passwordTextBox;
    @FindBy(id = "newUser")
    private WebElement newUserBtn;

    public BooksPage(WebDriver webDriver){
        super(webDriver);
    }

    public void browseToPage() {
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.titleIs("DEMOQA"));
    }

    public void clickInitialLoginButton(){
        this.loginBtn.click();
    }

    public void login(User user){
        loginBtn.sendKeys(user.getUserName());
        loginBtn.sendKeys((user.getPassword()));
        loginBtn.click();
    }

    public void createNewUser(User user){
        loginBtn.sendKeys(user.getUserName());
        loginBtn.sendKeys((user.getPassword()));
        newUserBtn.click();
    }

}
