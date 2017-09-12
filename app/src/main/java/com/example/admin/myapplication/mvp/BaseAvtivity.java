package com.example.admin.myapplication.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 2017/7/26.
 */

public abstract class BaseAvtivity extends AppCompatActivity implements BaseUiView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutView());
        initView();

    }

    protected abstract void initView();

    protected abstract int getLayoutView();


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
