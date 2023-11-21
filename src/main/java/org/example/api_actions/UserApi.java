package org.example.api_actions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.modules.User;

public class UserApi extends BaseApiActions{
    public User createUser(String userName, String password){
        User user = new User(userName, password);
        Response response = invokeUserPost(getHeaders(), user, "User", 201);

        JsonPath jsonResponse = response.jsonPath();
        user.setUserId(jsonResponse.get("userID"));
        return user;
    }

}
