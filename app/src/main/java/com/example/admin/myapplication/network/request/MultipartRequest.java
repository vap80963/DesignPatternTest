package com.example.admin.myapplication.network.request;

import com.example.admin.myapplication.network.entities.HTTPMethod;
import com.example.admin.myapplication.network.response.Response;
import com.example.admin.myapplication.network.entities.MultipartEntity;
import com.example.admin.myapplication.utils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hasee on 11/22/2017.
 *
 * @author tin
 */

public class MultipartRequest extends Request<String> {

    MultipartEntity mMultipartEntity = new MultipartEntity();

    public MultipartRequest(String url, RequestListener<String> requestListener) {
        super(HTTPMethod.POST, url, requestListener);
    }

    public MultipartEntity getMultipartEntity() {
        return mMultipartEntity;
    }

    @Override
    public String getBodyContentType() {
        return mMultipartEntity.getContentType().getValue();
    }

    public void addHeader(String key, String value) {
        Map<String, String> header = new HashMap<>();
        header.put(key, value);
        setHeaders(header);
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            //将MultipartEntity中的参数写入到bos中
            mMultipartEntity.writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e("IOException writing to ByteArrayOutoutStream");
        }
        /**
         * 在这一步需要将参数数据全部转换为字节数组，如果文件过大那么就会产生OOM，
         * 所以SimpleNet和Volley不能上传大文件
         */
        return bos.toByteArray();
    }

    @Override
    public String parseResponse(Response response) {
        if (response != null && response.getRawData() != null)
            return new String(response.getRawData());
        return "";
    }
}
