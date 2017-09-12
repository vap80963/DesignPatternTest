package com.example.module.retrofit;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tin on 2017/8/14.
 */

public interface BaseApiService {

    @POST("/m/post")
    Observable<String> create(@Body String name);

    @GET("/m/get/token")
    Observable<UserInfo> getUserInfo(@Query("token") String token);

}
