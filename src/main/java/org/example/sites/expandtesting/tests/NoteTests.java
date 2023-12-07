package org.example.sites.expandtesting.tests;

import org.checkerframework.checker.units.qual.N;
import org.example.core.report.Report;
import org.example.core.tests.BaseTest;
import org.example.core.tests.TestGroups;
import org.example.sites.expandtesting.api_actions.HealthCheckApi;
import org.example.sites.expandtesting.api_actions.NotesApi;
import org.example.sites.expandtesting.api_actions.UsersApi;
import org.example.sites.expandtesting.modules.Note;
import org.example.sites.expandtesting.modules.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NoteTests extends BaseTest {
    /**
     * User to be shared among the various tests instead of creating a new one
     */
    private User testUser;

    public NoteTests(){
        HealthCheckApi healthCheckApi = new HealthCheckApi();
        healthCheckApi.appHealthCheck();

        UsersApi usersApi = new UsersApi();
        User inputUser = new User();
        User responseUser = usersApi.registerUser(inputUser);
        Report.log(String.format("Created the user of: ", responseUser));
        System.out.println("User = "+ responseUser);
        testUser = responseUser;
        usersApi.userLogin(testUser);
        Report.log(String.format("User logged in: ", testUser));
        System.out.println("User = "+ testUser);
    }

    @Test(groups = {TestGroups.API, TestGroups.SANITY }, priority = 1)
    public void createNote() {
        NotesApi notesApi = new NotesApi();
        Note note = new Note();
        Note responseNote = notesApi.createNote(note, testUser.getToken());

        System.out.println("Created note is: " + responseNote);
        testUser.addUserNote(responseNote); //Only add the note to the test user if the operation of the creation was a success

        Note getResponseNote = notesApi.getNote(testUser.getToken(), responseNote.getId());
        System.out.println(String.format("Note from application by {} id is {}", testUser.getId(), getResponseNote));
    }

    @Test(groups = {TestGroups.API, TestGroups.SANITY }, priority = 2)
    public void getAllNotes() {
        NotesApi notesApi = new NotesApi();
        List<Note> responseNotes = notesApi.getAllNotes(testUser);

        System.out.println("Note list size is " + responseNotes.size());

        Assert.assertEquals(responseNotes.size(), 1);
    }

}
