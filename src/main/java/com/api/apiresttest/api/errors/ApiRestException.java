package com.api.apiresttest.api.errors;

import lombok.Data;

@Data
public class ApiRestException extends Exception{
    private String code;
    private String message;

    public ApiRestException(String code, String message, Exception exception) {
        super(code, exception);
        this.code = code;
        this.message = message;
    }
}
