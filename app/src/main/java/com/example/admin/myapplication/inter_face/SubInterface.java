package com.example.admin.myapplication.inter_face;

import android.util.Log;

/**
 * Created by hasee on 12/4/2017.
 *
 * @author tin
 */

public class SubInterface implements InterfaceTest {

    public static void main(String[] args) {
        SubInterface sub = new SubInterface();
        int i = sub.count();
        Log.e("SubInterface","sub.count() = " + i);
        int sum = InterfaceTest.startCount(++i);
        Log.e("SubInterface","InterfaceTest.startCount() = " + sum);
    }

}
