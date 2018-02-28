package com.example.admin.myapplication.network.httpstack;

import android.os.Build;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 根据Android API版本选择HTTPClient或者HttpURLConnection
 */

public class HttpStatckFactory {

    private static final int GINGERBREAD_SDK_NUM = 9;

    //根据SDK版本号来创建不同的HTTP执行器
    public static HttpStack createHttpStack() {
        int runtimeSDKApi = Build.VERSION.SDK_INT;
        if (runtimeSDKApi >= GINGERBREAD_SDK_NUM) {
            return new HttpURLConnectionStack();
        }
//        return new HttpClientStack();
        return null;
    }

    public class HttpConfig {
        public static final int CONNECT_TIMEOUT = 10000;
        public static final int READ_TIMEOUT = 15000;

    }
}
