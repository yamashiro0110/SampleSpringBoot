package com.yamashiro0110.springbootactuator.endpoint;

/**
 * Created by yamashiro-r on 2017/08/13.
 */
public class RestartEndpointResponse {
    private String msg = "";

    public RestartEndpointResponse() {
    }

    public RestartEndpointResponse(final String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
