package com.ing.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseJson {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;


    public ErrorResponseJson(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ErrorResponseJson setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponseJson setMessage(String message) {
        this.message = message;
        return this;
    }
}
