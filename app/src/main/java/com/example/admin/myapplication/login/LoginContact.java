package com.example.admin.myapplication.login;

import com.example.admin.myapplication.mvp.BaseUiView;

/**
 * Created by Tin on 2017/8/23.
 */

public interface LoginContact {

    interface View extends BaseUiView {

    }

    interface Presenter {
        void login();
    }

}
