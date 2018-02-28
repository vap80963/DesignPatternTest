package com.example.admin.myapplication.network.response;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.example.admin.myapplication.network.request.Request;

import java.util.concurrent.Executor;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 请求结果投递类，将请求结果投递给UI线程
 */

public class ResponseDelivery implements Executor {

    //关联主线程消息队列的handler
    Handler mResponseHandler = new Handler(Looper.getMainLooper());

    /**
     * 处理请求结果，将其执行在UI线程
     * @param request
     * @param response
     */
    public void deliveryResponse(final Request<?> request, final Response response) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                request.deliveryresponse(response);
            }
        };

        execute(runnable);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mResponseHandler.post(command);
    }
}
