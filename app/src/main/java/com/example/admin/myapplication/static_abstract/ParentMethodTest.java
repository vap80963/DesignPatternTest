package com.example.admin.myapplication.static_abstract;

/**
 * Created by hasee on 2/11/2018.
 *
 * @author tin
 */

public class ParentMethodTest extends A {

    public ParentMethodTest() {
        super.add();
        super.decrease();
        //A.add();  //Non-static method cannot be referenced from a static context
        A.decrease();
        A.B.b1();
        //A.C.c1();
    }

    //A.add();
    //A.decrease();

    @Override
    public void add() {
        super.add();
    }

    A a = new A();

}

class OutClassTest {
//    A.decrease();

}

