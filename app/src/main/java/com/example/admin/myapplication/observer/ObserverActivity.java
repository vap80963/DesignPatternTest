package com.example.admin.myapplication.observer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.myapplication.R;

/**
 * Created by admin on 2017/7/28.
 */

public class ObserverActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);

        mTextView = (TextView) findViewById(R.id.activity_observer_tv);

        Coder coder = new Coder("First");
        Coder coder1 = new Coder("Second");

        coder.setCallBacker(new CallBacker() {
            @Override
            public void onCallBack(Object o) {
                mTextView.setText(o.toString());
            }
        });

        Program program = new Program();
        program.addObserver(coder);
        program.addObserver(coder1);
        program.postNewsToObservers("The content has been changed!!");



    }


}
