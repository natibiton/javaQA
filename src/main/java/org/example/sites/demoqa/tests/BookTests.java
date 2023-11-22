package org.example.sites.demoqa.tests;

import org.example.core.tests.TestGroups;
import org.example.sites.demoqa.api_actions.BooksApi;
import org.example.sites.demoqa.modules.Book;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class BookTests {

    @Test(dataProvider = "pagesAmountProvider", groups = {TestGroups.API, TestGroups.SANITY })
    public void testGetLargeBooks(int pageAmount, int expectedBookAmount){
        BooksApi booksApi = new BooksApi();
        List<Book> books = booksApi.getLargeBooks(pageAmount);

        Assert.assertEquals(books.size(), expectedBookAmount, String.format("Expected %d books to have more than %d pages, but got %d books", books.size(), pageAmount, expectedBookAmount));
    }

    @DataProvider
    public Object[][] pagesAmountProvider(){
        return new Object[][]{
                {200, 8},
                {300, 3}
        };
    }

}
