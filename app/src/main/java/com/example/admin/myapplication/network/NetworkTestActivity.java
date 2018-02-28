package com.example.admin.myapplication.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.network.entities.HTTPMethod;
import com.example.admin.myapplication.network.entities.MultipartEntity;
import com.example.admin.myapplication.network.executor.RequestQueue;
import com.example.admin.myapplication.network.request.MultipartRequest;
import com.example.admin.myapplication.network.request.Request;
import com.example.admin.myapplication.network.request.StringRequest;
import com.example.admin.myapplication.utils.LogUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * @function
 */

public class NetworkTestActivity extends AppCompatActivity {
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mRequestQueue = new RequestQueue(10, null);
        sendStringRequest();

        try {
            sendMultiRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMultiRequest() throws IOException {
        MultipartRequest multipartRequest = new MultipartRequest("url", new Request.RequestListener<String>() {
            @Override
            public void onComplete(int statusCode, String result, String msg) {
                //TODO 处理事务
            }
        });
        multipartRequest.addHeader("header-name", "value");

        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addStringPart("type", "0");
        multipartEntity.addStringPart("location", "模拟的地理位置");

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        multipartEntity.addByteArrayPart("images", bitmapToBytes(bitmap));
        multipartEntity.addFilePart("images", new File(Environment.getExternalStorageDirectory() + "test.jpg"));

        mRequestQueue.addRequest(multipartRequest);
    }

    private byte[] bitmapToBytes(Bitmap bitmap) {

        return null;
    }

    public void sendStringRequest() {
        StringRequest request = new StringRequest(HTTPMethod.GET, "http://www.baidu.com", new Request.RequestListener() {
            @Override
            public void onComplete(int statusCode, Object result, String msg) {
                //处理结果
                LogUtils.d("result = " + result.toString());
            }
        });
        mRequestQueue.addRequest(request);
    }
}
