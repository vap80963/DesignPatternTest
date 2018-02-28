package com.example.admin.myapplication.algorithm.lastindex;

/**
 * Created by hasee on 12/13/2017.
 *
 * @author tin
 */

public class LastIndex {


    int lastIndexOfString(String source, String target, int fromIndex) {
        if (fromIndex < 0)
            return -1;

        int rightIndex = source.length() - target.length();

        if (fromIndex > rightIndex)
            rightIndex = fromIndex;

        if (target == null)
            return fromIndex;

        int lastCharIndex = target.length() - 1;
        char lastChar = target.charAt(lastCharIndex);
        int min = target.length() - 1;
        int i = min + rightIndex;

        startSearchLastIndex:
        while (true) {
            while (i >= lastCharIndex && source.charAt(i) != lastChar) {
                i--;
            }

            if (i < lastCharIndex) {
                return -1;
            }

            int j = i - 1;
            int start = j - (target.length() - 1);
            int t = lastCharIndex - 1;

            while (j > start) {
                if (source.charAt(j--) != target.charAt(t--)) {
                    i--;
                    continue startSearchLastIndex;
                }
            }
            return start + 1;
        }
    }
}
