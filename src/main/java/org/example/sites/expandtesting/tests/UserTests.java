package org.example.sites.expandtesting.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.core.report.Report;
import org.example.core.tests.BaseTest;
import org.example.core.tests.TestGroups;
import org.example.sites.expandtesting.api_actions.HealthCheckApi;
import org.example.sites.expandtesting.api_actions.UsersApi;
import org.example.sites.expandtesting.modules.User;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    @Test(groups = {TestGroups.API, TestGroups.SANITY })
    public void createUser() throws JsonProcessingException {
        HealthCheckApi healthCheckApi = new HealthCheckApi();
        healthCheckApi.appHealthCheck();

        UsersApi usersApi = new UsersApi();
        User inputUser = new User();
        User responseUser = usersApi.registerUser(inputUser);
        Report.log(String.format("Created the user of: ", responseUser));
        System.out.println("User = "+ responseUser);
    }

}
