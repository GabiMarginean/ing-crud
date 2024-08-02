package com.ing.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequestJson {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public AuthRequestJson setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthRequestJson setPassword(String password) {
        this.password = password;
        return this;
    }
}
