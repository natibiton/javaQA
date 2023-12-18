package org.example.sites.expandtesting.api_actions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class HealthCheckApi extends BaseApiActions {
    public void appHealthCheck(){
        Response response = invokeGet("health-check", 200);

        JsonPath jsonResponse = response.jsonPath();
        String message = jsonResponse.get("message");
        Assert.assertEquals(message, "Notes API is Running");
    }

    private Response invokeGet(String resourcePath, int expectedStatusCode){
        Response response = given()
                .headers(getHeaders())
                .when().get(this.baseEndPoint + resourcePath);
        response.then().statusCode(expectedStatusCode);

        return response;
    }

}
