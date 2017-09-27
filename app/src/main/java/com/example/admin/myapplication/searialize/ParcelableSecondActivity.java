package com.example.admin.myapplication.searialize;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.utils.LogUtils;
import com.example.admin.myapplication.R;

/**
 * Created by Tin on 2017/9/19.
 */

public class ParcelableSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        BabyInfo babyInfo = bundle.getParcelable("baby_info");
        LogUtils.e("babyInfo getName = " + babyInfo.getName());
        LogUtils.e("babyInfo getId = " + babyInfo.getId());

    }
}
