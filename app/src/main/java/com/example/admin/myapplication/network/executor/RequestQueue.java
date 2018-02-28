package com.example.admin.myapplication.network.executor;

import com.example.admin.myapplication.network.httpstack.HttpStack;
import com.example.admin.myapplication.network.httpstack.HttpStatckFactory;
import com.example.admin.myapplication.network.request.Request;
import com.example.admin.myapplication.utils.LogUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 请求队列，使用优先队列，使得请求可以按照优先级进行处理
 */

public final class RequestQueue {
    //线程安全的请求队列
    private BlockingQueue<Request<?>> mRequestBlockingQueue = new PriorityBlockingQueue<>();
    //请求的序列化生成器
    private AtomicInteger mSerialNumGenerator = new AtomicInteger(0);
    //默认的核心数：CPU数+1
    public static int DEFUALT_CORE_NUMS = Runtime.getRuntime().availableProcessors() + 1;
    //分发线程数
    private int mDispatcherNums = DEFUALT_CORE_NUMS;
    //执行网络请求的线程，这里最好替换为线程池
    private NetworkExecutor[] mDispatchers = null;
    //HTTP请求的真正执行者
    private HttpStack mHttpStack;

    protected RequestQueue(int coreNums, HttpStack httpStack) {
        mDispatcherNums = coreNums;
        mHttpStack = httpStack != null ? httpStack : HttpStatckFactory.createHttpStack();
    }

    //启动网络请求线程
    private final void startNetworkExecutors() {
        mDispatchers = new NetworkExecutor[mDispatcherNums];
        for (int i = 0; i < mDispatcherNums; i++) {
            mDispatchers[i] = new NetworkExecutor(mRequestBlockingQueue, mHttpStack);
            mDispatchers[i].start();
        }
    }

    public void start() {
        stop();
        startNetworkExecutors();
    }

    //停止NetworkExecutor中的所有线程执行
    private void stop() {
        if (mDispatchers != null && mDispatchers.length > 0) {
            for (int i = 0; i < mDispatchers.length; i++) {
                mDispatchers[i].quit();
            }
        }
    }

    //添加请求到请求队列中
    public void addRequest(Request<?> request) {
        if (!mRequestBlockingQueue.contains(request)) {
            //设置请求对应的序列号
            request.setSerialNum(this.generatorNumber());
            //加入队列
            mRequestBlockingQueue.add(request);
            //启动请求线程
            start();
        } else {
            LogUtils.d("##### 请求队列中已经含有该请求");
        }
    }

    //使用原子类型数据生成每个请求的序列号
    private int generatorNumber() {
        return mSerialNumGenerator.incrementAndGet();
    }

}
