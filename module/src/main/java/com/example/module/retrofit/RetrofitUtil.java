package com.example.module.retrofit;


import com.example.module.util.ContextFinder;

/**
 * Created by Tin on 2017/8/14.
 */

public class RetrofitUtil {

    static String url = "https://github.com";


    public static BaseApiService getApiService() {
        return RetrofitClient.getInstance(ContextFinder.getApplication(),url).create(BaseApiService.class);
    }
}
