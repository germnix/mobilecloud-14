package com.oauth.domain;

/**
 * Created by german on 27/11/14.
 */
public class EchoInfo {
    private String message;

    public EchoInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
