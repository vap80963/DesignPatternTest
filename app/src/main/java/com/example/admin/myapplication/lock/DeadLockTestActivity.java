package com.example.admin.myapplication.lock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;

/**
 * Created by hasee on 1/31/2018.
 *
 * @author tin
 */

public class DeadLockTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DeadLock lock = new DeadLockSynchronized();
        DeadLock lock = new DeadReentrantLock();

        for (int i = 0; i < 10; i++) {
            Thread producer = new Thread(new Producer(lock));
            Thread producer1 = new Thread(new Producer(lock));
            Thread consumer = new Thread(new Consumer(lock));
            Thread consumer1 = new Thread(new Consumer(lock));

            producer.start();
            producer1.start();
            consumer.start();
            consumer1.start();
        }
    }

    public class Producer implements Runnable {

        private DeadLock deakLock;

        public Producer(DeadLock lock) {
            this.deakLock = lock;
        }

        @Override
        public void run() {
            deakLock.product("北京烤鸭");
        }
    }

    public class Consumer implements Runnable {

        private DeadLock deadLock;

        public Consumer(DeadLock lock) {
            this.deadLock = lock;
        }

        @Override
        public void run() {
            deadLock.consume("消费一只");
        }
    }
}


