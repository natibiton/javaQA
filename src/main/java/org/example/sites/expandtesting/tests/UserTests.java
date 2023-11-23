package org.example.sites.expandtesting.tests;

import org.assertj.core.api.Assertions;
import org.example.core.report.Report;
import org.example.core.tests.BaseTest;
import org.example.core.tests.TestGroups;
import org.example.sites.expandtesting.api_actions.HealthCheckApi;
import org.example.sites.expandtesting.api_actions.UsersApi;
import org.example.sites.expandtesting.modules.User;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {
    /**
     * User to be shared among the various tests instead of creating a new one
     */
    private User testUser;

    @Test(groups = {TestGroups.API, TestGroups.SANITY }, priority = 1)
    public void createUser() {
        HealthCheckApi healthCheckApi = new HealthCheckApi();
        healthCheckApi.appHealthCheck();

        UsersApi usersApi = new UsersApi();
        User inputUser = new User();
        User responseUser = usersApi.registerUser(inputUser);
        Report.log(String.format("Created the user of: ", responseUser));
        System.out.println("User = "+ responseUser);
        testUser = responseUser;
    }

    @Test(groups = {TestGroups.API, TestGroups.ERROR_SCENARIO })
    public void createUserFailures() {
        UsersApi usersApi = new UsersApi();
        User inputUser = new User();
        inputUser.setPassword("2121"); //Less than 6 characters
        usersApi.failUserRegistration(inputUser, "Password must be between 6 and 30 characters");
        inputUser.setPassword("212121");
        inputUser.setEmail("noSuchMail"); //Invalid mail format
        usersApi.failUserRegistration(inputUser, "A valid email address is required");
        inputUser.setEmail("test11@mail.com");
        inputUser.setName(null); //Empty name
        usersApi.failUserRegistration(inputUser, "User name must be between 4 and 30 characters");
    }

    @Test(groups = {TestGroups.API, TestGroups.ERROR_SCENARIO })
    public void userLoginFailures() {
        UsersApi usersApi = new UsersApi();
        User inputUser = new User();
        User responseUser = usersApi.registerUser(inputUser);

        User userMailIssue = responseUser;
        userMailIssue.setEmail("a" + userMailIssue.getEmail());
        usersApi.userLoginFailure(userMailIssue);

        User userPassIssue = responseUser;
        userPassIssue.setPassword("a" + userPassIssue.getPassword());
        usersApi.userLoginFailure(userPassIssue);
    }

    @Test(groups = {TestGroups.API, TestGroups.SANITY} , priority = 2)
    public void loginToApp(){
        UsersApi usersApi = new UsersApi();
        usersApi.userLogin(testUser);
        Report.log(String.format("User logged in: ", testUser));
        System.out.println("User = "+ testUser);
    }

    @Test(groups = {TestGroups.API, TestGroups.REGRESSION}, priority = 3)
    public void getAndUpdateUserProfile(){
        UsersApi usersApi = new UsersApi();
        System.out.println("User = "+ testUser);
        User responseUser = usersApi.getUserProfile(testUser);
        System.out.println("Response User = "+ responseUser);

        testUser.setPhone("432409340");
        testUser.setCompany("Some company");

        usersApi.updateUserProfile(testUser);

        User responseUser2 = usersApi.getUserProfile(testUser);

        Assertions.assertThat(responseUser2).usingRecursiveComparison().ignoringFields("password", "token").isEqualTo(testUser);
    }

    @Test(groups = {TestGroups.API, TestGroups.ERROR_SCENARIO})
    public void updateProfileError(){
        UsersApi usersApi = new UsersApi();
        User inputUser = new User();
        User responseUser = usersApi.registerUser(inputUser);
        usersApi.userLogin(responseUser);
        usersApi.updateUserProfileFailure(responseUser);
    }

    @Test(groups = {TestGroups.API, TestGroups.SANITY}, priority = 4)
    public void logoutFromApp(){
        UsersApi usersApi = new UsersApi();
        usersApi.logout(testUser);
        Report.log(String.format("User logged out: ", testUser));
        System.out.println("User = "+ testUser);
    }

    @Test(groups = {TestGroups.API, TestGroups.SANITY}, priority = 5)
    public void deleteUser(){
        UsersApi usersApi = new UsersApi();

        //User must be logged in before running the delete user for some reason (token expiration)
        usersApi.userLogin(testUser);

        usersApi.deleteUser(testUser);
        Report.log(String.format("User deleted: ", testUser));
        System.out.println("User = "+ testUser);
    }

}
