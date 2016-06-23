package com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel;

import android.content.Context;

import com.zlh.mvvp_databind_retrofit2_rxjava.RxBus.RxBus;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.ContentFragmentInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.WechatSelectionInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.ContentBus;

import rx.Subscription;
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

        subscription = RxBus.getRxBus().toObservable(ContentBus.class)
                .subscribe(new Action1<ContentBus>() {
                    @Override
                    public void call(ContentBus contentBus) {
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
