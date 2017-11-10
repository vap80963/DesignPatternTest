package com.example.admin.myapplication.customview.success;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;
import com.example.admin.myapplication.utils.ScreenUtil;

/**
 * Created by Tin on 2017/9/27.
 * 使用了drawPath，如果要使用SuccessView需要设置不使用硬件加速或者在所要获取的path上执行mPathRight.rLineTo(0, 0);
 * 关闭硬件加速的方法：
 * android:hardwareAccelerated="false"
 * 或者 view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
 * 或者 getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
 */

public class SuccessView extends View implements ValueAnimator.AnimatorUpdateListener {

    private Paint mPaint;
    private Path mPathCircle;
    private Path mPathRight;
    private Path mPathRightDst;
    private Path mPathCircleDst;
    /**
     * Path管理
     */
    private PathMeasure mPathMeasure;

    private Context mContext;

    private int main_color = Color.parseColor("#7BD24F");
    private float radius = 0;
    private float strokeWidth = 0;
    private float roundX, roundY = 0;
    private float start_angel = 90;
    private boolean rotate_deasil = true;
    private volatile boolean repeating = false;

    /**
     * 当前绘制进度占总Path长度百分比
     */
    private float mCirclePercent;
    private float mRightPercent;

    private ValueAnimator mCircleAnimator;
    private ValueAnimator mRightAnimator;

    private Paint.Style paintStyle = Paint.Style.STROKE;

    private float parentW, parentH;
    private float offsetW, offsetH = 0;
//    private float left, top, right, bottom;  //计时器的四个顶点坐标

    private float originX, originY;  //路径的起点位置坐标
    private float nextX, nextY;  //路径的下一点坐标
    private float endX, endY;

    private static final int RESULT_CICLE = 0;
    private static final int RESULT_RIGHT = 1;
    private int result = RESULT_CICLE;

    public SuccessView(Context context) {
        this(context, null);
    }

    public SuccessView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuccessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (context == null) {
            return;
        }
        mContext = context;

        parentW = ScreenUtil.getScreenWidth(mContext);
        parentH = ScreenUtil.getScreenHeight(mContext);
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parentW = parent.getWidth();
            parentH = parent.getHeight();
            offsetW = parent.getLeft();
            offsetH = parent.getTop();
        }
        radius = parentW / 4f;
        roundX = parentW / 2f + offsetW;
        roundY = parentH / 6f + offsetH;
        strokeWidth = parentW / 20f;

        if (attrs != null)
            parseAttributeset(context.obtainStyledAttributes(attrs, R.styleable.SuccessView));

        mPaint = new Paint();
        mPaint.setColor(main_color);
        mPaint.setStyle(paintStyle);
        mPaint.setAntiAlias(true);

        mPathCircle = new Path();
        mPathRight = new Path();
        mPathCircleDst = new Path();
        mPathRightDst = new Path();

        mPathMeasure = new PathMeasure();

        mCircleAnimator = ValueAnimator.ofFloat(0, 1);
        mCircleAnimator.setDuration(1000);
        mCircleAnimator.addUpdateListener(this);
        mCircleAnimator.start();

        mRightAnimator = ValueAnimator.ofFloat(0, 1);
        mRightAnimator.setDuration(300);
        mRightAnimator.addUpdateListener(this);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float toolbarHeight = 0;
/*        left = roundX - radius;  top = roundY - radius - toolbarHeight;
        right = roundX + radius;  bottom = roundY + radius - toolbarHeight;
        roundY -= toolbarHeight;*/

        originX = parentW * 5 / 12;  originY = parentH * 5 / 32;
        nextX = parentW * 35 / 72;  nextY = parentH * 53 / 256;
        endX = parentW * 5 / 8;  endY = parentH * 9 / 64;
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parentW = parent.getWidth();
            parentH = parent.getHeight();
            offsetW = parent.getLeft();
            offsetH = parent.getTop();
        }
        LogUtils.e("onMeasure parentW = " + parentW + " parentH = " + parentH);
        LogUtils.e("onMeasure offsetW = " + offsetW + " offsetH = " + offsetH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parentW = parent.getWidth();
            parentH = parent.getHeight();
            offsetW = parent.getLeft();
            offsetH = parent.getTop();
        }
        LogUtils.e("onDraw parentW = " + parentW + " parentH = " + parentH);
        LogUtils.e("onDraw offsetW = " + offsetW + " offsetH = " + offsetH);

        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //设置扇形的四个顶点位置
/*        RectF rectF = new RectF(left, top, right, bottom);
        //绘制一个空心扇形
        canvas.drawArc(rectF, -90, progress * 3.6f, false, mPaint);*/

        //画一个圆
        mPathCircle.addCircle(roundX, roundY, radius, Path.Direction.CW);
        mPathMeasure.setPath(mPathCircle, true);
        int start = 360;
        //获取到指定范围内的一段轮廓，存入到dst参数中
        mPathMeasure.getSegment(start, mCirclePercent * (mPathMeasure.getLength() + start), mPathCircleDst, true);
        canvas.drawPath(mPathCircleDst, mPaint);

        if (result == RESULT_RIGHT) {
            mPaint.setStrokeJoin(Paint.Join.ROUND);  //设置拐角为圆角

            mPathRight.moveTo(originX, originY);
            mPathRight.lineTo(nextX, nextY);
            mPathRight.lineTo(endX, endY);
            mPathMeasure.nextContour();  //移动到下一个轮廓
            mPathMeasure.setPath(mPathRight, false);
            //避免KITKAT及以下机型mPathMeasure.getLength()无效
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT)
                mPathRight.rLineTo(0, 0);
            mPathMeasure.getSegment(0, mRightPercent * mPathMeasure.getLength(), mPathRightDst, true);
            canvas.drawPath(mPathRightDst, mPaint);
        }

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (animation.equals(mCircleAnimator)) {
            mCirclePercent = (float) animation.getAnimatedValue();
            invalidate();
            if (mCirclePercent == 1) {
                result = RESULT_RIGHT;
                mRightAnimator.start();
            }
        } else if (animation.equals(mRightAnimator)) {
            if (result == RESULT_RIGHT) {
                mRightPercent = (float) animation.getAnimatedValue();
                invalidate();
            }
        }
    }

    private void parseAttributeset(TypedArray a) {
        main_color = a.getColor(R.styleable.SuccessView_main_color, main_color);
        radius = a.getDimension(R.styleable.SuccessView_radius_size, radius);
        strokeWidth = a.getDimension(R.styleable.SuccessView_stroke_size, strokeWidth);
        roundX = a.getDimension(R.styleable.SuccessView_x_axis, roundX);
        roundY = a.getDimension(R.styleable.SuccessView_y_axis, roundY);
        start_angel = a.getFloat(R.styleable.SuccessView_start_angel, start_angel);
        rotate_deasil = a.getBoolean(R.styleable.SuccessView_rotate_deasil, rotate_deasil);
        repeating = a.getBoolean(R.styleable.SuccessView_repeating, repeating);
        a.recycle();
    }

/*    public void setRectF(float left, float top, float right, float bottom) {
        this.left = left;   this.top = top;
        this.right = right;  this.bottom = bottom;
        invalidate();
    }*/

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    public float getRadius() {
        return radius;
    }

    public void setRoundX(float roundX) {
        this.roundX = roundX;
        invalidate();
    }

    public float getRoundX() {
        return roundX;
    }

    public void setRoundY(float roundY) {
        this.roundY = roundY;
        invalidate();
    }

    public float getRoundY() {
        return roundY;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public float getOriginX() {
        return originX;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public float getOriginY() {
        return originY;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public float getNextX() {
        return nextX;
    }

    public void setNextX(float nextX) {
        this.nextX = nextX;
    }

    public float getNextY() {
        return nextY;
    }

    public void setNextY(float nextY) {
        this.nextY = nextY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }
}
