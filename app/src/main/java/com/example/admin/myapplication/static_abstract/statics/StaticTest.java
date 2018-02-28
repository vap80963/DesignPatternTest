package com.example.admin.myapplication.static_abstract.statics;

import com.example.admin.myapplication.static_abstract.A;
import com.example.admin.myapplication.static_abstract.AA;

/**
 * Created by Tin on 2017/10/11.
 */

public class StaticTest extends A {

    A a;
    static AA aa;

    public static void method1(){
//        a = new A();  //访问不到
        aa = new AA();
//        this.aa = new AA();  //static方法内不能使用this 或 super
//        super.notify();

    }

    public void method2() {
        a = new A();
        this.a = new A();
        aa = new AA();
        super.getClass();
    }

}


