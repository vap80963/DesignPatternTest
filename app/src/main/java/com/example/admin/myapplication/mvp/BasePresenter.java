package com.example.admin.myapplication.mvp;

/**
 * Created by admin on 2017/7/26.
 */

public abstract class BasePresenter<T extends IView, M extends IModel> implements IPresenter<T> {

    protected T mView;
    protected M mModel;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public boolean isViewAttached(){
        return mView != null;
    }

    public T getView(){
        return mView;
    }

    public M getModel(){
        return mModel;
    }

}
