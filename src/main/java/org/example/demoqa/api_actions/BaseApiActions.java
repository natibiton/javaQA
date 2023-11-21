package org.example.demoqa.api_actions;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.demoqa.modules.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class BaseApiActions {
    private final String BaseEndPoint = "https://demoqa.com/";
    private final String BaseEndPointAccount = BaseEndPoint + "Account/v1/";
    @Getter
    private final String BaseEndPointBook = BaseEndPoint + "BookStore/v1/";
    @Getter
    private Map<String, String> headers;

    public BaseApiActions() {
        headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("Content-Type", "application/json");
    }

    public Response invokeUserPost(Map<String, String> headers, User user, String resourcePath, int expectedStatusCode){
        Response response = given().headers(headers).body(user).when().post(BaseEndPointAccount + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

    public Response invokeBookGet(Map<String, String> headers, String resourcePath, int expectedStatusCode){
        Response response = given().headers(headers).when().post(BaseEndPointBook + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }
}
