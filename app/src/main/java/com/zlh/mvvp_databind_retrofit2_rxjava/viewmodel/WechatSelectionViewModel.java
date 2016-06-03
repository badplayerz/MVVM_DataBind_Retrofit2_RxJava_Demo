package com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableInt;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.google.common.eventbus.Subscribe;
import com.zlh.mvvp_databind_retrofit2_rxjava.app.WechatSelectionApplication;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.WechatSelectionInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;
import com.zlh.mvvp_databind_retrofit2_rxjava.network.NetWorkManager;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sdbean-zlh on 16/5/21.
 */
public class WechatSelectionViewModel implements WechatSelectionInterf.ViewModel{
    private WechatSelectionInterf.MainView mMainView;
    private Context mContext;
    private Subscription mSubscription;

    public ObservableInt mProgress;
    public ObservableInt mNetContent;
    public ObservableInt mList;

    public WechatSelectionViewModel(WechatSelectionInterf.MainView mainView, Context context) {
        mMainView = mainView;
        mContext = context;

        mProgress = new ObservableInt(View.GONE);
        mNetContent = new ObservableInt(View.GONE);
        mList = new ObservableInt(View.VISIBLE);
    }

    /**
     * 禁止无参实例化
     */
    private WechatSelectionViewModel(){

    }

    /**
     * 显示加载进度条
     */
    private void showProgress(){
        mProgress.set(View.VISIBLE);
        mNetContent.set(View.GONE);
        mList.set(View.GONE);
    }

    private void hiddenProgress(String error){
        mProgress.set(View.GONE);
        if(error == null || "".equals(error)){
            mNetContent.set(View.GONE);
            mList.set(View.VISIBLE);
        }else{
            mNetContent.set(View.VISIBLE);
            mList.set(View.GONE);
        }
    }

    /**
     * 请求数据
     */
    public void postWechatSelection(){

        showProgress();
        Log.e("eeee","eeee");
        mSubscription = WechatSelectionApplication.create(mContext)
                .getWechatSelectionNetwork()
                .getList(1,20,"json",NetWorkManager.WECHATSELECTION_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WechatSelectionBean>() {
                    @Override
                    public void call(WechatSelectionBean wechatSelectionBean) {

                        if(wechatSelectionBean.getReason().equals("success")){
                            Log.e("hhh","hhh");
                            hiddenProgress(null);
                            mMainView.loadDataRecycleView(wechatSelectionBean.getResult().getList());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("kkk","kkk");
                        hiddenProgress("1");
                    }
                });
    }

    /**
     * fab按钮事件
     * @param view
     */
    public void onClickFabLoad(View view) {
        Snackbar.make(view, "It is a fab button", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void destory() {
        if(mSubscription != null && !mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        mSubscription = null;
        mContext = null;
        mMainView = null;
    }
}
