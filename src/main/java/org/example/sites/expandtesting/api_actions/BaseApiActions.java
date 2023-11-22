package org.example.sites.expandtesting.api_actions;

import io.restassured.response.Response;
import org.example.core.api_actions.BaseAbstractApiActions;

import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class BaseApiActions extends BaseAbstractApiActions {

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
}
