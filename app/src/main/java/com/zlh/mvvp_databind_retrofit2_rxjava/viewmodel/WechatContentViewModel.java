package com.zlh.mvvp_databind_retrofit2_rxjava.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zlh.mvvp_databind_retrofit2_rxjava.interf.BaseInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.interf.WechatSelectionInterf;
import com.zlh.mvvp_databind_retrofit2_rxjava.model.WechatSelectionBean;

/**
 * Created by sdbean-zlh on 16/5/26.
 */
public class WechatContentViewModel implements WechatSelectionInterf.ViewModel{

    private BaseInterf.MainView mMainView;
    private Context mContext;
    private WechatSelectionBean.ResultBean.ListBean listBean;

    public String weburl;

    public String getWeburl() {
        return listBean.getUrl();
    }

    public WechatContentViewModel(BaseInterf.MainView mainView, WechatSelectionBean.ResultBean.ListBean bean, Context context) {
        mMainView = mainView;
        mContext = context;
        listBean = bean;

    }

    @BindingAdapter({"webLoadUrl"})
    public static void loadWebView(WebView webView,String url){
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void destory() {

    }
}
