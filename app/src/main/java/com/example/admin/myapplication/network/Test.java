package com.example.admin.myapplication.network;

import android.text.TextUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 11/20/2017.
 *
 * @author tin
 * @function
 */

public class Test {

    private static final String ENCODE_TYPE = "UTF-8";

    private void sendRequest(String url) throws IOException {
        InputStream is = null;
        try {
            URL newUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) newUrl.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(1000);  //设置链接超时时间
            conn.setRequestMethod("POST");
            conn.setDoInput(true);  //接收输入流
            conn.setDoOutput(true);  //启动输出流，当需要传递参数时需要开启
            conn.setRequestProperty("Connection", "Keep-Alive");  //添加Header
            //添加请求参数，注意：如果是GET请求，参数要写在URL中
            List<NameValuePair> parmsList = new ArrayList<>();
            parmsList.add(new BasicNameValuePair("username", "tin"));
            parmsList.add(new BasicNameValuePair("pwd", "111111"));
            writeParms(conn.getOutputStream(), parmsList);

            conn.connect();
            is = conn.getInputStream();
            String result = convertStreamToString(is);
        } finally {
            if (is != null)
                is.close();
        }

    }

    private void writeParms(OutputStream stream, List<NameValuePair> list) throws IOException {
        StringBuffer parmStr = new StringBuffer();
        for (NameValuePair nameValuePair : list) {
            if (!TextUtils.isEmpty(parmStr))
                parmStr.append("&");
            parmStr.append(URLEncoder.encode(nameValuePair.getName(), ENCODE_TYPE));
            parmStr.append("=");
            parmStr.append(URLEncoder.encode(nameValuePair.getValue(), ENCODE_TYPE));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, ENCODE_TYPE));
        writer.write(parmStr.toString());
        writer.flush();
        writer.close();
    }

    private String convertStreamToString(InputStream is) {
        String reuslt = "";
        BufferedInputStream bis = new BufferedInputStream(is);


        return reuslt;
    }
}
