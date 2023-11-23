package org.example.sites.expandtesting.tests;

import org.example.core.report.Report;
import org.example.core.tests.BaseTest;
import org.example.core.tests.TestGroups;
import org.example.sites.expandtesting.api_actions.HealthCheckApi;
import org.example.sites.expandtesting.api_actions.UsersApi;
import org.example.sites.expandtesting.modules.User;
import org.testng.annotations.Test;

public class NoteTests extends BaseTest {
    /**
     * User to be shared among the various tests instead of creating a new one
     */
    private User testUser;

    @Test(groups = {TestGroups.API, TestGroups.SANITY }, priority = 1)
    public void createUserAndLogin() {
        HealthCheckApi healthCheckApi = new HealthCheckApi();
        healthCheckApi.appHealthCheck();

        UsersApi usersApi = new UsersApi();
        User inputUser = new User();
        User responseUser = usersApi.registerUser(inputUser);
        Report.log(String.format("Created the user of: ", responseUser));
        System.out.println("User = "+ responseUser);
        testUser = responseUser;
        usersApi.userLogin(testUser);
        Report.log(String.format("User logged in: ", testUser));
        System.out.println("User = "+ testUser);
    }

}
