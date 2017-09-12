package com.example.admin.myapplication;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Tin on 2017/8/22.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivity2InstrumentationTest {

    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule(MainActivity2.class);

    @Test
    public void sayHello(){
        onView(withText("Hello World!")).perform(click());

        onView(withId(R.id.ui_result)).check(matches(withText("My Application")));
    }

}
