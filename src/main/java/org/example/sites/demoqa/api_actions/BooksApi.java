package org.example.sites.demoqa.api_actions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.sites.demoqa.modules.Book;

import java.util.List;

public class BooksApi extends BaseApiActions{
    public List<Book> getAllBooks(){
        List<Book> books;

        Response response = invokeBookGet(getHeaders(), "Books", 200);
        JsonPath jsonPath = response.jsonPath();
        books = jsonPath.getList("books", Book.class);

        return books;
    }

    public void printBookNames(){
        Response response = invokeBookGet(getHeaders(), "Books", 200);
        JsonPath jsonPath = response.jsonPath();
        System.out.println("All book names:");
        for(Object book_name : jsonPath.getList("books.title")){
            System.out.println(book_name.toString());
        }
    }

    public List<Book> getLargeBooks(int largerThanPageSize){
        Response response = invokeBookGet(getHeaders(), "Books", 200);

        List<Book> books = response.jsonPath()
                .param("BookPageSize", largerThanPageSize)
                .getList("books.findAll {book -> book.pages > BookPageSize }", Book.class);
        return books;
    }
}
