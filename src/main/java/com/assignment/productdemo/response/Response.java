package com.assignment.productdemo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private String message;

    public Response(String message) {
        this.message = message;
    }

    public Response() {
        this.message = "Response message not set";
    }

}
