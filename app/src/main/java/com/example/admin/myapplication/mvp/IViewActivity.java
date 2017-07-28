package com.example.admin.myapplication.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2017/7/26.
 */

public abstract class IViewActivity<P extends IPresenter> extends BaseAvtivity implements IView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //从子类中回调得到presenter
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);
        initDataAndView();
        if (getPresenter() != null){
            getPresenter().start();
        }
    }

    public P getPresenter(){
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null){
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract P onLoadPresenter();
    protected abstract void initDataAndView();
    protected abstract void initViews(Bundle onSaveInstanceState);

}
