package com.example.admin.myapplication.competence;

import com.example.admin.myapplication.competence.child.DiffChildChild;

/**
 * Created by Tin on 2017/9/30.
 */

public class Child extends DiffChildChild {

    @Override
    protected void Parent2() {
        super.Parent2();
    }

    @Override
    public void DiffChild() {
        super.DiffChild();
        final int example = 0;
        method(new AbstractClass() {
            @Override
            void onResult() {
                if (example > 0) {

                }
            }
        });
    }

    public void method(AbstractClass abstractClass) {

    }
}
