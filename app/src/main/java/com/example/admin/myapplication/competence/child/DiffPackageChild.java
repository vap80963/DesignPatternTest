package com.example.admin.myapplication.competence.child;

import com.example.admin.myapplication.competence.parent.Parent;

/**
 * Created by Tin on 2017/9/30.
 */

class DiffPackageChild extends Parent {

    @Override
    protected void Parent2() {
        super.Parent2();
    }

    //final 方法就不会被子类重写
    public final void finalDiffChild() {

    }

    protected void DiffChild() {

    }

    protected class protectedClass {

    }

    class defaultClass {

    }

    private class privateClass {

    }
}
