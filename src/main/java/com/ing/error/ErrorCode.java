package com.ing.error;

public enum ErrorCode {

    PRODUCT_NOT_FOUND(1, "Product with id {0} does not exist"),
    INVALID_CATEGORY(2, "Invalid product category: {0}"),
    UNKNOWN_ERROR(999, "An unknown error occurred");


    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
