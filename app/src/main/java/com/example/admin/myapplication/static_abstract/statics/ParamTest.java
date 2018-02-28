package com.example.admin.myapplication.static_abstract.statics;

/**
 * Created by hasee on 2/28/2018.
 *
 * @author tin
 */

public abstract class ParamTest {
    int a = 1;
    //    a = a + 1;  //变量可以初始化或不初始化但不能在抽象类中重新赋值或操作该变量（只能在子类中改变该变量）
    protected int b;
//    b = 2;
    public int c;
}
