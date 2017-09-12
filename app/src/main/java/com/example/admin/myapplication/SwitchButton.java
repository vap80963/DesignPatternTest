package com.example.admin.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Tin on 2017/8/24.
 */

public class SwitchButton extends View {

    private static final int DEFAULT_WIDTH = dp2pxInt(58);
    private static final int DEFAULT_HEIGHT = dp2pxInt(36);

    /**
     * 动画状态：
     * 1.静止
     * 2.进入拖动
     * 3.处于拖动
     * 4.拖动-复位
     * 5.拖动-切换
     * 6.点击切换
     * **/
    private final int ANIMATE_STATE_NONE = 0;
    private final int ANIMATE_STATE_PENDING_DRAG = 1;
    private final int ANIMATE_STATE_DRAGING = 2;
    private final int ANIMATE_STATE_PENDING_RESET = 3;
    private final int ANIMATE_STATE_PENDING_SETTLE = 4;
    private final int ANIMATE_STATE_SWITCH = 5;

    public SwitchButton(Context context) {
        super(context);
        init(context, null);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){

    }

    @Override
    public void setPadding(@Px int left, @Px int top, @Px int right, @Px int bottom) {
        super.setPadding(0, 0, 0, 0);
    }

    private static float dp2px(float dp){
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    private static int dp2pxInt(float dp){
        return (int) dp2px(dp);
    }
}
