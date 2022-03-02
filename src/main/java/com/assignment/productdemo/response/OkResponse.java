package com.assignment.productdemo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkResponse extends Response{
    private Object body;

    public OkResponse(String message, Object body) {
        super(message);
        this.body = body;
    }

    public OkResponse(Object body) {
        this.body = body;
    }
}
