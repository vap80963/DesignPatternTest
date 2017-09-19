package com.example.admin.myapplication.searialize;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;

/**
 * Created by Tin on 2017/9/19.
 */

public class ParcelCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCheck();
    }

    private void startCheck() {

        BabyInfo babyInfo = new BabyInfo();
        babyInfo.setName("First");
        babyInfo.setId(100);
        Bundle bundle = new Bundle();
        bundle.putParcelable("baby_info", babyInfo);
        Intent intent = new Intent(ParcelCheckActivity.this, ParcelableSecondActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
