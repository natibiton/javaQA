package org.example.sites.expandtesting.tests;

import org.example.core.tests.BaseTest;
import org.example.core.tests.TestGroups;
import org.example.sites.expandtesting.api_actions.HealthCheckApi;
import org.testng.annotations.Test;

public class E2ETests extends BaseTest {
    @Test(groups = {TestGroups.API, TestGroups.SANITY })
    public void validateAppIsAlive(){
        HealthCheckApi healthCheckApi = new HealthCheckApi();
        healthCheckApi.appHealthCheck();
    }

}
