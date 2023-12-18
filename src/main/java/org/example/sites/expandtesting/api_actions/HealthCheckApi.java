package org.example.sites.expandtesting.api_actions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class HealthCheckApi extends BaseApiActions {
    public void appHealthCheck(){
        Response response = invokeBasicGet("health-check", 200);

        JsonPath jsonResponse = response.jsonPath();
        String message = jsonResponse.get("message");
        Assert.assertEquals(message, "Notes API is Running");
    }

}
