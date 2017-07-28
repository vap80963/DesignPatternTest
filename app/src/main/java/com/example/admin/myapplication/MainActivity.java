package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.json_string);

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONObject jsonObject5 = new JSONObject();
        JSONObject jsonObject6 = new JSONObject();

        try {
            jsonObject.put("action","100");

            jsonObject1.put("domain","Talk");
            jsonObject1.put("intent","text");
            jsonObject1.put("sequence",0);
            jsonObject2.put("text","hei");
            jsonObject1.put("object",jsonObject2);

            jsonObject5.put("domain", "Motion");
            jsonObject5.put("intent", "random");
            jsonObject6.put("type", "dance");
            jsonObject5.put("object", jsonObject6);
            jsonArray1.put(jsonObject5);
            jsonObject1.put("directives", jsonArray1);

            jsonArray.put(jsonObject1);

            jsonObject3.put("domain","Talk");
            jsonObject3.put("intent","text");
            jsonObject3.put("sequence",0);
            jsonArray.put(jsonObject3);

            jsonObject4.put("data",jsonArray);
            jsonObject.put("content",jsonObject4);

            Log.e("MainActivity" , "  --->> jsonString = " + jsonObject.toString());
            mTextView.setText(jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
