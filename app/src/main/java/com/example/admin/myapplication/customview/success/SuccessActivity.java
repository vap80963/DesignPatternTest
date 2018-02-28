package com.example.admin.myapplication.customview.success;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tin on 2017/9/27.
 */

public class SuccessActivity extends AppCompatActivity {

    @BindView(R.id.successView)
    SuccessView mSuccessView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ButterKnife.bind(this);

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mSuccessView, "progress", 0, 100);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

    }
}
