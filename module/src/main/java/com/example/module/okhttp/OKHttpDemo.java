package com.example.module.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.module.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tin on 2017/9/15.
 */

public class OKHttpDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        startRequest();
    }

    public void startRequest(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        okHttpClient
                .newCall(request)  //本质是创建一个ReCall对象实例
                .enqueue(new Callback() {  //这里将ReCall实例加入请求任务队列中，等待合适的时间执行
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("OkHttp", "Call Failed:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("OkHttp", "Call succeeded:" + response.message());
                    }
                });

    }

}
