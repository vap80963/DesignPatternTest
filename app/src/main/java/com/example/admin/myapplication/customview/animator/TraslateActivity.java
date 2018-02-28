package com.example.admin.myapplication.customview.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TraslateActivity extends AppCompatActivity implements ValueAnimator.AnimatorListener {

    @BindView(R.id.step1_img)
    ImageView imgStep;
    @BindView(R.id.step1_hand_img)
    ImageView imgStepHand;
    @Bind(R.id.img_robot)
    ImageView imgRobot;

    AnimatorSet animatorSet;
    ObjectAnimator objectAnimator;
    ObjectAnimator objectAnimator1;

    float translateX = -210;
    float translateY = 120;

    float X, Y;

    private static final int START_ANIMATION = 110;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == START_ANIMATION) {
                if (translateX != 0 && translateY != 0) {
                    translateX = 0;
                    translateY = 0;
                    imgRobot.setImageResource(R.drawable.img_pressed);
                } else {
                    translateX = X;
                    translateY = Y;
                    imgRobot.setImageResource(R.drawable.img_normal);
                }
                startAnimation();
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslate);
        ButterKnife.bind(this);

    }

    private void initData() {
        float stepTop = imgStep.getTop();
        float stepBottom = imgStep.getBottom();
        float stepLeft = imgStep.getLeft();
        float stepRight = imgStep.getRight();
        float stepX = (stepRight - stepLeft) * 55 / 107f + stepLeft;
        float stepY = (stepBottom - stepTop) * 25 / 105f + stepTop;

        float handLeft = imgStepHand.getLeft();
        float handRight = imgStepHand.getRight();
        float handBottom = imgStepHand.getBottom();
        float handX = (handRight - handLeft) * 13 / 70f + handLeft;
        float handY = handBottom;

        X = stepX - handX;
        Y = -(stepY - handY);
        translateX = X;
        translateY = Y;
        LogUtils.e("stepX = " + stepX + " stepY = " + stepY + " handX = " + handX + " handY = " + handY);
        LogUtils.e("x = " + X + " Y = " + Y + " translateX = " + translateY + " translateY = " + translateY);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initData();
        startAnimation();
    }

    private void startAnimation() {
        /*       ViewPropertyAnimator animator = imgStepHand.animate().translationX(-210).translationY(120).setDuration(1000)
               .setInterpolator(new AccelerateDecelerateInterpolator()).setStartDelay(200);*/
        objectAnimator = ObjectAnimator.ofFloat(imgStepHand, "translationX", translateX);
        objectAnimator1 = ObjectAnimator.ofFloat(imgStepHand, "translationY", translateY);

        objectAnimator1.setStartDelay(150);

        animatorSet = new AnimatorSet();
        animatorSet.setDuration(600);
        animatorSet.setStartDelay(200);
        animatorSet.play(objectAnimator).with(objectAnimator1);
        objectAnimator.addListener(this);
        objectAnimator1.addListener(this);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (animation.equals(objectAnimator)) {
            mHandler.sendEmptyMessageDelayed(START_ANIMATION, 3000);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {


    }
}
