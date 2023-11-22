package org.example.sites.demoqa.api_actions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.sites.demoqa.modules.Token;
import org.example.sites.demoqa.modules.User;

public class TokenApi extends BaseApiActions{
    public Token createToken(User user){
        Response response = invokeUserPost(getHeaders(), user, "GenerateToken", 200);

        JsonPath jsonResponse = response.jsonPath();
        Token token = new Token(jsonResponse.getString("token"), jsonResponse.getString("expires"));

        return token;
    }
}
