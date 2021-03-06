package com.example.admin.myapplication.bit_arithmetic;

import com.example.admin.myapplication.utils.LogUtils;

/**
 * Created by Tin on 2017/10/11.
 */

public class BitArithmeticTest {

    /**
     * a & b
     */
    public static void test1() {
        int a = 267;
        int b = 0177;

        int result = a & b;

        LogUtils.e("a & b = " + result);

        int c = 077;
        result = a & c;
        LogUtils.e("a & c = " + result);

        int d = 5;
        int e = 4;
        result = d & e;
        LogUtils.e("d & e = " + result);

        e = 7;
        result = d & e;
        LogUtils.e("d & e = " + result);
    }

    /**
     * a | b
     */
    public static void test2() {
        int a = 027;
        int b = 035;

        int result = a | b;
        LogUtils.e("a | b = " + result);
    }

    /**
     * a ^ b
     */
    public static void test3() {
        int a = 021;
        int b = 035;

        int result = a ^ b;
        LogUtils.e("a ^ b = " + result);

        int c = 017;
        result = a ^ c;
        LogUtils.e("a ^ c = " + result);

        result = c ^ b;
        LogUtils.e("c ^ b = " + result);

        result = b ^ c;
        LogUtils.e("b ^ c = " + result);
    }

    public static void test4() {
        int a = 177;
        int b = 077;

        int result = a & ~b;
        LogUtils.e("a & ~b = " + result);

        result = ~b;
        LogUtils.e("~b = " + result);

        int c = 0xfff8;
        LogUtils.e("c = " + c);

        int d = ~0377;
        LogUtils.e("d = " + d);

    }

}
