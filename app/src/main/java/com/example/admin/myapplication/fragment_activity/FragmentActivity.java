package com.example.admin.myapplication.fragment_activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;

/**
 * Created by hasee on 10/31/2017.
 *
 * @author tin
 * @function
 */

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        LogUtils.e("FragmentActivity + onCreate()");
    }


    @Override
    public void onStart() {
        LogUtils.e("FragmentActivity + onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtils.e("FragmentActivity + onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.e("FragmentActivity + onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.e("FragmentActivity + onStop()");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtils.e("FragmentActivity + onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtils.e("FragmentActivity + onRestoreInstanceState()");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        LogUtils.e("FragmentActivity + onRestart()");
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        LogUtils.e("FragmentActivity + onDestroy()");
        super.onDestroy();
    }


}
