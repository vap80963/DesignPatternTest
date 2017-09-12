package com.example.admin.myapplication.customview.first;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.myapplication.R;

/**
 * Created by Tin on 2017/9/6.
 * 绘制某个给定的图形
 * 都是系统定义好的形状的一些简单变化
 */

public class FirstCustomView extends View {

    Paint paint;
    Paint textPaint;
    Bitmap mBitmap;
    Matrix mMatrix = new Matrix();
    float slope = 0.0f; //倾斜度
    float scale = 1.0f; //缩放比例
    int width,height;         //位图宽高
    int method = 0; //设置图像调整方案

    public FirstCustomView(Context context) {
        super(context);
        init(context, null);
    }

    public FirstCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FirstCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        paint = new Paint();
        //设置绘制模式
        paint.setStyle(Paint.Style.STROKE);
        //设置边框的宽度
        paint.setStrokeWidth(4);
        //设置画笔颜色
        paint.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
        //设置抗锯齿开关
        paint.setAntiAlias(true);
        //设置文字大小
        paint.setTextSize(30);

        textPaint = new Paint();
        //设置透明度
        textPaint.setAlpha(150);
        textPaint.setTextSize(50);
        //设置样式
        textPaint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        width = mBitmap.getWidth();
        height = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制一个圆
        canvas.drawCircle(200, 240, 100, paint);

        //绘制文本
        canvas.drawText("Hello World!", 120, 245, paint);
        canvas.drawText("Hello World!", 2, 5, 120 , 380, textPaint);

        //设置扇形的四个顶点位置
        RectF rectF = new RectF(250, 320, 480, 450);
        //绘制一个扇形弧线
        canvas.drawArc(rectF, 90, 270, true, paint);
        //绘制一个扇形
        paint.setStyle(Paint.Style.FILL);
        rectF.set(490, 460, 580, 550);
        canvas.drawArc(rectF, 90, 270, true, paint);

        /**
         * rx、ry：圆角值
         * 绘制一个四边形，API要求21 LOLLIPOP 以上
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRoundRect(250, 320, 480, 450, 50, 50, paint);
        }
        //绘制矩形
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(100, 500, 200, 600, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(230, 520, 330, 600, paint);

        //绘制一个圆点
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);  //设置这个点的形状
        canvas.drawPoint(400, 20, paint);
        //绘制一个矩形
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(400, 40, paint);
        //绘制多个点
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        canvas.drawPoints(points, 2, 8, paint); // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)

        //绘制椭圆
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawOval(300, 70, 380, 120, paint);
        }

        //绘制线条
        canvas.drawLine(350, 160, 450, 250, paint);
        //绘制多条线条
        float[] points1 = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
        canvas.drawLines(points1, paint);

        switch (method) {
            case 0:
                mMatrix.reset();
                scale = 1.0f;
                slope = 0.0f;
                break;
            case 1:
                if (scale < 2.0f){
                    scale += 0.1f;
                }
                mMatrix.setScale(scale, scale);
                break;
            case 2:
                if (scale > 0.5) {
                    scale -= 0.1;
                }
                mMatrix.setScale(scale, scale);
                break;
            case 3:
                slope += 0.1f;
                mMatrix.setSkew(slope, 0);
                break;
            case 4:
                slope -= 0.1f;
                mMatrix.setSkew(slope, 0);
                break;
        }
        /**
         * x、y ：绘制的起点，x > 0, y > 0
         * width、height : 所要绘制出的图像的宽高
         * java.lang.IllegalArgumentException: x + width must be <= bitmap.width()
         */
        Bitmap bitmap = Bitmap.createBitmap(mBitmap, 0, 30, width - 60, height -30, mMatrix, true);
        canvas.drawBitmap(bitmap, mMatrix, null);

        //绘制背景颜色  一般用于在绘制之前设置底色，或者在绘制之后为界面设置半透明蒙版
//        canvas.drawARGB(100, 100, 100, 100);
        //绘制背景颜色
//        canvas.drawColor(getResources().getColor(R.color.colorPrimaryDark));
        //可以用来绘制一条线、垂直的
//        canvas.drawPaint(paint);

    }

    public void setMethod(int change){
        method = change;
        postInvalidate();
    }
}
