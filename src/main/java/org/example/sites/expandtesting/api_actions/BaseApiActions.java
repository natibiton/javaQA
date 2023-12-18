package org.example.sites.expandtesting.api_actions;

import io.restassured.response.Response;
import org.example.core.api_actions.BaseAbstractApiActions;
import org.example.sites.expandtesting.modules.Note;
import org.example.sites.expandtesting.modules.User;

import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class BaseApiActions extends BaseAbstractApiActions {
    private final String USERS_PATH = "users/";
    private final String NOTES_PATH = "notes/";

    public BaseApiActions() {
        super("https://practice.expandtesting.com/notes/api/");
    }

    public Response invokeBasicGet(String resourcePath, int expectedStatusCode){
        Response response = given()
                .headers(getHeaders())
                .when().get(this.baseEndPoint + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserPost(String resourcePath, User user, int expectedStatusCode){
        Response response = given()
                .headers(getHeaders())
                .body(user)
                .when().post(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeNotePost(String resourcePath, String token, Note note, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);
        Response response = given()
                .headers(headersWithToken)
                .body(note)
                .when().post(this.baseEndPoint  + NOTES_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserDelete(String resourcePath, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .when().delete(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    /**
     * @param resourcePath       The note id
     * @param token
     * @param expectedStatusCode
     * @return
     */
    public Response invokeNoteDelete(String resourcePath, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .when().delete(this.baseEndPoint  + NOTES_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserGet(String resourcePath, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .when().get(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeNoteGet(String resourcePath, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .when().get(this.baseEndPoint  + NOTES_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    /**
     * @param resourcePath       The ID of the note
     * @param token
     * @param expectedStatusCode
     * @return
     */
    public Response invokeNoteGetSpecific(String resourcePath, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .when().get(this.baseEndPoint  + NOTES_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    /**
     * @param resourcePath       The note id
     * @param note
     * @param token
     * @param expectedStatusCode
     * @return
     */
    public Response invokeNotePut(String resourcePath, Note note, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .body(note)
                .when().put(this.baseEndPoint  + NOTES_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeNotePatch(String resourcePath, Note note, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .body(note)
                .when().patch(this.baseEndPoint  + NOTES_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserPatch(String resourcePath, User inputUser, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", inputUser.getToken());

        Response response = given()
                .headers(getHeaders())
                .body(inputUser)
                .when().patch(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }
}
