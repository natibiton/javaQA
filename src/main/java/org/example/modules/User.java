package org.example.modules;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
public class User {
    @Getter
    private String userName, password;
    @Getter
    @Setter
    private String userId;
    @Getter
    private List<Book> books;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        books = new ArrayList<>();
    }

    public void addABook(Book book){
        books.add(book);
    }
}
