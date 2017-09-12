package com.example.admin.myapplication.rx;


import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by Tin on 2017/8/21.
 */

public class Test {

    public void loadImage(){
        /*File[] folders = new File[]{};
        Observable.from(folders)
                .flatMap(new Func1<File, Observable<?>>() {
                    @Override
                    public Observable<?> call(File file) {
                        return Observable.from(file.listFiles());
                    }
                })
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        return null;
                    }
                })
                .map(new Func1<File, Bitmap>() {
                    @Override
                    public Bitmap call(File file) {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {

                    }
                });*/


    }

    Subscriber<String> subscriber;
    Observer<String> observer;
    /**
     * 创建观察者的两种方法，Observer和Subscriber
     */
    public void createObserver(){
        observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

         subscriber = new Subscriber<String>() {

            /**
             * 相比Observer增加的方法，属于可选重写方法
             * 在Subcriber刚开始，而事件还未发送之前被调用，用于做一些简单的准备工作
             */
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        //用来取消订阅前判断订阅状态
        subscriber.isUnsubscribed();
        //Subscriber所实现的另一个接口Subscription的方法，用于取消订阅，避免Obserable持有Subscriber的引用
        //这个引用如果不被及时释放，将有内存泄漏的风险
        subscriber.unsubscribe();

    }


    public void createObservable(){
        /**
         * OnSubscribe会被存储在返回的ObServable对象中，它的作用相当于一个计划表，当Observable被订阅的时候
         * OnSubscribe的call()方法会自动被调用，事件序列就会依照设定依次触发
         * 这样，由被观察者调用了观察者的回调方法，就实现了被观察者向观察者的事件传递，即观察者模式
         */
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("first");
                subscriber.onNext("second");
                subscriber.onNext("third");
                subscriber.onCompleted();
            }
        });

        /**
         * just(T... t)
         * 这里会依次调用：
         * onNext("hello");
         * onNext(",");
         * onNext("world");
         * onCompleted();
         */
        Observable observableJust = Observable.just("hello",",","world");

        /**
         * 订阅者订阅事件，同样有两种方式
         */
        observable.subscribe(observer);

        observable.subscribe(subscriber);
    }
}
