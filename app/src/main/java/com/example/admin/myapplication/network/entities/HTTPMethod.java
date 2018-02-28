package com.example.admin.myapplication.network.entities;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * HTTP请求方法枚举，这里我们只有GET,POST,PUT,DELETE四种
 */

public enum HTTPMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String mHttpMethod = "";

    HTTPMethod(String method) {
        this.mHttpMethod = method;
    }

    @Override
    public String toString() {
        return mHttpMethod.toString();
    }
}
