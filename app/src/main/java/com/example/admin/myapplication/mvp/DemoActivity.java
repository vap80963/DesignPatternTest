package com.example.admin.myapplication.mvp;

import android.os.Bundle;

/**
 * Created by admin on 2017/7/26.
 */

public class DemoActivity extends IViewActivity<IPresenter> implements IView {

    @Override
    protected IPresenter onLoadPresenter() {
        return new DemoPresenter();
    }

    @Override
    protected void initDataAndView() {

    }

    @Override
    protected void initViews(Bundle onSaveInstanceState) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutView() {
        return 0;
    }
}
