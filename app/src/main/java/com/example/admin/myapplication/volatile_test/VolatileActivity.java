package com.example.admin.myapplication.volatile_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Tin on 2017/11/2.
 */

public class VolatileActivity extends AppCompatActivity {

    boolean isStop = false;
    int increase = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startThread();
    }

    public void startThread() {
        //Thread 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop) {
                    doSomethings();
                }
            }
        }).start();

        //Thread 2
        new Thread(new Runnable() {
            @Override
            public void run() {
                isStop = true;
            }
        }).start();
    }

    public synchronized void increaseNum() {
        increase++;
    }

    public void startThreadSyn() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increaseNum();
                    }
                }
            }).start();
        }

        while(Thread.activeCount() > 1) {
            Thread.yield();
        }
        LogUtils.d("result = " + increase);
    }

    ReentrantLock mLock = new ReentrantLock();

    public void startThreadLock() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        mLock.lock();
                        try {
                            increase++;
                        } finally {
                            mLock.unlock();
                        }
                    }
                }
            }).start();
        }

        while(Thread.activeCount() > 1) {
            Thread.yield();
        }
        LogUtils.d("result = " + increase);
    }

    AtomicInteger mAtomicInteger = new AtomicInteger();

    public void startThreadAtomic() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        mAtomicInteger.getAndIncrement();
                    }
                }
            }).start();
        }

        while(Thread.activeCount() > 1) {
            Thread.yield();
        }
        LogUtils.d("result = " + increase);
    }

    private void doSomethings() {
        LogUtils.d("I have do Some things");
    }
}
