package com.example.admin.myapplication.competence.parent;

import com.example.admin.myapplication.utils.LogUtils;

/**
 * Created by Tin on 2017/9/30.
 */

public class Parent {

    int parent = 0;

    void Parent() {
        LogUtils.e("I am parent");
    }

    protected void Parent2() {
        LogUtils.e("I am parent2");
    }

}
