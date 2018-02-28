package com.example.admin.myapplication.inter_face;

/**
 * Created by hasee on 12/4/2017.
 *
 * @author tin
 */

public interface InterfaceTest {

    /**
     * JDK1.8中，可以在接口中定义默认方法，这种方法可以在子类中直接使用
     * 实现了接口的实例可以直接调用这个方法
     * @return
     */
    default int count() {
        return 1;
    }

    /**
     * JDK1.8中也支持了static关键字修饰的方法，可以在其他类中直接调用，同样默认是public的
     * @param n
     * @return
     */
    static int startCount(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }

}
