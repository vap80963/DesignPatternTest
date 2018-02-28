package com.example.admin.myapplication.multi_thread;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MainActivity2 extends AppCompatActivity {

    @BindView(R.id.ui_result)
    TextView uiResult;
    @BindView(R.id.json_string)
    TextView mTextView;
    String n = "";
    String nm = "";

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        startExecutor(null);
        /*
        LogUtils.e("ctl = " + ctl.get());
        LogUtils.e("COUNT_BITS = " + COUNT_BITS);
        LogUtils.e("CAPACITY = " + CAPACITY);
        LogUtils.e("RUNNING = " + RUNNING);
        LogUtils.e("SHUTDOWN = " + SHUTDOWN);
        LogUtils.e("STOP = " + STOP);
        LogUtils.e("TIDYING = " + TIDYING);
        LogUtils.e("TERMINATED = " + TERMINATED);
        LogUtils.e("runStateOf = " + runStateOf(ctl.get()));
        LogUtils.e("workerCountOf = " + workerCountOf(ctl.get()));
        LogUtils.e("ctlOf = " + ctlOf(RUNNING, 0));
        int result = 2;
        for (int i = 2; i < 31; i++) {
            result = 2;
        }
        result += (result - 1);
        LogUtils.e("2 ^ 32 = " + result);
*/

/*

        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
        JSONObject jsonObject5 = new JSONObject();
        JSONObject jsonObject6 = new JSONObject()/


                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

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

    }

    public void eventBusTest() {
        EventBus.getDefault().post(new TestEvent());
        EventBus.getDefault().postSticky(new TestEvent());
    }

    public class TestEvent {

    }

    private static ExecutorService mExecutor = Executors.newFixedThreadPool(10);

    public void startForResult() {
        Future future = mExecutor.submit(new Task());
        try {
            String result = (String) future.get();
            LogUtils.e("result = " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    public static void startExecutor(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        LogUtils.e("startTime = " + getFormatTime());
        try {
            for (int i = 0; i < 3; i++) {
//                Worker worker = new Worker();
                Task task = new Task();
                executorService.schedule(task, 10, TimeUnit.SECONDS);
                Thread.sleep(1000);
            }
            Thread.sleep(3000);
        } catch (InterruptedException e) {
                e.printStackTrace();
//            executorService.scheduleAtFixedRate();
//            executorService.scheduleWithFixedDelay();
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            //wait for all worker to be finished
        }

        LogUtils.e("All workers is finished, the time = " + getFormatTime());

/*        for (int i = 0; i < 10; i++) {
            mExecutor.execute(new Task());
        }*/
    }

    private static String getFormatTime() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatTime = dateFormat.format(currentTime);
        return formatTime;
    }

    static class Task implements Callable {
        @Override
        public Object call() throws Exception {
            TimeUnit.SECONDS.sleep(2);
            return "this is a future case";
        }
    }

    @OnClick(R.id.json_string)
    public void OnClick() {
        uiResult.setText("uiResult");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
