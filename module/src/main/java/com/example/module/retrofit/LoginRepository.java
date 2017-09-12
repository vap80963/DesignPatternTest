package com.example.module.retrofit;


/**
 * Created by Tin on 2017/8/14.
 */

public class LoginRepository {


    public void login(String token){

        RetrofitUtil.getApiService().getUserInfo("token").compose(RxSchedulers.io_main()).subscribe(new BaseSubscriber<UserInfo>() {
            @Override
            public void onNext(UserInfo info) {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }
        });

    }

}
