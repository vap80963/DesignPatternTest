package com.example.admin.myapplication.customview.second;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tin on 2017/9/6.
 * 绘制自定义图形,当你要绘制的图形比较特殊
 */

public class SecondView extends View {

    Paint mPaint;
    Path mPath;

    public SecondView(Context context) {
        super(context);
        init(context, null);
    }

    public SecondView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SecondView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mPaint = new Paint();
        mPath = new Path();
        //用Path 画一个心形，这里是配置数据
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPath.addArc(200, 200, 400, 400, -225, 225);
            mPath.arcTo(400, 200, 600, 400, -180, 225, false);
        }
        mPath.lineTo(400, 542);*/

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        //画一条线，从（0,0）到（100,100）
/*
        mPath.lineTo(100, 100);
*/

        //从前面画出的线终点出发，（100,100,）到（300,200),相当于画出一条折线
/*      mPath.lineTo(100, 100);
        mPath.rLineTo(200, 100); */

        //画一个弧形，与之前画的线会多出一条线相连，如果forceMoveTo设置为true，则没有这条线，相当于mPath.addArc()
/*      mPath.lineTo(100, 100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPath.arcTo(100, 100, 300, 300, -90, 90, false);
        }*/
/*
        mPath.arcTo(100, 100, 300, 300, -90, 90, true);
*/

        //画一个弧形
/*        mPath.lineTo(100, 100)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPath.addArc(100, 100, 300, 300, -90, 90);
        }*/
        //如果先moveTo()，就会从move的结果出开始画线，如果先lineTo()再moveTo()，则会保留lineTo()的结果
/*        mPaint.setStyle(Paint.Style.FILL);
        mPath.moveTo(100, 100);
        mPath.lineTo(300, 100);
        mPath.lineTo(200, 200);
        *//**
         * 会使得前面未封闭的图形直接连线形成封闭图形，如前面形成的三角形的两条线，这里加上close()就会连上剩余一条线形成三角形
         * close()和lineTo()是等价的
         * 并不是所有的未封闭图形都需要close()，如果使用填充样式，会自动封闭填充
         **//*
        mPath.close();*/

        mPaint.setStyle(Paint.Style.FILL);
        //设置图形交叉的填充方式
        mPath.setFillType(Path.FillType.EVEN_ODD);  //这种设置交叉部分不填充
        mPath.setFillType(Path.FillType.INVERSE_EVEN_ODD); //上面这种的反色形式
        mPath.setFillType(Path.FillType.WINDING);  //这种设置如果画线的方向相同交叉部分填充，不相同方向则不填充，是默认处理方式
        mPath.setFillType(Path.FillType.INVERSE_WINDING);  //上面这种的反色形式
        //画出两个圆
        mPath.addCircle(100, 100, 50, Path.Direction.CW);
        mPath.addCircle(170, 100, 50, Path.Direction.CW);

        mPath.setFillType(Path.FillType.WINDING);
        mPath.addCircle(300, 300, 80, Path.Direction.CCW); //CCW 和 CW 代表了不同的画线方向，影响WINDING的填充结果
        mPath.addCircle(300, 300, 40, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //用Path 画一个心形
        canvas.drawPath(mPath, mPaint);
    }
}
