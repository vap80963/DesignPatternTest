package com.example.admin.myapplication.bit_arithmetic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication.R;

/**
 * Created by Tin on 2017/10/11.
 */

public class BitArithmeticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BitArithmeticTest.test1();

        BitArithmeticTest.test2();

        BitArithmeticTest.test3();

        BitArithmeticTest.test4();
    }
}
