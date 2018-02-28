package com.example.admin.myapplication.network.executor;

import android.util.LruCache;

import com.example.admin.myapplication.network.response.Response;
import com.example.admin.myapplication.network.response.ResponseDelivery;
import com.example.admin.myapplication.network.httpstack.HttpStack;
import com.example.admin.myapplication.network.request.Request;
import com.example.admin.myapplication.utils.LogUtils;

import java.util.concurrent.BlockingQueue;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 网络请求Executor，继承自Thread，从网络请求队列中循环读取请求并且执行
 */

public class NetworkExecutor extends Thread {
    //网络请求队列
    private BlockingQueue<Request<?>> mRequestBlockingQueue;
    //网络请求栈
    private HttpStack mHttpStack;
    //结果分发器，将结果投递到主线程
    private static ResponseDelivery mResponseDelivery = new ResponseDelivery();
    //请求缓存
    private static LruCache<String, Response> mReqCache = new LruCache<>(1000);
    //是否停止
    private boolean isStop = false;

    public NetworkExecutor(BlockingQueue<Request<?>> queue, HttpStack stack) {
        mRequestBlockingQueue = queue;
        mHttpStack = stack;
    }

    @Override
    public void run() {
        try {
            while (!isStop) {
                final Request<?> request = mRequestBlockingQueue.take();
                if (request.isCancel()) {
                    LogUtils.d("###"+ request.getSerialNumber() + " 取消执行了");
                    continue;
                }
                Response response;
                if (isUseCache(request)) {
                    //从缓存中取
                    response = mReqCache.get(request.getUrl());
                } else {
                    //从网络上读取数据
                    response = mHttpStack.performRequest(request);
                    //如果该请求需要缓存，那么请求成功后则缓存到responseCache中
                    if (request.getShouldCache() && isSuccess(response))
                        mReqCache.put(request.getUrl(), response);
                }
                //分发请求结果
                mResponseDelivery.deliveryResponse(request, response);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtils.d("### 分发器退出");
        }
    }

    private boolean isSuccess(Response response) {
        return response != null && response.getStatusCode() == 200;
    }

    private boolean isUseCache(Request<?> request) {
        return request.getShouldCache() && mReqCache.get(request.getUrl()) != null;
    }

    public void quit() {
        isStop = true;
        interrupt();
    }
}
