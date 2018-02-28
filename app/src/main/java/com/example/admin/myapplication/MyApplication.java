package com.example.admin.myapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by hasee on 2/10/2018.
 *
 * @author tin
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
