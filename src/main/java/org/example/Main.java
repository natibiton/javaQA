package org.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.api_actions.UserApi;
import org.example.modules.User;
import org.example.web.BaseWebDriver;
import org.example.web.pages.BooksPage;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {

        BaseWebDriver baseWebDriver = new BaseWebDriver();
        BooksPage booksPage = new BooksPage(baseWebDriver.getWebDriver());
        booksPage.browseToPage();
        booksPage.clickInitialLoginButton();
        Thread.sleep(3000);

        User user = new User(RandomStringUtils.randomAlphabetic(7), "enterNB6!");
        booksPage.createNewUser(user);
        Thread.sleep(10000);


        baseWebDriver.quitDriver();
    }
}