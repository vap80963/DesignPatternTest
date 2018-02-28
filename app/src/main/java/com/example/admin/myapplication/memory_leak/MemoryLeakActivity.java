package com.example.admin.myapplication.memory_leak;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hasee on 2/10/2018.
 *
 * @author tin
 */

public class MemoryLeakActivity extends AppCompatActivity {

    Handler mHandler = new Handler(msg -> {
        if (msg.what == 1)
            startLeak();
        return true;
    });



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String a[] = new String[10];
        for (int i = 0; i < 10; i++) {
            LogUtils.e("a = " + a[i]);
        }

        String s1 = "I" + "Love";
        String s2 = "I" + new String("Love");
        String s3 = "ILove";
        String I = "I";
        String s4 = I + "Love";
        String s5 = new String("ILove");
        String s55 = new String("ILove");
        String s6 = new String("I") + "Love";
        String Love = "Love";
        String s7 = I + Love;
        LogUtils.e("s1 == s2" + (s1 == s2));
        LogUtils.e("s1 == s4" + (s1 == s4));
        LogUtils.e("s1 == s3" + (s1 == s3)); //true
        LogUtils.e("s2 == s3" + (s2 == s3));
        LogUtils.e("s3 == s4" + (s3 == s4));
        LogUtils.e("s3 == s5" + (s3 == s5));
        LogUtils.e("s5 == s55" + (s5 == s55));
        LogUtils.e("s3 == s6" + (s3 == s6));
        LogUtils.e("s5 == s6" + (s5 == s6));
        LogUtils.e("s5 == s6" + (s5 == s6));
        LogUtils.e("s3 == s7" + (s3 == s7));
        LogUtils.e("s4 == s7" + (s4 == s7));

        LogUtils.e("str" + 1 + 2);           //str12
        LogUtils.e("str" + (1 + 2));           //str3
/*        Map map = System.getProperties();
        Random r = new Random();
        while (true) {
            map.put(r.nextInt(), "value");
        }*/

/*        LogUtils.e("Start new Thread");
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                LogUtils.e("Start leak function");
                mHandler.sendEmptyMessage(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

    public void startLeak(){
        Map<Key,String> m = new HashMap<>();
        while(true) {
            for(int i=0;i<10000;i++) {
                if(!m.containsKey(new Key(i))) {
                    m.put(new Key(i), "Number:" + i);
                }
            }
            LogUtils.e("HashMap.size() = " + m.size());
        }
    }

    static class KeySon extends Key {

        KeySon(Integer id) {
            super(id);
        }

        KeySon() {
            super(0);
        }

    }

    static  class Key {
        Integer id;

        private int i = 0;

        Key(Integer id) {
            this.id = id;
        }

        public static void add() {

        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }
}
