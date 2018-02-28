package com.example.admin.myapplication.network.request;

import com.example.admin.myapplication.network.entities.HTTPMethod;
import com.example.admin.myapplication.network.response.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 返回的数据类型为Json的请求，Json对应的对象类型为JSONObject
 */

public class JsonRequest extends Request<JSONObject> {

    public JsonRequest(HTTPMethod HTTPMethod, String url, RequestListener<JSONObject> requestListener) {
        super(HTTPMethod, url, requestListener);
    }

    //将response的结果转换为JSonObject
    @Override
    public JSONObject parseResponse(Response response) {
        String jsonString = new String(response.getRawData());
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
