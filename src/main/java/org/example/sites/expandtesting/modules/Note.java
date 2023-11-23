package org.example.sites.expandtesting.modules;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Note {
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private Date created_at;
    private Date updated_at;
    private String Category;
    private String user_id;

    public enum Category{
        Home, Work, Personal;
    }

}
