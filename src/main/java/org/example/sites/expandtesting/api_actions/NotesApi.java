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

        String message = jsonPath.get("message");
        Assert.assertEquals(message, "Note successfully created");

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

        String message = jsonPath.get("message");
        Assert.assertEquals(message, "Notes successfully retrieved");

        return responseNotes;
    }

    public Note getNote(String userToken, String noteId){
        Response response = invokeNoteGetSpecific(null, noteId, userToken, 200);
        JsonPath jsonPath = response.jsonPath();
        Note responseNote = jsonPath.getObject("data", Note.class);

        String message = jsonPath.get("message");
        Assert.assertEquals(message, "Note successfully retrieved");

        Assert.assertNotNull(responseNote);

        return responseNote;
    }

    /**
     * Update the note details
     * @param inputUser The logged-in user
     * @param inputNote The input note to update
     * @return The response note
     * @Notes: Will not update the note object in the user object
     */
    public Note updateNote(User inputUser, Note inputNote){
        Response response = invokeNotePut(null, inputNote.getId(), inputNote, inputUser.getToken(), 200);
        JsonPath jsonPath = response.jsonPath();
        Note responseNote = jsonPath.getObject("data", Note.class);

        String message = jsonPath.get("message");
        Assert.assertEquals(message, "Note successfully Updated");

        Assertions.assertThat(responseNote).usingRecursiveComparison().comparingOnlyFields("title", "description", "category", "completed").isEqualTo(inputNote);

        return responseNote;
    }

    /**
     * Update the note status
     * @param inputUser The logged-in user
     * @param inputNote The input note to update status for
     * @return The response note
     * @Notes: Will not update the note object in the user object
     */
    public Note updateNoteStatus(User inputUser,Note inputNote){
        inputNote.setCompleted(Boolean.TRUE);
        Response response = invokeNotePatch(null, inputNote.getId(), inputNote, inputUser.getToken(), 200);
        JsonPath jsonPath = response.jsonPath();
        Note responseNote = jsonPath.getObject("data", Note.class);

        String message = jsonPath.get("message");
        Assert.assertEquals(message, "Note successfully Updated");

        Assertions.assertThat(responseNote).usingRecursiveComparison().ignoringFields("updatedAt").isEqualTo(inputNote);
        Assert.assertTrue(responseNote.getUpdatedAt().after(inputNote.getUpdatedAt()));

        return responseNote;
    }

    /**
     * Delete a note
     * @param inputUser The logged-in user
     * @param inputNote The input note to delete
     * @Notes: Will remove the note object in the user object
     */
    public void deleteNote(User inputUser,Note inputNote){
        Response response = invokeNoteDelete(null, inputNote.getId(), inputUser.getToken(), 200);
        JsonPath jsonPath = response.jsonPath();

        String message = jsonPath.get("message");
        Assert.assertEquals(message, "Note successfully deleted");
        Assert.assertEquals(jsonPath.getBoolean("success"), Boolean.TRUE);
        inputUser.removeUserNote(inputNote.getId());
    }

}
