package com.example.admin.myapplication.lambda_stream;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.admin.myapplication.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by hasee on 12/4/2017.
 *
 * @author tin
 */

public class StreamAndLambdaTest {


    @TargetApi(Build.VERSION_CODES.N)
    void collectForSomething(List<Transaction> list) throws ClassNotFoundException {
/*
        List<Transaction> result = list.parallelStream()
                .filter(list.get(0) -- > list.get(0).equals("")
                        .sorted(Comparator.comparing(Transaction::getValue).reversed())
                        .map(Transaction::getId)
                .collect(toList());
*/

        Object object = new Object();
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null)
                        return super.loadClass(name);
                    byte[] b = new byte[is.available()];
                    is.read();
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = classLoader.loadClass("com.example.admin.myapplication.lambda_stream.Transaction");
        LogUtils.e("" + obj.getClass());
        LogUtils.e((obj instanceof com.example.admin.myapplication.lambda_stream.Transaction) + "");

    }


}
