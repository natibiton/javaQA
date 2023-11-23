package org.example.sites.expandtesting.api_actions;

import io.restassured.response.Response;
import org.example.core.api_actions.BaseAbstractApiActions;
import org.example.sites.expandtesting.modules.User;

import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

//TODO refactor by usage of methods
public class BaseApiActions extends BaseAbstractApiActions {
    private final String USERS_PATH = "users/";

    public BaseApiActions() {
        super("https://practice.expandtesting.com/notes/api/");
    }

    public Response invokeBasicGet(Map<String, String> headers, String resourcePath, int expectedStatusCode){
        Response response = given()
                .headers(Optional.ofNullable(headers).orElseGet(super::getHeaders))
                .when().get(this.baseEndPoint + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserPost(Map<String, String> headers, String resourcePath, User user, int expectedStatusCode){
        Response response = given()
                .headers(Optional.ofNullable(headers).orElseGet(super::getHeaders))
                .body(user)
                .when().post(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserDelete(Map<String, String> headers, String resourcePath, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(Optional.ofNullable(headers).orElseGet(super::getHeaders))
                .when().delete(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserGet(Map<String, String> headers, String resourcePath, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(Optional.ofNullable(headers).orElseGet(super::getHeaders))
                .when().get(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeUserPatch(Map<String, String> headers, String resourcePath, User inputUser, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", inputUser.getToken());

        Response response = given()
                .headers(Optional.ofNullable(headers).orElseGet(super::getHeaders))
                .body(inputUser)
                .when().patch(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }
}
