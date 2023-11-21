package org.example.api_actions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.modules.Token;
import org.example.modules.User;

public class TokenApi extends BaseApiActions{
    public Token createToken(User user){
        Response response = invokeUserPost(getHeaders(), user, "GenerateToken", 200);

        JsonPath jsonResponse = response.jsonPath();
        Token token = new Token(jsonResponse.getString("token"), jsonResponse.getString("expires"));

        return token;
    }
}