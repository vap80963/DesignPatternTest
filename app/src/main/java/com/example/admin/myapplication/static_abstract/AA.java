package com.example.admin.myapplication.static_abstract;

/**
 * Created by Tin on 2017/10/9.
 */

public class AA {

    A.B b = new A.B() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    };

    A.B b1 = new A.B() {
        @Override
        public void b2() {
            super.b2();
        }
    };

    A a = new A();
    A.C c = a.c;
}
