package org.example.sites.expandtesting.modules;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Random;

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
    private Category category;
    @JsonProperty("user_id")
    private String userId;

    private static final Random random = new Random();

    public Note(){
        generateRandomNote();
    }

    /**
     * Copy constructor
     * @param note The note to create a copy from
     */
    public Note(Note note){
        this.title = note.title;
        this.description = note.description;
        this.category = note.category;
        this.id = note.id;
        this.completed = note.completed;
        this.createdAt = note.createdAt;
        this.updatedAt = note.updatedAt;
        this.userId = note.userId;
    }

    private void generateRandomNote() {
        this.title = "Test note: " + RandomStringUtils.randomAlphabetic(5);
        this.description = "Test description" + RandomStringUtils.randomAlphabetic(5);
        this.category = randomEnum(Category.class);
    }

    public enum Category{
        Home, Work, Personal;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int pick = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[pick];
    }

}
