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

    protected Response invokePost(Map<String, String> headers, String url, Object data, int expectedStatusCode){
        Response response = given()
                .headers(Optional.ofNullable(headers).orElseGet(super::getHeaders))
                .body(data)
                .when().post(url);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    protected Response invokeDelete(String url, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .when().delete(url);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    protected Response invokeGetWithToken(String url, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .when().get(url);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    protected Response invokePatch(String url, Object data, String token, int expectedStatusCode){
        Map<String, String> headersWithToken = this.getHeaders();
        headersWithToken.put("x-auth-token", token);

        Response response = given()
                .headers(getHeaders())
                .body(data)
                .when().patch(url);
        response.then().statusCode(expectedStatusCode);

        return response;
    }
}
