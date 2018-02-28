package com.example.admin.myapplication.network.request;

import com.example.admin.myapplication.network.entities.HTTPMethod;
import com.example.admin.myapplication.network.response.Response;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * @function
 */

public class StringRequest extends Request<String> {

    public StringRequest(HTTPMethod HTTPMethod, String url, RequestListener requestListener) {
        super(HTTPMethod, url, requestListener);
    }

    @Override
    public String parseResponse(Response response) {
        return new String(response.getRawData());
    }

}
