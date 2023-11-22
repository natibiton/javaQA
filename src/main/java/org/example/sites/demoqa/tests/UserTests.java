package org.example.sites.demoqa.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.example.core.tests.TestGroups;
import org.example.sites.demoqa.api_actions.AuthorizedApi;
import org.example.sites.demoqa.api_actions.TokenApi;
import org.example.sites.demoqa.api_actions.UserApi;
import org.example.sites.demoqa.modules.Token;
import org.example.sites.demoqa.modules.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class UserTests {

    @Test(dataProvider = "userInfoProvider", groups = {TestGroups.API, TestGroups.SANITY })
    public void testUserCreation(String userName, String password){
        UserApi userApi = new UserApi();
        User user = userApi.createUser(userName, password);
        User expectedUser = new User(userName, password);

        Assertions.assertThat(user).usingRecursiveComparison().ignoringFields("userId").isEqualTo(expectedUser); //Can't compare generated user id
        Assert.assertNotNull(user.getUserId(), "The generated user id should not be null");
    }

    @Test(groups = {TestGroups.API, TestGroups.SANITY })
    public void testAuthorized(){
        String userName = RandomStringUtils.randomAlphabetic(7);
        String password = "someP123!";

        UserApi userApi = new UserApi();
        TokenApi tokenApi = new TokenApi();
        AuthorizedApi authorizedApi = new AuthorizedApi();

        User user = userApi.createUser(userName, password);
        Token token = tokenApi.createToken(user);
        authorizedApi.authorize(user, token);
    }

    @DataProvider
    public Object[][] userInfoProvider(){
        return new Object[][]{
                {RandomStringUtils.randomAlphabetic(7), "enterNB6!"},
                {RandomStringUtils.randomAlphabetic(8), "otherPass1!"}
        };
    }
}
