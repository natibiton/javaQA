package org.example.sites.expandtesting.modules;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class User {
    private String name;
    private String email;
    private String password;
    private String id;
    /**
     * The Login token of the user
     */
    private String token;
    private String phone;
    private String company;

    public User(){
        generateRandomUser();
    }

    private void generateRandomUser(){
        this.name = RandomStringUtils.randomAlphabetic(7);
        this.password = RandomStringUtils.randomAlphanumeric(6);
        this.email = name.toLowerCase() + "@gmail.com";
    }

}
