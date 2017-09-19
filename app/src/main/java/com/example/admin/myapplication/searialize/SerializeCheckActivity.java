package com.example.admin.myapplication.searialize;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.LogUtils;
import com.example.admin.myapplication.R;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Tin on 2017/9/19.
 */

public class SerializeCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startCheck();

        reflectCheck();
    }

    /**
     * 通过反射获得UserInfo实例，比较两者是否相同
     */
    private void reflectCheck() {
        UserInfo userInfo = UserInfo.getInstance();
        userInfo.setName("Baby");
        Constructor[] constructs = UserInfo.class.getDeclaredConstructors();
        Constructor construct = constructs[0];
        construct.setAccessible(true);
        try {
            UserInfo userInfo1 = (UserInfo) construct.newInstance(null);
            LogUtils.e("userInfo HashCode = " + userInfo.hashCode());
            LogUtils.e("userInfo1 == userinfo  " + (userInfo == userInfo1));
            LogUtils.e("userInfo1 HashCode = " + userInfo1.hashCode());
            LogUtils.e("userinfo1 = " + userInfo1.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void startCheck() {

        UserInfo userInfo = UserInfo.getInstance();
        userInfo.setName("Baby");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() ;
        File file = new File(Environment.getExternalStorageDirectory(), "singleTon.txt");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(userInfo);
            oos.writeObject(null); //解决EOF的关键，加入一个空的对象
            oos.flush();
            oos.close();
            LogUtils.e("userInfo HashCode = " + userInfo.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = null;
            if ((o = ois.readObject()) != null) {
                UserInfo userInfo1 = (UserInfo) ois.readObject();
                LogUtils.e("userinfo1 = " + userInfo.getName());
//                LogUtils.e("userInfo1 HashCode = " + userInfo1.hashCode()); //Attempt to invoke virtual method 'int java.lang.Object.hashCode()' on a null object reference
                LogUtils.e("userInfo1 == userinfo  " + (userInfo == userInfo1));
            }
            ois.close();
            fis.close();

        } catch (EOFException e) {
          LogUtils.e("getMessage()" + e.getLocalizedMessage());
          LogUtils.e("getStackTrace" + e.getStackTrace().toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.e(e.getMessage());
        }

    }
}
