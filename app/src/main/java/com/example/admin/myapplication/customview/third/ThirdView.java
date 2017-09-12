package com.example.admin.myapplication.customview.third;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.admin.myapplication.R;

/**
 * Created by Tin on 2017/9/6.
 */

public class ThirdView extends View {

    Paint mPaint;
    Shader mShader;
    //将水平和竖直方向上都划分为20个格
    static final int WIDTH = 20;  static final int HEIGHT = 20;
    static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);  //记录该图片包含的21*21个点
    static final float[] ORIG = new float[COUNT * 2]; //扭曲前21*21个点的坐标
    static final float[] VERTS = new float[COUNT * 2]; //扭曲后21*21个点的坐标
    Bitmap mBitmap;
    float bHeight, bWidth;


    public ThirdView(Context context) {
        super(context);
        init(context, null);
    }

    public ThirdView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ThirdView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
        /**
         * 着色器，这里设置了线性渐变色
         *
         * x0,y0,x1,y1：渐变的两个端点的位置坐标
         * color0，color1：两个端点的颜色
         * tile：着色规则，有三个值可选：CLAMP,MIRROR和REPEAT。
         * CLAMP：会在端点之外延续端点的颜色
         * MIRROR：镜像模式
         * REPEAT：重复模式
         *
         * 设置了Shader,那么setColor()和setARGB()就不起作用了
         */
        mShader = new LinearGradient(100, 100, 250, 250, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);

        /**
         * 放射渐变色
         */
        mShader = new RadialGradient(350, 350, 75, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);

        /**
         * 梯度/扫描式着色器
         */
        mShader = new SweepGradient(350, 350, new int[]{Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Color.parseColor("#a196F3"), Color.parseColor("#3196F3")
                , Color.parseColor("#b196F3"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")}, null);

        mShader = new SweepGradient(300, 300, new int[]{Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
                , Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
                , Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
                , Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
                , Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
                , Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
                , Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
                , Color.parseColor("#FFFFFF"), Color.parseColor("#000000"), Color.parseColor("#FFFFFF"), Color.parseColor("#000000")
        }, null);

/*        mPaint.setShader(mShader);
        //无效设置
        mPaint.setColor(Color.parseColor("#FFFFFF"));*/

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_image);
        bWidth = mBitmap.getWidth();
        bHeight = mBitmap.getHeight();
        //初始化ORIG和VERTS数组
        int index = 0;
        for (int y = 0; y <= HEIGHT; y++) {
            float fY = bHeight * y / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fX = bWidth * x / WIDTH;
                ORIG[index * 2 + 0] = VERTS[index * 2 + 0] = fX;
                ORIG[index * 2 + 1] = VERTS[index * 2 + 1] = fY;
                index += 1;
            }
        }
        //设置背景色
        setBackgroundColor(Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(100, 100, 500, 500, mPaint);
//        canvas.drawCircle(350, 350, 75, mPaint);
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, VERTS, 0, null, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        warp(event.getX(), event.getY());
        return true;
    }

    /**
     * 工具方法，根据触摸事件的点击位置计算VERTS数组里各元素的值，用于重绘
     * @param x
     * @param y
     */
    private void warp(float x, float y) {
        for (int i = 0; i < COUNT * 2; i += 2) {
            float dx = x - ORIG[i + 0];
            float dy = y - ORIG[i + 1];
            float dd = dx * dx + dy * dy;
            //计算每个坐标点与当前点（x, y）之间的距离
            float d = (float) Math.sqrt(dd);
            //计算扭曲度，距离当前点（x, y）越远，扭曲度越小
            float pull = 80000 / (dd * d);
            //对VERTS数组重新赋值
            if (pull >= 1){
                VERTS[i + 0] = x;
                VERTS[i + 1] = y;
            } else {
                //控制各顶点向触摸事件发生点偏移
                VERTS[i + 0] = ORIG[i + 0] + dx * pull;
                VERTS[i + 1] = ORIG[i + 1] + dy * pull;
            }
        }
        //通知View组件重绘
        invalidate();
    }
}
