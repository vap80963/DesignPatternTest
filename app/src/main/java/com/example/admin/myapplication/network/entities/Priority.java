package com.example.admin.myapplication.network.entities;

/**
 * Created by hasee on 11/21/2017.
 *
 * @author tin
 * 优先级枚举，分别为低，正常，高，立即
 * 在请求队列中会根据加入队列的顺序和优先级对请求进行排序，优先级高的请求将优先得到执行
 */

public enum Priority {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE
}
