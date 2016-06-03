package com.zlh.mvvp_databind_retrofit2_rxjava.app;

import android.app.Application;
import android.content.Context;

import com.zlh.mvvp_databind_retrofit2_rxjava.network.NetWorkManager;
import com.zlh.mvvp_databind_retrofit2_rxjava.network.api.WechatSelectionApi;

/**
 * Created by sdbean-zlh on 16/5/21.
 */
public class WechatSelectionApplication extends Application{
    private WechatSelectionApi wechatSelectionApi;

    public static WechatSelectionApplication get(Context context){

        return (WechatSelectionApplication)context.getApplicationContext();
    }

    public static WechatSelectionApplication create(Context context){

        return WechatSelectionApplication.get(context);
    }

    public WechatSelectionApi getWechatSelectionNetwork(){
        if(wechatSelectionApi == null){
            wechatSelectionApi = NetWorkManager.getInstent().getWechatSelectionApi();
        }
        return wechatSelectionApi;
    }
}
