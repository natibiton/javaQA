package org.example.sites.expandtesting.api_actions;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.sites.expandtesting.modules.User;
import org.testng.Assert;

/**
 * @Notes
 * <ul>
 *     <li>There seems to be some bug with the change password, so that API does not appear here</ul>
 * </ul>
 */
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
    public User registerUser(User inputUser) {
        JsonPath jsonPath = invokeAndValidateMessage(Method.POST, "register",  inputUser,201, "User account created successfully");

        User responseUser = jsonPath.getObject("data", User.class);
        Assert.assertNotNull(responseUser.getId(), "The response User ID should not be empty!");

        //The response user has the user id but not the user password, while the input user has the user password but not the user id
        Assertions.assertThat(responseUser).usingRecursiveComparison().ignoringFields("id", "password").isEqualTo(inputUser);

        //Update the relevant data in the input and response users
        inputUser.setId(responseUser.getId());
        responseUser.setPassword(inputUser.getPassword());

        return inputUser;
    }

    public void failUserRegistration(User inputUser, String expectedMessage) {
        invokeAndValidateMessage(Method.POST, "register", inputUser, 400, expectedMessage);
    }

    /**
     * Login to the application
     *
     * @param inputUser The user credentials for the login
     * @Notes Will modify the inputUser object by adding the login token
     */
    public void userLogin(User inputUser){
        JsonPath jsonPath = invokeAndValidateMessage(Method.POST, "login", inputUser, 200, "Login successful");

        User responseUser = jsonPath.getObject("data", User.class);
        Assert.assertNotNull(responseUser.getToken());

        Assertions.assertThat(responseUser).usingRecursiveComparison().ignoringFields("password", "token", "company", "phone").isEqualTo(inputUser);

        inputUser.setToken(responseUser.getToken());
    }

    public void userLoginFailure(User inputUser) {
        invokeAndValidateMessage(Method.POST, "login", inputUser, 401, "Incorrect email address or password");
    }

    public User getUserProfile(User inputUser){
        JsonPath jsonPath = invokeAndValidateMessage(Method.GET, "profile", inputUser, 200, "Profile successful");
        return jsonPath.getObject("data", User.class);
    }

    public void updateUserProfile(User inputUser){
        invokeAndValidateMessage(Method.PATCH, "profile", inputUser, 200, "Profile updated successful");
    }

    public void updateUserProfileFailure(User inputUser){
        invokeAndValidateMessage(Method.PATCH, "profile", inputUser, 400, "Invalid Request");
    }

    /**
     * Logout from the application
     * @param inputUser The user to logout
     * @Notes Will remove the token value from the input user once logged out since it's no longer valid
     */
    public void logout(User inputUser){
        invokeAndValidateMessage(Method.DELETE, "logout", inputUser, 200, "User has been successfully logged out");
        inputUser.setToken(null);
    }

    /**
     * Logout from the application
     * @param inputUser The user to logout
     * @Notes Will remove the token value from the input user once logged out since it's no longer valid
     */
    public void deleteUser(User inputUser){
        invokeAndValidateMessage(Method.DELETE, "delete-account", inputUser, 200, "Account successfully deleted");
        inputUser.setToken(null);
    }

    /**
     * Helper method for this class, will assist in Invoking the API with message validation for support in both happy flow and error flow
     * @param method The REST method to run
     * @param resourcePath The resource path under the users API path
     * @param inputUser The input user object
     * @param expectedStatusCode The expected status code
     * @param expectedMessage The expected response message
     * @return The response of the API, relevant for the happy flow
     */
    private JsonPath invokeAndValidateMessage(Method method, String resourcePath, User inputUser, int expectedStatusCode, String expectedMessage){
        Response response;
        switch(method){
            case GET -> {
                response = invokeUserGet(null, resourcePath, inputUser.getToken(), expectedStatusCode);
            }
            case POST -> {
                response = invokeUserPost(null, resourcePath, inputUser, expectedStatusCode);
            }
            case DELETE -> {
                response = invokeUserDelete(null, resourcePath, inputUser.getToken(), expectedStatusCode);
            }
            case PATCH -> {
                response = invokeUserPatch(null, resourcePath, inputUser, expectedStatusCode);
            }
            default -> throw new RuntimeException(String.format("No support for the REST method of {}", method));
        }
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(message, expectedMessage);
        return jsonPath;
    }

}
