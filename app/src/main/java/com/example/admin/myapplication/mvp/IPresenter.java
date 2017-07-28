package com.example.admin.myapplication.mvp;

/**
 * Created by admin on 2017/7/26.
 */

public interface IPresenter<T extends IView> {

    void attachView(T view);
    void start();
    void detachView();

}
