package com.example.admin.myapplication.customview.success;

import android.animation.TypeEvaluator;

/**
 * Created by Tin on 2017/9/28.
 */

public class LineEvaluator implements TypeEvaluator {

    float next;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        return null;
    }

    public float getNext() {
        return next;
    }

    public void setNext(float next) {
        this.next = next;
    }
}
