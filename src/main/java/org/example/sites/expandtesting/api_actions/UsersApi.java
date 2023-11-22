package org.example.sites.expandtesting.api_actions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.sites.expandtesting.modules.User;
import org.testng.Assert;

public class UsersApi extends BaseApiActions {
    /**
     * Register/add a new user
     * @param inputUser The user details to register
     * @return The response user from the application
     * @Notes:
     * <ul>
     *     <li>Will perform validation that the response values correspond to the input user</li>
     *     <li>Will add the user id from the response user to the input user and will add the input user password to the response user</li>
     * </ul>
     */
    public User registerUser(User inputUser) throws JsonProcessingException {
        Response response = invokeUserPost(null, "register",  inputUser,201);

        JsonPath jsonResponse = response.jsonPath();
        String message = jsonResponse.get("message");
        Assert.assertEquals(message, "User account created successfully");
        User responseUser = jsonResponse.getObject("data", User.class);

        Assert.assertNotNull(responseUser.getId(), "The response User ID should not be empty!");

        //The response user has the user id but not the user password, while the input user has the user password but not the user id
        Assertions.assertThat(responseUser).usingRecursiveComparison().ignoringFields("id", "password").isEqualTo(inputUser);

        //Update the relevant data in the input and response users
        inputUser.setId(responseUser.getId());
        responseUser.setPassword(inputUser.getPassword());

        return inputUser;
    }

}
