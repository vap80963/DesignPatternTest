package com.example.admin.myapplication.network.httpstack;

import com.example.admin.myapplication.network.entities.HTTPMethod;
import com.example.admin.myapplication.network.request.Request;
import com.example.admin.myapplication.network.response.Response;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 使用HttpURLConnection执行网络请求的HTTPStack
 */
public class HttpURLConnectionStack implements HttpStack {

    @Override
    public Response performRequest(Request<?> request) {
        HttpURLConnection httpURLConnection = null;
        try {
            //构建HttpUrlConnection
            httpURLConnection = createUrlConnection(request.getUrl());
            setRequestHeader(httpURLConnection, request);
            setRequestParams(httpURLConnection, request);
            //构建请求结果response
            return fetchResponse(httpURLConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对网络请求的结果进行处理，将connection的返回值生成Response
     * @param connection
     * @return
     * @throws IOException
     */
    private Response fetchResponse(HttpURLConnection connection) throws IOException {
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        int responseCode = connection.getResponseCode();
        if (responseCode == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        //状态行数据
        StatusLine responseStatus = new BasicStatusLine(protocolVersion, responseCode,
                connection.getResponseMessage());
        //构建Response
        Response response = new Response(responseStatus);
        //设置Response数据
        response.setEntity(entityFromURLConnection(connection));
        //添加header到Response
        addHeaderToResponse(response, connection);
        return response;
    }

    //获得connection返回的头部，添加到Response中
    private void addHeaderToResponse(Response response, HttpURLConnection connection) {
        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
            if (header.getKey() != null) {
                Header h = new BasicHeader(header.getKey(), header.getValue().get(0));
                response.addHeader(h);
            }
        }
    }

    /**
     * 执行Http请求之获取到其数据流，即返回请求结果的流
     * @param connection
     * @return response数据
     */
    private HttpEntity entityFromURLConnection(HttpURLConnection connection) {
        BasicHttpEntity entity = new BasicHttpEntity();
        InputStream is = null;
        try {
            is = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            is = connection.getErrorStream();
        }
        entity.setContent(is);
        entity.setContentLength(connection.getContentLength());
        entity.setContentEncoding(connection.getContentEncoding());
        entity.setContentType(connection.getContentType());
        return entity;
    }

    //设置请求的参数
    private void setRequestParams(HttpURLConnection connection, Request<?> request) throws IOException {
        HTTPMethod method = request.getHttpMethod();
        connection.setRequestMethod(method.toString());
        byte[] body = request.getBody();
        if (body != null) {
            connection.setDoOutput(true);
            connection.addRequestProperty(Request.HEADER_CONTENT_TYPE, request.getBodyContentType());
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }

    //设置请求头部
    private void setRequestHeader(HttpURLConnection connection, Request<?> request) {
        Set<String> headerKeys = request.getHeaders().keySet();
        for (String headerName : headerKeys) {
            connection.addRequestProperty(headerName, request.getHeaders().get(headerName));
        }
    }

    //创建UrlConnection
    private HttpURLConnection createUrlConnection(String url) throws IOException {
        URL newUrl = new URL(url);
        URLConnection urlConnection = newUrl.openConnection();
        urlConnection.setConnectTimeout(HttpStatckFactory.HttpConfig.CONNECT_TIMEOUT);
        urlConnection.setReadTimeout(HttpStatckFactory.HttpConfig.READ_TIMEOUT);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setUseCaches(false);
        return (HttpURLConnection) urlConnection;
    }
}
