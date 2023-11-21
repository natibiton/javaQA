package org.example.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.api_actions.AuthorizedApi;
import org.example.api_actions.BooksApi;
import org.example.api_actions.TokenApi;
import org.example.api_actions.UserApi;
import org.example.modules.Book;
import org.example.modules.Token;
import org.example.modules.User;
import org.testng.annotations.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class E2ETests {
    @Test(groups = {TestGroups.API, TestGroups.SANITY })
    public void validateUserBooks(){
        Map.Entry<User, Token> authenticatedUserInfo = authorizeNewUser();
        User user = authenticatedUserInfo.getKey();
        Token token = authenticatedUserInfo.getValue();

        BooksApi booksApi = new BooksApi();
        List<Book> allBooks = booksApi.getAllBooks();
        user.addABook(allBooks.get(0));
        user.addABook(allBooks.get(1));

    }

    private Map.Entry<User, Token> authorizeNewUser(){
        String userName = RandomStringUtils.randomAlphabetic(7);
        String password = "someP123!";

        UserApi userApi = new UserApi();
        TokenApi tokenApi = new TokenApi();
        AuthorizedApi authorizedApi = new AuthorizedApi();

        User user = userApi.createUser(userName, password);
        Token token = tokenApi.createToken(user);
        authorizedApi.authorize(user, token);
        return new AbstractMap.SimpleEntry(user, token);
    }
}
