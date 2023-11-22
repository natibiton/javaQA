package org.example.sites.demoqa.modules;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Token {
    @Getter
    private String token;
    @Getter
    private String expires;

    public Token(String token, String expires){
        this.token = token;
        this.expires = expires;
    }
}
