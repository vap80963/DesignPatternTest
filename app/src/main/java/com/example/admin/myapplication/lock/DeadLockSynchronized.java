package com.example.admin.myapplication.lock;

import com.example.admin.myapplication.utils.LogUtils;

/**
 * Created by hasee on 2/1/2018.
 *
 * @author tin
 */

public class DeadLockSynchronized implements DeadLock {

    private volatile String name;
    public int count = 0;
    public boolean flag = false;

    public synchronized void product(String name) {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        this.name = name + count;
        LogUtils.e(Thread.currentThread().getName() + "..生产者... " + this.name);
        flag = true;
        notifyAll();
    }

    public synchronized void consume(String name) {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LogUtils.e(Thread.currentThread().getName() + "..消费者... " + this.name);
        count--;
        flag = false;
        notifyAll();
    }

}
