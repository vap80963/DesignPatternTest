package com.example.admin.myapplication.algorithm.wordsort;

import com.example.admin.myapplication.utils.LogUtils;

/**
 * Created by Tin on 2017/11/10.
 */

public class WordSortUtil {

    public char[] wordSort(String str) {
        //字母排序
//        String str = "asoboiuweobbAOSIIDBObohoSDII";
        char test[] = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < test.length; j++) {
                if (test[j] == ('a' + i) || test[j] == ('A' + i)) {
                    char temp = test[pos];
                    test[pos++] = test[j];
                    test[j] = temp;
                }
            }
        }
        for (int i = 0; i < test.length; i++) {
            LogUtils.e("a1 = " + test[i]);
        }
        return test;
    }
}
