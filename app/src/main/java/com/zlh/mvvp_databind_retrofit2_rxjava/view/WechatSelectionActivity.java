package com.zlh.mvvp_databind_retrofit2_rxjava.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.View;

import com.zlh.mvvp_databind_retrofit2_rxjava.R;
import com.zlh.mvvp_databind_retrofit2_rxjava.adapter.WechatSelectionAdapter;
import com.zlh.mvvp_databind_retrofit2_rxjava.app.WechatSelectionApplication;
import com.zlh.mvvp_databind_retrofit2_rxjava.databinding.ActivityWechatselectionBinding;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.WechatSelectionInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;
import com.zlh.mvvp_databind_retrofit2_rxjava.network.NetWorkManager;
import com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel.WechatSelectionViewModel;

import java.util.List;

import rx.functions.Action1;

public class WechatSelectionActivity extends BaseAcivity implements WechatSelectionInterf.MainView{

    private ActivityWechatselectionBinding activityWechatselectionBinding;
    private WechatSelectionViewModel wechatSelectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wechatselection);
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setEnterTransition(new Explode().setDuration(1000));
        }

        initDataBinding();
        setRecycleView();
        postNet();
//        activityWechatselectionBinding.contentWechatselection.wechatRecycleview
    }

    private void initDataBinding(){
        activityWechatselectionBinding = DataBindingUtil.setContentView(this,R.layout.activity_wechatselection);
        wechatSelectionViewModel = new WechatSelectionViewModel(this,getContext());
        activityWechatselectionBinding.setWechatSelectionViewModel(wechatSelectionViewModel);
        setSupportActionBar(activityWechatselectionBinding.toolbar);

    }

    private void setRecycleView(){
        WechatSelectionAdapter wechatSelectionAdapter = new WechatSelectionAdapter();
        activityWechatselectionBinding.wechatRecycleview.setAdapter(wechatSelectionAdapter);
        activityWechatselectionBinding.wechatRecycleview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void postNet(){
        wechatSelectionViewModel.postWechatSelection();
    }

    @Override
    public Context getContext() {
        return WechatSelectionActivity.this;
    }

    @Override
    public void loadDataRecycleView(List<WechatSelectionBean.ResultBean.ListBean> list) {

        Log.e("nnnn","nnnn"+list.size());
        WechatSelectionAdapter wechatSelectionAdapter = (WechatSelectionAdapter)activityWechatselectionBinding.wechatRecycleview.getAdapter();
        wechatSelectionAdapter.setList(list);

    }

}
