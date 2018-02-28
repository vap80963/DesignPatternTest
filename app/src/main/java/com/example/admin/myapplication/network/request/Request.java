package com.example.admin.myapplication.network.request;

import android.support.annotation.NonNull;

import com.example.admin.myapplication.network.response.Response;
import com.example.admin.myapplication.network.entities.HTTPMethod;
import com.example.admin.myapplication.network.entities.Priority;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 网络请求的载体，
 * 注意GET和DELETE不能传递请求参数，因为其请求的性质所致，
 * 用户可以将参数构建到URL后传递进来并入Request类中
 */

public abstract class Request<T> implements Comparable<Request<T>> {
    //默认的编码方式
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    //请求序列号
    protected int mSerialNum = 0;
    //请求的优先级，默认为normal
    protected Priority mPriority = Priority.NORMAL;
    //是否取消该请求
    protected  boolean isCancel = false;
    //该请求是否需要缓存
    private boolean isShouldCache = true;
    //请求的方式
    HTTPMethod mHTTPMethod = HTTPMethod.GET;
    //请求的URL
    private String mUrl = "";
    //请求的Listener，用于回调请求结果
    protected RequestListener<T> mRequestListener;
    //请求的header
    private Map<String, String> mHeaders = new HashMap<>();
    //请求参数
    private Map<String, String> mBodyParams = new HashMap<>();

    /**
     * @param HTTPMethod 请求方式
     * @param url 请求的目标URL
     * @param requestListener 请求回调，将结果回调给用户
     * 抽象类的构造方法，子类必须也在构造方法中回调才能执行
     */
    public Request(HTTPMethod HTTPMethod, String url, RequestListener<T> requestListener) {
        mHTTPMethod = HTTPMethod;
        mUrl = url;
        mRequestListener = requestListener;
    }

    /**
     * 从原生的网络请求中解析结果，子类必须覆写
     * @param response 请求返回
     * @return 对应类的解析结果
     */
    public abstract T parseResponse(Response response);

    /**
     * 处理Response，该方法需要运行在UI线程
     * @param response 请求返回
     */
    public final void deliveryresponse(Response response) {
        //解析得到的请求结果
        T result  = parseResponse(response);
        if (mRequestListener != null) {
            int statusCode = response != null ? response.getStatusCode() : -1;
            String msg = response != null ? response.getMessage() : "Unknown Error";
            mRequestListener.onComplete(statusCode, result, msg);
        }
    }
    
    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    //返回POST或PUT请求时的Body参数字节数组
    public byte[] getBody() {
        //TODO getParams() 这里的参数还没有处理
        Map<String, String> params = getParams();
        if (params != null && params.size() > 0)
            return encodeParameters(params, getParamsEncoding());
        return null;
    }

    //TODO 不是很懂，明明是POST和PUT的参数，为什么要拼接到URL后面
    //将参数转换为URL编码的参数串，格式为key1=value1&key2=value2
    private byte[] encodeParameters(Map<String, String> params, String encoding) {
        StringBuilder encodeParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodeParams.append(URLEncoder.encode(entry.getKey(), encoding));
                encodeParams.append('=');
                encodeParams.append(URLEncoder.encode(entry.getValue(), encoding));
                encodeParams.append('&');
            }
            return encodeParams.toString().getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //用于请求的排序处理，根据优先级和加入到队列的序号进行排序
    @Override
    public int compareTo(@NonNull Request<T> another) {
        Priority myPriority = this.getPriority();
        Priority anotherPriority = another.getPriority();
        //如果优先级相等，那么按照添加到队列的序号顺序来执行请求
        return myPriority.equals(anotherPriority) ? this.getSerialNumber() - another.getSerialNumber()
                : mPriority.ordinal() - anotherPriority.ordinal();
    }

    protected Priority getPriority() {
        return mPriority;
    }

    public int getSerialNumber() {
        return mSerialNum;
    }

    public void setSerialNum(int serialNum) {
        mSerialNum = serialNum;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setShouldCache(boolean shouldCache) {
        isShouldCache = shouldCache;
    }

    public void setHTTPMethod(HTTPMethod HTTPMethod) {
        mHTTPMethod = HTTPMethod;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Map<String,String> getParams() {
        return mBodyParams;
    }

    public String getUrl() {
        return mUrl;
    }

    public boolean getShouldCache() {
        return isShouldCache;
    }

    public Map<String,String> getHeaders() {
        return mHeaders;
    }

    public void setHeaders(Map<String, String> headers) {
        mHeaders = headers;
    }

    public void setBodyParams(Map<String, String> bodyParams) {
        mBodyParams = bodyParams;
    }

    public HTTPMethod getHttpMethod() {
        return mHTTPMethod;
    }

    /**
     * 网络请求Listener，会被执行在UI线程
     * @param <T> 请求的response类型
     */
    public interface RequestListener<T> {
        //请求完成的回调
        void onComplete(int statusCode, T result, String msg);
    }
}
