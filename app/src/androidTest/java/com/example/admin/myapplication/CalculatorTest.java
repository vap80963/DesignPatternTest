package com.example.admin.myapplication;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tin on 2017/8/22.
 */
public class CalculatorTest {

    Calculator mCalculator;

    @Before
    public void setUp() throws Exception{
        mCalculator = new Calculator();
    }

    @Test
    public void calculate() throws Exception {
        mCalculator.calculate(2, 3);
    }

}