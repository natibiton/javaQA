package org.example.sites.expandtesting.api_actions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.sites.expandtesting.modules.Note;
import org.example.sites.expandtesting.modules.User;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class NotesApi extends BaseApiActions {
    public Note createNote(Note inputNote, String userToken){
        Map headers = getHeaders();
        headers.put("x-auth-token", userToken);
        Response response = invokeNotePost(headers, "", inputNote, 200);
        JsonPath jsonPath = response.jsonPath();
        Note responseNote = jsonPath.getObject("data", Note.class);

        Assert.assertNotNull(responseNote.getId(), "The ID field can't be empty for note creation");
        Assert.assertNotNull(responseNote.getCreatedAt(), "The creation date field can't be empty for note creation");
        Assert.assertNotNull(responseNote.getUpdatedAt(), "The update date field can't be empty for note creation");
        Assert.assertNotNull(responseNote.getUserId(), "The User ID field can't be empty for note creation");
        if(inputNote.getId() == null) { // New note, validate is completed indicator
            Assert.assertFalse(responseNote.isCompleted(), "The is completed indicator must be empty for a new note creation");
        }
        else{
            Assert.assertEquals(responseNote.isCompleted(), inputNote.isCompleted(),
                    "The completed indicator should be the same, but it's true for the input while false for the output");
        }

        Assertions.assertThat(responseNote).usingRecursiveComparison().comparingOnlyFields("title", "description", "category").isEqualTo(inputNote);

        return responseNote;
    }

    public List<Note> getAllNotes(User inputUser){
        Response response = invokeNoteGet(null, "", inputUser.getToken(), 200);
        JsonPath jsonPath = response.jsonPath();
        List<Note> responseNotes = jsonPath.getList("data", Note.class);
        return responseNotes;
    }

    public Note getNote(User inputUser, Note inputNote){
        Response response = invokeNoteGetSpecific(null, inputNote.getId(), inputUser.getToken(), 200);
        JsonPath jsonPath = response.jsonPath();
        Note responseNote = jsonPath.getObject("data", Note.class);

        Assert.assertNotNull(responseNote);

        return responseNote;
    }

    public Note updateNote(Note inputNote){

        return null;
    }

    public Note updateNoteStatus(Note inputNote){
        return null;
    }

    public void deleteNote(Note inputNote){

    }

}
