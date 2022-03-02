package com.assignment.productdemo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends Response{
    private int errorCode;

    public ErrorResponse(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorResponse() {}
}
