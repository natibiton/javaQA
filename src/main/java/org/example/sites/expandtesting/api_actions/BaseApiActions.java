package org.example.sites.expandtesting.api_actions;

import io.restassured.response.Response;
import org.example.core.api_actions.BaseAbstractApiActions;
import org.example.sites.expandtesting.modules.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

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
        HashMap userData = new HashMap();
        userData.put("name", user.getName());
        userData.put("email", user.getEmail());
        userData.put("password", user.getPassword());

        Response response = given()
                .headers(Optional.ofNullable(headers).orElseGet(super::getHeaders))
                .body(user)
                .when().post(this.baseEndPoint  + USERS_PATH + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }
}
