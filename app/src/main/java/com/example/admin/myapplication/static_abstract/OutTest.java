package com.example.admin.myapplication.static_abstract;

/**
 * Created by hasee on 2/11/2018.
 *
 * @author tin
 */

public class OutTest implements Cloneable {

    StaticMethodClass mClass = new StaticMethodClass();

    public int num = StaticMethodClass.NUMS;
    public String phone = StaticMethodClass.addNum();


    protected OutTest clone() {
        OutTest outTest = null;
        try {
            outTest = (OutTest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return outTest;
    }

    private interface Interface {

        default int interfac(){
            return 1;
        }

        static void interfaces() {
            
        }

        void interfacess();
    }

    public void main(String a) {

        add(new Interface() {
            @Override
            public int interfac() {
                return 0;
            }

            @Override
            public void interfacess() {

            }
        });
    }

    protected interface Interface1 extends Interface {
        @Override
        default int interfac() {
            return 0;
        }
    }

    interface Interface2 extends Interface {

    }

    public void add(Interface i) {

    }



    public static void addNums() {

    }
}


