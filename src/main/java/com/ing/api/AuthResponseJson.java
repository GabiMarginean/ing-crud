package com.ing.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseJson {

    @JsonProperty("jwt")
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public AuthResponseJson setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }
}
