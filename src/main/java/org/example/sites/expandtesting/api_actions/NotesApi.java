package org.example.sites.expandtesting.api_actions;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.sites.expandtesting.modules.Note;
import org.example.sites.expandtesting.modules.User;
import org.testng.Assert;

import java.util.List;

public class NotesApi extends BaseApiActions {
    public Note createNote(Note inputNote, String userToken){
        JsonPath jsonPath = invokeAndValidateMessage(Method.POST, "", userToken, inputNote, 200, "Note successfully created");
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
        JsonPath jsonPath = invokeAndValidateMessage(Method.GET, "", inputUser.getToken(), null, 200, "Notes successfully retrieved");
        List<Note> responseNotes = jsonPath.getList("data", Note.class);

        return responseNotes;
    }

    public Note getNote(String userToken, String noteId){
        JsonPath jsonPath = invokeAndValidateMessage(Method.GET, noteId, userToken, null, 200, "Note successfully retrieved");
        Note responseNote = jsonPath.getObject("data", Note.class);

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
        JsonPath jsonPath = invokeAndValidateMessage(Method.PUT, inputNote.getId(), inputUser.getToken(), inputNote, 200, "Note successfully Updated");
        Note responseNote = jsonPath.getObject("data", Note.class);

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
        JsonPath jsonPath = invokeAndValidateMessage(Method.PATCH, inputNote.getId(), inputUser.getToken(), inputNote, 200, "Note successfully Updated");
        Note responseNote = jsonPath.getObject("data", Note.class);

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
        JsonPath jsonPath = invokeAndValidateMessage(Method.DELETE, inputNote.getId(), inputUser.getToken(), null, 200, "Note successfully deleted");

        Assert.assertEquals(jsonPath.getBoolean("success"), Boolean.TRUE);
        inputUser.removeUserNote(inputNote.getId());
    }

    private JsonPath invokeAndValidateMessage(Method method, String resourcePath, String inputUserToken, Note inputNote, int expectedStatusCode, String expectedMessage){
        Response response;
        switch(method){
            case GET -> {
                if(resourcePath == "") { //The resource path will contain the note id in case we want to get a specific note.
                    response = invokeNoteGet(resourcePath, inputUserToken, expectedStatusCode);
                }
                else{
                    response = invokeNoteGetSpecific(resourcePath, inputUserToken, expectedStatusCode);
                }
            }
            case POST -> {
                response = invokeNotePost(resourcePath, inputUserToken, inputNote, expectedStatusCode);
            }
            case PUT -> {
                response = invokeNotePut(resourcePath, inputNote, inputUserToken, expectedStatusCode);
            }
            case DELETE -> {
                response = invokeNoteDelete(resourcePath, inputUserToken, expectedStatusCode);
            }
            case PATCH -> {
                response = invokeNotePatch(resourcePath, inputNote, inputUserToken, expectedStatusCode);
            }
            default -> throw new RuntimeException(String.format("No support for the REST method of {}", method));
        }
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(message, expectedMessage);
        return jsonPath;
    }

}
