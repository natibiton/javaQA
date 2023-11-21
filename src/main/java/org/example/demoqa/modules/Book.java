package org.example.demoqa.modules;

import lombok.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Book {
    @Getter
    @Setter
    private String isbn, title, subTitle, author, publish_date, publisher, description, website;
    @Getter
    @Setter
    private int pages;

    public Book(){}  //Required for the databind construction of the object

}
