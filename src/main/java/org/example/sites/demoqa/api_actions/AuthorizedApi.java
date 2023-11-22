package org.example.sites.demoqa.api_actions;

import io.restassured.response.Response;
import org.example.sites.demoqa.modules.Token;
import org.example.sites.demoqa.modules.User;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class AuthorizedApi extends BaseApiActions{
    public void authorize(User user, Token token){
        Map<String, String> headers = this.getHeaders();
        headers.put("authorization", "Basic TmF0aUJpdDplbnRlck5CNiE=");
        headers.put("Authorization", token.getToken());

        Response response = invokeUserPost(headers, user, "Authorized", 200);

        Boolean responseBodyStatus = response.then()
                .extract()
                .as(Boolean.class);
        Assertions.assertTrue(responseBodyStatus);
    }
}
