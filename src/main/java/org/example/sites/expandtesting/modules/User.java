package org.example.sites.expandtesting.modules;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@EqualsAndHashCode
@ToString
public class User {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    /**
     * The Login token of the user
     */
    private String token;
    @Getter
    @Setter
    private String phone;
    @Getter
    @Setter
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
