package com.example.admin.myapplication.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.customview.first.FirstActivity;
import com.example.admin.myapplication.customview.second.SecondActivity;
import com.example.admin.myapplication.customview.third.ThirdActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.first_activity, R.id.second_activity, R.id.third_activity})
    public void OnClick(View v){
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.first_activity:
                intent.setClass(CustomViewActivity.this, FirstActivity.class);
                break;
            case R.id.second_activity:
                intent.setClass(CustomViewActivity.this, SecondActivity.class);
                break;
            case R.id.third_activity:
                intent.setClass(CustomViewActivity.this, ThirdActivity.class);
        }
        startActivity(intent);
    }

}
