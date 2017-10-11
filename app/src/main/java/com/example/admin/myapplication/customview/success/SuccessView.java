package com.example.admin.myapplication.customview.success;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.ScreenUtil;

/**
 * Created by Tin on 2017/9/27.
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

    private int main_color = 0x000000;

    /**
     * 当前绘制进度占总Path长度百分比
     */
    private float mCirclePercent;
    private float mRightPercent;

    private ValueAnimator mCircleAnimator;
    private ValueAnimator mRightAnimator;

    private float screenW, screenH;  //屏幕的宽和高
    private float left, top, right, bottom;  //计时器的四个顶点坐标
    private float radius;
    private float roundX, roundY;

    private float originX, originY;  //路径的起点位置坐标
    private float nextX, nextY;  //路径的下一点坐标
    private float endX, endY;

    private float strokeWidth;

    private static final int RESULT_CICLE = 0;
    private static final int RESULT_RIGHT = 1;
    private int result = RESULT_CICLE;

    public SuccessView(Context context) {
        super(context);
        init(context, null);
    }

    public SuccessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
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
        if (attrs != null) {
            parseAttributeset(context.obtainStyledAttributes(attrs, R.styleable.CountDownView));
        }

        screenW = ScreenUtil.getScreenWidth(mContext);
        screenH = ScreenUtil.getScreenHeight(mContext);
        radius = screenW / 4f;
        roundX = screenW / 2f;  roundY = screenH / 6f;

        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#7BD24F"));
        mPaint.setStyle(Paint.Style.STROKE);
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
        left = roundX - radius;  top = roundY - radius - toolbarHeight;
        right = roundX + radius;  bottom = roundY + radius - toolbarHeight;
        roundY -= toolbarHeight;

        originX = screenW * 5 / 12;  originY = screenH * 5 / 32;
        nextX = screenW * 35 / 72;  nextY = screenH * 53 / 256;
        endX = screenW * 5 / 8;  endY = screenH * 9 / 64;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        strokeWidth = screenW / 20f;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        //设置扇形的四个顶点位置
        RectF rectF = new RectF(left, top, right, bottom);
/*        //绘制一个空心扇形
        canvas.drawArc(rectF, -90, progress * 3.6f, false, mPaint);*/

        mPathCircle.addCircle(roundX, roundY, radius, Path.Direction.CW);
        mPathMeasure.setPath(mPathCircle, false);
        mPathMeasure.getSegment(0, mCirclePercent * mPathMeasure.getLength(), mPathCircleDst, true);
        canvas.drawPath(mPathCircleDst, mPaint);

        if (result == RESULT_RIGHT) {
            mPaint.setStrokeJoin(Paint.Join.ROUND);  //设置拐角为圆角

            mPathRight.moveTo(originX, originY);
            mPathRight.lineTo(nextX, nextY);
            mPathRight.lineTo(endX, endY);
            mPathMeasure.nextContour();
            mPathMeasure.setPath(mPathRight, false);
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
    }

    public void setRectF(float left, float top, float right, float bottom) {
        this.left = left;   this.top = top;
        this.right = right;  this.bottom = bottom;
        invalidate();
    }

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
