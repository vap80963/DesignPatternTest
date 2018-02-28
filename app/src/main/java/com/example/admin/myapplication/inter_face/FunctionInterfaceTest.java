package com.example.admin.myapplication.inter_face;

/**
 * Java8引入的一个核心概念是函数式接口(Function Interfaces)。
 * 通过在接口中添加一个抽象方法，这些方法可以在接口中直接运行
 *
 * 如果一个接口只定义了一个抽象方法，那么这个接口就成了函数式接口。
 * 同时，引入了一个新的注解：@FunctionalInterface。可以把它放在接口前，表明这个接口是函数式接口
 * 不过，这个注解是非必须的，只要接口只包含一个方法，虚拟机会自行判断。当然，最好还是加上。
 * 在接口添加了@FunctionalInterface 注解的，只允许有一个方法，否则编译器会报错。
 */
@FunctionalInterface
public interface FunctionInterfaceTest {

    public abstract void start();

}
