package com.example.admin.myapplication.observer;

import com.example.admin.myapplication.utils.LogUtils;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by admin on 2017/7/28.
 */

public class Coder implements Observer {

    public String mName;
    private CallBacker mCallbacker;

    public Coder(String name){
        this.mName = name;
    }

    public void setCallBacker(CallBacker callBacker){
        this.mCallbacker = callBacker;
    }

    @Override
    public void update(Observable o, Object arg) {
        LogUtils.d("Hi, " + mName + ", 更新内容为： " + arg);
        if (mCallbacker != null){
            mCallbacker.onCallBack("Hi, " + mName + ", 更新内容为： " + arg);
        }
    }

}
