package com.example.admin.myapplication.competence.parent;

import com.example.admin.myapplication.utils.LogUtils;

/**
 * Created by Tin on 2017/9/30.
 */

public class PackageChild extends Parent {

    @Override
    void Parent() {
        super.Parent();
        LogUtils.e("I am child " + " parent = " + parent);
    }
}
