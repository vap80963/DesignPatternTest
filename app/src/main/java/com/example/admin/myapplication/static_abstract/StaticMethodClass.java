package com.example.admin.myapplication.static_abstract;

import com.example.admin.myapplication.static_abstract.statics.ParamTest;

/**
 * Created by hasee on 2/11/2018.
 *
 * @author tin
 */

public class StaticMethodClass {

    public static OutTest outTest = new OutTest();

    public static final int NUMS = 1;
    public static int PHONE = 1;

    static {
        //static int num = 1;  //static变量需要声明为成员变量，不能在方法或代码块中声明
        addNum();
        OutTest outTest = new OutTest();
        PHONE = 2;
        //this.clone();
        //super.clone();
        //addString();   //不能调用非static方法，不能使用this或super关键字
    }

    {
        //static int num = 2;
        addString();
    }

    public StaticMethodClass() {


    }

    public void addString() {
        Thread thread = new Thread(() -> {

        });
        thread.getPriority();
        thread.setPriority(1000);
    }

    public static String addNum() {

        return "result";
    }

    class ParamTest2 extends ParamTest {
        public ParamTest2() {
            super.b = 1;
//            super.a = 2;
            c = 2;
            b = 3;
        }
        //        c = 2;
        public void add () {
            c = 2;
            super.b = 2;
            StaticMethodClass mStaticMethodClass = new StaticMethodClass();
            mStaticMethodClass.addString();

            ParamTest mParamTest = new ParamTest2();
            mParamTest.c = 2;
//            mParamTest.b = 3;  //父类屏蔽了子类的直接访问，智能通过super.b的方式访问
        }

    }
    ParamTest mParamTest2 = new ParamTest2();

    public void paramTest() {
//        mParamTest2.a = 1;
//        mParamTest2.b = 1;  //位于ParamTest2外部，不属于ParamTest的子类，所以没有访问权限
        mParamTest2.c = 2;
    }

    public static void main(String[] args) {
        StaticMethodClass staticMethodClass = new StaticMethodClass();
        staticMethodClass.addString();
    }
}

