package com.example.admin.myapplication.sync_test;

/**
 * Created by hasee on 11/4/2017.
 *
 * @author tin
 * @function
 */

public class SynchronizedTest {
    //对方法加锁,既是对象锁也是方法锁
    public synchronized void syncMethod() {
        System.out.println("我是对象锁也是方法锁");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //对代码块加锁
    public void syncBlock() {
        synchronized (this) {
            System.out.println("我是对象锁");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
