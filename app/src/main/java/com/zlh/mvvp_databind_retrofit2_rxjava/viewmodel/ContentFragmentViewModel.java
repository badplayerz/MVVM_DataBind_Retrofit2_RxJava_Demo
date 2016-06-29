package com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zlh.mvvp_databind_retrofit2_rxjava.RxBus.RxBus;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.ContentFragmentInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.WechatSelectionInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.ContentBus;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by sdbean-zlh on 16/6/22.
 */
public class ContentFragmentViewModel implements ContentFragmentInterf.ViewModel{

    private ContentFragmentInterf.MainView mainView;
    private Context context;
    private Subscription subscription;

    public ContentFragmentViewModel(ContentFragmentInterf.MainView inf, Context ct) {
        mainView = inf;
        context = ct;
        initRxBus();
    }

    private void initRxBus(){
        Log.e("xxx","xxx");
        subscription = RxBus.getRxBus().toObservable(ContentBus.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ContentBus>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("throw :",""+e);
                    }

                    @Override
                    public void onNext(ContentBus contentBus) {
                        mainView.initWebViewUrl(contentBus.getWebUrl());
                    }
                });
    }


    @Override
    public void destory() {
        if(!subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }

    }
}
