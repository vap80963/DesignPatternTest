package com.example.module.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tin on 2017/8/14.
 */

public class RetrofitClient {

    private static final int DEFAULT_TIMEOUT = 1000 * 20;
    private BaseApiService apiService;
    private static OkHttpClient okHttpClient;
    public static String baseUrl = "https://github.com";
    private static Context mContext;
    private static RetrofitClient sNewInstance;
    private static final String TAG = "RetrofitClient";


    private static Retrofit retrofit;
    private Cache cache = null;
    private File httpCacheDirectory;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl);

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .addNetworkInterceptor(
                            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);


    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }

        return new RetrofitClient(context, url);
    }


    public static RetrofitClient getInstance(Context context, String url, Interceptor interceptor) {
        if (context != null) {
            mContext = context;
        }
        return new RetrofitClient(context, url, interceptor);
    }


    private RetrofitClient(Context context) {

        this(context, baseUrl, null);
    }

    private RetrofitClient(Context context, String url) {

        this(context, url, null);
    }


    private RetrofitClient(Context context, String url, Interceptor interceptor) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "tamic_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TAG, message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .socketFactory(SocketFactory.getDefault())
                .addInterceptor(loggingInterceptor)
//                .cookieJar(new NovateCookieManger(context))
                .cache(cache)
//                .addInterceptor(interceptor == null ? new BaseInterceptor(null) : interceptor)
       /*         .addInterceptor(new CaheInterceptor(context))
                .addNetworkInterceptor(new CaheInterceptor(context))*/
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();

    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }

        return retrofit.create(service);
    }

}
