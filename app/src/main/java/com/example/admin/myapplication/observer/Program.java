package com.example.admin.myapplication.observer;

import java.util.Observable;

/**
 * Created by admin on 2017/7/28.
 */

public class Program extends Observable {

    public void postNewsToObservers(String content){
        //标识状态或者内容已经改变
        setChanged();
        //向所有订阅者发出通知
        notifyObservers(content);
    }

}
