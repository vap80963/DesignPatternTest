package com.example.admin.myapplication.customview.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.admin.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {

    @Bind(R.id.first_custom_view)
    FirstCustomView mFirstCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.reset,R.id.scale_plus,R.id.scale_minus,R.id.slope_plus,R.id.slope_minus})
    public void OnClick(View v){
        switch (v.getId()) {
            case R.id.reset:
                mFirstCustomView.setMethod(0);
                break;
            case R.id.scale_plus:
                mFirstCustomView.setMethod(1);
                break;
            case R.id.scale_minus:
                mFirstCustomView.setMethod(2);
                break;
            case R.id.slope_plus:
                mFirstCustomView.setMethod(3);
                break;
            case R.id.slope_minus:
                mFirstCustomView.setMethod(4);
                break;
        }
    }
}
