package org.example.sites.expandtesting.modules;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
@ToString
public class User {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String id;

    /**
     * The Login token of the user
     */
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private String phone;
    @Getter
    @Setter
    private String company;

    private Map<String, Note> userNotes = new HashMap<>();

    public User(){
        generateRandomUser();
    }

    private void generateRandomUser(){
        this.name = RandomStringUtils.randomAlphabetic(7);
        this.password = RandomStringUtils.randomAlphanumeric(6);
        this.email = name.toLowerCase() + "@gmail.com";
    }

    public void addUserNote(Note note){
        userNotes.put(note.getId(), note);
    }

    public void removeUserNote(String id){
        userNotes.remove(id);
    }

    /**
     * Returns a copy of the note
     * @param id The Note ID to get
     * @return A copy of the actual user note
     */
    public Note getUserNote(String id){
        Note note = userNotes.get(id);
        if(note == null){
            return null;
        }
        return new Note(note);
    }

    /**
     * Get (a copy of) the user notes
     * @return A list that contains a copy of all the current user notes
     */
    public List<Note> getUserNotes(){
        List<Note> notes = new ArrayList<>();
        for(Note note : userNotes.values()){
            notes.add(new Note(note));
        }

        return notes;
    }

    public int getUserNotesAmount(){
        return userNotes.size();
    }
}