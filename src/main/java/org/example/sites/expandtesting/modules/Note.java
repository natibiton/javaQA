package org.example.sites.expandtesting.modules;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
    private String Category;
    @JsonProperty("user_id")
    private String userId;

    public enum Category{
        Home, Work, Personal;
    }

}
