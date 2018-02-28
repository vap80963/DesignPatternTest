package com.example.admin.myapplication.md5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;

import static com.example.admin.myapplication.md5.MD5andKL.JM;
import static com.example.admin.myapplication.md5.MD5andKL.KL;
import static com.example.admin.myapplication.md5.MD5andKL.MD5;

/**
 * Created by Tin on 2017/11/10.
 */

public class MD5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = new String("123123asdsad87");
//      4297f44b13955235245b2497399d7a93
        LogUtils.e("原始：" + s);
        LogUtils.e("MD5后：" + MD5andKL.MD5(s));
        LogUtils.e("MD5后再加密：" + MD5andKL.KL(MD5(s)));
        LogUtils.e("解密为MD5后的：" + JM(KL(MD5(s))));
        LogUtils.e("MD5后解密：" + JM(MD5andKL.MD5(s)));


        char a1 = 'a';
        char A1 = 'A';
    }
}
