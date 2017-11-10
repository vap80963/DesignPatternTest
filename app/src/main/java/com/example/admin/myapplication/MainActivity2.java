package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity2 extends AppCompatActivity {

    @Bind(R.id.ui_result)
    TextView uiResult;
    @Bind(R.id.json_string)
    TextView mTextView;
    @Bind(R.id.button7)
    Button mButton;
    String n = "";
    String nm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONObject jsonObject5 = new JSONObject();
        JSONObject jsonObject6 = new JSONObject();

        HashMap<String, Object> hashMap = new HashMap<>();

        /*OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

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
        }*/
        int i;
/*        for (;;) {
            i = new Random(10).nextInt();
            Log.e("MainActivity2", "i = " + i);
            i = i * 100 / 200 * 4 / 3;
            int b = i * 100;
            if ((i % 4) == 0) {
                Log.e("MainActivity2", "i = " + i);
            }
        }*/

        for (int j = 0; j < 5; j++) {
            int audio = new Random().nextInt(5);
            String type[] = new String[] {"story_0", "poem_0", "song_0", "knowledge_0", "rabbit_dance"};
            StringBuffer resultStr = new StringBuffer("");
            switch (audio) {
                case 0:
                case 1:
                case 2:
                case 3:
                    int num = new Random().nextInt(5) + 1;
                    resultStr.append(type[audio]).append(num);
                    break;
                case 4:
                    resultStr.append(type[audio]);
                    break;
            }
            int result = getResource(resultStr.toString(), R.raw.class);
        }
    }

    public int getResource(String sourceName, Class<?> targetClass) {
/*        Context ctx=getBaseContext();
        int resId = getResources().getIdentifier(sourceName, "raw", ctx.getPackageName());
        //如果没有在"mipmap"下找到imageName,将会返回0
        return resId;*/
        Class cls = targetClass;
        try {
            Field field = cls.getDeclaredField(sourceName);
            return field.getInt(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @OnClick({R.id.json_string, R.id.button7})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.json_string:
                uiResult.setText("uiResult");
                break;
            case R.id.button7:

        }
    }

    private static final Object DELETED = new Object();
    private boolean mGarbage = false;

    private int[] mKeys;
    private Object[] mValues;
    private int mSize;

    private void gc() {
        int size = mSize;
        int o = 0;

        int keys[] = mKeys;
        Object values[] = mValues;

        assert size <= mSize;
        for (int i = 0; i < size; i++) {
            Object value = values[i];

            if (value != DELETED) {
                if (i != o) {
                    values[o] = value;
                    keys[o] = keys[i];
                    values[i] = null;
                }
                o++;
            }
        }

        mGarbage = false;
        mSize = o;
    }



}
