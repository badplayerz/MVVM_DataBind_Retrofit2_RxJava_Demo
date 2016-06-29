package com.zlh.mvvp_databind_retrofit2_rxjava.RxBus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * RxJava 实现事件总线（EventBus，OttO）
 * Created by sdbean-zlh on 16/6/23.
 */
public class RxBus {

    private static volatile RxBus staticRxBus;
    private final Subject subject;

    public RxBus(){
        subject = new SerializedSubject<>(PublishSubject.create());
    }

    public Throwable e;


    /**
     * Double Checked locking单例
     * 保证多线程安全
     * @return
     */
    public static RxBus getRxBus(){

        RxBus rxBus = staticRxBus;

        if(rxBus == null){

            synchronized (RxBus.class){
                rxBus = staticRxBus;
                if(rxBus == null){
                    rxBus = new RxBus();
                    staticRxBus = rxBus;
                }
            }
        }

        return rxBus;
    }

    /**
     * 发送事件
     * @param o
     */
    public void post(Object o){
        subject.onNext(o);
    }

    /**
     * 接收事件
     * ofType是filter（过滤）+cast（类型判断）
     * @param event
     * @param <T>
     * @return
     */
    public <T>Observable<T> toObservable(Class<T> event){

        return subject.ofType(event);
    }

}
