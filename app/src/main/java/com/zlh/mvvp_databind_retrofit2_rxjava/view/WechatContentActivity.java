package com.zlh.mvvp_databind_retrofit2_rxjava.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.zlh.mvvp_databind_retrofit2_rxjava.R;
import com.zlh.mvvp_databind_retrofit2_rxjava.RxBus.RxBus;
import com.zlh.mvvp_databind_retrofit2_rxjava.adapter.ContentViewPagerAdapter;
import com.zlh.mvvp_databind_retrofit2_rxjava.databinding.ActivityWechatContentBinding;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.BaseInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.WechatSelectionInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.ContentBus;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;
import com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel.WechatContentViewModel;

import java.util.List;

public class WechatContentActivity extends BaseAcivity implements BaseInterf.MainView {

    private ActivityWechatContentBinding activityWechatContentBinding;
    private WechatContentViewModel wechatContentViewModel;
    private WechatSelectionBean.ResultBean.ListBean listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wechat_content);

        if(Build.VERSION.SDK_INT>=21){
            getWindow().setEnterTransition(new Explode().setDuration(1000));
            getWindow().setExitTransition(null);
        }

        listBean = getIntent().getExtras().getParcelable("wechatcontent");


        Log.e("gggg",listBean.getFirstImg());

        initDataBind();


        if(Build.VERSION.SDK_INT >= 21){
            activityWechatContentBinding.contentToolimage.setTransitionName(listBean.getFirstImg()+"pic");
            Glide.with(activityWechatContentBinding.contentToolimage.getContext())
                    .load(listBean.getFirstImg())
                    .error(R.drawable.walle)
                    .fitCenter()
                    .into(activityWechatContentBinding.contentToolimage);
        }

        initViewPager();

    }

    private void initDataBind(){
        activityWechatContentBinding = DataBindingUtil.setContentView(WechatContentActivity.this,R.layout.activity_wechat_content);
        wechatContentViewModel = new WechatContentViewModel(this,listBean,getContext());
        activityWechatContentBinding.setWechatContentViewModel(wechatContentViewModel);
        setSupportActionBar(activityWechatContentBinding.toolbar);

        RxBus.getRxBus().post(new ContentBus(listBean.getUrl()));
    }

    private void initViewPager(){
        ContentViewPagerAdapter contentViewPagerAdapter = new ContentViewPagerAdapter(getSupportFragmentManager());
        contentViewPagerAdapter.addFragment(ContentFragment.newInstance(listBean.getUrl()));
        activityWechatContentBinding.contentViewpager.setAdapter(contentViewPagerAdapter);
    }

    @Override
    public Context getContext() {
        return WechatContentActivity.this;
    }

    @Override
    public void onBackPressed() {

        Glide.with(activityWechatContentBinding.contentToolimage.getContext())
                .load(listBean.getFirstImg())
                .error(R.drawable.walle)
                .fitCenter()
                .into(activityWechatContentBinding.contentToolimage);
        if(Build.VERSION.SDK_INT >= 21){
            finishAfterTransition();
        }
        super.onBackPressed();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
