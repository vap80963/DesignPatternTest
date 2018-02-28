package com.example.admin.myapplication.fragment_activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;

/**
 * Created by hasee on 10/31/2017.
 *
 * @author tin
 * @function
 */

public class TestFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        LogUtils.e("TestFragment + onAttach(context)");
        super.onAttach(context);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        LogUtils.e("TestFragment + onAttachFragment()");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Activity activity) {
        LogUtils.e("TestFragment + onAttach(activity)");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.e("TestFragment + onCreate()");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.e("TestFragment + onCreateView()");
        View v = inflater.inflate(R.layout.fragment_test, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtils.e("TestFragment + onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.e("TestFragment + onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        LogUtils.e("TestFragment + onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogUtils.e("TestFragment + onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtils.e("TestFragment + onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogUtils.e("TestFragment + onStop()");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtils.e("TestFragment + onDestroyView()");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogUtils.e("TestFragment + onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        LogUtils.e("TestFragment + onDetach()");
        super.onDetach();
    }
}
