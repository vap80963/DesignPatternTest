package com.example.admin.myapplication.network.httpstack;

import com.example.admin.myapplication.network.request.Request;
import com.example.admin.myapplication.network.response.Response;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 执行网络请求的接口
 */

public interface HttpStack {
    /**
     * 执行Http请求
     * @param request  待执行的请求
     * @return 返回response
     */
    Response performRequest(Request<?> request);
}
