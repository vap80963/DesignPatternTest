package com.example.admin.myapplication.customview.countdown;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.CountDownTimer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CountDownActivity extends AppCompatActivity {

    @Bind(R.id.count_down)
    CountDownView mCountDownView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.constraintLayout)
    ConstraintLayout mConstraintLayout;
    CountDownTimer mCountDownTimer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        ButterKnife.bind(this);

        mToolbar.setTitle("嘿嘿嘿");
        setSupportActionBar(mToolbar);

        mConstraintLayout.measure(0, 0);

        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mConstraintLayout.getLayoutParams();
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) mConstraintLayout.getLayoutParams();

        float height = lp.height;
        float topMargin = mlp.topMargin;
        height = height - topMargin;
        float radius = height * 8 / 20;
        float roundY = height / 2;
        mCountDownView.setRadius(radius);
        mCountDownView.setRoundY(roundY);
        if (mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(30 * 1000, 20) {

                @Override
                public void onTick(long millisUntilFinished) {
                    mCountDownView.setProgress(millisUntilFinished / 1000f);
                }

                @Override
                public void onFinish() {
                    mCountDownView.setProgress(0);
                }
            }.start();
        }

    }
}
