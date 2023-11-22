package org.example.sites.demoqa;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.sites.demoqa.modules.User;
import org.example.core.web.BaseWebDriver;
import org.example.sites.demoqa.web.pages.BooksPage;

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