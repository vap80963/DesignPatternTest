package com.example.admin.myapplication.lock;

import com.example.admin.myapplication.utils.LogUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hasee on 2/4/2018.
 *
 * @author tin
 */

public class DeadReentrantLock implements DeadLock {

    private String name;
    private int count = 1;//烤鸭的初始数量
    private boolean flag = false;//判断是否有需要线程等待的标志

    private Lock resourceLock = new ReentrantLock();
    private Condition condition = resourceLock.newCondition();

    public void product(String name) {
        resourceLock.lock();
        try {
            while (flag) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name + count;
            count++;
            LogUtils.e(Thread.currentThread().getName() + "..生产者... " + this.name);
            flag = true;
            condition.signalAll();
        } finally {
            resourceLock.unlock();
        }
    }

    public void consume(String name) {
        try {
            resourceLock.lock();
            while (!flag) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            this.name = name + count;
            LogUtils.e(Thread.currentThread().getName() + "..消费者... " + this.name);
            flag = false;
            condition.signalAll();
        } finally {
            resourceLock.unlock();
        }
    }
}
