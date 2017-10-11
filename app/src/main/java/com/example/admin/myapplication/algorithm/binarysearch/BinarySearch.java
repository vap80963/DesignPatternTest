package com.example.admin.myapplication.algorithm.binarysearch;

/**
 * 二分查找 / 折半查找算法
 * Created by Tin on 2017/8/29.
 */

public class BinarySearch {

    /**
     *  This is Arrays.binarySearch(), but doesn't do any argument validation.
     * 要求输入的是按照从小到大排好顺序的数组
     * @param array
     * @param value
     * @return
     */
    public int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1; //获得一个中间值，奇数时向下取整中间值，偶数时取中间值
            int midValue = array[mid];

            if (midValue < value) {  //如果中间值比目标值小，将下界移动到中间值后
                low = mid + 1;
            } else if (midValue > value) {  //如果中间值比目标值大，将上界移动到中间值前
                high = mid - 1;
            } else {
                return mid;  //查找到该值，返回其位置
            }
        }
        return ~low;  //查找不到
    }

    //
    static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;

        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            final int midVal = array[mid];

            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }

}
