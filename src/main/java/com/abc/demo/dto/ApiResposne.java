package com.abc.demo.dto;

import java.io.Serializable;

public class ApiResposne implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String message;
    private Object data;
    
    public ApiResposne() {
    }

    public ApiResposne(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
